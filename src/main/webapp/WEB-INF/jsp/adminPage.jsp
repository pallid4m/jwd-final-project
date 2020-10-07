<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Admin</title>
</head>
<body>
    <jsp:include page="header.jsp"/>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2">
                <nav class="nav flex-column">
                    <a class="nav-link active" href="#">User list</a>
                    <a class="nav-link" href="#">Product list</a>
                    <a class="nav-link" href="#">Settings</a>
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                </nav>
            </div>
            <div class="col-md-10">
                <h4>User list</h4>
                <button class="btn btn btn-light" type="button" data-toggle="collapse" data-target="#collapseUserForm" aria-expanded="false" aria-controls="collapseUserForm">
                    Add user
                </button>
                <div class="collapse" id="collapseUserForm">
                    <div class="card card-body">
                        <form>
                            <div class="row">
                                <div class="form-group col">
                                    <label for="inputId">Id</label>
                                    <input type="text" class="form-control" id="inputId" placeholder="Id">
                                </div>
                                <div class="form-group col">
                                    <label for="inputEmail">Email</label>
                                    <input type="text" class="form-control" id="inputEmail" placeholder="Email">
                                </div>
                                <div class="form-group col">
                                    <label for="inputPassword">Password</label>
                                    <input type="text" class="form-control" id="inputPassword" placeholder="Password">
                                </div>
                                <div class="form-group col">
                                    <label for="selectRole">Role</label>
                                    <select class="form-control" id="selectRole">
                                        <option value="">Select role...</option>
                                        <option>admin</option>
                                        <option>user</option>
                                    </select>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-secondary">add</button>
                        </form>
                    </div>
                </div>
                <table class="table table-hover table-sm">
                    <caption>List of users</caption>
                    <thead class="table-dark">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Email</th>
                        <th scope="col">Password</th>
                        <th scope="col">Role</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${sessionScope.users}">
                        <tr>
                            <th scope="row">#</th>
                            <td>${user.email}</td>
                            <td>${user.password}</td>
<%--                            <td>${user.role}</td>--%>
                            <td>role</td>
                            <td>
                                <a href="#">View</a>
                                <a href="#">Edit</a>
                                <a href="#">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>