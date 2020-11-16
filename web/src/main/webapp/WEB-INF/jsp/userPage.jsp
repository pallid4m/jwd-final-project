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
    <title><fmt:message key="user.title"/></title>
</head>
<body>

    <jsp:include page="header.jsp"/>

    <div class="container">
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link" href="main?command=profile-page">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="main?command=order-page">Orders</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Payment</a>
            </li>
        </ul>
    </div>

<%--    <div class="container">--%>

<%--&lt;%&ndash;        <form action="main?command=edit-user" method="post">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <label class="sr-only" for="inputEmail"><fmt:message key="form.email"/></label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <input type="email" name="email" id="inputEmail" class="form-control" placeholder="${sessionScope.user.email}" required="" autofocus=""/>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <label class="sr-only" for="inputPassword"><fmt:message key="form.password"/></label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="<fmt:message key="form.password"/>" required=""/>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <button type="submit" class="btn btn-primary">Edit</button>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </form>&ndash;%&gt;--%>

<%--        <c:if test="${!empty sessionScope.user}">--%>
<%--            <p>id: <c:out value="${sessionScope.user.id}"/></p>--%>
<%--            <p>email: <c:out value="${sessionScope.user.email}"/></p>--%>
<%--            <p>password: <c:out value="${sessionScope.user.password}"/></p>--%>
<%--            <p>phone: <c:out value="${sessionScope.user.phone}"/></p>--%>
<%--            <p>create date: <c:out value="${sessionScope.user.createDate}"/></p>--%>
<%--            <p>first name: <c:out value="${sessionScope.user.firstName}"/></p>--%>
<%--            <p>last name: <c:out value="${sessionScope.user.lastName}"/></p>--%>
<%--            <p>image: <c:out value="${sessionScope.user.image}"/></p>--%>
<%--        </c:if>--%>
<%--    </div>--%>

<%--    <div class="container">--%>
<%--        <ul class="nav nav-tabs" id="myTab" role="tablist">--%>
<%--            <li class="nav-item" role="presentation">--%>
<%--                <a class="nav-link active" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item" role="presentation">--%>
<%--                <a class="nav-link" id="orders-tab" data-toggle="tab" href="#orders" role="tab" aria-controls="orders" aria-selected="false">Orders</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item" role="presentation">--%>
<%--                <a class="nav-link" id="payment-tab" data-toggle="tab" href="#payment" role="tab" aria-controls="payment" aria-selected="false">Payment</a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--        <div class="tab-content" id="myTabContent">--%>
<%--            <div class="tab-pane fade how active" id="profile" role="tabpanel" aria-labelledby="profile-tab">--%>
<%--                <div class="pb-5">--%>
<%--                    <p class="h5">Change email</p><hr>--%>
<%--                    <form class="col-md-6" action="" method="post">--%>
<%--                        <div class="form-group input-group-sm">--%>
<%--                            <label class="" for="inputEmail">Email</label>--%>
<%--                            <input type="email" name="email" value="${sessionScope.user.email}" id="inputEmail" class="form-control" placeholder="" required="" autofocus=""/>--%>
<%--                        </div>--%>
<%--                        <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">--%>
<%--                        <button type="submit" class="btn btn-outline-dark btn-sm">Update email</button>--%>
<%--                    </form>--%>
<%--                </div>--%>

<%--                <div class="pb-5">--%>
<%--                    <p class="h5">Change password</p><hr>--%>
<%--                    <form class="col-md-6" action="" method="post" autocomplete="off">--%>
<%--                        <div class="form-group input-group-sm">--%>
<%--                            <label class="" for="inputOldPassword">Old password</label>--%>
<%--                            <input type="password" name="old-password" id="inputOldPassword" class="form-control" placeholder="" required=""/>--%>
<%--                        </div>--%>
<%--                        <div class="form-group input-group-sm">--%>
<%--                            <label class="" for="inputNewPassword">New password</label>--%>
<%--                            <input type="password" name="new-password" id="inputNewPassword" class="form-control" placeholder="" required=""/>--%>
<%--                        </div>--%>
<%--                        <div class="form-group input-group-sm">--%>
<%--                            <label class="" for="inputConfirmNewPassword">Confirm new password</label>--%>
<%--                            <input type="password" name="confirm-new-password" id="inputConfirmNewPassword" class="form-control" placeholder="" required=""/>--%>
<%--                        </div>--%>
<%--                        <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">--%>
<%--                        <button type="submit" class="btn btn-outline-dark btn-sm">Update password</button>--%>
<%--                    </form>--%>
<%--                </div>--%>

<%--                <div class="pb-5">--%>
<%--                    <p class="h5">Personal data</p><hr>--%>
<%--                    <form class="col-md-6" action="" method="post">--%>
<%--                        <div class="form-group input-group-sm">--%>
<%--                            <label class="" for="inputPhone">Phone</label>--%>
<%--                            <input type="tel" name="phone" value="${sessionScope.user.phone}" id="inputPhone" class="form-control" placeholder="" required=""/>--%>
<%--                        </div>--%>
<%--                        <div class="form-group input-group-sm">--%>
<%--                            <label class="" for="inputFirstName">First Name</label>--%>
<%--                            <input type="text" name="first-name" value="${sessionScope.user.firstName}" id="inputFirstName" class="form-control" placeholder="" required=""/>--%>
<%--                        </div>--%>
<%--                        <div class="form-group input-group-sm">--%>
<%--                            <label class="" for="inputLastName">Last Name</label>--%>
<%--                            <input type="text" name="last-name" value="${sessionScope.user.lastName}" id="inputLastName" class="form-control" placeholder="" required=""/>--%>
<%--                        </div>--%>
<%--                        <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">--%>
<%--                        <button type="submit" class="btn btn-outline-dark btn-sm">Update profile</button>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <div class="tab-pane fade" id="orders" role="tabpanel" aria-labelledby="orders-tab">--%>
<%--                <p>orders content</p>--%>
<%--            </div>--%>

<%--            <div class="tab-pane fade" id="payment" role="tabpanel" aria-labelledby="payment-tab">--%>
<%--                <p>payment content</p>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

    <jsp:include page="footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>