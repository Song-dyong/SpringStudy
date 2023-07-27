package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

/*
		client
		  |
	DispatcherServlet : 사용자의 모든 요청을 받는다 (Front Controller)
		  |				이미 스프링에서 라이브러리 제작
		  |				=> 등록 (web.xml) => servlet은 구동 (톰캣)
		  |			= HandlerAdapter : Model을 찾는 역할
		  |			= HandlerMapping : 찾은 Model에서 @RequsetMapping()을 찾는다 (URL을 통해)
		  |			----------------- DispatcherSerlvet설정과 동시에 설정
		  |			*** 자바로 환경 설정시에는 Handler~ 를 직접 설정
		  |					=> web.xml에 셋팅과 동시에 구동
		해당 모델 (Controller) : 개발자가 직접 제작
		  | 	request에 요청 처리값을 담는다
		  |		JSP파일명을 전송한다		Java => JSP 전송 불가능
	DispatcherServlet				JSP  => JSP / Servlet => JSP
		1) JSP 찾기
		2) Request를 전송
		--------------- ViewResolver
		  |
		View (JSP)
		  | request에 담긴 데이터를 출력 (JSTL/EL)
		Client
		
		==> 개발자는 Model과 View를 작성
				  ------  ----
				   dao	  JSP
		

*/
@Repository
public class FoodDAO {
	/*
	@Select("SELECT cno, title, subject, posert FROM food_category "
			+ "ORDER BY cno ASC")
	
	*/
	@Autowired
	private FoodMapper mapper;
	
	public List<CategoryVO> categoryListData(){
		return mapper.categoryListData();
	}
	
	/*
	  @Select("SELECT fno, name, score, address, phone, type, poster " +
	  "FROM food_house " + "WHERE cno=#{cno}")
	 */
	public List<FoodVO> foodListData(int cno){
		return mapper.foodListData(cno);
	}
	
}
