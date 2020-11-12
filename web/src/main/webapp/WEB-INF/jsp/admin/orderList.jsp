<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${!empty sessionScope.lang}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>
<fmt:setBundle basename="messages"/>
<div class="col-md-10">
    <table class="table table-sm table-borderless">
        <caption>List of orders</caption>
        <thead class="table-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Create date</th>
            <th scope="col">Amount</th>
            <th scope="col">Currency</th>
            <th scope="col">Status</th>
<%--            <th scope="col">Amount</th>--%>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <th scope="row">${order.id}</th>
                <td>${order.createDate}</td>
                <td>${order.amount}</td>
                <td>${order.currency.code}</td>
                <td>${order.orderStatus.state}</td>
<%--                <td>${order.amount}</td>--%>
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