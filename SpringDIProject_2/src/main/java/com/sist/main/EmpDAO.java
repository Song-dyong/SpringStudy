package com.sist.main;
import java.util.*;

import org.apache.ibatis.annotations.Results;

import lombok.Setter;

import java.sql.*;


public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String driver;
	@Setter
	private String url;
	@Setter
	private String username;
	@Setter
	private String password;
	
	public EmpDAO(String driver) {
		try {
			Class.forName(driver);
		} catch (Exception e) {}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
	
	public List<EmpVO> empListData(){
		List<EmpVO> list=new ArrayList<EmpVO>();
		try {
			getConnection();
			String sql="SELECT empno, ename, job, hiredate, sal "
					+ "FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	// 주입시, properties파일을 통해
	/*
		스프링에 클래스 등록 => 스프링이 관리 
						   ==========
						   -------------------------
						   1. 객체 생성
						   2. 객체의 멤버변수에 대한 초기값
						   3. 객체 메모리 해제
						   ------------------------- DI (객체 생명주기에 대한 관리)
						   		= 일반 멤버변수
						   		= 객체 주소 (ref)
		<bean id="b" class="B" p:a-ref="a"/>
		<bean id="a" class="A"/>
		==> 순서와 상관 없음 (등록된 모든 클래스에 대해 메모리 할당 후, 나중에 멤버변수 초기값을 부여하므로)
	*/	
}
