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
	width: 400px;
}
</style>
</head>
<body>
	<div class="wrapper row3">
		<main class="container clear">
			<h2 class="sectiontitle">Delete</h2>
			<div class="row">
				<table class="table">
					<tr>
						<td class="inline">password:
							<input type="password" class="input-sm" ref="pwd" v-model="pwd" size="20">
						</td>
					</tr>
					<tr>
						<td class="text-center">
							<input type="button" class="btn btn-sm btn-danger" value="삭제" @click="deleteBtn">
							<input type="button" class="btn btn-sm btn-danger" value="취소" 
								onclick="javascript:history.back()">
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
				no:${no},
				pwd:''
			},
			methods:{
				deleteBtn:function(){
					if(this.pwd===""){
						this.$refs.pwd.focus();
						return;
					}
					
					axios.get('../databoard/delete_vue.do',{
						params:{
							no:this.no,
							pwd:this.pwd
						}
					}).then(response=>{
						console.log(response.data)
						let result=response.data
						if(result==="yes"){
							location.href="../databoard/list.do"
						}else{
							alert("비밀번호가 틀립니다.")
							this.pwd=''
							this.$refs.pwd.focus()
							return;
						}
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		})
	</script>
</body>
</html>