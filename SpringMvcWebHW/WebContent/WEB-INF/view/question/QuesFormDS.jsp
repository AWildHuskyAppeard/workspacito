<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
%>
<HTML>
<HEAD>
<BODY BGCOLOR="aliceblue">
<meta charset="UTF-8">
<TITLE>題庫</TITLE>

</HEAD>

<BODY>
<FORM ACTION="/AwesomeProject/QuesServletDS">
<H1 ALIGN="CENTER">題庫</H1><BR>
<p align="center">   題目編號  :  <INPUT TYPE="TEXT" NAME="Q_ID" PLACEHOLDER="請輸入題目編號  ex:1" required></p>
<!--   題目種類  :  <INPUT TYPE="TEXT" NAME="Q_Type" PLACEHOLDER="請輸入題目種類  ex:choice"><BR> -->
<!--   題　目　  :  <INPUT TYPE="TEXT" NAME="Q_Ques" PLACEHOLDER="請輸入題目  ex:玩具的日文為?"><BR> -->
<!--   選　項　  :  <INPUT TYPE="TEXT" NAME="Q_Selection" PLACEHOLDER="請輸入選項  ex:1おもちゃ 2おさけ 3おいしい 4おしゃれ"><BR> -->
<!--   正　解　  :  <INPUT TYPE="TEXT" NAME="Q_Ans" PLACEHOLDER="請輸入正確解答  ex:1"><BR> -->
<!--   題目類別  :  <INPUT TYPE="TEXT" NAME="P_Class" PLACEHOLDER="請輸入題目類別  ex:Japanese"><BR><BR>？ -->
  
  <DIV>
   <p align="center"> <INPUT NAME="QUERY"  TYPE="SUBMIT" VALUE="QUERY" >
<!--     <INPUT NAME="UPDATE" TYPE="SUBMIT" VALUE="UPDATE"> -->
<!--     <INPUT NAME="DELETE" TYPE="SUBMIT" VALUE="DELETE"> -->
<!--     <INPUT NAME="CREATE" TYPE="SUBMIT" VALUE="CREATE"><BR> -->
  
     <!--   <INPUT NAME="AllQUERY" TYPE="SUBMIT" VALUE="BackStage"></p>-->
    <!--<INPUT NAME="HOME PAGE" TYPE="BUTTON" VALUE="HOME PAGE"  onclick="location.href='AwesomeProject/index_test.html'"/> -->
    <!-- <a href="AwesomeProject/index_test.html"></a> -->
    
  </DIV>
</FORM><BR>
<a href="/AwesomeProject/index_test.html">回首頁</a>


</BODY>
</HTML>