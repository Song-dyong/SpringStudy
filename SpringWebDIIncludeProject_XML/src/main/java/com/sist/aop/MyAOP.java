package com.sist.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
// Aspect => 메모리할당 x (context의 메모리할당용 어노테이션이 아니기 때문에)
//  	Aspect => 공통을 적용되는 내용 
@Aspect
@Component
public class MyAOP {

}
