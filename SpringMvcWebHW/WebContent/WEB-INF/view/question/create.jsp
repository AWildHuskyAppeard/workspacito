<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script>
var hasError = false;
window.onload = function() {
	var div = document.getElementById('result0c');
	
   var sendData = document.getElementById("sendData");
   sendData.onclick = function() {
		hasError = false;
   		// 讀取欄位資料	  
		var idValue = document.getElementById("q_ID").value;
		// 抓radio的使用者選取值
		var typeValue = document.getElementsByName("q_Type");
		// 建立用來判斷是否有選擇資料
		var typeValue0 = typeValue;
		//抓取陣列中,被使用者所選取的項目
		for(var i = 0; i < typeValue.length; i ++){ 
			if(typeValue[i].checked){ 
				 typeValue = typeValue[i].value;
 }
}
		var quesValue = document.getElementById("q_Ques").value;
		var selectionValue = document.getElementById("q_Selection").value;
		var ansValue = document.getElementById("q_Ans").value;

		var classValue = document.getElementsByName("p_Class");
		var classValue0 = classValue;
		for(var i = 0; i < classValue.length; i ++){ 
			if(classValue[i].checked){ 
				 classValue = classValue[i].value;
 }
}
		
		var div0 = document.getElementById('result0c');
		var div1 = document.getElementById('result1c');
		var div2 = document.getElementById('result2c');
		var div3 = document.getElementById('result3c');
		var div4 = document.getElementById('result4c');
		var div5 = document.getElementById('result5c');
		
		var divResult = document.getElementById('resultMsg');
		
		if (!idValue){
			setErrorFor(div0, "請輸入題目編號");
   		} 	else {
      		div0.innerHTML = "";
   		}
		//確認使用者有沒有從選項中進行選取
		if (typeValue == typeValue0){
			setErrorFor(div1, "請選擇題目類型");
		} else {
			div1.innerHTML = "";
		}
   		if (!quesValue){
			setErrorFor(div2, "請輸入問題內容");
		} else {
			div2.innerHTML = "";
   		}
   		if (!selectionValue){
			setErrorFor(div3, "請輸入回答選項");
		} else {
			div3.innerHTML = "";
   		}
   		if (!ansValue){
			setErrorFor(div4, "請輸入正解");
		} else {
			div4.innerHTML = "";
   		}
   		if (classValue == classValue0){
			setErrorFor(div5, "請選擇課程分類");
		} else {
			div5.innerHTML = "";
   		}


			
   		if (hasError){
       		return false;
   		}

   		var xhr1 = new XMLHttpRequest();
   		xhr1.open("POST", "<c:url value='/questions' />", true);
		var jsonQuestions = {
			"q_ID": idValue, 	
			"q_Type": typeValue,
			"q_Ques": quesValue,
			"q_Selection": selectionValue,
			"q_Ans": ansValue,
			"p_Class": classValue,

		}
  		xhr1.setRequestHeader("Content-Type", "application/json");
		//物件轉為 JSON 字串
  		xhr1.send(JSON.stringify(jsonQuestions));
//      以下敘述錯誤  		
// 		xhr1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//    		xhr1.send("id=" + idValue + "&name=" + nameValue + "&balance=" 
//    				+ balanceValue + "&birthday=" + birthdayValue );
   
   		xhr1.onreadystatechange = function() {
			// 伺服器請求完成
   		if (xhr1.readyState == 4 && (xhr1.status == 200 || xhr1.status == 201) ) {
			   result = JSON.parse(xhr1.responseText);
			   //JSON 字串轉為物件
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
				div4.innerHTML = "";
				div5.innerHTML = "";
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
						+ resultq_SelectionError + "</font>";
				} else {
          			div3.innerHTML = "";
				}
				if (result.q_AnsError) {
	    			div4.innerHTML = "<font color='green' size='-2'>"
						+ resultq_AnsError + "</font>";
				} else {
          			div4.innerHTML = "";
				}
				if (result.p_ClassError) {
	    			div5.innerHTML = "<font color='green' size='-2'>"
						+ resultq_p_ClassError + "</font>";
				} else {
          			div5.innerHTML = "";
				}
      		}
		} 
  	    }
    }
		
}
		


function setErrorFor(input, message){
	input.innerHTML = "<font color='red' size='-2'>" + message + "</font>";
    hasError = true;
 }
	
   

// 還用不到驗證生日及信箱

// function dateValidation(str) {
// 	  var re = new RegExp("^([0-9]{4})[.-]{1}([0-9]{1,2})[.-]{1}([0-9]{1,2})$");
// 	  var days = [0, 31, 28, 31, 30,  31, 30, 31, 31, 30, 31, 30, 31];
// 	  var strDataValue;
// 	  var valid = true;
// 	  if ((strDataValue = re.exec(str)) != null) {
// 	    var y, m, d;
// 	    y = parseFloat(strDataValue[1]);
// 	    if (y <= 0 || y > 9999) { /*年*/
// 	      return false;
// 	    } 
// 	    m = parseFloat(strDataValue[2]);
	    
// 	    if (m < 1 || m > 12) { /*月*/
// 	        return false;
// 	    }
// 	    d = parseFloat(strDataValue[3]);
// 	    if ( y % 4 == 0 && y % 100 != 0 || y % 400 == 0 ){
// 	       days[2] = 29;
// 	    }  else {
// 	       days[2] = 28;
// 	    }
// 	    if (d <= 0 || d > days[m]) { /*日*/
// 	      valid = false;
// 	    }
// 	  } else {
// 	    valid = false;
// 	  }  
// 	  return valid;
// 	}
  
// 	function isEmail(email) {
// 		return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
// 	}

   
</script>
<meta charset="UTF-8">
<title>Create</title>
</head>
<body>
	<div align='center'>
		<h3>輸入試題資料</h3>
		<hr>
		<div id='resultMsg' style="height: 18px; font-weight: bold;"></div>
		<br>
		<fieldset style='display: inline-block; width: 820px;'>
			<legend>填寫下列資料</legend>
			<table border='1'>
				<tr height='60'>
					<td width='200'>&nbsp;</td>
					<td width='400'>&nbsp;題目編號: <input type="text" name="q_ID"
						id='q_ID'><br>
					</td>
					<td width='200'>
						<div id='result0c' style="height: 10px;"></div> <br>
						<div id='result0s' style="height: 10px;"></div>
					</td>
				</tr>
				<tr height='60'>
					<td width='200'>&nbsp;</td>
					<td width='400'>&nbsp;題目類型: 
<!-- 					<input type="text" name="q_Type" id='q_Type'><br>						 -->
						<input type="radio" name="q_Type" id="q_Type" value="單選題">單選題
						<input type="radio"name="q_Type" id="q_Type" value="複選題">複選題 
						<input type="radio" name="q_Type" id="q_Type" value="簡答題">簡答題
					</td>
					<td width='200' style="vertical-align: top">
						<div id='result1c' style="height: 10px;"></div> <br>
						<div id='result1s' style="height: 10px;"></div>
					</td>
				</tr>
				<tr height='60'>
					<td width='200'>&nbsp;</td>
					<td width='400'>
						&nbsp;問&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;題: <input
						type="text" name="q_Ques" id='q_Ques'><br>
					</td>
					<td width='200' style="vertical-align: top">
						<div id='result2c' style="height: 10px;"></div> <br>
						<div id='result2s' style="height: 10px;"></div>
					</td>
				</tr>

				<tr height='60'>
					<td width='200'>&nbsp;</td>
					<td width='400'>&nbsp;回答選項: <input type="text"
						name="q_Selection" id='q_Selection'><br>
					</td>
					<td width='200' style="vertical-align: top">
						<div id='result3c' style="height: 10px;"></div> <br>
						<div id='result3s' style="height: 10px;"></div>
					</td>
				</tr>

				<tr height='60'>
					<td width='200'>&nbsp;</td>
					<td width='400'>
						&nbsp;正&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;解: <input
						type="text" name="q_Ans" id='q_Ans'><br>
					</td>
					<td width='200' style="vertical-align: top">
						<div id='result4c' style="height: 10px;"></div> <br>
						<div id='result4s' style="height: 10px;"></div>
					</td>
				</tr>

				<tr height='60'>
					<td width='200'>&nbsp;</td>
					<td width='400'>&nbsp;課程分類: 
<!-- 						 <input type="text" name="p_Class" id='p_Class'><br>						 -->
				 	    <input type="radio" name="p_Class" id="p_Class" value="日語">日語 
						<input type="radio"name="p_Class" id="p_Class" value="英語">英語 
						<input type="radio" name="p_Class" id="p_Class" value="德語">德語
						
					</td>
					<td width='200' style="vertical-align: top">
						<div id='result5c' style="height: 10px;"></div> <br>
						<div id='result5s' style="height: 10px;"></div>
					</td>
				</tr>


				<tr height='50'>
					<td colspan='3' align='center'><button id='sendData'>送出</button></td>
				</tr>
			</table>
		</fieldset>
		<hr>
		<p>
			<a href="<c:url value='/goQuesIndex' />">回前頁</a>
		<hr>
	</div>

</body>
</html>