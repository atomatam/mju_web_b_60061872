<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Page</title>
<style>
	ol {
		list-style:none;
	}
	li {
		padding:5px;
	}
	#content { 
		padding:20px 0 0 0;
	}
	
	#contentArea {
		position:relative;
		margin:10px;
		border:1px solid;
	}
	
	#contentBox{
	width:500px;
	border:1px solid;
	}
	
	#header{
		position:relative;
	}
	
	#footer {
		position:relative;
	}
	
	#searchBar {
		position:relative;
		padding:15px;
	}
	
	#newWriting {
		position:relative;
	}
	
	#login-box {
		float:right;
	}
	
	#logOut {
		position:relative;
	}
	
	body {
		background-color:#BCBCBC;
	}
</style>

</head>
<body>
	<div id="logo">
		Computer Engineering Service
	</div>
	
	<div id="login-box">
		<c:out value="${sessionScope.user_nickName} Page"/>
	</div>
	
	<div id="logOut">
		<form name="logout" method="get" action="PageServlet">
			<input type="submit" value="로그아웃">
			<input type="hidden" name="op" value="logout"> 
		</form>	
	</div>
		
	<div id="searchBar">
	<form name="form1" method="get" action="PageServlet">
		<input type="text" name="keyword">
		<input type="submit" value="검색">
		<input type="hidden" name="op" value="writingSearch">
	</form>
	</div>
	
	<div id="contentArea">
	<c:choose>
		<c:when test="${requestScope.isInitSearch == false }">
			<div id="elseModifybutton">
			<ol>
				<li>
					<div id="titleBox">
						Title : <c:out value="${requestScope.writing_title }"/>
					</div>
				</li>
				
				<li>
					<div id="dateAndWriterBox">
						Information(writer/date) - <c:out value="${requestScope.writing_writer} / ${requestScope.writing_date }"/>
					</div>
				</li>
				<li>
					<div id="contentBox">
						<pre>${requestScope.writing_content }</pre>
					</div>
				</li>
				
				<li>	
					<div id="historyBox">
					히스토리--
					<pre>${requestScope.writing_history }</pre>
					</div>
				</li>
				
				<li>
					<div id="imageBox">
					${requestScope.images }
					</div>
				<li>
			</ol>
			</div>
			<c:choose>
				<c:when test="${sessionScope.isLogin=='true'}">
					<div id="modifyButton">
						<form name="form" method="post" action="PageServlet">
							<input type="submit" value="정보 수정">
							<input type="hidden" name="op" value="modify">
						</form>
					</div>
				</c:when>
			</c:choose>
		</c:when>

		<c:otherwise>
			<h1>Manager Page입니다.</h1>
		</c:otherwise>
	</c:choose>
	</div>

	<div id="footer">
	<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>