<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>History Page</title>
<style>
body {
	margin-bottom: 40px;
	font-family: 'Lato', 'Sans-Serief';
	width:800px;
	}
</style>
</head>
<body align="center">
	<div id="title">
	<h1>title : ${requestScope.title }</h1>
	</div>
	<div id="head">
	<h2>date & writer: ${requestScope.date } & ${requestScope.writer }</h2>
	</div>
	<FIELDSET>
	<legend>Content</legend>
		<div id="content" style="line-height:1.7em;">
		<table>
			${requestScope.content }
		</table>
		</div>
		</FIELDSET>
	<input type="button" value="확인" Onclick="javascript:history.back(-1)"/>
</body>
</html>