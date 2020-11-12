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

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

<%--    <div class="container emp-profile">--%>
<%--        <form method="post">--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-4">--%>
<%--                    <div class="profile-img">--%>
<%--                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog" alt=""/>--%>
<%--                        <div class="file btn btn-lg btn-primary">--%>
<%--                            Change Photo--%>
<%--                            <input type="file" name="file"/>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-md-6">--%>
<%--                    <div class="profile-head">--%>
<%--                        <h5>--%>
<%--                            Kshiti Ghelani--%>
<%--                        </h5>--%>
<%--                        <h6>--%>
<%--                            Web Developer and Designer--%>
<%--                        </h6>--%>
<%--                        <p class="proile-rating">RANKINGS : <span>8/10</span></p>--%>
<%--                        <ul class="nav nav-tabs" id="myTab" role="tablist">--%>
<%--                            <li class="nav-item">--%>
<%--                                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">About</a>--%>
<%--                            </li>--%>
<%--                            <li class="nav-item">--%>
<%--                                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Timeline</a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-md-2">--%>
<%--                    <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Edit Profile"/>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-4">--%>
<%--                    <div class="profile-work">--%>
<%--                        <p>WORK LINK</p>--%>
<%--                        <a href="">Website Link</a><br/>--%>
<%--                        <a href="">Bootsnipp Profile</a><br/>--%>
<%--                        <a href="">Bootply Profile</a>--%>
<%--                        <p>SKILLS</p>--%>
<%--                        <a href="">Web Designer</a><br/>--%>
<%--                        <a href="">Web Developer</a><br/>--%>
<%--                        <a href="">WordPress</a><br/>--%>
<%--                        <a href="">WooCommerce</a><br/>--%>
<%--                        <a href="">PHP, .Net</a><br/>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-md-8">--%>
<%--                    <div class="tab-content profile-tab" id="myTabContent">--%>
<%--                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>User Id</label>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <p>Kshiti123</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>Name</label>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <p>Kshiti Ghelani</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>Email</label>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <p>kshitighelani@gmail.com</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>Phone</label>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <p>123 456 7890</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>Profession</label>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <p>Web Developer and Designer</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>Experience</label>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <p>Expert</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>Hourly Rate</label>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <p>10$/hr</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>Total Projects</label>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <p>230</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>English Level</label>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <p>Expert</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <label>Availability</label>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6">--%>
<%--                                    <p>6 months</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-12">--%>
<%--                                    <label>Your Bio</label><br/>--%>
<%--                                    <p>Your detail description</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>

    <div class="container">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item" role="presentation">
                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#main" role="tab" aria-controls="main" aria-selected="true">Main</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="orders-tab" data-toggle="tab" href="#orders" role="tab" aria-controls="orders" aria-selected="false">Orders</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="payment-tab" data-toggle="tab" href="#payment" role="tab" aria-controls="payment" aria-selected="false">Payment</a>
            </li>
        </ul>
        <div class="tab-content" id="myTabContent">

            <div class="tab-pane fade show active" id="main" role="tabpanel" aria-labelledby="main-tab">
                <p>main content</p>
            </div>

            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <div class="pb-5">
                    <p class="h5">Change email</p><hr>
                    <form class="col-md-6" action="" method="post">
                        <div class="form-group input-group-sm">
                            <label class="" for="inputEmail">Email</label>
                            <input type="email" name="email" value="${sessionScope.user.email}" id="inputEmail" class="form-control" placeholder="" required="" autofocus=""/>
                        </div>
                        <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">
                        <button type="submit" class="btn btn-outline-dark btn-sm">Update email</button>
                    </form>
                </div>

                <div class="pb-5">
                    <p class="h5">Change password</p><hr>
                    <form class="col-md-6" action="" method="post" autocomplete="off">
                        <div class="form-group input-group-sm">
                            <label class="" for="inputOldPassword">Old password</label>
                            <input type="password" name="old-password" id="inputOldPassword" class="form-control" placeholder="" required=""/>
                        </div>
                        <div class="form-group input-group-sm">
                            <label class="" for="inputNewPassword">New password</label>
                            <input type="password" name="new-password" id="inputNewPassword" class="form-control" placeholder="" required=""/>
                        </div>
                        <div class="form-group input-group-sm">
                            <label class="" for="inputConfirmNewPassword">Confirm new password</label>
                            <input type="password" name="confirm-new-password" id="inputConfirmNewPassword" class="form-control" placeholder="" required=""/>
                        </div>
                        <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">
                        <button type="submit" class="btn btn-outline-dark btn-sm">Update password</button>
                    </form>
                </div>

                <div class="pb-5">
                    <p class="h5">Personal data</p><hr>
                    <form class="col-md-6" action="" method="post">
                        <div class="form-group input-group-sm">
                            <label class="" for="inputPhone">Phone</label>
                            <input type="tel" name="phone" value="${sessionScope.user.phone}" id="inputPhone" class="form-control" placeholder="" required=""/>
                        </div>
                        <div class="form-group input-group-sm">
                            <label class="" for="inputFirstName">First Name</label>
                            <input type="text" name="first-name" value="${sessionScope.user.firstName}" id="inputFirstName" class="form-control" placeholder="" required=""/>
                        </div>
                        <div class="form-group input-group-sm">
                            <label class="" for="inputLastName">Last Name</label>
                            <input type="text" name="last-name" value="${sessionScope.user.lastName}" id="inputLastName" class="form-control" placeholder="" required=""/>
                        </div>
                        <input type="hidden" name="csrf_token" value="${sessionScope.csrf_token}">
                        <button type="submit" class="btn btn-outline-dark btn-sm">Update profile</button>
                    </form>
                </div>
            </div>

            <div class="tab-pane fade" id="orders" role="tabpanel" aria-labelledby="orders-tab">
                <p>orders content</p>
            </div>

            <div class="tab-pane fade" id="payment" role="tabpanel" aria-labelledby="payment-tab">
                <p>payment content</p>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>