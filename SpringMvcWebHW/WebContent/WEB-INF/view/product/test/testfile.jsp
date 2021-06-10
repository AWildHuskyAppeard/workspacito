<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload File</title>
</head>
<body>
<form action="upload" method="post" enctype="multipart/form-data">
    Please Select Picture To Upload:<br/>
    <input type="file" name="p_Img"/>
    <input type="file" name="p_Video"/>
    <input type="submit" value="upload"/>
</form>
</body>
</html>