package com.douzone.mysite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.mysite.config.web.ResourceMappingConfig;
import com.douzone.mysite.config.web.SecurityConfig;

@Configuration
//@EnableWebMvc   //<mvc:anntaion-driven/> 이랑 똑같음.. defaultservlethandler 랑 같음
//@ComponentScan(value= {"com.douzone.mysite.controller","com.douzone.mysite.exception"})
@Import(value= {SecurityConfig.class,ResourceMappingConfig.class})
public class WebConfig {

	
	
}
