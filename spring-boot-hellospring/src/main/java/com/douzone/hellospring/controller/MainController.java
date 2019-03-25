package com.douzone.hellospring.controller;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	//responsebody -> 바로 화면에 나오기위해 jsp 파일이 아니라 return string이 바로나옴
 	@ResponseBody
	//매서드 단독매핑
	@RequestMapping({"/main","/main!"})
	public String main() {
		return "main()";
	}
	
	
	//responsebody -> 바로 화면에 나오기위해
		@ResponseBody
		//매서드 단독매핑
		@RequestMapping("/main/a/b/c/d")
		public String main2() {
			return "MainController:main2()";
		}

		
		
		/*
		 * 비추천
		 * 기술이 침투되어 있다.
		*/
		@ResponseBody
		@RequestMapping("/main3")
		public String main3(HttpServletRequest request,Writer out) {
			
			//jsp 처럼 가능하긴 하나 이렇게 하면 이식성이 떨어짐. 
			String name = request.getParameter("n");
			return "MainController:main3()";
		}
}
