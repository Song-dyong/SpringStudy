package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.dao.*;
import com.sist.vo.*;

// Security 5 버전 => 반드시 BCryptPasswordEncoder를 추가 
@RestController
public class MemberRestController {
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private MemberDAO dao;
	
	@PostMapping(value = "member/join_ok.do",produces = "text/plain;charset=UTF-8")
	public String member_join(MemberVO vo) {
		System.out.println("id: "+vo.getId());
		System.out.println("pwd: "+vo.getPwd());
		System.out.println("name: "+vo.getName());
		System.out.println("sex: "+vo.getSex());
		String result="";
		try {
			// 암호화
			String en=encoder.encode(vo.getPwd());
			vo.setPwd(en);
			dao.memberInsert(vo);
			result="YES";
		} catch (Exception e) {
			result="NO";
			e.printStackTrace();
		}
		return result;
	}
	
	@PostMapping(value = "member/login_ok.do",produces = "text/plain;charset=UTF-8")
	// 세션에 아이디 저장 (매개변수로 세션 선언 => 디스패처서블릿이 값 주입)
	// 필요한 객체는 매개변수를 통해 받아온다 (이때, 순서는 상관 없음)
	// 사용자 전송 (요청) => request.getParameter()
	// 받을 수 있는 클래스 : 내장객체 + Model(전송 객체 => forward에서만 사용) 
	// sendRedirecct => RedirectAttributes
	// @RestController => 다른 언어와 연결할 때 사용 ex) JavaScript / Mobile(Kotlin)
	// 						JSON , 일반 문자열..		=> Ajax , Vue , React ... 
	//		초기에 @ResponseBody => @RestController (클래스화)
	public String member_login_ok(String id, String pwd, HttpSession session) {
		// 복호화하기
		String result="";
		int count=dao.memberIdCheck(id);
		if(count==0) {
			result="NOID";
		}else {
			MemberVO vo=dao.memberLogin(id);
//						보낸	비밀번호 / 암호화된 비밀번호 비교 => 복호화 과정		
			if(encoder.matches(pwd, vo.getPwd())) {
				// 비밀번호가 같다면 => 로그인된 상태
				result="OK";
				// Session Store
				session.setAttribute("id", id);
				session.setAttribute("name"	, vo.getName());
				session.setAttribute("sex", vo.getSex());
				
			}else {
				// 같지 않은 경우 => 같은 값임에도, 암호화는 다름
				result="NOPWD";
			}
		}
		return result;
		// return "redirect:../food/category.do"; => RestController 에서는 일반 문자열로 넘어감
		// 기존에 RestController를 사용하지 않았을 때에는 .jsp파일을 하나 더 거쳐서 들어갔어야 했음
	}
	
}
