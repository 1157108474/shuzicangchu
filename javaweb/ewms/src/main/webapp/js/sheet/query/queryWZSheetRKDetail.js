layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //数据列表
    table.render({
        elem: '#querygrid'
        , url: 'listQueryWZSheetRKDetail.json'
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //开启分页
        , limit: 20  //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '序号', fixed: "left",width: 50}
                , {title: '查看', align: "center", fixed: "left",toolbar: '#bar', width: 80}
                , {field: 'code', title: '单据编号',fixed: "left", align: "center", width: 140}
                , {field: 'materialcode', title: '物料编码', align: "center", width: 120}
                , {field: 'materialname', title: '物料名称', align: "center", width: 100}
                , {field: 'materialspecification', title: '物料规格', align: "center", width: 100}
                , {field: 'materialmodel', title: '物料型号', align: "center", width: 140}
                , {field: 'taxprice', title: '含税单价', align: "center", width: 100}
                , {field: 'subdetailcount', title: '入库数量', align: "center", width: 100}
                , {field: 'housename', title: '库房', align: "center", width: 100}
                , {field: 'storelocationname', title: '库位', align: "center", width: 100}
                , {field: 'description', title: '物料说明', align: "center", width: 180}
                , {field: 'kindName', title: '类型', align: "center", width: 100}
                , {
                    field: 'submittime', align: 'center', title: '入库时间',width: 100,templet: function (d) {
                    return datetimeformat(d.submittime);
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
            where: {
                ztid: $("#ztid").val(),
                code: $("#code").val(),
                storeid: $("#storeid").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val(),
                materialcode:$("#materialcode").val(),
                materialname:$("#materialname").val(),
            }
        });
    };
    laydate.render({
        elem: '#startTime'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'date'
    });
    //监听工具条
    table.on('tool(querygrid)', function (obj) {
        var data = obj.data;
        if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: data.url+"?oper=show", //地址
                title: "查看物资入库明细"
            });
        }
    });
    $("#export").click(function () {
        window.location.href="/sheet/query/exportRKExcel.htm?code="+$("#code").val()+"&storeid="+$("#storeid").val()+
            "&startTime="+ $("#startTime").val()+"&endTime=" +$("#endTime").val()+"&materialcode="+$("#materialcode").val()+
            "&materialname="+$("#materialname").val()
    });
});
