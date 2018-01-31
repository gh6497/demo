<%--
  Created by IntelliJ IDEA.
  User: caiha
  Date: 2018/1/30
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <%-- 引入bootstrap css--%>
    <link rel="stylesheet" href="/libs/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/libs/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
<form class="bs-example bs-example-form" data-example-id="simple-input-groups" action="/users/login" method="post">
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1">用户名</span>
        <input type="text" class="form-control" name="username"  aria-describedby="basic-addon1">
    </div>
    <br>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon2">密码</span>
        <input type="text" class="form-control" name="password"  aria-describedby="basic-addon2">
    </div>
    <br>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon3">https://example.com/users/</span>
        <input type="submit" class="form-control" value="登陆" aria-describedby="basic-addon3">
    </div>
</form>
<script src="/libs/jquery/jquery.min.js"></script>
<%-- 引入bootstrap js--%>
<script src="/libs/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
