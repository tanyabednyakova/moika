<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>hello</h1>
<p>text</p>
<br>
<h1>${hello}</h1>
<p>${someStr}</p>
<br>
<br>
<c:forEach var="bean" items="${beansList}">
    ${bean}<br>
</c:forEach>
</body>
</html>
