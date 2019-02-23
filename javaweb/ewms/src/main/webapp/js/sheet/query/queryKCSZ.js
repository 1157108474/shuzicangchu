layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;
    form.on("submit(formSubmit)", function (data) {
        if($("#startTime").val()==""&&$("#endTime").val()==""){
            layer.alert('请选择统计开始时间!', {title: '系统提示'});
            return false;
        }else{
            reload(data);
            return false;
        }
    });
    var reload = function (data) {
        table.reload('querygridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                materialcode:$("#materialcode").val(),
                materialname:$("#materialname").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val(),
                storeid: $("#storeid").val()
            }
        });
    };

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
    laydate.render({
        elem: '#startTime'
        , type: 'datetime'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'datetime'
    });

    $("#export").click(function () {
        window.location.href="/sheet/query/exportKCSZExcel.htm?materialcode="+$("#materialcode").val()+
            "&materialname="+$("#materialname").val()+"&startTime="+ $("#startTime").val()+"&endTime="+$("#endTime").val()+
            "&storeid="+$("#storeid").val()
    });
});