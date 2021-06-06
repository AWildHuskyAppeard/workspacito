<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊</title>
</head>
<body>
<div style="text-align: center;">
<div style="display: inline-block; text-align: left">
	<form action="savesignup.controller" method="post">
	  <table style="line-height:20px;">	
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
	<hr/>
	<a href="<c:url value='/user/gotoUserIndex.controller' />">上一頁</a>
</div>
</div>
</body>
</html>