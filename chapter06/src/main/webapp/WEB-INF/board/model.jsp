<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%
	String data2 = (String)request.getAttribute("data");
%>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Model</h1>
	<h3>${data}</h3>
	<h3><%= data2 %></h3>
</body>
</html>