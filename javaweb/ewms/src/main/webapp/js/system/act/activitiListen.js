layui.use(['laydate', 'form', 'layer', 'table', 'laytpl', 'element'], function () {
    var laydate = layui.laydate;
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        element = layui.element;

    //数据列表
    var tableIns = table.render({
        elem: '#activitiListen',
        url: 'findProcessingByperson.json?isAdmin=admin',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 30,
        id: "activitiListenTable",
        method: 'post',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {
                field: 'code', title: '单据编号', align: "center", event: "getId", templet: function (d) {
                return "<a class='layui-table-link' href='javascript:;'>" + d.code + "</a>";
            }
            },
            /*
                        {field: 'code', title: '单据编号', align: "center",minWidth:120,event:"getId"},
            */
            {field: 'sheetName', title: '单据名称', align: "center", minWidth: 120},
            {field: 'submitMan', title: '申请人', align: "center", minWidth: 120},
            {field: 'name', title: '审核环节', align: "center", minWidth: 120},
            {field: 'assignee', title: '处理人', align: "center", minWidth: 120},
            {field: 'createTime', title: '提交时间', align: "center", minWidth: 120},
            {field: 'status', title: '单据状态', align: "center", minWidth: 120},
            /*{field: 'id', title: '任务id', align: "center",minWidth:120},*/
        ]],
        page: true
    });
//绑定日期控件
    laydate.render({
        elem: '#startTime'
        , theme: 'molv'
    });
    laydate.render({
        elem: '#endTime'
        , theme: 'molv'
    });
    //搜索
    form.on("submit(formSubmit)", function (data) {
        table.reload('activitiListenTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                temCode: $("#temCode").val(),
                temName: $("#temName").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val(),
                status: $("#status select").val()//单据状态

            }
        });
        return false;
    });
    //重置
    $('#reset').on('click', function () {
        $("#temCode").val("");
        $(".temName").val("");
        $(".startTime").val("");
        $(".endTime").val("");
    });

    //审核
    $("#audit").on("click", function () {
        var checkStatus = table.checkStatus('activitiListenTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            /*$.post("/system/activitiListener/isTaskTrue.json",{'taskId':data[0].id},
                function(obj) {
                    if(obj){//是（提示点击直接办结）
                        layer.alert("该环节为最后任务环节，请直接办结。");
                    }else{//不是最后环节*/
            thisPart(data[0].id);
            /*}
        },"JSON");*/
        } else {
            layer.msg('请选择一条数据。', {
                anim: 6
            });
        }
    });
    //另派
    $("#another").on("click", function () {
        var checkStatus = table.checkStatus('activitiListenTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            layer.open({
                type: 2,
                title: '另派人员选择',
                fixed: false,
                area: ['60%', '80%'],
                content: "system/activitiListener/thisPartAssent.htm?taskId=" + data[0].id,
                end: function () {
                    tableIns.reload();
                }
            });
        } else {
            layer.msg('请选择一条数据。', {
                anim: 6
            });
        }
    });
    //导出
    $("#export").on("click", function () {
        alert("=====");
        var temCode = $("#temCode").val();
        var temName = $("#temName").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        /*var datas =  "temCode="+temCode+"&temName="+temName+"&startTime="+startTime+"&endTime="+endTime;
        window.location = "expActivitiListen.json?"+datas;*/
        $.post("expActivitiListen.json", {
                'temCode': temCode,
                "temName": temName,
                "startTime": startTime,
                "endTime": endTime
            },
            function (data) {
                if (data) {
                    layer.msg('导出文件D:/流程监控.xls成功！');
                }
            }, "JSON");
    });

    function thisPart(taskId) {
        layer.open({
            type: 2,
            title: '环节人员选择',
            fixed: false,
            area: ['60%', '90%'],
            content: "system/activitiListener/findNextPart.htm?taskId=" + taskId + "&&comment=管理员审核&&isAdmin=true",
            end: function () {
                tableIns.reload();
            }
        });
    }

    layui.table.on('tool(activitiListen)', function (obj) {
        // 代码块
        var data = obj.data;
        $.post("/system/activitiButton/isCompletedTask.htm", {'taskId': data.id},
            function (obj) {
                if (obj) {//已完成的任务
                    parent.tab.tabAdd({
                        href: "/system/activitiButton/getOnePro.htm?taskId=" + data.id, //地址
                        title: "审批页面" + data.id,
                        id: data.id
                    });
                } else {
                    parent.tab.tabAdd({
                        href: data.url + "?taskId=" + data.id, //地址
                        title: "审批页面" + data.id,
                        id: data.id
                    });
                }
            }, "JSON");
    });
});








