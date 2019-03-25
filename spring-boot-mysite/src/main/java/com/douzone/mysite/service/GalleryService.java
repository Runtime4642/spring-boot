package com.douzone.mysite.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryDao;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	public boolean saveDB(GalleryVo vo) {
		return galleryDao.update(vo);
	}

	public List<GalleryVo> getList() {
		return galleryDao.getList();
	}

	public boolean delete(Long no, String url) {
		System.out.println(url);
		String filePath = "/uploads/";
		String fileName = url.substring(url.lastIndexOf('/') + 1);

		File file = new File(filePath + fileName);
		if (file.exists()) {
		
			if (file.delete()) {
				System.out.println("파일삭제 성공");
				return galleryDao.delete(no);
			} else {
				System.out.println("파일삭제 실패");
				return false;
			}
		} 
		else {
			System.out.println("파일이 존재하지 않습니다.");
			return false;
		}
	}

}
