package com.newtouch.utils.timer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.dateutil.DateUtil.DateType;
import com.newtouch.utils.stringutil.StringUtil;
import com.newtouch.utils.timer.pojo.TimerPlan;
import com.newtouch.utils.uniqueseq.UniqueSeq;

/**
 * 定时器抽象类
 * 
 */
public abstract class AbsTimerRunable extends ServerBase implements
		TimerRunable {

	public static final String DAY = "D";// 执行频率天
	public static final String WEEK = "W";// 执行频率周
	public static final String MONTH = "M";// 执行频率月
	public static final String YEAR = "Y";// 执行频率年

	public static final String LAST = "L";// 月的最后一天

	protected TimerPlan timerPlan = new TimerPlan();

	public void run() {
		main();
	}

	public void main() {
		TimerPlan plan = this.getTimerPlan();
		String sql = "SELECT * FROM t_timerschedule WHERE plancode = ? AND enddate IS NULL";
		QuerySqlable querySql = DBHandleCreator.getInstance().getQuerySql();
		querySql.setSql(sql);
		List<Map<String, Object>> queryList = null;
		boolean isOutTime = true;
		try {
			this.dbHandle().startTransaction();
			querySql.clearParam();
			querySql.add(plan.getPlanCode());
			// 判断上次状态是否结束
			querySql.setLocalOrder(false);
			querySql.setPaginate(false);
			queryList = this.dbHandle().queryList(querySql);
			for (Map<String, Object> map : queryList) {
				// 若未结束，判断是否超时，若超时则设置结束，并更新状态为失败
				isOutTime = this.checkTimeout((Timestamp) map
						.get("touchoffdate"));
			}
			// 未超时，则不重新抽取
			if (!isOutTime)
				return;
			// 判断是否可以开始抽取
			if (!this.isEligible())
				return;
			// 设置开始
			this.updateStrat();
			// 抽取业务逻辑
			this.pumpBizData();
			// 设置成功结束
			this.updateSucceed();
		} catch (Throwable e) {
			// 设置失败结束
			this.updateFailed();
			this.dbHandle().rollbackTransaction();
			e.printStackTrace();
		} finally {
			this.dbHandle().commitTransaction();
		}
	}

	public boolean isEligible() {
		// 如果今天已经成功抽取过一次了，则不再抽取，如果没有，则继续抽取。
		String sql = "SELECT * FROM t_timerschedule WHERE plancode = ? AND status = ? AND touchoffdate = ? ";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		query.setSql(sql);
		query.add(this.timerPlan.getPlanCode());
		query.add("3");
		query.add(DateUtil.sysDate());
		List<Map<String, Object>> mapList = this.dbHandle().queryList(query);
		if (!mapList.isEmpty()) {
			return false;// 不为空表示有过成功抽取，不可以计算
		}
		// 前后两小时之内可以抽取，否则不可以抽取
		Calendar cal = Calendar.getInstance();
		String time = this.getTimeByPlan(this.timerPlan);
		if (time == null || "".equals(time)) {
			return false;
		}

		String[] args = time.split(":");
		// 设置计划执行时间_小时
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(args[0]));
		// 设置计划执行时间_分钟
		cal.set(Calendar.MINUTE, Integer.parseInt(args[1]));
		// 设置计划执行时间_前两小时
		cal.add(Calendar.HOUR_OF_DAY, -2);
		// 计划执行前两小时
		Timestamp stDate = new Timestamp(cal.getTimeInMillis());

		cal.add(Calendar.HOUR_OF_DAY, 4);// 后两小时
		// 计划执行后两小时
		Timestamp enDate = new Timestamp(cal.getTimeInMillis());

		// 当前时间在计划执行两小时之前，或当前时间在计划执行时间两小时之后，则不可以计算
		if (DateUtil.sysTimestamp().before(stDate)
				|| DateUtil.sysTimestamp().after(enDate)) {
			return false;
		}
		return true;
	}

	/**
	 * 更新定时器开始运行
	 */
	public void updateStrat() {
		Connection con = this.dbHandle().getConnection();
		String sql = "INSERT INTO t_timerschedule "
				+ "  (e_id, plancode, touchoffdate, startdate,  status, crtdate, optdate, operator, dataflag) "
				+ " VALUES                     "
				+ "  (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		PreparedStatement stat = null;
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1, UniqueSeq.getUniqueSeq("e_id").toString());
			stat.setString(2, this.timerPlan.getPlanCode());
			stat.setTimestamp(3, DateUtil.sysTimestamp());
			stat.setTimestamp(4, DateUtil.sysTimestamp());
			stat.setString(5, "2");
			stat.setTimestamp(6, DateUtil.sysTimestamp());
			stat.setTimestamp(7, DateUtil.sysTimestamp());
			stat.setString(8, this.user().getOptID());
			stat.setString(9, "1");
			stat.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新定时器运行完成，并成功
	 */
	public void updateSucceed() {
		Connection con = this.dbHandle().getConnection();
		String sql = "UPDATE t_TimerSchedule "
				+ "      SET enddate = ?, status = ?, optdate = ?, operator = ? "
				+ "    WHERE plancode = ? AND enddate IS NULL";
		PreparedStatement stat = null;
		try {
			stat = con.prepareStatement(sql);
			stat.setTimestamp(1, DateUtil.sysTimestamp());
			stat.setString(2, "3");
			stat.setTimestamp(3, DateUtil.sysTimestamp());
			stat.setString(4, this.user().getOptID());
			stat.setString(5, this.timerPlan.getPlanCode());
			stat.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新定时器运行完成，并失败
	 */
	public void updateFailed() {
		Connection con = this.dbHandle().getConnection();
		String sql = "UPDATE t_TimerSchedule "
				+ "      SET enddate = ?, status = ?, optdate = ?, operator = ? "
				+ "    WHERE plancode = ? AND enddate IS NULL";
		PreparedStatement stat = null;
		try {
			stat = con.prepareStatement(sql);
			stat.setTimestamp(1, DateUtil.sysTimestamp());
			stat.setString(2, "4");
			stat.setTimestamp(3, DateUtil.sysTimestamp());
			stat.setString(4, this.user().getOptID());
			stat.setString(5, this.timerPlan.getPlanCode());
			stat.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 持有本身的计算对象
	 */
	public void setTimerPlan(TimerPlan plan) {
		this.timerPlan = plan;
	}

	/**
	 * 持有本身的计算对象
	 */
	public TimerPlan getTimerPlan() {
		return this.timerPlan;
	}

	/*
	 * 通过频率、日期和时间计算出执行时间返回给主函数 调度运行频率(DWMY)(天、周、月、年) 1、D。当是天时(填写1表示每天执行)
	 * 2、W。1、2、3、4、5、6、7(周日、一、二、三、四、五、六) 3、M。1、2、3、4。。。。。。L(表示最后一天执行)
	 * 4、Y。日期格式(12-12)每年的12月12日执行
	 */
	private String getTimeByPlan(TimerPlan plan) {
		String time = "";
		String planFrequency = StringUtil.trimStr(plan.getPlanFrequency());
		String planDate = StringUtil.trimStr(plan.getPlanDate());
		String planTime = StringUtil.trimStr(plan.getPlanTime());

		// 按天执行
		if (DAY.equals(planFrequency)) {
			// 返回执行时间
			return planTime;
		}

		// 按周执行
		if (WEEK.equals(planFrequency)) {
			// 得到当前所属周(周一、周二、周三、....)
			// TODO 需要明确周一、周二、周三在数据库中如何配置
			String weekDay = String.valueOf(Calendar.getInstance().get(
					Calendar.DAY_OF_WEEK));
			// 判断与计划周是否相同
			if (weekDay.equals(planDate)) {
				// 返回计划执行时间
				return planTime;
			}
		}

		// 按月执行
		if (MONTH.equals(planFrequency)) {
			// 当前日期
			Date day = DateUtil.sysDate();
			// 最后一天
			Date lDay = DateUtil.getLastDayOfMonth(day);
			// 定义为最后一天执行，并且当前为最后一天
			if (LAST.equals(planDate) && day.equals(lDay)) {
				return planTime;
			}
			// 非最后一天执行，判断当前日期与定义是否相同
			String monthDay = String.valueOf(Calendar.getInstance().get(
					Calendar.DAY_OF_MONTH));
			// TODO 明确数据库如何定义
			if (monthDay.equals(planDate)) {
				return planTime;
			}
		}

		// 按年执行
		if (YEAR.equals(planFrequency)) {
			Date day = DateUtil.sysDate();
			// 做为当前的某月某日，某月某日为数据库定义
			Date yPlanDate = DateUtil.string2Date(DateUtil.date2String(day)
					.substring(0, 4) + "-" + planDate);
			if (DateUtil.compareDate(day, yPlanDate) == 0) {
				return planTime;
			}
		}
		return time;
	}

	/**
	 * 校验是否超时，若超时则更新为失败
	 * 
	 * @param date
	 *            触发日期
	 * @param timer
	 *            定时器对象
	 * @return true：已超时<br>
	 *         false：未超时
	 */
	private boolean checkTimeout(java.util.Date date) {
		// 得到触发日期到当前的小时数
		int hour = DateUtil.dateBetween(DateUtil.sysTimestamp(), date,
				DateType.HH);
		// 如果未超时，则直接返回
		if (hour < this.getTimerPlan().getTimeout()) {
			return false;
		}
		// 若超时则设置结束日期为当前，并更新状态为失败
		String sql = "UPDATE t_timerschedule "
				+ "   SET enddate = ?, status = ?, optdate = ?, operator = ? "
				+ " WHERE plancode = ? AND enddate IS NULL";
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		update.setSql(sql);
		// 状态：1 等待；2 开始；3 成功；4 失败
		update.setParameter(new Object[] { DateUtil.sysDate(), "4",
				DateUtil.sysDate(), this.user().getOptID(),
				this.getTimerPlan().getPlanCode() });
		this.dbHandle().update(update);
		return true;
	}

}
