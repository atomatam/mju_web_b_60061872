<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<style>
		
		body {
			background:#FFF;
			width:1000px;
			height:20px;
			margin:0px auto;
			padding:5px;
			border-bottom: 5px solid;
		}
		
		#login-box {
			float:right;
		}
		
		#logo {
			float:left;
		}
		
	</style>
</head>
<body>
	<div id="logo">
		Computer Engineering Service
	</div>
	
	<c:choose>
	<c:when test="${sessionScope.isLogin!='true'}">
		<div id="login-box">
			<jsp:include page="loginBox.jsp"/>
		</div>
	</c:when>
	<c:otherwise>
	<div id="login-box">
		<c:out value="${sessionScope.user_nickName} 회원 님 "/>
	</div>
	<div id="logOut">
		<form name="logout" method="get" action="PageServlet">
			<input type="submit" value="로그아웃">
			<input type="hidden" name="op" value="logout"> 
		</form>	
	</div>
	</c:otherwise>
	</c:choose>
</body>
</html>