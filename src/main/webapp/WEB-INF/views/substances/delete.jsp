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
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>글 삭제/</title>
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->


  
</head>
<body>
<c:import url="/menu/top.do" />

  <DIV class='title_line'>
    ${kindVO.title } > ${substancesVO.title } 삭제
  </DIV>
  
  <ASIDE class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.grammer_id != null }">
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
  
  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style='text-align: center; width: 50%; float: left;'>

          <c:choose>
            <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
              <img src="/substances/storage/${file1saved }" style='width: 90%;'> 
            </c:when>
            <c:otherwise> <!-- 이미지가 없는 경우 -->
              상품 관련 이미지가 없습니다.
            </c:otherwise>
          </c:choose>
        </DIV>

        <DIV style='text-align: left; width: 47%; float: left;'>
          <span style='font-size: 1.5em;'>${title}</span>
          <c:if test="${size1 > 0 }">
            <br>삭제되는 파일: ${file1 }
          </c:if>
          <br>
          <FORM name='frm' method='post' action='./delete.do'>
            <input type="hidden" name="substancesno" value="${substancesno }">
              <input type='hidden' name='kindno' value='${kindno}'>
            <input type="hidden" name="now_page" value="${param.now_page}">
              <br><br>
              <div style='text-align: center; margin: 10px auto;'>
                <span style="color: #FF0000; font-weight: bold;">삭제를 진행 하시겠습니까? 삭제하시면 복구 할 수 없습니다.</span><br><br>
                <br><br>
                <button type = "submit" class="btn btn-info btn-sm">삭제 진행</button>
                <button type = "button" onclick = "history.back()" class="btn btn-info btn-sm">취소</button>
              </div>   
          </FORM>
        </DIV>
      </li>
     </ul>
  </fieldset>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
  
  
  
  
  
  
 
