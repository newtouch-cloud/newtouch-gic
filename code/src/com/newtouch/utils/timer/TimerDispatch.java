package com.newtouch.utils.timer;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.stereotype.Component;

import com.newtouch.core.context.SpringContext;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.observer.initparam.ParamObservable;
import com.newtouch.utils.timer.pojo.TimerPlan;

/**
 * CMS系统定时器调度类
 * 
 * @author cn_shuai
 * 
 */
@Component
public class TimerDispatch extends ServerBase implements ParamObservable {
	private static List<TimerRunable> timerList = new Vector<TimerRunable>();

	// 如果timerList为空，则初始化
	public void cmsTimer() {
		if (timerList.isEmpty())
			this.reloadParam();
		for (TimerRunable timer : timerList) {
			Thread thread = new Thread((Runnable) timer);
			thread.start();
		}
	}

	public synchronized void reloadParam() {
		// 查询当前有效的数据
		String sql = "SELECT * FROM t_timerplan WHERE flag = ?";
		QuerySqlable querySql = DBHandleCreator.getInstance().getQuerySql();
		querySql.setSql(sql);
		querySql.add("1");
		querySql.setPaginate(false);
		querySql.setLocalOrder(true);
		List<Map<String, Object>> planList = this.dbHandle()
				.queryList(querySql);
		User user = new User();
		user.setOptID("Timer");
		timerList.clear();
		for (Map<String, Object> map : planList) {
			TimerPlan timerPlan = new TimerPlan();
			timerPlan.setPlanName((String) map.get("planame"));
			timerPlan.setPlanCode((String) map.get("plancode"));
			timerPlan.setPlanModule((String) map.get("planmodule"));
			timerPlan.setPlanMthd((String) map.get("planmthd"));
			timerPlan.setPlanFrequency((String) map.get("planfrequency"));
			timerPlan.setPlanDate((String) map.get("plandate"));
			timerPlan.setPlanTime((String) map.get("plantime"));
			timerPlan.setTimeout(Double.parseDouble(map.get("timeout")
					.toString()));
			timerPlan.setDbDriveName((String) map.get("dbdrive"));
			timerPlan.setDbUrl((String) map.get("dburl"));
			timerPlan.setDbUsername((String) map.get("dbusername"));
			timerPlan.setDbPassword((String) map.get("dbpassword"));
			timerPlan.setFlag((String) map.get("flag"));
			TimerRunable timer = null;
			try {
				timer = (TimerRunable) SpringContext.getBean(timerPlan
						.getPlanModule());
				timer.setTimerPlan(timerPlan);
				timerList.add(timer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
