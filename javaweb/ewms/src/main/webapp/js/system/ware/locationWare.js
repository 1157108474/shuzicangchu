layui.use(['table', 'layer'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    var ran = Math.random();
    //数据列表
    table.render({
        elem: '#locationList'  //绑定table id
        ,url:'listLocation'  //数据请求路径
        ,cellMinWidth: 60
        ,method:'POST'
        ,cols: [[
            // {indexcolumn:true,fixed:'left'},
           // {checkbox: true, fixed: 'left',width:30},
             {field: 'code', align: 'center', title: '编码', width: 150}
            , {field: 'name', align: 'center', title: '名称', width: 150}
            , {field: 'ztName', align: 'center', title: '库存组织', width: 260}
            // ,{fixed: 'right',title: '操作', width:180,      align:'center', toolbar: '#toolBar'}
            //一个工具栏  具体请查看layui官网
        ]]
        ,page: true   //开启分页
        ,limit:30   //默认十五条数据一页
        ,limits:[10,20,30]   //数据分页条
        ,id: 'locationListReload'
        , where: {parentId: 0}
        , height: 'full-90'
        , trclick: function (data,tr) {
            parent.layui.$("#"+$("#pre").val()+"locationId").val(data.id);
            parent.layui.$("#"+$("#pre").val()+"locationName").val(data.name);
            parent.layui.$("#"+$("#pre").val()+"locationCode").val(data.code);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });

    var reload = function(){

        table.reload('locationListReload', {
            url:"listLocation"
            ,page: {
                curr: 1 //重新从第 1 页开始
            },
            where:{
                name:$("#name").val(),
                code:$("#code").val(),
                parentId: 0,
                ran:ran++
            }

        })
    };
    $("#search").click( function () {
        reload();
    });

});



