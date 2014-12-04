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
	<c:when test="${sessionScope.isLogin== 'null' }">
		<div id="login-box">
			<form name="form1" method="post" action="login.jsp">
				아이디<input type="text" name="id">
				비밀번호<input type="password" name="pwd">
				<input type="submit" value="확인">
			</form>
		</div>
	</c:when>
	<c:otherwise>
		<c:out value="${sessionScope.user_nickName} 회원 님 "/>
		<c:out value="${sessionScope.isLogin }"/>
	</c:otherwise>
	</c:choose>
</body>
</html>