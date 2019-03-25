package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.douzone.mysite.vo.UserVo;


@Repository
public class UserDao{
	
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(String email,String password)
	{
//		StopWatch stopWatch = new StopWatch();
//		stopWatch.start();
		Map<String,String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		UserVo userVo=sqlSession.selectOne("user.getByEmailAndPassword",map);
		
//		stopWatch.stop();
//		Long totalTime = stopWatch.getTotalTimeMillis();
//		System.out.println("--------->"+totalTime);
		
		return userVo;
//		UserVo result = null;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs =null;
//
//		try {
//			conn = dataSource.getConnection();
//
//			String sql = "select no,name,gender from user where email=? and password=?"; 
//				
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, email);
//			pstmt.setString(2, password);
//			 rs = pstmt.executeQuery();
//			 if(rs.next())
//			 {
//				 Long no = rs.getLong(1);
//				 String name = rs.getString(2);
//				 String gender = rs.getString(3);
//				 
//				 result = new UserVo();
//				 result.setNo(no);
//				 result.setName(name);
//				 result.setEmail(email);
//				 result.setPassword(password);
//				 result.setGender(gender);
//				 
//			 }
//
//		} catch (SQLException e) {
//			System.out.println("error :" + e);
//		} finally {
//			// 자원 정리
//			try {
//				if(rs !=null)
//				{
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return result;
	}
	
	
	public UserVo get(String email) {
		
		return sqlSession.selectOne("user.getByEmail",email);
	
	}
	public boolean update(UserVo vo)
	{
		return sqlSession.update("user.update",vo)==1;
	}
	
	public int insert(UserVo vo) {
		int count = sqlSession.insert("user.insert",vo);
		return count;
//		int count = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = dataSource.getConnection();
//
//			String sql = 
//				" insert" + 
//				"   into user" + 
//				" values ( null, ?, ?, ?, ?, current_timestamp())";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getEmail());
//			pstmt.setString(3, vo.getPassword());
//			pstmt.setString(4, vo.getGender());
//
//			count = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new UserDaoException("회원 정보 저장 실패");
//		} finally {
//			// 자원 정리
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return count;
	}
	
	
}