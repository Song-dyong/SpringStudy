<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	width: 650px;
}
</style>
</head>
<body>
	<div class="row row1">
		<h1 class="text-center">글수정하기</h1>
		<form action="../databoard/update_ok.do" method="post">
		<table class="table">
			<tr>
				<th width="15%" class="text-right success">이름</th>
				<td width="85%">
					<input type="hidden" name=no value="${vo.no }">
					<input type="text" name=name size=15 value="${vo.name }">
				</td> 
			</tr>
			<tr>
				<th width="15%" class="text-right success">제목</th>
				<td width="85%">
					<input type="text" name=subject size=15 class="input-sm" value="${vo.subject }">
				</td> 
			</tr>
			<tr>
				<th width="15%" class="text-right success">제목</th>
				<td width="85%">
					<textarea rows="10" cols="50" name="content" class="input-sm">${vo.content }</textarea>
				</td> 
			</tr>
			<tr>
				<th width="15%" class="text-right success">비밀번호</th>
				<td width="85%">
					<input type="password" name=pwd size=10 class="input-sm">
				</td> 
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<button class="btn btn-sm btn-primary">수정</button>
					<input type="button" value="취소" class="btn btn-sm btn-danger" 
					onclick="javascript:history.back()">
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>