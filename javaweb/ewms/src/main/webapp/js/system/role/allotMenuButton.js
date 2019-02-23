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
                //չ��tree
                treeObj.expandAll(true);
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    });

    $("#addNews").click(function () {

        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            oCks = zTree.getCheckedNodes(true);
        var menuIds = []; //���Ƿ���
        var buttonIds = []; //���Ƿ���

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
            roleCode: $("#roleCode").val()  //��ɫID
        };
        //����loading
        var index = top.layer.msg('�����ύ�У����Ժ�',{icon: 16,time:false,shade:0.8});
        //ʵ��ʹ��ʱ���ύ��Ϣ
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