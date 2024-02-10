<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class='container_main'>
  <div class='top_img'>
    <div class="top_menu_label">맛집 찾아 3만리</div>      
    <nav class="top_menu">
      <a href="/" class="menu_link">홈</a><span class="top_menu_sep"> </span>
	      <c:forEach var="kindVO" items="${list }">
	        <a href="#" class= "menu_link">${kindVO.title }</a><span class="top_menu_sep"> </span>
	      </c:forEach>
      <a href="/kind/list_all.do" class="menu_link">카테고리 전체 목록</a>
    </nav>
  </div>
  <div class='content_body'> <!--  내용 시작 -->  