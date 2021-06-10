<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增文章</title>
<script>
var hasError = false;
window.onload = function(){
	var sendData = document.getElementById("sendData");
	sendData.onclick = function(){
		//抓欄位資料
		var c_ID = document.getElementById("c_ID").value;
		var c_Date = document.getElementById("c_Date").value;
		var c_Class = document.getElementById("c_Class").value;
		var c_Title = document.getElementById("c_Title").value;
		var c_Conts = document.getElementById("c_Conts").value;
		var u_ID = document.getElementById("u_ID").value;
		var span0 = document.getElementById('result0c');
		var span1 = document.getElementById('result1c');
		var span2 = document.getElementById('result2c');
		var span3 = document.getElementById('result3c');
		var span4 = document.getElementById('result4c');
		var span5 = document.getElementById('result5c');

		if(!c_ID){
			setErrorFor(span0, "請輸入文章編號");
		} else{
			span0.innerHTML = "";
		}
		if(!c_Date){
			setErrorFor(span1, "請輸入日期");
		} else{
			span1.innerHTML = "";
		}
		if(!c_Class){
			setErrorFor(span2, "請輸入類別")
		} else{
			span2.innerHTML = "";
		}
		if(!c_Title){
			setErrorFor(span3, "請輸入標題");
		} else{
			span3.innerHTML = "";
		}
		if(!c_Conts){
			setErrorFor(span4, "請輸入內容");
		} else{
			span4.innerHTML = "";
		}
		if(!u_ID){
			setErrorFor(span5, "請輸入帳號");
		} else{
			span5.innerHTML = "";
		}
		
		if (hasError){
			return false;
		}
		var xhr1 = new XMLHttpRequest();
		xhr1.open("POST", "<c:url value='/insertChat' />");
		var jsonInsertData = {
			"c_ID" : c_ID,
			"c_Date" : c_Date,
			"c_Class" : c_Class,
			"c_Title" : c_Title,
			"c_Conts" : c_Conts,
			"u_ID" : u_ID
		}
		xhr1.setRequestHeader("Content-Type", "application/json");
		xhr1.send(JSON.stringify(jsonInsertData));
		xhr1.onreadystatechange = function() {
			if (xhr1.readyState == 4 && xhr1.status == 200){
				result = JSON.parse(xhr1.responseText);
				//判斷回傳
				if(result.fail){
					spanResult.innerHTML = "<font color='red' >" + result.fail + "</font>";
				}else if(result.success){
					alert(result.success + "! 為您導回上一頁...");
					top.location='<c:url value='/chatIndex' />';
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
<div style="text-align: center;">
	<h3>新增文章</h3>
	<span id='resultMsg' style="height: 18px; font-weight: bold;"></span>
	<hr>
<div style="display: inline-block; text-align: left">
  <form>
  <table style="line-height:20px;">	
    <tr>
      <td>文章編號:&nbsp;&nbsp;<span id='result0c'></span><br/><input type="text" id="c_ID" name="c_ID"/></td>
    </tr>
    <tr>
      <td>日期:&nbsp;&nbsp;<span id='result1c'></span><br/><input type="text" id="c_Date" name="c_Date"/></td>
    </tr>
    <tr>
      <td>類別:&nbsp;&nbsp;<span id='result2c'></span><br/><input type="text" id="c_Class" name="c_Class"/></td>
    </tr>
    <tr>
      <td>標題:&nbsp;&nbsp;<span id='result3c'></span><br/><input type="text" id="c_Title" name="c_Title"/></td>
    </tr>
    <tr>
      <td>內容:&nbsp;&nbsp;<span id='result4c'></span><br/><input type="text" id="c_Conts" name="c_Conts"/></td>
    </tr>
    <tr>
      <td>帳號:&nbsp;&nbsp;<span id='result5c'></span><br/><input type="text" id="u_ID" name="u_ID"/></td>
    </tr>
  </table>
  </form>
</div>
</div>
<p/>
<div align='center'>
	<hr>
	<a href="<c:url value='/chatIndex' />">上一頁</a>
</div>
</body>
</html>