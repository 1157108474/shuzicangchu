layui.use(['form','layer'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    // 绑定键盘按下事件
    $(document).keypress(function(e) {
        // 回车键事件
        if(e.which == 13) {
            submit();
        }
    });

    $("#submit").on("click", function (data) {
        submit();
    });

    function submit() {
        var data = {
            "code" : $("#code").val(),
            "passWord" : $("#passWord").val(),
        };

        var url = "/system/auth/login.json";
        //弹出loading
        var index = top.layer.msg('登录中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.post(url, data, function(data) {
            setTimeout(function(){
                top.layer.close(index);
                top.layer.msg(data.message);
                if ('1'==data.status) {
                    self.location = '/ewms.jsp';
                }
            },500);

        });
        return false;
    }
});