<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>輸入商品資料</title>
</head>
<body>
	<form
		action="${pageContext.servletContext.contextPath }/updateproduct/${product.p_ID }"
		method="post" enctype="multipart/form-data">
		<%-- 	<input type="hidden" name="id" value="${product.p_ID }"/> --%>
		<!-- 	<input type="hidden" name="_method" value="PUT"/> -->

		<div align='center'>
			<h3>輸入商品需要更改的資料</h3>
			<hr>
			<div id='resultMsg' style="height: 18px; font-weight: bold;"></div>
			<br>
			<fieldset style='display: inline-block; width: 820px;'>
				<legend>填寫下列資料</legend>
				<table border='1'>
					<tr height='60'>
						<td width='400'>&nbsp;商品編號: <input type="text" name="p_ID"
							id='p_ID' value="${product.p_ID }" disabled="disabled"><br>
						</td>
					<tr height='60'>
						<td width='400'>&nbsp;導師: <input type="text" name="u_ID"
							id='u_ID' value="${product.u_ID}"><br>
						</td>
					</tr>
					<tr height='60'>
						<td width='400'>&nbsp;商品名稱: <input type="text" name="p_Name"
							id='p_Name' value="${product.p_Name }"><br>
						</td>
					</tr>
					<tr height='60'>
						<td width='400'>&nbsp;商品類別:<select name="p_Class"
							id="p_Class">
								<option>請選擇類別</option>
								<option value="${product.p_Class}"
									selected="${product.p_Class}=='英文'?'selected':''">英文</option>
								<option value="${product.p_Class}"
									selected="${product.p_Class}=='日文'?'selected':''">日文</option>
						</select><br>
						</td>
					</tr>
					<tr height='60'>
						<td width='400'>&nbsp;商品價錢: <input type="text" name="p_Price"
							id='p_Price' value="${product.p_Price }"><br>
						</td>
					</tr>
					<tr height='60'>
						<td width='400'>&nbsp;商品描述:<textarea rows="10" cols="100"
								name="p_DESC" id="p_DESC"></textarea>
						</td>
					</tr>
					<tr height='60'>
						<td width='400'>&nbsp;商品圖片上傳: <input type="file" name="p_Img"
							id="p_Img" value="${product.p_Img }"><br>
						</td>
					</tr>
					<tr height='60'>
						<td width='400'>&nbsp;商品影片上傳: <input type="file"
							name="p_Video" id='p_Video' value="${product.p_Video }"><br>
						</td>
					</tr>
					<tr height='50'>
						<td colspan='3' align='center'><input type="submit"
							value="送出"></td>
					</tr>
				</table>
			</fieldset>
		</div>
	</form>
	
	<p>
	<div align='center'>
		<hr>
		<a href="<c:url value='/products'/>">回上一頁</a>
	</div>






</body>
<script type="text/javascript">
	window.onload = function() {
		var p_DESC = "${product.p_DESC}";
		document.getElementById('p_DESC').innerHTML = p_DESC;
	}
</script>
</html>