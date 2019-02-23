layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;
    var vipTable = layui.vip_table;

    //数据列表
    var ordergr = table.render({
        elem: '#rkgrid'
        , url: 'listManageRk.json'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //开启分页
        , limit: 20   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "rkgridTable"
        , cols: [
            [{type: 'numbers', title: '序号',fixed: "left", width: 50}
                , {title: '操作', fixed: "left", toolbar: '#bar', width: 120}
                , {field: 'code', title: '单据编号', fixed: "left", align: "center", width: 170}
                // , {field: 'extendstring6', title: 'ERP接收单号', align: "center", width: 180}
                , {field: 'typename', title: '入库类型', align: "center", width: 130}
                , {field: 'ordernum', title: '采购订单编号', align: "center", width: 190}
                , {field: 'receivenum', title: '接收单号', align: "center", width: 170}
                , {field: 'depname', title: '库存组织', align: "center", width: 280}
                , {field: 'extendstring1', title: '供应商名称', align: "center", width: 230}
                , {field: 'statusname', title: '单据状态', align: "center", width: 120}
                , {
                field: 'createdate', title: '制单日期', align: "center", width: 180, templet: function (d) {
                return datetimeformat(d.createdate);
            }}
                , {field: 'personname', title: '制单人', align: "center", width: 120}
            ]
        ]
    });

    var reload = function (data) {
        table.reload('rkgridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: JSON.parse(JSON.stringify(data.field))
        });
    };

    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });

    laydate.render({
        elem: '#startTime'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'date'
    });

    $("#creatorBtn").on("click", function (e) {
        vipTable.openPage("制单人", "../../system/user/publicDepartUser.htm?ztId=" + $("#ztid").val(), '85%', '90%');
        return false;

    });

    //监听工具条
    table.on('tool(rkgrid)', function (obj) {

        var data = obj.data;
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: data.url + "?oper=edit", //地址
                title: "编辑物资入库单" + data.code
            });
        } else if (obj.event === 'show') {
            parent.tab.tabAdd({
                href: data.url + "?oper=show", //地址
                title: "查看物资入库单" + data.code
            });
        } else if (obj.event === 'delete') {
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    type: "delete",
                    url: "/sheet/delete-" + data.id,
                    dataType: "json",
                    success: function (ret) {
                        if (ret.status == '1') {
                            ordergr.reload();
                            layer.msg("删除成功！");
                            layer.close(index);
                        } else {
                            layer.alert('删除失败：' + ret.message);
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("删除请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                    }
                });

            })
        }
    });

});

function obtainUser(data, type) {
    document.getElementById("creatorName").value = data.name;
    document.getElementById("creator").value = data.id;
}

