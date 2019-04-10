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
    table.render({
        elem: '#useDepList'  //绑定table id
        , url: 'listUseDep.json'  //数据请求路径
        , cellMinWidth: 80
        , height: 'full-75'
        , method: 'post'
        , cols: [[
            {checkbox: true, fixed: 'left',width:30}
            , {field: 'code', align: 'center', title: '编码', width: 100, fixed: 'left'}
            , {field: 'name', align: 'center', title: '使用单位名称', width: 200, fixed: 'left'}
            , {field: 'organizationName', align: 'center', title: '组织机构', width: 230}
            , {
                field: 'organizationType', align: 'center', title: '组织类型', width: 70, templet: function (d) {
                    if (d.organizationType == 1) {
                        return "区队";
                    } else {
                        return "科室";
                    }
                }
            }
            , {field: 'departName', align: 'center', title: '库存组织编码', width: 95}
            , {field: 'ztName', align: 'center', title: '库存组织名称', width: 230}
            , {
                field: 'status', align: 'center', title: '是否启用', minWidth: 70, templet: function (d) {
                    if (d.status == 1) {
                        return "启用";
                    } else {
                        return "禁用";
                    }
                }
            }
            , {field: 'memo', align: 'center', title: '备注'}
        ]]
        , page: true   //开启分页
        , limit: 30   //默认二十条数据一页
        , limits: [10, 20, 30, 40]  //数据分页条
        , id: 'useDepListReload'
    });

    var resetSezrch = function () {
        $("#name").val('');
    };
    
    var reload = function (data) {

        table.reload('useDepListReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {name:$("#name").val()}

        })
    };

    //编辑
    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('useDepListReload');
        if (checkStatus.data.length != 1) {
            layer.msg("请选择一条记录进行编辑", {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            layer.open({
                type: 2,
                title: '编辑使用单位',
                fixed: false,
                area: ['60%', '90%'],
                content: "/system/useDep/updateUseDep.htm?id=" + data[0].id,
                end:function(){
                    resetSezrch();
                }
            })
        }
    });

    //新增
    $("#add").on("click", function (e) {
        layer.open({
            type: 2,
            title: '新增使用单位',
            //shadeClose: true,
            //shade: 0.8,
            fixed: false,
            area: ['60%', '90%'],
            content: "/system/useDep/updateUseDep.htm",
            end:function(){
                resetSezrch()
            }
        })
    });

    //禁用
    $("#delete").on("click", function (e) {
        var checkStatus = table.checkStatus('useDepListReload');
        var datas = checkStatus.data;
        var arr = [];
        if (datas == "") {
            layer.msg("请选择一条记录进行删除", {
                offset: 't',
                anim: 6
            });
        } else {
            layer.confirm('您确定要删除？', {
                icon: 3, title: '提示信息', offset: '35%',
                btn: ['确定', '取消'] //按钮
            }, function () {
                for (var i = 0; i < datas.length; i++) {
                    arr.push(datas[i].id);
                }
                var url = "delUseDep.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('删除成功', {
                            time: 2000,
                            end: function () {
                                table.reload('useDepListReload');
                            }
                        });
                    } else {
                        layer.alert(data.message, {
                            offset: '35%'
                        });
                    }
                })
            }, function () {
                layer.closeAll();
            });
        }
    });

    //物料范围操作
    $("#materielRange").on("click", function (e) {
        var checkStatus = table.checkStatus('useDepListReload');
        if (checkStatus.data.length != 1) {
            layer.msg("请选择一条记录进行编辑", {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            var useId = data[0].id;
            var useName = data[0].name;
            layer.open({
                type: 2,
                title: '物料范围管理-'+useName,
                fixed: false,
                area: ['90%', '90%'],
                content:"showMaterielRange.htm?id=" + useId + "&name=" + useName
            });
        }
    });

});
