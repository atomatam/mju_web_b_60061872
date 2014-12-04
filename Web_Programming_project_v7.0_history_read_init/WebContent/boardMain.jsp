<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</style>
</head>
<body>
	<div id="header">
	<jsp:include page="header.jsp"/>
	</div>
	<div id="content">
		
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
					<pre>${requestScope.writing_history }</pre>
					</div>
				</li>
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
			<h1>Welcome to Wiki COMGONG!!</h1>
			<h3>원하시는 정보를 검색창에 검색해 주세요~!!</h3>
		</c:otherwise>
	</c:choose>
	</div>
	
	<c:choose>
	<c:when test="${sessionScope.isLogin=='true'}">
	<div id="newWriting">
		<form name="form2" method="get" action="PageServlet">
			<input type="submit" value="새글쓰기">
			<input type="hidden" name="op" value="newWriting">
		</form>
	</div>
	</c:when>
	</c:choose>

	</div>
	<div id="footer">
	<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>