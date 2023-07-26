package com.sist.main;
/*
	1. 프레임워크
		= 라이브러리 : 완제품
		= 프레임워크 : 조립품
			=> 스프링 (전자정부 프레임워크-공기업, 애니 프레임워크-대기업)
			=> 개발에서 기본이 되는 틀을 제공 (기능을 추가해서 사용)
			=> 스프링의 목표 (OpenSource)
				1) 클래스와 클래스 간의 느슨한 결합 , 낮은 결합도 유지
									---------------------
									변경 혹은 추가될 때, 다른 클래스에 영향이 없는 개발
										POJO (독립적인 클래스 제잘)
				2) 메소드마다 반복해서 사용하는 소스코드 => 공통 모듈로 분리 (응집력이 높은 프로그램 제작)
					=> AOP : OOP를 보완 ObjectOrientedProgram
			=> 클래스를 모아서 관리 (생성~소멸) : 컨테이너 (Container)
				생성에 필요한 데이터나 객체 주소가 필요할 때도 있다 (DI)
				
				*** DI : 객체 생성 , 클래스의 연관 관계
				*** AOP: 공통 모듈 (AOP기반 => 로그파일, 트랜잭션, 보안)
				 DI / AOP => 인터페이스 기반
			
			1. 컨테이너의 종류
				1) BeanFactory : 가장 단순한 컨테이너
						|		사용자가 만든 클래스만 관리 => DI
						|		--------------------- CORE (기본 베이스)
				2) ApplicationContext : AOP, 메세지 지원(JMS)
					=> 구현된 클래스 - ClassPathApplicationContext 
						|			FileSystemXmlApplicationContext
						|
				3) WebApplicationContext :MVC(웹)
				** 컨테이너의 역할 : 객체 생성, 검색, 소멸
					1| DL - DepengencyLookup : 클래스를 찾아주는 역할 getBean()
					2| DI - DepengencyInjection : 필요한 데이터를 받아서 변수 초기화
							= Setter DI		 (setter를 통해 초기화) 
							= Constructor DI (매개변수를 통해 초기화)
				
			2. DI (값을 스프링에 전송하는 방식)
				주입 (변수값을 주입, 객체 주소 설정)
				*** 변수 => private : 변수에 접근이 어려움
					--------------------------------
						setter	=> p:변수명 => setXxx()
						생성자	=> c:변수명 => 매개변수명
				***스프링에서 지원하는 라이브러리
					Spring Core : DI (객체 생성 - 소멸) => Container
					Spring AOP : 공통 모듈 => 자동 호출
						=Before
						=After
						=After-Returning
						=After-Throwing
						=Around
						=JoinPoint , PointCut , 위빙
						=Advice
					Spring ORM : 데이터 베이스 연동 (MyBatis , Hibernate , JPA)
					Spring WEB : JSP 연동 
					Spring MVC : View / Model / Controller
					---------- Spring legacy Project (기본 프로젝트에 포함)
				*** XML 사용법
					=클래스명 (메모리 할당이 가능)을 전송
				 ------
				 XML
				 	XML 이용법
				 	=> 클래스 한 개를 설정
				 		<bean id="a" class="A">
				 		<bean id="b" class="B">
				 	MAP
				 	-------------------------------
				 		KEY			클래스 객체
				 	-------------------------------
				 		a			new A()
				 	-------------------------------
				 		b			new B()
				 	-------------------------------
					*** 프로그램 동작 시작 => 클래스를 모아서 저장 => 주소값 변경이 안됨
						=> SingleTon
				=> 패키지 단위 설정
					<context:component-scan base-package="com.sist.*">
				=> 단점
					사용자 정의 / 라이브러리 클래스 (MyBatis, ViewResolver...)
								=> 어노테이션이 없음 => 가급적이면 XML 등록
								
				 Annotation
				 	=> 메모리 할당
				 	   -------- 클래스의 종류별 구분
				 	@Component	: 일반 클래스 (MainClass, 크롤링, OpenAPI)
				 	@Repository	: DAO (저장소)
				 	@Service	: DAO 여러개를 한번에 통합 
				 	@Controller : MVC구조의 Model(페이지 지정)
				 	@RestController : MVC구조의 Model => JSON
				 	@ControllerAdvice : 공통 예외처리
				 	@RestControllerAdvice : 공통 예외처리
				 	---------------------------------------------- 클래스에 설정하면 컨테이너가 메모리 할당
				 *** 빈(객체) 생명주기
				 	빈 생성 => DI => init-method => 객체사용(개발자) => destroy-method(소멸)
				 	
				 자바코드
				 --------
			3. AOP의 개념
			4. MVC구조 파악
			5. 보안
			+ 데이터베이스 연결 (ORM: MyBatis,JPA) 
			------------------------------ 스프링 입문
*/

public class MainClass {
	public static void main(String[] args) {
		String path="/Users/dyongsong/Desktop/SpringDev/SpringStudy/SpringDIProject_8/src/main/java/com/sist/main/app.xml";
		ApplicationContext app=new ClassPathApplicationContext(path);
		Sawon sa=(Sawon)app.getBean("sa");
		System.out.println("sabun: "+ sa.getSabun() );
		System.out.println("Name: "+ sa.getName() );
		System.out.println("Dept: "+ sa.getDept() );
		System.out.println("Job: "+ sa.getJob() );
	}
}
