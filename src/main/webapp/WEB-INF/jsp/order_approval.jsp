<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <title>Tour Order</title>
    <link rel="stylesheet" href="../../styles/main.css" type="text/css"/>
</head>
<body>
<jsp:include page="../jspf/header.jsp"/>
<div style="overflow-x: auto">
<table>
    <tr>
        <th>Title</th>
        <th>Price</th>
        <th>Destination</th>
        <th>Description</th>
        <th>Duration</th>
        <th>Start</th>
        <th>Type</th>
        <th>Discount</th>
    </tr>
    <tr>
        <td><c:out value="${tour.title}"/></td>
        <td><ctg:pricetag price="${tour.price}"/></td>
        <td><c:out value="${tour.destination}"/></td>
        <td><c:out value="${tour.description}"/></td>
        <td><c:out value="${tour.duration}"/></td>
        <td><c:out value="${tour.start}"/></td>
        <td><c:out value="${tour.type}"/></td>
        <td><ctg:discounttag discount="${tour.discount}"/></td>
    </tr>
</table>
</div>
<div class="orderForm">
<form action="${pageContext.request.contextPath}">
    <input type="hidden" name="command" value="makeOrder">
    <input type="hidden" name="tourId" value="${tour.id}">

    <label for="toursAmount">Tours Amount</label>
    <input type="text" id="toursAmount" name="toursAmount" value="1">

    <label for="payment">Payment for 1 tour: <ctg:pricetag price="${payment}"/></label>
    <input id="payment" type="submit" value="Order">
</form>
</div>
</body>
</html>
