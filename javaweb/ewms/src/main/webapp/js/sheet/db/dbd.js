layui.use(['form', 'table', 'layer'], function () {


    var table = layui.table;
    var $ = layui.$;



    //数据列表
    var sheetgrid = table.render({
        elem: '#sheetgrid'
        , url: 'dbdSheets?ztId=' + $("#ztId").val() + "&type=" + $("#type").val()
        , cellMinWidth: 80
        , height: "full-110"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "sheetgridReload"
        , cols: [
            [
                {field: 'code', title: '调拨单号', align: "center"}
            ]
        ], trclick: function (data,tr) {
            parent.layui.$("#dbId").val(data.id);
            parent.layui.$("#dbCode").val(data.code);
            parent.DBDGeneral(data);
            var index = parent.layer.getFrameIndex(window.name);
            tr.append($("#dbId"));
            parent.layer.close(index);
        }
    });



    var reload =function(data){
        table.reload('sheetgridReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {"ztId":$("#ztId").val(),"type":$("type").val(),"code":$("code").val()}


        })
    };

    $("#search").click( function () {
        reload();
    });

});

