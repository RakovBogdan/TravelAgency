<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en_US'}" scope="session"/>
<fmt:setBundle basename="language" var="bundle"/>
<ul class="navbar">
    <li><a class="active" href="/travelAgency/main?command=showMain">
        <fmt:message key="header.mainButton" bundle="${bundle}"/>
    </a></li>
    <li><a href="/travelAgency/?command=showMyAccount">
        <fmt:message key="header.myAccountButton" bundle="${bundle}"/>
    </a></li>
    <li style="float: right">
        <form method="post" action="/travelAgency/">
            <input type="hidden" name="command" value="changeLanguage">
            <select name="language" onchange="this.form.submit()">
                <option value="en_US" ${sessionScope.language == 'en_US' ? 'selected' : ''}> Eng </option>
                <option value="ua" ${sessionScope.language == 'ua' ? 'selected' : ''}> UA </option>
            </select>
        </form>
    </li>
    <li style="float: right;">
        <c:choose>
            <c:when test="${not empty client}">
                <a href="/travelAgency/logout?command=logout">
                    <fmt:message key="header.logoutButton" bundle="${bundle}"/>
                </a>
            </c:when>
            <c:otherwise>
                <a href="/travelAgency/login?command=login">
                    <fmt:message key="header.loginButton" bundle="${bundle}"/>
                </a>
            </c:otherwise>
        </c:choose>
    </li>
</ul>