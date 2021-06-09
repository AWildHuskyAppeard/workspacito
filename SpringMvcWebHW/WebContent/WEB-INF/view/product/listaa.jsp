<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>展示商品資訊</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function() {
		$(".del").click(function() {
			if (confirm("確認刪除嗎")) {
				//submit 將所獲得的form元素提交
				$("form").attr("action", this.href).submit(); //$(this).attr("href")
				return false;
			}
			return false; //將超連結的默認行為取消
		});
	});
</script>
<body>
	<table>
		<tr>
			<th>p_ID</th>
			<th>p_Name</th>
			<th>p_Class</th>
			<th>p_Price</th>
			<th>p_DESC</th>
			<th>u_ID</th>
			<th>p_createDate</th>
			<th>p_Img</th>
			<th>p_Video</th>
			<th>OPTION(<a href="product">ADD</a>)
			</th>
		</tr>
		<c:forEach items="${products }" var="product">
			<tr>
				<td>${product.p_ID }</td>
				<td>${product.p_Name }</td>
				<td>${product.p_Class }</td>
				<td>${product.p_Price}</td>
				<td>${product.p_DESC}</td>
				<td>${product.u_ID}</td>
				<td>${product.p_createDate}</td>
				<td>${product.p_Img}</td>
				<td>${product.p_Video}</td>
				<td>
					<a href="product/${product.p_ID }">UPDATE</a>
					<a href="product/${product.p_ID }" class='del'>DELETE</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<form method='post'>
		<input type="hidden" name="_method" value="DELETE">
	</form>
</body>
</html>