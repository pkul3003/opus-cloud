package com.anup.tutorials.sampleserviceaopsecurity.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.anup.tutorials.sampleserviceaopsecurity.security.handler.DataSecurityHandler;

@Aspect
public class DataSecurityAnnotationHandler {

	@Autowired
	private DataSecurityHandler dataSecurityHandler;
	
	@Around("execution(* com.anup.tutorials.sampleserviceaopsecurity.dao.entity.*.set*(..)) "
			+ "&& @annotation(com.anup.tutorials.sampleserviceaopsecurity.security.EnableSecure)")
	public void encryptData(ProceedingJoinPoint joinPoint) {
		final Object[] updatedArgs = new Object[joinPoint.getArgs().length];
		for (int i=0; i < updatedArgs.length;i++) {
			Object argObj = joinPoint.getArgs()[i];
			if(argObj.getClass().isAssignableFrom(String.class)) {
				updatedArgs[i] = dataSecurityHandler.encrypt((String)argObj);
			} else {
				updatedArgs[i] = argObj;
			}
		}
		try {
			joinPoint.proceed(updatedArgs);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Around("execution(* com.anup.tutorials.sampleserviceaopsecurity.dao.entity.*.get*()) "
			+ "&& @annotation(com.anup.tutorials.sampleserviceaopsecurity.security.EnableSecure)")
	public Object decryptData(ProceedingJoinPoint joinPoint) {
		try {
			final Object returnValue = joinPoint.proceed();
			if(returnValue.getClass().isAssignableFrom(String.class)) {
				return dataSecurityHandler.decrypt((String)returnValue);
			} else {
				return returnValue;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
}
