layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;

    //明细列表
    var planDetailGr = table.render({
        elem: '#planDetailGrid'
        , url: '/sheet/query/listPlanDetail.json'
        , cellMinWidth: 60
        , height: "full-125"
        , method: 'post'
        , page: true   //开启分页
        , limit: 20   //默认二十条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "planDetailGridTable"
        , cols: [
            [{type: 'numbers', title: '序号', width: 50}
                , {field: 'deptName', title: '计划部门', align: "center", width: 260}
                , {field: 'planCode', title: '计划编号', align: "center", width: 150}
                , {field: 'userDepName', title: '使用单位', align: "center", width: 220}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 150}
                , {field: 'materialDes', title: '物料描述', align: "center", width: 320}
                , {field: 'planCount', title: '计划数量', align: "center", width: 80}
                , {field: 'haveslCount', title: '已申领数量', align: "center", width: 100}
                , {field: 'storeCount', title: '库存数量', align: "center", width: 80}
                , {field: 'storeuseCount', title: '库存可用数量', align: "center", width: 120}
                , {field: 'unIt', title: '单位', align: "center", width: 60}
                , {
                field: 'planDate', title: '计划日期', align: "center", width: 100, templet: function (d) {
                    return vipTable.dateformat(d.planDate);
                }
            }
            ]
        ]

    });
    //查询计划明细
    form.on("submit(formSubmit)", function (data) {
        table.reload('planDetailGridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                planCode: $("#planCode").val()
                , deptName: $("#deptName").val()
                , userDepName: $("#userDepName").val()
                , materialCode: $("#materialCode").val()
                , materialDes: $("#materialDes").val()
            }
        });
        return false;
    });
    $("#export").click(function () {
        var parme = '?planCode=' + $('#planCode').val() + '&deptName=' + $('#deptName').val() +
            '&userDepName=' + $('#userDepName').val() + '&materialCode=' + $('#materialCode').val() +
            '&materialDes=' + $('#materialDes').val();
        window.location.href = '/sheet/query/exportPlanExcel.htm' + parme;
    });
});

