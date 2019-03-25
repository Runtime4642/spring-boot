package com.douzone.mysite.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public Map<String, Object> list(String spage,String start) {

//		// isNumeric [a-zA-Z0-9]* *-> 길이 제한없이 d-> 숫자문자
//		if (spage.matches("\\d*") == false)
//			spage = "1";

		int page = Integer.parseInt(spage);
		int toTalPageCount = boardDao.BoardCount();

		

		// 시작 페이지
		int bStart = 0;
		// 끝 페이지
		int bEnd = 0;
		if (toTalPageCount % 10 == 0)
			bEnd = toTalPageCount / 10;
		else
			bEnd = toTalPageCount / 10 + 1;

		
		if(Integer.parseInt(start)!=1)
			bStart = Integer.parseInt(spage) - (Integer.parseInt(spage) % Integer.parseInt(start));
		else
			bStart=1;
		if(bStart==0)
			bStart=1;
			
		if(page<bStart || page >bEnd)
			page=1;
			
		List<BoardVo> list = boardDao.getList(page);
		Map<String, Object> map = new HashMap<String, Object>();
		

		map.put("list", list);
		map.put("pagerTotalPageCount", toTalPageCount);
		map.put("bStart", bStart);
		map.put("bEnd", bEnd);

		return map;
	}

	public Map<String, Object> searchList(String spage,String searchValue,String text,String start) {

//		// isNumeric [a-zA-Z0-9]* *-> 길이 제한없이 d-> 숫자문자
//		if (spage.matches("\\d*") == false)
//			spage = "1";
		
		//몇번 페이지를 구하는지
		int page = Integer.parseInt(spage);
		
		 if(searchValue.equals("titleSearch"))
			 searchValue = "board.title";
		 else if(searchValue.equals("writerSearch"))
			 searchValue ="user.name";
		
		//전체 페이지 수
		int toTalPageCount = boardDao.searchCount(text, searchValue);
		
		
		

		// 시작 페이지
		int bStart = 0;
		// 끝 페이지
		int bEnd = 0;
		
		//총 개수에 10개씩이니깐 끝 페이지 저장 알고리즘
		if (toTalPageCount % 10 == 0)
			bEnd = toTalPageCount / 10;
		else
			bEnd = toTalPageCount / 10 + 1;
		
		if(Integer.parseInt(start)!=1)
			bStart = Integer.parseInt(spage) - (Integer.parseInt(spage) % Integer.parseInt(start));
			else
				bStart=1;
			if(bStart==0)
				bStart=1;
		//마찬가지로 시작페이지 계산 알고리즘  게시판은 5페이지씩임.
			
			
		//이상한 페이지 입력시 모두 1로 처리	
		if(page<bStart || page >bEnd)
				page=1;	
		
		//검색 결과
				List<BoardVo> list = boardDao.searchGetList(text, searchValue, page);

				//결과 반환할 map
				Map<String, Object> map = new HashMap<String, Object>();
			
			
		map.put("list", list);
		map.put("pagerTotalPageCount", toTalPageCount);
		map.put("bStart", bStart);
		map.put("bEnd", bEnd);

		return map;
	}

	public Map<String, Object> view(String spage,String boardNo,String boolcomment,String start) {

		if(spage==null)
			spage="1";
		
		if (start==null)
		{
			start="1";
		}
		
		int toTalPageCount= boardDao.commentCount(boardNo);
		// 시작 페이지
				int bStart = 0;
				// 끝 페이지
				int bEnd = 0;
				if (toTalPageCount % 10 == 0)
					bEnd = toTalPageCount / 10;
				else
					bEnd = toTalPageCount / 10 + 1;
//				if (Integer.parseInt(spage) % 6 != 0)
//					bStart = Integer.parseInt(spage) - (Integer.parseInt(spage) % 6) + 1;
//				else
					if(Integer.parseInt(start)!=1)
					bStart = Integer.parseInt(spage) - (Integer.parseInt(spage) % Integer.parseInt(start));
					else
						bStart=1;
					if(bStart==0)
						bStart=1;
		int page  = Integer.parseInt(spage);

		BoardVo vo = boardDao.view((long)Integer.parseInt(boardNo));
		
		//hit
		if(boolcomment==null) //처음 들어왔고 댓글적는게 아닐경우
		{
			boardDao.hit(boardNo);
		}
		List <CommentVo> list = boardDao.getCommentList(page, boardNo);
		//결과 반환할 map
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("boardVo", vo);
		map.put("pagerTotalPageCount", toTalPageCount);
		map.put("bStart", bStart);
		map.put("bEnd", bEnd);
		map.put("page", page);
		map.put("list", list);

		return map;
	}

	public boolean write(BoardVo vo) {
		
		boolean result =boardDao.write(vo);
		return result;
	}
	public boolean delete(String no) {
		String url =boardDao.getFileNameByNo(no);
		boolean result=false;
		if(url !=null && !url.equals("")) {
		String filePath = "/uploads/";
		String fileName = url.substring(url.lastIndexOf('/') + 1);
		File file = new File(filePath + fileName);
		if (file.exists()) {
			
			if (file.delete()) {
				System.out.println("파일삭제 성공");
				result= boardDao.delete(no);
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
		//url == null 이면 , 즉 첨부파일이 없는 게시판이면
		else {
			result= boardDao.delete(no);
		}
		
		return result;
	}
	public boolean modify(BoardVo boardVo) {
		boolean result =boardDao.modify(boardVo);
		return result;
	}
	public BoardVo modifyShow(String no) {
		BoardVo boardVo =boardDao.view((long)Integer.parseInt(no));
		return boardVo;
	}
	public void reply(BoardVo boardVo) {
		boardDao.replyWrite(boardVo);
	}
	public void comment(String contents, long userNo, long boardNo) {
		boardDao.commentWrite(contents,userNo,boardNo);
	}
	public void commentDelete(String commentNo) {
		boardDao.commentDelete(commentNo);
	}
	
}
