layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //数据列表
    table.render({
        elem: '#orderList'  //绑定table id
        , url: 'listOrderGeneral.json'  //数据请求路径
        , cellMinWidth: 50
        , method: 'post'
        , cols: [[
            {type: "checkbox", fixed: "left", width: 50}
            , {field: 'ordernum', align: 'center', title: '订单编码', width: 230}
            , {field: 'providerdepname', align: 'center', title: '供应商', width: 280}
            , {field: 'stockorgname', align: 'center', title: '库存组织', width: 370}
            , {field: 'ordertype', align: 'center', title: '订单类型', width: 120}
            , {field: 'issuecode', align: 'center', title: '发放号', width: 100}
        ]]
        , page: true   //开启分页
        , limit: 30   //默认十条数据一页
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

    //添加
    $("#add").on("click", function (e) {
        var checkStatus = table.checkStatus('orderListReload');
        if (checkStatus.data.length != 1) {
            layer.msg("只能选择一条订单");
        } else {
            var data = checkStatus.data;
            parent.orderGeneral(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
});
