<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>刪除文章</title>
<script>
var c_ID = "${c_ID}";

window.onload = function(){
	var c_ID = document.getElementById("c_ID");
	var c_Date = document.getElementById("c_Date");
	var c_Class = document.getElementById("c_Class");
	var c_Title = document.getElementById("c_Title");
	var c_Conts = document.getElementById("c_Conts");
	var u_ID = document.getElementById("u_ID");
	var xhr = new XMLHttpRequest();

	xhr.open("GET", "<c:url value='/selectAllChat/" + c_ID + "' />", true);
	xhr.send();
	var message = "";
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var chatBean = JSON.parse(xhr.responseText);
			c_ID.value = chatBean.c_ID;
			c_Date.value = chatBean.c_Date;
			c_Class.value = chatBean.c_Class;
			c_Title.value = chatBean.c_Title;
			c_Conts.value = chatBean.c_Conts;
			u_ID.value = chatBean.u_ID;
		}
	}
	var deleteData = document.getElementById("deleteData");
	deleteData.onclick = function(){
		var result = confirm("確定要刪除文章: " + c_ID.value + " 嗎?");
		if(result){
			var xhr1 = new XMLHttpRequest();
			xhr1.open("DELETE", "<c:url value='/deleteChat/" + c_ID + "' />", true);
			xhr1.send();
			xhr1.onreadystatechange = function() {
				if(xhr1.readyState == 4 && (xhr1.status == 200 || xhr1.status == 204)){
					result = JSON.parse(xhr1.responseText);
					if(result.fail){
						divResult.innerHTML = "<font color='red' >" + result.fail + "</font>";
					} else if (result.success){
						alert("刪除成功! 點擊確認將為您導回上一頁...");
						top.location= '<c:url value='/goSelectAllChat' />';
					}
				}
			}
		}
	}
}
</script>
</head>
<body>
<div align='center'>
	<h3>刪除文章</h3>
	<span id='resultMsg' style="height: 18px; font-weight: bold;"></span>
	<hr>
<div style="display: inline-block; text-align: left">
  <table style="line-height:20px;">	
    <tr>
      <td align='left'>文章編號: </td>
	  <td align='center'>&nbsp;<input type='text' disabled="disabled" id="c_ID"/></td>
    </tr>
    <tr>
      <td align='left'>日期: </td>
	  <td align='center'>&nbsp;<input type='text' disabled="disabled" id="c_Date"/></td>
    </tr>
    <tr>
      <td align='left'>類別: </td>
	  <td align='center'>&nbsp;<input type='text' disabled="disabled" id="c_Class"/></td>
    </tr>
    <tr>
      <td align='left'>標題: </td>
	  <td align='center'>&nbsp;<input type='text' disabled="disabled" id="c_Title"/></td>
    </tr>
    <tr>
      <td align='left'>內容: </td>
	  <td align='center'>&nbsp;<input type='text' disabled="disabled" id="c_Conts"/></td>
    </tr>
    <tr>
      <td align='left'>帳號: </td>
	  <td align='center'>&nbsp;<input type='text' disabled="disabled" id="u_ID"/></td>
    </tr>
    <tr>
		<td colspan='2' align='center'><button id='deleteData'>刪除</button></td>
	</tr>
  </table>
</div>
</div>
<p/>
<div align='center'>
	<hr>
	<a href="<c:url value='/goSelectAllChat' />">上一頁</a>
</div>
</body>
</html>