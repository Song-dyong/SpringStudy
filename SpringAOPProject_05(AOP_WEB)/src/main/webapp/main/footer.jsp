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
<div class="wrapper row4">
  <footer id="footer" class="clear"> 
    <div class="one_third first">
      <h6 class="title">맛집 top7</h6>
      <ul class="nospace">
	      <c:forEach var="fvo" items="${foodList }">
        	<li>${fvo.name }</li>
    	  </c:forEach>
      </ul>
    </div>
    <div class="one_third">
      <h6 class="title">서울 top7</h6>
      <ul class="nospace">
        <c:forEach var="svo" items="${seoulList }">
        	<li>${svo.title }</li>
    	  </c:forEach>
      </ul>
    </div>
    <div class="one_third">
    	<h6>로그인</h6>
      <h6 class="title">Keep In Touch</h6>
      <form class="btmspace-30" method="post" action="#">
        <fieldset>
          <legend>Newsletter:</legend>
          <input class="btmspace-15" type="text" value="" placeholder="Email">
          <button type="submit" value="submit">Submit</button>
        </fieldset>
      </form>
      <ul class="faico clear">
        <li><a class="faicon-facebook" href="#"><i class="fa fa-facebook"></i></a></li>
        <li><a class="faicon-twitter" href="#"><i class="fa fa-twitter"></i></a></li>
        <li><a class="faicon-linkedin" href="#"><i class="fa fa-linkedin"></i></a></li>
        <li><a class="faicon-google-plus" href="#"><i class="fa fa-google-plus"></i></a></li>
        <li><a class="faicon-instagram" href="#"><i class="fa fa-instagram"></i></a></li>
        <li><a class="faicon-tumblr" href="#"><i class="fa fa-tumblr"></i></a></li>
      </ul>
    </div>
  </footer>
</div>
<div class="wrapper row5">
  <div id="copyright" class="clear"> 
    <p class="fl_left">SsangYong<a href="https://github.com/Song-dyong">D Room</a></p>
    <p class="fl_right">Created by <a href="https://github.com/Song-dyong">SongDoYong</a></p>
  </div>
</div>
</body>
</html>