<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品資訊</title>
</head>
<body>


	<form:form action="${pageContext.servletContext.contextPath }/product" method="post" modelAttribute="productInfo">
		<c:if test="${empty product.p_ID }" var="flag" />
		<c:if test="${!flag }">
			<form:hidden path="p_ID" />
			<input type="hidden" name="_method" value="PUT">
		</c:if>


		<table>
			<tr>
				<th colspan="2"><c:if test="${flag }">新增商品</c:if> <c:if
						test="${!flag }">修改商品</c:if></th>
			</tr>
			<tr>
				<td>p_ID</td>
				<td><form:input path="p_ID" /></td>
			</tr>
			<tr>
				<td>p_Name</td>
				<td><form:input path="p_Name" /></td>
			</tr>
			<tr>
				<td>p_Class</td>
				<td><form:input path="p_Class" /></td>
			</tr>
			<tr>
				<td>p_Price</td>
				<td><form:input path="p_Price" /></td>
			</tr>
			<tr>
				<td>p_DESC</td>
				<td><form:input path="p_DESC" /></td>
			</tr>
			<tr>
				<td>u_ID</td>
				<td><form:input path="u_ID" /></td>
			</tr>
			<tr>
				<td>p_createDate</td>
				<td><form:input path="p_createDate" /></td>
			</tr>
			<tr>
				<td>p_Img</td>
				<td><form:input path="p_Img" type ="file" /></td>
			</tr>
			<tr>
				<td>p_Video</td>
				<td><form:input path="p_Video" type="file" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${flag }">
						<input type="submit" value="ADD">
					</c:if> <c:if test="${!flag }">
						<input type="submit" value="UPDATE">
					</c:if></td>

			</tr>

		</table>

	</form:form>





</body>
</html>