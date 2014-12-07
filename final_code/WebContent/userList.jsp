<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User list</title>
<style>
body {
	margin-bottom: 40px;
	font-family: 'Lato', 'Sans-Serief';
}
</style>
</head>
<body align="center">	
	<div id="go back">
		<form name="form" method="post" action="ManagerServlet">
			<input type="submit" value="확인" width="100">
		</form>
	</div>
	<h1>모든 유저 리스트 입니다.</h1>
	<div id="userList">
		<pre>${requestScope.userList }</pre>
	</div>
	
	<hr>
	<hr>
	<h1>차단 당한 회원 리스트 입니다.</h1>
	<div id="banUserList">
		<pre>${requestScope.banedUserList }</pre>
	</div>
</body>
</html>