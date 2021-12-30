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
                        <td>
                            <select name="masterId" class="form-select">
                                <c:forEach var="master" items="${sessionScope.masters}">
                                    <option value="${master.id}" ${master.id == order.master.id ? 'selected="selected"' : ''}>${master.username}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="completionStatus" class="form-select">
                                <option value="6" ${order.completionStatus.status.name() == 'NEW' ? 'selected="selected"' : ''}>New</option>
                                <option value="4" ${order.completionStatus.status.name() == 'IN_PROGRESS' ? 'selected="selected"' : ''}>In progress</option>
                                <option value="5" ${order.completionStatus.status.name() == 'FINISHED' ? 'selected="selected"' : ''}>Finished</option>
                            </select>
                        </td>
                        <td>
                            <select name="paymentStatus" class="form-select">
                                <option value="6" ${order.paymentStatus.status.name() == 'NEW' ? 'selected="selected"' : ''}>New</option>
                                <option value="1" ${order.paymentStatus.status.name() == 'WAITING_FOR_PAYMENT' ? 'selected="selected"' : ''}>Waiting for payment</option>
                                <option value="2" ${order.paymentStatus.status.name() == 'PAID' ? 'selected="selected"' : ''}>Paid</option>
                                <option value="3" ${order.paymentStatus.status.name() == 'CANCELLED' ? 'selected="selected"' : ''}>Cancelled</option>
                            </select>
                        </td>
                        <td><c:out value="${order.description}"/></td>
                        <td><c:out value="${order.feedback}"/></td>
                        <td><input type="number" name="cost" value="${order.cost}" class="form-control" /></td>
                        <td>
                            <input type="text" name="orderId" hidden value="${order.id}" />
                            <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Оновити</button>
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
