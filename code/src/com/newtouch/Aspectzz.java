package com.newtouch;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class Aspectzz {
	// 匹配org.sun.service.impl包下的所有类的、所有方法的执行作为切入点
	@Before("execution(* com..*Controller.*(..)) || execution(* com..*ServiceImp.*(..)) || execution(* com..*Service.*(..))")
	public void BeforeLog() {
		System.out.println("模拟权限检查");
	}
}
