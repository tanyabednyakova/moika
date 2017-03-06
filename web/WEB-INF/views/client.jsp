<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
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
    <link rel="stylesheet" href="<c:url value="css/main_style.css" />">
</head>
<body>
<div class="container">
    <div class="row header">
        <div class="col-xs-12">
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
                                           src="<c:url value="images/logo1.svg" />">
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
                            <li><div class="btn-group" role="group" aria-label="reg">
                                <button type="button" class="btn btn-default navbar-btn" data-toggle="modal" data-target="#loginModal">
                                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span> Войти</button>
                                <button type="button" class="btn btn-default navbar-btn" data-toggle="modal" data-target="#regModal">
                                    Зарегистрироваться <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                            </div></li>
                            <%--li><button type="button" class="btn btn-default navbar-btn">Войти
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span></button>
                            </li>
                            <li><button type="button" class="btn btn-default navbar-btn">Регистрация
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></li--%>
                        </ul>
                    </div><%-- /.navbar-collapse --%>
                </div><%-- /.container-fluid --%>
            </nav>
        </div>
    </div>
    <div class="row main-content">
        <div class="col-xs-12">

            <h3>Классический текст Lorem Ipsum, используемый с XVI века</h3>
            <p>
                <blockquote>
            <p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                deserunt mollit anim id est laborum."</p>
            <footer>Абзац 1.10.32 "de Finibus Bonorum et Malorum", написанный Цицероном в 45 году н.э.</footer>
            </blockquote>
            <blockquote>
                <p>"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium,
                    totam
                    rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt
                    explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia
                    consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui
                    dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi
                    tempora
                    incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis
                    nostrum
                    exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis
                    autem
                    vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel
                    illum
                    qui dolorem eum fugiat quo voluptas nulla pariatur?"
                </p>
                <footer>Английский перевод 1914 года, H. Rackham</footer>
            </blockquote>
           <%-- <blockquote>
                <p>"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was
                    born
                    and I will give you a complete account of the system, and expound the actual teachings of the great
                    explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids
                    pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure
                    rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or
                    pursues or desires to obtain pain of itself, because it is pain, but because occasionally
                    circumstances
                    occur in which toil and pain can procure him some great pleasure. To take a trivial example, which
                    of us
                    ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has
                    any
                    right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or
                    one
                    who avoids a pain that produces no resultant pleasure?"</p>
                <footer>Абзац 1.10.33 "de Finibus Bonorum et Malorum", написанный Цицероном в 45 году н.э.</footer>
            </blockquote>
            <blockquote>
                <p>"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum
                    deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non
                    provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum
                    fuga.
                    Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est
                    eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas
                    assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum
                    necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque
                    earum
                    rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut
                    perferendis doloribus asperiores repellat."</p>
                <footer>Английский перевод 1914 года, H. Rackham</footer>
            </blockquote>--%>
            <blockquote>
                "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and
                demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the
                pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty
                through weakness of will, which is the same as saying through shrinking from toil and pain. These cases
                are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled
                and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and
                every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of
                business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise
                man therefore always holds in these matters to this principle of selection: he rejects pleasures to
                secure other greater pleasures, or else he endures pains to avoid worse pains."
            </blockquote>
            </p>


        </div>
    </div>
    <div class="row footer vertical-align">
        <div class="col-xs-12">О команде, контакты, ©</div>
    </div>
</div>
<%-- Модальные окна --%>

<%-- Модальное окно  ауторизации--%>
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
                        <input type="text" class="form-control" name="login" id="loginInputLogin" placeholder="Login">
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="regModalLabel">Форма регистрации</h3>
            </div>
            <div class="modal-body">
                <form id="regForm">
                    <div class="form-group has-feedback">
                        <label for="regInputEmail">Email</label>
                        <input type="email" class="form-control" name="email" id="regInputEmail" placeholder="Email">
                        <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="regInputLogin">Логин</label>
                        <input type="email" class="form-control" name="login" id="regInputLogin" placeholder="Login">
                        <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="regInputPassword">Пароль</label>
                        <input type="password" class="form-control" name="password" id="regInputPassword" placeholder="Password">
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
        </div><%-- /.modal-content --%>
    </div><%-- /.modal-dialog --%>
</div><%-- /.modal --%>
<script type="text/javascript">

</script>
</body>
</html>
