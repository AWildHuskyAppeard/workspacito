<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Question Index</title>
</head>
<body>
<div align='center'>
<h2>測驗系統 - 後台</h2>
<hr>
</div>
<br>
<div style="text-align: center;">
<div style="display: inline-block; text-align: left">
	
<a href="<c:url value='/insertQuestionForm' />" >1. 新增試題資料</a><br>
<a href="<c:url value='/showAllQuestionsAjax' />" >2. 查詢、修改與刪除題庫資料(Ajax)</a><br>

</div>
</div>
</body>
</html>