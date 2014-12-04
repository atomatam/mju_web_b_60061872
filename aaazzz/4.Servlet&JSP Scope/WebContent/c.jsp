<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session scope 예제 c.jsp</title>
</head>
<body>
 name이 scope에 이미 등록되어 있나?<%=pageContext.findAttribute("name") %><br/>
 addr이 scope에 이미 등록되어 있나?<%=pageContext.findAttribute("addr") %><br/>
 zipcode가 scope에 이미 등록되어 있나?<%=pageContext.findAttribute("zipcode") %><br/>
 <%
   String addr=(String)session.getAttribute("addr");
   String name=(String)session.getAttribute("name")+addr;
   String tel="010-0010-0000";
   session.setAttribute("name",name);
 %>
 c.jsp에서 선언한 addr::<%=addr %><br/>
 c.jsp에서 선언한 name::<%=name %><br/>
 c.jsp에서 선언한 tel::<%=tel %><br/>
 
 session scope에 등록된 attribute name::<%=session.getAttribute("name") %><br/>
 session scope에 등록된 attribute addr::<%=session.getAttribute("addr") %><br/>
 session scope에 등록된 attribute zipcode::<%=session.getAttribute("zipcode") %><br/>
 session scope에 등록된 attribute tel::<%=session.getAttribute("tel") %><br/>
 
 <input type="button" value="b.jsp" onclick="location='b.jsp'">
 <%--<jsp:forward page="b.jsp"></jsp:forward> --%>
</body>
</html>