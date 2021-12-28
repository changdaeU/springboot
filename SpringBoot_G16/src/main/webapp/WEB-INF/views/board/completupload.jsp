<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/board.css">
<script type="text/javascript">
window.opener.frm.imgfilename.value='${image}';
window.opener.frm.image.value='${image}';
self.close();
</script>

</head>
<body>

</body>
</html>