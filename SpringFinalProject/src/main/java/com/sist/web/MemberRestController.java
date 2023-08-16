package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RestController
public class MemberRestController {
	@Autowired
	private MemberDAO dao;
	
	@GetMapping(value = "../member/idcheck_ok_vue.do", produces = "text/plain;charset=UTF-8")
	public String member_idCheck(String id) {
		String result="";
		
		int count=dao.memberIdCheck(id);
		if(count==0) {
			result="no";
		}else {
			result=id;
		}
		return result;
	}
}
