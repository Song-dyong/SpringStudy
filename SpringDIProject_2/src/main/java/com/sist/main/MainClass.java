package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		// 아래와 같이 new를 통해 생성할 경우, username, password, url이 설정되지 않았으므로
		// 오류 발생
		// EmpDAO dao=new EmpDAO("oracle.jdbc.driver.OracleDriver");
		// xml에서 저장, 설정한 값을 가져와서 사용 => xml을 읽어가는 ClassPathXmlApp...
		// getBean을 통해 설정한 id값을 통해 dao를 가져옴 => com.sist.main.EmpDAO
		// 해당 dao에서 설정한 메소드(사용자 정의)를 사용 => dao.empListData()
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob());
		}
	}
}
