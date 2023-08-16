package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
/*

			1. Controller (Servlet => URL주소에 맞게 톰캣에 의해 호출)
				DispatcherServlet (Servlet) = Spring 라이브러리
						|
				클래스 관리자 (설정 파일을 전송)
				<init-param>
					<param-name>contextConfigLocation</param-name>
					<param-value>/WEB-INF/config/application-*.xml</param-value>
				</init-param>		
			2. HttpServletRequest : 사용하지 않는다 (권장)
					  |
				=> 한글 변환 등록
				=> Cookie 사용
				=> HttpServletResponse : Cookie , 파일 업로드
				=> HttpServletRequest : 사용자의 요청값 , JSP로 결과값 전송
					  |
				요청값은 매개변수로 설정
				전송은 Model을 통해  	
	
		사용자 요청
		  .do		====> DispatcherServlet ===== HandlerMapping ===== Model (사용자 정의)
		  															@Controller
		  															@RestController
		  																 |
		  															메소드를 찾아서 요청 수행
		  															@RequestMapping : GET / POST
		  															@GetMapping : GET
		  															@PostMapping : POST
							ViewResolver		<=================
							(경로명 , 확장자 => return과 관련)
							JSP를 찾아서 request전송
								|
								|
							결과값 (Request)를 받아서 출력 => JSP
							================================
								브라우저에 출력
			1. 환경설정 / 클래스 등록 / 데이터베이스 연결
			====================================
				application-*.xml
			2. 사용자 정의 클래스 메모리 할당 요청
			==============================
				1) XML로 설정 => <bean...> : 공용
				2) Annotation으로 설정 
					=> <context: component-scan basepackage="">
						=> 패키지 단위로 등록시 선별적으로 메모리 할당 요청
						=> 메모리 할당에 포함되지 않는 클래스 : 인터페이스 / VO
						=> 기능별 메모리 할당 요청
							= 일반 클래스
								@Component
									ex)JUnit (테스트용) , ~Manager(Open API)
							= 데이터 베이스 연결
								@Repository
									= 테이블 한 개 연결
								@Service
									= 테이블 여러 개 연결 (BI, Business Integration)
							= Model(기능) : 조립기 (데이터베이스 + 브라우저 전송)
								@Controller : 요청 처리 / 화면 이동
											  =================
											  = forward 기법 : request 전송 (Model 전송)
											  		request.setAttribute()
											  		model.addAttribute()
											  	  return "경로명/JSP명"
											  = redirect 기법 : request 초기화 후 지정된 화면으로 이동
											  	return "redirect:~~.do"; // 재사용 기법
											  		_ok.do ... 
										=> 매개변수 사용 
											(request.getParameter(), ..., Model , 기타 (내장객체)
												내장객체 = HttpServletRequest
														HttpServletResponse
														HttpSession
														RedirectAttributes
														ModelAttribute
														-------------- ~VO (커맨드 객체) , [] , List
														일반 변수 => 해당 변수 , 모든 데이터를 String으로 받을 수 있음
								@RestController (6버전)
									=> 화면 이동 X , 자바스크립트에 데이터 전송 
												  =====================
												  일반 데이터 전송 가능
												  JSON (자바 스크립트 객체 표현법) Vue / React / Ajax
												  VO => {}
												  List => []
							= 공통으로 사용되는 예외처리
									@ControllerAdvice
									@RestControllerAdvice
							= 인터셉트
								=> <bean>를 이용해서 생성
							= 자바 환경 설정 파일 => XML 대신 사용 => 보안
							= AOP는 메모리 할당이 아니다 => @Component를 사용한다
							
							
					스프링의 기반
						= DI
							스프링을 통해서 클래스 관리
										========
										객체 생명주기 (생성 ~ 소멸)
													|
												생성시 필요한 데이터가 필요할 수 있음
												=> 필요한 데이터를 전송 (DI)
												=> Setter DI , Constructor DI , Method DI
												=> XML을 이용해서 전송 , Autowired
												=> 클래스와 클래스의 연관 관계도를 작성하는 것 ***
						= AOP
							공통 모듈 => 핵심만 소스 코딩
							=======
								JoinPoint / PointCut
									|			|
							  호출 시점 설정 / 적용되는 메소드 설정
							  =Before
							  =After
							  =After-Throwing
							  =After-Returning
							  =Around
							 => Transaction / Security / log 
							  
						= Application / WebApplication => 동작을 할 수 있게 미리 틀을 제작
							=> 메뉴얼을 제작해서 전송 (XML)
						=> 인터셉트
						=> Task
						=> WebSocket
						==========================
						Spring + Ajax
						Spring + Vue
						Spring + React
						========================== Full Stack
 
*/

@Controller
public class MainController {
	@Autowired
	private FoodDAO fdao;
	
	@GetMapping("main/main.do")
	public String main_page(Model model) {
		
//		List<CategoryVO> list=fdao.foodCategoryListData();
//		model.addAttribute("list",list);
		model.addAttribute("main_jsp","../main/home.jsp");
		return "main/main";
	}
	
	@GetMapping("chat/chat.do")
	public String chat_chat(Model model) {
		
		model.addAttribute("main_jsp","../chat/chat.jsp");
		return "main/main";
	}
}
