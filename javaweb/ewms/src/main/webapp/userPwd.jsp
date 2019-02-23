<%@ page language="java" import="java.util.*" pageEncoding="gbk" %>


<!DOCTYPE HTML>
<html>

<head>
    <title>修改密码</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body style="width: 90%">
<form class="layui-form layui-form-pane" style="margin-top: 20px;" method="post" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">原密码</label>
        <div class="layui-input-block">
            <input type="password" id="jiuPassword" lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-block">
            <input type="password" id="newPassword" lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input type="password" id="againPassword" lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input"/>
        </div>
    </div>
    <!-- 按钮组 -->
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit="" lay-filter="btnSubmit">立即提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>

</form>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script>
    layui.use(['form', 'layer', 'layedit', 'laydate', 'upload'], function () {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            laypage = layui.laypage,
            upload = layui.upload,
            layedit = layui.layedit,
            laydate = layui.laydate,
            $ = layui.jquery;
        //监听提交
        form.on('submit(btnSubmit)', function (data) {
            if ($("#newPassword").val() != $("#againPassword").val()) {
                layer.msg('前后密码不一致', {
                    offset: 't',
                    anim: 6
                });
                return false;
            }
            var json = {
                jiuPassword: $("#jiuPassword").val(),  //原密码
                newPassword: $("#newPassword").val()  //新密码
            };
            var url = '/system/user/usersPass.json';
            //弹出loading
            var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            // 实际使用时的提交信息
            $.post(url, json, function (data) {
                top.layer.close(index);
                top.layer.msg(data.message, {
                    time: 500
                });
                if ('1' == data.status) {
                    parent.location.reload();
                }
            });
            return false;
        });


    });
</script>

</body>
</html>