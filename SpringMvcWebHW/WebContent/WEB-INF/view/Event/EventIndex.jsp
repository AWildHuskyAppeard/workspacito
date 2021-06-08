<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />" >
<meta charset="UTF-8">
<title>Event</title>
</head>
<body>
<div align='center'>
<h2>Event首頁</h2>
<hr>
</div>
<br>
<div style="text-align: center;">
<div style="display: inline-block; text-align: left">
	

<a href="<c:url value='/insertEventForm' />" >新增活動</a><br>
<a href="<c:url value='/Events' />" >3. 檢視Server送回的JSON資料(生資料)</a><br>
<a href="<c:url value='/showAllEvent' />" >查詢、修改與刪除活動</a><br>
<a href="<c:url value='/gohome' />" >回大家首頁</a><br>
</div>
</div>
<hr>
<div class='center'>
<font color='red' size='-2'>DB:${sys.dbType}</font>
<hr>
</div>
</body>
</html>