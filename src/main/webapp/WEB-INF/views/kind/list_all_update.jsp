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
<div class='title_line'>종류 수정</div>

    <ASIDE class="aside_right">
    <A href="./create.do?kindno=${kindVO.kindno }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 
  <div class="menu_line"></div>

<form name='frm' method='post' action='/kind/update.do'>
  <input type='hidden' name='kindno' value="${kindVO.kindno }">
  <div>
    <label>종류</label>
    <input type="text" name="title" value="${kindVO.title }" required="required" autofocus="autofocus" 
               class="" style="width: 20%">
               <label>글수</label>
     <input type="text" name="cnt" value="${kindVO.cnt }" required="required" autofocus="autofocus" 
                   class="" style="width: 20%">
    <button type="submit" class="btn btn-secondary btn-sm" style="height:28px; margin-bottom: 5px;">등록</button>
    <button type="button" onclick="location.href='./list_all.do'" class="btn btn-secondary btn-sm" style="height:28px; margin-bottom: 5px;">목록</button> 
  </div>
</form>

<table class="table table-hover">
  <colgroup>
      <col style='width: 20%;'/>
      <col style='width: 20%;'/>
      <col style='width: 20%;'/>    
      <col style='width: 20%;'/>
      <col style='width: 20%;'/>
    </colgroup>
    <thead>
      <tr>
        <th class="th_bs">순서</th>
        <th class="th_bs">종류</th>
        <th class="th_bs">자료수</th>
        <th class="th_bs">등록일</th>
        <th class="th_bs">기타</th>
      </tr>
    </thead>
    <tbody>

    <c:forEach var="kindVO" items="${list }" varStatus="info">
      <c:set var="kindno" value="${kindVO.kindno }" />
      <tr>
        <td class="td_bs">${info.count }</td>
        <td><a href="./read.do?kindno=${kindno }" style="display: block;">${kindVO.title }</a></td>
        <td class="td_bs">${kindVO.cnt }</td>
        <td class="td_bs">${kindVO.rdate.substring(0,10) }</td>
        <td class="td_bs">
          <img src="/kind/images/show.png" class="icon">
          <img src="/kind/images/decrease.png" class="icon">
          <img src="/kind/images/increase.png" class="icon">
          <a href="./update.do?kindno=${kindno }"><img src="/kind/images/update.png" class="icon"></a>
          <a href="./delete.do?kindno=${kindno }"><img src="/kind/images/delete.png" class="icon"></a>
        </td>
      </tr>
     </c:forEach>
    </tbody>
    
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
