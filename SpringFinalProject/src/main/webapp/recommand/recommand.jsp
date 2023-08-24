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
	width: 850px;
}
</style>
</head>
<body>
	<div class="wrapper row3">
		<main class="container clear">
			<h2 class="sectiontitle">맛집 추천</h2>
			<div class="row">
				<div class="text-center">
					<button class="btn btn-lg btn-success" @click="change(1)">계절/날씨</button>
					<button class="btn btn-lg btn-info" @click="change(2)">감성</button>
					<button class="btn btn-lg btn-primary" @click="change(3)">스타일</button>
				</div>
				<div style="height: 15px;"></div>
				<div class="row">
					<div class="text-center">
						<span class="btn btn-sm btn-danger" v-for="m in sub_list"
							style="margin-left:3px;margin-top: 3px;" @click="recommand(m)">{{m}}</span>
					</div>
				</div>
				<div style="height: 15px;"></div>
				<div class="row">
					<div class="text-center">
						<div class="col-md-3" v-for="vo in food_list">
					    <div class="thumbnail">
					      <a href="#">
					        <img :src="vo.poster" style="width:100%">
					        <div class="caption">
					          <p>{{vo.name}}</p>
					        </div>
					      </a>
					    </div>
					</div>
				</div>
			</div> 
		</main>
	</div>
	<script>
		new Vue({
			el:'.container',
			data:{
				no:1,
				sub_list:[],
				food_list:[]
			},
			mounted:function(){
				axios.get('../recommand/recommand_sub_vue.do',{
					params:{
						no:this.no
					}
				}).then(res=>{
					console.log(res.data)
					this.sub_list=res.data
				}).catch(error=>{
					console.log(error.response)
				})
			},
			methods:{
				change:function(no){
					this.no=no;
					this.sub()
				},
				sub:function(){
					axios.get('../recommand/recommand_sub_vue.do',{
						params:{
							no:this.no
						}
					}).then(res=>{
						console.log(res.data)
						this.sub_list=res.data
					}).catch(error=>{
						console.log(error.response)
					})
				},
				recommand:function(title){
					axios.get('../recommand/recommand_vue.do',{
						params:{
							fd:title
						}
					}).then(res=>{
						console.log(res.data)
						this.food_list=res.data
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		})
	</script>
</body>
</html>