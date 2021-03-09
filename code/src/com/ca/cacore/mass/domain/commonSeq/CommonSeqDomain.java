package com.ca.cacore.mass.domain.commonSeq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;

/**
* @since:    2013年12月16日
* @author   wang_ds
* @description:查询指定的Sequence的下一个值
*/
@Service
public class CommonSeqDomain  implements ICommonSeqDomain{
@Autowired 	ICommonSeqDao commonSeqDao;

	public String queryCommonSeq(String seq_name){
		return commonSeqDao.queryCommonSeq(seq_name);
	}
	
}
