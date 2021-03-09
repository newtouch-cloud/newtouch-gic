package com.ca.cacore.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.cacore.mass.dao.commonSeq.ICommonSeqDao;
import com.newtouch.core.returnmsg.Message;
import com.newtouch.utils.stringutil.StringUtil;

/**
* @since:    2014年3月3日   
* @author    ss
* @description:获取有格式的非重复的id号
*/
@Service
public class GetFormatId {
	@Autowired private ICommonSeqDao seqDao;
	/** 
	* 根据长度和序列名称生成有规则的非重复id
	* @param length长度
	* @param sequence_name序列名称
	* @return String
	* @description:
	*/
	public String createSalesId(int length,String sequence_name){
		String seq_code=seqDao.queryCommonSeq(sequence_name);
		try {
			//调用现成的方法对取出的识别码进行10位补0
			seq_code=StringUtil.alignLeft(seq_code, length);
		} catch (Message e) {
			e.printStackTrace();
		}
		return seq_code;
	}
}
