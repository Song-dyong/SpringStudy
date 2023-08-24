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
			<h2 class="sectiontitle">Update</h2>
			<div class="row">
				<form @submit.prevent="submitForm">
				<table class="table">
					<tr>
						<th width="15%">Name</th>
						<td width="85%">
							<input type="text" ref="name" v-model="name" class="input-sm" size="20">
							<input type="hidden" v-model="no" ref="no">
						</td>
					</tr>
					<tr>
						<th width="15%">Subject</th>
						<td width="85%">
							<input type="text" ref="subject" v-model="subject" class="input-sm" size="55">
						</td>
					</tr>
					<tr>
						<th width="15%">content</th>
						<td width="85%">
 							<textarea rows="5" cols="60" ref="content" v-model="content"></textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">Pwd</th>
						<td width="85%">
							<input type="password" ref="pwd" v-model="pwd" class="input-sm" size="15">
						</td>
					</tr>
					<tr>
						<td class="text-center" colspan="2">
							<input type="submit" value="수정" class="btn btn-sm btn-danger">
							<input type="button" value="취소" class="btn btn-sm btn-danger"
								onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
				</form>
			</div>
		</main>
	</div>
	<script>
		new Vue({
			el:'.container',
			data:{
				name:'',
				subject:'',
				content:'',
				pwd:'',
				no:${no},
				update_data:{}
			},
			mounted:function(){
				axios.get('../databoard/update_vue.do',{
					params:{
						no:this.no
					}
				}).then(response=>{
					console.log(response.data)
					this.update_data=response.data;
					this.name=response.data.name
					this.subject=response.data.subject
					this.content=response.data.content
				}).catch(error=>{
					console.log(error.response)
				})
			},
			methods:{
				submitForm:function(){
					if(this.name==""){
						this.$refs.name.focus();
						return;
					}
					if(this.subject==""){
						this.$refs.subject.focus();
						return;
					}
					if(this.content==""){
						this.$refs.content.focus();
						return;
					}
					if(this.pwd==""){
						this.$refs.pwd.focus();
						return;
					}
					
					let form=new FormData();
					form.append("name",this.name)
					form.append("subject",this.subject)
					form.append("content",this.content)
					form.append("pwd",this.pwd)
					form.append("no",this.no)
					
					axios.post('../databoard/update_ok_vue.do',form).then(response=>{
						let result=response.data
						if(result==="yes"){
							location.href="../databoard/detail.do?no="+this.no
						}else{
							alert("비밀번호가 틀립니다.")
							this.pwd='';
							this.$refs.pwd.focus();
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