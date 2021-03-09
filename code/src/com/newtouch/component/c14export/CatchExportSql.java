package com.newtouch.component.c14export;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import com.newtouch.core.daobase.BaseDao;
@Component
public class CatchExportSql extends BaseDao{
	public String getSql(String sqlId,Object params){
		String sqlStr = "";
		SqlMapClientImpl sqlmap = (SqlMapClientImpl) this.getSqlMapClient();  
		/**获取隐身对象*/
		MappedStatement stmt = sqlmap.getMappedStatement(sqlId); 
		Sql sql = stmt.getSql();
		/**获取规则*/
		SessionScope sessionScope = new SessionScope();   
		sessionScope.incrementRequestStackDepth();   
		StatementScope statementScope = new StatementScope(sessionScope);   
		stmt.initRequest(statementScope);   
		stmt.getCacheKey(statementScope, params);
		/**获取sql映射对象*/
		sqlStr = sql.getSql(statementScope, params);
		Object[] sqlParam = sql.getParameterMap(statementScope, params).getParameterObjectValues(statementScope, params);
		for (Object object : sqlParam) {
			System.out.println(object.toString());
			if(object instanceof String){
				System.out.println("string:"+object.toString());
				sqlStr=	sqlStr.replaceFirst("\\?", "'"+ object.toString()+"'");
			}else if(object instanceof Integer){
				sqlStr=	sqlStr.replaceFirst("\\?", object.toString());
			}else if (object instanceof Date) {
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				sqlStr=sqlStr.replaceFirst("\\?", "date"+format.format(object));
			}else {
				sqlStr.replaceFirst("\\?", object.toString());
			}
			System.out.println(sqlStr);
		}
		return sqlStr;
	}

}
