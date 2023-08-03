<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<c:forEach var="vo" items="${list }">
			<div class="col-md-3">
					<div class="thumbnail">
						<a href="#" target="_blank"> <img
							src="${vo.poster}" alt="../seoul/aaaa.png" style="width: 380px;height: 180px;">
							<div class="caption">
								<p style="font-size:12px">${vo.title }</p>
							</div>
						</a>
					</div>
				</div>
		</c:forEach>
	</div>
	<div style="height: 20px"></div>
	<div class="row text-center">
		<ul class="pagination">
		<c:if test="${startPage>1 }">
			  <li><a href="../seoul/nature.do?page=${startPage-1 }">&lt;</a></li>
		</c:if>
			  <c:forEach var="i" begin="${startPage }" end="${endPage }">
			  <li ${i==curpage?"class=disabled":"" }><a href="../seoul/nature.do?page=${i }">${i }</a></li>
			  </c:forEach>
			  <c:if test="${endPage<totalpage }">
			  <li><a href="../seoul/nature.do?page=${endPage+1 }">&gt;</a></li>
			  </c:if>
		</ul>
	</div>
</body>
</html>