layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element','vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var vipTable = layui.vip_table;
    var laydate = layui.laydate;

    //�����б�
    table.render({
        elem: '#querygrid'
        , url: 'listQueryCGDD.json'
        , cellMinWidth: 80
        , height: "full-140"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 15   //Ĭ��ʮ��������һҳ
        , limits: [15, 30, 45]  //���ݷ�ҳ��
        , id: "querygridTable"
        , cols: [
            [
                {type: 'numbers', title: '���',fixed: "left", width: 50}
                , {field: 'ordernum', title: '�ɹ��������',fixed: "left", align: "center", width: 180}
                , {field: 'ffcode', title: '���ź�', align: "center", width: 80}
                , {field: 'ordertype', title: '��������', align: "center", width: 105}
                , {field: 'stockorgcode', title: '�����֯����', align: "center", width: 120}
//                , {field: 'stockorgname', title: '�����֯����', align: "center", width: 280}
                , {field: 'isCount', title: '�ѽ���', align: "center", width: 110}
                , {field: 'noCount', title: 'δ����', align: "center", width: 110}
                , {field: 'materialcode', title: '���ϱ���', align: "center", width: 110}
                , {field: 'description', title: '��������', align: "center", width: 150}
                , {field: 'detailunit', title: '��λ', align: "center", width: 80}
                , {field: 'detailcount', title: '����', align: "center", width: 80}
                , {field: 'notaxpriceDouble', title: '����˰����', align: "center", width: 100}
                , {field: 'providerdepname', title: '��Ӧ��', align: "center", width: 210}
                , {field: 'consignmentname', title: '��������', align: "center", width: 95}
                , {
                field: 'createdate', title: '�Ƶ�����', align: "center", width: 185, templet: function (d) {
                    return datetimeformat(d.createdate);
                }
            }
                , {
                field: 'updatedate', title: '��������', align: "center", width: 185, templet: function (d) {
                    return datetimeformat(d.updatedate);
                }
            }
                , {field: 'status', title: '״̬', align: "center", width: 100}
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: JSON.parse(JSON.stringify(data.field))

        });
    };
    //����
    $("#export").click(function () {
        var parme = '?ordernum=' + $('#ordernum').val() + '&stockorgid=' + $('#stockorgidDiv select').val() +
            '&materialcode=' + $('#materialcode').val() + '&extendint1=' + $('#extendint1Div select').val() +
            '&description=' + $('#description').val() + '&providerName=' + $('#providerName').val() +
            '&providerId=' + $('#providerId').val() + '&consignment=' + $('#consignmentDiv select').val() +
            '&startTime=' + $('#startTime').val() + '&endTime=' + $('#endTime').val();
        window.location.href = '/sheet/query/exportCGDDExcel.htm' + parme;
    });

    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });

    $("#providerBtn").on("click", function (e) {
        vipTable.openPage("��Ӧ���б�", "/system/provider/generalProvider.htm", '60%', '90%');
        return false;

    });

    // �������ڲ��
    laydate.render({
        elem: '#startTime'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'date'
    });


});

function showSheet(id) {
    parent.layer.open({
        type: 2,
        title: "�����ϸ",
        area: ['90%', '80%'],
        resize: true,
        fixed: false, //���̶�
        maxmin: true,
        content: "/sheet/query/queryKCDetails.htm?id=" + id
    });
};