package com.sist.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private String id, pwd, name,sex, msg,dbday;
	private Date regdate;
}
