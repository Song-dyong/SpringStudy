package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

/*
	1. 페이징 기법 in VueJS
	2. Cookie
	3. Session
	4. Login + Password 암호화 / 복호화
	5. Front => Vue => watch / computed / component / filter 
	6. 회원가입 => 유효성 검사
*/
@Getter
@Setter
public class GoodsVO {
	private int no, discount, hit, account;
	private String name,sub,price,first_price,delivery,poster;
}
