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
<jsp:include page="../jspf/header.jspf"/>
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
            <td><a href="/travelAgency/?command=orderApproval&tourId=${tour.id}">Order</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>