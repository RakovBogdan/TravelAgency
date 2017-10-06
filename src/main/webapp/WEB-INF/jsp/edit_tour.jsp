<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit Tour</title>
    <link rel="stylesheet" href="../../styles/main.css" type="text/css"/>
</head>
<body>
<jsp:include page="../jspf/header.jspf"/>
<div class="form">
<form action="${pageContext.request.contextPath}">
    <input type="hidden" name="command" value="confirmEdit">
    <input type="hidden" name="id" value="${tour.id}">

    <label for="title">Title</label>
    <input type="text" id="title" name="title" value="${tour.title}">

    <label for="destination">Destination</label>
    <input type="text" id="destination" name="destination" value="${tour.destination}">

    <label for="description">Description</label>
    <input type="text" id="description" name="description" value="${tour.description}">

    <label for="duration">Duration</label>
    <input type="text" id="duration" name="duration" value="${tour.duration}">

    <label for="start">Starts</label>
    <input type="date" id="start" name="start" value="${tour.start}">

    <label for="type">Type</label>
    <select id="type" name="type">
        <option value="${tour.type}" selected hidden>${tour.type}</option>
        <option value="VACATION">Vacation</option>
        <option value="EXCURSION">Excursion</option>
        <option value="SHOPPING">Shopping</option>
    </select>

    <label for="hot">Hot</label>
    <select id="hot" name="hot">
        <option value="${tour.hot}" selected hidden>${tour.hot}</option>
        <option value="true">true</option>
        <option value="false">false</option>
    </select>

    <label for="price">Price</label>
    <input type="text" id="price" name="price" value="${tour.price}">

    <label for="enabled">Enabled</label>
    <select id="enabled" name="enabled">
        <option value="${tour.enabled}" selected hidden>${tour.enabled}</option>
        <option value="true">true</option>
        <option value="false">false</option>
    </select>

    <label for="discount">Discount</label>
    <input type="text" id="discount" name="discount" value="${tour.discount}">

    <input type="submit" value="Submit">
</form>
</div>
</body>
</html>
