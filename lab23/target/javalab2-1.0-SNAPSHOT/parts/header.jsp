<div class="container">
    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
        <a href="${pageContext.servletContext.contextPath}/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
            <i class="bi bi-tools me-3" style="font-size: 1.5rem;"></i>
            <span class="fs-4">Автомайстерня</span>
        </a>

        <ul class="nav nav-pills">
            <li>
                <c:if test="${sessionScope.user == null}">
                    <a href="${pageContext.servletContext.contextPath}/login" class="btn btn-primary">Вхід</a>
                    <a href="${pageContext.servletContext.contextPath}/register" class="btn btn-primary">Реєстрація</a>
                </c:if>
                <c:if test="${sessionScope.user != null}">
<%--                    <li><a href="${pageContext.servletContext.contextPath}/" class="nav-link">Головна</a></li>--%>
                    <li class="nav-link">
                        <c:out value="${sessionScope.user.username}"/>
                        (<c:out value="${sessionScope.user.email}"/>)
                    </li>
                    <a href="${pageContext.servletContext.contextPath}/logout" class="btn btn-primary">Вихід</a>
                </c:if>
            </li>
        </ul>
    </header>
</div>
