<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Tour</title>
    <link href="../../styles/form.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form">
<form action="/travelAgency/confirmEdit">
    <input type="hidden" name="command" value="confirmEdit">
    <input type="hidden" name="id" value="${tour.id}">

    <label for="title">Title</label>
    <input type="text" id="title" name="title" placeholder="${tour.title}">

    <label for="destination">Destination</label>
    <input type="text" id="destination" name="destination" placeholder="${tour.destination}">

    <label for="description">Description</label>
    <input type="text" id="description" name="description" placeholder="${tour.description}">

    <label for="duration">Duration</label>
    <input type="text" id="duration" name="duration" placeholder="${tour.duration}">

    <label for="start">Starts</label>
    <input type="date" id="start" name="start" value="${tour.start}">

    <label for="type">Type</label>
    <select id="type" name="type">
        <option value="" selected disabled hidden>${tour.type}</option>
        <option value="VACATION">Vacation</option>
        <option value="EXCURSION">Excursion</option>
        <option value="SHOPPING">Shopping</option>
    </select>

    <label for="hot">Hot</label>
    <select id="hot" name="hot">
        <option value="" selected disabled hidden>${tour.hot}</option>
        <option value="true">true</option>
        <option value="false">false</option>
    </select>

    <label for="price">Price</label>
    <input type="text" id="price" name="price" placeholder="${tour.price}">

    <label for="enabled">Enabled</label>
    <select id="enabled" name="enabled">
        <option value="" selected disabled hidden>${tour.enabled}</option>
        <option value="true">true</option>
        <option value="false">false</option>
    </select>

    <label for="discount">Discount</label>
    <input type="text" id="discount" name="discount" placeholder="${tour.discount}">

    <input type="submit" value="Submit">
</form>
</div>
</body>
</html>
