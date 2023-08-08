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
.container-fluid {
	margin-top: 50px;
}

.row {
	margin: 0px auto;
	width: 100%;
}
</style>
</head>
<body>	
	<div class="container-fluid">
		<div class="row">	<!-- ref랑 v-model의 역할 공부하기 -->
			<input type="text" ref="fd" size=30 class="input-sm" v-model="fd">
			<input type="button" class="btn btn-sm btn-primary" value="검색" v-on:click="findData()">
		</div>
		<div style="height: 20px;"></div>
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
				<div class="thumbnail">
					<a href="#" target="_blank"> <img :src="vo.poster"
						alt="Lights" style="width: 100%">
						<div class="caption">
							<p style="font-size: 12px">{{vo.name}}&nbsp;<span style="color:orange;">{{vo.score}}</span></p>
						</div>
					</a>
				</div>
			</div>
		</div>
		<div style="height: 10px;"></div>
		<div class="row">
		<!-- class="active" -->
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startPage>1"><a href="#" v-on:click="prev()">&lt;</a></li>			<!-- 속성값 앞에 : 언제 필요한지 공부 -->
					<li v-for="i in range(startPage,endPage)" :class="i==curpage?'active':''"><a href="#" v-on:click="selectPage(i)">{{i}}</a></li>
					<li v-if="endPage<totalpage"><a href="#" v-on:click="next()">&gt;</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script>
		new Vue({
			el:'.container-fluid',	
			// React => state
			// React: 라이브러리 , VueJS : JavaScript 프레임 워크
			// 라이브러리 : 완제품 => jar파일이므로 수정 불가능
			// 프레임워크 : 조립품 => 수정해서 사용 가능
			// data:{} => 멤버변수 (화면 이동 전까지 메모리 유지)
			data:{
				fd:'마포',
				food_list:[],
				curpage:1,
				totalpage:0,
				startPage:0,
				endPage:0,
				
			},
			// 시작과 동시에 데이터 하나 가져와서 출력
			// mounted => CallBack함수 (시스템에 의해서 자동으로 호출되는 함수) => 생명주기
			// window.onloat => $(function(){}) => componentDidMount => useEffect()
			// 양방향 통신 => 입력한 값을 Vue로 가져옴
			mounted:function(){
				this.send();
			},
			// 사용자 정의 멤버함수 
			methods:{
				send:function(){
					// Spring 서버를 연결해서 필요한 데이터 전송 + 전송한 데이터를 통한 결과값 받기
					axios.get("http://localhost/web/food/find_vue.do",{
						params:{
							page:this.curpage,
							fd:this.fd
							// 매개변수 2개 넘기기 
						}	
					}).then(response=>{
						console.log(response.data)
						// this.food_list => []	// response.data는 JSONArray를 통해 []형태로 바뀐 채 넘어옴
						this.food_list=response.data
						// this.curpage => 1 ==> response.data[0]에 curpage를 지정함
						this.curpage=response.data[0].curpage;
						this.totalpage=response.data[0].totalpage;
						this.startPage=response.data[0].startPage;
						this.endPage=response.data[0].endPage;
					})
				},
				findData:function(){
					this.curpage=1;
					this.send();
				},
				range:function(start,end){
					let arr=[];
					let length=end-start;
					for(let i=0;i<=length;i++){
						arr[i]=start;
						start++;
					}
					return arr;
				},
				selectPage:function(page){
					this.curpage=page;
					this.send();
				},
				prev:function(){
					this.curpage=this.startPage-1;
					this.send();
				},
				next:function(){
					this.curpage=this.endPage+1;
					this.send();
				}
			}
		})
	</script>
</body>
</html>








