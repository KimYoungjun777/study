<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class='container_main'>
  <div class='top_img'>
    <div class="top_menu_label">맛있는 배달음식!</div>   </div>   
    <nav class="top_menu">
      <a href="/" class="menu_link">홈</a><span class="top_menu_sep"> </span>
      
      
        <c:forEach var="kindVO" items="${list_top }">
          <a href="/substances/list_by_kindno.do?kindno=${kindVO.kindno }" class= "menu_link">${kindVO.title }</a><span class="top_menu_sep"> </span>
        </c:forEach>
        <a href="/visitor/create.do" class="menu_link">회원 가입</a><span class="top_menu_sep"> </span>
      	<a href="/visitor/list.do" class="menu_link">회원 목록</a><span class="top_menu_sep"> </span>
        
        
      <c:choose>
        <c:when test="${sessionScope.grammer_id == null }">
        <a href="/grammer/login.do" class="menu_link">개발자 로그인</a><span class="top_menu_sep"> </span>
        </c:when>
        <c:otherwise>
          <a href="/substances/list_all.do" class="menu_link">전체글 목록</a><span class="top_menu_sep"> </span>
          <a href="/substances/list_all_gallery.do" class="menu_link">갤러리</a><span class="top_menu_sep"> </span>
          <a href="/kind/list_all.do" class="menu_link">종류 전체 목록</a><span class="top_menu_sep"> </span>
          <a href = "/grammer/logout.do" class="menu_link">개발자 ${sessionScope.grammer_gname }님 로그아웃</a>
        </c:otherwise>
       </c:choose>
    </nav>
  </div>
  <div class='content_body'> <!--  내용 시작 -->  