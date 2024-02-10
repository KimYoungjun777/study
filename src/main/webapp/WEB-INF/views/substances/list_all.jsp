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
<c:set var="file1" value="${substancesVO.file1 }"/>
<c:set var="size1_label" value="${substancesVO.size1_label }"/>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>전체글 목록</title>
 
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'>
    전체 글 목록
  </DIV>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 80%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>

    <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>제목</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    
    </thead>
    
    <tbody>
        <c:forEach var="substancesVO" items="${list }" varStatus="info">
          <c:set var="substancesno" value="${substancesVO.substancesno }" />
          <c:set var="thumb1" value="${substancesVO.thumb1 }" />
          <c:if test="${substancesVO.kindno != 5 }">
          <tr onclick="location.href='./read.do?substancesno=${substancesno}'" style="cursor:pointer;">
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
                <a href="./map.do?substancesno=${substancesno }&kindno=${param.kindno}" title="위치"><img src="/substances/images/map.jpg" class="icon"></a>
                <a href="./youtube.do?substancesno=${substancesno }&kindno=${param.kindno}" title="유튜브"><img src="/substances/images/youtube.jpg" class="icon"></a>
                <a href="./delete.do?substancesno=${substancesno }&kindno=${param.kindno}&now_page=${param.now_page == null ? 1 : param.now_page }" title="삭제"><img src="/substances/images/delete.jpg" class="icon"></a>
              </td>
          </tr>
          </c:if>
        </c:forEach>
      </tbody>


  </table>


 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
 