package com.sist.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private String id,pwd, name, sex, birthday, email, post, addr1, addr2, content, admin;
	private String phone, phone1, phone2, dbday, role, msg;
	private Date regdate;
}
// msg => vue로 값을 보낼 때, 유효성 검사시 사용 (id가 존재하지 않는 경우 등,, NOID)