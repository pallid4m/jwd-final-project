<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

    <form class="sign-in" action="main?command=sign-in" method="post">
        <div class="form-group">
            <label class="sr-only" for="inputEmail"><fmt:message key="form.email"/></label>
            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="<fmt:message key="form.email"/>" required="" autofocus=""/>
        </div>
        <div class="form-group">
            <label class="sr-only" for="inputPassword"><fmt:message key="form.password"/></label>
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="<fmt:message key="form.password"/>" required=""/>
        </div>
        <div class="form-group form-check">
            <input type="checkbox" name="remember-me"  class="form-check-input" id="rememberCheck">
            <label class="form-check-label" for="rememberCheck"><fmt:message key="form.remember_me"/></label>
        </div>
        <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">
        <button type="submit" class="btn btn-primary"><fmt:message key="sign_in.button"/></button>
    </form>

    <jsp:include page="footer.jsp"/>

<script src="<c:url value="/resources/js/main.js"/>"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>