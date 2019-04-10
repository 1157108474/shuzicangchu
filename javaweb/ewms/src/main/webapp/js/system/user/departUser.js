layui.use(['form', 'table', 'layer'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;

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
    //ƴװ���νṹ
    $(document).ready(function () {
        $.ajax({
            type: "post",
            url: "/system/dept/publicDepart.json",
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
    });

    //�û������б�
    var tableIns =table.render({
        elem: '#manageUser'  //��table id
        , url: '/system/user/listNoRoleUser.json'  //��������·��
        , cellMinWidth: 80
        , height: 'full-60'  //����Ӧ�߶�
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ������һҳ
        , limits: [10, 20, 30, 50]  //���ݷ�ҳ��
        , where: {
            roleCode: $("#roleCode").val()
        }
        , id: 'manageUserTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'code', align: 'center', title: 'Ա�����', width: 100}
            , {field: 'name', align: 'center', title: '����', width: 100}
            , {field: 'parent', align: 'center', title: '��������', minWidth: 180,templet: function (d) {
                if (null==d.parent ) {
                    return '';
                } else {
                    return d.parent.name;
                }
            }}
            , {field: 'status', align: 'center', title: '״̬', width: 80 ,templet: "#showStatus"}
        ]]
    });
    //����
    $("#btnSearch").click(function () {
        var json = [];
        if ('name' == $("#select select").val()) {
            json['name'] = $("#content").val();

        } else if ('code' == $("#select select").val()) {
            json['code'] = $("#content").val();
        }
        json['roleCode'] = $("#roleCode").val();
        table.reload("manageUserTable", {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            }
            , where: json
        })
    });
    //tree-����鿴����
    function onClick(event, treeId, treeNode, clickFlag) {
         table.reload("manageUserTable", {
             page: {
                 curr: 1 //���´ӵ� 1 ҳ��ʼ
             }
             , where:{
                 departId:treeNode.id
             }
         })
    }
    //����
    $("#addNews").click(function () {
        var checkStatus = table.checkStatus('manageUserTable');
        var data = checkStatus.data;
        var ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            var roleCode = $("#roleCode").val();
            var url ="/system/role/saveRoleUsers.json?roleCode="+roleCode+"&ids="+ids;
            layer.confirm('ȷ�����ѡ�е��û���', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post(url, function (data) {
                    layer.msg(data.message);
                    layer.close(index);
                    if ('1' == data.status) {
                        parent.location.reload();
                    }
                })
            })
        } else {
            layer.msg('��ѡ��һ�����ݡ�', {
                offset: 't',
                anim: 6
            });
        }

    });
});







