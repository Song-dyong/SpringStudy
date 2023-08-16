package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodVO {
	private int fno,cno;
	private String name, phone, type, price, menu, parking, time,poster,address; 
	private double score;
	private String addr1, addr2;
	private String sessionId;
}
