<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/validation.tld" %>
<c:if test="${!empty sessionScope.lang}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>
<fmt:setBundle basename="messages"/>
<!doctype html>
<html lang="<fmt:message key="html.lang"/>">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>">
    <title><fmt:message key="sign_in.title"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>

    <form class="sign-in" action="main" method="post">
        <input type="hidden" name="command" value="sign-in">
        <div class="form-group">
            <label class="sr-only" for="inputEmail"><fmt:message key="form.email"/></label>
            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="<fmt:message key="form.email"/>" autofocus=""/>
            <tag:validate path="email"/>
        </div>
        <div class="form-group">
            <label class="sr-only" for="inputPassword"><fmt:message key="form.password"/></label>
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="<fmt:message key="form.password"/>" required=""/>
            <tag:validate path="password"/>
        </div>
        <tag:validate path="userNotFound"/>
        <tag:validate path="auth"/>
        <tag:validate path="userAlreadyExist"/>
        <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">
        <button type="submit" class="btn btn-primary"><fmt:message key="sign_in.button"/></button>
    </form>

<script src="<c:url value="/resources/js/main.js"/>"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>