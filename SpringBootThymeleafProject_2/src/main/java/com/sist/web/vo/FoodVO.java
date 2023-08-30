package com.sist.web.vo;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class FoodVO {
	private int fno,hit;
	private double score;
	private String name,address,tel, type, price, time, parking, menu, poster;
}
