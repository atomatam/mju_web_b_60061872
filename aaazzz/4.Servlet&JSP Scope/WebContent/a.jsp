<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session scope 예제 a.jsp</title>
</head>
<body>
 <% String name="밤비";
    String addr="양재동";
    String zipcode="123-123";
 %>
 name::<%=name %><br/>
 addr::<%=addr %><br/>
 zipcode::<%=zipcode %><br/>
 
 <% session.setAttribute("name",name);  
    session.setAttribute("addr",addr);
 %>
 <input type="button" value="c.jsp" onclick="location='c.jsp'">
 <%--<jsp:forward page="c.jsp"></jsp:forward>--%>
</body>
</html>