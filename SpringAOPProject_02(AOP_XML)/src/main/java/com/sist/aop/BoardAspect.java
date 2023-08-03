package com.sist.aop;
/*
	언제 어디서 호출할지 여부 확인
	1. 시점	  => pointcut 	=> 메소드
	2. 호출위치 => joinpoint
		@Before		=> try 전에 호출
		@After		=> finally에서 호출
		@Around		=> 위 아래 (로그파일에서 사용 多 => 시작 시간, 끝 시간 체크 / 트랜잭션에서 사용 多) 
		@After-Throwing	=> catch (오류발생)
		@After-Returning => 정상수행 처리
		
		@Transactional
		public String display(){
			try{
				@Before
				------------------------- @Around (핵심 코딩과 동시에 처리) 
				--- 핵심 코딩 위치 (INSERT)		=> conn.setAutoCommit(false)
				-------------------------		conn.commit()
			}catch(Exception e){
				@After-Throwing					conn.rollback()
			}finally{
				@After
			}
			return ""; @After-Returning			conn.setAutoCommit(true)
		}
		
	----------------------- 합쳐서 advice
	
	advice의 모음 => adviser
	------------------------------------ 전체의 모음 aspect
	
*/
public class BoardAspect {
	public void before() {	// 문장수행 전에 호출
		System.out.println("getConnection");
	}
	public void after() {	// 문장이 끝난 후 호출 (finally)
		System.out.println("disConnection");
	}
}
