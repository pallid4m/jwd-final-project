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
    <title>Orders</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" href="main?command=profile-page">Profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="main?command=order-page">Orders</a>
        </li>
    </ul>
    <c:forEach var="order" items="${requestScope.orders}">
        <p>Order</p>
        <div class="row order-item border m-2">
            <div class="container m-2">
                <div class="order-item mt-3">
                    <c:forEach var="product" items="${order.products}">
                        <div class="row order-item">
                            <div class="card mb-3 p-3">
                                <div class="row no-gutters">
                                    <div class="col-md-1">
                                        <img src="<c:url value="/resources/img/product/${product.category.name}/${product.image}"/>" class="card-img" alt="${product.category.name}">
                                    </div>
                                    <div class="col-md-9">
                                        <div class="card-body">
                                            <a href=""><h5 class="card-title">${product.name}</h5></a>
                                            <p class="card-text">${product.description}</p>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <p><strong>${product.price} ${product.currency.code}</strong></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="row">
                        <fmt:parseDate value="${order.createDate}" type="date" pattern="y-M-dd'T'H:m" var="parsedDate"/>
                        <div class="col"><b>date: <fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm"/></b></div>
                        <div class="col"><b>status: ${order.orderStatus.state}</b></div>
                        <div class="col"><b>amount: ${order.amount} ${order.currency.code}</b></div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<%--<jsp:include page="footer.jsp"/>--%>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>