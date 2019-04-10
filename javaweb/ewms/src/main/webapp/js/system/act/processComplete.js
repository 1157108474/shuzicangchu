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
        elem: '#processComplete',
        url:'../../system/activitiListener/processComplete.json?taskId='+$("#taskId").val(),
        cellMinWidth: 95,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 30,
        id: "processCompleteTable",
        method: 'post',
        cols: [[
            {title: '序号',templet: '#indexTpl',align: "center",width: 50},
            {field: 'id', title: '任务ID', align: "center",minWidth:120},
            {field: 'name', title: '环节名称', align: "center",minWidth:120},
            {field: 'assignee', title: '办理人', align: "center",minWidth:120},
            {field: 'endTime', title: '办理时间', align: "center",minWidth:120,templet:function (d){
                return datetimeformat(d.endTime);
                }
            }
        ]],
    });
    //返回年-月-日 时：分：秒型的日期
    function datetimeformat(longdate) {
        if (null == longdate || '' == longdate) {
            return '';
        } else {
            var date = new Date(longdate);
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var seconds = date.getSeconds();
            return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + " " + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (seconds < 10 ? "0" + seconds : seconds);
        }
    }
//绑定日期控件
    laydate.render({
        elem: '#startTime'
        , theme: 'molv'
    });
    laydate.render({
        elem: '#endTime'
        , theme: 'molv'
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








