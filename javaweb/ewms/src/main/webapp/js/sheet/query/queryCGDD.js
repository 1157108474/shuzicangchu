layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element','vip_table'], function () {

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
        , url: 'listQueryCGDD.json'
        , cellMinWidth: 80
        , height: "full-140"
        , method: 'post'
        , page: true   //开启分页
        , limit: 15   //默认十五条数据一页
        , limits: [15, 30, 45]  //数据分页条
        , id: "querygridTable"
        , cols: [
            [
                {type: 'numbers', title: '序号',fixed: "left", width: 50}
                , {field: 'ordernum', title: '采购订单编号',fixed: "left", align: "center", width: 180}
                , {field: 'ffcode', title: '发放号', align: "center", width: 80}
                , {field: 'ordertype', title: '订单类型', align: "center", width: 105}
                , {field: 'stockorgcode', title: '库存组织编码', align: "center", width: 120}
//                , {field: 'stockorgname', title: '库存组织名称', align: "center", width: 280}
                , {field: 'isCount', title: '已接收', align: "center", width: 110}
                , {field: 'noCount', title: '未接收', align: "center", width: 110}
                , {field: 'materialcode', title: '物料编码', align: "center", width: 110}
                , {field: 'description', title: '物料描述', align: "center", width: 150}
                , {field: 'detailunit', title: '单位', align: "center", width: 80}
                , {field: 'detailcount', title: '数量', align: "center", width: 80}
                , {field: 'notaxpriceDouble', title: '不含税单价', align: "center", width: 100}
                , {field: 'providerdepname', title: '供应商', align: "center", width: 210}
                , {field: 'consignmentname', title: '寄售物资', align: "center", width: 95}
                , {
                field: 'createdate', title: '制单日期', align: "center", width: 185, templet: function (d) {
                    return datetimeformat(d.createdate);
                }
            }
                , {
                field: 'updatedate', title: '更新日期', align: "center", width: 185, templet: function (d) {
                    return datetimeformat(d.updatedate);
                }
            }
                , {field: 'status', title: '状态', align: "center", width: 100}
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
    //导出
    $("#export").click(function () {
        var parme = '?ordernum=' + $('#ordernum').val() + '&stockorgid=' + $('#stockorgidDiv select').val() +
            '&materialcode=' + $('#materialcode').val() + '&extendint1=' + $('#extendint1Div select').val() +
            '&description=' + $('#description').val() + '&providerName=' + $('#providerName').val() +
            '&providerId=' + $('#providerId').val() + '&consignment=' + $('#consignmentDiv select').val() +
            '&startTime=' + $('#startTime').val() + '&endTime=' + $('#endTime').val();
        window.location.href = '/sheet/query/exportCGDDExcel.htm' + parme;
    });

    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });

    $("#providerBtn").on("click", function (e) {
        vipTable.openPage("供应商列表", "/system/provider/generalProvider.htm", '60%', '90%');
        return false;

    });

    // 加载日期插件
    laydate.render({
        elem: '#startTime'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'date'
    });


});

function showSheet(id) {
    parent.layer.open({
        type: 2,
        title: "库存明细",
        area: ['90%', '80%'],
        resize: true,
        fixed: false, //不固定
        maxmin: true,
        content: "/sheet/query/queryKCDetails.htm?id=" + id
    });
};