<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Board Write Form</title>
      <link rel="stylesheet" type="text/css" href="static/css/board.css">
      <script type="text/javascript" src="static/script/board.js"></script>
   </head>
   <body>
      <div id="wrap" align="center">
         <h1>게시글 등록</h1>
         <form action="boardWrite" method="post" name="frm" enctype="multipart/form-data">
            <table>
               <tr>
                  <th>작성자</th>
                  <td>${loginUser.id} <input type="hidden" name="userid" value="${loginUser.id}"></td>
               </tr>
               <tr>
                  <th>비밀번호</th>
                  <td><input type="password" name="pass"> * 필수(게시물 수정 삭제시 필요합니다.)</td>
               </tr>
               <tr>
                  <th>이메일</th>
                  <td>${loginUser.email} <input type="hidden" name="email" value="${loginUser.email}"></td>
               </tr>
               <tr>
                  <th>제목</th>
                  <td><input type="text" size="70" name="title" value="${dto.title}"> * 필수</td>
               </tr>
               <tr>
                  <th>내용</th>
                  <td><textarea cols="70" rows="15" name="content">${dto.content}</textarea></td>
               </tr>
               <tr>
                  <th>이미지</th>
                  <td><input type="file" name="image"></td>
               </tr>
            </table><br><br>
            <input type="submit" value="등록" onclick="return boardCheck();">
            <input type="reset" value="다시 작성">
            <input type="button" value="목록" onclick="location.href='main'">
         </form><br>
         <div id="message"></div>
      </div>
   </body>
</html>