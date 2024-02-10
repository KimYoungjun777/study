<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>갤러리</title>
 
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

 <!-- Fotorama -->
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="/jquery/fotorama/fotorama.css" rel="stylesheet"> <!-- /static 기준 -->
<script src="/jquery/fotorama/fotorama.js"></script>

</head> 
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'>
    갤러리
  </DIV>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 
  
  <DIV class='menu_line'></DIV>
  
  <DIV style='margin-left: 300px; text-align: center;'>
  <!-- Fotorama data-ratio="100%/66%" -->
  <div class="fotorama"
         data-autoplay="5000"
         data-nav="thumbs"
         data-width="60%"
         data-ratio="800/520"
         style='margin-top: 50px;'>
         
      <img src="/jquery/fotorama/images/pretty1.jpg">
	    <img src="/jquery/fotorama/images/pretty2.jpg">
	    <img src="/jquery/fotorama/images/pretty3.jpg">
	    <img src="/jquery/fotorama/images/pretty4.jpg">
	    <img src="/jquery/fotorama/images/pretty5.jpg">
    
        
  </div>
  </DIV>


 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
 