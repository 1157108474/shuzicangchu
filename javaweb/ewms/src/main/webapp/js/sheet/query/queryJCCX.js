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
        , url: 'listQueryJCCX.json'
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '序号',fixed: "left", width: 50}
                , {field: 'materialcode', title: '物料编码', align: "center", width: 120}
                , {field: 'materialname', title: '物料名称', align: "center", width: 120}
                , {field: 'flname', title: '物料分类', align: "center", width: 120}
                , {field: 'materialspecification', title: '物料规格', align: "center", width: 120}
                , {field: 'materialmodel', title: '物料型号', align: "center",width: 120}
                , {field: 'storecount', title: '库存数量', align: "center", width: 120}
                , {field: 'housename', title: '库房', align: "center", width: 120}
                , {field: 'storelocationname', title: '库位', align: "center", width: 120}
                , {field: 'description', title: '物料说明', align: "center", width: 120}
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                materialcode:$("#materialcode").val(),
                materialname:$("#materialname").val(),
                storeid: $("#storeid").val(),
                storelocationid: $("#locationId").val(),
            }
        });
    };

    $("#locationBtn").on("click", function () {
        if($("#storeid").val()==""){
            alert("请先选择库房！");
        }else{
            layui.layer.open({
                type: 2,
                title: '库位',
                fixed: false,
                area: ['60%', '90%'],
                content: "../../system/ware/location?parentId=" + $("#storeid").val()
                // scrollbar:false
            });
            return false;
        }
    });

    $("#reset").click(function () {
        $("#locationId").val("");
    });

});