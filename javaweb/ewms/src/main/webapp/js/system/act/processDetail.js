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
   /* var tableIns = table.render({
        elem: '#activitiProcessing',
        url: '',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 20,
        id: "activitiProcessingTable",
        method: 'post',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {title: '序号',templet: '#indexTpl',align: "center",width: 50},
            {field: 'id', title: '任务ID', align: "center",minWidth:120},
            {field: 'activityName', title: '任务名称', align: "center",minWidth:120},
            {field: 'assignee', title: '办理人', align: "center",minWidth:120},
            {field: 'startTime', title: '开始时间', align: "center",minWidth:120},
            {field: 'endTime', title: '结束时间', align: "center",minWidth:120}
        ]],
    });*/
    var tableHis = table.render({
        elem: '#activitiHis',
        url: '/system/activitiListener/historyActInstanceList.json',
        where:{taskId: $("#taskId").val()},
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 20,
        id: "activitiHisTable",
        method: 'post',
        cols: [[
            // {title: '序号',templet: '#indexTpl',align: "center",width: 50},
            {field: 'activityName', title: '环节名称', align: "center",minWidth:120},
            {field: 'assignee', title: '办理人', align: "center",minWidth:100},
            {field: 'endTime', title: '办理时间', align: "center",minWidth:120,templet:'<div>{{ layui.laytpl.toDateString(d.endTime) }}</div>'},
            {field: '', title: '操作结果', align: "center",minWidth:100,templet:function(d){
                if(d.endTime != null){
                    return "已处理"
                }else{
                    return "未处理"
                }
            }},
            {field: 'comment', title: '操作意见', align: "center",minWidth:100}
            ]],
        page: true
    });
    window.reloadHis = function(){

        //可以被外部引用
        table.reload('activitiHisTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                taskId: $("#taskId").val()
            }
        });
    }
    $("#submit").on("click", function () {//提交
        if($("#taskId").val() != ''){
            $.post("/system/activitiListener/isSheetTrue.json", {'taskId':  $("#taskId").val()},
                function(data) {
                    if(data.code){
                        nextPart();
                    }else{
                        layer.alert(data.message);
                    }
                },"JSON");
        }else{
            layer.msg('请先保存单据。', {
                anim: 6
            });
        }
    });
    $("#review").on("click", function () {//审核
        nextPart();
        //先判断是否已经审核过、是否是最后最后环节
        /* $.post("/system/activitiListener/isTaskTrue.json",{'taskId':taskId},
           function(data) {
               if(data){//是（提示点击直接办结）
                    layer.alert("该环节为最后任务环节，请直接办结。");
               }else{//不是最后环节
                    nextPart();
               }
           },"JSON");*/
    });
    /*$("#complete").on("click", function () {//办结
       //最后一个环节，直接完成任务
        $.post("/system/activitiListener/completeTask.htm",{'taskId':taskId},
              function(data){
                 alert("最后环节");
                  //完成任务后的页面处理
               },"JSON");
    });*/
   /* $("#reject").on("click", function (e) {//驳回到上环节
        $.post("/system/activitiButton/taskRollBack.htm",{'taskId':taskId},
            function(data) {
                //驳回任务后的页面处理
            },"JSON");
    });*/
     $("#reject").on("click", function () {//驳回到所选环节
         layer.open({
             type: 2,
             title: '驳回环节选择',
             fixed: false,
             area: ['50%', '40%'],
             content: "system/activitiButton/historyActInstanceList.htm?taskId="+$("#taskId").val()+"&&comment="+$("#comment").val(),
             end: function () {
                 $.post("/system/activitiListener/isProcessComplete.json",{'taskId':$("#taskId").val()},
                     function(data) {
                         if(data){
                             var url = location.href;
                             var parm = parseInt(Math.random() * 10);
                             if (url.lastIndexOf('?') > -1) {
                                 url = url +"&&time="+ parm;
                             } else {
                                 url = url + "?" + parm;
                             }
                             window.location.href = url;
                             //location.reload();
                         }
                     },"JSON");
             }
         });
     });
    /*$("#saveSheet").on("click", function () {//保存
        $.post("/system/processManage/startProIns.htm",{'menuCode':$("#menuCode").val()},
            function(data) {
                $("#taskId").val(data);
                alert("保存成功");
                //驳回任务后的页面处理
            },"JSON");

    });*/
    function nextPart(){
        layer.open({
            type: 2,
            title: '环节人员选择',
            fixed: false,
            area: ['60%', '90%'],
            content: "system/activitiListener/findNextPart.htm?taskId="+$("#taskId").val()+"&&comment="+$("#comment").val()+"&&isAdmin=true",
            end: function () {
                $.post("../../system/activitiListener/isProcessComplete.json",{'taskId':$("#taskId").val()},
                    function(data) {
                       if(data){
                           var url = location.href;
                           var parm = parseInt(Math.random() * 10);
                           if (url.lastIndexOf('?') > -1) {
                               url = url +"&&time="+ parm;
                           } else {
                               url = url + "?" + parm;
                           }
                            window.location.href = url;
                          // location.reload();
                       }
                    },"JSON");
               /*
                parent.tab.deleteTab($("#taskId").val());
                parent.tab.tabAdd({
                    href:"system/activitiButton/getOnePro.htm?taskId="+$("#taskId").val(), //地址
                    title: "任务完成"
                });*/
            }

        });
    }
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







