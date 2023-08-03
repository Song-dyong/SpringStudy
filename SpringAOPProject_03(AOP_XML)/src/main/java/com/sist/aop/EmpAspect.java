package com.sist.aop;

import java.util.*;
import com.sist.dao.*;

import org.aspectj.lang.ProceedingJoinPoint;

/*
	1. Before 
	2. After
	---------------
	3. Around
	4. After-Returning
	5. After-Throwing
	
*/
public class EmpAspect {
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("Around Call... try Start , try End");
/*
		try{
			----------------- 여기서 처리
				핵심 코딩
			----------------- 여기서 처리 
		}catch(Exception e){}
*/
		
		Object obj=null;
		long start=0, end=0;
		start=System.currentTimeMillis();	// 시작점
		System.out.println("사용자가 호출한 메소드명: "+jp.getSignature().getName());
		obj=jp.proceed();					// 메소드 호출 => invoke()
		end=System.currentTimeMillis();		// 종료점
		System.out.println("수행시간: "+(end-start));
		return obj;
	}
	// 정상 수행시에 리턴 값을 받아서 처리
	public void afterReturning(Object obj) throws Throwable{
		System.out.println("afterReturning Call... 정상수행");
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
			System.out.println(vo.getEname()+" "
					+vo.getEname()+" "
					+vo.getSal()+" "
					+vo.getJob()+" "
					+vo.getDbday());
		}
	}
	// catch절을 수행했을 때, 호출될 메소드
	public void afterThrowing(Throwable e) throws Throwable{
		System.out.println("afterThrowing Call...");
		e.printStackTrace();
	}
}
