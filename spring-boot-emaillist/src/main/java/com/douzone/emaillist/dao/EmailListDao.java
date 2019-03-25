package com.douzone.emaillist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.emaillist.vo.EmailListVo;


@Repository
public class EmailListDao extends Dao {
	
	public List<EmailListVo> getList(){
		List<EmailListVo> list = new ArrayList<EmailListVo>();
		
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		try {
			 conn = getConnection();
			 
			 String sql = "select no , first_name , last_name, email from emaillist order by no desc";
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 while(rs.next())
			 {
				 long no = rs.getLong(1);
				 String firstName = rs.getString(2);
				 String lastName = rs.getString(3);
				 String email = rs.getString(4);
				 
				 EmailListVo vo = new EmailListVo();
				 vo.setNo(no);
				 vo.setEmail(email);
				 vo.setFirstName(firstName);
				 vo.setLastName(lastName);
				 
				 list.add(vo);
			 }
			 return list;
			 
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} 
		finally 
		{
				try {
					if(conn !=null)
					conn.close();
					if(rs !=null)
						rs.close();
					if(pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
		
		return list;
		
		
		
	}
	
	public boolean insert(EmailListVo vo)
	{
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();
			
			String sql = "insert into emaillist values(null,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			
			int count = pstmt.executeUpdate();
			result = count ==1;
			
		}catch (SQLException e) {
			System.out.println("error:"+e);
		} 
		finally 
		{
				try {
					if(conn !=null)
					conn.close();
					if(pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
		
		
		return result;
	}

}
