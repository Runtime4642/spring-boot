package com.douzone.mysite.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	
	public int join(UserVo userVo) {
		//1. DB에 가입 회원정보 insert 하기
		int result=	userDao.insert(userVo);
		return result;
		//2. email 주소 확인하는 메일 보내기
		
	}
	public UserVo login(String email,String password) {
		
		UserVo authuser =userDao.get(email,password);
			
			return authuser;
				
				
				
	}
	public boolean modify(UserVo userVo) {
		
		boolean result =userDao.update(userVo);
		return result;
	}
	
	public boolean existEmail(String email) {
		return userDao.get(email)!=null;
	}
	

}
