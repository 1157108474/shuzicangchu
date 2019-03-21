layui.config({
    base: '/js/static/' // 模块目录
}).use(['table', 'layer','vip_table'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;
    var ran = Math.random();
    //数据列表
    table.render({
        elem: '#applyList'  //绑定table id
        , url: '/sheet/apply/listSqNum.json'  //数据请求路径
        , cellMinWidth: 60
        , method: 'POST'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]   //数据分页条
        , id: 'applyReload'
        , height: 'full-50'
        , where: {ztId: $("#ztId").val()}//初始条件
        , cols: [[
            {field: 'code', align: 'center', title: '申领单号', width: 180}
            , {field: 'usedDep', align: 'center', title: '使用单位', width: 150}
            , {field: 'applyDepartName', align: 'center', title: '申请单位（中心/部门）', width: 150}
            , {field: 'extendString1', align: 'center', title: '用途', width: 150}
            , {field: 'memo', align: 'center', title: '备注', width: 150}
            , {field: 'createDate', align: 'center', title: '创建时间', width: 150, templet: function (d) {
                return vipTable.dateformat(d.createDate);
            }
            }
        ]]

        , trclick: function (data, tr) {
            parent.getApply(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });

    var reload = function () {
        table.reload('applyReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                code: $("#code").val(),
                ran: ran++
            }
        })
    };
    $("#search").click(function () {
        reload();
    });
});



