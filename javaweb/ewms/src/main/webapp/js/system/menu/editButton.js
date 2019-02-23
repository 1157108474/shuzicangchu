layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    form.verify({

    });
    form.on("submit(addNews)", function (data) {
        var url = "/system/button/saveButton.json";
        var json = {
            code: $("#code").val()  //按钮编码
            ,menuCode: $("#menuCode").val()  //关联菜单
            ,name: $("#name").val()  //按钮名称
            ,sort: $("#sort").val()  //按钮排序
            ,status: $("#status select").val()  //按钮状态
            ,url: $("#url").val()  //请求地址
            ,authIdentity: $("#authIdentity").val()  //权限标识
            ,menuId: $("#menuId").val()  //所属菜单ID
        };
        var id = $("#id").val();
        if ('' != id) {
            url = "/system/button/updateButton.json";
        }
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
    })
});