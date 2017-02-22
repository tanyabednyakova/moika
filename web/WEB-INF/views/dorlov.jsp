<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="solid">
    <tr>
        <td><h3>Модель</h3></td>
        <td><h3>Номер</h3></td>
        <td><h3>Тип кузова</h3></td>
        <td><h3>Описание</h3></td>
    </tr>
    <c:forEach items="${cars}" var="car">
    <tr>
        <td><c:out value="${car.carModel}"/></td>
        <td><c:out value="${car.carNumber}"/></td>
        <td><c:out value="${car.carType}"/></td>
        <td><c:out value="${car.description}"/></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
