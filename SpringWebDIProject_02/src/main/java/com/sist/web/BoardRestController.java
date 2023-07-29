package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

/*
	@Controller
		=> 요청 처리 => 요청한 결과값을 전송 => 페이지 (파일)
		.jsp (forward) = request를 전송 / .do (sendRedirect) = request를 전송하지 않고, 기존의 파일로 넘어감
											=> 이동하는 화면이 다른 경우 
			ex) detail.do => detail.jsp를 출력해야함
				insert_ok.do => list.do => list.jsp를 출력해야함 (sendRedirect -> list.do로 이동)
				update_ok.do => detail.do => detial.jsp
				
	@Restcontroller (다른 프로그램과 연결할 경우 사용)
		=> 요청 처리 => 요청한 결과값을 전송 => 결과값만 전송 
										=> 자바스크립트 , 일반 문자열
										=> JSON (Vue , React)
		
*/
@RestController
@RequestMapping("board/")
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	
	// vo로 값을 받는 모델 => ModelAttribute
	/*	 Spring 
			Model(Controller) 메소드
			= 리턴형 : String / void
			= 매개변수 : 단일 변수 (int, String ...)
					   VO 객체 
					   List , [배열]
					   => Model : 전송 객체 (forward)
					   => RedirectAttribute : 전송 객체 (sendRedirect)
					   => HttpSession 
			=request를 사용하지 않는다
				1) request.getParameter() : 매개변수 (DispatcherServlet에서 
											request.getParameter()한 다음, 매개변수로 값을 전송)
				2) request 대신, Model을 이용해서 request에 담긴 데이터를 JSP전송
		
		PringWriter out = request.getWriter()
		print.out("")
		
		아래의 코딩은 문자열을 그대로 전송해서 ajax, vuejs등이 데이터를 바로 받도록 함
		cf) 자바스크립트에 데이터를 전송하는 것은 크롬에서만 가능
		
			Ajax , Vue , React 
			
			Vue는 request로 데이터를 보내도 값을 받지 못한다 why? 자바스크립트이므로..
			따라서 vue가 인식할 수 있는 언어로 바꿔서 보내줘야 함 (JSON - JavaScriptObjectNotaion 자바스크립트 언어 개념)
			
			자바스크립트는 	VO	, 		List	를 인식하지 못한다
					 ----------  ---------
			 JSON => JSONObject  JSONArray
		
		RestController는 화면 이동용이 아닌, 자바스크립트 연동용 
		
		
		스프링의 어노테이션은 인덱스와 같은 역할 (조건문)
			실행할 jsp파일을 찾아가는 역할 
			restController에서는 화면 이동이 아닌 자바스크립트를 넘겨주는 역할
			.do로 설정되어있는 (web.xml에서 설정) 어노테이션에 한해서.
			 
		
	*/
	@PostMapping(value = "update_ok.do", produces = "text/html;charset=UTF-8")
	public String board_update_ok(BoardVO vo) {
		
		String result="";
		boolean bCheck=dao.boardUpdate(vo);
		if(bCheck==true) {
			result="<script>location.href=\"detail.do?no="+vo.getNo()+"\"</script>";
		}else {
			result="<script>"
					+ "alert(\"비밀번호가 틀립니다\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
	
}
