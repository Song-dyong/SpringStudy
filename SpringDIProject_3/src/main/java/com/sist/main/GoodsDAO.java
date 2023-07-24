package com.sist.main;
// 객체 주입 => 자동 주입(어노테이션) / xml을 통한 주입
// Dependency injection
import java.util.*;

import lombok.Setter;

import java.sql.*;

public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps;
	// Injection => Spring이 제공하는 클래스를 통해서 멤버변수 초기화
	//				------------------- 생성자의 매개변수에 값 채우기 / Setter(p:)를 통한 값 채우기.
	@Setter
	private String url;
	@Setter
	private String username;
	@Setter
	private String password;
	
	public GoodsDAO(String driver) {
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
		}catch(Exception e) {}
	}
	
	public List<String> goodsNameList(){
		List<String> list=new ArrayList<String>();
		try {
			getConnection();
			String sql="SELECT goods_name FROM goods_all ORDER BY no ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
}
