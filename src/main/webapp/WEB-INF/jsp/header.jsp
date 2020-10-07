<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="main?command=sign-in-page">Sign in</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="main?command=sign-up-page">Sign up</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="main?command=sign-out">Sign out</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="main?command=admin-page">Admin</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="main?command=user-page">User</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}">Main</a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}?${pageContext.request.queryString}&lang=en_US">English</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}?${pageContext.request.queryString}&lang=ru_RU">Russian</a>
            </li>
        </ul>
    </nav>
</header>