package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;


@Auth(Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping({"","/main"})
	public String main(Model model) {
		model.addAttribute("siteVo",siteService.getMain());
		return "admin/main";
	}
	
	@RequestMapping("/main/update")
	public String mainUpdate(@ModelAttribute SiteVo siteVo,Model model,@RequestParam(value="img") MultipartFile multipartFile) {
		String profile = fileuploadService.restore(multipartFile,"profile");
		siteVo.setProfile(profile);
		boolean result = siteService.mainUpdate(siteVo);
		model.addAttribute("result",result);
		return "admin/main";
	}
	
	
	@RequestMapping("board")
	public String board() {
		
		return "admin/board";
	}
	
	
}
