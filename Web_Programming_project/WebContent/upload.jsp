<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>파일 업로드 폼</title>
</head>
 
<body>
 
<form name="fileForm" id="fileForm" method="POST" action="PageServlet" enctype="multipart/form-data">
    설명(20자 이내) : <input type="text" name="explain">
    <input type="file" name="uploadFile"> 
    <input type="submit" value="전송">
</form>
</body>
</html>