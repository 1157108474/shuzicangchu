layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //数据列表
    table.render({
        elem: '#querygrid'
        , url: 'listQueryInventory.json'
        , cellMinWidth: 80
        , height: "full-186"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "querygridTable"
        , cols: [
            [{type: 'checkbox', title: '选择', fixed: "left",width: 50}
                ,{type: 'numbers', title: '序号',fixed: "left", width: 50}
                , {title: '操作', align: "center",fixed: "left", toolbar: '#bar', width: 80}
                , {field: 'ztidname', title: '库存组织', align: "center", width: 240}
                , {field: 'materialcode', title: '物料编码', align: "center", width: 120}
                , {field: 'description', title: '物料描述', align: "center"}
                , {field: 'housename', title: '库房', align: "center", width: 100}
                , {field: 'detailunitname', title: '库位', align: "center", width: 100}
                , {field: 'ownertypename', title: '是否寄售', align: "center", width: 120}
                /*, {field: 'providername', title: '供应商', align: "center", width: 200}*/
                , {field: 'storecount', title: '库存数量', align: "center", width: 120}
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
                materialcode:$("#materialcode").val(),
                /*materialname:$("#materialname").val(),*/
                flname: $("#flname").val(),
                /*providerdepid: $("#providerId").val(),*/
                description: $("#description").val(),
                storeid: $("#storeid").val(),
                ownertype: $("#ownertype").val(),
                startarea: $("#startarea").val(),
                endarea: $("#endarea").val()
            }
        });
    };

    //获取供应商列表
    $("#providerBtn").on("click", function () {
        layui.layer.open({
            type: 2,
            title: '供应商',
            fixed: false,
            area: ['60%', '90%'],
            content: "../../system/provider/generalProvider.htm",
            // scrollbar:false
        });
        return false;
    });

    $("#reset").click(function () {
        $("#providerId").val("");
    });

    $("#print").click(function () {
        var checkStatus = table.checkStatus('querygridTable');
        var length = checkStatus.data.length;
        if (length < 1) {
            //正上方
            layer.msg('请至少选择一条信息', {
                offset: 't',
                anim: 6
            })
        } else {
            layer.open({
                type: 2,
                title: '打印',
                fixed: false,
                area: ['60%', '60%'],
                content: "/system/print/tag?length="+length,
                // scrollbar:false
                success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);
                    body.find('input').val(length);
                }
            })
        }
    });
    $("#export").click(function () {

        var data ="ztid="+ $("#ztid").val()+"&materialcode="+$("#materialcode").val()+
        "&flname="+ $("#flname").val()+
        /*"&providerdepid="+$("#providerId").val()+*/
        "&description="+$("#description").val()+"&storeid="+$("#storeid").val()+"&ownertype="+$("#ownertype").val()+
        "&startarea="+$("#startarea").val()+"&endarea="+$("#endarea").val();
        console.log(data);
        window.location.href="/sheet/query/exportKCExcel.htm?"+data
    });
});
function showSheet(id,storelocationcode) {
    parent.layer.open({
        type: 2,
        title: "库存明细",
        area: ['90%', '80%'],
        resize: true,
        fixed: false, //不固定
        maxmin: true,
        content: "/sheet/query/queryKCDetails.htm?id="+id+"&storelocationcode="+storelocationcode
    });
};