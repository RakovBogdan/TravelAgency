<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Travel Agency</title>
    <link rel="stylesheet" href="../../styles/main.css" type="text/css"/>
</head>
<body>
<jsp:include page="../jspf/header.jsp"/>
<h1>Login to Travel Agency</h1>
<p>To make orders, enter your email address and password below.</p>
<h4 style="text-align: center">${message}</h4>
<div class="form">
<form method="post" action="/travelAgency/">
    <input type="hidden" name="command" value="login">
    <label class="pad_top">Email:</label>
    <input type="email" name="email" required><br>
    <label class="pad_top">Password:</label>
    <input type="password" name="password" required><br>
    <label>&nbsp;</label>
    <input type="submit" value="Login" class="margin_left">
</form>
</div>
<p> Don't have account? <a href="/travelAgency/register?command=showRegister">Register</a></p>
</body>
</html>