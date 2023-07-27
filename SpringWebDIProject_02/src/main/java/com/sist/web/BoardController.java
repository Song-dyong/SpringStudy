package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;
import com.sist.vo.BoardVO;
// 메모리 할당
@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	// list.do?page=1 => 매개변수와 넘어오는 변수명이 동일해야함
	// 최초로 출력하는 페이지에 null값이 넘어오면, 정수형의 null값을 처리할 수 없으므로 String으로 처리
	// 목록 출력  RequestMapping은 get,post 동시 처리
	@GetMapping("list.do")
	public String board_list(String page, Model model) {
		// Model model => 전송 객체 (request 대신 사용)
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		List<BoardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		return "board/list";
	}
	
	// 데이터 추가
	@GetMapping("insert.do")
	public String board_insert() {
		return "board/insert";
	}
	@PostMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do";
	}
	// 데이터 삭제
	/*
		class Model{
			HttpServletRequest request;
			public void addAttribute(String key, Object obj){
				request.setAttribute(key,obj);
			}
		}
	*/
	// 상세보기
	@GetMapping("detail.do")
	public String board_detail(int no, Model model) {
		
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	// 찾기(검색) => 동적쿼리
}
