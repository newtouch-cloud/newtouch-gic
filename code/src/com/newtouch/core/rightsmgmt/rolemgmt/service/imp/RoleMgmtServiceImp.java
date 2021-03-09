package com.newtouch.core.rightsmgmt.rolemgmt.service.imp;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.core.dbconnection.handle.DBHandleCreator;
import com.newtouch.core.dbconnection.handle.QuerySqlable;
import com.newtouch.core.dbconnection.handle.UpdateSqlable;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.core.returnmsg.ReturnMsg;
import com.newtouch.core.rightsmgmt.dao.T_Data_AuthDao;
import com.newtouch.core.rightsmgmt.dao.T_Data_Auth_TypeDao;
import com.newtouch.core.rightsmgmt.dao.T_Operator_RolesDao;
import com.newtouch.core.rightsmgmt.dao.T_RolesDao;
import com.newtouch.core.rightsmgmt.rolemgmt.service.IRoleMgmtService;
import com.newtouch.core.serverbase.ServerBase;
import com.newtouch.utils.dateutil.DateUtil;
import com.newtouch.utils.stringutil.StringUtil;


@Service
public class RoleMgmtServiceImp extends ServerBase implements IRoleMgmtService {
	@Autowired
	private T_RolesDao rolesDao;
	@Autowired
	private T_Data_AuthDao rolAuthDao;
	@Autowired
	private T_Data_Auth_TypeDao authTypeDate;
	@Autowired
	private T_Operator_RolesDao oper_user_dao;

	public ReturnMsg queryRoleList(Map<String, Object> map) {
		ReturnMsg returnMsg = new ReturnMsg();
		String sql = "SELECT tr.role_no, tr.role_name, tr.role_type, tr.role_type as re#opt_type#role_typen , "
				+ "          CASE WHEN COUNT(r2m.serno) = 0 THEN '未添加' ELSE '已添加' END menu_info, "
				+ "          CASE WHEN COUNT(r2o.serno) = 0 THEN '未添加' ELSE '已添加' END user_info "
				+ "     FROM t_roles tr, t_data_auth r2m, t_operator_roles r2o "
				+ "    WHERE tr.role_no = r2m.object_no(+) "
				+ "      AND tr.role_no = r2o.role_no(+) "
				+ "      AND r2m.data_auth_type(+) = ? AND r2m.object_type(+) = ?";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.add("MENU");
		query.add("ROLE");
		if (map.get("role_no") != null && !"".equals(map.get("role_no"))) {
			sql += " AND tr.role_no = ?";
			query.add(map.get("role_no"));
		}
		if (map.get("role_name") != null && !"".equals(map.get("role_name"))) {
			sql += " AND tr.role_name like ?";
			query.add("%" + map.get("role_name") + "%");
		}
		if (map.get("role_type") != null && !"".equals(map.get("role_type"))) {
			sql += " AND role_type=?";
			query.add(map.get("role_type"));
		}
		sql += " group by tr.role_no, tr.role_name, tr.role_type, tr.crt_date order by tr.crt_date desc";
		query.setSql(sql);
		query.setOrderBy("tr.role_no");
		query.setOrderBy("tr.crt_date");
		returnMsg.setDataList(this.dbHandle().queryList(query));
		return returnMsg;
	}

	@Override
	public ReturnMsg addRole(Map<String, Object> map) {
		ReturnMsg reutn = this.checkRole(map, "add");
		if (!reutn.isSuccessflag()) {
			return reutn;
		}
		rolesDao.insert(map);
		return reutn;
	}

	private ReturnMsg checkRole(Map<String, Object> map, String type) {
		ReturnMsg reutn = new ReturnMsg();
		if (StringUtil.isNull(map.get("role_no"))) {
			reutn.setFailMessage(new Message("", "角色编码不能为空"));
		}
		if (StringUtil.isNull(map.get("role_name"))) {
			reutn.setFailMessage(new Message("", "角色名称不能为空"));
		}
		if (StringUtil.isNull(map.get("role_type"))) {
			reutn.setFailMessage(new Message("", "角色类型不能为空"));
		}
		if (type.equals("add")) {
			Map isExistMap = new HashMap();
			isExistMap.put("role_no", map.get("role_no"));
			Map<String, Object> existObj = rolesDao.query(isExistMap, false);
			if (existObj.size() > 0) {
				reutn.setFailMessage(new Message("该角色已经存在，请重新录入！"));
			}
		}
		return reutn;
	}

	@Override
	public ReturnMsg mdfRoleBaseInfo(Map<String, Object> map) {
		ReturnMsg reutn = this.checkRole(map, "mdf");
		if (!reutn.isSuccessflag()) {
			return reutn;
		}
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("role_no", map.get("role_no"));
		rolesDao.update(where, map);
		return reutn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReturnMsg mdfRoleAuthInfo(Map<String, Object> map) {
		ReturnMsg reutn = new ReturnMsg();
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("object_no", map.get("role_no"));
		where.put("data_auth_type", "MENU");
		rolAuthDao.delete(where);
		Map<String, Object> treeList = (Map<String, Object>) map.get("treelist");
		if(treeList!=null){
			for (String key : treeList.keySet()) {
				Map<String, Object> aRoleAuth = (Map<String, Object>) treeList
						.get(key);
				aRoleAuth.put("object_no", map.get("role_no"));
				aRoleAuth.put("object_type", "ROLE");
				aRoleAuth.put("data_auth_type", map.get("data_auth_type"));
				aRoleAuth.put("data_auth_type", map.get("data_auth_type"));
				aRoleAuth.put("start_date", DateUtil.sysDate());
				aRoleAuth.put("end_date", DateUtil.maxDate());
				rolAuthDao.insert(aRoleAuth);
			}
		}
		return reutn;
	}

	@Override
	public ReturnMsg queryRoleAuthList(Map<String, Object> map) {
		Map<String, Object> authTypeMap = authTypeDate.query(map, false);
		String no = (String) authTypeMap.get("mapping_no");
		String name = (String) authTypeMap.get("mapping_name");
		String parent = (String) authTypeMap.get("mapping_parent");
		String mapping_rule = (String) authTypeMap.get("mapping_rule");
		String table_name = (String) authTypeMap.get("mapping_tab");
		String sql = "SELECT tm."
				+ no
				+ " data_auth_no, "
				+ "       tm."
				+ name
				+ " name, "
				+ "       tm."
				+ parent
				+ " parent_data_auth,          "
				+ "       CASE WHEN tr.object_no IS NOT NULL THEN ? ELSE ? END checked, "
				+ "       tr.data_auth_child, "
				+ "       tr.is_half_check halfCheck " + "  FROM " + table_name
				+ " tm, t_data_auth tr " + " WHERE tm." + no
				+ " = tr.data_auth_no(+) " + "   AND tr.object_no(+) = ? "
				+ "   AND tr.data_auth_type(+) = ? AND tr.object_type(+) = ? ";
		String[] rule = mapping_rule.split("##");
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql4Calc();
		query.add("true");
		query.add("false");
		query.add(map.get("role_no"));
		query.add(map.get("data_auth_type"));
		query.add("ROLE");
		String sub_rule[];
		for (int i = 0; i < rule.length; i++) {
			sub_rule = rule[i].replaceAll("'", "").split("=");
			sql += " AND tm." + sub_rule[0] + " = ? ";
			query.add(sub_rule[1]);
		}
		query.setSql(sql);
		query.setOrderBy("to_number(tm.parent_no), to_number(tm.menu_order)");
		ReturnMsg msg = new ReturnMsg();
		msg.setDataList(this.dbHandle().queryList(query));
		return msg;
	}

	@Override
	public ReturnMsg queryRoleUserList(Map<String, Object> map) {
		String sql = "SELECT op.opt_no, op.opt_name, op.dept_no rd#dept_name,          "
				+ " CASE WHEN tor.role_no =?  THEN ? ELSE ? END checked , tor.role_no,op.crt_date"
				+ " FROM t_operator op  LEFT JOIN t_operator_roles tor on op.opt_no = tor.opt_no"
				+ " where TOR.ROLE_NO is null or tor.role_no = ? AND tor.end_date = ?";
		QuerySqlable query = DBHandleCreator.getInstance().getQuerySql();
		query.add(map.get("role_no"));
		query.add("true");
		query.add("false");
		query.add(map.get("role_no"));
		query.add(DateUtil.maxDate());
		// query.add(this.user().getCurOptDeptList());
		if (!StringUtil.isNull(map.get("opt_no"))) {
			sql += "   AND op.opt_no = ? ";
			query.add(map.get("opt_no"));
		}
		if (!StringUtil.isNull(map.get("opt_name"))) {
			sql += "   AND op.opt_name like ? ";
			query.add("%" + map.get("opt_name") + "%");
		}
		if (!StringUtil.isNull(map.get("opt_type"))) {
			sql += "   AND op.opt_type = ? ";
			query.add(map.get("opt_type"));
		}
		sql+=" order by checked desc,op.crt_date desc ";
		query.setSql(sql);
		ReturnMsg msg = new ReturnMsg();
		msg.setDataList(this.dbHandle().queryList(query));
		return msg;
	}

	@Override
	public ReturnMsg deleteRoleUserList(Map<String, Object> map) {
		ReturnMsg msg = new ReturnMsg();
		Map<String, Object> checkbox = (Map<String, Object>) map
				.get("checkbox");
		if (checkbox != null) {
			for (String key : checkbox.keySet()) {
				Map<String, Object> role = (Map<String, Object>) checkbox
						.get(key);
				rolesDao.delete(role);
				role.put("object_no", role.get("role_no"));
				rolAuthDao.delete(role);
			}
		}
		return msg;
	}
	/**
	 * 
	 * yanqiguang
	 * 2017年11月30日下午3:10:49
	 * TODO 修改再用户页面 添加角色关系逻辑，一个用户只能绑定一个角色
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReturnMsg addRoleUserList(Map<String, Object> map,String current,String target) {
		ReturnMsg reutn = new ReturnMsg();
		Map<String, Object> mapList = (HashMap<String, Object>) map.get("checkbox");
		/*if (mapList == null)
			mapList = new HashMap<String, Object>();
		if(mapList.size()>1){
			reutn.setFailMessage(new Message("一个用户只能有一个角色"));
			return reutn;
		}*/
		//删除当前用户的所有角色
		UpdateSqlable delete =DBHandleCreator.getInstance().getUpdateSql();
		String sql_delete = "DELETE FROM t_operator_roles WHERE opt_no =? ";
		delete.add(map.get("opt_no"));
		delete.setSql(sql_delete);
		this.dbHandle().update(delete);
		//再新增用户和角色关系
		Set<String> keySet = mapList.keySet();
		Map<String, Object> oneRow = new HashMap<String, Object>();
		for (String key : keySet) {
				oneRow.put("opt_no", map.get("opt_no"));
				oneRow.put("role_no",((Map<String,Object>)mapList.get(key)).get("role_no") );
				oneRow.put("end_date", DateUtil.maxDate());
				oneRow.put("start_date", DateUtil.sysDate());
				oper_user_dao.insert(oneRow);
		}
		String sql = "DELETE FROM t_operator_roles WHERE start_date > end_date";
		UpdateSqlable update = DBHandleCreator.getInstance().getUpdateSql();
		update.setSql(sql);// 针对当天增加，当天取消的，需要删除掉错误数据
		this.dbHandle().update(update);
		return reutn;
	}
	/**
	 * 
	 * yanqiguang
	 * 2017年11月30日下午3:11:18
	 * TODO 剥离用户-角色的两个页面用一个逻辑  一个角色可以对应多个用户
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReturnMsg addRoleRelationUser(Map<String, Object> map, String string, String string2) {
		ReturnMsg reutn = new ReturnMsg();
		Map<String, Object> checkbox_List = (HashMap<String, Object>) map.get("checkbox");
		
		
		QuerySqlable query_RoleList=DBHandleCreator.getInstance().getQuerySql();
		String sql_RoleList="SELECT opt_no FROM t_operator_roles  where  role_no = ? ";
		query_RoleList.add(map.get("role_no"));
		query_RoleList.setSql(sql_RoleList);
		List<Map<String,Object>> Rolelist=this.dbHandle().queryList(query_RoleList);
		
		QuerySqlable query_UserList=DBHandleCreator.getInstance().getQuerySql();
		String sql_UserList="SELECT op.opt_no, op.opt_name, op.dept_no rd#dept_name,          "
				+ " CASE WHEN tor.role_no is NOT NULL THEN ? ELSE ? END checked , tor.role_no"
				+ " FROM t_operator op  LEFT JOIN t_operator_roles tor on op.opt_no = tor.opt_no"
				+ " where TOR.ROLE_NO is null or tor.role_no = ?  AND tor.end_date = ?";
		//query_UserList.add(map.get("role_no"));
		query_UserList.add("true");
		query_UserList.add("false");
		
		query_UserList.add(map.get("role_no"));
		query_UserList.add(DateUtil.maxDate());
		query_UserList.setSql(sql_UserList);
		List<Map<String,Object>> userlist=this.dbHandle().queryList(query_UserList);
		//用Iterator 进行遍历
		//checkbox 中的元素遍历器 
		for ( Iterator<Entry<String, Object>> it_map = checkbox_List.entrySet().iterator();it_map.hasNext();){
			 String opt_checkbox=(String) ((Map<String,Object>)it_map.next().getValue()).get("opt_no");
			 for ( Iterator<Map<String,Object>> it_list=Rolelist.iterator();it_list.hasNext();){
				 String opt_db=(String) it_list.next().get("opt_no");
				 if(opt_db.equals(opt_checkbox)){//两个相同时，代码checkox选中，同时数据库也有，删除两个集合中的元素
				
		 				it_list.remove();//
			 			it_map.remove();
			 		} 
			 }
		 }
		 //再遍历比较结束后，checkbox_List中opt_no时需要增加，userlist 中opt_no时要删除的
		    //删除	
//		 	if(userlist.size()>0){
//		 		for (int l=0;l<userlist.size();l++){
//		 			Map<String, Object> where = new HashMap<String, Object>();
//		 			where.put("opt_no", userlist.get(l).get("opt_no") );
//		 			where.put("role_no", map.get("role_no"));
//		 			oper_user_dao.delete(where);
//		 		}
//		 	}
		 	//新增
	
			Map<String, Object> oneRow = new HashMap<String, Object>();
			Iterator<Entry<String, Object>> it_map = checkbox_List.entrySet().iterator();
			if(checkbox_List.size()>0){
				while(it_map.hasNext()){
					System.out.println("000");
					for(int i = 0; i<userlist.size();i++){
						if(userlist.get(i).get("checked").equals("true")&&userlist.get(i).get("role_no").equals(null)){
							reutn.setFailMessage("该用户已有角色，不能选为该角色");
							return reutn;
						}	
					}
					oneRow.put("opt_no",(String) ((Map<String,Object>)it_map.next().getValue()).get("opt_no") );
					oneRow.put("role_no",map.get("role_no") );
					oneRow.put("end_date", DateUtil.maxDate());
					oneRow.put("start_date", DateUtil.sysDate());
					oper_user_dao.insert(oneRow);
				}
			}
		return reutn;
	}
	
	
	public static void main(String[] args) throws Message {
		
//		List list= new ArrayList();
//		list.add("a");
//		list.add("1");
//		list.add("b");
//		list.add("c");
//		list.add("1");
//		list.add("d");
//		
//		Iterator<Object> it = list.iterator();  
//		int i=1;
//		while(it.hasNext()){
//			if(it.next().equals("1")){
//				it.remove();
//			}
//		}
//		Iterator<Object> it1 = list.iterator();  
//		while(it1.hasNext()){
//			System.out.println(it1.next());
//		}
		//这是我刚才注释的地方记得取消！
//		int a=3;
//		String b=Integer.toString(a);
//		System.out.println(StrUtil.alignLeft(b, 3));
		
	}
}
