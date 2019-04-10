layui.config({
    base: '/js/static/' // 模块目录
}).use(['form', 'table', 'layer', 'laydate', 'vip_table'], function () {

    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;

    //数据列表
    var sheetgrid = table.render({
        elem: '#sheetgrid'
        , url: 'zc/sheets'
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "sheetgridReload"
        , cols: [
            [    {fixed: 'left',type: 'numbers', title: '序号', width: 50, align:'center', toolbar: '#barDemo'}
                , {fixed: 'left', title: '操作',width:110,  toolbar: '#barDemo'}
                , {field: 'code', title: '单据编号',fixed: 'left', align: "center", width: 170}
                , {field: 'depName', title: '库存组织', align: "center", width: 240}
                , {field: 'extendString1', title: '供应商', align: "center", width: 200}
                , {field: 'statusName', title: '单据状态', align: "center", width: 80}
                , {field: 'personName', title: '制单人', align: "center", width: 120}
                , {field: 'createDate', title: '制单日期', align: "center", width: 150,
                templet: function (d) {
                    return datetimeformat(d.createDate)
                }}
                , {field: 'memo', title: '备注', align: "center"}

            ]
        ]
    });

    var laydate = layui.laydate;
    //常规用法
    laydate.render({
        elem: '#begin'
    });
    laydate.render({
        elem: '#end'
    });
    form.on('submit(search)', function(data){
        reload(data);
        return false;
    });

    var reload =function(data){
        table.reload('sheetgridReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: data.field

        })
    };

    $("#creatorBtn").on("click", function (e) {
        vipTable.openPage("制单人", "../../system/user/publicDepartUser.htm?ztId=" + $("#ztid").val(), '90%', '90%');
        return false;

    });

    //监听工具条
    table.on('tool(sheetgrid)', function (obj) {

        var data = obj.data;
        var arrs = data.url.split("/zc");
        data.url = arrs[0]+"/zc/"+arrs[1];
            if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: data.url+"?oper=edit", //地址
                title: "编辑杂出单"+data.code
            });
        }else if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: data.url+"?oper=show", //地址
                title:"查看杂出单"+data.code
            });
        }else if(obj.event === 'delete'){
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    type: "delete",
                    url: "/sheet/WZZC-"+data.id,
                    dataType: "json",
                    success: function (ret) {
                        if (ret.status == '1') {
                            layer.alert("删除成功");
                            $("#submitBtn").click();
                        } else {
                            layer.alert('删除失败：' + ret.message);
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("删除请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                    }
                });

            })
        }
    });
    //获取供应商列表
    $("#providerBtn").on("click", function () {
        layui.layer.open({
            type: 2,
            title: '供应商',
            fixed: false,
            area: ['60%', '75%'],
            content: "../../system/provider/generalProvider.htm",
            // scrollbar:false
        });
        return false;
    });

    //重置
    $("#reset").on("click", function () {
        $("#creatorId").val("");
        $("#providerId").val("");
    });
});

function obtainUser(data,type) {
    document.getElementById("creatorName").value = data.name;
    document.getElementById("creatorId").value = data.id;
}

