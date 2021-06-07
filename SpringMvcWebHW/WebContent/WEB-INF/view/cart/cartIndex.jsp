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
	<%  
		request.getSession(true);
		List<ProductInfo> cart = (ArrayList<ProductInfo>)(session.getAttribute("cart"));
		session.setAttribute("cart", cart);
	%>

	<h1>您的購物車內有：</h1>
	<!-- 1. 顯示當前購物車內容表格 -->
	<form method="POST" action="#"> 
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
			<tbody id='dataArea'>
			</tbody>
		</table>

		<hr>
	<!-- 2. 按鈕導向各頁 -->
		<button id="remove" formaction="<c:url value='/cart.controller/remove' />" disabled>移除</button>
		<button id="checkout" formaction="<c:url value='/cart.controller/cartCheckout' />">去結帳</button>
		<button name="" value="" formaction="#">回首頁</button>
		<hr>
	</form>

	
	<script src="../resources/js/jquery-3.6.0.min.js"></script>
	<script>
		$(function(){

			let dataArea = $('#dataArea');

			// 4 (remove by AJAX)
			$("#remove").click(function(){
				let xhr = new XMLHttpRequest();
				let url = "<c:url value='/cart.controller/remove' />";
				alert("url = " + url);
				xhr.open("GET", url, true);
				xhr.send();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						dataArea.innerHTML = parseCart(xhr.responseText);
					}
				}
			});

			// 3 (showCart by AJAX)
			$(window).load(function(){
				let xhr = new XMLHttpRequest();
				let url = "<c:url value='/cart.controller/showCart' />";
				alert("url = " + url)
				xhr.open("GET", url, true);
				xhr.send();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						dataArea.innerHTML = parseCart(xhr.responseText);
					}
				}
			});
			
			// 3-1 parseCart()
			function parseCart(cart) {
	                   let products = JSON.parse(cart);
	                   let segment = "";
					   let totalPrice = 0;

	                   for (let i = 0; i < products.length; i++) {
						   segment += "<tr>"
										 + "<td><input type='checkbox' name='ckbox' value='" + i + "' id='ckbox'>取消</td>"
										 + "<td>" + products[i].p_Name + "</td>"
										 + "<td>" + products[i].p_ID + "</td>"
										 + "<td>" + products[i].p_Price + "</td>"
										 + "<td>" + products[i].p_DESC + "</td>"
										 + "<td>" + products[i].u_ID + "</td>"
										 + "</tr>";
							totalPrice += products[i].p_Price;
	                   }
	                   return segment;
			};

			// 1 (AJAX)
			btn02.onclick = function() {
				let xhr = new XMLHttpRequest();
				let queryString = "pk=3";
				let url = "<c:url value='/showCart' />?" + queryString;
				// alert("url = " + url)
				xhr.open("GET", url, true);
				xhr.send();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						// dataArea.innerHTML = xhr.responseText;
						dataArea.innerHTML = showSingleMember(xhr.responseText);
					}
				}
			}
			
			// 1-1
			function showSingleMember(text) {
				let member = JSON.parse(text);
				let segment = "<table border='1'>";
					segment += "<tr>"
						+ "<th>主鍵值</th>"
						+ "<th>會員代號</th>"
						+ "<th>姓名</th>"
						+ "<th>餘額</th>"
						+ "<th>生日</th>"
						+ "</tr>"
						+ "<tr>"
						+ "<td>" + member.pk + "</td>"
						+ "<td>" + member.name + "</td>"
						+ "<td>" + member.id + "</td>"
						+ "<td>" + member.balance + "</td>"
						+ "<td>" + member.birthday + "</td>"
						+ "</tr>"
						return segment;
			}

			// 2 (防呆)
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