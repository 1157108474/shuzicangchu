layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //�����б�
    table.render({
        elem: '#querygrid'
        , url: 'SXXGJ.json'
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 20   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '���',fixed: "left", width: 50}
                , {title: '�鿴', align: "center",fixed: "left", toolbar: '#bar', width: 50}
                , {field: 'code', title: '���ϱ���', align: "center", width: 120}
                , {field: 'name', title: '��������', align: "center", width: 180}
                , {field: 'description', title: '��������', align: "center", width: 230}
                , {field: 'models', title: '�����ͺ�', align: "center", width: 200}
                , {field: 'con', title: '�������', align: "center", width: 100}
                , {field: 'stockdown', title: '��������', align: "center", width: 100}
                , {field: 'stockup', title: '��������', align: "center", width: 100}
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                code:$("#code").val(),
                description: $("#description").val(),
                models: $("#models").val(),
                gjtype: $("#gjtype").val(),
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
        window.location.href="/system/sxxgj/exportSxxGjExcel.htm?code="+$("#code").val()+
            "&description="+$("#description").val()+"&models="+ $("#models").val()+ "&gjtype="+$("#gjtype").val()
    });
});
function showSheet(id) {
    parent.layer.open({
        type: 2,
        title: "��ϸ��Ϣ",
        area: ['674px', '340px'],
        resize: true,
        fixed: false, //���̶�
        maxmin: true,
        content: "/system/sxxgj/sxxGjDetails.htm?id="+id
    });
};
