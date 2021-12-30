<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<center>
<h1><%=exception.getMessage() %></h1>
<h3><a href="${pageContext.servletContext.contextPath}/index.jsp">Повернутися на головну сторінку</a></h3>
</center>
</body>
</html>
