<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊</title>
</head>
<body>id,psw,lastname,firstname,email
<center>
	<form action="savesignup.controller" method="post">
	  <table>
	    <tr>
	      <td>帳號:<br/><input type="text" name="u_id"/>&nbsp;<a href="xxxController">檢查</a></td>
	    </tr>
	    <tr>
	      <td>密碼:<br/><input type="password" name="u_psw"/></td>
	    </tr>
	    <tr>
	      <td>姓:<br/><input type="text" name="u_lastname"/></td>
	    </tr>
	    <tr>
	      <td>名:<br/><input type="text" name="u_firstname"/></td>
	    </tr>
	    <tr>
	      <td>信箱:<br/><input type="text" name="u_email"/></td>
	    </tr>
	    <tr><td><input type="submit" value="確認">&nbsp;&nbsp;<input type="reset" value="清除"></td></tr>
	  </table>
	</form>  
</center>
</body>
</html>