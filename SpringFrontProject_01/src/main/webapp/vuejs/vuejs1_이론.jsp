<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top:50px;
}
.row{
	margin: 0px auto;
	width: 960px;
}
</style>
<!-- <script type="text/javascript">
$(function(){
	$('#msg').keyup(function(){
		let m=$('#msg').val();
		$('#print').text(m);
	})	
})
</script> -->
</head>
<body>
<!-- 
		1. VueJS의 생명주기 => vue3(React), vuex (MVC)
			beforeCreate
			created
			------------- Vue 객체 생성
			beforeMount 
			mounted
			------------- 메모리에 저장
			boforeUpdate
			updated
			------------- 수정
			beforeDestroy
			destroyed
			------------- 메모리 해제
			
			가상돔 : 게임(더블 버퍼링) => 메모리를 두 개 생성 (가상 메모리 => 실제 메모리에 저장)
			String => 메모리와 연결 , StringBuffer
			
			cf) 돔형태 => html
						  |
					-------------
					|			|
				   head			body
				    |			|
				---------	---------
				|		|	|		|
			link    script div		div
		----------------------------------------- DOM Document Object Model (트리 형태)
		
		중간에 span태그가 추가되면, 새로고침(깜빡임)을 통해 전체 트리를 다시 작성해야함 (속도 느림)
		
		태그가 추가될 때, 가상메모리(돔)에 먼저 첨부 => 실제 메모리와 비교해서 추가된 부분만 처리 (Vue)
		
	2. 디렉티브
		=> 제어문
			v-for : 반복문 => 태그 <div v-for="">
			v-if / v-else
			v-show
		=> 이벤트
			v-on:click => button v-on:click="aaa()">
			v-on:change
		=> 양방향 통신(뷰JS) / 단방향 통신(리액트)
		   ------------- AngularJS에서 파생된 VueJS => GO 언어
	3. 컴포넌트 : 재사용
		Vue.Component('modal',
			template:'<html>...')
	4. 데이터를 읽어서 출력 (클라이언트 <=> 서버 통신) => axios
		axios.get('URL',{
			params:{
					데이터 전송
					}		
				}).then(){결과값읽기}
		=> 일반 문자열 / JSON형식으로 값을 읽음 => RestController 사용 
		=> id, class 이용 x => ref=""
	5. 라우터 (화면 변경) => Spring에서 화면 변경
		=> 검색 , 로그인 처리 , 댓글
		
	6. Vue생성	
		<script>
			=> 여러 개 사용
			new Vue({
				el:'.container' 	=> 제어 영역 class=".container" id="#row"
				data:{
					=> 멤버변수 (Vue에서 제어하는 변수)
					a:[]	=> 스프링에서 전송 => ArrayList (JSONArray)
					b:{}				   => VO (JSONObject) 
					c:true				   => boolean
					d:''				   => String
					e:0					   => 정수 / 실수
				}
				생명주기 함수 이용
				mounted:function(){}
				updated:function(){}
				사용자 정의 함수 => 이벤트 처리
				mothods:{
					aaa:function(){}
				}
				filters: 변환 (날짜, 숫자)
				-------- complete
			})
		</script> 
	
			
		==> 데이터 출력
		{{data에 설정된 변수 사용}}
		=> 한번에 데이터를 출력 => {{$data}}
		=> <img src="{{poster}}" => 오류 발생
		=> <img :src="poster">
		=> : => <input type="text" :value="id"> => :은 v-bind / 변수와 html태그를 연결
													  ------- 생략해서 : 로 표현
 -->
<div class="container">
	<div class="row">
	<!-- 양방향 : 입력값을 받아서 출력을 하는 역할 -->
		input: <input type="text" v-model="msg" size="30" class="input-sm">
		<!-- v-model => 멤버변수 값을 변경 -->
		<div id="print">	<!-- 출력: msg에 저장된 내용을 출력 -->
		{{msg}}
		</div>
	</div>	
</div>
<script>
	new Vue({
		el:'.container',
		data:{
			msg:''
		}
	})
</script>
</body>
</html>