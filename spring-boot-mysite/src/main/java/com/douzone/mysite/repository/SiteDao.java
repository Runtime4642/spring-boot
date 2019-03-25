package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class SiteDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public SiteVo mainGet() {
		
	SiteVo siteVo=sqlSession.selectOne("site.mainGet");
		return siteVo;
	}
	
	public boolean mainUpdate(SiteVo siteVo) {
		
		return sqlSession.update("mainUpdate",siteVo)==1;
		
	}
	

}
