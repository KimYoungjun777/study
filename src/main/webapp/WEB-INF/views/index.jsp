<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<% 
DecimalFormat df = new DecimalFormat("#,###,##0");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>홈 화면</title>
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="/jquery/fotorama/fotorama.css" rel="stylesheet"> <!-- /static 기준 -->
<script src="/jquery/fotorama/fotorama.js"></script>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
</head>
<body>
<c:import url="/menu/top.do"/>



  <DIV style='margin-left: 300px; text-align: center;'>

  </DIV>

<jsp:include page="./menu/bottom.jsp" flush="false" />
 
</body>
</html>