<%@page import="tw.group5.controller.cart.*"%>
<%@page import="tw.group5.model.cart.*"%>
<%@page import="tw.group5.model.product.ProductInfo"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",-1);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout Page</title>
</head>
<body>
	<%  
		request.getSession(true);
		List<ProductInfo> cart = (ArrayList<ProductInfo>)(session.getAttribute("cart"));
		// session.setAttribute("cart", cart);
	%>

	<h1>您欲購買的項目如下：</h1>
	<!-- 1. 顯示當前購物車內容表格........................................ -->
	<form method="POST" action="#"> 
		<table>
			<thead>
				<tr>
				    <th>課程名稱(P_Name)</th>
				    <th>課程編號(P_ID)</th>
				    <th>課程價格(P_Price)</th>
				    <th>課程介紹(P_DESC))</th>
				    <th>課程老師(U_ID)</th>
				</tr>
			</thead>
			<tbody>
			<% Integer totalPrice = 0; %>
			<% for(int i = 0; i < cart.size(); i++) {
			%>
			<% if(cart != null){ %>
			<tr>
				<td> <%= cart.get(i).getP_Name () %>   </td>
				<td> <%= cart.get(i).getP_ID   () %>   </td>
				<td> <%= cart.get(i).getP_Price() %>   </td>
				<td> <%= cart.get(i).getP_DESC () %>   </td>
				<td> <%= cart.get(i).getU_ID   () %>   </td>
				<% totalPrice += cart.get(i).getP_Price(); %>
			</tr>
			<% } %>
			<%} 
			%>
			</tbody>
		</table>
		<h1>總計：NT$<%= totalPrice %></h1>
		<% session.setAttribute("O_Amt", totalPrice); %>
		<hr>
<!-- 2. 按鈕導向各頁................................................... -->
		<button formaction="<c:url value='/cart.controller/cartIndex' />">回上一頁</button>
		<button formaction="<c:url value='/cart.controller/pay' />">確定結帳</button>
		<hr>
	</form>
	<form method="POST" action="#">
		<button name="" value="">回首頁</button>
	</form>
	<script src="../resources/js/jquery-3.6.0.min.js"></script>
	<script>
		$(function(){

			
			
		})
	</script>
</body>
</html>