<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Майстерня</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>
<body>

<%@ include file="/parts/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-xl-4 col-lg-6">
            <c:if test="${sessionScope.user.role.role.name().equals('MANAGER')}">
                <jsp:include page="/parts/menu-manager.jsp" />
            </c:if>
            <c:if test="${sessionScope.user.role.role.name().equals('CLIENT')}">
                <jsp:include page="/parts/menu-user.jsp" />
            </c:if>
            <c:if test="${sessionScope.user.role.role.name().equals('MASTER')}">
                <jsp:include page="/parts/menu-master.jsp" />
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
