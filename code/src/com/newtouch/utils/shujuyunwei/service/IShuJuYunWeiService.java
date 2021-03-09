package com.newtouch.utils.shujuyunwei.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.newtouch.core.returnmsg.ReturnMsg;

@Service
public interface IShuJuYunWeiService {
	public ReturnMsg chiXinSql(Map<String, Object> param);

}
