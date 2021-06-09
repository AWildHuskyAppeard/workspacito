<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊</title>
<script>
var hasError = false;
window.onload = function(){
	var alink = document.getElementById("accountCheck");
	var sendData = document.getElementById("sendData");

	//檢查帳號是否重複
	alink.onclick = function(){
		var u_id = document.getElementById("u_id").value;
		var span = document.getElementById("result0c");
		if(!u_id){
			span.innerHTML = "<font color='blue' size='-1'>請輸入帳號</font>";
			return;
		}
		var xhr = new XMLHttpRequest();
		// 待寫後端判斷(done)
		xhr.open("POST", "<c:url value='/checkUserId' />", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send("u_id=" + u_id);
		var message = "";
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var result = JSON.parse(xhr.responseText);
				if(result.u_id.length == 0){
					message = "<font color='green' size='-2'>帳號可用!</font>";
				}
				else if ( result.u_id.startsWith("Error") ) {
				message = "<font color='red' size='-2'>發生錯誤: 代號" + result.u_id + "</font>";
				}
				else {
					message = "<font color='red' size='-2'>帳號重複，請使用其它帳號!</font>";
				}
				span.innerHTML = message;
			}
		}
	}

    
	//確認送出資料
	sendData.onclick = function(){
		//抓欄位資料
		var u_id = document.getElementById("u_id").value; //帳號
		var u_psw = document.getElementById("u_psw").value; //密碼
		var ck_psw = document.getElementById("ck_psw").value; //確認密碼
		var u_lastname = document.getElementById("u_lastname").value; //姓
		var u_firstname = document.getElementById("u_firstname").value; //名
		var u_email = document.getElementById("u_email").value; //信箱
		var span0 = document.getElementById('result0c'); //帳號span
		var span1 = document.getElementById('result1c'); //密碼span
		var span2 = document.getElementById('result2c'); //姓span
		var span3 = document.getElementById('result3c'); //名span
		var span4 = document.getElementById('result4c'); //信箱span
		var spanCheckPsw = document.getElementById('checkPsw'); //確認密碼
		
		var spanResult = document.getElementById('resultMsg');

		if(!u_id){
			setErrorFor(span0, "請輸入帳號");
		} else{
			span0.innerHTML = "";
		}
		if(!u_psw){
			setErrorFor(span1, "請輸入密碼");
		} else{
			span1.innerHTML = "";
		}
		if(!ck_psw){
			setErrorFor(spanCheckPsw, "請再次輸入密碼!")
		} else{
			spanCheckPsw.innerHTML = "";
		}
		if(!u_lastname){
			setErrorFor(span2, "請輸入姓氏");
		} else{
			span2.innerHTML = "";
		}
		if(!u_firstname){
			setErrorFor(span3, "請輸入名字");
		} else{
			span3.innerHTML = "";
		}
		if(!u_email){
			setErrorFor(span4, "請輸入信箱");
		} else{
			span4.innerHTML = "";
		}
		// 檢查密碼是否一致
		if(u_psw==ck_psw && ck_psw!=""){
			spanCheckPsw.innerHTML = "";
			hasError = false;
		}else{
			spanCheckPsw.innerHTML = "<font color='red' size='-2'>密碼不同，請再次輸入並確認!</font>";
			hasError = true;
		}
		if (hasError){
			return false;
		}
		var xhr1 = new XMLHttpRequest();
		xhr1.open("POST", "<c:url value='/userSignup' />");
		var jsonSignupData = {
			"u_id" : u_id,
			"u_psw" : u_psw,
			"u_lastname" : u_lastname,
			"u_firstname" : u_firstname,
			"u_email" : u_email
		}
		xhr1.setRequestHeader("Content-Type", "application/json");
		xhr1.send(JSON.stringify(jsonSignupData));
		xhr1.onreadystatechange = function() {
			if (xhr1.readyState == 4 && xhr1.status == 200){
				result = JSON.parse(xhr1.responseText);
				//判斷回傳
				if(result.fail){
					spanResult.innerHTML = "<font color='red' >" + result.fail + "</font>";
				}else if(result.success){
					alert(result.success + "! 為您導回上一頁...");
					top.location='<c:url value='/gotoUserIndex.controller' />';
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
<h3>填寫會員資料</h3>
<span id='resultMsg' style="height: 18px; font-weight: bold;"></span>
<hr>
<div style="display: inline-block; text-align: left">
  <form>
  <table style="line-height:20px;">	
    <tr>
      <td>帳號:&nbsp;&nbsp;<span id='result0c'></span><br/><input type="text" id="u_id" name="u_id"/>&nbsp;<a href='#' id='accountCheck' style='font-size: 10pt;'>檢查帳號</a></td>
    </tr>
    <tr>
      <td>密碼:&nbsp;&nbsp;<span id='result1c'></span><br/><input type="password" id="u_psw" name="u_psw"/></td>
    </tr>
    <tr>
      <td>確認密碼:&nbsp;&nbsp;<span id='checkPsw'></span><br/><input type="password" id="ck_psw" name="ck_psw"/></td>
    </tr>
    <tr>
      <td>姓:&nbsp;&nbsp;<span id='result2c'></span><br/><input type="text" id="u_lastname" name="u_lastname"/></td>
    </tr>
    <tr>
      <td>名:&nbsp;&nbsp;<span id='result3c'></span><br/><input type="text" id="u_firstname" name="u_firstname"/></td>
    </tr>
    <tr>
      <td>信箱:&nbsp;&nbsp;<span id='result4c'></span><br/><input type="text" id="u_email" name="u_email"/></td>
    </tr>
    <tr><td><button type="button" id="sendData">送出</button>&nbsp;&nbsp;<button type="reset">清除</button></td></tr>
  </table>
  </form>
</div>
<hr/>
<a href="<c:url value='/gotoUserIndex.controller' />">上一頁</a>
</div>
</body>
</html>