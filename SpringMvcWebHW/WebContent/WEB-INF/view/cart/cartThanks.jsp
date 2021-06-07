<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",-1);
response.setHeader("refresh", "2; ../index_test.html");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Thank Page</title>
</head>
<body>
<h1>您的本次購買已被受理，感謝您！</h1>
<h1>自動導回首頁中...</h1>

</body>
</html>