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
    <title><fmt:message key="cart.title"/></title>
</head>
<body>

    <jsp:include page="header.jsp"/>

    <div class="container">
        <c:if test="${empty sessionScope.order && sessionScope.order.products == null}">
            <div class="order-item mt-3">
                <p class="h5">Cart is empty</p><hr>
            </div>
        </c:if>
        <c:if test="${!empty sessionScope.order && sessionScope.order.products != null}">
            <div class="order-item mt-3">
                <p class="h5">Cart</p><hr>
                <c:forEach var="product" items="${sessionScope.order.products}">
                    <div class="row order-item">
                        <div class="card mb-3 p-3">
                            <div class="row no-gutters">
                                <div class="col-md-1">
                                    <img src="<c:url value="/resources/img/product/${product.category.name}/${product.image}"/>" class="card-img" alt="${product.category.name}">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <a href=""><h5 class="card-title">${product.name}</h5></a>
                                        <p class="card-text">${product.description}</p>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="row number">
                                        <form action="main">
                                        <input type="hidden" name="command" value="change-product-count">
                                        <input type="hidden" name="product-id" value="${product.id}">
                                        <button type="submit" class="btn btn-outline-secondary btn-sm" onclick="this.nextElementSibling.stepDown(); this.nextElementSibling.onchange();">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-dash-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path fill-rule="evenodd" d="M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8z"/>
                                            </svg>
                                        </button>
                                        <input type="number" name="count" value="${product.quantity}" min="1" max="50" size="2" readonly>
                                        <button type="submit" class="btn btn-outline-secondary btn-sm" onclick="this.previousElementSibling.stepUp(); this.previousElementSibling.onchange();">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
                                                <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
                                            </svg>
                                        </button>
                                        </form>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <p><strong>${product.price} ${product.currency.code}</strong></p>
                                </div>
                                <div class="col-md-1">
                                    <form action="main">
                                        <input type="hidden" name="command" value="remove-cart-product">
                                        <input type="hidden" name="product-id" value="${product.id}">
                                        <button type="submit" class="btn btn-primary">Remove</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <p><b>Totally price: ${order.amount} ${order.currency.code}</b></p>
                <form action="main">
                    <input type="hidden" name="command" value="place-order">
                    <button type="submit" class="btn btn-primary">Place order</button>
                </form>
            </div>
        </c:if>
    </div>

<%--    <jsp:include page="footer.jsp"/>--%>

<script src="<c:url value="/resources/js/main.js"/>"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>