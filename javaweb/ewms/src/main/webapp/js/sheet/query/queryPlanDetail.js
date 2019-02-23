layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;

    //��ϸ�б�
    var planDetailGr = table.render({
        elem: '#planDetailGrid'
        , url: '/sheet/query/listPlanDetail.json'
        , cellMinWidth: 60
        , height: "full-125"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 20   //Ĭ�϶�ʮ������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "planDetailGridTable"
        , cols: [
            [{type: 'numbers', title: '���', width: 50}
                , {field: 'deptName', title: '�ƻ�����', align: "center", width: 260}
                , {field: 'planCode', title: '�ƻ����', align: "center", width: 150}
                , {field: 'userDepName', title: 'ʹ�õ�λ', align: "center", width: 220}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 150}
                , {field: 'materialDes', title: '��������', align: "center", width: 320}
                , {field: 'planCount', title: '�ƻ�����', align: "center", width: 80}
                , {field: 'haveslCount', title: '����������', align: "center", width: 100}
                , {field: 'storeCount', title: '�������', align: "center", width: 80}
                , {field: 'storeuseCount', title: '����������', align: "center", width: 120}
                , {field: 'unIt', title: '��λ', align: "center", width: 60}
                , {
                field: 'planDate', title: '�ƻ�����', align: "center", width: 100, templet: function (d) {
                    return vipTable.dateformat(d.planDate);
                }
            }
            ]
        ]

    });
    //��ѯ�ƻ���ϸ
    form.on("submit(formSubmit)", function (data) {
        table.reload('planDetailGridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                planCode: $("#planCode").val()
                , deptName: $("#deptName").val()
                , userDepName: $("#userDepName").val()
                , materialCode: $("#materialCode").val()
                , materialDes: $("#materialDes").val()
            }
        });
        return false;
    });
    $("#export").click(function () {
        var parme = '?planCode=' + $('#planCode').val() + '&deptName=' + $('#deptName').val() +
            '&userDepName=' + $('#userDepName').val() + '&materialCode=' + $('#materialCode').val() +
            '&materialDes=' + $('#materialDes').val();
        window.location.href = '/sheet/query/exportPlanExcel.htm' + parme;
    });
});

