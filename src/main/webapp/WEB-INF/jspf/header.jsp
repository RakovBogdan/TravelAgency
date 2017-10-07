<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="navbar">
    <li><a class="active" href="/travelAgency/main?command=showMain">All Tours</a></li>
    <li><a href="/travelAgency/?command=showMyAccount">My Account</a></li>
    <li style="float: right;">
        <c:choose>
            <c:when test="${not empty client}">
                <a href="/travelAgency/logout?command=logout">Logout</a></li>
            </c:when>
            <c:otherwise>
                <a href="/travelAgency/login?command=login">Login</a></li>
            </c:otherwise>
        </c:choose>
</ul>