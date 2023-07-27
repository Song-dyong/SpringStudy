package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/*
	@Component 
	@Repository, @Service	(데이터베이스 연결)
	@Controller 		웹
	@RestController 	웹
	@ControllerAdvice 	웹
	@Configuration (환경설정)
*/
//Configuration -> Xml 대체하는 자바라는 의미의 어노테이션 
@Configuration
// <context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages = "com.sist.*")
// <mybatis-spring:scan base-package="com.sist.mapper2"/>
@MapperScan(basePackages = "com.sist.mapper2")
public class EmpConfig {
	/*
	Spring에서는 자바 코딩이 안되기 때문에, 스프링에서 지정한 태그를 통해 값을 넣어줘야했음.
	<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="oracle.jdbc.driver.OracleDriver"
		p:url="jdbc:oracle:thin:@211.238.142.124:1521:xe"
		p:username="hr"
		p:password="happy"
	/>
	Spring5 버전식 자바 코딩 (XML없애기)
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
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
	
}
