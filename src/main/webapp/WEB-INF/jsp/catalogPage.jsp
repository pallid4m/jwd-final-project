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
    <title><fmt:message key="catalog.title"/></title>
</head>
<body>

    <jsp:include page="header.jsp"/>

    <div class="container-fluid catalog">
        <div class="row">
            <div class="col-md-2">
                <nav class="nav flex-column">
                    <a class="nav-link active" href="<c:url value="main?command=catalog-page&category=phone"/>"><fmt:message key="catalog.phones"/></a>
                    <a class="nav-link" href="<c:url value="main?command=catalog-page&category=laptop"/>"><fmt:message key="catalog.laptops"/></a>
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                </nav>
            </div>
            <div class="col-md-10">
                <c:if test="${!empty products}">
                    <c:forEach var="product" items="${products}">
                        <div class="card mb-3">
                            <div class="row card-item">
                                <div class="col-md-2" style="max-width: 540px;">
                                    <img class="card-img" src="<c:url value="/resources/img/product/${product.image}"/>" alt="${product.category.name}">
                                </div>
                                <div class="col-md-10">
                                    <div class="card-body">
                                        <a href="<c:url value="main?command=product&id=${product.id}"/>"><h5 class="card-title">${product.name}</h5></a>
                                        <p class="card-text">${product.description}</p>
                                        <p><strong>300.00 rub</strong></p>
<%--                                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>--%>
<%--                                        <div class="btn-group">--%>
<%--                                            <button class="btn btn-sm btn-outline-secondary" type="button">View</button>--%>
<%--                                            <button class="btn btn-sm btn-outline-secondary" type="button">Edit</button>--%>
<%--                                        </div>--%>
                                        <button class="btn btn-sm btn-outline-primary" type="button">Add</button>
                                    </div>
                                </div>
                            </div>
<%--                            <div class="card-footer">--%>
<%--                                <small class="text-muted">Price</small>--%>
<%--                                <small class="text-muted">${product.id}</small>--%>
<%--                            </div>--%>
                        </div>
                    </c:forEach>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-end">
                            <c:choose>
                                <c:when test="${pagination.currentPage == 1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="main?command=catalog-page&category=${category.name}&page=${pagination.currentPage - 1}" tabindex="-1" aria-disabled="true">Previous</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link" href="main?command=catalog-page&category=${category.name}&page=${pagination.currentPage - 1}">Previous</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
<%--                            <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
                            <li class="page-item active" aria-current="page">
                                <span class="page-link">
                                    ${pagination.currentPage}
                                    <span class="sr-only">(current)</span>
                                </span>
                            </li>
<%--                            <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
                            <li class="page-item">
                                <a class="page-link" href="main?command=catalog-page&category=${category.name}&page=${pagination.currentPage + 1}">Next</a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>