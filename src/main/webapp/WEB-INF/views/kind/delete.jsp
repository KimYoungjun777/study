<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dev.mvc.kind.KindVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>삭제하기</title>
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head>
<%
KindVO kindVO = (KindVO)request.getAttribute("kindVO");
int kindno = kindVO.getKindno();
String title = kindVO.getTitle();
%>
<body>
<c:import url="/menu/top.do"/>
<div class='title_line'>종류> [<%=title %>]삭제</div>
<div id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <form name='frm_delete' id='frm_delete' method='post' action='./delete.do'>
      <input type="hidden" name="kindno" value="<%=kindno %>">
      
      <div class="msg_warning">종류를 삭제하면 복구 할 수 없습니다.</div>
      <label>종류</label>: <%=title %>
  
      <button type="submit" id='submit' class='btn btn-warning btn-sm' style='height: 28px; margin-bottom: 5px;'>삭제</button>
      <button type="button" onclick="location.href='/kind/list_all.do'" class='btn btn-info btn-sm' style='height: 28px; margin-bottom: 5px;'>취소</button>
    </FORM>
  </DIV>

<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>

