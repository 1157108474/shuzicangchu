layui.config({
    base: '/js/static/' // 模块目录
}).use(['table', 'layer', 'vip_table'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var ran = Math.random();
    //数据列表
    table.render({
        elem: '#cKOrderList'  //绑定table id
        , url: '/sheet/ck/listCKOder.json'  //数据请求路径
        , cellMinWidth: 60
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]   //数据分页条
        , id: 'cKOrderReload'
        , height: 'full-50'
        , method: 'POST'
        , cols: [[

            {field: 'cksheetCode', align: 'center', title: '出库单号', width: 150}
            , {
                field: 'createDate', align: 'center', title: '出库时间', width: 150, templet: function (d) {
                    return vipTable.dateformat(d.createDate);
                }
            }
            , {field: 'llSheetCode', align: 'center', title: '申请单号', width: 150}
            , {field: 'useddepartname', align: 'center', title: '使用单位', width: 260}
        ]]

        , trclick: function (data, tr) {
            parent.getCkOrder(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });

    var reload = function () {
        table.reload('cKOrderReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                cksheetCode: $("#code").val(),
                ran: ran++
            }
        })
    };
    $("#search").click(function () {
        reload();
    });


});



