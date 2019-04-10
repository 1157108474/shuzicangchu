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
        elem: '#jsdList'  //绑定table id
        , url: 'listJSDList.json'  //数据请求路径
        , cellMinWidth: 80
        , method: 'post'
        , cols: [[
            {type: "checkbox", fixed: "left", width: 50}
            , {field: 'code', align: 'center', title: '接收单号', width: 190}
            , {field: 'ordernum', align: 'center', title: '订单编号', width: 250}
            , {field: 'extendstring1', align: 'center', title: '供应商', width: 290}
            , {field: 'extendstring3', align: 'center', title: '订单类型', width: 126}
            , {field: 'extendstring5', align: 'center', title: '发放号'}
            , {
                field: 'createdate', align: 'center', title: '接收时间', width: 190, templet: function (d) {
                    return datetimeformat(d.createdate);
                }
            }
        ]]
        , page: true   //开启分页
        , limit: 30   //默认十条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: 'listJSDGeneral'
    });
    var reload = function (data) {

        table.reload('listJSDGeneral', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: JSON.parse(JSON.stringify(data.field))

        })
    };

    //添加
    $("#add").on("click", function (e) {
        var checkStatus = table.checkStatus('listJSDGeneral');
        if (checkStatus.data.length != 1) {
            layer.msg("只能选择一条订单", {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            parent.JSDGeneral(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
});
