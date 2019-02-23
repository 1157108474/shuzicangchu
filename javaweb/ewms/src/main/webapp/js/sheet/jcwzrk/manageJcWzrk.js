layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;
    var vipTable = layui.vip_table;

    //数据列表
    var ordergr = table.render({
        elem: '#jcwzrkgrid'
        , url: 'listManageJcwzrk.json'
        , cellMinWidth: 80
        , height: "full-110"
        , method: 'post'
        , page: true   //开启分页
        , limit: 20   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "ordergridTable"
        , cols: [
            [{type: 'numbers', title: '序号',fixed: "left", width: 50}
                , {title: '操作', fixed: "left", toolbar: '#barDemo', width: 130}
                , {field: 'code', title: '单据编号',fixed: "left", align: "center", width: 150}
                , {field: 'ownerDepName', title: '寄存单位', align: "center"}
                , {field: 'statusName', title: '单据状态', align: "center", width: 150}
                , {
                field: 'createDate', title: '制单日期', align: "center", width: 150,
                templet: function (d) {
                    return datetimeformat(d.createDate)
                }
            }
                , {field: 'personName', title: '制单人', align: "center", width: 100}
                , {field: 'depName', title: '制单部门', align: "center", width: 150}
                , {field: 'memo', title: '备注', align: "center", width: 100}
            ]
        ]
    });

    var reload = function (data) {

        table.reload('ordergridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: JSON.parse(JSON.stringify(data.field))
        });
    };

    form.on('submit(search)', function (data) {
        reload(data);
        return false;
    });

    laydate.render({
        elem: '#begin'
        , type: 'date'
    });
    laydate.render({
        elem: '#end'
        , type: 'date'
    });
    $("#reset").on("click", function () {
        $("#code").val('');
        $("#status").val('');
        $("#creator").val('');
        $("#creatorName").val('');
        $("#begin").val('');
        $("#end").val('');
    });
    //选择制单人
    $("#creatorBtn").on("click", function () {
        var type="zdr";
        var url = "/system/user/publicDepartUser.htm?type="+type;
        layui.layer.open({
            title: "人员信息"
            , type: 2
            , fixed: false
            , area: ['85%', '90%']
            , content: url
        });
        return false;
    });
    //监听工具条
    table.on('tool(jcwzrkgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: data.url+"?oper=edit", //地址
                title: "编辑寄存物资入库单"+data.code
            });
        }else if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: data.url+"?oper=show", //地址
                title:"查看寄存物资入库单"+data.code
            });
        }else if(obj.event === 'delete'){
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    type: "delete",
                    url: "/sheet/wzjcrkd-"+data.id,
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

});
//获取领料人员信息页面回传参数
function obtainUser(data,type) {
    if (type=="zdr"){
        document.getElementById("creatorName").value = data.name;
        document.getElementById("creator").value = data.id;
    }
}




