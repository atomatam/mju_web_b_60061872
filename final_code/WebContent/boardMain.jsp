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

	#contentBox{
	width:800px;
	}
	
	#searchBar {
		padding:15px 0 0 0;
	}
	
	#newWriting{
		float:right;
	}
	body {
		font-family: 'Lato', 'Sans-Serief';
		width:1000px;
		margin-bottom: 40px;
		background-color:#fafad2;
	}
</style>
<script>
function checkIt() {
	var userinput = eval("document.searchBar");
	if(!userinput.keyword.value) {
		alert("찾을 키워드를 입력해 주세요.");
		return false;
	}
	
}

</script>
</head>
<body>
	<div id="header">
	<jsp:include page="header.jsp"/>
	</div>
	<div id="content">
		
	<div id="searchBar">
	<form name="searchBar" method="get" action="PageServlet" onsubmit="return checkIt()">
		<input type="text" name="keyword">
		<input type="submit" value="검색">
		<input type="hidden" name="op" value="writingSearch">
	</form>
	</div>
		
	<c:choose>
	<c:when test="${sessionScope.isLogin=='true'}">
	<div id="newWriting">
		<form name="content_input" method="get" action="PageServlet">
			<input type="submit" value="새글쓰기">
			<input type="hidden" name="op" value="newWriting">
		</form>
	</div>
	</c:when>
	</c:choose>
	
	<div id="contentArea">
	<c:choose>
		<c:when test="${requestScope.isInitSearch == false }">
			<div id="elseModifybutton">
			<ol>
				<li>
					<div id="titleBox">
						<h2>Title : <c:out value="${requestScope.writing_title }"/></h2>
					<hr>
					</div>
				</li>
				
				<li>
					<div id="contentBox" style="line-height:1.7em;">
					<table>
						${requestScope.writing_content }
					</table>
					</div>
				</li>
						
				<li>
					<div id="imageBox">
					${requestScope.images }
					</div>
				</li>
				<li>
					<hr>
				</li>
				
				<li>
					<div id="dateAndWriterBox">
						Information(writer/date) - <c:out value="${requestScope.writing_writer} / ${requestScope.writing_date }"/>
					</div>
				</li>
				
				<li>	
					<div id="historyBox">
					히스토리--
					<pre>${requestScope.writing_history }</pre>
					</div>
				</li>
			</ol>
			</div>
			<c:choose>
				<c:when test="${sessionScope.isLogin=='true'}">
					<div>
						<jsp:include page="upload.jsp"/>
					</div>
					<div id="modifyButton">
						<form name="form" method="post" action="PageServlet">
							<input type="submit" value="정보 수정" style="width:150px;">
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

	</div>
	<div id="footer">
	<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>