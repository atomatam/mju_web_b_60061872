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
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Expires" CONTENT="0">
<title>Insert title here</title>
<style>
	margin-bottom: 40px;
	font-family: 'Lato', 'Sans-Serief';
	color:#fff;
</style>
</head>
<body>

	<div>
	<form name="login-box" method="post" action="PageServlet">
		아이디 : <input type="text" name="user_id">
		비밀 번호 : <input type="password" name = "user_pwd">
		<input type="hidden" name="op" value="login">
		<input type="submit" value="확인"><input type="reset" value="취소">
	</form>
	</div>
</body>
</html>