<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>輸入商品資料</title>
</head>
<body>
	<form action="product" method="post" enctype="multipart/form-data">
	
	<div align='center'>
		<h3>輸入會員資料</h3>
		<hr>
		<div id='resultMsg' style="height: 18px; font-weight: bold;"></div>
		<br>
		<fieldset style='display: inline-block; width: 820px;'>
			<legend>填寫下列資料</legend>
			<table border='1'>
				<tr height='60'>
					<td width='400'>&nbsp;導師: <input type="text" name="u_ID"id='u_ID'><br>
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品名稱: <input type="text" name="p_Name" id='p_Name'><br>
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品類別:<select name="p_Class">
						<option>請選擇類別</option>
						<option>英文</option>
						<option>日文</option>
					</select><br>
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品價錢: <input type="text" name="p_Price"id='balance'><br>
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品描述:<textarea rows="10" cols="100" name="p_DESC" id="p_DESC"></textarea>
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品圖片上傳: <input type="file" name="p_Img" id="p_Img"><br>
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品影片上傳: <input type="file" name="p_Video"id='p_Video'><br>
					</td>
				</tr>
				<tr height='50'>
					<td colspan='3' align='center'><input type="submit" value="送出"></td>
				</tr>
			</table>
		</fieldset>
	</div>
	</form>
		<hr>
		<p>
			<a href="<c:url value='/index'  />">回前頁</a>
		<hr>






</body>
</html>