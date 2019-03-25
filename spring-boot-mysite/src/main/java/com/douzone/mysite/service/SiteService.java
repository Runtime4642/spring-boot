package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.SiteDao;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {

	@Autowired
	private SiteDao siteDao;
	
	
	public SiteVo getMain() {
		return siteDao.mainGet();
	}
	
	public boolean mainUpdate(SiteVo siteVo) {
		
		return siteDao.mainUpdate(siteVo);
	}
	
	
}
