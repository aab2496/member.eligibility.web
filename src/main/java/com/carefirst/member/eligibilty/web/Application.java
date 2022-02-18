package com.carefirst.member.eligibilty.web;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({"com.carefirst.member.eligibilty.web.*","com.carefirst.nexus.gen"})
public class Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		 registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
         .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}

}
