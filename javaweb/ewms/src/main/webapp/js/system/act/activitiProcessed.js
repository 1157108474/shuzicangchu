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
        elem: '#activitiProcessed',
        url: 'findProcessedByperson.json',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 30,
        id: "activitiProcessedTable",
        method: 'post',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {
                field: 'code', title: '单据编号', align: "center",width:210, event: "getId", templet: function (d) {
                return "<a class='layui-table-link' href='javascript:;'>" + d.code + "</a>";
            }
            },
            {field: 'sheetName', title: '单据名称', align: "center",width:150},
            {field: 'submitMan', title: '提交人', align: "center",width:150},
            {field: 'name', title: '审核环节', align: "center",width:130},
            {field: 'assignee', title: '处理人', align: "center",width:150},
            {field: 'endTime', title: '提交时间', align: "center",width:160},
            {field: 'status', title: '单据状态', align: "center",width:120}
        ]],
        page: true
    });
     laydate.render({
         elem: '#startTime'
         , theme: 'molv'
     });
     laydate.render({
         elem: '#endTime'
         , theme: 'molv'
     });
    layui.table.on('tool(activitiProcessed)', function(obj){
        // 代码块
        var data = obj.data;
        parent.tab.tabAdd({
            href:data.url+"?taskId="+data.id+"&&oper=show",
            title: "审批页面"+data.id,
            id:data.id
        });
        /*parent.tab.tabAdd({
            href: "/system/activitiButton/getOnePro.htm?taskId="+data.id, //地址
            title: "审批查看"
        });*/
    });
     form.on("submit(formSubmit)", function (data) {
         table.reload('activitiProcessedTable', {
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
    //时间戳的处理
    layui.laytpl.toDateString = function(d, format){
        var date = new Date(d || new Date())
            ,ymd = [
            this.digit(date.getFullYear(), 4)
            ,this.digit(date.getMonth() + 1)
            ,this.digit(date.getDate())
        ]
            ,hms = [
            this.digit(date.getHours())
            ,this.digit(date.getMinutes())
            ,this.digit(date.getSeconds())
        ];

        format = format || 'yyyy-MM-dd HH:mm:ss';

        return format.replace(/yyyy/g, ymd[0])
            .replace(/MM/g, ymd[1])
            .replace(/dd/g, ymd[2])
            .replace(/HH/g, hms[0])
            .replace(/mm/g, hms[1])
            .replace(/ss/g, hms[2]);
    };
    //数字前置补零
    layui.laytpl.digit = function(num, length, end){
        var str = '';
        num = String(num);
        length = length || 2;
        for(var i = num.length; i < length; i++){
            str += '0';
        }
        return num < Math.pow(10, length) ? str + (num|0) : num;
    };
});








