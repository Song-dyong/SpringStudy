package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao2.*;
import java.util.*;


public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app2.xml");
		StudentDAO dao=(StudentDAO)app.getBean("dao");
		//StudentDAO dao=app.getBean("dao",StudentDAO.class); 제네릭스 형변환 DL 
		Scanner scan=new Scanner(System.in);
		while(true) {
			System.out.println("==== 메뉴 ====");
			System.out.println("1. 학생 목록");
			System.out.println("2. 학생 상세보기");
			System.out.println("3. 학생 검색");
			System.out.println("4. 학생 등록");
			System.out.println("5. 학생 정보 수정");
			System.out.println("6. 학생 정보 삭제");
			System.out.println("7. 프로그램 종료");
			System.out.println("=============");
			System.out.println("메뉴 선택!");
			int no=scan.nextInt();
			switch (no) {
			case 1:{
				List<StudentVO> list=dao.studentListData();
				if(list.size()==0) {
					System.out.println("등록된 학생이 없습니다.");
				}else {
					for(StudentVO vo:list) {
						System.out.println(vo.getHakbun()+". 이름: "+vo.getName()
										+" - "+vo.getKor()+", "+vo.getEng()+", "+vo.getMath());
					}
				}
			}
				break;
			case 2:{
				System.out.println("학번 입력:");
				int hakbun=scan.nextInt();
				StudentVO vo=dao.studentDetailData(hakbun);
				System.out.println(vo.getHakbun()+". "+vo.getName());
				System.out.println("국어점수: "+vo.getKor());
				System.out.println("영어점수: "+vo.getEng());
				System.out.println("수학점수: "+vo.getMath());
				System.out.printf("총점: "+(vo.getKor()+vo.getEng()+vo.getMath()));
				System.out.printf("평균:%.2f\n",((vo.getKor()+vo.getEng()+vo.getMath())/3.0));
			}
				break;
			case 3:{
				System.out.println("학생 이름 검색");
				String name=scan.next();
				List<StudentVO> list=dao.studentFindData(name);
				if(list.size()==0) {
					System.out.println("등록된 학생이 없습니다.");
				}else {
					for(StudentVO vo:list) {
						System.out.println(vo.getHakbun()+". 이름: "+vo.getName()
										+" - "+vo.getKor()+", "+vo.getEng()+", "+vo.getMath());
					}
				}
			}
				break;
			case 4:{
				System.out.println("이름 입력: ");
				String name=scan.next();
				System.out.println("국어 점수: ");
				int kor=scan.nextInt();
				System.out.println("영어 점수: ");
				int eng=scan.nextInt();
				System.out.println("수학 점수: ");
				int math=scan.nextInt();
				StudentVO vo=new StudentVO();
				vo.setName(name);
				vo.setKor(kor);
				vo.setEng(eng);
				vo.setMath(math);
				dao.studentInsert(vo);
				System.out.println("등록이 완료되었습니다.");
			}
				break;
			case 5:{
				System.out.println("이름 입력: ");
				String name=scan.next();
				System.out.println("국어 점수: ");
				int kor=scan.nextInt();
				System.out.println("영어 점수: ");
				int eng=scan.nextInt();
				System.out.println("수학 점수: ");
				int math=scan.nextInt();
				System.out.println("학번 입력: ");
				int hakbun=scan.nextInt();
				StudentVO vo=new StudentVO();
				vo.setName(name);
				vo.setKor(kor);
				vo.setEng(eng);
				vo.setMath(math);
				vo.setHakbun(hakbun);
				
				dao.studentUpdate(vo);
				System.out.println("수정이 완료되었습니다.");
			}
				break;
			case 6:{
				System.out.println("삭제할 학번 입력:");
				int hakbun=scan.nextInt();
				dao.studentDelete(hakbun);
				System.out.println("삭제 완료!");
			}
				break;
			case 7:
				System.out.println("프로그램 종료!!");
				System.exit(0);// 프로그램 종료
			}
		}
		
	}
}
