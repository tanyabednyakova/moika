<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <!-- Возможно стоит добавить Bootstrap в проект?! -->
    <!-- Bootstrap latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <!-- Bootstrap latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="css/main_style.css" />">
</head>
<body>
    <div class="container">
        <div class="row header">
            <div class="col-xs-12"><h3>CarWash</h3></div>
        </div>
        <div class="row main-content">
            <div class="col-xs-4">sidebar</div>
            <div class="col-xs-8">pagecontent</div>
        </div>
        <div class="row footer">
            <div class="col-xs-12">О команде, контакты, ©</div>
        </div>
    </div>
</body>
</html>
