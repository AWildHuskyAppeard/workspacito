<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>展示商品資訊</title>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
	//  	$(function() {
	//  		$(".add").on("click",function(){
	//  			swal({
	//  				  title: "确定删除吗？",
	//  				  text: "你将无法恢复该虚拟文件！",
	//  				  type: "warning",
	//  				  showCancelButton: true,
	//  				  confirmButtonColor: "#DD6B55",
	//  				  confirmButtonText: "确定删除！",
	//  				  cancelButtonText: "取消删除！",
	//  				  closeOnConfirm: false,
	//  				  closeOnCancel: false
	//  				});
	//  		});
	//  	});
</script>
<body>
	<div align='center'>
		<h3>所有課程資訊</h3>
		<hr>
		<br>
		<fieldset style='display: inline-block; width: 820px;'>
		
		<table border='1'>
			<tr height='60'>
				<th width='400'>課程ID</th>
				<th width='400'>課程名稱</th>
				<th width='400'>課程類別</th>
				<th width='400'>課程價格</th>
				<th width='400'>導師</th>
				<th width='400'>課程發布日期</th>
				<th width='400'>課程內容</th>
				<th width='400'>圖片</th>
				<th width='400'>影片</th>
				<th width='400'><a href="product">新增課程</a></th>
			</tr>
			<c:forEach items="${products }" var="product">
				<tr height='60'>
					<td width='400'>${product.p_ID }</td>
					<td width='400'>${product.p_Name }</td>
					<td width='400'>${product.p_Class }</td>
					<td width='400'>${product.p_Price}</td>
					<td width='400'>${product.u_ID}</td>
					<td width='400'>${product.p_createDate}</td>
					<td width='400'>${product.p_DESC}</td>
					<td width='400'>${product.p_Img}</td>
					<td width='400'>${product.p_Video}</td>
					<td width='400'><a href="product/${product.p_ID }">更新</a>
					<a	href="deleteproduct/${product.p_ID }" class='del'>刪除</a></td>
				</tr>
			</c:forEach>
		</table>

		</fieldset>
	</div>
	<p>
	<div align='center'>
	<hr>
	<a href="<c:url value='/'/>" >回首頁</a>
	</div>
	<!-- 	<form method='post'> -->
	<!-- 		<input type="hidden" name="_method" value="DELETE"> -->
	<!-- 	</form> -->
</body>
</html>