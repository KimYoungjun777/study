<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>개발자 로그인</title>
<link rel="shortcut icon" href="/css/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->


</head> 
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'>개발자 로그인</DIV>

  <DIV class='content_body'> 
    <DIV style='width: 40%; margin: 0px auto;'>
      <FORM name='frm' method='POST' action='./login.do'>
      
        <div class="form-group">
          <label>아이디:</label> 
          <input type='text' class="form-control" name='id' id='id' required="required" 
                    style='width: 80%;' placeholder="아이디" autofocus="autofocus" value="${cookie.ck_grammer_id.value }">
          <Label>   
            <input type='checkbox' name='id_save' value='Y' ${cookie.ck_grammer_id_save.value == 'Y' ? "checked='checked'" : "" }> 저장
          </Label> 
        </div>   
     
        <div class="form-group">
          <label>패스워드:</label> 
          <input type='password' class="form-control" name='passwd' id='passwd' 
                    required="required" style='width: 80%;' placeholder="패스워드" value="${cookie.ck_grammer_passwd.value }">
          <Label>
            <input type='checkbox' name='passwd_save' value='Y' ${cookie.ck_grammer_passwd_save.value == 'Y' ? "checked='checked'" : "" }> 저장
          </Label> 
        </div>   
     
        <button type="submit" class="btn btn-info">로그인</button>
        <button type="button" onclick="history.back();" class="btn btn-info">취소</button>
        
      </FORM>
    </DIV>
  </DIV> <%-- <DIV class='content_body'> END --%>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>