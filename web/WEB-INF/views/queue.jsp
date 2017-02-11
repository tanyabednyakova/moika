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
    <script src="<c:url value="js/utils.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/css/main_style.css" />">
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

                    <%-- Collect the nav links, forms, and other content for toggling --%>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="#">Новости<span class="sr-only">(current)</span></a></li>
                            <li><a href="#">Услуги</a></li>
                            <li><a href="#">Магазин</a></li>
                            <li><a href="#">О Нас</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right" id="client-reg">
                            <%--TODO Дописать логику проверки на ауторизацию--%>
                            <c:choose>
                                <c:when test="${isAuth==false}">
                                    <li>
                                        <div class="btn-group" role="group" aria-label="reg">
                                            <button type="button" class="btn btn-default navbar-btn" data-toggle="modal"
                                                    data-target="#loginModal">
                                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span> Войти
                                            </button>
                                            <button type="button" class="btn btn-default navbar-btn" data-toggle="modal"
                                                    data-target="#regModal">
                                                Зарегистрироваться <span class="glyphicon glyphicon-pencil"
                                                                         aria-hidden="true"></span></button>
                                        </div>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="<c:url value="/user"/>"><span class="glyphicon glyphicon-user"
                                                                               aria-hidden="true"></span>${userFirstName}
                                    </a></li>
                                    <li><a href="<c:url value="/user/logout"/>"><span
                                            class="glyphicon glyphicon-log-out" aria-hidden="true"></span>Выйти</a></li>
                                </c:otherwise>
                            </c:choose>
                            <%--li><button type="button" class="btn btn-default navbar-btn">Войти
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span></button>
                            </li>
                            <li><button type="button" class="btn btn-default navbar-btn">Регистрация
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></li--%>
                        </ul>
                    </div>
                    <%-- /.navbar-collapse --%>
                </div>
                <%-- /.container-fluid --%>
            </nav>
        </div>
    </div>
    <div class="row main-content">
        <div class="col-xs-12">
            <h3>Записаться на </h3>

                <form method="post" action="">
                    <fieldset>
                        <legend>
                            Выберите услугу<br>
                        </legend>
                        <c:forEach items="${serviceType}" var="service_Type">
                            <label>
                                <input type="checkbox" value="${service_Type.typeCode}"> ${service_Type.typeName}
                            </label>
                            <br>
                        </c:forEach>
                    </fieldset>


                    <label for="date_servise">Выберите дату:</label>
                    <input type="date" id="date_servise" name="date_servise">
                    <fieldset>
                        <legend>
                            Выберите время<br>
                        </legend>
                        <c:forEach items="${}" var="time">
                            <label>
                                <input type="radio" value="">
                            </label>
                            <br>
                        </c:forEach>
                    </fieldset>

                    <br>
                    <fieldset>
                        <legend>
                           Введите контактную информацию<br>
                        </legend>
                            <label>Ваше имя:
                                <input type="text">
                            </label>

                            <label>Контактный телефон:
                                <input type="tel">
                            </label>
                            <br>
                    </fieldset>
                    <fieldset>
                        <legend>
                            Введите информацию о машине<br>
                        </legend>
                        <label>Номер машины:
                            <input type="text">
                        </label>

                        <label>Модель:
                            <input type="text">
                        </label>
                        <br>
                    </fieldset>


                    <!-- если пользователь не залогинелся-->
                    <!-- TODO если пользователь авторизован - взять данные-->

                    <input type="submit"  value="send"/>
                </form>


        </div>
    </div>
    <%--TODO вынести в отдельную стр--%>
    <div class="row footer vertical-align">
        <div class="col-xs-12">О команде, контакты, ©</div>
    </div>
</div>
<c:if test="${isAuth==false}">
    <%-- Модальные окна --%>
    <%-- Модальное окно ауторизации--%>
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="loginModalLabel">Вход</h3>
                </div>
                <div class="modal-body">
                    <form id="loginForm">
                        <div class="alert alert-danger hide">
                            <p>Invalid login or password </p>
                        </div>
                        <div class="form-group">
                            <label for="loginInputLogin">Логин</label>
                            <input type="text" class="form-control" name="login" id="loginInputLogin"
                                   placeholder="Login">
                        </div>
                        <div class="form-group">
                            <label for="loginInputPassword">Пароль</label>
                            <input type="password" class="form-control" name="password" id="loginInputPassword"
                                   placeholder="Password">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="loginBtn" class="btn btn-primary">Войти</button>
                        <%--button type="button" class="btn btn-primary">Save changes</button--%>
                </div>
            </div>
                <%-- /.modal-content --%>
        </div>
            <%-- /.modal-dialog --%>
    </div>
    <%-- /.modal --%>

    <%-- Модальное окно регистрации--%>
    <div class="modal fade" id="regModal" tabindex="-1" role="dialog" aria-labelledby="regModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="regModalLabel">Форма регистрации</h3>
                </div>
                <div class="modal-body">
                    <form id="regForm">
                        <div class="form-group has-feedback">
                            <label for="regInputEmail">Email</label>
                            <input type="email" class="form-control" name="email" id="regInputEmail"
                                   placeholder="Email">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputLogin">Логин</label>
                            <input type="email" class="form-control" name="login" id="regInputLogin"
                                   placeholder="Login">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputPassword">Пароль</label>
                            <input type="password" class="form-control" name="password" id="regInputPassword"
                                   placeholder="Password">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputPassword1">Повторите пароль</label>
                            <input type="password" class="form-control" id="regInputPassword1" placeholder="Password">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="regBtn" class="btn btn-primary">Регистрация</button>
                        <%--button type="button" class="btn btn-primary">Save changes</button--%>
                </div>
            </div>
                <%-- /.modal-content --%>
        </div>
            <%-- /.modal-dialog --%>
    </div><%-- /.modal --%>
    <%--TODO вынести в отдельный js--%>
    <script type="text/javascript">
        <%-- Здесь описана логика работы модальных окон регистрации и аутентификации --%>
        $("#regForm").attr('error', 1);
        var changeErr = function (res) {
            var form = $("#regForm");
            var err = parseInt(form.attr('error'));
            if (res) {
                form.attr('error', err == 0 ? err : err - 1);
            } else {
                form.attr('error', err == 2 ? err : err + 1);
            }
        };

        setChangeListener('#regInputPassword1', 1500, function (elem) {
            if ($('#regInputPassword').val() == elem.val()) {
                setStatusElement('#regInputPassword1', 'success');
                changeErr(true);
            } else {
                setStatusElement('#regInputPassword1', 'error', 'Пароли должны совпадать!');
                changeErr(false);
            }
        });
        //TODO добавить актуальные url
        setActiveFormInput(<c:url value="/user/"/>, '#regInputEmail', changeErr);
        setActiveFormInput(<c:url value="/user/"/>, '#regInputLogin', changeErr);

        $("#loginBtn").click(function () {
            var jsonData = parseFormToJSON('#loginForm');
            $.ajax({
                method: "POST",
                contentType: 'application/json;charset=UTF-8',
                url:<c:url value="/user/"/>,//TODO добавить актуальные url
                data: jsonData,
                success: function (data) {
                    var obj = $.parseJSON(data);
                    if (obj.redirect) {
                        window.location.replace(obj.redirect);
                    }
                    if (obj.errorMsg) {
                        var div = $('#loginForm').find('div.alert');
                        div.removeClass('hide'); //TODO Возможно стоит добавить отображение сообщения с бэка?!
                    }
                }
            });
        });

        $("#regBtn").click(function () {
            if ($("#regForm").attr('hasError') == 0) {
                var jsonData = parseFormToJSON("#regForm");
                $.ajax({
                    method: "POST",
                    contentType: 'application/json;charset=UTF-8',
                    url:<c:url value="/user/"/>,//TODO добавить актуальные url
                    data: jsonData,
                    success: function (data) {
                        var obj = $.parseJSON(data);
                        if (obj.redirect) {
                            window.location.replace(obj.redirect);
                        }
                        if (obj.errors) {
                            processErrors(obj.errors);
                        }
                    }
                });
            }
        });
    </script>
</c:if>
</body>
</html>
