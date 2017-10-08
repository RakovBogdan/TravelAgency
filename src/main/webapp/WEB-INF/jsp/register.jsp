<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Travel Agency</title>
    <link rel="stylesheet" href="../../styles/main.css" type="text/css"/>
</head>
<body>
<jsp:include page="../jspf/header.jsp"/>
<h4 style="text-align: center">${message}</h4>
<div class="form">
    <form action="${pageContext.request.contextPath}">
        <input type="hidden" name="command" value="register">

        <label for="email">Email</label>
        <input type="email" id="email" name="email">

        <label for="name">Name</label>
        <input type="text" id="name" name="name">

        <label for="password">Password</label>
        <input type="password" id="password" name="password">

        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>