package com.newtouch.utils.timer;

import com.newtouch.utils.timer.pojo.TimerPlan;

/**
 * 定时器接口类，需要定时运行的类均需要实现此接口
 * 
 * @author cn_shuai
 * 
 */
public interface TimerRunable extends Runnable {
	/**
	 * 更新定时器开始运行
	 */
	public void updateStrat();

	/**
	 * 更新定时器运行完成，并成功
	 */
	public void updateSucceed();

	/**
	 * 更新定时器运行完成，并失败
	 */
	public void updateFailed();

	/**
	 * 定时器可以运行
	 * 
	 * @return true:可以运行<br>
	 *         false:不可以运行
	 */
	public boolean isEligible();

	/**
	 * 抽取业务数据
	 * 
	 * @throws Exception
	 *             抽取数据错误
	 */
	public void pumpBizData() throws Exception;

	/**
	 * 持有本身的计算对象
	 */
	public void setTimerPlan(TimerPlan plan);

	/**
	 * 持有本身的计算对象
	 */
	public TimerPlan getTimerPlan();

	/**
	 * 总执行计划，用于多线程触发处理
	 */
	public void main();
}
