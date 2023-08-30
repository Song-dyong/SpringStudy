package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "food_category")	// table에 지정한 변수가 VO로 생성 => 추가 변수 설정은 불가능
@Getter
@Setter
public class FoodEntity {
	@Id				// auto increment => 자동 증가 번호에 설정하기(primary key)  insert할 경우, 자동 증가 
	private int cno;
	private String title,subject,poster,link;
}
