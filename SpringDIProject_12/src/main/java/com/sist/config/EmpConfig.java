package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
// 환경 설정하는 부분
@Configuration						// <context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages = "com.sist.*")	// Component 어노테이션이 부여된 클래스 추적
//	<mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
@MapperScan(basePackages = "com.sist.mapper")
public class EmpConfig {
	
	/*
	<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="oracle.jdbc.driver.OracleDriver"
		p:url="jdbc:oracle:thin:@211.238.142.124:1521:xe"
		p:username="hr"
		p:password="happy"
	/>
	*/
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@211.238.142.124:1521:xe");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	/*
		<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
			p:dataSource-ref="ds"
		/>
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		// SqlSessionFactory는 인터페이스 => ssf.getObject()는 SqlSessionFactory를 리턴하므로,
		// 데이터형 통일
		return ssf.getObject();
	}
}
