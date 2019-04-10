layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //�����б�
    var tableIns =table.render({
        elem: '#manageRole'  //��table id
        , url: '/system/role/listRole.json'  //��������·��
        , cellMinWidth: 80
        , height :"full-60"  //����Ӧ�߶�
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: 'manageRoleTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'roleCode', align: 'center', title: '��ɫ����', width: 100}
            , {field: 'roleName', align: 'center', title: '��ɫ����', width: 120}
            , {field: 'roleType', align: 'center', title: '��ɫ����', width: 120,templet: function (d) {
                if (d.roleType == 1) {
                    return "����Ա��ɫ";
                } else if(d.roleType == 2){
                    return "ҵ���ɫ";
                }else {
                    return "����";
                }
            }}
            , {field: 'sort', align: 'center', title: '����', width: 60}
            , {
                field: 'enabled', align: 'center', title: '״̬', width: 80, templet: function (d) {
                    if (d.enabled == 1) {
                        return "����";
                    } else {
                        return "����";
                    }
                }
            }
            , {field: 'remark', align: 'center', title: '��ע'}
        ]]
        ,trclick:function (data,tr) {
             $("#roleCode").val(data.roleCode);
             $("#tabName").text("��ɫ��"+data.roleName+"�����û�");
             reloadUserTable(data.roleCode);
        }

    });
    //�����б�
    var tableUser =table.render({
        elem: '#manageUser'  //��table id
        , url: '/system/role/listRoleUser.json'  //��������·��
        , cellMinWidth: 80
        , height :"full-136"  //����Ӧ�߶�
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ������һҳ
        , limits: [10, 20, 30, 50]  //���ݷ�ҳ��
        , id: 'manageUserTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'name', align: 'center', title: '����', width: 100}
            , {field: 'parent', align: 'center', title: '��������', width: 240,templet: function (d) {
                if (null==d.parent ) {
                    return '';
                } else {
                    return d.parent.name;
                }
            }}
            , {field: 'status', align: 'center', title: '״̬', width: 60,templet: "#showUserStatus"}
        ]]
    });

    //��ѯ��ɫ
    var reload = function (data) {
        table.reload('manageRoleTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                roleName: $("#roleName").val()
            }
        })
    };
    //��ѯ�û�
    function  reloadUserTable(roleCode ) {
        table.reload('manageUserTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where:  {
                roleCode: roleCode
            }
        });
    }
    //����
    $("#add").on("click", function (e) {
        openPage("������ɫ", "/system/role/addRole.htm","40%","70%");
    });
    //�༭
    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('manageRoleTable');
        if (checkStatus.data.length != 1) {
            layer.msg("��ѡ��һ����¼���б༭", {
                offset: 't',
                anim: 6
            })
        } else {
            openPage("�༭��ɫ", "/system/role/editRole.htm?r.roleCode=" + checkStatus.data[0].roleCode,"40%","70%");
        }
    });
    //����Ȩ��
    $("#allotMenuButton").on("click", function (e) {
        var checkStatus = table.checkStatus('manageRoleTable');
        if (checkStatus.data.length != 1) {
            layer.msg("��ѡ��һ����¼���б༭", {
                offset: 't',
                anim: 6
            })
        } else {
            openPage("����Ȩ��", "/system/role/allotMenuButton.htm?roleCode=" + checkStatus.data[0].roleCode,"40%","80%");
        }
    });
    //��ҳ��
    function openPage(tit, url,width,height) {
        layui.layer.open({
            title: tit
            , type: 2
            , fixed: false
            , area: [width, height]
            , content: url
        })
    }
    //����ɾ��
    $("#delAll").click(function () {
        var checkStatus = table.checkStatus('manageRoleTable');
        var  data = checkStatus.data;
        var roleCodes = [];
        if (data.length > 0) {
            for (var i in data) {
                roleCodes.push(data[i].roleCode);
            }
            layer.confirm('ȷ��ɾ��ѡ�еĽ�ɫ��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post("/system/role/delRoles.json?roleCodes=" + roleCodes , function (data) {
                    tableIns.reload();
                    layer.msg(data.message);
                    layer.close(index);
                })
            })
        } else {
            layer.msg('��ѡ��һ�����ݡ�', {
                offset: 't',
                anim: 6
            });
        }
    });


    //�����Աҳ��
    $("#addUser").on("click", function (e) {
        var roleCode = $("#roleCode").val();
        if(""!=roleCode){
            openPage("��Ա��Ϣ", "/system/role/departUser.htm?roleCode=" + roleCode, "85%", "90%");
        }else {
            layer.msg("��ѡ��һ����ɫ", {
                offset: 't',
                anim: 6
            })
        }

    });

    //�Ƴ��û�
    $("#delUser").on("click", function (e) {
        var checkStatus = table.checkStatus('manageUserTable');
        var  data = checkStatus.data;
        var ids = [];

        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            var roleCode = $("#roleCode").val();
            var url ="/system/role/delRoleUsers.json?roleCode="+roleCode+"&ids="+ids;
            layer.confirm('ȷ���Ƴ�ѡ�е��û���', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post(url, function (data) {
                    reloadUserTable();
                    layer.msg(data.message);
                    layer.close(index);
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


