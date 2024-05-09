<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <c:set var="substancesno" value="${substancesVO.substancesno }"/>
<c:set var="kindno" value="${substancesVO.kindno }"/>
<c:set var="thumb1" value="${substancesVO.thumb1 }"/>
<c:set var="file1saved" value="${substancesVO.file1saved }"/>
<c:set var="substance" value="${substancesVO.substance }"/>
<c:set var="rdate" value="${substancesVO.rdate }"/>
<c:set var="youtube" value="${substancesVO.youtube }"/>
<c:set var="map" value="${substancesVO.map }"/>
<c:set var="word" value="${substancesVO.word }"/>
<c:set var="address" value="${substancesVO.address }"/>
<c:set var="file1" value="${substancesVO.file1 }"/>
<c:set var="size1_label" value="${substancesVO.size1_label }"/>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>종류 별 글 목록</title>
 
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->


    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'>
    ${kindVO.title } 글 목록 
    <c:if test="${param.word.length() > 0 }">
    > 『${param.word }』 검색 ${search_count} 건
    </c:if>
  </DIV>

  <ASIDE class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.id != null or sessionScope.grade == 1}">
      <A href="./create.do?kindno=${kindVO.kindno }">등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>    
    <A href="./list_by_kindno.do?kindno=${param.kindno }&now_page=${param.now_page == null ? 1 : param.now_page}&word=${param.word }">목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_kindno_grid.do?kindno=${param.kindno }&now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word }">갤러리형</A>
    
  </ASIDE> 
  
    <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_kindno.do'>
      <input type='hidden' name='kindno' value='${kindVO.kindno }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우는 검색어 출력 --%>
          <input type='text' name='word' id='word' value='${param.word }'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우는 검색어 출력x --%>
          <input type='text' name='word' id='word' value=''>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-info btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;">검색</button>
      <c:if test="${param.word.length() > 0 }"> <%-- '검색취소' 버튼 출력 --%>
        <button type='button' class='btn btn-info btn-sm' 
                    onclick="location.href='./list_by_kindno.do?kindno=${kindVO.kindno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 10%;"></col>
      <col style="width: 70%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>

    <thead>
      <tr>
        <th style='text-align: center;'>닉네임</th>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>제목</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    
    </thead>
    
    <tbody>
        <c:forEach var="substancesVO" items="${list }" varStatus="info">
          <c:set var="substancesno" value="${substancesVO.substancesno }" />
          <c:set var="thumb1" value="${substancesVO.thumb1 }" />
    
          <tr onclick="location.href='./read.do?substancesno=${substancesno}&kindno=${param.kindno }&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }'" style="cursor:pointer;">
             <td class="td_bs" style="vertical-align: middle;">
             	${substancesVO.name}
             </td>
             <td>
              <c:choose>
                <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                  <img src="/substances/storage/${thumb1 }" style="width: 120px; height: 90px;">
                </c:when>
                <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/contents/images/none1.png -->
                  <img src="/substances/images/none1.png" style="width: 120px; height: 90px;">
                </c:otherwise>
              </c:choose>
            </td>

            <td class="td_bs_left">
           <span style="font-weight: bold;"> ${substancesVO.title }</span><br>
            <c:choose>
              <c:when test="${substancesVO.substance.length() > 160 }">
              ${substancesVO.substance.substring(0,160) }...
              </c:when>
              <c:otherwise>
              ${substancesVO.substance }
              </c:otherwise>
              </c:choose> 
            (${substancesVO.rdate.substring(0,16) })
            </td>
              <td class="td_bs" style="vertical-align: middle;">
              <c:if test="${sessionScope.visitorno == substancesVO.visitorno or sessionScope.grade == 1}">
                <a href="./map.do?substancesno=${substancesno }&kindno=${param.kindno}" title="지도"><img src="/substances/images/map.jpg" class="icon"></a>
                <a href="./youtube.do?substancesno=${substancesno }&kindno=${param.kindno}" title="유튜브"><img src="/substances/images/youtube.jpg" class="icon"></a>
                <a href="./delete.do?substancesno=${substancesno }&kindno=${param.kindno}&now_page=${param.now_page == null ? 1 : param.now_page }" title="삭제"><img src="/substances/images/delete.jpg" class="icon"></a>
              </c:if>
              </td>
              
          </tr>
        </c:forEach>
      </tbody>


  </table>
  <!-- 페이지 목록 출력 부분 시작 -->
    <div class="bottom_menu">${paging}</div>
  <!-- 페이지 목록 출력 부분 종료 -->

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
 