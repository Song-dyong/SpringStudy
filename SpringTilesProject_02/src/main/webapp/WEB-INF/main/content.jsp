<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper row3">
		<div id="slider" class="clear">
			<!-- ################################################################################################ -->
			<div class="flexslider basicslider">
				<ul class="slides">
					<li><a href="#"><img class="radius-10"
							src="../images/demo/slides/01.png" alt=""></a></li>
					<li><a href="#"><img class="radius-10"
							src="../images/demo/slides/02.png" alt=""></a></li>
					<li><a href="#"><img class="radius-10"
							src="../images/demo/slides/03.png" alt=""></a></li>
				</ul>
			</div>
			<!-- ################################################################################################ -->
		</div>
	</div>
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<div class="wrapper row3">
		<main class="container clear">
			<!-- ################################################################################################ -->
			<ul class="nospace group btmspace-80">
				<li class="one_third first">
					<article class="service">
						<i class="icon fa fa-ambulance"></i>
						<h6 class="heading">
							<a href="#">오늘의 맛집 추천</a>
						</h6>
						<p>오늘의 맛집 추천</p>
						<footer>
							<a href="#">Read More &raquo;</a>
						</footer>
					</article>
				</li>
				<li class="one_third">
					<article class="service">
						<i class="icon fa fa-h-square"></i>
						<h6 class="heading">
							<a href="#">여행 추천</a>
						</h6>
						<p>Aenean semper elementum tellus, ut placerat leo. Quisque
							vehicula, urna sit amet.</p>
						<footer>
							<a href="#">Read More &raquo;</a>
						</footer>
					</article>
				</li>
				<li class="one_third">
					<article class="service">
						<i class="icon fa fa-hospital-o"></i>
						<h6 class="heading">
							<a href="#">날씨 추천</a>
						</h6>
						<p>Aenean semper elementum tellus, ut placerat leo. Quisque
							vehicula, urna sit amet.</p>
						<footer>
							<a href="#">Read More &raquo;</a>
						</footer>
					</article>
				</li>
			</ul>
			<!-- ################################################################################################ -->
			<h2 class="sectiontitle">지역별 맛집</h2>
			<div class="flexslider carousel basiccarousel btmspace-80">
				<ul class="slides">
					<c:forEach var="vo" items="${cList }" varStatus="s">
						<c:if test="${s.index>0 && s.index<13 }">
						<li>
							<figure>
								<img class="radius-10 btmspace-10" src="${vo.poster }" alt="">
								<figcaption>
									<a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a>
								</figcaption>
							</figure>
						</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<h2 class="sectiontitle">두번째 맛집</h2>
			<div class="flexslider carousel basiccarousel btmspace-80">
				<ul class="slides">
					<c:forEach var="vo" items="${cList }" varStatus="s">
						<c:if test="${s.index>12 && s.index<19 }">
						<li>
							<figure>
								<img class="radius-10 btmspace-10" src="${vo.poster }" alt="">
								<figcaption>
									<a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a>
								</figcaption>
							</figure>
						</li></c:if>
					</c:forEach>
				</ul>
			</div>
			<h2 class="sectiontitle">뭔 맛집</h2>
			<div class="flexslider carousel basiccarousel btmspace-80">
				<ul class="slides">
					<c:forEach var="vo" items="${cList }" varStatus="s">
						<c:if test="${s.index>18 && s.index<31 }">
						<li>
							<figure>
								<img class="radius-10 btmspace-10" src="${vo.poster }" alt="">
								<figcaption>
									<a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a>
								</figcaption>
							</figure>
						</li></c:if>
					</c:forEach>
				</ul>
			</div>
			<!-- / main body -->
			<div class="clear"></div>
		</main>
	</div>
</body>
</html>