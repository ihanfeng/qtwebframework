<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>中移全通医疗云项目基础框架展示</title>
    <link rel="stylesheet" href="${ctx}/static/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/static/css/index.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/lib/plugins/html5shiv.js"></script>
    <script src="${ctx}/static/lib/plugins/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="${ctx}/static/img/favicon.ico" type="image/x-icon">
</head>
<body>
<div id="page-header" role="heading" class="page-header">
    <div class="container">
        <span id="company"> <h1>中移全通系统集成有限公司 |
            <small>医疗云</small>
        </h1></span>
    </div>
</div>
<div id="banner" role="banner" class="banner">
    <div class="container">
        <div class="row">
            <div id="loginbox"
                 class="col-lg-4 col-lg-offset-8 col-md-5 col-md-offset-6 col-sm-6 col-sm-offset-6 col-xs-6 col-xs-offset-5">
                <div class="text-center" style="margin-top: 60px;"><h2>登录</h2></div>
                <form action="hello/welcome" class="form-horizontal" id="login-form" method="post">
                    <div class="form-group">
                        <span class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-user"></span>
                            </span>
                            <input type="text" class="form-control" name="username" placeholder="用户名">
                        </span>
                    </div>
                    <div class="form-group">
                        <span class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-lock"></span>
                            </span>
                            <input type="password" class="form-control" name="password" placeholder="密码">
                        </span>
                    </div>

                    <div class="form-group form-inline">
                        <span class="input-group">
                            <input type="checkbox" name="rememberMe" class="checkbox">
                            <span> <small>&nbsp;&nbsp;记住我</small></span>
                        </span>
                        <span class="input-group pull-right">
                            <input type="submit" class="btn btn-primary" value="登录" autocomplete="on">
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="footer text-center">
    <p>CopyRight © 中医全动系统集成有限公司</p>
</div>
</body>
<script src="${ctx}/static/lib/jquery.min.js"></script>
<script src="${ctx}/static/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.cookie.js"></script>
<script>
    $(document).ready(function () {
        /*cookie 自动填充*/
        if ($.cookie("username") != undefined) {
            $("input[name='username']").val($.cookie("username"));
            $("input[name='password']").val($.cookie("password"));
            $("input[name='rememberMe']").attr("checked", "checked");
        }
        /*提交时检查是否记住用户名密码*/
        $("#login-form").on("submit", function () {
            if ($("input[name='rememberMe']").attr("checked") == "checked") {
                $.cookie("username", $("input[name='username']").val());
                $.cookie("password", $("input[name='password']").val());
            } else {
                $.cookie("username", $("input[name='username']").val());
                $.cookie("password", $("input[name='password']").val());
            }
            return true;
        });
        /*打勾和去勾时是否记住密码*/
        $("input[name='rememberMe']").on("click", function () {
            if ($(this).attr("checked") == "checked") {
                $.removeCookie("username");
                $.removeCookie("password");
            } else {
                $.cookie("username", $("input[name='username']").val());
                $.cookie("password", $("input[name='password']").val());
            }
        });
        /*按回车自动提交*/
        var autoComplete = function () {
            $("#login-form").bind('keyDown', function (event) {
                if (event.keyCode == 13) {
                    var username = $("input[name='username']").val();
                    var password = $("input[name='password']").val();
                    if (username != null && password != null && username.length * password.length > 0) {
                        $("#login-form").submit();
                    }
                }
            });
        }
        autoComplete();
    });

</script>
</html>