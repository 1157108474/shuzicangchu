layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    form.verify({
        code: function(value, item){
            var regex = /^[a-zA-Z0-9_]{0,}$/;
            if(value.replace(/^\s+|\s+$/g,"") ==''){
                return '必填项不能为空';
            }
            if (!value.match(regex)) {
                return '编码不能含有中文或特殊字符';
            }
        }
    });
    form.on("submit(addNews)", function (data) {
        var url = "/system/role/saveRole.json";
        var json = {
            roleCode: $("#roleCode").val()  //角色代码
            ,roleName: $("#roleName").val()  //角色名称
            ,roleType: $("#roleType select").val() //角色类型
            ,sort: $("#sort").val() //排序
            ,enabled: $("#enabled select").val() //状态
            ,remark: $("#remark").val()
        };
        if ('' != $("#id").val()) {
            url = "/system/role/updateRole.json";
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