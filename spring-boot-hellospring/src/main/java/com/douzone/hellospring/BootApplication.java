package com.douzone.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//옛날 방식 얘 하려면 패키지에 .bootstrap
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan("com.douzone.hellospring.controller") 

@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}
