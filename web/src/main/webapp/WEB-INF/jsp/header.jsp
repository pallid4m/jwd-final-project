<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty sessionScope.lang}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>
<fmt:setBundle basename="messages"/>
<header>

    <c:url var="signInPageUrl" value="main">
        <c:param name="command" value="sign-in-page"/>
    </c:url>

    <c:url var="signUpPageUrl" value="main">
        <c:param name="command" value="sign-up-page"/>
    </c:url>

    <c:url var="signOutUrl" value="main">
        <c:param name="command" value="sign-out"/>
    </c:url>

    <c:url var="adminPageUrl" value="main">
        <c:param name="command" value="admin-page"/>
    </c:url>

    <c:url var="profilePageUrl" value="main">
        <c:param name="command" value="profile-page"/>
    </c:url>

    <c:url var="catalogPageUrl" value="main">
        <c:param name="command" value="catalog-page"/>
    </c:url>

    <c:url var="cartPageUrl" value="main">
        <c:param name="command" value="cart-page"/>
    </c:url>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}"><fmt:message key="main.title"/></a>
            </li>
            <c:if test="${empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link" href="${signInPageUrl}"><fmt:message key="sign_in.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${signUpPageUrl}"><fmt:message key="sign_up.title"/></a>
                </li>
            </c:if>
            <c:if test="${!empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link" href="${signOutUrl}"><fmt:message key="sign_out.button"/></a>
                </li>
                <c:if test="${sessionScope.user.role.name == 'Admin'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${adminPageUrl}">Admin</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.role.name == 'User'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${profilePageUrl}"><fmt:message key="profile.title"/></a>
                    </li>
                </c:if>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="${catalogPageUrl}"><fmt:message key="catalog.title"/></a>
            </li>
            <c:if test="${sessionScope.user.role.name == 'User'}">
                <li class="nav-item">
                    <a class="nav-link" href="${cartPageUrl}"><fmt:message key="cart.title"/></a>
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