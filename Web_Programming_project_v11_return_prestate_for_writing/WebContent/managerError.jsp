<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
	<c:out value="${errorMsg }"/>
	<form name="form1" method="get" action="ManagerServlet">
		<input type="hidden" name="op" value="writingSearch">
		<input type="submit" value="${requestScope.errorMsg }">
		<input type="hidden" name="keyword" value="${requestScope.keyword }">
	</form>
</body>
</html>
