<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session scope 예제 b.jsp</title>
</head>
<body>
 name이 scope에 이미 등록되어 있나? <%=pageContext.findAttribute("name") %><br/>
 addr이 scope에 이미 등록되어 있나? <%=pageContext.findAttribute("addr") %><br/>
 zipcode이 scope에 이미 등록되어 있나? <%=pageContext.findAttribute("zipcode") %><br/>
 tel이 scope에 이미 등록되어 있나? <%=pageContext.findAttribute("tel") %><br/>
 <%
  String tel="019-111-9999";
  String addr="삼성동";
  session.setAttribute("tel",tel);
  session.setAttribute("addr",addr);
 %>
 b.jsp에서 선언한 tel::<%=tel %><br/>
 b.jsp에서 선언한 addr::<%=addr %><br/>
 
 session scope에 등록된 attribute name::<%=session.getAttribute("name") %><br/>
 session scope에 등록된 attribute addr::<%=session.getAttribute("addr") %><br/>
 session scope에 등록된 attribute zipcode::<%=session.getAttribute("zipcode") %><br/>
 session scope에 등록된 attribute tel::<%=session.getAttribute("tel") %><br/>
 
 <input type="button" value="d.jsp" onclick="location='d.jsp'">
 <%--<jsp:forward page="d.jsp"></jsp:forward>--%>
</body>
</html>