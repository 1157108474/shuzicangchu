layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;

    //明细列表
    var planDetailGr = table.render({
        elem: '#detailsgrid'
        , url: '/sheet/apply/listSheetApply.json'
        , cellMinWidth: 80
        , height: "full-50"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20 ,30]  //数据分页条
        , where: {
            id: $("#id").val()
            , sheetId: $("#sheetId").val()
        }
        , id: "detailsgridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'extendString2', title: '使用地址', align: "center", width: 160, edit: 'text'}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 80}
                , {field: 'detailCount', title: '申领数量', align: "center", width: 80}
                , {field: 'ckCount', title: '已出库数量', align: "center", width: 100}
                , {field: 'snCode', title: '计划编号', align: "center", width: 150}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 120}
                , {field: 'description', title: '物料描述', align: "center", width: 320}

            ]
        ]
    });
    //修改
    $("#detail_edit").on("click", function (e) {
        var rows = table.checkStatus('detailsgridTable').data;
        if (rows.length > 0) {
            var extendString2 = rows[0].extendString2;//获取地址
            $("#reloadDetailsgrid").val(1);
            if (null == extendString2 || extendString2 == "") {
                layer.msg("请填写使用地址");
                return;
            }
            $.ajax({
                url: "/sheet/apply/updateApplySheet.json",
                type: "post",
                data: {id: rows[0].id, extendString2: extendString2},
                success: function (result) {
                    top.layer.msg(result.message, {time: 500});
                    if ('1' == data.status) {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }
                }
            });
        } else {
            layer.msg("请选择一条明细");
        }

    })
});

