package com.newtouch.core.view.jsptag.button;

import com.newtouch.core.dbconnection.handle.DBHandleable;

public interface IButtonService {

	public boolean query(DBHandleable dbHandle, String name, String roleId,
			String insurerch_code);
}
