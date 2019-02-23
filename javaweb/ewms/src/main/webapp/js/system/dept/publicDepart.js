layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer;
    var setting = {
        view: {
            showIcon: false,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }
    };
    //初始方法
    $(document).ready(function () {
        assemblingTree("");
    });
    //搜索
    $("#btnSearch").click(function () {
        var str = "";
        if("name"==$("#select select").val()){
            str+="?name="+$("#content").val();
        }else if("code"==$("#select select").val()){
            str+="?code="+$("#content").val();
        }
        assemblingTree(str)
    });
    //拼装树形结构
    function assemblingTree(str) {
        $.ajax({
            type: "post",
            url: "/system/dept/publicDepart.json"+str,
            dataType: "json",
            success: function (ret) {
                var treeObj=$.fn.zTree.init($("#treeDemo"), setting, ret);
                //展开tree
                treeObj.expandAll(true);
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    }

    //tree-点击查看方法
    function onClick(event, treeId, treeNode, clickFlag) {

    }
    //保存
    $("#addNews").click(function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getSelectedNodes();
        if (nodes.length == 1) {
            var data =nodes[0];

            window.parent.obtainPart(data.id,data.name,data.ztId,data.code);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        } else {
            layer.msg("请选择一个父节点");
        }
    });

});







