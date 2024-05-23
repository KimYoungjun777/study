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
<title>보낸 메시지</title>
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

  
</head>
<body>
<c:import url="/menu/top.do"/>
<div class='title_line'>『${chattingVO.vname}』님에게 받은 메시지 목록</div>
  <aside class="aside_right">
   <a href="./create.do?kindno=${kindVO.kindno }">등록</a>
	 <span class='menu_divide' >│</span>
	 <a href="javascript:location.reload();">새로고침</a>
	 <span class='menu_divide' >│</span>
  	<a href="./list_by_reciver.do">받은 메시지</a>
  </aside>
  <div class="menu_line"></div>


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
        <th class="th_bs">보낸 사람</th>
        <th class="th_bs">내용</th>
        <th class="th_bs">등록일</th>
      </tr>
    </thead>
    <tbody>
    
      <c:forEach var="chattingVO" items="${chatting_list_by_reciver }" varStatus="info">
      <c:set var="chattingno" value="${chattingVO.chattingno }" />
      <tr onclick="location.href='./read_by_reciver.do?chattingno=${chattingVO.chattingno }'" style="cursor:pointer;">
	        <td class="td_bs">${chattingVO.chattingno }</td>
					<td class="td_bs">${chattingVO.vname}</td>				
	        <td class="td_bs">${chattingVO.msg }</td>
	        <td class="td_bs">${chattingVO.rdate.substring(0,19) }</td>
     		</tr>
     </c:forEach>
     
    </tbody>
    
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
