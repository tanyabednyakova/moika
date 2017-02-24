<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test for test entity</title>
</head>
<body>
<p>Текущее время ${currentTime}</p>
<table>
    <tr >
        <td>ID</td>
        <td>Name</td>
    </tr>
    <c:forEach items="${testlist}" var="testlist">
        <tr>
            <td><c:out value="${test.id}"/></td>
            <td><c:out value="${test.name1}"/></td>
            <td><c:out value="${test.name2}"/></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
