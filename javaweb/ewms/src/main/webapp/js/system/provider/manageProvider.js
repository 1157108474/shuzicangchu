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
        elem: '#providerList'  //绑定table id
        , url: 'listProvider.json'  //数据请求路径
        , cellMinWidth: 80
        , height: 'full-70'
        , method: 'post'
        , cols: [[
            {checkbox: true, fixed: 'left',width:30}
            , {field: 'code', align: 'center', title: '编码', width: 100, fixed: 'left'}
            , {field: 'name', align: 'center', title: '供应商名称', width: 200, fixed: 'left'}
            , {field: 'address', align: 'center', title: '供应商地址', width: 160}
            , {field: 'zipCode', align: 'center', title: '邮政编码', width: 100,templet:function(d){
                if(d.zipCode == 0){
                    return "000000";
                }
            }}
            , {field: 'contactPerson', align: 'center', title: '联系人', width: 80}
            , {field: 'contractPhone', align: 'center', title: '联系电话', width: 100}
            , {field: 'fax', align: 'center', title: '传真', width: 100}
            , {field: 'email', align: 'center', title: '电子邮箱', width: 140}
            , {
                field: 'status', align: 'center', title: '是否启用', minWidth: 100, templet: function (d) {
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
        , id: 'providerListReload'
    });
    var reload = function (data) {

        table.reload('providerListReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {name: $("#name").val()}

        })
    };

    var resetSearch = function () {
        $("#name").val('');
    };

    //编辑
    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('providerListReload');
        if (checkStatus.data.length != 1) {
            layer.msg("请选择一条记录进行编辑", {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            layer.open({
                type: 2,
                title: '编辑供应商',
                fixed: false,
                area: ['40%', '90%'],
                content: "/system/provider/updateProvider.htm?id=" + data[0].id,
                end:function(){
                    resetSearch();
                }
            })
        }
    });

    //新增
    $("#add").on("click", function (e) {
        layer.open({
            type: 2,
            title: '新增供应商',
            //shadeClose: true,
            //shade: 0.8,
            fixed: false,
            area: ['70%', '90%'],
            content: "/system/provider/updateProvider.htm",
            end:function(){
                resetSearch();
            }
        })
    });

    $("#delete").on("click", function (e) {
        var checkStatus = table.checkStatus('providerListReload');
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
                var url = "delProvider.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('删除成功', {
                            time: 2000,
                            end: function () {
                                table.reload('providerListReload');
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
    })
});
