layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;
    form.on("submit(formSubmit)", function (data) {
        if($("#startTime").val()==""&&$("#endTime").val()==""){
            layer.alert('��ѡ��ͳ�ƿ�ʼʱ��!', {title: 'ϵͳ��ʾ'});
            return false;
        }else{
            reload(data);
            return false;
        }
    });
    var reload = function (data) {
        table.reload('querygridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
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

    //������-��-�� ʱ���֣����͵�����
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