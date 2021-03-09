package com.newtouch.core.rightsmgmt.usermgmt.service.imp;

import com.ca.cacore.manage.model.bo.IUserModel;
import com.newtouch.component.c11assistant.TransHelper;
import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.dao.*;
import com.newtouch.core.rightsmgmt.model.ISuperiorModel;
import com.newtouch.core.rightsmgmt.model.SuperiorModel;
import com.newtouch.core.rightsmgmt.usermgmt.service.IUserMgmtService;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.md5.Md5;
import com.newtouch.utils.stringutil.StringUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserMgmtServiceImp extends ServerBase implements IUserMgmtService {
	private Logger log = Logger.getLogger(UserMgmtServiceImp.class);
	@Autowired
	private T_OperatorDao userDao;
	@Autowired
	private T_Operator_RolesDao oper_user_dao;
	@Autowired
	private T_Data_AuthDao oper_auth_dao;
	@Autowired
	private T_Data_Auth_TypeDao authTypeDate;
	@Autowired
	private Ins_Ca_User_BindingDao ca_opt_dao;
	@Autowired
	private ISuperiorDao superiorDao;
	

	public ReturnMsg queryUserList(Map<String, Object> map) {
		ReturnMsg returnMsg = new ReturnMsg();

		String sql = "select\n" + "distinct oper.opt_no,\n" + "oper.opt_name,\n" + "ins.usercode ins_usercode,\n"
				+ "case when oper.opt_type = 1 then '系统管理员' else '普通用户' end opt_type,\n"
				+ "case when oper.opt_status = 1 then '有效' else '无效' end opt_status,\n"
				+ "case when oper.opt_sex = 1 then '男' else '女' end opt_sex,\n"
				+ "oper.opt_mail,\n"
                + "oper.opt_phone,\n"
				+ "sys.branch_name branch_name,\n"
				+ "oper.dept_no dept_no,\n"
				+ "(select case when operauth.object_no is null then '未分配' else '已分配' end deptnumber from t_operator operat left join t_data_auth operauth on operat.opt_no =operauth.object_no and operauth.data_auth_type =? and operauth.object_type =? where oper.opt_no = operat.opt_no and rownum = 1) operauth,\n"
				+ "(select case when operrole.role_no is null then '未分配' else '已分配' end rolenumber  from t_operator operat left join t_operator_roles operrole on operat.opt_no =operrole.opt_no where oper.opt_no = operat.opt_no and rownum = 1) operrole,\n"
				+ "case when oper.opt_confer is null then  '未分配' else '已分配' end grantrole,\n" +"oper.crt_date\n"
				+ "from t_operator oper left join (select * from INS_CA_USER_BINDING g where g.status like '1') ins  on oper.opt_no=ins.ca_code\n"
				+ "left join sys_branch sys on oper.dept_no = sys.branch_id where 1=1 and sys.branch_id in (select id from (select distinct branch_id id,branch_name name,branch_parentid pid from sys_branch t start with t.branch_id in (select tda.data_auth_no from t_data_auth tda where tda.is_half_check is null and tda.object_no = ? and object_type = ?) connect by prior t.branch_id = branch_parentid))";
		
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.add("DEPT");
		query.add("USER");
		query.add(map.get("userName"));
		query.add("USER");
		
		if (map.get("user_name") != null && !"".equals(map.get("user_name"))) {
			sql += " AND oper.opt_name like '%" + map.get("user_name") + "%'";
		}
		if (map.get("user_no") != null && !"".equals(map.get("user_no"))) {
			sql += " AND oper.opt_no = ?";
			query.add(map.get("user_no"));
		}
		if (map.get("user_type") != null && !"".equals(map.get("user_type"))) {
			sql += " AND oper.opt_type=?";
			query.add(map.get("user_type"));
		}
		if (map.get("status") != null && !"".equals(map.get("status"))) {
			sql += " AND oper.opt_status=?";
			query.add(map.get("status"));
		}
		if (map.get("branch_id") != null && !"".equals(map.get("branch_id"))) {
			sql += " AND oper.dept_no=?";
			query.add(map.get("branch_id"));
		}
		
		sql += "order by oper.crt_date desc";
		query.setSql(sql);
		query.setOrderBy("oper.opt_no");
		returnMsg.setDataList(this.dbHandle().queryList(query));
		return returnMsg;
	}

	@Override
	public ReturnMsg addUserList(Map<String, Object> map) {
		ReturnMsg reutn = new ReturnMsg();
		reutn = checkUser(map);
		if (reutn.getDataTable() != null && reutn.getDataTable().size() > 0) {
			reutn.setFailMessage(new Message("", "用户代码已存在!"));
			return reutn;
		}
		/*
		 * reutn = checkUserName(map); if(reutn.getDataTable() != null &&
		 * reutn.getDataTable().size() > 0){ reutn.setFailMessage(new
		 * Message("","用户姓名已存在!")); return reutn; }
		 */
		map.put("opt_status", "1");
		map.put("start_date", DateUtil.sysDate());
		map.put("end_date", DateUtil.maxDate());
		map.put("dept_type", "01");// 部门类型默认为01
		map.put("opt_password", Md5.EncoderPwdByMd5(map.get("opt_password").toString()));
		map.put("dept_no", map.get("branch_id"));
		userDao.insert(map);
		reutn.setSuccessMessage("添加成功！");
		return reutn;
	}

	@Override
	public ReturnMsg addUserPathList(Map<String, Object> map) {
		ReturnMsg msg = new ReturnMsg();
		try {
			Date d = new Date();
//			GregorianCalendar gc = new GregorianCalendar();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//			gc.setTime(d);
//			gc.add(2, +3);
//			gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
			map.put("status", "0");
			map.put("start_date", sf.format(d));
			map.put("end_date", DateUtil.maxDate());
			map.put("opt_password", Md5.EncoderPwdByMd5(map.get("opt_password").toString()));
			userDao.addUserPathList(map);
			msg.setSuccessMessage("添加成功！");
		} catch (Exception e) {
			msg.setFailMessage(new Message("", "发生异常错误!"));
		}
		return msg;
	}

	/**
	 * 检查用户代码是否已存在
	 * 
	 * @param map
	 * @return
	 */
	public ReturnMsg checkUser(Map<String, Object> map) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String sql = "select * from t_operator where opt_no = ? ";
		query.add(map.get("opt_no"));
		if (!StringUtil.isNull(map.get("serno"))) {
			sql += "and serno <> ? ";
			query.add(map.get("serno"));
		}
		query.setSql(sql);
		ReturnMsg msg = new ReturnMsg();
		msg.setDataTable(this.dbHandle().query(query));
		return msg;
	}

	/**
	 * 
	 * @param map
	 * @return ReturnMsg
	 * @description:检查用户姓名是否存在
	 */
	public ReturnMsg checkUserName(Map<String, Object> map) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String sql = "select * from t_operator where opt_no=? and opt_name = ? ";
		query.add(map.get("opt_no"));
		query.add(map.get("opt_name"));
		if (!StringUtil.isNull(map.get("serno"))) {
			sql += "and serno <> ? ";
			query.add(map.get("serno"));
		}
		
		query.setSql(sql);
		ReturnMsg msg = new ReturnMsg();
		msg.setDataTable(this.dbHandle().query(query));
		return msg;
	}

	@Override
	public ReturnMsg queryUserRoleList(Map<String, Object> map) {
		ReturnMsg returnMsg = new ReturnMsg();
		String sql = "select distinct tr.role_no,tr.role_name,"
				+ "(select case when t2o.opt_no is null then '未添加' else '已添加' end opt_number  from t_roles roles left join t_operator_roles t2o on roles.role_no=t2o.role_no where tr.role_no = roles.role_no and rownum=1) opt_number,\n"
				+ "(select case when t2m.serno is null then '未分配' else '已分配' end memu_number from t_roles roles left join t_data_auth t2m on roles.role_no=t2m.object_no and t2m.object_type = ? where  tr.role_no = roles.role_no and rownum=1) menu_info,\n"
				+ " CASE WHEN tor.role_no IS NOT NULL THEN ? ELSE ? END checked , ? opt_no , tr.crt_date              "
				+ "  from t_roles tr, t_operator_roles tor " + " where tr.role_no = tor.role_no(+)   "
				+ "   AND tor.DATA_FLAG(+)=? AND tor.opt_no(+) = ?";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.add("ROLE");
		query.add("true");
		query.add("false");
		query.add(map.get("opt_no"));
		query.add("1");
		query.add(map.get("opt_no"));
		if (map.get("role_no") != null && !"".equals(map.get("role_no"))) {
			sql += " AND tr.role_no = ?";
			query.add(map.get("role_no"));
		}
		if (map.get("role_name") != null && !"".equals(map.get("role_name"))) {
			sql += " AND tr.role_name like '%" + map.get("role_name") + "%'";
		}
		sql += " order by checked desc, tr.crt_date desc";
		query.setSql(sql);

		returnMsg.setDataList(this.dbHandle().queryList(query));
		return returnMsg;
	}

	@Override
	public ReturnMsg addUserRoleList(Map<String, Object> map) {
		ReturnMsg reutn = new ReturnMsg();
		Map mapList = (HashMap) map.get("checkbox");
		Set<String> keySet = mapList.keySet();
		for (String key : keySet) {
			String role_no = (String) ((Map) mapList.get(key)).get("checkbox");
			String opt_no = (String) ((Map) mapList.get(key)).get("opt_no");
			map.put("role_no", role_no);
			map.put("opt_no", opt_no);
			map.put("start_date", DateUtil.sysDate());
			map.put("end_date", DateUtil.maxDate());
			Map<String, Object> existObj = oper_user_dao.query(map, false);
			if (existObj.size() > 0) {
				reutn.setFailMessage(new Message("该用户此权限已经存在，请重新选择！"));
				return reutn;
			}
			oper_user_dao.insert(map);
		}
		return reutn;
	}

	/**
	 * 
	 * yanqiguang 2017年11月27日下午4:38:21 TODO
	 * 数据权限改造：在修改完用户的数据权限后，需要修改session中的数据权限deptlist
	 */

	@Override
	public ReturnMsg addUserDeptList(Map<String, Object> map) {
		ReturnMsg reutn = new ReturnMsg();
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("object_no", map.get("opt_no"));
		where.put("data_auth_type", "DEPT");
		oper_auth_dao.delete(where);
		Map<String, Object> treeList = (Map<String, Object>) map.get("treelist");
		if (treeList != null) {
			for (String key : treeList.keySet()) {
				Map<String, Object> aRoleAuth = (Map<String, Object>) treeList.get(key);
				aRoleAuth.put("object_no", map.get("opt_no"));
				aRoleAuth.put("object_type", "USER");
				aRoleAuth.put("data_auth_type", "DEPT");
				aRoleAuth.put("start_date", DateUtil.sysDate());
				aRoleAuth.put("end_date", DateUtil.maxDate());
				oper_auth_dao.insert(aRoleAuth);
			}
		}
		// 修改完数据权限之后，更新session中deptlist数据
		// --add by yanqiguang 2017-11-27查询这当前人的数据权限，拼接为字符串。
		if (map.get("opt_no").equals(this.user().getOptID())) {
			List<Map<String, Object>> list_data_auth = getDataList((String) map.get("opt_no"));
			// 遍历拼接为（‘10’，‘101’）格式
			StringBuffer deptList = new StringBuffer("(");
			if (list_data_auth.size() > 0) {
				for (int k = 0; k < list_data_auth.size(); k++) {
					deptList.append("'");
					deptList.append(list_data_auth.get(k).get("id"));
					deptList.append("'");
					if ((list_data_auth.size() - 1) != k) {
						deptList.append(",");
					}
				}
			} else {
				deptList.append("'");
				deptList.append("'");
			}
			deptList.append(")");
			log.info(this.user().getOptID() + "的数据权限改为" + deptList.toString());
			this.user().setDeptList(deptList.toString());
		}
		return reutn;
	}

	/**
	 * 
	 * yanqiguang 下午5:24:30 TODO 修改机构树查询规则
	 */
	@Override
	public ReturnMsg queryUserAuthList(Map<String, Object> map) {
		Map<String, Object> authTypeMap = authTypeDate.query(map, false);
		String no = (String) authTypeMap.get("mapping_no");
		String name = (String) authTypeMap.get("mapping_name");
		String parent = (String) authTypeMap.get("mapping_parent");
		String mapping_rule = (String) authTypeMap.get("mapping_rule");
		String table_name = (String) authTypeMap.get("mapping_tab");
		String sql = "SELECT tm." + no + " data_auth_no, " + "       tm." + name + " name, " + "       tm." + parent
				+ " parent_data_auth,          " + "       CASE WHEN tr.serno IS NOT NULL THEN ? ELSE ? END checked, "
				+ "       tr.data_auth_child, " + "       tr.is_half_check halfCheck " + "  FROM " + table_name + " tm, t_data_auth tr "
				+ " WHERE tm." + no + " = tr.data_auth_no(+) " + "   AND tr.object_no(+) = ? "
				+ "   AND tr.data_auth_type(+) = ? AND tr.object_type(+) = ? ";
		String[] rule = mapping_rule.split("##");
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		query.add("true");
		query.add("false");
		query.add(map.get("opt_no"));
		query.add(map.get("data_auth_type"));
		query.add("USER");
		String sub_rule[];
		for (int i = 0; i < rule.length; i++) {
			sub_rule = rule[i].replaceAll("'", "").split("=");
			sql += " AND tm." + sub_rule[0] + " = ? ";
			query.add(sub_rule[1]);
		}
		// sql=sql+" and tm.branch_level<> ?";
		// query.add("5");
		query.setSql(sql);
		ReturnMsg msg = new ReturnMsg();
		List<Map<String, Object>> list_result = this.dbHandle().queryList(query);
		// 遍历取出父节点为全选状态的节点，然后查询起所有子节点
		List<String> list_parentnode = new ArrayList<String>();
		for (int i = 0; i < list_result.size(); i++) {
			String checked = (String) list_result.get(i).get("checked");
			String halfCheck = (String) list_result.get(i).get("halfcheck");
			if ("true".equals(checked) && ("".equals(halfCheck) || halfCheck == null)) {
				String branch_id = (String) list_result.get(i).get("data_auth_no");
				System.out.println(branch_id + list_result.get(i).get("checked") + list_result.get(i).get("halfcheck"));
				list_parentnode.add(branch_id);
			}
		}
		if (list_parentnode.size() != 0) {
			StringBuffer nodelist = new StringBuffer("(");
			for (int j = 0; j < list_parentnode.size(); j++) {
				nodelist.append("'");
				nodelist.append(list_parentnode.get(j));
				nodelist.append("'");
				if ((list_parentnode.size() - 1) != j) {
					nodelist.append(",");
				}
			}
			nodelist.append(")");
			System.out.println(nodelist.toString());
			QuerySqlable query_childnode = DBHandleCreator.getInstance().getQuerySql();
			String sql_childnode = "select DISTINCT branch_id,branch_name  from sys_branch  start with branch_id in " + nodelist.toString()
					+ "connect by prior branch_id=branch_parentid ";
			query_childnode.setPaginate(false);
			query_childnode.setSql(sql_childnode);
			// 查询所以要展示勾选的结果集，并放入list<String>中做对比。
			List<Map<String, Object>> list_childnode_result = this.dbHandle().queryList(query_childnode);
			List<String> list_childnode = new ArrayList<String>();
			for (int n = 0; n < list_childnode_result.size(); n++) {
				list_childnode.add((String) list_childnode_result.get(n).get("branch_id"));
			}
			// 判断节点是否属于全选节点下的子节点，然后修改checked字段为true
			for (int m = 0; m < list_result.size(); m++) {
				String data_auth_no = (String) list_result.get(m).get("data_auth_no");
				if (list_childnode.contains(data_auth_no)) {
					list_result.get(m).put("checked", "true");
				}
			}
			list_childnode = null;
		}
		list_parentnode = null;
		msg.setDataList(list_result);
		return msg;
	}

	public String queryIs4Sub(String empid) {

		String sql = "" + "select 	" + "is4sub	" + "from t_operator where opt_no=?";

		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		query.setSql(sql);
		query.add(empid);

		Map<String, Object> a = this.dbHandle().query(query);
		String is4sub = (String) a.get("is4sub");
		return is4sub;

	}

	@Override
	public ReturnMsg delUserDeptList(Map<String, Object> map) {
		ReturnMsg msg = new ReturnMsg();

		Map<String, Object> checkbox = (Map<String, Object>) map.get("checkbox");
		if (checkbox != null) {
			for (String key : checkbox.keySet()) {
				Map<String, Object> user = (Map<String, Object>) checkbox.get(key);
				userDao.delete(user);
				oper_user_dao.delete(user);
				user.put("object_no", user.get("opt_no"));
				oper_auth_dao.delete(user);
			}
		}
		return msg;
	}

	@Override
	public ReturnMsg queryUserMenuList(Map<String, Object> map) {
		ReturnMsg msg = new ReturnMsg();
		String sql = "select distinct roleauth.data_auth_no as id,menu.menu_name name,menu.parent_no pid,menu.menu_url aurl,menu.menu_order \n"
				+ "from t_operator operator,t_operator_roles operrole,t_data_auth roleauth,t_menu menu\n"
				+ "where operator.opt_no = operrole.opt_no\n" + "and operrole.role_no=roleauth.object_no\n"
				+ "and roleauth.data_auth_no=menu.menu_no\n"
				+ "and operator.opt_no=?  and roleauth.object_type=? and roleauth.is_display = ? ";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.add(map.get("opt_no"));
		query.add("ROLE");
		query.add(true);
		if (null == map.get("menu_type")) {
			sql += " ORDER BY to_number(menu.parent_no), to_number(menu.menu_order)";
		} else {
			sql += " and menu.menu_type = ? ORDER BY to_number(menu.parent_no), to_number(menu.menu_order)";
			query.add(map.get("menu_type"));
		}
		query.setPaginate(false);
		query.setSql(sql);
		msg.setDataList(this.dbHandle().queryList(query));
		return msg;
	}

	@Override
	public ReturnMsg queryUserByOptno(Map<String, Object> map) {
		ReturnMsg msg = new ReturnMsg();
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		String sql = "select ? dept_type ,t.dept_no ro#dept_no, t.dept_no, td.branch_name dept_name,t.serno, "
				+ "  t.opt_no,t.opt_name,t.opt_sex,t.opt_type,opt_mail "
				+ "     from t_operator t left join sys_branch td  on t.dept_no = td.branch_id" + "    WHERE  1=1  ";
		query.add("01");
		if (map.get("opt_no") != null && !"".equals(map.get("opt_no"))) {
			sql += " and opt_no = ?";
			query.add(map.get("opt_no"));
		}
		query.setSql(sql);
		Map<String, Object> result = this.dbHandle().query(query);
		msg.setDataTable(result);
		return msg;
	}

	@Override
	public ReturnMsg mdfUserDeptList(Map<String, Object> map) {
		ReturnMsg reutn = checkUser(map);
		if (reutn.getDataTable() != null && reutn.getDataTable().size() > 0) {
			reutn.setFailMessage(new Message("", "用户代码已存在!"));
			return reutn;
		}
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("opt_no", map.get("opt_no"));
		map.put("dept_type", "01");
		map.put("mdf_user", this.user().getOptName());
		userDao.update(where, map);
		return reutn;
	}

	@Override
	public ReturnMsg mdfUserPassWord(Map<String, Object> map) {
		ReturnMsg reutn = new ReturnMsg();
		if (!map.get("opt_password").equals(map.get("opt_conf_password"))) {
			reutn.setFailMessage(new Message("密码不一致，请重新录入！"));
			return reutn;
		} else {
			reutn.setSuccessMessage(new Message("修改成功"));
		}
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("opt_no", map.get("opt_no"));
		map.put("opt_password", Md5.EncoderPwdByMd5(map.get("opt_password").toString()));
		map.put("opt_conf_password", Md5.EncoderPwdByMd5(map.get("opt_conf_password").toString()));
		map.put("dept_type", "01");
		userDao.update(where, map);
		return reutn;
	}

	/**
	 * 在系统管理下的密码重置功能
	 */
	@Override
	public ReturnMsg restUserPassWord(Map<String, Object> map) {
		ReturnMsg reutn = new ReturnMsg();
		if (map.get("opt_name").equals(map.get("opt_name_login"))) {
			reutn.setFailMessage(new Message("不能修改自己的密码，请到右上角修改"));
		} else {
			reutn.setSuccessMessage(new Message("密码修改成功！"));
			Map<String, Object> where = new HashMap<String, Object>();
			where.put("opt_no", map.get("opt_no"));
			where.put("opt_name", map.get("opt_name"));
			map.put("opt_password", Md5.EncoderPwdByMd5(map.get("opt_password").toString()));
			map.put("opt_conf_password", Md5.EncoderPwdByMd5(map.get("opt_conf_password").toString()));
			userDao.update(where, map);
		}
		return reutn;
	}

	@Override
	public ReturnMsg queryUserMappingList(Map<String, Object> map) {
		ReturnMsg returnMsg = new ReturnMsg();
		String sql = "select distinct iu.usercode usercode,iu.username username,decode(icu.status,'1',icu.ca_code,'2','') ca_code2,decode(icu.status,'1',icu.ca_name,'2','') ca_name2 ,icu.status    from  cbs_smc_user iu left join  (select * from  ins_ca_user_binding w where w.status like '1') icu on iu.usercode=icu.usercode      where   iu.usercode not in (select a.usercode from ins_ca_user_binding a  where a.status like '1' and a.ca_code not like ? )";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.add(map.get("opt_no"));
		// query.add("ROLE");
		// query.add(map.get("opt_no"));
		if (map.get("usercode2") != null && !"".equals(map.get("usercode2"))) {
			sql += " AND iu.usercode = ?";
			query.add(map.get("usercode2"));
		}
		if (map.get("username2") != null && !"".equals(map.get("username2"))) {
			sql += " AND iu.username like '%" + map.get("username2") + "%'";
		}
		sql = sql + "  order by ca_code2";
		query.setSql(sql);

		List<Map<String, Object>> list = this.dbHandle().queryList(query);

		if (!list.isEmpty()) {
			for (Map<String, Object> user : list) {
				user.put("opt_no", map.get("opt_no"));
				user.put("opt_name", map.get("opt_name"));
			}
		}
		Map<String, Object> user_map = new HashMap<String, Object>();
		user_map.put("opt_no", map.get("opt_no"));
		user_map.put("opt_name", map.get("opt_name"));
		returnMsg.setDataTable(user_map);
		returnMsg.setDataList(list);
		return returnMsg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReturnMsg addUserMapping(Map<String, Object> map) {
		ReturnMsg reutn = new ReturnMsg();

		Map<String, Object> mapList = (HashMap<String, Object>) map.get("checkbox");
		Set<String> keySet = mapList.keySet();
		Map<String, Object> oneRow = new HashMap<String, Object>();
		for (String key : keySet) {
			oneRow = (Map<String, Object>) mapList.get(key);
		}

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("usercode", oneRow.get("usercode"));
		map2.put("username", oneRow.get("username"));
		map2.put("ca_code", oneRow.get("opt_no"));
		map2.put("ca_name", oneRow.get("opt_name"));
		int a = this.querySize4Edit(map2);// 如果返回结果大于0 ，则进行修改
		System.out.println(a);
		if (this.querySize4Edit(map2) > 0) {
			Map<String, Object> where = new HashMap<String, Object>();
			Map<String, Object> values = new HashMap<String, Object>();
			where.put("ca_code", map.get("opt_no"));
			values.put("status", "2");
			ca_opt_dao.update(where, values);
			ca_opt_dao.insert(map2);
		} else {
			ca_opt_dao.insert(map2);
		}
		return reutn;
	}

	// 判断是否存在绑定
	public int querySize4Edit(Map<String, Object> map) {
		String sql = "select * from ins_ca_user_binding icu  where icu.ca_code=?";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();

		query.add(map.get("ca_code"));
		query.setSql(sql);

		List<Map<String, Object>> list = this.dbHandle().queryList(query);

		return list.size();
	}

	public boolean editUser(Map<String, Object> map) {
		String opt_no = (String) map.get("opt_no");
		String is4Sub = (String) map.get("is4Sub");
		Map<String, Object> is4SubMap = new HashMap<String, Object>();
		is4SubMap.put("is4Sub", is4Sub);
		is4SubMap.put("mdf_user", this.user().getOptID());
		Map<String, Object> optnoMap = new HashMap<String, Object>();
		optnoMap.put("opt_no", opt_no);
		userDao.update(optnoMap, is4SubMap);
		return true;
	}

	/*
	 * public static void main(String[] args){ List list=new ArrayList<>();
	 * list.add("1"); list.add("2"); list.add("3"); String str="("; for (int i
	 * =0;i<list.size();i++){ str+="'"+list.get(i)+"'"; if(list.size()-1!=i){
	 * str+=","; } } str=str+")"; System.out.println(str); }
	 */
	/**
	 * 
	 * yanqiguang 下午3:57:29 TODO 查询当前用户的机构数据权限
	 */
	@Override
	public List<Map<String, Object>> getDataList(String opt_no) {
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		String sql = "	select id,name,pid from ( "
//				+ " select distinct branch_id id, branch_name name, branch_parentid pid from sys_branch t where  t.branch_id	in ( "
//				+ " select tda.data_auth_no	from t_data_auth tda where tda.is_half_check =? and tda.object_no = ? and object_type=?  )"
//				+ " and status = ? " + " union "
				+ " select distinct branch_id id, branch_name name, branch_parentid pid from sys_branch t   where 	t.status = ?  start with   "
				+ " t.branch_id	in ( select tda.data_auth_no	from t_data_auth tda where tda.is_half_check is null and tda.object_no = ? and object_type=?  )"
				+ " connect by prior t.branch_id=branch_parentid ) order by id";
//		query.add("false");
//		query.add(opt_no);
//		query.add("USER");
//		query.add("1");
		query.add("1");
		query.add(opt_no);
		query.add("USER");
		query.setSql(sql);
		query.setPaginate(false);
		return this.dbHandle().queryList(query);
	}

	@Override
	public ReturnMsg updateUserPassWord(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ReturnMsg reutn = new ReturnMsg();
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("opt_no", map.get("opt_no1"));
		map.put("opt_password", Md5.EncoderPwdByMd5(map.get("opt_password").toString()));
		map.put("opt_conf_password", Md5.EncoderPwdByMd5(map.get("opt_conf_password").toString()));
		userDao.update(where, map);
		reutn.setSuccessMessage(new Message("密码修改成功！"));
		return reutn;
	}

	@Override
	public SuperiorModel querySuperior(ISuperiorModel model) {
		// TODO Auto-generated method stub
		/*QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		String sql = "select superior_no from superior WHERE  1=1";
		if (map.get("opt_no") != null && !"".equals(map.get("opt_no"))) {
			sql += " and opt_no = ?";
			query.add(map.get("opt_no"));
		}
		query.setSql(sql);
		Map<String, Object> result = this.dbHandle().query(query);
		msg.setDataTable(result);*/
		
		return superiorDao.querySuperior(model);
	}

	@Override
	public ArrayList<SuperiorModel> querySuperiorList(ISuperiorModel model) {
		// TODO Auto-generated method stub
		return superiorDao.querySuperiorList(model);
	}

	@Override
	public ReturnMsg insertSuperior(ISuperiorModel model) {
		ReturnMsg msg = new ReturnMsg();
		// TODO Auto-generated method stub
		ISuperiorModel superior = superiorDao.querySuperior(model);
		if(superior!=null){
			msg.setFailMessage("该上级已存在");
			msg.setDataTable(TransHelper.obj2map(superior));
			return msg;
		}
		try{
			superiorDao.insertSuperior(model);
			msg.setSuccessMessage("保存成功");
		}catch(Exception e){
			msg.setFailMessage("保存失败");
			e.printStackTrace();
		}
		//保存成功后回显的是保存得当上级的中介公司机构,要想回显当前用户的就从用户表再查一遍
		//
		//ISuperiorModel model1 = superiorDao.queryOpt(model);
		//msg.setDataTable(TransHelper.obj2map(model1));
		msg.setDataTable(TransHelper.obj2map(model));
		return msg;
	}

	@Override
	public ReturnMsg updateSuperior(ISuperiorModel model) {
		// TODO Auto-generated method stub
		ReturnMsg msg = new ReturnMsg();
		try{
			superiorDao.updateSuperior(model);
			msg.setSuccessMessage("保存成功");
		}catch(Exception e){
			msg.setFailMessage("保存失败");
			e.printStackTrace();
		}
		ISuperiorModel superior = superiorDao.querySuperior(model);
		msg.setDataTable(TransHelper.obj2map(superior));
		return msg;
	}

	@Override
	public Map<String, Object> getInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ReturnMsg msg = new ReturnMsg();
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		String sql = "select sys.branch_id, sys.branch_name, se1.person_no opt_no, se1.person_name opt_name, se2.sex opt_sex, se2.email opt_mail, se2.phone opt_phone "
				+ "from sys_employee se1 left join sys_employee_info se2 on se1.person_no = se2.person_no and se2.status = 1"
				+ "left join sys_branch sys on se1.branch_id = sys.branch_id and sys.status = 1 where se1.status = 1 ";
		
		if (map.get("opt_no") != null && !"".equals(map.get("opt_no"))) {
			sql += " and se1.person_no = ?";
			query.add(map.get("opt_no"));
		}
		query.setSql(sql);
		Map<String, Object> result = this.dbHandle().query(query);
		return result;
	}

	@Override
	public Map<String, Object> queryUserDataAuth(IUserModel model) {
		// TODO Auto-generated method stub
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		String sql = "select count(*) flag from sys_branch where status = '1' and branch_level in('0','1') "
				+ "and branch_id in (SELECT DISTINCT branch_id FROM sys_branch t WHERE t.status = '1' "
				+ "START WITH t.branch_id IN (SELECT tda.data_auth_no FROM t_data_auth tda "
				+ "WHERE tda.is_half_check IS NULL AND tda.object_no = ? AND object_type = ?) "
				+ "CONNECT BY PRIOR t.branch_id = branch_parentid)";
		query.add(model.getUserName());
		query.add(model.getUserName());
		Map<String, Object> result = this.dbHandle().query(query);
		return result;
	}

	@Override
	public ReturnMsg goUpdateOptStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ReturnMsg msg = new ReturnMsg();
		Map<String, Object> where = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		//map1.put("opt_no",map.get("opt_no"));
		map1.put("opt_status", map.get("opt_status"));
		where.put("opt_no", map.get("opt_no"));
		try{
			userDao.update(where,map1);
			msg.setSuccessMessage("设置成功");
		}catch(Exception e){
			e.printStackTrace();
			msg.setFailMessage("设置失败");
		}
		return msg;
		
	}

	@Override
	public ArrayList<SuperiorModel> findSupersByBranchId(ISuperiorModel model) {
		// TODO Auto-generated method stub
		return superiorDao.findSupersByBranchId(model);
	}

	@Override
	public ArrayList<SuperiorModel> querySuperiorInfos(ISuperiorModel model) {
		// TODO Auto-generated method stub
		return superiorDao.querySuperiorInfos(model);
	}

	@Override
	public void deleteSuperior(ISuperiorModel model) {
		// TODO Auto-generated method stub
		superiorDao.deleteSuperior(model);
	}

	@Override
	public Boolean queryIsExistByName(String superior) {
		// TODO Auto-generated method stub
		return superiorDao.queryIsExistByName(superior);
	}

	@Override
	public ISuperiorModel queryOpt(ISuperiorModel model) {
		// TODO Auto-generated method stub
		return superiorDao.queryOpt(model);
	}

}
