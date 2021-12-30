<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Користувачі</title>
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
            <h4 class="title my-4">Управління користувачами</h4>

            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Ел.пошта</th>
                    <th scope="col">Роль</th>
                    <th scope="col">Гаманець</th>
                    <th scope="col">грн.</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.users}" var="user" varStatus="loop">
                <tr>
                    <th scope="row"><c:out value="${user.id}"/></th>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.role.role.name()}"/></td>
                    <td><c:out value="${user.wallet_id}"/></td>
                    <td><c:out value="${user.wallet.money}"/></td>
                    <td>
                        <form method="POST" style="max-width: 400px;">
                            <div class="input-group">
                                <input type="text" name="wallet_id" hidden value="${user.wallet_id}" />
                                <span class="input-group-text">грн.</span>
                                <input type="number" class="form-control" name="sum" />
                                <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Додати до гаманця</button>
                            </div>
                        </form>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
