<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin account</title>
    <link rel="stylesheet" href="../../styles/main.css" type="text/css"/>
</head>
<body>
<jsp:include page="../jspf/header.jsp"/>
<h2>Admin:</h2>
<p>Name: ${client.name}<br>
    email: ${client.clientCredentials.email}<br>
    Discount: ${client.discount}
</p>
<div class="showAddTourForm">
    <form action="/travelAgency/">
        <input type="hidden" name="command" value="showAddTour">
        <input type="submit" value="Add tour">
    </form>
</div>

<h2>All Tours:</h2>
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
            <td><a href="${pageContext.request.contextPath}?command=tourDetails&tourId=${tour.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
