<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>題庫</title>
<%-- <script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />" ></script> --%>

<script>
window.onload = function() {
	//經由AJAX提出HTTP請求,使用XMLHttpRequest
	//or使用縫縫表單
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "<c:url value='/questions' />", true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var content = "<table border='1'>";
			content += "<tr><th width='50'>編輯</th><th width='80'>題目編號</th><th width='80'>題目類型</th><th width='200'>問題</th><th width='350'>回答選項</th><th width='80'>正解</th><th width='80'>課程分類</th></tr>";
			var questions = JSON.parse(xhr.responseText);
			for(var i=0; i < questions.length; i++){
				tmp = "<c:url value='/quesEdit/' />";
			    content += 	"<tr><td width='50' ><a href='" + tmp + questions[i].q_ID + "'>" + 
			    			"<img width='36' height='36' src='<c:url value='/images/question/q_edit.png' />' ></a>" + 
			                "<td align='center'>" + questions[i].q_ID + "</td>" +
		        	       	"<td align='center'>" + questions[i].q_Type + "</td>" +
		        	       	"<td align='center'>" + questions[i].q_Ques + "</td>" +
		        	       	"<td align='center'>" + questions[i].q_Selection + "</td>" +
		        	       	"<td align='center'>" + questions[i].q_Ans + "</td>" +
		        	       	"<td align='center'>" + questions[i].p_Class + "</td>" +		            	  
		               		"</tr>";
			}
			content += "</table>";
// 			?????
			var divs = document.getElementById("somedivS");
			divs.innerHTML = content;
		}
	}
		
}
</script>
</head>
<body>
<div class='center' >
	<h2>題庫</h2>
	<hr>
	<div class='center'  id='somedivS'></div>
	</div>
</body>
	
<p/>
<div align='center'>
<a href="<c:url value='/goQuesIndex' />">回前頁</a>
</div>
</body>
</html>
