<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main Travel Agency</title>
    <link rel="stylesheet" href="../../styles/main.css" type="text/css"/>
</head>
<body>
<ul class="navbar">
    <li><a class="active" href="/travelAgency/">Home</a></li>
    <li><a href="#news">News</a></li>
    <li><a href="#contact">Contact</a></li>
    <li style="float: right;"><a href="/travelAgency/login?command=login">Login</a></li>
</ul>
<h1> Here are all tours:</h1>
<table id="tours">
    <tr>
        <th>Title</th>
        <th>Price</th>
        <th></th>
    </tr>
    <c:forEach items="${tours}" var="tour">
        <tr>
            <td><c:out value="${tour.title}"/></td>
            <td><c:out value="${tour.price}"/></td>
            <td><a href="travelAgency/editTour?command=orderApproval&tourId=${tour.id}">Order</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>