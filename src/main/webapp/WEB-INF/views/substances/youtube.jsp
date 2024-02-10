<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>youtube 등록</title>
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

  
</head>
<body>

<c:import url="/menu/top.do" />
  <DIV class='title_line'><A href="./list_by_kindno.do?kindno=${kindVO.kindno }" class='title_link'>${kindVO.title }</A> > ${substancesVO.title } > 지도 등록/수정</DIV>
  
  <ASIDE class="aside_right"> 
    <A href="javascript:location.reload();">새로고침</A>
      <span class='menu_divide' >│</span>    
      <A href="./list_by_kindno.do?kindno=${kindno }&now_page=${param.now_page}&word=${param.word }">기본 목록형</A>    
      <span class='menu_divide' >│</span>
      <A href="./list_by_kindno_grid.do?kindno=${kindno }&now_page=${param.now_page}&word=${param.word }">갤러리형</A>
    </ASIDE> 
    
    <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_kindno.do'>
      <input type='hidden' name='kindno' value='${kindno }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' class='input_word'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-info btn-sm'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' class='btn btn-info btn-sm' 
                    onclick="location.href='./list_by_kindno.do?kindno=${kindno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>
  <%--등록/수정/삭제 폼 --%>
  <FORM name='frm_youtube' method='post' action='./youtube.do'>
    <input type="hidden" name="substancesno" value="${param.substancesno }">
    
    <div>
       <label>Youtube 소스</label>
       <textarea name='youtube' class="form-control" rows="12" style='width: 100%;'>${substancesVO.youtube }</textarea>
    </div>
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-info btn-sm">저장</button>
      <button type="button" onclick="frm_youtube.youtube.value=''; frm_youtube.submit();" class="btn btn-info btn-sm">소스 삭제</button>
      <button type="button" onclick="history.back();" class="btn btn-info btn-sm">취소</button>
    </div>
  
  </FORM>

    
    
    
    
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>