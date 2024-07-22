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
<c:set var="price" value="${substancesVO.price }"/>
<c:set var="file1" value="${substancesVO.file1 }"/>
<c:set var="size1_label" value="${substancesVO.size1_label }"/>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>글 수정</title>
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

  
</head>
<body>
<c:import url="/menu/top.do" />

  <DIV class='title_line'>
    ${kindVO.title } > ${substancesVO.title } 수정
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
  
  <form name='frm' method='post' action='./update_text.do'>
    <input type="hidden" name="kindno" value="${kindno }">
    <input type="hidden" name="substancesno" value="${substancesno }">
    <input type="hidden" name="now_page" value="${param.now_page }">
    
    <div>
       <label>제목</label>
       <input type='text' name='title' value='${substancesVO.title }' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='substance' required="required" class="form-control" rows="12" style='width: 100%;'>${substance }</textarea>
    </div>
    <div>
       <label>검색어</label>
       <input type='text' name='word' value="${word }" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>   
    
    <div>
       <label>거래 장소</label>
       <input type='text' class="form-control form-sm" value=${address } style='width: 30%;'name='address' >
    </div>   
    
    <div>
       <label>거래 가격</label>
       <input type='text' class="form-control form-sm" value=${price } style='width: 30%;'name='price' >
    </div>   
    
    <div>
      <label>패스워드</label>
      <input type='password' name='passwd' value='' required="required" 
                class="form-control" style='width: 50%;'>
    </div>
    
    <div>
    	<input type="radio" name="state" value="no"> 판매중 <br>
      <input type="radio" name="state" value="ing"> 채팅거래중 <br>
      <input type="radio" name="state" value="end"> 판매완료
    </div>
       
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-info btn-sm">저장</button>
      <button type="button" onclick="history.back();" class="btn btn-info btn-sm">취소</button>
    </div>
  
  </FORM>  
  
  
  
  
  
  
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>