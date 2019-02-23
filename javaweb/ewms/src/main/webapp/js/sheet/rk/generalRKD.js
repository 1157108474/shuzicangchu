layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var creator = $("#creator").val();
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //数据列表
    table.render({
        elem: '#rkdList'  //绑定table id
        , url: 'listRKDList.json?creator=' + creator  //数据请求路径
        , cellMinWidth: 100
        , method: 'post'
        , cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'code', align: 'center', title: '入库单号', width: 170}
            , {field: 'orderNum', align: 'center', title: '采购订单编号', width: 170}
            , {field: 'extendString1', align: 'center', title: '供应商'}
            , {field: 'extendString3', align: 'center', title: '订单类型'}
            , {field: 'extendString2', align: 'center', title: '库存组织名称'}
            , {field: 'extendString5', align: 'center', title: '发放号'}
        ]]
        , page: true   //开启分页
        , limit: 10   //默认十条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: 'listRKDGeneral'
        , trclick: function (data,tr) {
           
            parent.setRkInfo(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
    var reload = function (data) {

        table.reload('listRKDGeneral', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: JSON.parse(JSON.stringify(data.field))

        })
    };

    // //添加
    // $("#add").on("click", function (e) {
    //     var checkStatus = table.checkStatus('listRKDGeneral');
    //     if (checkStatus.data.length != 1) {
    //         layer.msg("只能选择一条订单", {
    //             offset: 't',
    //             anim: 6
    //         })
    //     } else {
    //         var data = checkStatus.data;
    //         parent.RKDGeneral(data);
    //         var index = parent.layer.getFrameIndex(window.name);
    //         parent.layer.close(index);
    //     }
    // });
});
