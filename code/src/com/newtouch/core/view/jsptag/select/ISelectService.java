package com.newtouch.core.view.jsptag.select;

import java.util.List;
import java.util.Map;

import com.newtouch.core.dbconnection.handle.DBHandleable;

public interface ISelectService {

	public List<Map<String, Object>> queryList(Map<String, Object> queryObj,
			DBHandleable dbHandle);
}
