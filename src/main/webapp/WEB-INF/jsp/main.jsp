<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main Travel Agency</title>
    <link rel="stylesheet" href="../../styles/main.css" type="text/css"/>
</head>
<body>
<jsp:include page="../jspf/header.jsp"/>
<h2> Here are all tours:</h2>
<div class="tourTypeForm">
    <form action="/travelAgency/">
        <input type="hidden" name="command" value="showByTourType">

        <label for="type">Show By Type</label>
        <select id="type" name="type">
            <option value="${type}" selected hidden>${type}</option>
            <option value="VACATION">Vacation</option>
            <option value="EXCURSION">Excursion</option>
            <option value="SHOPPING">Shopping</option>
        </select>

        <input type="submit" value="Filter">
    </form>
</div>
<div class="showAllForm">
    <form action="/travelAgency/">
        <input type="hidden" name="command" value="showMain">
        <input type="submit" value="Show All">
    </form>
</div>
<table id="tours">
    <tr>
        <th>Title</th>
        <th>Type</th>
        <th>Price</th>
        <th>Discount</th>
        <th>Hot</th>
        <th></th>
    </tr>
    <c:forEach items="${tours}" var="tour">
        <tr>
            <td><c:out value="${tour.title}"/></td>
            <td><c:out value="${tour.type}"/></td>
            <td><ctg:pricetag price="${tour.price}"/></td>
            <td><ctg:discounttag discount="${tour.discount}"/></td>
            <td><c:out value="${tour.hot}"/></td>
            <td><a href="/travelAgency/?command=orderApproval&tourId=${tour.id}">Order</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>