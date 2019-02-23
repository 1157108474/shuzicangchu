layui.config({
    base: '/plugins/static/js/' // 模块目录
}).use(['form', 'layer', 'treeselect'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    var treeselect = layui.treeselect;

    $.get('/system/menu/listMenuStatus.json', function (ret) {
        treeselect.render({
            elem: "#menutreeselect",
            data: ret.data,
            method: "post"
        });
    });

    // 监听select事件
    form.on('select(type)', function (data) {
        var selectValue = data.value;
        var selectName = data.elem[data.elem.selectedIndex].text;
        console.log(selectName);
        if (selectName == '目录') {
            hideChange();
        } else if (selectName == '页面') {
            showChange();
        }
        form.render('select'); //重新渲染select，这个很重要
    });


    //页面去掉隐藏属性
    function showChange() {
        //页面
        $("#url").removeClass(" layui-disabled");
        $("#authIdentity").removeClass(" layui-disabled");
        $("#menuCode").removeClass(" layui-disabled");
        $("#menutreeselect").removeClass(" layui-disabled");
        $("#url").attr("disabled", false);
        $("#authIdentity").attr("disabled", false);
        $("#menuCode").attr("disabled", false);
        $("#menutreeselect").attr("disabled", false);
    }

    //目录
    function hideChange() {
        //目录
        $("#url").addClass(" layui-disabled");
        $("#authIdentity").addClass(" layui-disabled");
        $("#url").attr("disabled", true);
        $("#authIdentity").attr("disabled", true);
        $("#url").val('');
        $("#authIdentity").val('');
    }


    form.verify({});
    form.on("submit(addNews)", function (data) {
        var menutreeselect = $("#menutreeselect").val();
        var menuName = $("#menuName").val();
        var menuCode = (menuName == menutreeselect? $("#menuCode").val():menutreeselect);
        menuCode =(''==menuCode?'0':menuCode);
        var type = $("#type select").val();

        if (type == '2' && '0' == menuCode) {
            top.layer.msg("页面必须有上级菜单", {time: 500});
            return false;
        }
        var url = "/system/menu/saveMenu.json";
        var json = {
            menuCode: menuCode //上级菜单
            , type: $("#type select").val() //菜单类型
            , code: $("#code").val()	//菜单编码
            , name: $("#name").val()	//菜单名称
            , sort: $("#sort").val()	//菜单排序
            , buttonMode: $("#buttonMode select").val()  //按钮模式
            , status: $("#status select").val()  //状态
            , icon: $("#icon").val()  //菜单图标
            , url: $("#url").val()  //菜单地址
            , authIdentity: $("#authIdentity").val()  //权限标识
        };
        var id = $("#id").val();
        if ('' != id) {
            json['id'] = id;
            url = "/system/menu/updateMenu.json";
        }
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {
            icon: 16,
            time: false,
            shade: 0.8
        });
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