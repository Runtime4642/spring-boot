package com.douzone.mysite.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	//java config 로 하면 defaultservlet으로 빠짐 아마도 매핑되는게 없어서 .. 그걸 방지하기위함. auth 작동시키려고
	@RequestMapping(value="/auth",method=RequestMethod.POST)
	public void auth() {
		
	}

	//java config 로 하면 defaultservlet으로 빠짐 아마도 매핑되는게 없어서 .. 그걸 방지하기위함. auth 작동시키려고
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public void logout() {
		
	}
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() {
		
		return "user/join";
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo  userVo,BindingResult result ,Model model) {
		if(result.hasErrors())
		{
		   model.addAllAttributes(result.getModel());
			return "user/join";
		}
		if(userService.join(userVo)==1)
		return "redirect:/user/joinsuccess";
		
		return "user/join";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	//interceptor 로 처리
//	@RequestMapping(value="/login",method=RequestMethod.POST)
//	public String login(HttpSession session,@ModelAttribute UserVo userVo,Model model)
//	{
//		UserVo authuser = userService.login(userVo.getEmail(), userVo.getPassword());
//		if(authuser==null) {
//			model.addAttribute("result",false);
//			return "user/login";
//		}
//		session.setAttribute("authuser", authuser);
//		return "redirect:/";
//	}
//	@RequestMapping("/logout")
//	public String logout(HttpSession session,@ModelAttribute UserVo userVo)
//	{
//		UserVo authUser=(UserVo)session.getAttribute("authuser");
//		if(session != null && session.getAttribute("authuser") != null)
//		{
//		// 로그아웃 처리
//		session.removeAttribute("authuser"); //--> 세션 삭제
//		session.invalidate(); // --> 세션아이디를 다시 만드는 매소드
//		}
//		return "redirect:/";
//	}
	
	@Auth
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser,Model model)
	{
	//	UserVo authUser=(UserVo)session.getAttribute("authuser");
		System.out.println(authUser);
		model.addAttribute("email",authUser.getEmail());
		model.addAttribute("gender",authUser.getGender());
		return "user/modify";
	}
	
	@Auth
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser,@ModelAttribute UserVo userVo,Model model)
	{

		userVo.setNo(authUser.getNo());
		if(userService.modify(userVo)) {
		model.addAttribute("result",true);
		authUser.setName(userVo.getName());
		}
		return "redirect:/user/modify";
	}
//	@ExceptionHandler( UserDaoException.class )
//	public String handleUserDaoException() {
//		//1.로깅작업
//		//2. 패아지 전환
//		return "/WEB-INF/views/error/exception.jsp";
//	}

}
