<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
</head>
<body>
<div align='center'>
<h2>Chat首頁</h2>
<hr>
</div>
<br>
<div style="text-align: center;">
<div style="display: inline-block; text-align: center">
<a href="<c:url value='/goSelectAllChat' />" >查詢所有文章</a><br>
<a href="<c:url value='/goInsertChat' />" >新增文章</a><br>
<a href="<c:url value='/goUpdateChat' />" >更新文章</a><br>
</div>
  <hr>
  <div align='center'>
  <a href="<c:url value='/' />">首頁</a>
  </div>
</div>
</body>
</html>