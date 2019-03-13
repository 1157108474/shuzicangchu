layui.use(['laydate', 'form', 'layer', 'table', 'laytpl','element'], function () {
    var laydate = layui.laydate;
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        $ = layui.jquery,
        element = layui.element;


    //数据列表
    var tableIns = table.render({
        elem: '#activitiProcessing',
        url: 'findProcessingByperson.json?isAdmin=person',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 20,
        id: "activitiProcessingTable",
        method: 'post',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {
                field: 'code', title: '单据编号',width:210, align: "center", event: "getId", templet: function (d) {
                return "<a class='layui-table-link' href='javascript:;'>" + d.code + "</a>";
            }
            },
            {field: 'sheetName', title: '单据名称', align: "center",width:150},
            {field: 'submitMan', title: '提交人', align: "center",width:150},
            {field: 'name', title: '审核环节', align: "center",width:130},
            {field: 'assignee', title: '处理人', align: "center",width:150},
            {field: 'createTime', title: '提交时间', align: "center",width:160},
            {field: 'status', title: '单据状态', align: "center",width:120}
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
    layui.table.on('tool(activitiProcessing)', function(obj){
        // 代码块
        var data = obj.data;
//        alert(data.url);
        $.post("/system/activitiButton/isCompletedTask.htm",{'taskId':data.id},
            function(obj) {
               if(obj){//已完成的任务
                   parent.tab.tabAdd({
                       href:"/system/activitiButton/getOnePro.htm?taskId="+data.id+"&shenpi=shenpi", //地址
                       title: "审批页面"+data.id,
                       id:data.id
                   });
               }else{
                   parent.tab.tabAdd({
                       href:data.url+"?taskId="+data.id+"&shenpi=shenpi", //地址 href:data.url+"?taskId="+data.id,
                       title: "审批页面"+data.id,
                       id:data.id
                   });
               }
            },"JSON");
    });
    form.on("submit(formSubmit)", function (data) {
        table.reload('activitiProcessingTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                temCode:$("#temCode").val(),
                temName: $("#temName").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val()
            }
        });
        return false;
    });
    $('#reset').on('click',function(){
        $("#temCode").val("");
        $(".temName").val("");
        $(".startTime").val("");
        $(".endTime").val("");
    });
});








