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
    var tableIns =table.render({
        elem: '#manageRole'  //绑定table id
        , url: '/system/role/listRole.json'  //数据请求路径
        , cellMinWidth: 80
        , height :"full-60"  //自适应高度
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: 'manageRoleTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'roleCode', align: 'center', title: '角色代码', width: 100}
            , {field: 'roleName', align: 'center', title: '角色名称', width: 120}
            , {field: 'roleType', align: 'center', title: '角色类型', width: 120,templet: function (d) {
                if (d.roleType == 1) {
                    return "管理员角色";
                } else if(d.roleType == 2){
                    return "业务角色";
                }else {
                    return "其他";
                }
            }}
            , {field: 'sort', align: 'center', title: '排序', width: 60}
            , {
                field: 'enabled', align: 'center', title: '状态', width: 80, templet: function (d) {
                    if (d.enabled == 1) {
                        return "启用";
                    } else {
                        return "禁用";
                    }
                }
            }
            , {field: 'remark', align: 'center', title: '备注'}
        ]]
        ,trclick:function (data,tr) {
             $("#roleCode").val(data.roleCode);
             $("#tabName").text("角色【"+data.roleName+"】的用户");
             reloadUserTable(data.roleCode);
        }

    });
    //数据列表
    var tableUser =table.render({
        elem: '#manageUser'  //绑定table id
        , url: '/system/role/listRoleUser.json'  //数据请求路径
        , cellMinWidth: 80
        , height :"full-136"  //自适应高度
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十条数据一页
        , limits: [10, 20, 30, 50]  //数据分页条
        , id: 'manageUserTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'name', align: 'center', title: '姓名', width: 100}
            , {field: 'parent', align: 'center', title: '所属部门', width: 240,templet: function (d) {
                if (null==d.parent ) {
                    return '';
                } else {
                    return d.parent.name;
                }
            }}
            , {field: 'status', align: 'center', title: '状态', width: 60,templet: "#showUserStatus"}
        ]]
    });

    //查询角色
    var reload = function (data) {
        table.reload('manageRoleTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                roleName: $("#roleName").val()
            }
        })
    };
    //查询用户
    function  reloadUserTable(roleCode ) {
        table.reload('manageUserTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where:  {
                roleCode: roleCode
            }
        });
    }
    //新增
    $("#add").on("click", function (e) {
        openPage("新增角色", "/system/role/addRole.htm","40%","70%");
    });
    //编辑
    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('manageRoleTable');
        if (checkStatus.data.length != 1) {
            layer.msg("请选择一条记录进行编辑", {
                offset: 't',
                anim: 6
            })
        } else {
            openPage("编辑角色", "/system/role/editRole.htm?r.roleCode=" + checkStatus.data[0].roleCode,"40%","70%");
        }
    });
    //分配权限
    $("#allotMenuButton").on("click", function (e) {
        var checkStatus = table.checkStatus('manageRoleTable');
        if (checkStatus.data.length != 1) {
            layer.msg("请选择一条记录进行编辑", {
                offset: 't',
                anim: 6
            })
        } else {
            openPage("分配权限", "/system/role/allotMenuButton.htm?roleCode=" + checkStatus.data[0].roleCode,"40%","80%");
        }
    });
    //打开页面
    function openPage(tit, url,width,height) {
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
        var checkStatus = table.checkStatus('manageRoleTable');
        var  data = checkStatus.data;
        var roleCodes = [];
        if (data.length > 0) {
            for (var i in data) {
                roleCodes.push(data[i].roleCode);
            }
            layer.confirm('确定删除选中的角色？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/system/role/delRoles.json?roleCodes=" + roleCodes , function (data) {
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


    //添加人员页面
    $("#addUser").on("click", function (e) {
        var roleCode = $("#roleCode").val();
        if(""!=roleCode){
            openPage("人员信息", "/system/role/departUser.htm?roleCode=" + roleCode, "85%", "90%");
        }else {
            layer.msg("请选择一个角色", {
                offset: 't',
                anim: 6
            })
        }

    });

    //移除用户
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
            layer.confirm('确定移除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(url, function (data) {
                    reloadUserTable();
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
});


