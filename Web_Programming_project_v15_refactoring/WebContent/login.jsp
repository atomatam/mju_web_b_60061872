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
<title>Insert title here</title>
<style>
	#login-box {
		padding:20px;
	}
	#logout-box{
		padding:40px 0 0 0;
	}
	body{
		padding:20px;
		margin:10px auto;
		background-color:#CFF;
	}
</style>
</head>
<body>	
	<div id="logo">
		<a href="index.jsp">Computer Engineering Service</a>
	</div>
	
	<div id="login-box">
	<jsp:include page="loginBox.jsp"/>
	</div>
	<div id="logout-box">
	<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>