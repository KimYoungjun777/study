<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<style>
  .top_menu_link:link{  /* 방문전 상태 */
    text-decoration: none; /* 밑줄 삭제 */
    color: #FFFFFF;
    font-weight: bold;
  }

  .top_menu_link:visited{  /* 방문후 상태 */
    text-decoration: none; /* 밑줄 삭제 */
    color: #FFFFFF;
    font-weight: bold;
  }

  .top_menu_link:hover{  /* A 태그에 마우스가 올라간 상태 */
    text-decoration: none; /* 밑줄 출력 */
    color: #FFFFFF;
    font-size: 1.05em;
  }
</style> 
<div class='container_main'>
  <div class='top_img'>
    </div>   
	
	<nav class="navbar navbar-expand-md navbar-dark" style="--bs-bg-opacity: .1; background-color: #dda0dd;">
      <a class="navbar-brand" href="/"><img src='/css/images/home2.png' title="시작페이지" style='display: block; padding-left: 5px;'></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle Navigation">
        <span class="navbar-toggler-icon"></span>
      </button>    
      
     	<div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
      
        <c:forEach var="kindVO" items="${list_top }">
         <c:set var="kindno" value="${kindVO.kindno }" />
         <c:set var="title" value="${kindVO.title }" />
         <li class="nav-item">
          <a href="/substances/list_by_kindno.do?kindno=${kindVO.kindno }&now_page=1" class= "nav-link top_menu_link">${kindVO.title }</a>
         </li>
        </c:forEach>
        
        <li class="nav-item">
          <a href="/substances/list_all.do" class="nav-link top_menu_link">전체글 목록</a>
        </li>
        
        <li class="nav-item dropdown"> <%-- 회원 서브 메뉴 --%>
              <a class="nav-link top_menu_link dropdown-toggle" data-bs-toggle="dropdown" href="#">회원</a>
              <div class="dropdown-menu">
                <c:choose>
                  <c:when test="${sessionScope.id == null }">
                    <a class="dropdown-item" href="/visitor/create.do">회원 가입</a>
                    <a class="dropdown-item" href="#">아이디 찾기</a>
                    <a class="dropdown-item" href="#">비밀번호 찾기</a>
                  </c:when>
                  <c:otherwise>
                    <a class="dropdown-item" href="/visitor/read.do">가입 정보</a>
                    <a class="dropdown-item" href="/visitor/passwd_update.do">비밀번호 변경</a>
                    <a class="dropdown-item" href="/visitor/read.do">회원 정보 수정</a>
                    <a class="dropdown-item" href="javascript: alert('개발 예정')">로그인 내역</a>
                    <a class="dropdown-item" href="#">회원 탈퇴</a>
                  </c:otherwise>
                </c:choose>
              </div>
            </li>
            
        
        
      <c:choose>
        <c:when test="${sessionScope.grammer_id == null }">
        <li class="nav-item">
        	<a href="/grammer/login.do" class="nav-link top_menu_link">개발자 로그인</a>
        </li>
        </c:when>
        <c:otherwise>
        	 <li class="nav-item dropdown"> <%-- 관리자 서브 메뉴 --%>
             <a class="nav-link top_menu_link dropdown-toggle" data-bs-toggle="dropdown" href="#">개발자</a>
             <div class="dropdown-menu">
             		<a href="/substances/list_by_kindno.do?kindno=5&now_page=1" class= "dropdown-item">태니의 낙서</a>
			          <a href="/substances/list_all_gallery.do" class="dropdown-item">갤러리</a>
			          <a href="/kind/list_all.do" class="dropdown-item">종류 전체 목록</a>
			          <a href="/visitor/list.do" class="dropdown-item">회원 목록</a>
			          <a href = "/grammer/logout.do" class="dropdown-item">개발자 ${sessionScope.grammer_gname }님 로그아웃</a>
			        </div>
          	</li>
        </c:otherwise>
       </c:choose>
       
       <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <c:choose>
                  <c:when test="${sessionScope.id == null}">
                      <a class="nav-link top_menu_link" href="/visitor/login.do">로그인</a>
                  </c:when>
                  <c:otherwise>
                      <a class="nav-link top_menu_link" href='/visitor/logout.do'>${sessionScope.mname }님 로그아웃</a>
                  </c:otherwise>
              </c:choose>
            </li>     
          </ul>
      </div>    
  </nav>
  
  <div class='content_body'> <!--  내용 시작 -->  