<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全部文章</title>
<script>
window.onload = function() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/selectAllChat' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "<table border='1'>";
			content += "<tr><th>刪除</th><th width='100'>帳號</th><th width='80'>類別</th><th width='220'>文章標題</th><th width='100'>發布時間</th></tr>";
			var users = JSON.parse(xhr.responseText);
			for(var i=0; i < users.length; i++){
				var deleteChat = "<c:url value='/deleteChat/' />";
				content += 	"<tr><td align='center'><a href='" + deleteChat + users[i].c_ID + "'>" +
				"<img width='36' height='36' src='<c:url value='/images/user/d_user.svg' />' ></a>" +
				"<td align='center'>" + users[i].c_ID + "</td>" + 
                "<td align='center'>" + users[i].c_Class + "</td>" +
    	       	"<td align='left'>" + users[i].c_Title + "</td>" +
        	   	"<td align='center'>" + users[i].c_Date + "</td>" +
           		"</tr>";
			}
			content += "</table>";
			var selectAll = document.getElementById("selectAll");
			selectAll.innerHTML = content;
		}
	}
}
</script>
</head>
<body>
<div class='center' >
	<h3>文章</h3>
	<hr>
	<div class='center'  id='selectAll'></div>
</div>
<p/>
<div align='center'>
	<hr>
	<a href="<c:url value='/chatIndex' />">上一頁</a>
</div>
</body>
</html>