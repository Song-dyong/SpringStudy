package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
	Spring : 자바 라이브러리
	------
		1) DI : 스프링을 통해 객체 생성 ~ 소멸 
						  --------
						  필요한 데이터가 있는 경우, 데이터를 전송 (의존성 주입 - 멤버변수의 초기화) 
						  변수의 초기화 
						  	1| 명시적 초기화 : 클래스 제작시, 변수에 값을 직접 채우는 방식 (스프링 x)
						  	2| 생성자 : 생성자 DI
						  	3| setter : Setter DI
						  	   ------------------ XML을 이용 or 자바를 이용
					=> DI는 Spring을 통해 작업시, 항상 등장
					
		2) AOP : 프로그램 (핵심 코드 / 공통 코드)
								  -------- getConnection(), disConnection()
				=> Class마다 공통으로 사용되는 메소드를 모았다가 필요한 시기에 자동 호출이 가능
					ex) 트랜잭션 , Log , 보안
		
		3) MVC : 웹 제작에 자주 사용 (View / Model / Controller)
					1| Model : 데이터 관리 (자바)
					2| View : 데이터 출력 (JSP)
					3| Controller : Model과 View의 연결 => 요청값, 데이터 전송
							=> Controller는 이미 제작된 상태 (DispatcherServlet) 
								Tomcat에 의해 구동 
								=> 톰캣이 읽어가는 위치 : Web.xml
								=> Web.xml에 톰캣이 읽을 수 있도록 dispatcher를 등록
								 3-1| DispatcherServlet 등록
								 3-2| DispatcherServlet을 찾는 방법
								 		<url-pattern>*.do</url-pattern> => .do를 찾음
								 		<url-pattern>/</url-pattern> => /를 찾음
									cf) PathValiable : 데이터가 폴더 형태로 넘어가는 형식
										=> admin/hong/1234 
					요청을 받는 경우 (요청 데이터 전송)
					=> request 안에 담겨서 들어온다 => request.getParameter();
					but! request는 사용자 정보 (ip, port)를 포함 (보안 문제)
						=> 가급적이면 사용하지 않기
						=> 사용자 정의 메소드를 만들 때, 매개변수를 설정 
						=> DispatcherServlet에 의해 매개변수가 채워짐
						=> JSP로 결과값 전송 => Model(Request)을 통해서 전송
						
			Model 작성법
			----------
			1) @Controller로 설정해야 메모리 할당 가능 (메소드 찾는 경우)
			2) 메소드
				= 리턴형 : String, void
					String - 화면 이동에 관련된 JSP정보 / Redirect 정보
				= 매개변수 : JSP에서 제공하는 내장객체 설정
				 	HttpServletRequest
				 	HttpServletResponse
				 	HttpSession
				 ex)	
					@GetMapping
					public String display(HttpSession session)
					=> 내장객체 활용한 경우
					public String display(List<MyVO> list, MyVO vo, String[], 기본형(int,double,boolean))
					=> 사용자 정의를 사용한 경우 (순서상관 x)
			3) 메소드 찾기
				=> 어노테이션을 활용한 인덱스 형식의 lookup
				@GetMapping		=> Get방식으로 요청 (default) <a> , ajax , axios ...
																	   ------ axios.get(), axios.post()
				@PostMapping	=> Post방식으로 요청 <form> , ajax , axios ...
														  ----- Vue, React로 대체
				-------------- Spring 4.3이후부터 나뉘어짐
				
				@RequestMapping => GET/POST를 동시에 처리 가능
				
			4) return => 반드시 JSP명 (확장자 기재x)
						재호출 : "redirect : ~.do"
						
		==> MVC 구동 순서 및 방식 
			1| 사용자의 요청 => *.do
			2| DispatcherServlet이 요청을 받음
			3| DispatcherServlet => HandlerMapping
				클래스를 찾아서 메소드를 호출하라고 전송 (Annotation : @GetMapping / @PostMapping)
			4| Model에서 처리 => 사용자 정의 (***개발자 코딩 영역***)
			5| Model에서 처리한 결과값을 DispatcherServlet에 담기
			6| ViewResolver로 전송
			   ------------ JSP를 찾아서 request를 전송하는 역할
			7| JSP에서 request에 담긴 데이터를 출력 (***개발자 코딩 영역***)
			8| DispatcherServlet에 의해 브라우저 화면 전송
					
		cf) 클래스를 못찾는 경우 => Controller 확인 
			메소드를 못찾는 경우 => GetMapping / PostMapping 확인 
		
		아직 안배운 기능
		= 인터셉터 , AOP , 트랜잭션
		Spring-security / Spring-data / Spring-batch / Spring-Cloud
		
*/

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class MainController {
	// 객체 주소를 받아야 하는지 확인 (dao 필요)
	@Autowired
	private FoodDAO dao;
	// 사용자 요청에 대한 처리 
	
	@GetMapping("main/main.do")
	public String main_main(Model model, String cno) {
						//	----------	------------
						//    전송 객체	사용자가 보내준 값
		if(cno==null)
			cno="1";
		Map map=new HashMap();
		map.put("cno", Integer.parseInt(cno));
		List<CategoryVO> list=dao.foodCategoryData(map);
		model.addAttribute("list",list);
		model.addAttribute("main_jsp","../main/home.jsp");
		return "main/main";
	}
	
	
}
