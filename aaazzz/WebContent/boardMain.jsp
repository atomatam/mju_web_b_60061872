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
<script lang="javascript">
function checkIt(){
	var searchinput = eval("document.form1");
	if(!searchinput.keyword.value){
		alert("검색어를 입력하세요");
		document.addjoin.id.focus();
		exit;
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
	<form name="form1" method="post" action="PageServlet" onsubmit="return checkIt()">
		<input type="text" name="keyword">
		<input type="submit" value="검색">
		<input type="hidden" name="op" value="writingSearch">
	</form>
	</div>
	
	<div id="contentArea">
	<c:choose>
		<c:when test="${requestScope.isInitSearch == false }">
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

				<c:choose>
				<c:when test="${requestScope.modifyMode!=true }">
				<li>
					<div id="contentBox">
						${requestScope.writing_content }
					</div>
				</li>
				</c:when>
				
				<c:otherwise>
				<li>
					<div id="contentBox">
						${requestScope.writing_content }
						sdf dsf dsf dsf sdfsd
					</div>
				</li>
				</c:otherwise>
				</c:choose>
				
				<li>	
					<div id="historyBox">
						<c:out value="${requestScope.writing_history }"/>
					</div>
				</li>
				
				<li>
					<div id="modify-button">
						<form name="form3" method="post" action="PageServlet">
							<input type="submit" value="정보 추가">
							<input type="hidden" name="op" value="modify">
							<input type="hidden" name="content" value=>
						</form>
					</div>
				</li>
			</ol>
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