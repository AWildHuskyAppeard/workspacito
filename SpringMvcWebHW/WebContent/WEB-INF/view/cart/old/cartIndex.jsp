<%@page import="cart.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart Index Page</title>
<style>
	table {
	    border: 2px solid black;
	    border-collapse: 
	}
	
	tbody thead {
		border: 2px solid black;
	    border-collapse: 
	}
	
	tr td {
	    border: 1.5px solid gray;
	    border-collapse: collapse;
	}
	
	tr th {
	    border: 1.5px solid gray;
	    border-collapse: collapse;
	}
</style>
</head>
<body>
<jsp:useBean id="addedProduct" scope="session" class="cart.ProductBean" type="cart.ProductBean" />
<%  
	request.getSession(true);
	List<ProductBean> cart = (ArrayList<ProductBean>)(session.getAttribute("cart"));
	// 測試用。cart如果是空的，會自動補3件下列商品作為測試
	if(cart == null || cart.size() == 0) 
	{
%>
		<h1 style='background-color: aquamarine; font-family: 
		Comic Sans MS ;font-size: 200%'>※購物車沒有任何東西，因此管理員塞了三個課程進來✌💀✌</h1>
<%
		cart = new ArrayList<ProductBean>();
		cart.add(CartControllerServlet.testBean1);
		cart.add(CartControllerServlet.testBean2);
		cart.add(CartControllerServlet.testBean3);
	}
%>
	
<%	
	session.setAttribute("cart", cart);
	if(cart == null) cart = new ArrayList<ProductBean>();
%>

<h1>您的購物車內有：</h1>
<!-- 1. 顯示當前購物車內容表格 -->
	<form method="POST" action="/AwesomeProject/CartControllerServlet"> 
		<table>
			<thead>
				<tr>
				    <th>移除</th>
				    <th>課程名稱(P_Name)</th>
				    <th>課程編號(P_ID)</th>
				    <th>課程價格(P_Price)</th>
				    <th>課程介紹(P_DESC))</th>
				    <th>課程老師(U_ID)</th>
				</tr>
			</thead>
			<tbody>
			<% for(int i = 0; i < cart.size(); i++) {
			%>
			<% if(cart != null){ %>
				<tr>
				<td><input type="checkbox" name="ckbox" value="<%=i%>" id="ckbox">取消</td>
				<td> <%= cart.get(i).getP_Name () %>   </td>
				<td> <%= cart.get(i).getP_ID   () %>   </td>
				<td> <%= cart.get(i).getP_Price() %>   </td>
				<td> <%= cart.get(i).getP_DESC () %>   </td>
				<td> <%= cart.get(i).getU_ID   () %>   </td>
			</tr>
			<% } %>
			<%} 
			%>
			</tbody>
		</table>

		<hr>
<!-- 2. 按鈕導向各頁 -->
		<button name="todo" value="remove" id="delete" disabled>移除</button>
		<button name="todo" value="checkout" id="checkout">去結帳</button>
		<hr>
	</form>
	<form method="POST" action="/AwesomeProject/index_test.html">
		<button name="" value="">回首頁</button>
	</form>
	<script src="../assets/jquery-3.6.0.min.js"></script>
	<script>
		$(function() {
			$('input#ckbox').on('click', function() {
					let ckboxes = $('input#ckbox:checked');
					$('#delete').attr('disabled', true);
						if($(ckboxes).length == 0 || $(ckboxes).length == null) {
						} else {
							$('#delete').attr('disabled', false);			
						}
				})
		})
	</script>
</body>
</html>