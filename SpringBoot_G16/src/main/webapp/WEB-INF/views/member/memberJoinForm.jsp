<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/board.css">
<script type="text/javascript" src="/script/board.js"></script>
</head>
<body>
<div id ="warp" align="center">
<h1>사용자등록</h1>
<form name="frm" method="post" action="memberJoin">
<table>
	<tr><th>아이디</th><td><input type="text" name="id" size="20" value="${dto.id }">*
		<input type="button" value="중복체크" onclick="idCheck();">
		<input type="hidden" name="re_id" value="${re_id }"></td></tr>
	<tr><th>암호</th><td><input type="password" name="pw" size="20">*</td></tr>
	<tr><th>확인</th><td><input type="password" name="pw_check" size="20">*</td></tr>
	<tr><th>이름</th><td><input type="text" name="name" value="${dto.name }">*</td></tr>
	<tr><th>전화번호</th><td><input type="text" name="phone1" size="5" value="${dto.phone1}">-
		<input type="text" name="phone2" size="7" value="${dto.phone2}">-
		<input type="text" name="phone3" size="7" value="${dto.phone3}">*</td></tr>
	<tr><th>이메일</th><td><input type="text" name="email" size="20" value="${dto.email}">*</td></tr>
</table><br><br>
<input type="submit" value="등록" ><input type="reset" value="다시작성">
<input type="button" value="로그인페이지로" onclick="history.go(-1);"><br><br>
${message }
</form></div>
</body>
</html>