<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>暫用の首頁</title>
</head>
<body>
<div align='center'>
  <h2>首頁</h2>
  <hr>
</div>
<br/>
<div style="text-align: center;">
<div style="display: inline-block; text-align: left">
  <a href="<c:url value='/gotoUserIndex.controller' />" >會員</a><br>
  <a href="<c:url value='/Event' />" >活動</a><br>
  <a href="<c:url value='/products' />" >課程</a><br>
  <a href="<c:url value='/gotoQuesIndex' />" >題庫</a><br>
  <a href="<c:url value='/chatIndex' />" >討論區</a><br>
  <a href="<c:url value='/cart.controller/cartIndex' />" >購物車</a><br>
</div>
</div>
</body>
</html>