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
function elseInform(){
	var userinput = eval("document.userInform");
	if(!userinput.user_id.value || !userinput.user_pwd.value || !userinput.user_nickName.value || !userinput.user_email.value){
		alert("모든 정보를 입력해 주세요");
		return false;
	}
}
</script>
<style>
	#realbody {
		padding:20px;
	}
</style>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="realbody">
	<h1>회원가입</h1>
	<form action="PageServlet" id="join" method="post" name="userInform" onsubmit="return elseInform()">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="user_id"></td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="user_pwd"></td>
		</tr>
		
		<tr>
			<td>닉네임</td>
			<td><input type="text" name="user_nickName"></td>
		</tr>
		
		<tr>
			<td>E - Mail  </td>
			<td><input type="email" name="user_email"></td>
		</tr>
	</table>
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