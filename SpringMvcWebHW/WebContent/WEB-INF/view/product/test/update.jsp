<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>輸入商品資料</title>
<script type="text/javascript">

var bean = "${productInfo}"
var p_id = "${productInfo.p_ID}";
var hasError = false;
	window.onload = function() {
		var divResult = document.getElementById('resultMsg');
		var id = document.getElementById('p_ID');
		var name = document.getElementById('p_Name');
		var price = document.getElementById('p_Price');
		var clas = document.getElementById('p_Class');
		var desc = document.getElementById('p_DESC');
		var img = document.getElementById('p_Img');
		var video = document.getElementById('p_Video');
		var uid = document.getElementById('u_ID');
		var date = document.getElementById('p_createDate');

		var updateData = document.getElementById("updateData");
		var xhr = new XMLHttpRequest();

		xhr.open("GET", "<c:url value='/product/" + u_id + "' />", true);
		xhr.send();
		var message = "";
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var productBean = JSON.parse(xhr.responseText);
				id.innerHTML = productInfo.p_ID;
//	 			psw.value = userBean.u_psw;
				p_Name.value = productBean.p_Name;
				p_Price.value = productBean.p_Price;
				birthday.value = productBean.p_Class;
				p_Class.value = productBean.p_DESC;
				p_Img.value = productBean.p_Img;
				p_Video.value = productBean.p_Video;
				u_ID.value = productBean.u_ID;
				p_createDate.value = productBean.p_createDate;
//	 			img.value = userBean.u_img;
		}
		}

		updateData.onclick = function(){
			var divResult = document.getElementById('resultMsg');
			var p_Name = document.getElementById("p_Name").innerHTML;
//	 		var pswV = document.getElementById("u_psw").value;
//	 		var cfmpswV = document.getElementById("cfm_psw").value;
			var p_Price = document.getElementById("p_Price").value; //必填
			var p_Class = document.getElementById("p_Class").value; //必填
			var p_DESC = document.getElementById("p_DESC").value;
// 			var p_Img = document.getElementById("p_Img").value; //必填
// 			var p_Video = document.getElementById("p_Video").value;
			var u_ID = document.getElementById("u_ID").value;
			var p_createDate = document.getElementById("p_createDate").value;
//		 	var imgV = document.getElementById("u_img").value;

			var p_Video = "${productInfo.p_Video}"; //從sessionAttribute抓u_psw的值
			var p_Img = "${productInfo.p_Img}"; //從sessionAttribute抓u_img的值

			var span0 = document.getElementById("result0c");
			var span1 = document.getElementById("result1c");
			var span2 = document.getElementById("result2c");
			if(!p_Name){
				setErrorFor(span0, "姓氏為必填欄位!");
			} else{
				span0.innerHTML = "";
			}
			if(!p_Class){
				setErrorFor(span1, "名字為必填欄位!");
			} else{
				span1.innerHTML = "";
			}
			if(!p_DESC){
				setErrorFor(span2, "電子郵件為必填欄位!");
			} else{
//	 			hasError = false;
				span2.innerHTML = "";
			}
			if(hasError){
				return false;
			}
			var xhr1 = new XMLHttpRequest();
			xhr1.open("PUT", "<c:url value='/product/' />" + p_id, true);
			var jsonUpdateData = {
					"u_id" : u_ID,
					"p_createDate" : p_createDate,
					"p_DESC" : p_DESC,
					"p_Class" : p_Class,
					"p_Price" : p_Price,
					"p_Name" : p_Name,
					"p_id" : p_id,
					"p_Video": null;
//		 			"p_Img" : null
			xhr1.setRequestHeader("Content-Type", "application/json");
   		xhr1.send(JSON.stringify(jsonUpdateData));

   		xhr1.onreadystatechange = function() {
   			if (xhr1.readyState == 4 && (xhr1.status == 200 || xhr1.status == 201) ) {
   				result = JSON.parse(xhr1.responseText);
   				if (result.fail) {
   			 		divResult.innerHTML = "<font color='red' >" + result.fail + "</font>";
   				} else if (result.success) {
   					divResult.innerHTML = "<font color='GREEN'>"
					   					+ result.success + "</font>";
					   					  span0.innerHTML = "";	
					   					  span1.innerHTML = "";
					   					  span2.innerHTML = "";
   	   			} 
   	   		}
				}
			}	
			function setErrorFor(input, message){
				input.innerHTML = "<font color='red' size='-2'>" + message + "</font>";
				hasError = true;
			}


			
		}
</script>
</head>
<body>

	<div align='center'>
		<h3>輸入商品需要更改的資料</h3>
		<hr>
		<div id='resultMsg' style="height: 18px; font-weight: bold;"></div>
		<br>
		<fieldset style='display: inline-block; width: 820px;'>
			<legend>填寫下列資料</legend>
			<table border='1'>
				<tr height='60'>
					<td width='400'>&nbsp;導師: <input type="text" name="u_ID" id='u_ID' value="u_ID"><br>
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品名稱: <input type="text" name="p_Name"
						id='p_Name' value="p_Name"><br>
					</td>
					<td>&nbsp;<span id='result1c'></span></td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品類別:<select name="p_Class">
							<option>請選擇類別</option>
							<option value="p_Class">英文</option>
							<option value="p_Class">日文</option>
					</select><br>
					</td>
					<td>&nbsp;<span id='result0c'></span></td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品價錢: <input type="text" name="p_Price"
						id='p_Price' value="p_Price"><br>
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品描述:<textarea rows="10" cols="100"
							name="p_DESC" id="p_DESC"></textarea>
					</td>
					<td>&nbsp;<span id='result1c'></span></td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品圖片上傳: <input type="file" name="p_Img"
						id="p_Img" value="p_Img"><br>
					</td>
				</tr>
				<tr height='60'>
					<td width='400'>&nbsp;商品影片上傳: <input type="file"
						name="p_Video" id='p_Video' value="p_Video"><br>
					</td>
				</tr>
				<tr height='50'>
					<td colspan='3' align='center'><button id="updateData">更新</button></td>
				</tr>
			</table>
		</fieldset>
	</div>
	<hr>
	<p>
		<a href="<c:url value='/index'  />">回前頁</a>
	<hr>






</body>
</html>