<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全部會員資料</title>
<script>
window.onload = function() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/showAllUser.controller' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "<table border='1'>";
			content += "<tr><th>刪除</th><th width='100'>帳號</th><th width='100'>密碼</th>"
					+ "<th width='80'>姓</th><th width='80'>名</th><th width='100'>生日</th><th width='100'>電子郵件</th>"
					+ "<th width='100'>電話</th><th width='50'>性別</th><th width='150'>地址</th><th width='50'>圖片</th></tr>";
			var users = JSON.parse(xhr.responseText);
			for(var i=0; i < users.length; i++){
				//待寫刪除
				var deleteUser = "<c:url value='/xxxxxxxxxx/' />";
			    content += 	"<tr><td align='center'><a href='" + deleteUser + users[i].u_id + "'>" + 
    			"<img width='25' height='25' src='<c:url value='/images/d_user.svg' />' ></a>" + 
			    			"<td align='center'>" + users[i].u_id + "</td>" + 
			                "<td align='center'>" + users[i].u_psw + "</td>" +
		        	       	"<td align='center'>" + users[i].u_lastname + "</td>" +
		            	   	"<td align='center'>" + users[i].u_firstname + "</td>" +
							"<td align='center'>" + users[i].u_birthday + "</td>" +
							"<td align='center'>" + users[i].u_email + "</td>" +
							"<td align='center'>" + users[i].u_tel + "</td>" +
							"<td align='center'>" + users[i].u_gender + "</td>" +
							"<td align='center'>" + users[i].u_address + "</td>" +
							"<td align='center'>" + users[i].u_img + "</td>" +
		               		"</tr>";
			}
			content += "</table>";
			var showUser = document.getElementById("showUser");
			showUser.innerHTML = content;
		}
	}
		
}
</script>
</head>
<body>
<div align='center'>
	<h2>會員資料</h2>
	<hr>
	<div class='center'  id='showUser'></div>
	</div>
	
<p/>
<div align='center'>
<hr>
<a href="<c:url value='/gotoUserIndex.controller' />">上一頁</a>
</div>
</body>
</html>