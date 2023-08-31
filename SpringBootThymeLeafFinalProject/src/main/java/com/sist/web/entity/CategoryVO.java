package com.sist.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="food_category")
@Getter
@Setter
public class CategoryVO {
	@Id
	private int cno;
//	@Column => 컬럼명과 다를 경우 사용
	private String title,subject,poster,link;
}
