layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

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
        , url: 'listQueryCGJH.json'
        , cellMinWidth: 80
        , height: "full-155"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [15, 30, 45]  //���ݷ�ҳ��
        , id: "querygridTable"
        , cols: [
            [
                {type: 'numbers', title: '���', fixed: "left", width: 50}
                , {field: 'planCode', title: '�ƻ����', fixed: "left", align: "center", width: 145}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 135}
                , {field: 'materIaldes', title: '��������', align: "center", width: 270}
                , {field: 'applyDepName', title: '�걨��λ', align: "center", width: 308}
                , {field: 'useDepName', title: 'ʹ�õ�λ', align: "center", width: 240}
                , {field: 'count', title: '����', align: "center", width: 80}
                , {field: 'unIt', title: '��λ', align: "center", width: 80}
                , {
                field: 'createDate', title: '�ƻ��ƶ�����', align: "center", width: 194, templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }
                , {
                field: 'needDate', title: '��������', align: "center", width: 194, templet: function (d) {
                    return datetimeformat(d.needDate);
                }
            }
                , {field: 'status', title: '״̬', align: "center", width: 80}
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
    $("#export").click(function () {
        var parme = '?planCode=' + $('#planCode').val() + '&materialCode=' + $('#materialCode').val() +
            '&materIaldes=' + $('#materIaldes').val() + '&applyDepName=' + $('#applyDepName').val() +
            '&applyDepId=' + $('#applyDepId').val() + '&useDepName=' + $('#useDepName').val() +
            '&useDepId=' + $('#useDepId').val() + '&status=' + $('#statusDiv select').val() +
            '&startTime1=' + $('#startTime1').val() + '&endTime1=' + $('#endTime1').val() +
            '&startTime2=' + $('#startTime2').val() + '&endTime2=' + $('#endTime2').val();
        window.location.href = '/sheet/query/exportCGJHExcel.htm' + parme;
    });
    //�������뵥λҳ��
    $("#useDepName").on("click", function (e) {
        var url = "/system/useDep/generalUseDep.htm";
        vipTable.openPage("���뵥λ�б�", url, "75%", "85%");
    });

    //����ʹ�õ�λҳ��
    $("#applyDepName").on("click", function (e) {
        var url = "/system/applyDep/generalApplyDep.htm";
        vipTable.openPage("ʹ�õ�λ�б�", url, "75%", "85%");
    });

    // �������ڲ��
    laydate.render({
        elem: '#startTime1'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime1'
        , type: 'date'
    });
    laydate.render({
        elem: '#startTime2'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime2'
        , type: 'date'
    });

    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });


});

function applyDepGeneral(data) {
    document.getElementById("applyDepName").value = data[0].name;
    document.getElementById("applyDepId").value = data[0].id;
}

function UseDepGeneral(data) {
    document.getElementById("useDepName").value = data[0].name;
    document.getElementById("useDepId").value = data[0].id;
}

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
}