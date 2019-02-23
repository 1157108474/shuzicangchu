<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>数字化厂库物资系统</title>
    <script language="JavaScript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/css/login.css" media="all"/>
    <script language="javascript">
        $(function () {
            $('.loginbox').css({
                'position': 'absolute',
                'left': ($(window).width() - 692) / 2
            });
            $(window).resize(function () {
                $('.loginbox').css({
                    'position': 'absolute',
                    'left': ($(window).width() - 692) / 2
                });
            })
        });
    </script>
</head>
<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>

<div class="loginbody">
    <span class="systemlogo"></span>
    <div class="loginbox">

        <ul>
            <li><input id="code" name="code" type="text" class="loginuser" placeholder="请输入账号"
                       onclick="JavaScript:this.value=''"/></li>
            <li><input id="passWord" name="password" type="password" class="loginpwd" placeholder="请输入密码"
                       onclick="JavaScript:this.value=''"/></li>

            <li><input type="button" class="loginbtn" value="登入" id="submit"/></li>
        </ul>
    </div>
</div>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
</body>
<%--<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>数字化厂库物资系统</title>
    <link rel="stylesheet" href="/css/login.css" media="all"/>
    <link rel="stylesheet" href="/css/skin.css" media="all">
    <link rel="stylesheet" href="/css/cssbtn.css" media="all">
</head>
<body style="background-color: rgb(28, 119, 172); background-repeat: no-repeat; background-position: -32.9px 0px; overflow: hidden;">
<div id="mainBody">
    <div id="cloud1" class="cloud" style="background-position: -57.5px 100px;">
    </div>
    <div id="cloud2" class="cloud" style="background-position: 604px 460px;">
    </div>
</div>
<div class="loginbody">
    <div class="loginName">
        <img id="nwebname" src="/images/webName1.png">
    </div>
    <div class="loginbox">
        <div class="systemlogo">
        </div>
        <div style="margin-top: 18px; margin-left: 288px">
            <p style="margin-bottom:10px">
                <input type="text" id="code" name="code" placeholder="请输入账号" class="loginuser" tabindex="1">
            </p>
            <p>
                <input type="passWord" id="passWord" name="password" tabindex="2" placeholder="请输入密码" class="loginpwd">
            </p>

            <div class="login_actions" style="margin-top:15px;">
                <a href="javascript:void(0)" class="cssbtn mini-button mini-button-primary" title="登入"
                   style="padding: 12px 50px;" id="submit">登入</a>
            </div>
        </div>
        <div class="login_msg"></div>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/jquery.js"></script>
<script type="text/javascript" src="/js/cloud.js"></script>
<script type="text/javascript" src="/js/login.js"></script>--%>

</html>