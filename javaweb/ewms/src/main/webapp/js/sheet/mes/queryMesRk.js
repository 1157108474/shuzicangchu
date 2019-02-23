layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var vipTable = layui.vip_table;
    var laydate = layui.laydate;

    //数据列表
    table.render({
        elem: '#querygrid'
        , url: 'listMesRk.json'
        , cellMinWidth: 80
        , height: "full-155"
        , method: 'post'
        , page: true   //开启分页
        , limit: 15   //默认十五条数据一页
        , limits: [15, 30, 45]  //数据分页条
        , id: "querygridTable"
        , cols: [
            [
                {type: 'numbers', title: '序号',fixed: "left", width: 50}
                , {field: 'assetNumber', title: '资产编号', fixed: "left",align: "center", width: 145}
                , {field: 'locatorCode', title: '库位编码', align: "center", width: 135}
                , {field: 'locatorName', title: '库位名称', align: "center", width: 135}
                , {field: 'subinvCode', title: '库房编码', align: "center", width: 135}
                , {field: 'itemNo', title: '物料编码', align: "center", width: 135}
                , {field: 'itemDesc', title: '物料描述', align: "center", width: 135}
                , {field: 'uom', title: '入库基本单位', align: "center", width: 235}
                , {field: 'quantity', title: '入库基本单位数量', align: "center", width: 235}
                , {field: 'shifts', title: '班次', align: "center", width: 135}
                , {field: 'batchName', title: '批次', align: "center", width: 135}
                , {
                field: 'createDate', title: '创建时间', align: "center", width: 194, templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
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


});

