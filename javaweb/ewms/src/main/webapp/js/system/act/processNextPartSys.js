layui.use(['laydate', 'form', 'layer', 'table', 'laytpl','element'], function () {
    var laydate = layui.laydate;
    var laypage = layui.laypage
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.$,
        laytpl = layui.laytpl,
        table = layui.table,
        element = layui.element;
    //数据列表
    var taskId = $("#taskId").val();
    var outcome;
    var isCom = false;//连线点击行标识

    var $allTr = $(".layui-table,tbody").not($("#tableCon"));//设置点击行颜色
    ($allTr.find('tr').eq(1)).css("background-color","#e6e6e6");

    var userCount = $("#userCount").val();

    var tableIns = table.render({
        elem: '#processNextpart',
        url: 'findOutComeListByTaskId.json?taskId='+taskId+'&&isAdmin=true',
        cellMinWidth: 95,
        page: false,
        height: "full-125",
        id: "processNextpartTable",
        method: 'post',
        cols: [[
            /*{type: "checkbox", fixed: "left", width: 50},*/
            {title: '序号',templet: '#indexTpl',align: "center",width: 50},
            {field: 'outcome', title: '环节名称', align: "center",minWidth:120},
        ]],
        trclick:function (data,is) {
            if(isCom){
                isCom = false;
                is.prevAll().css("background-color","#ffffff");
                is.nextAll().css("background-color","#ffffff");
                is.css("background-color","#e6e6e6");
                $.post("findNextAssigneeCount.json", {'taskId': taskId, "outcome": data.outId},
                    function (obj) {
                        reloadUserTable(data,obj);
                    }, "JSON");

            }
        }

    });

    laypage.render({
        elem: 'demo1'
        ,count: userCount //数据总数
        ,jump: function(obj){
            $.post("findNextAssignee.json",{'taskId':taskId,"outcome":outcome,"cur":obj.curr},
                function(data) {
                    $("#tableCon").empty();
                        $(data).each(function(index,obj){
                            $("#tableCon").append("<tr><td><input type=\"radio\" value=\""+obj.code+","+obj.name+"\" id=\""+obj.code+"\" name=\"userRadio\"/></td><td>"+obj.code+"</td><td>"+obj.name+"</td></tr>");
                        });
                        form.render('radio');//重新渲染input
                    isCom = true;
                },"JSON");
            /*$("tr").css("background-color","#e6e6e6");*/
        }
    });
    /*var partUserTable = table.render({
        elem: '#partUser',
        url: 'findNextAssignee.json?taskId='+taskId,
        cellMinWidth: 95,
        page: true,
        height: "full-15",
        limits: [10, 20, 30, 40],
        limit: 20,
        id: "partUserTable",
        method: 'post',
        cols: [[
            {field: 'sex',templet: '#radioTpl',unresize:true,width: 50},
            /!* {type: "checkbox", fixed: "left", width: 50,event:"getUserId"},*!/
            {field: 'code', title: '编号', align: "center",minWidth:120},
            {field: 'name', title: '姓名', align: "center",minWidth:120},
        ]],
        page: true
    });*/
    form.on('radio', function(data){
        $(".userNames").empty();
        var arr = data.value.split(",");
        $(".userNames").append("<li id=\""+arr[0]+"\"  style=\"float: left\">"+arr[1]+"<i class=\"li-tab-cha\" onclick=\"cha()\">×</i><input type=\"hidden\" class=\"userId\" value=\""+arr[0]+"\"></li>");
    });
    window.emptyRadio = function(){
        $('input:radio')[0].checked = false;
        form.render('radio');
        $(".userNames").empty();
    }
    /*layui.table.on('radio(partUser)', function(obj) {
           var checkStatus = table.checkStatus('partUserTable');
            alert(checkStatus.data.length);
            //var data = checkStatus.data;
            var data = obj.data;
            var child=document.getElementById(data.code);
            var nnn = document.getElementsByName("layTableCheckbox");
            for(var i = 0;i<nnn.length;i++){
                if(nnn[i] === child){
                }else{
                    nnn[i].checked=false;
                }
            }
            if(child){
                child.parentNode.removeChild(child);
            }else{
                $(".userNames").empty();
                $(".userNames").append("<li id=\""+data.code+"\"  style=\"float: left\">"+data.name+"<i class=\"li-tab-cha\">×</i><input type=\"hidden\" class=\"userId\" value=\""+data.code+"\"></li>");
            }
    });*/
    function reloadUserTable(data,userCou){
        outcome = data.outId;
        $(".userNames").empty();
        $("#outcome").val(outcome);
        //$("#tableCon").empty();
        laypage.render({
            elem: 'demo1'
            ,count: userCou //数据总数
            ,jump: function(obj){
                $("#tableCon").empty();
                    $.post("findNextAssignee.json", {'taskId': taskId, "outcome": outcome, "cur": obj.curr},
                        function (data) {
                            $(data).each(function (index, obj) {
                                $("#tableCon").append("<tr><td><input type=\"radio\" value=\"" + obj.code + "," + obj.name + "\" id=\"" + obj.code + "\" name=\"userRadio\"/></td><td>" + obj.code + "</td><td>" + obj.name + "</td></tr>");
                            });
                            form.render('radio');//重新渲染input
                            isCom = true;
                        }, "JSON");
            }
        });
       /* $("#outcome").val(data.outcome);
        table.reload('partUserTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                taskId: $("#taskId").val(),
                outcome:data.outcome
            }
        });*/
    }

});







