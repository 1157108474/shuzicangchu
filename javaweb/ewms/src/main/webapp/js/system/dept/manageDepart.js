layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer;
    var jquery = layui.jquery;
    var setting = {
        view: {
            showIcon: false,
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        edit: {
            enable: true,
            showRemoveBtn: showRemoveBtn,
            showRenameBtn: showRenameBtn
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick,
            beforeRemove: beforeRemove,
            beforeEditName: beforeEditName
        }
    };
    assemblingTree();
    //拼装树形结构
    function assemblingTree() {
        $.ajax({
            type: "post",
            url: "/system/dept/listDepart.json",
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
        if (treeNode.id == 0) {
            setFormNull();
        } else {
            setForm(treeNode.id, "/system/dept/showDepart.json");
            $("#addNews").hide();
            $("#reset").hide();
        }
    }

    //tree-树修改
    function beforeEditName(treeId, treeNode) {
        setForm(treeNode.id, "/system/dept/editDepart.json");
        $("#addNews").show();
        $("#reset").show();
        return false;
    }

    //tree-树删除
    function beforeRemove(treeId, treeNode) {
        setFormDel(treeNode);

        return false;
    }

    function showRemoveBtn(treeId, treeNode) {
        return treeNode.id != 100001;
    }

    function showRenameBtn(treeId, treeNode) {
        return treeNode.id != 0;
    }

    //tree-树新增
    function addHoverDom(treeId, treeNode) {

        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='add node' onfocus='this.blur();' ></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_" + treeNode.tId);
        if (btn) btn.bind("click", function () {
            setFormNull();
            setFormAdd(treeNode);
            return false;
        });
    };

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
    };

    //联动方法
    function setForm(id, url) {
        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",
            data: {"id": id},
            success: function (ret) {
                if (ret.status == 0) {
                    layer.alert('获取信息失败：' + ret.message);
                } else {
                    var data = ret.data;
                    $("#id").val(data.id);
                    $("#parentId").val(null == data.parent ? 0 : data.parent.id);
                    $("#parentName").val(null == data.parent ? '' : data.parent.name);
                    $("#name").val(data.name);
                    $("#code").val(data.code);
                    $("#levelCount").val(data.levelCount);
                    setTypeSelect(data.type);//拼装类型
                    setStatusSelect(data.status);//拼装状态
                    $("#sort").val(data.sort);
                    $("#memo").val(data.memo);
                    form.render('select'); //重新渲染select，这个很重要
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    }
    //类型下垃框
    function setTypeSelect(type) {
        if(0==type){
            $("#typeSelect").html('<option value="0" selected>分部</option><option value="1" >部门</option>');
        }else {
            $("#typeSelect").html('<option value="0" >分部</option><option value="1" selected>部门</option>');
        }
    }
    //状态下垃框
    function setStatusSelect(status) {
        if(1==status){
            $("#statusSelect").html('<option value="1" selected>启用</option><option value="0" >禁用</option>');
        }else {
            $("#statusSelect").html('<option value="1" >启用</option><option value="0" selected>禁用</option>');
        }
    }
    //清空
    function setFormNull() {
        $("#id").val('');
        $("#parentId").val('');
        $("#parentName").val('');
        $("#code").val('');
        $("#name").val('');
        $("#sort").val('');
        $("#levelCount").val('');
        $("#memo").val('');
    };
    //初始赋值
    function setFormAdd(node) {
        $("#parentId").val(node.id);
        $("#parentName").val(node.name);
        $("#sort").val('100');
        $("#levelCount").val(node.levelCount+1);
        $("#addNews").show();
        $("#reset").show();
    };
    //删除实现方法
    function setFormDel(node) {
        var json ={"id": node.id,"type":node.type};
        layer.confirm('确认删除 --'+ node.name + ' 吗？', {icon: 3, title: '提示信息'}, function (index) {
            $.post("/system/dept/delDepart.json",json, function (data) {
                layer.msg(data.message);
                assemblingTree();
            })
        });

    };
    //新增
    // $("#add").click(function () {
    //     var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
    //         nodes = zTree.getSelectedNodes();
    //     if (nodes.length == 1) {
    //         setFormNull();
    //         setFormAdd(nodes[0]);
    //     } else {
    //         layer.msg("请选择一个父节点");
    //     }
    // });
    //编辑
    // $("#edit").click(function () {
    //     var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
    //         nodes = zTree.getSelectedNodes();
    //     if (nodes.length == 1) {
    //         setForm(nodes[0].id, "/system/dept/editDepart.json");
    //         $("#addNews").show();
    //         $("#reset").show();
    //     } else {
    //         layer.msg("请选择一个父节点");
    //     }
    // });

    //删除
    // $("#del").click(function () {
    //     var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
    //         nodes = zTree.getSelectedNodes();
    //     if (nodes.length == 1) {
    //         setFormDel(nodes[0]);
    //     } else {
    //         layer.msg("请选择一个父节点");
    //     }
    // });
    //保存
    form.on("submit(addNews)", function (obj) {
        var url = "/system/dept/saveDepart.json";
        var json = {
            code: $("#code").val()  //编码
            ,name: $("#name").val()  //名称
            ,type: $("#type select").val() //类型
            ,sort: $("#sort").val() //排序
            ,levelCount: $("#levelCount").val()//级别
            ,status: $("#status select").val() //状态
            ,memo: $("#memo").val()
            ,parentId: $("#parentId").val()//上级ID
        };
        if ('' != $("#id").val()) {
            json['id'] = $("#id").val();
            url = "/system/dept/updateDepart.json";
        }
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        jquery.post(url, json, function (data) {
            setHide();
            top.layer.close(index);
            top.layer.msg(data.message, {time: 500});
            form.render("select");
            assemblingTree();
        });
        return false;
    });
    //取消
    $("#reset").click(function () {
        setHide();
    });
    //清空
    function setHide() {
        $("#addNews").hide();
        $("#reset").hide();
    };
});







