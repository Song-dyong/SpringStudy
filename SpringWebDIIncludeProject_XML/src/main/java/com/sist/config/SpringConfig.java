package com.sist.config;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
	요청 : .do
		DispatcherServlet
			=> HandlerAdapter : Model 클래스 찾기
			=> HandlerMapping : @GetMapping / @PostMapping / @RequestMapping 메소드 찾기
								==> 호출
		DispatcherServlet : request를 ViewResolver에게 전달
									 ------------JSP
		Spring에서 DispatcherServlet의 기능을 처리
*/
// 스프링에서 클래스 등록
//	<context:component-scan base-package="com.sist.*" />
@Configuration
@ComponentScan(basePackages = {"com.sist.*"})
// 	<mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
@MapperScan(basePackages = {"com.sist.mapper"})
public class SpringConfig implements WebMvcConfigurer{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// configurer.enable()이 없으면, HandlerMapping / HandlerAdapter가 등록되지 않음.
		// 어노테이션을 통해 모델 클래스와 그 안의 메소드를 찾을 수 없음
		configurer.enable();	// HandlerMapping / HandlerAdapter 메모리 할당
	}
	/*
		Web.xml (dispatcherServlet 은 기본적으로 HandlerMapping / HandlerAdapter가 등록됨)
		<servlet>
			<servlet-name>dispatcher</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<init-param>
				<param-name>contextConfigLocation</param-name>
				<!-- 경로만 수정 -->
				<param-value>/WEB-INF/config/application-*.xml</param-value>
			</init-param>
			<load-on-startup>1</load-on-startup>
		</servlet>
		
		HandlerMapping / HandlerAdapter => 메모리 할당
	*/
	/*
		<bean id="viewResolver"
			class="org.springframework.web.servlet.view.InternalResourceViewResolver"
			p:prefix="/" p:suffix=".jsp" />
		
		<bean id="multipartResolver"
			class="org.springframework.web.multipart.commons.CommonsMultipartResolver" />
	
		<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
			p:driverClassName="#{db['driver']}"
			p:url="#{db['url']}"	
			p:username="#{db['username']}"
			p:password="#{db['password']}"
			p:maxActive="#{db['maxActive']}"
			p:maxIdle="#{db['maxIdle']}"
			p:maxWait="#{db['maxWait']}"
		/>
	*/
	@Bean("viewResolver")
	public ViewResolver viewResolver(){
		InternalResourceViewResolver iv=new InternalResourceViewResolver();
		iv.setPrefix("/");
		iv.setSuffix(".jsp");
		
		return iv;
	}
	
	@Bean("multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver cr=new CommonsMultipartResolver();
		return cr;
	}
	
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@211.238.142.124:1521:xe");
		ds.setUsername("hr");
		ds.setPassword("happy");
		ds.setMaxActive(10);
		ds.setMaxIdle(10);
		ds.setMaxWait(-1);
		return ds;
	}
	
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory()  throws Exception{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
	
}
