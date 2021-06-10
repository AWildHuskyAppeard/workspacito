<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>商品資料</title>
<script>

	window.onload = function(){
		var xhr = new XMLHttpRequest();
		xhr.open("GET","<c:url value='/products' />",true) 
		xhr.send();
		xhr.onreadystatechange = function() {

			if(xhr.readyState == 4 && xhr.status == 200){
				var content = "<table border='1'>";
				content += "<tr><th>p_ID</th><th>p_Name</th><th>p_Class</th><th>p_Price</th><th>p_DESC</th><th>p_createDate</th><th>u_ID</th><th>p_Img</th><th>p_Video</th></tr>"
				var products = JSON.parse(xhr.responseText);
				for(var i=0; i< products.length;i++){
					tmp = "<c:url value='/productsEdit/' />";
					content += "<tr>" + 
	                "<td align='center'>" + products[i].p_ID + "</td>" +
        	       	"<td align='center'>" + products[i].p_Name + "</td>" +
            	   	"<td align='center'>" + products[i].p_Price + "</td>" +
					"<td align='center'>" + products[i].p_Class + "</td>" +
					"<td align='center'>" + products[i].p_DESC + "</td>" +
					"<td align='center'>" + products[i].u_ID + "</td>" +
					"<td align='center'>" + products[i].p_createDate + "</td>" +
					"<td align='center'>" + products[i].p_Img + "</td>" +
					"<td align='center'>" + products[i].p_Video + "</td>" +
               		"</tr>";
					}
				content += "</table>";
				var divs = document.getElementById("somedivS");
				divs.innerHTML = content;
				
				}


			};



		};
	








</script>
</head>
<body>

	<div class='center'>
		<h2>商品資料</h2>
		<hr>
		<div class='center' id='somedivS'></div>
	</div>


	<p />
	<div align="center">
		<a href="<c:url value='/index/'/>">回前頁</a>
</div>
</body>
</html>