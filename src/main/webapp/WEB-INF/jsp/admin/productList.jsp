<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty sessionScope.lang}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>
<fmt:setBundle basename="messages"/>
<div class="col-md-10">
    <button class="btn btn btn-light" type="button" data-toggle="collapse" data-target="#collapseUserForm" aria-expanded="false" aria-controls="collapseUserForm">
        Add product
    </button>
    <div class="collapse" id="collapseUserForm">
        <div class="card card-body">
            <form>
                <div class="row">
                    <div class="form-group col">
                        <label class="sr-only" for="inputName">Name</label>
                        <input type="text" class="form-control" id="inputName" placeholder="Name">
                    </div>
                    <div class="form-group col">
                        <label class="sr-only" for="inputDescription">Description</label>
                        <input type="text" class="form-control" id="inputDescription" placeholder="Description">
                    </div>
                    <div class="form-group col">
                        <label class="sr-only" for="inputPrice">Price</label>
                        <input type="text" class="form-control" id="inputPrice" placeholder="Price">
                    </div>
                    <div class="form-group col">
                        <label class="sr-only" for="inputCurrency">Currency</label>
                        <input type="text" class="form-control" id="inputCurrency" placeholder="Currency">
                    </div>
                    <div class="form-group col">
                        <label class="sr-only" for="selectCategory">Category</label>
                        <select class="form-control" id="selectCategory">
                            <option value="">Select category...</option>
                            <option>Phone</option>
                            <option>Laptop</option>
                        </select>
                    </div>
                </div>
                <button type="submit" class="btn btn-secondary">add</button>
            </form>
        </div>
    </div>
    <table class="table table-sm table-borderless">
        <caption>List of products</caption>
        <thead class="table-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Price</th>
            <th scope="col">Currency</th>
            <th scope="col">Category</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <th scope="row">${product.id}</th>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.currency}</td>
                <td>${product.category.name}</td>
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