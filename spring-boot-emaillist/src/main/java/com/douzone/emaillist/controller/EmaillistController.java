package com.douzone.emaillist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.emaillist.dao.EmailListDao;
import com.douzone.emaillist.vo.EmailListVo;

@Controller
public class EmaillistController {

	@Autowired
	private EmailListDao emaillistDao;
	
	
//	@RequestMapping({"","/list","/main"})
//	public ModelAndView list() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", emaillistDao.getList());
//		mav.setViewName("/WEB-INF/views/list.jsp");
//		return mav;
//	}
	

	@RequestMapping({"","/list","/main"})
	public String list(Model model) {
		model.addAttribute("list",emaillistDao.getList());
		return "list";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(EmailListVo emaillistVo) {
		
		emaillistDao.insert(emaillistVo);
		return "redirect:/main";
	}
	
	
}
