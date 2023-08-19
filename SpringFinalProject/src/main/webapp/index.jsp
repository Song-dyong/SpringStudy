<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	location.href="main/main.do"
	/* 
		1. 가상돔 : 개발자가 html을 올리는 공간 => diff(실제 돔과 비교해서 변경)
				Vue(프레임워크 : 변경 가능) / React (라이브러리 사용)
		2. template : 컴포넌트 (기능)=> 화면에 출력할 요소(태그)를 작성
				Vue.component('태그명',
						template : '<html> ...  => 형식 (xml) => 루트
				<template> </template>
				=> props:[''] => 부모가 전달한 데이터를 받기 위해 만든다
		3. 출력시 <태그>{{}}</태그>
				속성  <a :href="">
		4. 부모 데이터를 읽어올 때
				Vue.component() => Child
				=> 메소드 , 변수를 찾는 경우
				=> this.$parent.변수 , 메소드
				new Vue({}) => Parent
		5. v-model => 데이터 양방향 => 입력 => 자동으로 변수에 값을 채워줌
		6. v-for, v-if v-else , v-show, v-bind
		7. v-html v-text
			text() html()
		8. 이벤트 처리	
			@click , @keydown...
		9. split, reserse, join
		10. router.. : vue를 단독으로 사용 => react
		11. axios => 서버와 연결 (요청 , 응답)
		
		
	*/
</script>
</head>
<body>
	
</body>
</html>
