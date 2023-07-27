package com.sist.main2;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.config.EmpConfig;
import com.sist.dao2.*;
import com.sist.vo.*;
/*
	DAO를 사용하기 위해서는 getBean 혹은 AutoWired를 사용해야 한다.
	=> MainClass에서 DAO를 멤버변수로 잡은 뒤, AutoWired를 하기 위해서는 먼저 
		MainClass의 메모리를 할당해야한다. @Component를 사용해서 스프링에서 메모리 할당
	
	Spring은 클래스 관리자로, 객체의 생성부터 소멸까지를 개발자 대신 맡아서 처리하는 역할 (컨테이너)
	
	Service는 dao 여러 개를 모아둔 곳 => 메인 클래스에서 dao를 직접 요청하는 것이 아니라,
		서비스를 먼저 가져온 뒤, 서비스의 dao를 요청 => dao에 오류가 나더라도 service에서 에러 발생.
		이때 Service는 인터페이스 (유지보수시에 인터페이스에서 오류가 나면, 스프링 컨테이너는 오류가 나지 않기 때문에
										클라이언트에 오류가 보이지 않는다.)
		
		현재 페이지에서 메인클래스(클라이언트에 해당)에 DAO를 생성하는 것은 정석적인 코딩 X
		=> 인터페이스에 DAO를 생성한 뒤 사용해야한다.
			+ 컨트롤러의 부하를 방지하기 위해 Service에 모아두기
		
		
	클라이언트 --------- 스프링 컨테이너 ------------ A B C 라는 객체가 존재
	-------
	A라는 객체를 요청할 경우? AutoWired ------------> A가져오기
	
	클라이언트의 요청 ======= 서버 (현재 작성 중인 자바)
	
*/

@Component("mc")
public class MainClass {
	@Autowired
	private EmpDAO dao;
	public static void main(String[] args) {
//		ApplicationContext app=new ClassPathXmlApplicationContext("app2.xml");
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(EmpConfig.class);
		MainClass mc=(MainClass)app.getBean("mc");
		List<EmpVO> list=mc.dao.empdeptListData();
		for(EmpVO vo:list) {
			System.out.println(
				vo.getEmpno()+" "
				+vo.getEname()+" "
				+vo.getJob()+" "
				+vo.getDbday()+" "
				+vo.getSal()+" "
				+vo.getDvo().getDname()+" "
				+vo.getDvo().getLoc()
			);
		}
		System.out.println("============================");
		Scanner scan=new Scanner(System.in);
		System.out.println("사번 입력!");
		int empno=scan.nextInt();
		EmpVO vo=mc.dao.empdeptDetailData(empno);
		System.out.println(vo.getEmpno());
		System.out.println(vo.getEname());
		System.out.println(vo.getJob());
		System.out.println(vo.getDbday());
		System.out.println(vo.getSal());
		System.out.println(vo.getDvo().getDeptno());
		System.out.println(vo.getDvo().getLoc());
	}
}
