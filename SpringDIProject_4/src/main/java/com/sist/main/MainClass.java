package com.sist.main;

/*
	이미 제작되어 있는 라이브러리
	---------------------
	1. Controller : 사용자가 등록한 클래스를 관리 (POJO : 일반 클래스, 인터페이스, 상속이 없는 )
	2. DI : 클래스의 클래스 간의 의존관계 설정
			--------------------------
			변수의 초기화, 필요한 메소드를 호출
	  2-1) XML로 등록 (메뉴얼)
	  	=> XML , Annotation => Container => 프로그램 동작이 가능
	  	=> 동작
	  		XML을 제공 => Container
	  					---------
	  					1) BeanFactory : core (DI)
	  					2) ApplicationContext (XML) : core + AOP
	  						=> 순수하게 자바로 코딩 (보안) => Spring 5 (보안)
	  							AnnotationConfigApplicationContext
	  					3) WebApplicationContext : core + AOP + MVC 
	  			= Bean 생성 (클래스 메모리 할당)
	  				<bean id="a" class="A"/> => Class.forName("a") => 찾는 조건은 id
	  					  ------ 중복할 수 없음
	  			= 의존성 주입 (DI)
	  				class가 동작하기 위한 멤버변수의 초기화
	  								  ------ 일반 변수, 클래스 객체 (주소)
	  								  		 -------  -------------
	  								  		 	|			| A, B, C ... 
	  								  		  기본형 (int, double, ... String)
	  				<bean id="" class=""
	  					p:name=""
	  					p:age=""
	  					------------ 일반변수 설정
	  					p:dao-ref=""
	  					------------ 생성된 객체의 주소값
	  				/>
	  			= init-method : 생성과 동시에 자동으로 메소드 호출
	  				=> 크롤링 , 트위터 읽기, 자동 로그인 ...
	  			= 개발자가 등록된 클래스 객체 활용 (스프링에서 처리하는 부분이 아님)
	  			= destroy-method : 객체 소멸시 호출
	  			
	  			*** 일반 멤버변수는 데이터베이스 정보 외에는 거의 사용 빈도가 없다.
	  			DI (스프링을 통해서 프로그램 동작을 위한 필요한 데이터를 전송)
	  			=> 결합성을 낮게 만든다
	  			  ——— 
	  			  클래스에 영향 적음 (유지보수에 최적화된 프레임워크)
	  			  
	  			클래스에 설정된 변수에 대한 값을 주입
	  			——————————— 초기화
	  			 1) 명시적 초기화 (X)
	  			 2) 생성자	==> 매개변수에 값을 지
	  			 3) Setter	==> setXxx()에 값을 지정
	  			 	property
	  			 	
	  			 ==> 클래스가 많다면 XML이 커진다 (속도가 늦어진다)
	  			 	————————————
	  			 			|
	  			 		패키지 단위로 등록
	  			 		-----------
	  			 			스프링에 등록 / 등록이 안되는 클래스
	  			 			------------------------------------ 구분 (어노테이션) ***
	  			 ==> 1. 사용자 정의 클래스 (패키지단위 등록)
	  			 	 2. 라이브러리 클래스 (<BEAN>)
	  			 ----------------------------------------------------------------------
	  			 	XML : 파일이 한개 (공통)
	  			 	ANNOTATION : 클래스 마다… (개발자)
	  			 	==> MyBatis
	  			 		=> XML
	  			 		=> Annotation
	  			 		=> XML + Annotation
	  			 		
	  								  		  
	3. AOP : 공통 모듈을 모아서 적용 ( 중복 제거 )
	4. MVC : 웹 관련 (데이터 관리 + 화면 출력)
	5. Front + Back => RestFul
	6. 보안 (권한, 암호화 , 복호화)
	———————————————————
	기타: 데이터베이스 (ORM) => MyBatis, 트랜잭션


*/
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass {
	public static void main(String[] args) {
		// Spring에 등록
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
								+vo.getEname()+" "
								+vo.getJob()+" "
								+vo.getSal()+" "
								+vo.getHiredate());
			
		}
		System.out.println("========================");
		Scanner scan=new Scanner(System.in);
		System.out.println("사번 입력");
		int empno=scan.nextInt();
		EmpVO vo=dao.empDetailData(empno);
		System.out.println("====사원정보 출력====");
		System.out.println("사번: "+vo.getEmpno());
		System.out.println("name: "+vo.getEname());
		System.out.println("job: "+vo.getJob());
		System.out.println("mgr: "+vo.getMgr());
		System.out.println("hiredate: "+vo.getDbday());
		System.out.println("pay: "+vo.getSal());
		System.out.println("comm: "+vo.getComm());
		System.out.println("dept: "+vo.getDeptno());
	}
}
