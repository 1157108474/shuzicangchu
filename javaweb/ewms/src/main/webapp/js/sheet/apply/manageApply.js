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
        , url: '/sheet/apply/listManageApply.json'
        , where: {
            usedDepartId: $("#usedDepartId").val()//使用单位
        }
        , cellMinWidth: 80
        , height: "full-150"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认二十条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "llsheetgridTable"
        , cols: [
            [{type: 'numbers', title: '序号', fixed: "left", width: 50}
                , {field: 'action', title: '操作', fixed: "left", width: 120, toolbar: '#bar'}
                , {field: 'code', title: '单据编号', fixed: "left", align: "center", width: 170}
                // , {field: 'officeName', title: '上级审批部门', align: "center", width: 180}
                , {field: 'applyunitName', title: '申请单位', align: "center", width: 180}
                , {field: 'statusName', title: '单据状态', align: "center", width: 100}
                , {field: 'useUnitName', title: '使用单位', align: "center", width: 180}
                , {field: 'extendString2', title: '库存组织', align: "center", width: 260}
                , {field: 'personName', title: '制单人', align: "center", width: 100}
                , {
                field: 'createDate', title: '制单日期', align: "center", width: 200, templet: function (d) {
                    return vipTable.dateformat(d.createDate);
                }
            }
                , {field: 'extendString1', title: '用途', align: "center", width: 100}
            ]
        ]
    });

    //绑定日期控件
    laydate.render({
        elem: '#beginDate'
    });
    //绑定日期控件
    laydate.render({
        elem: '#endDate'
    });
    //查询列表
    form.on("submit(formSubmit)", function (data) {
        table.reload('llsheetgridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                ztId: $("#ztId select").val()//库存组织
                , creator: $("#workManId").val()//制单人
                , code: $("#code").val()
                , status: $("#status select").val()//单据状态
                , beginDate: $("#beginDate").val()//开始时间
                , endDate: $("#endDate").val()//结束时间
                , usedDepartId: $("#usedDepartId").val()//使用单位
                , applyDepartId: $("#applyDepartId").val()//申请单位
                // ,officesId: $("#officesId").val()//上级审批部门
            }
        });
        return false;
    });

    //进入上级审批部门页面
    $("#openOffice").on("click", function (e) {
        var url = "/system/useDep/generalUseDepDepart.htm";
        vipTable.openPage("科室", url, "75%", "85%");
    });
    //进入制单人页面
    $("#openWorkMan").on("click", function (e) {
        var url = "/sheet/apply/publicDepartUser.htm";
        vipTable.openPage("人员信息", url, "85%", "90%");
    });
    //监听工具条
    table.on('tool(llsheetgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: "/sheet/apply/" + data.id + "?oper=edit", //地址
                title: "编辑申领单" + data.code
            });
        } else if (obj.event === 'show') {
            parent.tab.tabAdd({
                href: "/sheet/apply/" + data.id + "?oper=show", //地址
                title: "查看申领单:" + data.code
            });
        } else if (obj.event === 'delete') {
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/sheet/apply/delSheet.json", {id: data.id}, function (data) {
                    llsheetgr.reload();
                    layer.msg(data.message);
                    layer.close(index);
                });
            })
        }
    });
});

//获取人员信息页面回传参数
function obtainUser(data, type) {
    document.getElementById("workManName").value = data.name;
    document.getElementById("workManId").value = data.id;
}

//上级审批页面返回传参数
function UseDepDepartGeneral(data) {
    document.getElementById("officesName").value = data[0].name;
    document.getElementById("officesId").value = data[0].id;
}
