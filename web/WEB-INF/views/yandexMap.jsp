<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%--TODO вынести в отдельную стр--%>
<head>
    <title>Title</title>
    <%-- Возможно стоит добавить Bootstrap в проект?! --%>
    <%-- Bootstrap latest compiled and minified CSS --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%-- jQuery library --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <%-- Bootstrap latest compiled JavaScript --%>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<c:url value="/js/utils.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/css/main_style.css" />">
    <head>
        <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
        <script type="text/javascript">

        </script>

    </head>
</head>

<body>
<div class="container">
    <div class="row header">
        <div class="col-xs-12">
            <%--TODO вынести в отдельную стр--%>
            <nav class="navbar navbar-default">
                <div class="container-fluid main-menu">
                    <%-- Brand and toggle get grouped for better mobile display --%>
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <%--${pageContext.request.contextPath}--%>
                        <a class="navbar-brand" href="<c:url value="/client" />">
                                <span><img class="logo-car" alt="CarWash" height="40" width="32"
                                           src="<c:url value="/images/logo1.svg" />">
                                 </span> CarWash</a>
                    </div>
                        <c:forEach items="${placemarkYandexList}" var="plaseMark">
                        ${plaseMark.balloonContent}

                        <br>
                        </c:forEach>
                    <div>

                        <p>Карта Москвы</p>
                        <div id="first_map" style="width:400px; height:300px"></div>
                        <p>Карта Санкт-Петербурга</p>
                        <p style="width:400px; height:200px"></p>
                    </div>
</body>
<script type="text/javascript">
    var moscow_map,
        piter_map;


    ymaps.ready(function(){
        moscow_map = new ymaps.Map("first_map", {
            center: [55.76, 37.64],
            zoom: 10
        });
        piter_map = new ymaps.Map(document.getElementsByTagName('p')[2], {
            center: [59.94, 30.32],
            zoom: 9
        });

        <c:forEach items="${placemarkYandexList}" var="plaseMark">

        myPlacemark = new ymaps.Placemark(
            [${plaseMark.latitude}, ${plaseMark.longitude}],
            { hintContent: '${plaseMark.hintContent}', balloonContent: '${plaseMark.balloonContent}' });

        moscow_map.geoObjects.add(myPlacemark);
        </c:forEach>
    });
</script>
</html>
