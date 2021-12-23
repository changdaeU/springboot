<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/board.css">
<script type="text/javascript" src="/script/board.js"></script>
</head>
<body>
<div id="wrap" align="center">
<h1>사용자 수정</h1>
<form name="frm" method="post" action="memberEdit">
<table>
	<tr><th>아이디</th><td>${dto.id }
		<input type="hidden" name="id" value="${dto.id }"></td></tr>
	<tr><th>암호</th><td><input type="password" name="pw" size="20">*</td></tr>
	<tr><th>확인</th><td><input type="password" name="pw_check" size="20">*</td></tr>
	<tr><th>이름</th><td><input type="text"  size="20" name="name" value="${dto.name }">*</td></tr>
	<tr><th>전화번호</th><td>
		<input type="text" name="phone1" size="5" value="${dto.phone1}">-
		<input type="text" name="phone2" size="7" value="${dto.phone2}">-
		<input type="text" name="phone3" size="7" value="${dto.phone3}"></td></tr>
	<tr><th>이메일</th><td><input type="text"  size="20" name="email" 
		value="${dto.email }"></td></tr>
</table><br><br>
	<input type="submit" value="수정">
	<input type="reset" value="다시작성" >
	<input type="button" value="목록으로" onclick="location.href='main'">
</form>
</div>
</body>
</html>