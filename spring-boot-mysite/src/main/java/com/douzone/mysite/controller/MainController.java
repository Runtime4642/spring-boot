package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.UserVo;


@Controller
public class MainController {
	@Autowired
	private SiteService siteService;
	
	@RequestMapping({"/main",""})
	public String main(Model model) {
//		return "/WEB-INF/views/main/index.jsp"  view ViewResolver 설정을 해주면 밑에처럼 써주면됨 prefix + return + suffix
		model.addAttribute("siteVo",siteService.getMain());
		return "main/index";
	}
	
	@RequestMapping({"/weather"})
	public String weather() {
//		return "/WEB-INF/views/main/index.jsp"  view ViewResolver 설정을 해주면 밑에처럼 써주면됨 prefix + return + suffix
		return "main/weather";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "<h1>안녕하세요</h1>";
	}
	
	@ResponseBody
	@RequestMapping("/hello2")
	public JSONResult hello2() {
		return JSONResult.success(new UserVo());
	}
	

}
