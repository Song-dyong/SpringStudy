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
	
	// 수정하기
	@GetMapping("update.do")
	public String board_update(int no, Model model) {
		
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	// form태그 외에는 GetMapping
	// delete.do?no=${vo.no} 	400 : Bad Request => ?뒤의 데이터형이 매개변수의 데이터형과 일치하지 않을 경우
	@GetMapping("delete.do")
	public String board_delete(int no, Model model) {
		model.addAttribute("no",no);
		return "board/delete";
	}

	/*
		delete_ok.jsp를 만들지 않고, restController에서 자바스크립트를 통해 자체 처리해도 가능
		Controller에서도 자바스크립트 (문자열)을 전송할 수 있음
		@ResponseBody 사용 => 현재 @RestController로 승격
	*/
	@PostMapping("delete_ok.do")
	public String board_delete_ok(int no, String pwd, Model model) {
		boolean bCheck=dao.boardDelete(no, pwd);
		model.addAttribute("bCheck", bCheck);
		return "board/delete_ok";
		// bCheck를 받아서 true일 경우와 false일 경우를 delete_ok에서 처리해야함
		// restController를 사용하면 자바스크립트를 바로 보낼 수 있음
	}
	/*
		Spring
		
		ApplicationContext app = new ClassPath~~ => dispatcherServlet으로 이동
													------------- init-param
								xml 파일을 읽어서 WebApplicationContext로 넘겨줌
					dispatcher서블릿에게 xml의 경로를 알려준 뒤, 웹어플리케이션컨텍스트가 값을 읽어서 처리
					디스패쳐서블릿은 .do를 찾아서(모델 혹은 컨트롤러) 값을 전달하고 받는 역할(이때 사용되는 Model model)
			
			톰캣이 구동되면 web.xml을 가장 먼저 읽은 뒤,
				dispatcher에 메모리를 할당하고 시작 => 디스패쳐가 없으면 값을 전송하고 받을 수 없으므로
			
			예전에 dispatcher를 대체하던 코딩 => @WebServlet("*.do")
			Spring은 WebServlet이 없기 때문에, web.xml에 등록해서 사용 (디스패처 만들기)
			=> @WebServlet은 이클립스에서만 존재하는 어노테이션
			
		cf) Class가 여러 개 => 각각 연결관계를 가지고 있음 => 하나의 클래스에서 오류가 발생할 경우, 모든 클래스에 영향
				=> 스프링은 클래스간의 연관성을 낮춤으로써 유지보수에 용이하도록 만듦
				
		cf) new를 사용하면 안되는 이유? 웹 사이트는 수십만명이 사용할 수 있음 
			 	=> 메모리 할당이 많아지면 서버 속도 저하 , 다운
			 Spring은 사용하지 않는 메모리를 즉각 회수 (객체의 생성부터 소멸까지 담당)
				
		1. Spring MVC 순서
		
			1) DispatcherServlet 등록 => web.xml
			
				1| 클래스로 등록한 파일 셋팅 (클래스 관리)
			 		
			 	2| 한글 변환 코드	
			 		
			 				
			2) 클래스 제작
				=> VO
				=> Mapper
				=> DAO
				=> Model(Controller)
				
			3) application.xml (클래스 등록)
			
			4) JSP 작성
		----------------------------------------------
			실제 프로젝트는 2번과 4번 => 자바코딩(서버) + View(클라이언트) 
		
	
	*/
	
	// 찾기(검색) => 동적쿼리
}
