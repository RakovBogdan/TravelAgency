<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account</title>
    <link rel="stylesheet" href="../../styles/main.css" type="text/css"/>
</head>
<body>
<jsp:include page="../jspf/header.jspf"/>
<h1>Your Account:</h1>
<p>${client.name}, ${client.clientCredentials.email}, Discount: ${client.discount}</p>
<table id="orders">
    <tr>
        <th>Tour</th>
        <th>Amount</th>
        <th>Date</th>
        <th>Status</th>
        <th>Payment</th>
        <th></th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td><c:out value="${order.tour.title}"/></td>
            <td><c:out value="${order.toursAmount}"/></td>
            <td><c:out value="${order.date}"/></td>
            <td><c:out value="${order.status}"/></td>
            <td><c:out value="${payment}"/></td>
            <td><a href="${pageContext.request.contextPath}?command=payOrder&tourId=${order.id}">Pay</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>