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

.title {
	width: 220px; /* 너비는 변경될수 있습니다. */
	text-overflow: ellipsis; /* 위에 설정한 100px 보다 길면 말줄임표처럼 표시합니다. */
	white-space: nowrap; /* 줄바꿈을 하지 않습니다. */
	overflow: hidden; /* 내용이 길면 감춤니다 */
	display: block; /* ie6이상 현재요소를 블럭처리합니다. */
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3" v-for="vo in activity_list"
				style="width: 300px; height: 250px;">
				<div class="thumbnail">
					<a href="#" target="_blank"> <img :src="vo.main_poster"
						alt="Lights" style="width: 290px; height: 180px;">
						<p style="font-size: 12px" class="title">{{vo.title}}</p> <span>{{vo.price}}원
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<span style="color: orange;">{{vo.score}} {{vo.review_count}}</span> 
				</div>
				</a>
			</div>
		</div>
		<div style="height: 10px;"></div>
		<div class="row">
			<div class="text-center">
				<input type="button" value="이전" class="btn btn-sm btn-danger"
					v-on:click="prev()"> {{curpage}} page / {{totalpage}} pages
				<input type="button" value="다음" class="btn btn-sm btn-primary"
					v-on:click="next()">
			</div>
		</div>
	</div>
	<script>
	new Vue({
		el:'.container-fluid',
		data:{
			curpage:1,
			totalpage:0,
			activity_list:[],
			activity_detail:{}
		},
		mounted:function(){
			this.send();
		},
		methods:{
			send:function(){
				axios.get("http://localhost/web/activity/list_vue.do",{
					params:{
						page:this.curpage
					}
				}).then(response=>{
					console.log(response.data)
					this.activity_list=response.data;
					// 0번째에만 curpage, totalpage 첨부해둠
					this.curpage=response.data[0].curpage;
					this.totalpage=response.data[0].totalpage;
				})
			},
			prev:function(){
				this.curpage=this.curpage>1?this.curpage-1:this.curpage;
				this.send();
			},
			next:function(){
				this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage;
				this.send();
			}
		}
	})
	</script>
</body>
</html>