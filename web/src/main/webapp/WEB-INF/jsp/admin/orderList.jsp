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
            <th scope="col">Date order</th>
            <th scope="col">Amount</th>
            <th scope="col">Currency</th>
            <th scope="col">Status</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <th scope="row">${order.id}</th>
                <fmt:parseDate value="${order.createDate}" type="date" pattern="y-M-dd'T'H:m" var="parsedDate"/>
                <td><fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm"/></td>
                <td>${order.amount}</td>
                <td>${order.currency.code}</td>
                <td>${order.orderStatus.state}</td>
                <td>
                    <div class="row">
                        <form action="main">
                            <input type="hidden" name="command" value="update-order">
                            <input type="hidden" name="id" value="${order.id}">
                            <div class="row">
                                <div class="form-group col">
                                    <label class="sr-only" for="selectState">State</label>
                                    <select name="state" class="form-control" id="selectState">
                                        <option>Processing</option>
                                        <option>Completed</option>
                                        <option>Canceled</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <button type="submit" class="btn btn-outline-primary">Update state</button>
                                </div></div>
                        </form>
                        <form action="main">
                            <input type="hidden" name="command" value="show-user">
                            <input type="hidden" name="order-id" value="${order.id}">
                            <div class="col">
                                <button type="submit" class="btn btn-outline-primary">Show user</button>
                            </div>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>