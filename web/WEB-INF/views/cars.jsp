<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<table>
    <tr >
        <td>ID</td>
        <td>Type</td>
        <td>Number</td>
    </tr>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td><c:out value="${car.id}"/></td>
            <td><c:out value="${car.CarType}"/></td>
            <td><c:out value="${car.CarNumber}"/></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
