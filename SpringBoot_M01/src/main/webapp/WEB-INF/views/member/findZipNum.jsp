<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{ font-family:Verdana; background:magenta;}
#popup h1{font-family:Verdana; font-size:45px; color:#CCC; font-weight:normal;}
table#zipcode{border-collapse:collapse; border-top:3px solid #fff;	border-bottom:3px solid #fff; 
margin-top:15px; width:100%;}
table#zipcode th, table#zipcode td{text-align:center; color:#fff; border-bottom: 1px dotted  #fff;}
table#zipcode  a{display:block; height:20px; text-decoration:none; color:#fff; padding:10px;}
table#zipcode a:hover{color: #F90; font-weight: bold;}

</style>
<script type="text/javascript">
function result( zipNum, sido, gugun, dong){
	opener.document.formm.zip_num.value=zipNum;
	opener.document.formm.addr1.value=sido+" "+gugun+" "+dong;
	self.close();
}
</script>
</head>
<body>
<div id="popup">
	<h1>우편번호검색</h1>
	<form method="get" name="formm" action="findZipNum">
		동 이름 : <input name="dong"  type="text">
		<input type="submit" value="찾기"  class="submit">
	</form><br><br>
	<!-- 검색된 우편번호와 동이 표시되는 곳 -->
	<table id="zipcode">
		<tr><th>우편번호</th><th>주소</th></tr>
		<c:forEach items="${addressList}" var="addressVO">
			<tr>
				<td>${addressVO.zip_num}</td>
				<td><a href="#" onClick="result( '${addressVO.zip_num}' , 
				'${addressVO.sido}' , '${addressVO.gugun}' , '${addressVO.dong}'  );" >
				${addressVO.sido} ${addressVO.gugun} ${addressVO.dong}</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>