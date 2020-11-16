<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty sessionScope.lang}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>
<fmt:setBundle basename="messages"/>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}"><fmt:message key="main.title"/></a>
            </li>
            <c:if test="${empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link" href="main?command=sign-in-page"><fmt:message key="sign_in.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="main?command=sign-up-page"><fmt:message key="sign_up.title"/></a>
                </li>
            </c:if>
            <c:if test="${!empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link" href="main?command=sign-out"><fmt:message key="sign_out.button"/></a>
                </li>
                <c:if test="${sessionScope.user.role.name == 'Admin'}">
                    <li class="nav-item">
                        <a class="nav-link" href="main?command=admin-page">Admin</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.role.name == 'User'}">
                    <li class="nav-item">
                        <a class="nav-link" href="main?command=profile-page">Profile</a>
                    </li>
                </c:if>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="main?command=catalog-page"><fmt:message key="catalog.title"/></a>
            </li>
            <c:if test="${sessionScope.user.role.name == 'User'}">
                <li class="nav-item">
                    <a class="nav-link" href="main?command=cart-page"><fmt:message key="cart.title"/></a>
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