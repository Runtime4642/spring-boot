package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping({"","/list","/select"})
	public String list(@RequestParam(value="page",required=false,defaultValue="1") String page,
			@RequestParam(value="start",required=false,defaultValue="1") String start,Model model) {
		Map<String,Object> map =boardService.list(page,start);
		int bCnt= (int)map.get("pagerTotalPageCount");
		int bStart=(int)map.get("bStart");
		int bEnd=(int)map.get("bEnd");
		List <BoardVo> list = (List)map.get("list");
		
		model.addAttribute("list",list);
		model.addAttribute("bStart",bStart);
		model.addAttribute("bEnd",bEnd);
		model.addAttribute("search","false");
		model.addAttribute("page",page);
		return "board/list";
	}
	

	@RequestMapping("/search")
	public String searchList(@RequestParam(value="page",required=false,defaultValue="1") String page,
			@RequestParam(value="kwd",required=false) String text,
			@RequestParam(value="search-value",required=false) String searchValue,
			@RequestParam(value="start",required=false,defaultValue="1") String start,Model model) {

		Map<String,Object> map =boardService.searchList(page, searchValue, text,start);
		
		int bCnt= (int)map.get("pagerTotalPageCount");
		int bStart=(int)map.get("bStart");
		int bEnd=(int)map.get("bEnd");
		List <BoardVo> list = (List)map.get("list");
		
		model.addAttribute("list",list);
		model.addAttribute("bStart",bStart);
		model.addAttribute("bEnd",bEnd);
		model.addAttribute("search","true");
		model.addAttribute("page",page);
		model.addAttribute("text",text);
		model.addAttribute("searchValue",searchValue);
		return "board/list";
	}

	@RequestMapping("/view")
	public String view(
			@RequestParam(value="page",required=false) String page,
			@RequestParam(value="no",required=false) String boardNo,
			@RequestParam(value="userNo",required=false) String userNo,
			@RequestParam(value="c",required=false) String boolcomment,
			@RequestParam(value="start",required=false) String start,Model model) {
		
		
		Map<String,Object> map =boardService.view(page, boardNo, boolcomment,start);
		
		model.addAttribute("list",map.get("list"));
		model.addAttribute("boardVo",map.get("boardVo"));
		model.addAttribute("bEnd",map.get("bEnd"));
		model.addAttribute("bStart", map.get("bStart"));
		model.addAttribute("page",map.get("page"));
		return "board/view";
	}
	@Auth
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(@AuthUser UserVo authUser,@ModelAttribute BoardVo boardVo,@RequestParam(value="file")MultipartFile multipartFile) {
		
		String fileName = fileuploadService.restore(multipartFile,"board");
		boardVo.setFileName(fileName);
		boardVo.setUserNo(authUser.getNo());
		boardService.write(boardVo);
		
		return "redirect:/board/";
	}

	@Auth
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("no") String no)
	{
		boardService.delete(no);
		return "redirect:/board/";
	}
	
	@Auth
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public String modify(@RequestParam("no") String no,Model model)
	{
		model.addAttribute("boardVo",boardService.modifyShow(no));
		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo boardVo,Model model)
	{
		boardService.modify(boardVo);
		return "redirect:/board/";
	}
	
	@Auth
	@RequestMapping(value="/reply",method=RequestMethod.GET)
	public String reply()
	{
		
		return "board/reply";
	}
	@Auth
	@RequestMapping(value="/reply",method=RequestMethod.POST)
	public String reply(@AuthUser UserVo authUser,@ModelAttribute BoardVo boardVo)
	{
		boardVo.setUserNo(authUser.getNo());
		boardService.reply(boardVo);
		return "redirect:/board/";
	}
	
	@Auth
	@RequestMapping(value="/comment",method=RequestMethod.POST)
	public String comment(@AuthUser UserVo authUser,
			@RequestParam(value="contents",required=false) String contents,
			@RequestParam(value="boardNo",required=false) Long boardNo,Model model)
	{

		
		boardService.comment(contents, authUser.getNo(), boardNo);
		model.addAttribute("no",boardNo);
		return "redirect:/board/view";
	}
	
	@Auth
	@RequestMapping(value="/commentdelete",method=RequestMethod.GET)
	public String commentDelete(
			@RequestParam(value="boardNo",required=false) String boardNo,
			@RequestParam(value="no",required=false) String commentNo,Model model)
	{
		boardService.commentDelete(commentNo);
		model.addAttribute("no",boardNo);
		return "redirect:/board/view";
	}
}
