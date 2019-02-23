<%@ page language="java" import="java.util.*" pageEncoding="gbk" %>


<!DOCTYPE HTML>
<html>

<head>
    <title>�޸�����</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body style="width: 90%">
<form class="layui-form layui-form-pane" style="margin-top: 20px;" method="post" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">ԭ����</label>
        <div class="layui-input-block">
            <input type="password" id="jiuPassword" lay-verify="required" placeholder="������" autocomplete="off"
                   class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">������</label>
        <div class="layui-input-block">
            <input type="password" id="newPassword" lay-verify="required" placeholder="������" autocomplete="off"
                   class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">ȷ������</label>
        <div class="layui-input-block">
            <input type="password" id="againPassword" lay-verify="required" placeholder="������" autocomplete="off"
                   class="layui-input"/>
        </div>
    </div>
    <!-- ��ť�� -->
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit="" lay-filter="btnSubmit">�����ύ</button>
        <button type="reset" class="layui-btn layui-btn-primary">����</button>
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
        //�����ύ
        form.on('submit(btnSubmit)', function (data) {
            if ($("#newPassword").val() != $("#againPassword").val()) {
                layer.msg('ǰ�����벻һ��', {
                    offset: 't',
                    anim: 6
                });
                return false;
            }
            var json = {
                jiuPassword: $("#jiuPassword").val(),  //ԭ����
                newPassword: $("#newPassword").val()  //������
            };
            var url = '/system/user/usersPass.json';
            //����loading
            var index = top.layer.msg('�����ύ�У����Ժ�', {icon: 16, time: false, shade: 0.8});
            // ʵ��ʹ��ʱ���ύ��Ϣ
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