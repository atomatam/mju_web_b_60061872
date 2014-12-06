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
</head>
<body>
	<h1>정보 글 쓰기</h1>
	<h2>${sessionScope.user_nickName }님의 정보에 감사드립니다.</h2>
	<table>
  <tr>
   <td>
    <table width="100%" cellpadding="1" cellspacing="0" border="1">
     <tr style="background:url('img/table_mid.gif') repeat-x; text-align:center;">
      <td width="5"><img src="img/table_left.gif" width="5" height="30" /></td>
      <td>글쓰기</td>
      <td width="5"><img src="img/table_right.gif" width="5" height="30" /></td>
     </tr>
    </table>
    
    <form name="form1" method="post" action="PageServlet" onsubmit="return checkIt()">
	   	<table>
	     <tr>
	      <td>&nbsp;</td>
	      <td align="center">제목</td>
	      <td><input type="text" name="writing_title" size="50" maxlength="100"></td>
	      <td>&nbsp;</td>
	     </tr>
	     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
	
	    <tr>
	      <td>&nbsp;</td>
	      <td align="center">내용</td>
	      <td><textarea name="writing_content" cols="50" rows="13"></textarea></td>
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
   </td>
  </tr>
 </table>	
</body>
</html>