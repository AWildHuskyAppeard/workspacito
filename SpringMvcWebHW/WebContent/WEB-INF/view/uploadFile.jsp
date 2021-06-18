<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Upload File</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function() {
	
		$('#upload').click(function(e) {
			e.preventDefault();
			var form = $('form')[0];
			var formData = new FormData(form);
			$.ajax({
				url : '/SpringMvcWebHW/testUploadFile.controller',
				type : 'POST',
				data : formData,
				contentType : false,
				cache : false,
				processData : false,
				success : function(data) {
					alert(data.success);
				},
				error : function(data) {
					alert(data.fail);
				}
			})
		});

		
		dataArea = document.getElementById("dataArea");
		let xhr = new XMLHttpRequest();
		xhr.open('GET', "<c:url value='/prac/showPic.controller' />", true);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				dataArea.src = showData(xhr.responseText);
			}
		};
		xhr.send();

	});

	function showData(textData){
		let data = JSON.parse(textData);
		let image = data.picturesString;
		return image;
	}
</script>

</head>
<body>
	<form id="uploadForm" enctype="multipart/form-data">
		Please Select Picture To Upload:<br /> 
		<input type="file" name="myFiles" id="myFiles" />
		<input type="text" name="theText" id="theTextId" /> <!-- 測試 -->
		<button id="upload" type="button">upload</button>
	</form>
    <img id="dataArea" width='120' height='120'>
</body>
</html>