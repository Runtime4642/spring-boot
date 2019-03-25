package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookDao;
import com.douzone.mysite.vo.GuestBookVo;


@Service
public class GuestbookService {
	
	@Autowired
	private GuestBookDao guestbookDao;
	

	public List<GuestBookVo>list() {
		List<GuestBookVo> list = guestbookDao.getList();
		
		return list;
	}
	
	public void delete(String no,String password) {
	 guestbookDao.delete(no, password);
	}
	
	public GuestBookVo write(GuestBookVo guestBookVo) {
		//방금 넣은 데이터의 전체값을 반환해주어야 페이지에 message name 등.. 모든 데이터를 보이게 추가가능
		return guestbookDao.get(guestbookDao.insert(guestBookVo));
	}
	public List<GuestBookVo> getList(int page)
	{
		return guestbookDao.getList(page);
	}
	
	public boolean delete(GuestBookVo guestBookVo) {
		 return guestbookDao.delete(guestBookVo);
	}
}
