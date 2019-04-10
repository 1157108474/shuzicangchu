layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    //数据列表
    table.render({
        elem: '#querygrid'
        , url: 'listQueryKCDetails.json'
        , where: {
            materialcode: $("#materialcode").val(),
            storeid: $("#storeid").val(),
            storelocationcode: $("#storelocationcode").val(),
        }
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '序号', width: 50}
                , {field: 'housename', title: '库房', align: "center", width: 120}
                , {field: 'storelocationname', title: '库位', align: "center"}
                , {field: 'detailunitname', title: '单位', align: "center", width: 100}
                , {field: 'storecount', title: '库存数量', align: "center", width: 100}
                , {field: 'tagcode', title: '序列号', align: "center", width: 100}
            ]
        ]
    });
});
