<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- <link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" /> --%>
<meta charset="UTF-8">
<title>會員資料</title>
<%-- <script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />" ></script> --%>


<script>
window.onload = function() { 
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/Events' />", true);  
	xhr.send(); //用GET 送 value='/Events'
	xhr.onreadystatechange = function() {
		//伺服器回傳之後 觸發 
		if (xhr.readyState == 4 && xhr.status == 200) {
			
			var content = "<table border='1'>";
			content += "<tr><th width='80'>編輯</th><th width='100'>活動ID</th><th width='180'>活動名稱</th><th width='90'>活動日期</th><th width='140'>活動代幣</th></tr>";
// 			alert(content);
			var members = JSON.parse(xhr.responseText);
			for(var i=0; i < members.length; i++){
				tmp = "<c:url value='/EventsEdit/' />";

				//tmp  用GET 送  c:url value='/EventsEdit/'   然後 把上面 "GET", "<c:url value='/Events' />"  從資料庫物件呼叫 aid出來  他的/?
				
			    content += 	"<tr><td width='70'><a href='" + tmp + members[i].aid + "'>" + 
			    			"<img width='36' height='36' src='<c:url value='/images/user/d_user.svg' />' ></a>" + 
			                "<td align='center'>" + members[i].uid + "</td>" +
		        	       	"<td>" + members[i].aname + "</td>" +
		            	   	"<td align='right'>" + members[i].adate + "&nbsp;</td>" +
							"<td>" + members[i].acoin + "</td>" +
		               		"</tr>";
// 			    alert(content);
			}
			content += "</table>";
			var divs = document.getElementById("somedivS");
			divs.innerHTML = content;
		}
	}
		
}
</script>
</head>
<body>
<div class='center' >
	<h2>會員資料</h2>
	<hr>
	<div class='center'  id='somedivS'></div>
	</div>
</body>
	
<p/>
<div align='center'>
<a href="<c:url value='/Event' />">回前頁</a>
</div>
</body>
</html>
