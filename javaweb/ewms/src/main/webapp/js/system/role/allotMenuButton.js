var setting = {
    check: {
        enable: true
    },
    data: {
        key: {
            title: "title"
        },
        simpleData: {
            enable: true
        }
    }
};
layui.use(['form', 'tree', 'layer', 'table', 'laytpl'], function () {
    var layer = layui.layer,
        form = layui.form,
        jquery = layui.jquery,
        laytpl = layui.laytpl;
    var tree1;
    var ran = Math.random();
    $(function () {
        $.ajax({
            type: "post",
            url: "/system/role/listMenuButton.json?roleCode="+$("#roleCode").val(),
            dataType: "json",
            success: function (ret) {
                var treeObj = $.fn.zTree.init($("#treeDemo"), setting, ret);
                //展开tree
                treeObj.expandAll(true);
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    });

    $("#addNews").click(function () {

        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            oCks = zTree.getCheckedNodes(true);
        var menuIds = []; //这是方法
        var buttonIds = []; //这是方法

        for (var i = 0; i < oCks.length; i++) {
            var id =oCks[i].id;
            if (id.search("m-") != -1) {
                id=id.replace("m-","");
                menuIds.push(id);
            } else {
                buttonIds.push(id);
            }
        }
        var url = "/system/role/saveRoleMenuButton.json?menuIds="+menuIds+"&buttonIds="+buttonIds;
        var json = {
            roleCode: $("#roleCode").val()  //角色ID
        };
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        //实际使用时的提交信息
        $.post(url,json,function(date){
            setTimeout(function(){
                top.layer.close(index);
                top.layer.msg(date.message);
                if ('1'==date.status) {
                    var index2 = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index2);
                }
            },500);
        })
    });

});