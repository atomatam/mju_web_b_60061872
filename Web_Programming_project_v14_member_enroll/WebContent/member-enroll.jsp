
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<script lang="JavaScript">
function checkIt(){
	var userinput = eval("document.userInform");
	if(!userinput.user_id.value){
		alert("ID를 입력하세요");
		return false;
	}
	if(!userinput.user_pwd.value){
		alert("비밀번호를 입력하세요");
		return false;
	}
	if(!userinput.user_nickName.value){
		alert("닉네임을 입력하세요");
		return false;
	}
	if(!userinput.user_email.value){
		alert("E-mail을 입력하세요");
		return false;
	}
}

</script>
<style>
#realbody {
	border:10px;
	padding:15px;
}

</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="realbody">
<h1>Join Us</h1>
<form action="PageServlet" id="join" method="post" name="userInform" onsubmit="return checkIt()">
<fieldset>
<legend>기본 사항</legend>

<label>아이디  </label>
<input type="text" name="user_id">

<label>비밀번호</label>
<input type="password" name="user_pwd"><br>

<label>닉네임  </label>
<input type="text" name="user_nickName"><br>

<label>E - Mail  </label>
<input type="email" name="user_email"><br>
</fieldset>
<div>
<input type="submit" value="회원가입">
<input type="reset" value="다시쓰기">
<input type="hidden" name="op" value="memberEnroll">
</div>
</form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>