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
    <title>Profile</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="main?command=profile-page">Profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="main?command=order-page">Orders</a>
        </li>
    </ul>

    <div class="profile">
        <div class="pb-5">
            <p class="h5">Change email</p><hr>
            <form class="col-md-6" action="" method="post">
                <div class="form-group input-group-sm">
                    <label class="" for="inputEmail">Email</label>
                    <input type="email" name="email" value="${sessionScope.user.email}" id="inputEmail" class="form-control" placeholder="" required="" autofocus=""/>
                </div>
                <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">
                <button type="submit" class="btn btn-outline-dark btn-sm">Update email</button>
            </form>
        </div>
        <div class="pb-5">
            <p class="h5">Change password</p><hr>
            <form class="col-md-6" action="" method="post" autocomplete="off">
                <div class="form-group input-group-sm">
                    <label class="" for="inputOldPassword">Old password</label>
                    <input type="password" name="old-password" id="inputOldPassword" class="form-control" placeholder="" required=""/>
                </div>
                <div class="form-group input-group-sm">
                    <label class="" for="inputNewPassword">New password</label>
                    <input type="password" name="new-password" id="inputNewPassword" class="form-control" placeholder="" required=""/>
                </div>
                <div class="form-group input-group-sm">
                    <label class="" for="inputConfirmNewPassword">Confirm new password</label>
                    <input type="password" name="confirm-new-password" id="inputConfirmNewPassword" class="form-control" placeholder="" required=""/>
                </div>
                <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">
                <button type="submit" class="btn btn-outline-dark btn-sm">Update password</button>
            </form>
        </div>
        <div class="pb-5">
            <p class="h5">Personal data</p><hr>
            <form class="col-md-6" action="" method="post">
                <div class="form-group input-group-sm">
                    <label class="" for="inputPhone">Phone</label>
                    <input type="tel" name="phone" value="${sessionScope.user.phone}" id="inputPhone" class="form-control" placeholder="" required=""/>
                </div>
                <div class="form-group input-group-sm">
                    <label class="" for="inputFirstName">First Name</label>
                    <input type="text" name="first-name" value="${sessionScope.user.firstName}" id="inputFirstName" class="form-control" placeholder="" required=""/>
                </div>
                <div class="form-group input-group-sm">
                    <label class="" for="inputLastName">Last Name</label>
                    <input type="text" name="last-name" value="${sessionScope.user.lastName}" id="inputLastName" class="form-control" placeholder="" required=""/>
                </div>
                <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">
                <button type="submit" class="btn btn-outline-dark btn-sm">Update profile</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>