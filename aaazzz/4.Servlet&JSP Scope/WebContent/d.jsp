<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session scope 예제 d.jsp</title>
</head>
<body>
 name이 scope에 이미 등록되어 있나? <%=pageContext.findAttribute("name") %><br/>
 addr이 scope에 이미 등록되어 있나? <%=pageContext.findAttribute("addr") %><br/>
 zipcode이 scope에 이미 등록되어 있나? <%=pageContext.findAttribute("zipcode") %><br/>
 tel이 scope에 이미 등록되어 있나? <%=pageContext.findAttribute("tel") %><br/>
 <%
  String addr="서소문동";
  String name="베니";
  %>
  d.jsp에서 선언한 addr::<%=addr%><br/>
  d.jsp에서 선언한 name::<%=name %><br/>
  
   session scope에 등록된 attribute name::<%=session.getAttribute("name") %><br/>
   session scope에 등록된 attribute addr::<%=session.getAttribute("addr") %><br/>
   session scope에 등록된 attribute zipcode::<%=session.getAttribute("zipcode") %><br/>
   session scope에 등록된 attribute tel::<%=session.getAttribute("tel") %><br/>
</body>
</html>