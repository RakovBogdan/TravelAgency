<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin account</title>
    <link rel="stylesheet" href="../../styles/table.css" type="text/css"/>

</head>
<body>
<h1>Admin:</h1>
<p>Name: ${client.name}<br>
    email: ${client.clientCredentials.email}<br>
    Discount: ${client.discount}
</p>

<h1>All Tours:</h1>
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
            <td><a href="travelAgency/editTour?command=tourDetails&tourId=${tour.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
