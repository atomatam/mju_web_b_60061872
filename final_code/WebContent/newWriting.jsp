<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 쓰기</title>
<script>
function checkIt() {
	var userInput = eval("document.form1");
	if(!userInput.writing_title.value) {
		alert("제목을 입력해 주세요");
		return false;
	}	
	if(!userInput.writing_content.value) {
		alert("글을 입력해 주세요.");
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
</head>
<body align="center">
	<h1>정보 글 쓰기</h1>
	<table  align="center">
  <tr>
   <td>
    <form name="form1" method="post" action="PageServlet" onsubmit="return checkIt()">
	   	<table>
	     <tr>
	      <td>&nbsp;</td>
	      <td>제목</td>
	      <td><input type="text" name="writing_title" size="30" maxlength="19"></td>
	      <td>&nbsp;</td>
	     </tr>
	     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
	
	    <tr>
	      <td>&nbsp;</td>
	      <td align="center">내용</td>
	      <td><textarea name="writing_content" cols="100" rows="25"></textarea></td>
	      <td>&nbsp;</td>
	     </tr>
	     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
	     <tr align="center">
	      <td>&nbsp;</td>
	      <td colspan="2">
	      	<input type="submit" value="등록">
	      	<input type="reset" value="취소">
	      <td>&nbsp;</td>
	     </tr>
	    </table>
	    <input type="hidden" name="op" value="newWriting">
	    <input type="hidden" name="writing_writer" value="${sessionScope.user_nickName }" >
    </form>
    
		<h2>${sessionScope.user_nickName }님의 정보에 감사드립니다.</h2>
   </td>
  </tr>
 </table>	
</body>
</html>