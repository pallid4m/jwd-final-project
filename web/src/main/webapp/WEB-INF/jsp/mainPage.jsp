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
    <title><fmt:message key="main.title"/></title>
</head>
<body>

    <jsp:include page="header.jsp"/>

    <div class="container">
        <h3 class="pt-3">Low price</h3>
        <c:forEach var="product" items="${requestScope.products}">
            <div class="row">
                <div class="card-deck">
                    <div class="col mb-3">
                        <div class="card">
                            <div class="col-md-2" style="max-width: 100px;">
                                <img class="card-img" src="<c:url value="/resources/img/product/${product.category.name}/${product.image}"/>" alt="${product.category.name}">
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${product.name}</h5>
                                <div class="">
                                    <div class="btn-group">
                                        <button class="btn btn-sm btn-outline-secondary" type="button">View</button>
                                        <form action="main">
                                            <input type="hidden" name="command" value="add-product-to-cart">
                                            <input type="hidden" name="product-id" value="${product.id}">
                                            <button class="btn btn-sm btn-outline-primary" type="submit"><fmt:message key="action.add.to.cart"/></button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted"><b>Price ${product.price} ${product.currency.code}</b></small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

<%--        <c:forEach var = "i" begin="1" end = "5">--%>
<%--            <div class="row">--%>
<%--                <div class="card-deck">--%>
<%--                    <c:forEach var = "i" begin="1" end = "4">--%>
<%--                        <div class="col mb-3">--%>
<%--                            <div class="card">--%>
<%--                                <img src="..." class="card-img-top" alt="...">--%>
<%--                                <div class="card-body">--%>
<%--                                    <c:if test="${!empty products}">--%>
<%--                                        <h5 class="card-title">${product.name}</h5>--%>
<%--                                        <p class="card-text">${product.description}</p>--%>
<%--                                    </c:if>--%>
<%--                                    <c:if test="${empty products}">--%>
<%--                                        <h5 class="card-title">Card title</h5>--%>
<%--                                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                                    </c:if>--%>
<%--                                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>--%>
<%--                                    <div class="">--%>
<%--                                        <div class="btn-group">--%>
<%--                                            <button class="btn btn-sm btn-outline-secondary" type="button">View</button>--%>
<%--                                            <button class="btn btn-sm btn-outline-secondary" type="button">Edit</button>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="card-footer">--%>
<%--                                    <small class="text-muted">Price</small>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </c:forEach>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
    </div>

    <jsp:include page="footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>