<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
	margin: 0px auto;
	width: 100%;
}
</style>
</head>
<body>
	<div class="wrapper row3">
		<main class="container clear">
			<h2 class="sectiontitle">DataBoard</h2>
			<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="../databoard/insert.do" class="btn btn-sm btn-danger">등록</a>
					</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<th class="text-center" width=10%>번호</th>
					<th class="text-center" width=45%>제목</th>
					<th class="text-center" width=15%>이름</th>
					<th class="text-center" width=20%>작성일</th>
					<th class="text-center" width=10%>조회수</th>
				</tr>
				<tr v-for="vo in board_list">
					<td class="text-center" width=10%>{{vo.no}}</td>
					<td width=45%><a :href="'../databoard/detail.do?no='+vo.no">{{vo.subject}}</a></td>
					<td class="text-center" width=15%>{{vo.name}}</td>
					<td class="text-center" width=20%>{{vo.dbday}}</td>
					<td class="text-center" width=10%>{{vo.hit}}</td>
				</tr>
				<tr>
					<td colspan="5" class=text-center>
						<button class="btn btn-sm btn-success" @click="prev">이전</button>
						{{curpage}} page / {{totalpage}} pages
						<button class="btn btn-sm btn-success" @click="next">다음</button>
					</td>
				</tr>
			</table>
			</div>
		</main>
	</div>
<script>
	new Vue({
		el:'.container',
		data:{
			board_list:[],
			page_list:{},
			curpage:1,
			totalpage:0
		},
		mounted:function(){
			this.dataRecv();
		},
		methods:{
			dataRecv:function(){
				axios.get('../databoard/list_vue.do',{
					params:{
						page:this.curpage
					}
				}).then(response=>{
					this.board_list=response.data
				}).catch(error=>{
					console.log(error.response)
				})
				
				axios.get('../databoard/page_vue.do',{
					params:{
						page:this.curpage
					}
				}).then(response=>{
					this.curpage=response.data.curpage;
					this.totalpage=response.data.totalpage;
				})
			},
			prev:function(){
				this.curpage=this.curpage>1?this.curpage-1:this.curpage
				this.dataRecv();
			},
			next:function(){
				this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage
				this.dataRecv();
			}
		}
	})
</script>
</body>
</html>