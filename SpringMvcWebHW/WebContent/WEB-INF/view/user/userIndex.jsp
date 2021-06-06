<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User首頁</title>
</head>
<body>
<div align='center'>
  <h2>User首頁</h2>
  <hr>
</div>
<br/>
<div style="text-align: center;">
<div style="display: inline-block; text-align: left">
  <a href="<c:url value='/user/login.controller' />" >登入</a><br>
  <a href="<c:url value='/user/signup.controller' />" >註冊</a><br>
  <a href="<c:url value='/user/showAllUser.controller' />" >查看全部會員資料</a><br>
  <a href="<c:url value='/user/updateUserinfo.controller' />" >修改會員資料</a><br>
</div>
</div>
</body>
</html>