package com.ca.cacore.mass.dao.commonSeq;


import org.springframework.stereotype.Component;
import com.newtouch.core.daobase.BaseDao;

/**
* @since:    2013年12月16日
* @author   wang_ds
* @description:查询指定的Sequence的下一个值
*/
@Component
public class CommonSeqDao extends BaseDao implements ICommonSeqDao{
	
	private String seq_name;
	
	public String getSeq_name() {
		return seq_name;
	}

	public void setSeq_name(String seq_name) {
		this.seq_name = seq_name;
	}

	public String queryCommonSeq(String seq_name){
		return (String)this.getSqlMapClientTemplate().queryForObject("Seq.queryNextVal", seq_name);
	}
	
}
