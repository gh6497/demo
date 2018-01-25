<%--
  Created by IntelliJ IDEA.
  User: caiha
  Date: 2018/1/23
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="pic" id="">
    <input type="submit" value="上传">
</form>
</body>
</html>
