<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>글 등록</title>
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->


  
</head>
 
<body>
<c:import url="/menu/top.do" />
 
  <div class='title_line'>${kindVO.title } > 글 등록</div>
  
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
  
  <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_kindno_search_paging.do'>
      <input type='hidden' name='kindno' value='${kindVO.kindno }'>  <%-- 게시판의 구분 --%>
      
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
                    onclick="location.href='./list_by_kindno_search.do?kindno=${kindVO.kindno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </div>
  
  <div class='menu_line'></div>
  
  <form name='frm' method='post' action='./create.do' enctype="multipart/form-data">
    <input type="hidden" name="kindno" value="${param.kindno }">
    
    <div>
       <label>제목</label>
       <input type='text' name='title' value='제목' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='substance' required="required" class="form-control" rows="12" style='width: 100%;'></textarea>
    </div>
    <div>
       <label>검색어</label>
       <input type='text' name='word' value='야식, 배달' required="required" 
                 class="form-control" style='width: 100%;'>
    </div>   
    <div>
       <label>이미지</label>
       <input type='file' class="form-control" name='file1MF' id='file1MF' value='' placeholder="파일 선택">
    </div>   
    <div>
       <label>거래 장소</label>
       <input type='text' name='address' class="form-control form-sm" style='width: 30%;'>
    </div>   
    
    <div>
       <label>거래 가격</label>
       <input type='text' name='price'  class="form-control form-sm" style='width: 30%;'>
    </div>   
    <div>
       <label>패스워드</label>
       <input type='password' name='passwd' value='1234' required="required" 
                 class="form-control" style='width: 50%;'>
    </div>   

    <div class="content_body_bottom">
      <button type="submit" class="btn btn-info btn-sm">등록</button>
      <button type="button" onclick="location.href='./list_by_kindno.do?kindno=${param.kindno}'" class="btn btn-info btn-sm">목록</button>
    </div>
  
  </form>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>