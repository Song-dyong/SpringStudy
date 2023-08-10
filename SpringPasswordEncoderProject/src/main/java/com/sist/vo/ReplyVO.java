package com.sist.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
	int no,fno;
	private String id,pwd,name,sex, dbday;
	private Date regdate;
}
