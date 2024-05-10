<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.kind.KindVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>종류 목록</title>
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

  
</head>
<body>
<c:import url="/menu/top.do"/>
<div class='title_line'>종류</div>
  <aside class="aside_right">
   <a href="./create.do?kindno=${kindVO.kindno }">등록</a>
	 <span class='menu_divide' >│</span>
	 <a href="javascript:location.reload();">새로고침</a>
  </aside>
  <div class="menu_line"></div>

<form name='frm' method='post' action='/kind/create.do'>
  <div>
    <label>종류</label>
    <input type="text" name="title" value="" required="required" autofocus="autofocus" 
               class="" style="width: 50%">
    <button type="submit" class="btn btn-secondary btn-sm" style="height:28px; margin-bottom: 5px;">등록</button>
    <button type="button" onclick="location.href='./list_all.do'" class="btn btn-secondary btn-sm" style="height:28px; margin-bottom: 5px;">목록</button> 
  </div>
</form>

<table class="table table-hover">
  <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 10%;'/>    
      <col style='width: 60%;'/>
      <col style='width: 20%;'/>
    </colgroup>
    <thead>
      <tr>
        <th class="th_bs">메시지번호 </th>
        <th class="th_bs">채팅 상대</th>
        <th class="th_bs">내용</th>
        <th class="th_bs">등록일</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="chattingVO" items="${list_by_visitor }" varStatus="info">
      <c:set var="chattingno" value="${chattingVO.chattingno }" />
      <tr>
        <td class="td_bs">${chattingVO.chattingno }</td>
				<td class="td_bs">${chattingVO.rname}</td>				
        <td class="td_bs">${chattingVO.msg }</td>
        <td class="td_bs">${chattingVO.rdate.substring(0,19) }</td>
      </tr>
     </c:forEach>
    </tbody>
    
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
