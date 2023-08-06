<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">Tiles</a></h1>
    </div>
    <div class="fl_right">
      <ul class="inline">
        <li><i class="fa fa-heart"></i>ID: <input type="text" name="id" size=10> </li>
        <li><i class="fa fa-heart"></i>PWD: <input type="password" name="pwd" size=10> </li>
        <li><input type="button" name="login" value="login"> </li>
      </ul>
    </div>
    <!-- ################################################################################################ --> 
  </header>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <!-- ################################################################################################ -->
    <ul class="clear">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li><a class="drop" href="#">회원</a>
        <ul>
          <li><a href="../member/join.do">회원가입</a></li>
          <li><a href="pages/full-width.html">아이디 찾기</a></li>
          <li><a href="pages/sidebar-left.html">비밀번호 찾기</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">맛집</a>
        <ul>
          <li><a href="../food/recommand.do">맛집 추천</a></li>
          <li><a href="../food/food_find.do">맛집 찾기</a></li>
        </ul>
      </li>
      <li><a class="drop" href="../seoul/list.do">서울</a>
        <ul>
          <li><a href="../seoul/list.do">명소</a></li>
          <li><a href="../seoul/list.do?no=2">자연</a></li>
          <li><a href="../seoul/list.do?no=3">쇼핑</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">스토어</a>
        <ul>
          <li><a href="pages/gallery.html">상품</a></li>
          <li><a href="pages/full-width.html">장바구니</a></li>
        </ul>
      </li>
      <li><a href="pages/gallery.html">Community</a></li>
          <li><a href="pages/full-width.html">몰라~</a></li>
          <li><a href="../movie/movie_rank.do">MovieChart</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </nav>
</div>
</body>
</html>