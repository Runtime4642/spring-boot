package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.security.Auth;


@Controller
@RequestMapping("/gb")
public class GuestbookController {

	@Autowired
	private GuestbookService GuestbookService;
	
	@RequestMapping({"","/list"})
	public String list(Model model) {
		
		model.addAttribute("list",GuestbookService.list());
		return "guestbook/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String deleteform() {
		
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@RequestParam(value="no",required=false) String no,
			@RequestParam(value="password",required=false) String password) {
		System.out.println(no+"@@@@@"+password);
		GuestbookService.delete(no, password);
		return "redirect:/gb";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(@ModelAttribute GuestBookVo guestbookVo) {
		
		GuestbookService.write(guestbookVo);
		return "redirect:/gb";
	}
	
}
