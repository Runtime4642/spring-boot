package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.security.Auth;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model)
	{
		model.addAttribute("list",galleryService.getList());
		return "gallery/index";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam(value="file") MultipartFile multipartFile,
			@ModelAttribute GalleryVo galleryVo)
	{
		String fileURL=fileuploadService.restore(multipartFile, "gallery");
		galleryVo.setImageURL(fileURL);
		galleryService.saveDB(galleryVo);
		return "redirect:/gallery";
	}
	
	@Auth
	@RequestMapping("/delete")
	public String upload(@RequestParam(value="no",required=false) Long no,@RequestParam(value="url",required=false) String url)
	{
		
		galleryService.delete(no,url);
		return "redirect:/gallery";
	}
	
	
	
}
