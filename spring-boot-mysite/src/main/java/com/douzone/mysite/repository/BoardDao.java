package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;

@Repository
public class BoardDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<BoardVo> getList(int page)
	{	
		page=(page-1)*10;
		List<BoardVo> list =sqlSession.selectList("board.getList",page);
		return list;
	}

	public boolean write(BoardVo vo)
	{
		sqlSession.insert("board.insert",vo);
		return false;
		
	}

	public boolean delete(String no)
	{
		boolean result = sqlSession.delete("board.delete",no) ==1;
		return result;
	}
	public String getFileNameByNo(String no)
	{
		return sqlSession.selectOne("getFileNameByNo",no);
	}

	public boolean hit(String boardNo) {
		boolean result = sqlSession.update("board.hit",boardNo) ==1;
		return result;
	}

	public BoardVo view(long no)
	{
		return sqlSession.selectOne("board.view",no);
	}

	public boolean modify(BoardVo vo)
	{
		return sqlSession.update("board.modify",vo)==1;
	}

	public void replyWrite(BoardVo vo)
	{
		BoardVo boardVo=sqlSession.selectOne("board.replyWrite0",vo.getNo());
		vo.setDepth(boardVo.getDepth());
		vo.setoNo(boardVo.getoNo());
		vo.setgNo(boardVo.getgNo());
		sqlSession.update("board.replyWrite1",vo);
		sqlSession.insert("board.replyWrite2",vo);
	}

	public List<BoardVo> searchGetList(String text,String way,int page)
	{
		Map <String,Object> map = new HashMap<String, Object>();
		map.put("text", text);
		map.put("way", way);
		map.put("page", (page-1)*10);
		return sqlSession.selectList("board.getSearchList", map);
	}	

	public int searchCount(String text,String way) {
		Map <String,Object> map = new HashMap<String, Object>();
		map.put("text", text);
		map.put("way", way);
		return sqlSession.selectOne("board.searchCount", map);
	}
	public int BoardCount() {
		return sqlSession.selectOne("board.boardCount");
	}

	public void commentWrite(String contents, long userNo, long boardNo) {
		Map<String,Object> map = new HashMap<>();
		map.put("contents", contents);
		map.put("userNo", userNo);
		map.put("boardNo", boardNo);
		sqlSession.insert("commentWrite",map);
	}

	public List<CommentVo> getCommentList(int page,String boardNo)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("boardNo",boardNo);
		map.put("page", (page-1)*10);
		List<CommentVo> list = sqlSession.selectList("board.commentList",map);
		return list;
		
	}
	public void commentDelete(String no)
	{
		sqlSession.delete("board.commentDelete",no);
	}

	public int commentCount(String boardNo) {
		return sqlSession.selectOne("board.commentCount",boardNo);
	}
}
