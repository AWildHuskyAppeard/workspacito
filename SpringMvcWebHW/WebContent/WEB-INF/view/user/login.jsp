<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
window.onload = function(){
	var id = document.getElementById("id");
	var pwd = document.getElementById("pwd");
	var login = document.getElementById("login");
	var result0c = document.getElementById("result0c");
	var result1c = document.getElementById("result1c");
	var resultMsg = document.getElementById("resultMsg");
	login.onclick = function(){
		alert("alert測試");		
	}
	
}
</script>
<title>登入</title>
</head>
<body>
<div align='center'>
  <h3>登入</h3>
  <div id='resultMsg' style="height: 18px; font-weight: bold;"></div>
  <hr>
</div>

<div style="text-align: center;">
  <div style="display: inline-block; text-align: left;">
      帳號: <input type="text" name="id" id='id'><br>
      <div id='result0c' style="height: 10px;"></div><br>
      密碼: <input type="text" name="id" id='pwd'><br>
      <div id='result1c' style="height: 10px;"></div><br>
  </div>
  <div align='center'>
    <button id="login">登入</button>
    <hr>
    <a href="<c:url value='/user/gotoUserIndex.controller' />">上一頁</a>
  </div>
</div>
</body>
</html>
