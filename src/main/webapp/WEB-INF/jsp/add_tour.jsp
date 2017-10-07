<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit Tour</title>
    <link rel="stylesheet" href="../../styles/main.css" type="text/css"/>
</head>
<body>
<jsp:include page="../jspf/header.jsp"/>
<div class="form">
<form action="${pageContext.request.contextPath}">
    <input type="hidden" name="command" value="addTour">

    <label for="title">Title</label>
    <input type="text" id="title" name="title" value="">

    <label for="destination">Destination</label>
    <input type="text" id="destination" name="destination" value="">

    <label for="description">Description</label>
    <input type="text" id="description" name="description" value="">

    <label for="duration">Duration</label>
    <input type="text" id="duration" name="duration" value="">

    <label for="start">Starts</label>
    <input type="date" id="start" name="start" value="">

    <label for="type">Type</label>
    <select id="type" name="type">
        <option value="VACATION">Vacation</option>
        <option value="EXCURSION">Excursion</option>
        <option value="SHOPPING">Shopping</option>
    </select>

    <label for="hot">Hot</label>
    <select id="hot" name="hot">
        <option value="true">true</option>
        <option value="false">false</option>
    </select>

    <label for="price">Price</label>
    <input type="text" id="price" name="price" value="">

    <label for="enabled">Enabled</label>
    <select id="enabled" name="enabled">
        <option value="true">true</option>
        <option value="false">false</option>
    </select>

    <label for="discount">Discount</label>
    <input type="text" id="discount" name="discount" value="">

    <input type="submit" value="Add">
</form>
</div>
</body>
</html>
