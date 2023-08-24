package com.sist.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// 공통 예외처리 => Controller만 해당
@ControllerAdvice
public class CommonsException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex) {
		System.out.println("=======RunTimeException=======");
		ex.printStackTrace();
		System.out.println("==============================");
	}
	// RunTimeException , IOException , SqlException , Exception
	@ExceptionHandler(IOException.class)
	public void iOException(IOException ex) {
		System.out.println("=======IOException=======");
		ex.printStackTrace();
		System.out.println("=========================");
	}
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex) {
		System.out.println("=======SQLException=======");
		ex.printStackTrace();
		System.out.println("==========================");
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex) {
		System.out.println("=======Exception=======");
		ex.printStackTrace();
		System.out.println("=======================");
	}
}
