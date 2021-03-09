package com.newtouch.core.dbconnection;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newtouch.utils.observer.initparam.ParamObservable;
import com.newtouch.utils.observer.initparam.ParamSubject;

public class SpringFactory implements ParamObservable {

	private static SpringFactory springFactor = null;
	private static BeanFactory beanFactory = null;

	/**
	 * 获取默认数据库连接
	 * 
	 * @return 数据库连接
	 */
	public DataSource getDataSource() {
		return this.getDataSource("defaultDataSource");
	}

	/**
	 * 获取指定库的数据库连接
	 * 
	 * @param dataSource
	 *            指定库名
	 * @return 数据库连接
	 */
	public DataSource getDataSource(String dataSource) {
		return (DataSource) beanFactory.getBean(dataSource);
	}

	/**
	 * 初始化参数，并声明为观察者
	 */
	private SpringFactory() {
		this.reloadParam();
		ParamSubject.getInstance().addParamObserver(this);
	}

	/**
	 * 得到数据库操作实例
	 * 
	 * @return
	 */
	public synchronized static SpringFactory getInstance() {
		if (springFactor == null) {
			springFactor = new SpringFactory();
		}
		return springFactor;
	}

	public synchronized void reloadParam() {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		beanFactory = (BeanFactory) appContext;
	}

}
