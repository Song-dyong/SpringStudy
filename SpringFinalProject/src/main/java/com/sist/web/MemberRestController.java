package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
	Vue, React => router를 통해 이동 (Controller가 존재하지 않음) 
		BUT! Spring과 연결하기 위해 Controller를 통해 화면 이동
			라우터를 통해 이동시키면, url주소가 맞지 않기 때문에 사용 불가능
	
	Controller는 화면 이동 / RestController는 데이터 이동 (JSON을 사용한 자바스크립트 언어 변환)
*/
import com.sist.dao.*;
import com.sist.vo.*;


import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@RestController
public class MemberRestController {
	@Autowired
	private MemberDAO dao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping(value = "member/idcheck_ok_vue.do", produces = "text/plain;charset=UTF-8")
	public String member_idCheck(String id) {
		String result="";
		
		int count=dao.memberIdCheck(id);
		if(count!=0) {
			result="no";
		}else {
			result=id;
		}
		return result;
	}
	
	@GetMapping(value = "member/email_check_vue.do", produces = "text/plain;charset=UTF-8")
	public String member_emailCheck(String email) {
		String result="";
		
		int count=dao.memberEmailCheck(email);
		if(count!=0) {
			result="no";
		}else {
			result=email;
		}
		return result;
	}
	
	@PostMapping(value = "member/login_ok_vue.do", produces = "text/plain;charset=UTF-8")
	public String member_login(String id, String pwd, HttpSession session, boolean ck, HttpServletResponse response) {
		String result="";
		int count=dao.memberIdCheck(id);
		if(count==0) {
			result="NOID";
		}else {
			MemberVO vo=dao.memberInfoData(id);
			if(encoder.matches(pwd, vo.getPwd())) {
				result="OK";
				session.setAttribute("id", id);
				session.setAttribute("name", vo.getName());
				session.setAttribute("role", vo.getRole());
				if(ck==true) {
					Cookie cookie=new Cookie("id",id);
					cookie.setPath("/");
					cookie.setMaxAge(60*24*60);
					response.addCookie(cookie);
					
					cookie=new Cookie("name",vo.getName());
					cookie.setPath("/");
					cookie.setMaxAge(60*24*60);
					response.addCookie(cookie);
					
					cookie=new Cookie("role",vo.getRole());
					cookie.setPath("/");
					cookie.setMaxAge(60*24*60);
					response.addCookie(cookie);
				}
			}else {
				result="NOPWD";
			}
		}
		return result;
	}
	@GetMapping(value = "member/logout_vue.do",produces = "text/plain;charset=UTF-8")
	public String member_logout(HttpSession session) {
		session.invalidate();
		return "";
	}
	
	
}
