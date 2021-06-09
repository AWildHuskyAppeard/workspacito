<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	

<!DOCTYPE html>
<html>
<head>
<script>
var q_ID = ${q_ID};
var hasError = false;

window.onload = function() {
	var divResult = document.getElementById('resultMsg');
	var q_ID = document.getElementById("q_ID");
	var q_Type = document.getElementById("q_Type");
	var q_Ques = document.getElementById("q_Ques");
	var q_Selection = document.getElementById("q_Selection");
	var q_Ans = document.getElementById("q_Ans");
	var p_Class = document.getElementById("p_Class");
	var xhr = new XMLHttpRequest();

	xhr.open("GET", "<c:url value='/questions/" + q_ID + "' />", true);
	xhr.send();	
	var message = "";
	xhr.onreadystatechange = function() {
	 // 伺服器請求完成
	    if (xhr.readyState == 4 && xhr.status == 200) {
		   var quesBean = JSON.parse(xhr.responseText);
		   q_ID.value = quesBean.q_ID;
		   q_Type.value = quesBean.q_Type;
		   q_Ques.value = quesBean.q_Ques;
		   q_Selection.value = quesBean.q_Selection;
		   q_Ans.value = quesBean.q_Ans;
		   p_Class.value = quesBean.p_Class;
	    }
     }
   
   var updateData = document.getElementById("updateData");
   var deleteData = document.getElementById("deleteData");

   deleteData.addEventListener('click', (e)=> {
	   var result = confirm("確定刪除此筆記錄(題目編號:" + q_ID.value + ")?");
	   if (result) {
		    var xhr2 = new XMLHttpRequest();
	   		xhr2.open("DELETE", "<c:url value='/questions/' />" + q_ID, true);
	   		xhr2.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	   		xhr2.send();
	   		xhr2.onreadystatechange = function() {
						// 伺服器請求完成
	   		if (xhr2.readyState == 4 && (xhr2.status == 200 || xhr2.status == 204) ) {
	      		result = JSON.parse(xhr2.responseText);
	      		if (result.fail) {
			 		divResult.innerHTML = "<font color='red' >"
						+ result.fail + "</font>";
		  		} else if (result.success) {
		  			window.location.href = "http://localhost:8080/SpringRest21/showAllMembersAjax";
	      		}                                                             
			} 
		      
		  }
	   } 
   });

   updateData.onclick = function() {
		hasError = false;
   		// 讀取欄位資料	  
		var q_ID = document.getElementById("q_ID").value;
		var q_Type = document.getElementById("q_Type").value;
		var q_Ques = document.getElementById("q_Ques").value;
		var q_Selection = document.getElementById("q_Selection").value;
		var q_Ans = document.getElementById("q_Ans").value;
		var p_Class = document.getElementById("p_Class").value;

		var div0 = document.getElementById('result0c');
		var div1 = document.getElementById('result1c');
		var div2 = document.getElementById('result2c');
		var div3 = document.getElementById('result3c');
		var div4 = document.getElementById('result4c');
		var div5 = document.getElementById('result5c');

		if (!q_ID){
			setErrorFor(div0, "請輸入帳號");
   		} 	else {
      		div0.innerHTML = "";
   		}
		if (!q_Type){
			setErrorFor(div1, "請輸入題目類型");
		} else {
			div1.innerHTML = "";
		}
		if (!q_Ques){
			setErrorFor(div2, "請輸入問題");
		} else {
			div2.innerHTML = "";
		}
		if (!q_Selection){
			setErrorFor(div3, "請輸入回答選項");
		} else {
			div3.innerHTML = "";
		}
		if (!q_Ans){
			setErrorFor(div4, "請輸入正解");
		} else {
			div4.innerHTML = "";
		}
		if (!p_Class){
			setErrorFor(div5, "請輸入課程分類");
		} else {
			div5.innerHTML = "";
		}

   		var xhr1 = new XMLHttpRequest();
   		xhr1.open("PUT", "<c:url value='/questions/' />" + q_ID, true);
		var jsonMember = {
			"q_ID": q_ID, 					
			"q_Type": q_Type, 	
			"q_Ques": q_Ques,
			"q_Selection": q_Selection,
			"q_Ans": q_Ans,
			"p_Class": p_Class

   		}
   		xhr1.setRequestHeader("Content-Type", "application/json");
   		xhr1.send(JSON.stringify(jsonMember));
	
   
   		xhr1.onreadystatechange = function() {
				// 伺服器請求完成
   		if (xhr1.readyState == 4 && (xhr1.status == 200 || xhr1.status == 201) ) {
      		result = JSON.parse(xhr1.responseText);
      		if (result.fail) {
		 		divResult.innerHTML = "<font color='red' >"
					+ result.fail + "</font>";
	  		} else if (result.success) {
				divResult.innerHTML = "<font color='GREEN'>"
					+ result.success + "</font>";
				div0.innerHTML = "";	
				div1.innerHTML = "";
				div2.innerHTML = "";
				div3.innerHTML = "";
	 		} else {
				if (result.q_IDError) {
          			div0.innerHTML = "<font color='green' size='-2'>"
	     				+ result.q_IDError + "</font>";
				} else {
          			div0.innerHTML = "";
				}
				if (result.q_TypeError) {
	      			div1.innerHTML = "<font color='green' size='-2'>"
						+ result.q_TypeError + "</font>";
				} else {
	      			div1.innerHTML = "";
	   			}
				if (result.q_QuesError) {
          			div2.innerHTML = "<font color='green' size='-2'>"
						+ result.q_QuesError + "</font>";
				} else {
          			div2.innerHTML = "";
    			}
				if (result.q_SelectionError) {
	    			div3.innerHTML = "<font color='green' size='-2'>"
						+ result.q_SelectionError + "</font>";
				} else {
          			div3.innerHTML = "";
				}
				if (result.q_AnsError) {
	    			div4.innerHTML = "<font color='green' size='-2'>"
						+ result.q_AnsError + "</font>";
				} else {
          			div4.innerHTML = "";
				}
				if (result.p_ClassError) {
	    			div5.innerHTML = "<font color='green' size='-2'>"
						+ result.p_ClassError + "</font>";
				} else {
          			div5.innerHTML = "";
				}
      		}
		} 
   		} 
    }
   }



</script>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
<div align='center'>
<h2>修改試題資料</h2>
<div id='resultMsg' style="height:18px; font-weight: bold;"></div>
	<fieldset style='display: inline-block; width: 820px;'> 
	<legend>請修改下列資料</legend>
	<table border='1'>
	<tr height='60'>
		<td width='200'><input type="hidden" name="id" id='id'><br></td>
		<td width='400'>
			&nbsp;題目編號: <label id='q_ID'></label><br>
		</td>
		<td width='200'>
			<div id='result0c' style="height: 10px;"></div><br>
			<div id='result0s' style="height: 10px;"></div>
		</td>
	</tr>
	
	<tr height='60'>
		<td width='200'>&nbsp;</td>
		<td width='400'>
			&nbsp;題目類型: <input type="text" name="q_Type" id='q_Type'><br>
		</td>
		<td width='200' style="vertical-align:top">
			<div id='result1c' style="height: 10px;"></div><br>
			<div id='result1s' style="height: 10px;"></div>
		</td>	
	</tr>
	
	<tr height='60'>
		<td width='200'>&nbsp;</td>
		<td width='400'>
			&nbsp;問&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;題: <input type="text" name="q_Ques" id='q_Ques'><br>
		</td>
		<td width='200' style="vertical-align:top">
			<div id='result2c' style="height: 10px;"></div><br>
			<div id='result2s' style="height: 10px;"></div>
		</td>	
	</tr>
	
	<tr height='60'>		
		<td width='200'>&nbsp;</td>
		<td width='400'>
			&nbsp;回答選項: <input type="text" name="q_Selection" id='q_Selection' size='24'>
		</td>	
		<td width='200'>
			<div id='result3c' style="height: 10px;"></div><br>
			<div id='result3s' style="height: 10px;"></div>			
		</td>	
	</tr>
	
	<tr height='60'>
		<td width='200'>&nbsp;</td>
		<td width='400'>
			&nbsp;正&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;解: <input type="text" name="q_Ans" id='q_Ans'><br>
		</td>
		<td width='200' style="vertical-align:top">
			<div id='result2c' style="height: 10px;"></div><br>
			<div id='result2s' style="height: 10px;"></div>
		</td>	
	</tr>
	
	<tr height='60'>
		<td width='200'>&nbsp;</td>
		<td width='400'>
			&nbsp;課程分類: <input type="text" name="p_Class" id='p_Class'><br>
		</td>
		<td width='200' style="vertical-align:top">
			<div id='result2c' style="height: 10px;"></div><br>
			<div id='result2s' style="height: 10px;"></div>
		</td>	
	</tr>
	
	
	<tr height='50'>		
		<td colspan='3' align='center'>
			<button id='updateData'>更新</button>
			<button id='deleteData'>刪除</button>
		</td>
	</tr>		
			</table>
		</fieldset>
	<hr>	
	<p>	
	<a href="<c:url value='/showAllQuestionsAjax' />">回前頁</a>
<hr>
</div>

</body>
</html>