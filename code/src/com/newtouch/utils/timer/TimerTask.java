package com.newtouch.utils.timer;

import com.newtouch.core.context.SpringContext;
import com.newtouch.core.quanxianguanli.pojo.PageCount;
import com.newtouch.core.quanxianguanli.pojo.User;
import com.newtouch.core.serverbase.ServerBase;

public class TimerTask extends java.util.TimerTask {

	private TimerDispatch cmsTimer = null;
	private ServerBase serverBase = null;

	@Override
	public void run() {
		if (cmsTimer == null)
			this.createCMSTimer();
		if (cmsTimer == null)
			return;
		cmsTimer.cmsTimer();
	}

	private synchronized void createCMSTimer() {
		if (!SpringContext.isReady())
			return;
		if (cmsTimer != null)
			return;
		if (serverBase == null)
			serverBase = (ServerBase) SpringContext.getBean("serverbase");
		User user = new User();
		user.setOptID("timer");
		user.setDid("86");
		user.setMenuID("timertask");
		user.setFuncID("timertask");
		PageCount pageCount = new PageCount();
		serverBase.getThreadLocal().get().setUser(user);
		serverBase.getThreadLocal().get().setPageCount(pageCount);
		cmsTimer = (TimerDispatch) SpringContext.getBean("TimerDispatch");
	}
}
