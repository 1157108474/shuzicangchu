layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    form.verify({});
    form.on("submit(addNews)", function (data) {
        if ($("#newPWD").val() != $("#confirmPWD").val()) {
            top.layer.msg("两次密码输入不一致", {
                time: 500
            });
            return false;
        }
        var url = "/system/user/updateResetPWD.json";
        var json = {
            id: $("#id").val() //id
            , passWord: $("#newPWD").val()
        };
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(url, json, function (data) {
            top.layer.close(index);
            top.layer.msg(data.message, {
                time: 500
            });
            if ('1' == data.status) {
                if ($("#source").val() !== "changeOwnPwd") {
                    parent.location.reload();
                }
                else {
                    $.ajax({
                        url: "/system/auth/logout.json",
                        method: "post",
                        success: function (res) {
                            if (res.status === 1) {
                                layer.msg("系统已注销，请您重新用新密码登录！", {icon: 1, time: 2000}, function () {
                                    parent.location.href = "/login.html";
                                });
                            }
                        }
                    });
                }
            }

        });
        return false;
    })
});