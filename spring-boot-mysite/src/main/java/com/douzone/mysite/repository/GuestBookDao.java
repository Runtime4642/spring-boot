package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;

	
	public GuestBookVo get(long no) {
		GuestBookVo vo = sqlSession.selectOne("guestbook.getNo",no);
		return vo;
//		Connection conn = null;
//		PreparedStatement pstmt=null;
//		ResultSet rs =null;
//		 GuestBookVo vo = new GuestBookVo();
//		try {
//			 conn = dataSource.getConnection();
//			 
//			 String sql = "select no , name  , message,reg_date from guestbook where no=?";
//			 pstmt = conn.prepareStatement(sql);
//			
//			 pstmt.setLong(1, no);
//			 rs = pstmt.executeQuery();
//			 while(rs.next())
//			 {
//				 String name = rs.getString(2);
//				 String message = rs.getString(3);
//				 String date = rs.getString(4);
//				 vo.setNo(no);
//				 vo.setName(name);
//				 vo.setDate(date);
//				 vo.setMessage(message);
//			 }
//			 return vo;
//			 
//		} catch (SQLException e) {
//			System.out.println("error:"+e);
//		} 
//		finally 
//		{
//				try {
//					if(conn !=null)
//					conn.close();
//					if(rs !=null)
//						rs.close();
//					if(pstmt != null)
//						pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			
//		}
//		
//		return null;
	}
	
	public List<GuestBookVo> getList()
	{
		 List<GuestBookVo> list = sqlSession.selectList("guestbook.getList");
				 return list;
	}
	
	public List<GuestBookVo> getList(int page)
	{
		return sqlSession.selectList("guestbook.getListByPage",page);
	}
	public Long insert(GuestBookVo vo)
	{
		sqlSession.insert("guestbook.insert", vo);
		long no = vo.getNo();
		return no;
	}
	public void delete(String no,String password)
	{
		Map<String,String> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);
		sqlSession.delete("guestbook.delete", map);
	}
	public boolean delete(GuestBookVo guestbookVo)
	{
		int cnt = sqlSession.delete("guestbook.delete2", guestbookVo);
		return cnt==1;
	}
}


