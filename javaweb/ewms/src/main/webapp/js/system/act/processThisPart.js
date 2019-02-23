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

    var $allTr = $(".layui-table,tbody").not($("#tableCon"));//设置点击行颜色
    ($allTr.find('tr').eq(1)).css("background-color","#e6e6e6");
    var userCount = $("#userCount").val();
    laypage.render({
        elem: 'demo1'
        ,count: userCount //数据总数
        ,jump: function(obj){
            $.post("findThisAssignee.json",{'taskId':taskId,"cur":obj.curr},
                function(data) {
                    $("#tableCon").empty();
                        $(data).each(function(index,obj){
                            $("#tableCon").append("<tr><td><input type=\"radio\" value=\""+obj.code+","+obj.name+"\" id=\""+obj.code+"\" name=\"userRadio\"/></td><td>"+obj.code+"</td><td>"+obj.name+"</td></tr>");
                        });
                        form.render('radio');//重新渲染input
                },"JSON");
        }
    });

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
});







