<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新文章</title>
<script>
var hasError = false;
window.onload = function(){
	var divResult = document.getElementById('resultMsg');
	var c_ID = document.getElementById("c_ID").value;
	var c_Date = document.getElementById("c_Date").value;
	var c_Class = document.getElementById("c_Class").value;
	var c_Title = document.getElementById("c_Title").value;
	var c_Conts = document.getElementById("c_Conts").value;
	var u_ID = document.getElementById("u_ID").value;
	var xhr = new XMLHttpRequest();

	xhr.open("GET", "<c:url value='/selectSingleChat/" + c_ID + "' />", true);
	xhr.send();
	var message = "";
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var chatBean = JSON.parse(xhr.responseText);
			c_ID.innerHTML = chatBean.c_ID;
			c_Date.value = chatBean.c_Date;
			c_Class.value = chatBean.c_Class;
			c_Title.value = chatBean.c_Title;
			c_Conts.value = chatBean.c_Conts;
			u_ID.value = chatBean.u_ID;
		}
	}
	
	updateData.onclick = function(){
		var divResult = document.getElementById('resultMsg');
		var c_IDu = document.getElementById("c_ID").value;
		var c_Dateu = document.getElementById("c_Date").value;
		var c_Classu = document.getElementById("c_Class").value;
		var c_Titleu = document.getElementById("c_Title").value;
		var c_Contsu = document.getElementById("c_Conts").value;
		var u_IDu = document.getElementById("u_ID").value;
		var span0 = document.getElementById('result0c');
		var span1 = document.getElementById('result1c');
		var span2 = document.getElementById('result2c');
		var span3 = document.getElementById('result3c');
		var span4 = document.getElementById('result4c');
		var span5 = document.getElementById('result5c');

		if(!c_IDu){
			setErrorFor(span0, "請輸入文章編號");
		} else{
			span0.innerHTML = "";
		}
		if(!c_Dateu){
			setErrorFor(span1, "請輸入日期");
		} else{
			span1.innerHTML = "";
		}
		if(!c_Classu){
			setErrorFor(span2, "請輸入類別")
		} else{
			span2.innerHTML = "";
		}
		if(!c_Titleu){
			setErrorFor(span3, "請輸入標題");
		} else{
			span3.innerHTML = "";
		}
		if(!c_Contsu){
			setErrorFor(span4, "請輸入內容");
		} else{
			span4.innerHTML = "";
		}
		if(!u_IDu){
			setErrorFor(span5, "請輸入帳號");
		} else{
			span5.innerHTML = "";
		}
		
		if (hasError){
			return false;
		}
		var xhr1 = new XMLHttpRequest();
		xhr1.open("PUT", "<c:url value='/updateChat' />" + c_IDu, true);
		var jsonInsertData = {
			"c_ID" : c_IDu,
			"c_Date" : c_Dateu,
			"c_Class" : c_Classu,
			"c_Title" : c_Titleu,
			"c_Conts" : c_Contsu,
			"u_ID" : u_IDu
		}
		xhr1.setRequestHeader("Content-Type", "application/json");
		xhr1.send(JSON.stringify(jsonInsertData));
		xhr1.onreadystatechange = function() {
			if (xhr1.readyState == 4 && (xhr1.status == 200 || xhr1.status == 201) ){
				result = JSON.parse(xhr1.responseText);
				if(result.fail){
					spanResult.innerHTML = "<font color='red' >" + result.fail + "</font>";
				}else if(result.success){
					divResult.innerHTML = "<font color='GREEN'>"
	   					+ result.success + "</font>";
	   					  span0.innerHTML = "";	
	   					  span1.innerHTML = "";
	   					  span2.innerHTML = "";
	   					  span3.innerHTML = "";	
	   					  span4.innerHTML = "";
	   					  span5.innerHTML = "";
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
	<h3>更新文章</h3>
	<span id='resultMsg' style="height: 18px; font-weight: bold;"></span>
	<hr>
<div style="display: inline-block; text-align: left">
  <table style="line-height:20px;">	
    <tr>
      <td align='left'>文章編號: </td>
	  <td align='center'>&nbsp;<input type='text' id="c_ID" required/></td>
	  <td>&nbsp;<span id='result0c'></span></td>
    </tr>
    <tr>
      <td align='left'>日期: </td>
	  <td align='center'>&nbsp;<input type='text' id="c_Date" required/></td>
	  <td>&nbsp;<span id='result1c'></span></td>
    </tr>
    <tr>
      <td align='left'>類別: </td>
	  <td align='center'>&nbsp;<input type='text' id="c_Class" required/></td>
	  <td>&nbsp;<span id='result2c'></span></td>
    </tr>
    <tr>
      <td align='left'>標題: </td>
	  <td align='center'>&nbsp;<input type='text' id="c_Title" required/></td>
	  <td>&nbsp;<span id='result3c'></span></td>
    </tr>
    <tr>
      <td align='left'>內容: </td>
	  <td align='center'>&nbsp;<input type='text' id="c_Conts" required/></td>
	  <td>&nbsp;<span id='result4c'></span></td>
    </tr>
    <tr>
      <td align='left'>帳號: </td>
	  <td align='center'>&nbsp;<input type='text' id="u_ID" required/></td>
	  <td>&nbsp;<span id='result5c'></span></td>
    </tr>
    <tr>
		<td colspan='2' align='center'><button id='updateData'>儲存</button></td>
	</tr>
  </table>
</div>
</div>
<p/>
<div align='center'>
	<hr>
	<a href="<c:url value='/chatIndex' />">上一頁</a>
</div>
</body>
</html>