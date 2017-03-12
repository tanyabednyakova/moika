<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                            <%--TODO добавить checkbox "remeber me" для Spring Secucurity--%>
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
        setActiveFormInput('<c:url value="/user/util"/>', '#regInputEmail', changeErr);
        setActiveFormInput('<c:url value="/user/util"/>', '#regInputLogin', changeErr);

        $("#loginBtn").click(function () {
            var jsonData = parseFormToJSON('#loginForm');
            $.ajax({
                method: "POST",
                contentType: 'application/json;charset=UTF-8',
                url:"<c:url value="/user/login"/>",//TODO добавить актуальные url
                data: jsonData,
                success: function (data) {
                    if (data.redirect) {
                        window.location.replace(data.redirect);
                    }
                    if (data.errorMsg) {
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
                    url:"<c:url value="/user/reg"/>",//TODO добавить актуальные url
                    data: jsonData,
                    success: function (data) {
                        if (data.redirect) {
                            window.location.replace(data.redirect);
                        }
                        if (data.errors) {
                            processErrors(data.errors);
                        }
                    }
                });
            }
        });
    </script>
</c:if>
