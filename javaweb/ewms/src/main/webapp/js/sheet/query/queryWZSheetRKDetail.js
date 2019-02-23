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
        , url: 'listQueryWZSheetRKDetail.json'
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 20  //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '���', fixed: "left",width: 50}
                , {title: '�鿴', align: "center", fixed: "left",toolbar: '#bar', width: 80}
                , {field: 'code', title: '���ݱ��',fixed: "left", align: "center", width: 140}
                , {field: 'materialcode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'materialname', title: '��������', align: "center", width: 100}
                , {field: 'materialspecification', title: '���Ϲ��', align: "center", width: 100}
                , {field: 'materialmodel', title: '�����ͺ�', align: "center", width: 140}
                , {field: 'taxprice', title: '��˰����', align: "center", width: 100}
                , {field: 'subdetailcount', title: '�������', align: "center", width: 100}
                , {field: 'housename', title: '�ⷿ', align: "center", width: 100}
                , {field: 'storelocationname', title: '��λ', align: "center", width: 100}
                , {field: 'description', title: '����˵��', align: "center", width: 180}
                , {field: 'kindName', title: '����', align: "center", width: 100}
                , {
                    field: 'submittime', align: 'center', title: '���ʱ��',width: 100,templet: function (d) {
                    return datetimeformat(d.submittime);
                    }
                }
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ztid: $("#ztid").val(),
                code: $("#code").val(),
                storeid: $("#storeid").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val(),
                materialcode:$("#materialcode").val(),
                materialname:$("#materialname").val(),
            }
        });
    };
    laydate.render({
        elem: '#startTime'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'date'
    });
    //����������
    table.on('tool(querygrid)', function (obj) {
        var data = obj.data;
        if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: data.url+"?oper=show", //��ַ
                title: "�鿴���������ϸ"
            });
        }
    });
    $("#export").click(function () {
        window.location.href="/sheet/query/exportRKExcel.htm?code="+$("#code").val()+"&storeid="+$("#storeid").val()+
            "&startTime="+ $("#startTime").val()+"&endTime=" +$("#endTime").val()+"&materialcode="+$("#materialcode").val()+
            "&materialname="+$("#materialname").val()
    });
});
