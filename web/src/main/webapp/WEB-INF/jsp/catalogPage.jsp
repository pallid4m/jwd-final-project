<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/pagination.tld" %>
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
                    <a class="nav-link active" href="<c:url value="main?command=catalog-page&category=phone&page=1"/>"><fmt:message key="catalog.phones"/></a>
                    <a class="nav-link" href="<c:url value="main?command=catalog-page&category=laptop&page=1"/>"><fmt:message key="catalog.laptops"/></a>
                </nav>
            </div>
            <div class="col-md-10">
                <c:if test="${!empty requestScope.products}">
                    <c:forEach var="product" items="${requestScope.products}">
                        <div class="card mb-3">
                            <div class="row card-item">
                                <div class="col-md-2" style="max-width: 540px;">
                                    <img class="card-img" src="<c:url value="/resources/img/product/${product.category.name}/${product.image}"/>" alt="${product.category.name}">
                                </div>
                                <div class="col-md-10">
                                    <div class="card-body">
                                        <c:url var="productUrl" value="main">
                                            <c:param name="command" value="product"/>
                                            <c:param name="id" value="${product.id}"/>
                                        </c:url>
                                        <a href="<c:url value="${productUrl}"/>"><h5 class="card-title">${product.name}</h5></a>
                                        <p class="card-text">${product.description}</p>
                                        <p><strong>${product.price} ${product.currency.code}</strong></p>
                                        <form action="main">
                                            <input type="hidden" name="command" value="add-product-to-cart">
                                            <input type="hidden" name="product-id" value="${product.id}">
                                            <button class="btn btn-sm btn-outline-primary" type="submit"><fmt:message key="action.add.to.cart"/></button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="pagination justify-content-end">
                        <c:url var="paginateUri" value="main">
                            <c:param name="command" value="catalog-page"/>
                            <c:param name="category" value="${requestScope.category.name}"/>
                        </c:url>
                        <tag:paginate max="3" offset="${requestScope.offset}" count="${requestScope.count}" uri="${paginateUri}" next="&raquo;" previous="&laquo;" />
                    </div>
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