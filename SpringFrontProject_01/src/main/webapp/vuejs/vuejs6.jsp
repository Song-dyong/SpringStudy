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
	width: 960px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<h1 class="text-center">{{title}}</h1>
		<div class="col-sm-6">
			<table class="table" v-show="isShow">
				<tr>
					<td width="30%" class="text-center" rowspan="7">
						<img :src="'https://www.kobis.or.kr'+movie_detail.thumbUrl" style="width: 100%">
					</td>
					<td width=70%>
						<h3>{{movie_detail.movieNm}}</h3>
					</td>
				</tr>
				<tr>
					<td width=70%>{{movie_detail.director}}</td> 
				</tr>
				<tr>
					<td width=70%>{{movie_detail.genre}}</td> 
				</tr>
				<tr>
					<td width=70%>{{movie_detail.watchGradeNm}}</td> 
				</tr>
				<tr>
					<td width=70%>{{movie_detail.repNationCd}}</td> 
				</tr>
				<tr>
					<td width=70%>{{movie_detail.rank}}</td> 
				</tr>
				<tr>
					<td width=70%>{{movie_detail.showTm}}</td> 
				</tr>
				<tr>
					<td colspan="2">{{movie_detail.synop}}</td>
				</tr>
			</table>
		</div>
		<div class="col-sm-6">
		<table class="table">
			<tr>
				<td class="text-center">
					<button class="btn btn-sm btn-danger" v-on:click="selectMovie(1,'일별 박스 오피스')">일별 박스 오피스</button>
					<button class="btn btn-sm btn-info" v-on:click="selectMovie(2,'실시간 예매율')">실시간 예매율</button>
					<button class="btn btn-sm btn-warning" v-on:click="selectMovie(3,'좌석 점유율')">좌석 점유율</button>
				</td>
			</tr>
		</table>
			<table class="table">
				<thead>
					<tr class="danger">
						<th class="text-center">Rank</th>
						<th class="text-center"></th>
						<th class="text-center">Title</th>
						<th class="text-center">Director</th>
						<th class="text-center">Genre</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="m in movie_list" v-on:click="detailData(m)">
						<td class="text-center">{{m.rank}}</td>
						<td class="text-center">
							<img :src="'https://www.kobis.or.kr'+m.thumbUrl" style="width: 30px;height: 30px">
						</td>
						<td>{{m.movieNm}}</td>
						<td class="text-center">{{m.director}}</td>
						<td class="text-center">{{m.genre}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<script>
		new Vue({
			el:'.container-fluid',
			data:{
				no:1,
				movie_list:[],
				title:'',
				movie_detail:{},
				isShow:false
			},
			mounted:function(){
				// 시작과 동시에 서버에서 데이터 읽기
	/* 		 앞의 no는 url?뒤의 설정할 key 이름 , :뒤는 설정할 값 => 지금은 1로 설정하기 위해서
					 data:{} 안에 설정한 매개변수 no를 가져왔음 (this.no)
					 아래의 no:no는 매개변수 no를 사용했기 때문에 그냥 no 사용
					 axios.get("url",{params:{데이터설정}}).then
					 .then() 뒤에 데이터 전송
					 then(response=>{})	화살표 함수로 사용할 경우, this는 vue 객체의 멤버변수를 의미
					 then(function(response){}) but! then(function(response){})에서 this를 사용하면,
					 function의 no를 this로 인식하기 때문에, _this를 this로 지정해줘야 한다.
					*/				
				this.title="일별 박스오피스"
				let _this=this
				axios.get("http://localhost/web/movie/movie_vue.do",{
					params:{	// params=> ?뒤에 값을 설정
						no:this.no
					}
				}).then(function(response){
					_this.movie_list=response.data
				})
			},
			methods:{
				selectMovie:function(no,title){
					this.title=title
					axios.get("http://localhost/web/movie/movie_vue.do",{
						params:{
							no:no
					/*		key: 매개변수 no	
							아래의 response 는 결과값!		*/
						}
					}).then(response=>{
						console.log(response.data)
						this.movie_list=response.data
					})
				},
				detailData:function(m){
					this.isShow=true
					this.movie_detail=m
				}
			}
		})
	</script>
</body>	
</html>