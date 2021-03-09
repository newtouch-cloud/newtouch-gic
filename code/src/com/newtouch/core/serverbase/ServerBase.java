package com.newtouch.core.serverbase;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ca.cacore.ams.domain.IUserMgMtDomain;
import com.ca.cacore.ams.model.vo.IUserMgMtVOModel;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.DBHandleable;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.objfactory.pojo.ServerObj;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.utils.log.Ulog;

@Service("serverbase")
public class ServerBase implements ServerBaseable {

	public static final ThreadLocal<ServerObj> threadLocal = new ThreadLocal<ServerObj>();
	@Autowired private IUserMgMtDomain userMgmtDomain;

	
	@Override
	public IUserMgMtVOModel queryOptPathInfo(IUserMgMtVOModel model) {
		IUserMgMtVOModel m=userMgmtDomain.queryOptPathInfo(model);
		return m;
	}

	
	@Autowired
	private ServerObj dataSoruce = null;
	@Autowired
	@Qualifier("jdbcTemplateHandle")
	private DBHandleable dbHandle;

	public final ThreadLocal<ServerObj> getThreadLocal() {
		this.initThis();
		return threadLocal;
	}

	public final DBHandleable dbHandle() {
		this.initThis();
		return threadLocal.get().getDbHandle();
	}

	// public final void setDBHandle(DBHandleable dbHandle) {
	// this.dbHandle = dbHandle;
	// }

	public final User user() {
		this.initThis();
		return threadLocal.get().getUser();
	}

	public final void setUser(User user) {
		this.initThis();
		threadLocal.get().setUser(user);
	}

	public final PageCount getPageCount() {
		this.initThis();
		return threadLocal.get().getPageCount();
	}

	public final void setPageCount(PageCount pageCount) {
		this.initThis();
		threadLocal.get().setPageCount(pageCount);
	}

	private void initThis() {
		if (dataSoruce == null && dbHandle != null) {
			dataSoruce = dbHandle.getThreadLocal().get();
		}
		if (threadLocal.get() == null) {
			ServerObj serverObj = new ServerObj();
			serverObj.setDataSoruce(dataSoruce.getDataSoruce());
			threadLocal.set(serverObj);
		}

		if (threadLocal.get().getDbHandle() == null) {
			// threadLocal.get().setDbHandle(
			// DBHandleCreator.getInstance().getDBHandle(threadLocal));
			dbHandle.setThreadLocal(threadLocal);
			threadLocal.get().setDbHandle(dbHandle);
		}
	}

	@Override
	public void setDBHandle(DBHandleable dbHandle) {
		if (this.dbHandle == null)
			this.dbHandle = dbHandle;
		this.initThis();
		threadLocal.get().setDbHandle(dbHandle);
	}

	/**
	 * 为财富封装的一层，平台调用校验规则使用
	 * 
	 * @param registerCode
	 * @param param
	 * @return
	 * @throws
	 */
	public ReturnMsg executeValidateRule(String registerCode,
			Map<String, Object> param) throws Exception {
		return executeRul(registerCode, param);
	}

	/**
	 * 根据类名实例化类
	 * 
	 * @param registerCode
	 *            规则注册编号
	 * @param param
	 *            传入参数
	 * @param inputParams
	 *            传入参数
	 * @param outputParams
	 *            输出参数
	 * @return Map<String,Boolean> key：规则编号(rule_code)，value：规则返回值(true/false)
	 */

	@SuppressWarnings("rawtypes")
	private ReturnMsg executeRul(String registerCode, Map<String, Object> param) {

		ReturnMsg retMsg = new ReturnMsg();

		QuerySqlable querySql = DBHandleCreator.getInstance().getQuerySql();

		String sql = "select rule_code,rule_classname,rule_classmethod "
				+ " from t_register t_1 inner join t_register_rule_relations t_2 on t_1.register_id=t_2.register_id"
				+ " inner join t_base_rule t_3 on t_2.rule_id=t_3.rule_id "
				+ " where t_1.register_code=?   and  t_1.dept_type = ?  order by display_order";
		querySql.setSql(sql);
		querySql.add(registerCode);
		querySql.add(this.user().getDept_type());
		querySql.setPaginate(false);
		List<Map<String, Object>> ruleList = this.dbHandle()
				.queryList(querySql);
		if (ruleList == null || ruleList.size() == 0) {
			Message msg = new Message();
			// msg.setMsgCode(msgCode);
			msg.setMsgRemark("规则编号为" + registerCode + "的规则不存在！");
			retMsg.setFailMessage(msg);
			return retMsg;
		}
		for (Map<String, Object> rule : ruleList) {
			String className = (String) rule.get("rule_classname");
			String classMethod = (String) rule.get("rule_classmethod");
			Thread currentThread = Thread.currentThread();
			ClassLoader loader = currentThread.getContextClassLoader();
			try {
				Ulog.debug("规则调用[" + className + "." + classMethod + "]");
				Class<?> cls = loader.loadClass(className);
				Object obj = cls.newInstance();
				Object[] paraValues = null;
				if (param != null) {
					Class[] paraTypes = new Class[] { Map.class };
					Method method = obj.getClass().getMethod(classMethod,
							paraTypes);
					paraValues = new Object[] { param };
					ReturnMsg msg = (ReturnMsg) method.invoke(obj, paraValues);
					if (!msg.isSuccessflag()) {
						retMsg.setReturnMsg(msg);
					}
				}
			} catch (Exception e) {

				throw new RuntimeException(e.getMessage());
			}
		}
		return retMsg;
	}
}
