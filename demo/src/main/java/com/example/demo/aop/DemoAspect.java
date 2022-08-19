package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.demo.controller.RqBody_user;

@Aspect
@Component
public class DemoAspect {

	/**
	 * @param joinPoint
	 * @param result
	 */
	@Before("execution(public * *..controller..*.*(..))")
	public void before(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (RqBody_user.class.equals(arg.getClass())) {
				RqBody_user rqBase = (RqBody_user) arg;
				System.out.println("Before getEmail: " + rqBase.getEmail());
			}
		}
	}

	/**
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(pointcut = "execution(public * *..controller..*.*(..)) && within(com.example..*)", returning = "result")
	public void after(JoinPoint joinPoint, Object result) {
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (RqBody_user.class.equals(arg.getClass())) {
				RqBody_user rqBody = (RqBody_user) arg;
				System.out.println("AfterReturning getEmail: " + rqBody.getEmail());
			}
		}
		
		if ( result != null ) {
			if( String.class.equals(result.getClass()) ) {
				System.out.println("AfterReturning getResult: " + (String)result);
			}
		}
	}
}
