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
<c:set var="bookmark_cnt" value="${bookmark_cnt }" />


<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>글 조회</title>
 
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">

var bookmark_cnt = ${bookmark_cnt};
var substancesno = ${substancesno};
var visitorno = ${visitorno};

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/bookmark/bookmark_by_visitor_cnt.do',
        cache: false,
        data: { substancesno: substancesno, visitorno: visitorno },
        dataType: 'json',
        success: function (response) {
            console.log(response.cnt);
            var visitor_cnt = response.cnt;
            if (visitor_cnt == 1) {
                $('#bookmarkbtn').hide();
                $('#bookmarkbtn2').show();
            } else {
                $('#bookmarkbtn').show();
                $('#bookmarkbtn2').hide();
            }
            $('#bookmark_cnt').text("『" + bookmark_cnt + "명의 이용자들이 이 게시글을 북마크했습니다.』");
        },
        error: function (error) {
            console.error('게시글 찜 실패:', error);
        }
    });
});

function create() {
    $.ajax({
        type: 'POST',
        url: '/bookmark/create.do',
        data: { substancesno: substancesno, visitorno: visitorno },
        success: function (response) {
                bookmark_cnt = response.bookmark_cnt;
                $('#bookmarkbtn').hide();
                $('#bookmarkbtn2').show();
                $('#bookmark_cnt').text("『" + bookmark_cnt + "명의 이용자들이 이 게시글을 북마크했습니다.』");
        },
        error: function (error) {
            console.error('찜 등록 실패:', error);
        }
    });
}

function unlike() {
    var substancesno = ${substancesno};
    var visitorno = ${visitorno};

    // 서버로 좋아요 요청을 보냄
    $.ajax({
        type: 'POST',
        url: '/bookmark/delete.do',
        data: { substancesno: substancesno, visitorno: visitorno },
        success: function (response) {
        				bookmark_cnt = response.bookmark_cnt;
                $('#bookmarkbtn').show();
                $('#bookmarkbtn2').hide();
                $('#bookmark_cnt').text("『" + bookmark_cnt + "명의 이용자들이 이 게시글을 북마크했습니다.』");
        },
        error: function (error) {
            console.error('찜 해제 실패:', error);
        }
    });
}
</script>
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'><A href="./list_by_kindno.do?kindno=${kindno }" class='title_link'>${kindVO.title } > ${substancesVO.title }</A></DIV>

  <ASIDE class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.visitorno == substancesVO.visitorno}">
      <%--
      http://localhost:9091/contents/create.do?kindno=1
      http://localhost:9091/contents/create.do?kindno=2
      http://localhost:9091/contents/create.do?kindno=3
      --%>
      <A href="./create.do?kindno=${kindno }">등록</A>
      <span class='menu_divide' >│</span>
      
      <A href="./update_text.do?substancesno=${substancesno}&now_page=${param.now_page == null ? 1 : param.now_page}&word=${param.word }&kindno=${kindno }">글 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./update_file.do?substancesno=${substancesno}&now_page=${param.now_page == null ? 1 : param.now_page}&kindno=${kindno }">파일 수정</A>  
      <span class='menu_divide' >│</span>
      <A href="./map.do?kindno=${kindno }&substancesno=${substancesno}&kindno=${kindno }">지도</A>
      <span class='menu_divide' >│</span>
      <A href="./youtube.do?kindno=${kindno }&substancesno=${substancesno}&kindno=${kindno }">Youtube</A>
      <span class='menu_divide' >│</span>
      <A href="./delete.do?substancesno=${substancesno}&now_page=${param.now_page == null ? 1 : param.now_page}&kindno=${kindno}">삭제</A>  
      <span class='menu_divide' >│</span>
    </c:if>

    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>    
    <A href="./list_by_kindno.do?kindno=${kindno }&now_page=${param.now_page == null ? 1 : param.now_page}&word=${param.word }">목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_kindno_grid.do?kindno=${kindno }&now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word }">갤러리형</A>
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

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">

          <span style="font-size: 1.5em; font-weight: bold;">제목 : ${substancesVO.title }</span ><br>
          <span style="font-size: 25px; text-align: right;">작성자 :  ${substancesVO.name }</span>
          <br><span style="font-size: 1em;">${rdate }</span><br>
          <br>
          ${substance }
        </DIV>
      </li>
      
      <c:if test="${youtube.trim().length() > 0 }">
        <li class="li_none" style="clear: both; padding-top: 5px; padding-bottom: 5px;">
          <DIV style="text-align: center;">
            ${youtube }
          </DIV>
        </li>
      </c:if>
      
      <c:if test="${substancesVO.map.trim().length() > 0 }">
        <li class="li_none" style="clear: both; padding-top: 5px; padding-bottom: 5px;">
          <DIV style='text-align: center; width:640px; height: 360px; margin: 0px auto;'>
            ${substancesVO.map }
          </DIV>
        </li>
      </c:if>
      
    
    	
      <li class="li_none">
        <div>
          <c:if test="${file1.trim().length() > 0 }">
            첨부 파일: <a href='/download?dir=/substances/storage&filename=${file1saved}&downname=${file1}'>${file1}</a> (${size1_label}) 
            <a href='/download?dir=/substances/storage&filename=${file1saved}&downname=${file1}'><img src="/substances/images/download.jpg" style='width: 25px; height:25px;]'></a>
          </c:if>
        </div>
     </li>   
     <li class="li_none">
      <button type="button" class="btn btn-info btn-sm" onclick="location.href='/chatting/create.do?reciverno=${substancesVO.visitorno}&rname=${substancesVO.name }'">채팅하기</button>
			</li>
    </ul>
  </fieldset>

  <form id="bookmark_form">						
	  	<input type="hidden" name="substancesno" value="${substancesno}">			
	  	<input type="hidden" name="visitorno" value="${visitorno}">			
			<img src="/bookmark/images/bookmark2.png"  onclick="create();" id="bookmarkbtn" title="찜하기">	
			<img src="/bookmark/images/bookmark1.png"  onclick="unlike();" id="bookmarkbtn2" title="찜해제"style="display: none;">	
			<span id= "bookmark_cnt" name="bookmark_cnt">『${bookmark_cnt }명의 이용자들이 이 게시글을 북마크했습니다.』</span>
  </form>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>
 