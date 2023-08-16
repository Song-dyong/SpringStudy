<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script
	src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script
	src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<style type="text/css">
.row{
	margin: 0px auto;
	width: 100%;
}
</style>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<div class="wrapper row3">
		<main class="container clear">
			<h2 class="sectiontitle">회원가입</h2>
			<div class="row">
				<form method="post" action="../member/join_ok.do" name="joinFrm"
					id="joinFrm">
					<table class="table">
						<tr>
							<th class="text-right" width=10%>ID</th>
							<td width=90% class="inline">
							<input type=text name=id ref="id" size=20 class="input-sm" v-model="id" @blur="idCheck"
								v-bind:disabled="isDisabled"> 
								<p>{{idOk}}</p>
							</td>
						</tr>

						<tr>
							<th class="text-right" width=10%>비밀번호</th>
							<td width=90% class="inline"><input type=password name=pwd
								id=pwd size=20 class="input-sm"> &nbsp;재입력:<input
								type=password name=pwd1 id=pwd1 size=20 class="input-sm">
							</td>
						</tr>

						<tr>
							<th class="text-right" width=10%>이름</th>
							<td width=90% class="inline"><input type=text name=name
								id=name size=20 class="input-sm"></td>
						</tr>

						<tr>
							<th class="text-right" width=10%>성별</th>
							<td width=90% class="inline"><input type="radio" name=sex
								value="남자" checked>남자 <input type="radio" name=sex
								value="여자">여자</td>
						</tr>

						<tr>
							<th class="text-right" width=10%>생년월일</th>
							<td width=90% class="inline"><input type="date"
								name=birthday size=20></td>
						</tr>

						<tr>
							<th class="text-right" width=10%>이메일</th>
							<td width=90% class="inline"><input type=text name=email
								id=email size=55 class="input-sm"> <input type=button
								value="이메일체크" class="btn btn-sm btn-danger" id="emailBtn">
							</td>
						</tr>

						<tr>
							<th class="text-right" width=10%>우편번호</th>
							<td width=90% class="inline">
							<!-- name 대신에 ref / id 대신에 v-model -->
								<input type=text ref="post" v-model="post" size=10 class="input-sm"> 
								<input type=button value="우편번호검색" class="btn btn-sm btn-info" v-on:click="postFind()">
							</td>
						</tr>
						<tr>
							<th class="text-right" width=10%>주소</th>
							<td width=90% class="inline">
								<input type=text ref="addr1" v-model="addr1" size=55 class="input-sm" readonly>
							</td>
						</tr>

						<tr>
							<th class="text-right" width=10%>상세주소</th>
							<td width=90% class="inline"><input type=text name=addr2
								id=addr2 size=55 class="input-sm"></td>
						</tr>

						<tr>
							<th class="text-right" width=10%>전화</th>
							<td width=90% class="inline"><select name=phone1
								class="input-sm">
									<option>010</option>
							</select> <input type=text name=phone id=phone size=12 class="input-sm">
								<input type=button value="전화체크" class="btn btn-sm btn-warning"
								id="phoneBtn"></td>
						</tr>

						<tr>
							<th class="text-right" width=10%>소개</th>
							<td width=90% class="inline"><textarea rows="10" cols="55"
									name=content></textarea></td>
						</tr>

						<tr>
							<td colspan="2" class="text-center"><input type="button"
								value="회원가입" class="btn btn-success btn-sm" id="joinBtn">
								<input type=button value="취소" class="btn btn-info btn-sm"
								onclick="javascript:history.back()"></td>
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
				id:'',
				pwd:'',
				name:'',
				sex:'',
				birthday:'',
				email:'',
				post:'',
				addr1:'',
				addr2:'',
				phone1:'010',
				phone2:'',
				content:'',
				idOk:'',
				pwdOk:'',
				pwdOk2:'',
				emailOk:'',
				phoneOk:'',
				idDisabled:false
			},
			methods:{
				postFind:function(){
					let _this=this;
					/* new를 사용하면 새로운 객체가 생성되는 것 => this는 그 객체의 변수를 의미하므로 _this 필요 */
					new daum.Postcode({
						onconplete:function(data){
							_this.post=data.zonecode;
							_this.addr1=data.address;
						}
					}).open()
				},
				idCheck:function(){
					if(this.id!==''){
						axios.get("http://localhost/web/member/idcheck_ok_vue.do",{
							params:{
								id:this.id
							}
						}).then(res=>{
							console.log(res.data);
							if(res.data='no'){
								this.idOk='이미 존재하는 아이디입니다.'
							}else{
								this.idCheckValidate(res.data)
							}
						})
					}else{
						this.idOk='';
						this.idDisabled=true
					}
				},
				idCheckValidate:function(id){
					console.log(id)
					let id_res=String(id)
					let num=id_res.search(/[0-9]/g)	// 숫자가 있는지 확인 (유효성 검사)
					let eng=id_res.search(/[a-z]/ig)	// -1은 없는 상태
					if(id_res.length<6 || id_res.length>12){
						this.idOk='아이디는 6~12자리 이내로 입력해주세요.'
						return;
					}else if(id_res.search(/\s/)!=-1){
						// 공백 찾기
						this.idOk='아이디는 공백 없이 입력해주세요.'
						return;
					}else if(num<0 || eng<0){
						this.idOk='아이디는 영문과 숫자를 혼합하여 입력해주세요.'
						return;
					}else{
						this.idOk='';
						return;
					}
				}
			}
		})
	</script>
</body>
</html>