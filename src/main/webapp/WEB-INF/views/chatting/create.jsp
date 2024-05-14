<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>등록하기</title>
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

  
</head>
<body>
<c:import url="/menu/top.do"/>
<div class='title_line'>메시지 등록</div>

<form name='frm' method='post' action='/chatting/create.do'>
	<input type='hidden' name='visitorno' value='${sessionScope.visitorno }'>
	<input type='hidden' name='vname' value='${sessionScope.mname }'>
	<input type='hidden' name='reciverno' value='${param.reciverno }'>
	<input type='hidden' name='rname' value='${param.rname }'>

  <div>
    <label>내용</label>
    <input type="text" name="msg" value="" required="required" autofocus="autofocus" 
               class="form-control form-control-sm" style="width: 50%">
  </div>
  <div class="content_body_bottom">
    <button type="submit" class="btn btn-secondary btn-sm">등록</button>
    <button type="button" onclick="location.href='/substances/list_all.do'" class="btn btn-secondary btn-sm">목록</button> 
  </div>
</form>

<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>

