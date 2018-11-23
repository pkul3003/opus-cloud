package com.anup.tutorials.sampleserviceaopsecurity.security;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;

@Configuration
@EnableLoadTimeWeaving(aspectjWeaving=AspectJWeaving.ENABLED)
@EnableAspectJAutoProxy
//@EnableSpringConfigured
public class AnnotationConfiguration {
	@Bean
	public DataSecurityAnnotationHandler dataSecurityAnnotationHandler() {
		System.out.println("Has Aspect : " + Aspects.hasAspect(DataSecurityAnnotationHandler.class));
		DataSecurityAnnotationHandler dataSecurityAnnotationHandler = null;
		if(Aspects.hasAspect(DataSecurityAnnotationHandler.class)) {
			dataSecurityAnnotationHandler = Aspects.aspectOf(DataSecurityAnnotationHandler.class);
		}
		return dataSecurityAnnotationHandler;
	}
	
	/*@Bean
	public DataSecurityAnnotationHandler dataSecurityAnnotationHandler() {
		return new DataSecurityAnnotationHandler();
	}*/
}
