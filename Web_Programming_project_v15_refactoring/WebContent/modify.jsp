<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 쓰기</title>
</head>
<script>
function checkIt() {
	var userInput = eval("document.userInput");
	if(!userInput.modify_content.value) {
		alert("글을 입력해 주세요");
		return false;
	}
	
}
</script>
<style>
body {
	margin-bottom: 40px;
	font-family: 'Lato', 'Sans-Serief';
}
</style>
<body align="center">
	<h1>정보 글 업데이트</h1>
	<h2>${sessionScope.user_nickName }님의 정보에 감사드립니다.</h2>
	<table align="center">
  <tr>
   <td>
    <table width="100%" cellpadding="1" cellspacing="0" border="1">
     <tr style="text-align:center;">
      <td>정보 추가</td>
     </tr>
    </table>
    
    <form name="userInput" method="post" action="PageServlet" onsubmit="return checkIt()">
	   	<table>
	     <tr>
	      <td>&nbsp;</td>
	      <td align="center">제목</td>
	      <td>${requestScope.writing_title }</td>
	      <td>&nbsp;</td>
	     </tr>
	     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
	
	    <tr>
	      <td>&nbsp;</td>
	      <td align="center">내용</td>
	      <td><textarea name="modify_content" cols="100" rows="25">${requestScope.writing_content }</textarea></td>
	      <td>&nbsp;</td>
	     </tr>
	     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
	     <tr align="center">
	      <td>&nbsp;</td>
	      <td colspan="2">
	      	<input type="submit" value="정보 수정">
	      <td>&nbsp;</td>
	     </tr>
	    </table>
	    <input type="hidden" name="op" value="isModify">
	    <input type="hidden" name="writing_writer" value="${sessionScope.user_nickName }" >
    </form>
    <form name="cancel" method="get" action="PageServlet">
	   	<input type="submit" value="취소">
	   	<input type="hidden" name="keyword" value="${requestScope.writing_title }">
	   	<input type="hidden" name="op" value="writingSearch">
	  </form>
   </td>
  </tr>
 </table>	
</body>
</html>