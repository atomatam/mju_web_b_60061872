<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>History Page</title>
</head>
<body>
	<div id="title">
	<h1>title : ${requestScope.titlea }</h1>
	</div>
	<div id="head">
	<h2>date & writer: ${requestScope.datea } & ${requestScope.writera }</h2>
	</div>
	<div id="content">
	<h3>content : ${requestScope.contenta }</h3>
	</div>
	<input type="button" value="확인" Onclick="javascript:history.back(-1)"/>
</body>
</html>