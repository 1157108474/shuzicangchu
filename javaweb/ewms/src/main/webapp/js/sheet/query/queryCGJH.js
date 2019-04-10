layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var vipTable = layui.vip_table;
    var laydate = layui.laydate;

    //数据列表
    table.render({
        elem: '#querygrid'
        , url: 'listQueryCGJH.json'
        , cellMinWidth: 80
        , height: "full-155"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [15, 30, 45]  //数据分页条
        , id: "querygridTable"
        , cols: [
            [
                {type: 'numbers', title: '序号', fixed: "left", width: 50}
                , {field: 'planCode', title: '计划编号', fixed: "left", align: "center", width: 145}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 135}
                , {field: 'materIaldes', title: '物料描述', align: "center", width: 270}
                , {field: 'applyDepName', title: '申报单位', align: "center", width: 308}
                , {field: 'useDepName', title: '使用单位', align: "center", width: 240}
                , {field: 'count', title: '数量', align: "center", width: 80}
                , {field: 'unIt', title: '单位', align: "center", width: 80}
                , {
                field: 'createDate', title: '计划制定日期', align: "center", width: 194, templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }
                , {
                field: 'needDate', title: '需求日期', align: "center", width: 194, templet: function (d) {
                    return datetimeformat(d.needDate);
                }
            }
                , {field: 'status', title: '状态', align: "center", width: 80}
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: JSON.parse(JSON.stringify(data.field))

        });
    };
    $("#export").click(function () {
        var parme = '?planCode=' + $('#planCode').val() + '&materialCode=' + $('#materialCode').val() +
            '&materIaldes=' + $('#materIaldes').val() + '&applyDepName=' + $('#applyDepName').val() +
            '&applyDepId=' + $('#applyDepId').val() + '&useDepName=' + $('#useDepName').val() +
            '&useDepId=' + $('#useDepId').val() + '&status=' + $('#statusDiv select').val() +
            '&startTime1=' + $('#startTime1').val() + '&endTime1=' + $('#endTime1').val() +
            '&startTime2=' + $('#startTime2').val() + '&endTime2=' + $('#endTime2').val();
        window.location.href = '/sheet/query/exportCGJHExcel.htm' + parme;
    });
    //进入申请单位页面
    $("#useDepName").on("click", function (e) {
        var url = "/system/useDep/generalUseDep.htm";
        vipTable.openPage("申请单位列表", url, "75%", "85%");
    });

    //进入使用单位页面
    $("#applyDepName").on("click", function (e) {
        var url = "/system/applyDep/generalApplyDep.htm";
        vipTable.openPage("使用单位列表", url, "75%", "85%");
    });

    // 加载日期插件
    laydate.render({
        elem: '#startTime1'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime1'
        , type: 'date'
    });
    laydate.render({
        elem: '#startTime2'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime2'
        , type: 'date'
    });

    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });


});

function applyDepGeneral(data) {
    document.getElementById("applyDepName").value = data[0].name;
    document.getElementById("applyDepId").value = data[0].id;
}

function UseDepGeneral(data) {
    document.getElementById("useDepName").value = data[0].name;
    document.getElementById("useDepId").value = data[0].id;
}

function showSheet(id) {
    parent.layer.open({
        type: 2,
        title: "库存明细",
        area: ['90%', '80%'],
        resize: true,
        fixed: false, //不固定
        maxmin: true,
        content: "/sheet/query/queryKCDetails.htm?id=" + id
    });
}