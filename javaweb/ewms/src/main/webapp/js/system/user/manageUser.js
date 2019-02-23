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
    //数据列表
    var tableIns = table.render({
        elem: '#manageUser'  //绑定table id
        , url: '/system/user/listUser.json'  //数据请求路径
        , cellMinWidth: 80
        , height: "full-30"  //自适应高度
        , method: 'post'
        , page: true   //开启分页
        , limit: 20   //默认20条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: 'manageUserTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'code', align: 'center', title: '员工编号', width: 100}
            , {field: 'name', align: 'center', title: '姓名', width: 100}
            , {field: 'email', align: 'center', title: '邮箱', width: 120}
            , {
                field: 'parent', align: 'center', title: '所属部门', width: 240, templet: function (d) {
                    if (null == d.parent) {
                        return '';
                    } else {
                        return d.parent.name;
                    }
                }
            }
            , {
                field: 'offices', align: 'center', title: '科室/区队', width: 240, templet: function (d) {
                    if (null == d.offices) {
                        return '';
                    } else {
                        return d.offices.name;
                    }
                }
            }
            , {field: 'status', align: 'center', title: '状态', width: 60, templet: "#showStatus"}
            , {fixed: 'right', title: '操作', width: 70, align: 'center', toolbar: '#barOption'}
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
            //初始赋值
            setFormAdd(data);
        }
    });

    //角色列表
    var tableRole = table.render({
        elem: '#manageRole'  //绑定table id
        , url: '/system/role/listRole.json'  //数据请求路径
        , cellMinWidth: 80
        , method: 'post'
        , height: "full-110"  //自适应高度
        , page: true   //开启分页
        , limit: 15   //默认15条数据一页
        , limits: [15, 30, 45, 60]  //数据分页条
        , id: 'manageRoleTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'roleCode', align: 'center', title: '角色代码', width: 80}
            , {field: 'roleName', align: 'center', title: '角色名称', width: 120}
            , {
                field: 'roleType', align: 'center', title: '角色类型', width: 100, templet: function (d) {
                    if (d.roleType == 1) {
                        return "管理员角色";
                    } else if (d.roleType == 2) {
                        return "业务角色";
                    } else {
                        return "其他";
                    }
                }
            }
            , {
                field: 'enabled', align: 'center', title: '状态', width: 60, templet: function (d) {
                    if (d.enabled == 1) {
                        return "启用";
                    } else {
                        return "禁用";
                    }
                }
            }
            , {field: 'remark', align: 'center', title: '备注'}
        ]]
        , done: function (res, curr, count) {
            var roleCode = $("#roleCodes").val();
            var roleCodes = roleCode.split(',')
            var data = res.data;
            for (var i = 0; i < data.length; i++) {
                for (var k in roleCodes) {
                    if (data[i].roleCode == roleCodes[k]) {
                        data[i]["LAY_CHECKED"] = 'true';
                        //找到对应数据改变勾选样式，呈现出选中效果
                        var index = data[i]['LAY_TABLE_INDEX'];
                        $('.role tr[data-index=' + index + '] input[type="checkbox"]').prop('checked', true);
                        $('.role tr[data-index=' + index + '] input[type="checkbox"]').next().addClass('layui-form-checked');
                    }
                }
            }
        }
    });
    //查询
    var reload = function (data) {
        table.reload('manageUserTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                name: $("#userName").val()
                ,officesName: $("#offName").val()
                ,parentName: $("#parentName").val()
            }
        })
    };

    //查询角色
    function reloadRoleTable() {
        table.reload('manageRoleTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {}
        });
    }

    //新增
    $("#add").on("click", function (e) {

        $("#addNews").show();
        $("#reset").show();
        $("#id").val();
        document.getElementById("userForm").reset();
        //$("input").val('');
    });
    //编辑
    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('manageUserTable');

        var data = checkStatus.data;
        if (data.length != 1) {
            layer.msg("请选择一条记录", {
                offset: 't',
                anim: 6
            })
        } else {
            setFormAdd(data[0]);
            //显示按钮
            $("#addNews").show();
            $("#reset").show();
        }
    });

    //初始赋值
    function setFormAdd(data) {
        document.getElementById("userForm").reset();
        $("#id").val(data.id);
        $("#code").val(data.code);
        $("#name").val(data.name);
        $("#spell").val(data.spell);
        setSexSelect(data.sex);//拼装性别
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
        setStatusSelect(data.status);//拼装状态
        setIsSingleLoginSelect(data.isSingleLogin);//拼装单点登录
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

        form.render('select'); //重新渲染select，这个很重要
    };

    //性别下垃框
    function setSexSelect(sex) {
        if (0 == sex) {
            $("#sexSelect").html('<option value="0" selected>男</option><option value="1" >女</option>');
        } else {
            $("#sexSelect").html('<option value="0" >男</option><option value="1" selected>女</option>');
        }
    }

    //状态下垃框
    function setStatusSelect(status) {
        if (1 == status) {
            $("#statusSelect").html('<option value="1" selected>在用</option><option value="0" >禁用</option>');
        } else {
            $("#statusSelect").html('<option value="1" >在用</option><option value="0" selected>禁用</option>');
        }
    }

    //状态下垃框
    function setIsSingleLoginSelect(isSingleLogin) {
        if (1 == isSingleLogin) {
            $("#isSingleLoginSelect").html('<option value="1" selected>是</option><option value="0" >否</option>');
        } else {
            $("#isSingleLoginSelect").html('<option value="1" >是</option><option value="0" selected>否</option>');
        }
    }

    //取消
    $("#reset").click(function () {
        setHide();
    });

    //隐藏
    function setHide() {
        $("#addNews").hide();
        $("#reset").hide();
    };

    //监听工具条
    table.on('tool(manageUser)', function (obj) {
        var data = obj.data;
        if (obj.event === 'operation') {
            openPage('人员范围-' + data.name, '/system/user/manageOperation.htm?id=' + data.id, '700px', '420px');
        }
    });
    //重置密码
    $("#rePWD").on("click", function (e) {
        var checkStatus = table.checkStatus('manageUserTable');
        if (checkStatus.data.length != 1) {
            layer.msg("请选择一条记录", {
                offset: 't',
                anim: 6
            })
        } else {
            var url = "/system/user/openResetPWD.htm?id=" + checkStatus.data[0].id;
            openPage("重置密码", url, '40%', '55%');
        }
    });

    //打开页面
    function openPage(tit, url, width, height) {
        layui.layer.open({
            title: tit
            , type: 2
            , fixed: false
            , area: [width, height]
            , content: url
        })
    }

    //批量删除
    $("#delAll").click(function () {
        var checkStatus = table.checkStatus('manageUserTable');
        var data = checkStatus.data;
        var ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的人员？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/system/user/delUsers.json?ids=" + ids, function (data) {
                    tableIns.reload();
                    layer.msg(data.message);
                    layer.close(index);
                })
            })
        } else {
            layer.msg('请选择一条数据。', {
                offset: 't',
                anim: 6
            });
        }
    });

    //组织部门弹窗
    var zfocus = true;
    $("#departName").on("focus", function (e) {
        if (zfocus) {
            zfocus = false;
            var url = "/system/user/publicDepart.htm";
            openPage("组织机构", url, '50%', '75%');
        }
    }).on("blur", function (e) {
        zfocus = true
    });
    //科室区队弹窗
    var provider = true;
    $("#officesName").on("focus", function (e) {
        if (provider) {
            provider = false;
            var url = "/system/useDep/generalUseDep.htm";
            openPage("科室区队", url, '50%', '75%');
        }
    }).on("blur", function (e) {
        provider = true
    });
    //保存角色分配
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
    //保存
    form.on("submit(addNews)", function (data) {
        var url = "/system/user/saveUser.json";
        var json = {
            code: $("#code").val()  //员工编码
            , name: $("#name").val()  //姓名
            , spell: $("#spell").val()  //名称拼音
            , sex: $("#sex select").val()  //性别
            , email: $("#email").val()  //邮箱
            , parentId: $("#departId").val()  //部门Id
            , officesId: $("#officesId").val()  //科室区队ID
            , phone: $("#phone").val()  //联系方式
            , qq: $("#qq").val()  //QQ
            , sort: $("#sort").val()  //排序
            , status: $("#status select").val()  //状态
            , isSingleLogin: $("#isSingleLogin select").val()  //单点登录
            , memo: $("#memo").val()  //备注

        };
        var id = $("#id").val();
        if ('' != id) {
            json['id'] = id;
            url = "/system/user/updateUser.json";
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
                setHide();
                tableIns.reload();
            }
        });
        return false;
    })

});

//获取部门页面回传参数
function obtainPart(id, name, ztId, code) {
    document.getElementById("departName").value = name;
    document.getElementById("departId").value = id;
}

//获取科室区队页面回传参数
function UseDepGeneral(data) {
    document.getElementById("officesName").value = data[0].name;
    document.getElementById("officesId").value = data[0].id;
}

