layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;


    //数据列表
    var llsheetgr = table.render({
        elem: '#llsheetgrid'
        , url: '/sheet/ck/listManageCK.json'
        , cellMinWidth: 60
        , height: "full-125"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认20条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , where:{
            ztId: $("#ztId").val()//库存组织
            ,creator: $("#creator").val()//制单人
        }
        , id: "llsheetgridTable"
        , cols: [
            [{type: 'numbers', title: '序号',fixed: "left", width: 50}
                , {field: 'action', title: '操作', fixed: "left",  width: 160, toolbar: '#bar'}
                , {field: 'code', title: '单据编号', fixed: "left", align: "center", width: 180}
                , {field: 'typeName', title: '出库类型', align: "center", width: 180}
//                , {field: 'orgName', title: '库存组织', align: "center",width: 260}
                , {field: 'useDepName', title: '申领部门', align: "center", width: 160}
                , {field: 'renewalCostStr', title: '更新成本', align: "center", width: 120}
                , {field: 'statusName', title: '单据状态', align: "center", width: 100}
                , {field: 'personName', title: '制单人', align: "center", width: 100}
                , {field: 'createDate', title: '制单日期', align: "center", width: 200, templet: function (d) {
                return vipTable.dateformat(d.createDate);
            }}
            ]
        ]
    });

    //查询列表
    form.on("submit(formSubmit)", function (data) {
        table.reload('llsheetgridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                ztId: $("#ztId").val()//库存组织
                ,creator: $("#creator").val()//制单人
                ,code: $("#code").val()
                ,typeId:$("#typeId select").val()//出库类型
                ,status: $("#status select").val()//单据状态
                ,beginDate: $("#beginDate").val()//开始时间
                ,endDate: $("#endDate").val()//结束时间
                ,extendint3: $("#extendint3 select").val()//是否更新成本
            }
        });
        return false;
    });

    //绑定日期控件
    laydate.render({
        elem: '#beginDate'
    });
    //绑定日期控件
    laydate.render({
        elem: '#endDate'
    });
    //监听工具条
    table.on('tool(llsheetgrid)', function (obj) {
        var data = obj.data;
        console.log(data);
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: "/sheet/ck/"+data.id+"?oper=edit", //地址
                title: "编辑出库单"+data.code
            });
        }else if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: "/sheet/ck/"+data.id+"?oper=show", //地址
                title: "查看出库单:"+data.code
            });
        }else if(obj.event === 'renewalCost'){
            layer.confirm('确定更新成本？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/sheet/ck/renewalCost.json",{id:data.id}, function (data) {
                    llsheetgr.reload();
                    layer.msg(data.message);
                    layer.close(index);
                });
            })
        }else if(obj.event === 'delete'){
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/sheet/ck/delSheetCk.json",{id:data.id}, function (data) {
                    llsheetgr.reload();
                    layer.msg(data.message);
                    layer.close(index);
                });
            })
        }
    });

});

