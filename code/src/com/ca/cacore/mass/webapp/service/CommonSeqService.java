package com.ca.cacore.mass.webapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.mass.domain.commonSeq.ICommonSeqDomain;

/**
* @since:    2013年12月16日
* @author   wang_ds
* @description:查询指定的Sequence的下一个值
*/
@Service
public class CommonSeqService  implements ICommonSeqService{
@Autowired 	ICommonSeqDomain commonSeqDomain;

	public String queryCommonSeq(String seq_name){
		return commonSeqDomain.queryCommonSeq(seq_name);
	}
	
}
