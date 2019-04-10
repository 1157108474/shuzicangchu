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
        , url: 'BZQGJ.json'
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '���',fixed: "left", width: 50}
                , {title: '�鿴', align: "center",fixed: "left", toolbar: '#bar', width: 50}
                , {field: 'materialcode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'materialname', title: '��������', align: "center", width: 130}
                , {field: 'description', title: '��������', align: "center", width: 240}
                , {field: 'materialmodel', title: '�����ͺ�', align: "center", width: 140}
                , {field: 'storecount', title: '�������', align: "center", width: 100}
                , {field: 'createdate', title: '���ʱ��', align: "center", width: 100}
                , {field: 'expirationtime', title: '������ʱ��', align: "center", width: 100}
                , {field: 'syday', title: 'ʣ������', align: "center", width: 100}
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                materialcode:$("#materialcode").val(),
                description: $("#description").val(),
                syday: $("#syday").val(),
                maturity: $("#maturity").val(),
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
        window.location.href="/system/bzqgj/exportBzqGjExcel.htm?materialcode"+$("#materialcode").val()+
            "&description="+$("#description").val()+"&syday="+ $("#syday").val()+ "&maturity="+$("#maturity").val()
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
        content: "/system/bzqgj/bzqGjDetails.htm?id="+id
    });
};
