<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en_US'}" scope="session"/>
<fmt:setBundle basename="language" var="bundle"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="main.title" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="../../styles/main.css" type="text/css"/>
</head>
<body>
<jsp:include page="../jspf/header.jsp"/>
<h2><fmt:message key="main.header" bundle="${bundle}"/></h2>
<div class="tourTypeForm">
    <form action="/travelAgency/">
        <input type="hidden" name="command" value="showByTourType">

        <label for="type"><fmt:message key="main.label.showByType" bundle="${bundle}"/></label>
        <select id="type" name="type">
            <option value="VACATION" ${type == 'VACATION' ? 'selected' : ''}>
                <fmt:message key="main.vacation" bundle="${bundle}"/>
            </option>
            <option value="EXCURSION" ${type == 'EXCURSION' ? 'selected' : ''}>
                <fmt:message key="main.excursion" bundle="${bundle}"/>
            </option>
            <option value="SHOPPING" ${type == 'SHOPPING' ? 'selected' : ''}>
                <fmt:message key="main.shopping" bundle="${bundle}"/>
            </option>
        </select>

        <input type="submit" value="<fmt:message key="main.button.filter" bundle="${bundle}"/>">
    </form>
</div>
<div class="showAllForm">
    <form action="/travelAgency/">
        <input type="hidden" name="command" value="showMain">
        <input type="submit" value="<fmt:message key="main.button.showAll" bundle="${bundle}"/>">
    </form>
</div>
<table id="tours">
    <tr>
        <th><fmt:message key="main.table.tour.title" bundle="${bundle}"/></th>
        <th><fmt:message key="main.table.tour.type" bundle="${bundle}"/></th>
        <th><fmt:message key="main.table.tour.price" bundle="${bundle}"/></th>
        <th><fmt:message key="main.table.tour.discount" bundle="${bundle}"/></th>
        <th><fmt:message key="main.table.tour.hot" bundle="${bundle}"/></th>
        <th></th>
    </tr>
    <c:forEach items="${tours}" var="tour">
        <tr>
            <td><c:out value="${tour.title}"/></td>
            <td><c:out value="${tour.type}"/></td>
            <td><ctg:pricetag price="${tour.price}"/></td>
            <td><ctg:discounttag discount="${tour.discount}"/></td>
            <td><c:out value="${tour.hot}"/></td>
            <td><a href="/travelAgency/?command=orderApproval&tourId=${tour.id}">
                <fmt:message key="main.link.order" bundle="${bundle}"/>
            </a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>