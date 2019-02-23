<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">
    <title>数字化仓库管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="css/global.css" media="all">
    <link rel="stylesheet" href="css/inputSearch.css">
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
</head>

<body style="overflow-y:hidden;">
<div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #0088d0;height: 60px;background-image:url('/static/images/bg.jpg');background-size:100% ">
    <div class="layui-header header header-demo">
        <div class="layui-main">
            <div class="admin-login-box" style="text-align:center; vertical-align:middle;">
                <span class="logo" style="font-size: 21px;left: 0;font-weight:400;color:white">数字化仓库物资库系统</span>
                <div class="admin-side-toggle">

                    <i class="fa fa-exchange" aria-hidden="true"></i>
                </div>
            </div>

            <ul class="layui-nav admin-header-item">
                <li class="layui-nav-item ">
                    <a href="javascript:;" class="admin-header-user">
                        <span>欢迎您：${userName}，登录本系统！</span>
                        <span class="layui-nav-more"></span>
                    </a>

                    <dl class="layui-nav-child crms-nav-right">
                        <dd>
                            <a href="javascript:;" id="changePwd">
                                <i class="fa fa-key" aria-hidden="true"></i>
                                <cite>修改密码</cite>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;" id="logout">
                                <i class="fa fa-sign-out" aria-hidden="true"></i>
                                <cite>退出</cite>
                            </a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-side layui-bg-black" id="admin-side"
         style="background-color:#48b8f6; overflow-y:scroll; overflow-x:hidden;">
        <div class="layui-side-scroll" style="height: inherit;" id="admin-navbar-side" lay-filter="side"></div>
    </div>

    <div class="layui-body" style="bottom: 0;border-left: solid 1px #0085cf;" id="admin-body">
        <div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
            <ul class="layui-tab-title">

                <li class="layui-this">
                    <i class="fa fa-dashboard" aria-hidden="true"></i>
                    <cite>首页</cite>
                </li>

            </ul>
            <div class="layui-tab-content" style="height: 480px; padding: 5px 0 0 0;">
                <div id="demo1" class="layui-tab-item layui-show">
                    <iframe src="main.html"></iframe>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-footer footer footer-demo" id="admin-footer">
        <div class="layui-main">
            <p>Copy Right 2018 &copy;<a>中煤信息有限责任公司</a></p>
        </div>
    </div>
    <div class="site-tree-mobile layui-hide">
        <i class="layui-icon">&#xe602;</i>
    </div>
    <div class="site-mobile-shade"></div>
</div>
</body>
<script type="text/javascript" src="plugins/layui/layui.js"></script>
<script type="text/javascript" src="js/compatible.js"></script>
<script type="text/javascript" src="datas/nav.js"></script>
<script src="js/index.js"></script>
<script>
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery,
            layer = layui.layer;
        var form = layui.form;

        $("#changePwd").on("click", function () {
            openPage("重置密码", "/system/user/openResetPWD.htm?id=<%=session.getAttribute("userId")%>&source=changeOwnPwd", '45%', '50%');
        });

        $('#logout').on('click', function () {
            $.ajax({
                url: "/system/auth/logout.json",
                method: "post",
                success: function (res) {
                    if (res.status === 1) {
                        layer.msg(res.message, {icon: 1, time: 2000}, function () {
                            window.location.href = "/login.html";
                        });
                    }
                }
            });
        });

        //打开页面
        function openPage(tit, url, width, height) {
            layui.layer.open({
                title: tit
                , type: 2
                , fixed: false
                , area: [width, height]
                , content: url
            })
        }
    });

</script>
</html>