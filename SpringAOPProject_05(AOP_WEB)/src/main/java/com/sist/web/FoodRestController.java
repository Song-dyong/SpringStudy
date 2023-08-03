package com.sist.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
/*
	어노테이션 => 메모리 할당
	@Component, @Repository, @Service, @Controller, 
	@RestController, @ControllerAdvice, @Configuration 
	
	다른 언어 연결 => VueJS
	자바스크립트는 자바의 언어를 인식할 수 없으므로, {}을 잡아서 보내야한다.
	FoodVO => {}
	List<FoodVO> => [{},{},{},...]
	이렇게 변환해주는 JSON
	
	RestController는 다른 프로그램에 맞게 데이터를 변환해서 보내주는 역할
	VueJS, React는 포트가 3000
	=> CrossOrigin은 포트가 다른 경우, 3000을 허용한다는 어노테이션
	현재 서버의 포트는 80번
*/
@RestController
@CrossOrigin("http://localhost:3000")
public class FoodRestController {

}
