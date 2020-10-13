<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}">Main</a>
            </li>
            <c:if test="${empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link" href="main?command=sign-in-page">Sign in</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="main?command=sign-up-page">Sign up</a>
                </li>
            </c:if>
            <c:if test="${!empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link" href="main?command=sign-out">Sign out</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="main?command=admin-page">Admin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="main?command=user-page">User</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="main?command=catalog-page">Catalog</a>
            </li>
            <c:if test="${!empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link" href="main?command=cart-page">Cart</a>
                </li>
            </c:if>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="main?${pageContext.request.queryString}&lang=en_US">English</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="main?${pageContext.request.queryString}&lang=ru_RU">Russian</a>
            </li>
        </ul>
    </nav>
</header>