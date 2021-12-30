<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Замовлення</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>

<body>

<%@ include file="/parts/header.jsp" %>

<div class="container">
    <div class="ror">
        <div class="col">
            <h4 class="title my-4">Управління замовленнями</h4>

            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Клієнт</th>
                    <th scope="col">Майстер</th>
                    <th scope="col">Статус</th>
                    <th scope="col">Оплата</th>
                    <th scope="col">Примітка</th>
                    <th scope="col">Зворотній зв'язок</th>
                    <th scope="col">грн.</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.orders}" var="order" varStatus="loop">
                    <form method="POST">
                    <tr>
                        <th scope="row"><c:out value="${order.id}"/></th>
                        <td><c:out value="${order.client.username}"/></td>
                        <td><c:out value="${order.master.username}"/></td>
                        <td>
                            <c:if test="${sessionScope.user.id == order.master.id}">
                                <select name="completionStatus" class="form-select">
                                    <option value="6" ${order.completionStatus.status.name() == 'NEW' ? 'selected="selected"' : ''}>New</option>
                                    <option value="4" ${order.completionStatus.status.name() == 'IN_PROGRESS' ? 'selected="selected"' : ''}>In progress</option>
                                    <option value="5" ${order.completionStatus.status.name() == 'FINISHED' ? 'selected="selected"' : ''}>Finished</option>
                                </select>
                            </c:if>
                            <c:if test="${sessionScope.user.id != order.master.id}">
                                <c:out value="${order.completionStatus.status.name()}"/>
                            </c:if>
                        </td>
                        <td><c:out value="${order.paymentStatus.status.name()}"/></td>
                        <td><c:out value="${order.description}"/></td>
                        <td><c:out value="${order.feedback}"/></td>
                        <td><c:out value="${order.cost}"/> грн.</td>
                        <td>
                            <c:if test="${order.master == null}">
                                <input type="text" name="orderId" hidden value="${order.id}" />
                                <input type="text" name="action" hidden value="assign" />
                                <input type="text" name="masterId" hidden value="${sessionScope.user.id}" />
                                <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Assign to me</button>
                            </c:if>
                            <c:if test="${order.master.id == sessionScope.user.id}">
                                <input type="text" name="orderId" hidden value="${order.id}" />
                                <input type="text" name="action" hidden value="update" />
                                <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Update</button>
                            </c:if>
                        </td>
                    </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
