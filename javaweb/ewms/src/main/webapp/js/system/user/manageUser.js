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
    var tableIns = table.render({
        elem: '#manageUser'  //��table id
        , url: '/system/user/listUser.json'  //��������·��
        , cellMinWidth: 80
        , height: "full-30"  //����Ӧ�߶�
        , method: 'post'
        , page: true   //������ҳ
        , limit: 20   //Ĭ��20������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: 'manageUserTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'code', align: 'center', title: 'Ա�����', width: 100}
            , {field: 'name', align: 'center', title: '����', width: 100}
            , {field: 'email', align: 'center', title: '����', width: 120}
            , {
                field: 'parent', align: 'center', title: '��������', width: 240, templet: function (d) {
                    if (null == d.parent) {
                        return '';
                    } else {
                        return d.parent.name;
                    }
                }
            }
            , {
                field: 'offices', align: 'center', title: '����/����', width: 240, templet: function (d) {
                    if (null == d.offices) {
                        return '';
                    } else {
                        return d.offices.name;
                    }
                }
            }
            , {field: 'status', align: 'center', title: '״̬', width: 60, templet: "#showStatus"}
            , {fixed: 'right', title: '����', width: 70, align: 'center', toolbar: '#barOption'}
        ]]
        , trclick: function (data, tr) {
            $("#userId").val(data.id);
            $.post("/system/user/listUserRoles.json?id=" + data.id, function (ret) {
                var roleCodes = [];
                for (var i in ret) {
                    roleCodes.push(ret[i].roleCode);
                }
                $("#roleCodes").val(roleCodes);
            });
            setHide();
            reloadRoleTable();
            //��ʼ��ֵ
            setFormAdd(data);
        }
    });

    //��ɫ�б�
    var tableRole = table.render({
        elem: '#manageRole'  //��table id
        , url: '/system/role/listRole.json'  //��������·��
        , cellMinWidth: 80
        , method: 'post'
        , height: "full-110"  //����Ӧ�߶�
        , page: true   //������ҳ
        , limit: 15   //Ĭ��15������һҳ
        , limits: [15, 30, 45, 60]  //���ݷ�ҳ��
        , id: 'manageRoleTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'roleCode', align: 'center', title: '��ɫ����', width: 80}
            , {field: 'roleName', align: 'center', title: '��ɫ����', width: 120}
            , {
                field: 'roleType', align: 'center', title: '��ɫ����', width: 100, templet: function (d) {
                    if (d.roleType == 1) {
                        return "����Ա��ɫ";
                    } else if (d.roleType == 2) {
                        return "ҵ���ɫ";
                    } else {
                        return "����";
                    }
                }
            }
            , {
                field: 'enabled', align: 'center', title: '״̬', width: 60, templet: function (d) {
                    if (d.enabled == 1) {
                        return "����";
                    } else {
                        return "����";
                    }
                }
            }
            , {field: 'remark', align: 'center', title: '��ע'}
        ]]
        , done: function (res, curr, count) {
            var roleCode = $("#roleCodes").val();
            var roleCodes = roleCode.split(',')
            var data = res.data;
            for (var i = 0; i < data.length; i++) {
                for (var k in roleCodes) {
                    if (data[i].roleCode == roleCodes[k]) {
                        data[i]["LAY_CHECKED"] = 'true';
                        //�ҵ���Ӧ���ݸı乴ѡ��ʽ�����ֳ�ѡ��Ч��
                        var index = data[i]['LAY_TABLE_INDEX'];
                        $('.role tr[data-index=' + index + '] input[type="checkbox"]').prop('checked', true);
                        $('.role tr[data-index=' + index + '] input[type="checkbox"]').next().addClass('layui-form-checked');
                    }
                }
            }
        }
    });
    //��ѯ
    var reload = function (data) {
        table.reload('manageUserTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                name: $("#userName").val()
                ,officesName: $("#offName").val()
                ,parentName: $("#parentName").val()
            }
        })
    };

    //��ѯ��ɫ
    function reloadRoleTable() {
        table.reload('manageRoleTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {}
        });
    }

    //����
    $("#add").on("click", function (e) {

        $("#addNews").show();
        $("#reset").show();
        $("#id").val();
        document.getElementById("userForm").reset();
        //$("input").val('');
    });
    //�༭
    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('manageUserTable');

        var data = checkStatus.data;
        if (data.length != 1) {
            layer.msg("��ѡ��һ����¼", {
                offset: 't',
                anim: 6
            })
        } else {
            setFormAdd(data[0]);
            //��ʾ��ť
            $("#addNews").show();
            $("#reset").show();
        }
    });

    //��ʼ��ֵ
    function setFormAdd(data) {
        document.getElementById("userForm").reset();
        $("#id").val(data.id);
        $("#code").val(data.code);
        $("#name").val(data.name);
        $("#spell").val(data.spell);
        setSexSelect(data.sex);//ƴװ�Ա�
        $("#email").val(data.email);
        if (null != data.parent) {
            $("#departName").val(data.parent.name);
            $("#departId").val(data.parent.id);
        }
        if (null != data.offices) {
            $("#officesName").val(data.offices.name);
            $("#officesId").val(data.offices.id);
        }

        $("#offices").val(null == data.offices ? '' : data.offices.name);
        $("#phone").val(data.phone);
        $("#qq").val(data.qq);
        $("#sort").val(data.sort);
        setStatusSelect(data.status);//ƴװ״̬
        setIsSingleLoginSelect(data.isSingleLogin);//ƴװ�����¼
        $("#memo").val(data.memo);

        $("#isOnline select").val(null == data.isOnline ? 0 : data.isOnline);

        $("#loginTime").val(datetimeformat(data.loginTime));
        $("#loginIp").val(data.loginIp);
        $("#loginCity").val(data.loginCity);
        $("#lastChangePassWord").val(datetimeformat(data.lastChangePassWord));

        $("#isAudit select").val(null == data.isAudit ? 0 : data.isAudit);
        $("#auditby").val(data.auditbyName);
        $("#auditTime").val(datetimeformat(data.auditTime));
        $("#creator").val(data.createName);
        $("#createDate").val(datetimeformat(data.createDate));
        $("#updater").val(data.updaterName);
        $("#updateDate").val(datetimeformat(data.updateDate));

        form.render('select'); //������Ⱦselect���������Ҫ
    };

    //�Ա�������
    function setSexSelect(sex) {
        if (0 == sex) {
            $("#sexSelect").html('<option value="0" selected>��</option><option value="1" >Ů</option>');
        } else {
            $("#sexSelect").html('<option value="0" >��</option><option value="1" selected>Ů</option>');
        }
    }

    //״̬������
    function setStatusSelect(status) {
        if (1 == status) {
            $("#statusSelect").html('<option value="1" selected>����</option><option value="0" >����</option>');
        } else {
            $("#statusSelect").html('<option value="1" >����</option><option value="0" selected>����</option>');
        }
    }

    //״̬������
    function setIsSingleLoginSelect(isSingleLogin) {
        if (1 == isSingleLogin) {
            $("#isSingleLoginSelect").html('<option value="1" selected>��</option><option value="0" >��</option>');
        } else {
            $("#isSingleLoginSelect").html('<option value="1" >��</option><option value="0" selected>��</option>');
        }
    }

    //ȡ��
    $("#reset").click(function () {
        setHide();
    });

    //����
    function setHide() {
        $("#addNews").hide();
        $("#reset").hide();
    };

    //����������
    table.on('tool(manageUser)', function (obj) {
        var data = obj.data;
        if (obj.event === 'operation') {
            openPage('��Ա��Χ-' + data.name, '/system/user/manageOperation.htm?id=' + data.id, '700px', '420px');
        }
    });
    //��������
    $("#rePWD").on("click", function (e) {
        var checkStatus = table.checkStatus('manageUserTable');
        if (checkStatus.data.length != 1) {
            layer.msg("��ѡ��һ����¼", {
                offset: 't',
                anim: 6
            })
        } else {
            var url = "/system/user/openResetPWD.htm?id=" + checkStatus.data[0].id;
            openPage("��������", url, '40%', '55%');
        }
    });

    //��ҳ��
    function openPage(tit, url, width, height) {
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
        var checkStatus = table.checkStatus('manageUserTable');
        var data = checkStatus.data;
        var ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('ȷ��ɾ��ѡ�е���Ա��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post("/system/user/delUsers.json?ids=" + ids, function (data) {
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

    //��֯���ŵ���
    var zfocus = true;
    $("#departName").on("focus", function (e) {
        if (zfocus) {
            zfocus = false;
            var url = "/system/user/publicDepart.htm";
            openPage("��֯����", url, '50%', '75%');
        }
    }).on("blur", function (e) {
        zfocus = true
    });
    //�������ӵ���
    var provider = true;
    $("#officesName").on("focus", function (e) {
        if (provider) {
            provider = false;
            var url = "/system/useDep/generalUseDep.htm";
            openPage("��������", url, '50%', '75%');
        }
    }).on("blur", function (e) {
        provider = true
    });
    //�����ɫ����
    $("#addUserRole").click(function () {
        var checkStatus = table.checkStatus('manageRoleTable');
        var userId = $("#userId").val();
        var data = checkStatus.data;
        var roleCodes = [];
        for (var i in data) {
            roleCodes.push(data[i].roleCode);
        }
        $.post("/system/user/saveUserRoles.json?id=" + userId + "&roleCodes=" + roleCodes, function (data) {
            layer.msg(data.message);
        });
    });
    //����
    form.on("submit(addNews)", function (data) {
        var url = "/system/user/saveUser.json";
        var json = {
            code: $("#code").val()  //Ա������
            , name: $("#name").val()  //����
            , spell: $("#spell").val()  //����ƴ��
            , sex: $("#sex select").val()  //�Ա�
            , email: $("#email").val()  //����
            , parentId: $("#departId").val()  //����Id
            , officesId: $("#officesId").val()  //��������ID
            , phone: $("#phone").val()  //��ϵ��ʽ
            , qq: $("#qq").val()  //QQ
            , sort: $("#sort").val()  //����
            , status: $("#status select").val()  //״̬
            , isSingleLogin: $("#isSingleLogin select").val()  //�����¼
            , memo: $("#memo").val()  //��ע

        };
        var id = $("#id").val();
        if ('' != id) {
            json['id'] = id;
            url = "/system/user/updateUser.json";
        }
        //����loading
        var index = top.layer.msg('�����ύ�У����Ժ�', {icon: 16, time: false, shade: 0.8});
        // ʵ��ʹ��ʱ���ύ��Ϣ
        $.post(url, json, function (data) {
            top.layer.close(index);
            top.layer.msg(data.message, {
                time: 500
            });
            if ('1' == data.status) {
                setHide();
                tableIns.reload();
            }
        });
        return false;
    })

});

//��ȡ����ҳ��ش�����
function obtainPart(id, name, ztId, code) {
    document.getElementById("departName").value = name;
    document.getElementById("departId").value = id;
}

//��ȡ��������ҳ��ش�����
function UseDepGeneral(data) {
    document.getElementById("officesName").value = data[0].name;
    document.getElementById("officesId").value = data[0].id;
}

