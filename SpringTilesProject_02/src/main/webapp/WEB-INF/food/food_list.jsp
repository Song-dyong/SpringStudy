<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top: 50px;
}

.row {
	margin: 0px auto;
	width: 960px;
}
</style>
</head>
<body>
	<div class="wrapper row3">
		<main class="container clear">
		<div class="jumbotron">
			<h3 class="text-center">${cvo.title }</h3>
			<h4 class="text-center">${cvo.subject }</h4>
		</div>
			<table class="table">
				<tr>
					<td>
					<c:forEach var="vo" items="${list }">
						<table class="table">
							<tr>
								<td width=30% class="text-center" rowspan="3">
									<img alt="" src="${vo.poster }" style="width:250px" class="img-rounded">
								</td>
									<td width="70%"><h3>${vo.name }&nbsp;<span style="color: orange;">${vo.score }</span></h3></td>
							</tr>
							<tr>
								<td width="70%">
								${vo.address }
								</td>
							</tr>
							<tr>
								<td width="70%" height="120">
								</td>
							</tr>
						</table>
					</c:forEach>
					</td>
				</tr>
			</table>
		</main>
	</div>
</body>
</html>