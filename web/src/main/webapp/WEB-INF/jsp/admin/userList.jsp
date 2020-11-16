<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty sessionScope.lang}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>
<fmt:setBundle basename="messages"/>
<div class="col-md-10">
    <button class="btn btn btn-light" type="button" data-toggle="collapse" data-target="#collapseUserForm" aria-expanded="false" aria-controls="collapseUserForm">
        Add user
    </button>
    <div class="collapse" id="collapseUserForm">
        <div class="card card-body">
            <form>
                <div class="row">
                    <div class="form-group col">
                        <label class="sr-only" for="inputEmail">Email</label>
                        <input type="text" class="form-control" id="inputEmail" placeholder="Email">
                    </div>
                    <div class="form-group col">
                        <label class="sr-only" for="inputPassword">Password</label>
                        <input type="text" class="form-control" id="inputPassword" placeholder="Password">
                    </div>
                    <div class="form-group col">
                        <label class="sr-only" for="selectRole">Role</label>
                        <select class="form-control" id="selectRole">
                            <option value="">Select role...</option>
                            <option>Admin</option>
                            <option>User</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col">
                        <label class="sr-only" for="inputPhone">Phone</label>
                        <input type="text" class="form-control" id="inputPhone" placeholder="Phone">
                    </div>
                    <div class="form-group col">
                        <label class="sr-only" for="inputFirstName">First name</label>
                        <input type="text" class="form-control" id="inputFirstName" placeholder="First name">
                    </div>
                    <div class="form-group col">
                        <label class="sr-only" for="inputLastName">Last name</label>
                        <input type="text" class="form-control" id="inputLastName" placeholder="Last name">
                    </div>
                </div>
                <button type="submit" class="btn btn-secondary">add</button>
            </form>
        </div>
    </div>
    <table class="table table-sm table-borderless">
        <caption>List of users</caption>
        <thead class="table-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Email</th>
            <th scope="col">Phone</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Role</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <th scope="row">${user.id}</th>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.role.name}</td>
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