layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var ran = Math.random();

    //数据列表
    table.render({
        elem: '#orderList'  //绑定table id
        , url: 'listOrderHistory.json'  //数据请求路径
        , cellMinWidth: 80
        , method: 'post'
        , where: {ran: ran++, relationGuid: $("#detailsGUID").val()}
        , cols: [[
            {field: 'materialCode', align: 'center', title: '物料编码'}
            , {
                field: 'operationType', align: 'center', title: '类型', templet: function (d) {
                    if (d.operationType == '1') {
                        return "接收";
                    } else {
                        return "退货";
                    }
                }
            }
            , {field: 'count', align: 'center', title: '数量'}
            , {field: 'personname', align: 'center', title: '操作人'}
            , {
                field: 'createDate', align: 'center', title: '操作日期', templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }
        ]]
        , page: true   //开启分页
        , limit: 10   //默认十条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: 'orderListReload'
    });
    var reload = function (data) {

        table.reload('orderListReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: JSON.parse(JSON.stringify(data.field))

        })
    };
});
