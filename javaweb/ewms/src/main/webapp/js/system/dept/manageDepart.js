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
    //ƴװ���νṹ
    function assemblingTree() {
        $.ajax({
            type: "post",
            url: "/system/dept/listDepart.json",
            dataType: "json",
            success: function (ret) {
                var treeObj=$.fn.zTree.init($("#treeDemo"), setting, ret);
                //չ��tree
                treeObj.expandAll(true);
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    }
    //tree-����鿴����
    function onClick(event, treeId, treeNode, clickFlag) {
        if (treeNode.id == 0) {
            setFormNull();
        } else {
            setForm(treeNode.id, "/system/dept/showDepart.json");
            $("#addNews").hide();
            $("#reset").hide();
        }
    }

    //tree-���޸�
    function beforeEditName(treeId, treeNode) {
        setForm(treeNode.id, "/system/dept/editDepart.json");
        $("#addNews").show();
        $("#reset").show();
        return false;
    }

    //tree-��ɾ��
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

    //tree-������
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

    //��������
    function setForm(id, url) {
        $.ajax({
            type: "GET",
            url: url,
            dataType: "json",
            data: {"id": id},
            success: function (ret) {
                if (ret.status == 0) {
                    layer.alert('��ȡ��Ϣʧ�ܣ�' + ret.message);
                } else {
                    var data = ret.data;
                    $("#id").val(data.id);
                    $("#parentId").val(null == data.parent ? 0 : data.parent.id);
                    $("#parentName").val(null == data.parent ? '' : data.parent.name);
                    $("#name").val(data.name);
                    $("#code").val(data.code);
                    $("#levelCount").val(data.levelCount);
                    setTypeSelect(data.type);//ƴװ����
                    setStatusSelect(data.status);//ƴװ״̬
                    $("#sort").val(data.sort);
                    $("#memo").val(data.memo);
                    form.render('select'); //������Ⱦselect���������Ҫ
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    }
    //����������
    function setTypeSelect(type) {
        if(0==type){
            $("#typeSelect").html('<option value="0" selected>�ֲ�</option><option value="1" >����</option>');
        }else {
            $("#typeSelect").html('<option value="0" >�ֲ�</option><option value="1" selected>����</option>');
        }
    }
    //״̬������
    function setStatusSelect(status) {
        if(1==status){
            $("#statusSelect").html('<option value="1" selected>����</option><option value="0" >����</option>');
        }else {
            $("#statusSelect").html('<option value="1" >����</option><option value="0" selected>����</option>');
        }
    }
    //���
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
    //��ʼ��ֵ
    function setFormAdd(node) {
        $("#parentId").val(node.id);
        $("#parentName").val(node.name);
        $("#sort").val('100');
        $("#levelCount").val(node.levelCount+1);
        $("#addNews").show();
        $("#reset").show();
    };
    //ɾ��ʵ�ַ���
    function setFormDel(node) {
        var json ={"id": node.id,"type":node.type};
        layer.confirm('ȷ��ɾ�� --'+ node.name + ' ��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
            $.post("/system/dept/delDepart.json",json, function (data) {
                layer.msg(data.message);
                assemblingTree();
            })
        });

    };
    //����
    // $("#add").click(function () {
    //     var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
    //         nodes = zTree.getSelectedNodes();
    //     if (nodes.length == 1) {
    //         setFormNull();
    //         setFormAdd(nodes[0]);
    //     } else {
    //         layer.msg("��ѡ��һ�����ڵ�");
    //     }
    // });
    //�༭
    // $("#edit").click(function () {
    //     var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
    //         nodes = zTree.getSelectedNodes();
    //     if (nodes.length == 1) {
    //         setForm(nodes[0].id, "/system/dept/editDepart.json");
    //         $("#addNews").show();
    //         $("#reset").show();
    //     } else {
    //         layer.msg("��ѡ��һ�����ڵ�");
    //     }
    // });

    //ɾ��
    // $("#del").click(function () {
    //     var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
    //         nodes = zTree.getSelectedNodes();
    //     if (nodes.length == 1) {
    //         setFormDel(nodes[0]);
    //     } else {
    //         layer.msg("��ѡ��һ�����ڵ�");
    //     }
    // });
    //����
    form.on("submit(addNews)", function (obj) {
        var url = "/system/dept/saveDepart.json";
        var json = {
            code: $("#code").val()  //����
            ,name: $("#name").val()  //����
            ,type: $("#type select").val() //����
            ,sort: $("#sort").val() //����
            ,levelCount: $("#levelCount").val()//����
            ,status: $("#status select").val() //״̬
            ,memo: $("#memo").val()
            ,parentId: $("#parentId").val()//�ϼ�ID
        };
        if ('' != $("#id").val()) {
            json['id'] = $("#id").val();
            url = "/system/dept/updateDepart.json";
        }
        //����loading
        var index = top.layer.msg('�����ύ�У����Ժ�', {icon: 16, time: false, shade: 0.8});
        // ʵ��ʹ��ʱ���ύ��Ϣ
        jquery.post(url, json, function (data) {
            setHide();
            top.layer.close(index);
            top.layer.msg(data.message, {time: 500});
            form.render("select");
            assemblingTree();
        });
        return false;
    });
    //ȡ��
    $("#reset").click(function () {
        setHide();
    });
    //���
    function setHide() {
        $("#addNews").hide();
        $("#reset").hide();
    };
});







