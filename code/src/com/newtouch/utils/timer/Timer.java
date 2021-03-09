package com.newtouch.utils.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Timer implements ServletContextListener {
	private java.util.Timer timer = null;

	public void contextInitialized(ServletContextEvent arg0) {
		// 创建定时器
		timer = new java.util.Timer(true);
		TimerTask cmsTimer = new TimerTask();
		System.out.println("定时器启动 CMSTimer");
		timer.schedule(cmsTimer, 0, 60 * 60 * 1000);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		if (timer != null) {
			timer.cancel();
			System.out.println("定时器已销毁 CMSTimer");
		}
	}
}
