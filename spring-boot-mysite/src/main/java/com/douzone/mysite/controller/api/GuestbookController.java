package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller("guestbookApiController")
@RequestMapping("gb/api")
public class GuestbookController {

	@Autowired
	GuestbookService guestbookService;
	
	@RequestMapping("")
	public String main()
	{
		return "guestbook/index-ajax";
	}
	
	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public JSONResult list(@RequestParam(value="p",required=true,defaultValue="1") int page)
	{
		return JSONResult.success(guestbookService.getList(page));
	}
	 
	@ResponseBody
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public JSONResult write(@ModelAttribute GuestBookVo guestBookVo)
	{
		return JSONResult.success(guestbookService.write(guestBookVo));
	}
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public JSONResult delete(@ModelAttribute GuestBookVo guestBookVo)
	{
		if(guestbookService.delete(guestBookVo)==true)
		{
			return JSONResult.success(guestBookVo.getNo());
		}
		return JSONResult.success(null);
	}
	
	
}
