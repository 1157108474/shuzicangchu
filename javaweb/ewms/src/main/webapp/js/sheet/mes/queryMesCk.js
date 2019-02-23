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
        , url: 'listMesCk.json'
        , cellMinWidth: 80
        , height: "full-155"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 15   //Ĭ��ʮ��������һҳ
        , limits: [15, 30, 45]  //���ݷ�ҳ��
        , id: "querygridTable"
        , cols: [
            [
                {type: 'numbers', title: '���',fixed: "left", width: 50}
                , {field: 'transDate', title: '�ƻ����', fixed: "left",align: "center", width: 145}
                , {field: 'locatorCode', title: '��λ����', align: "center", width: 135}
                , {field: 'locatorName', title: '��λ����', align: "center", width: 135}
                , {field: 'subinvCode', title: '�ⷿ����', align: "center", width: 135}
                , {field: 'itemNo', title: '���ϱ���', align: "center", width: 135}
                , {field: 'itemDesc', title: '��������', align: "center", width: 135}
                , {field: 'use', title: '��;', align: "center", width: 240}
                , {field: 'transQuantity', title: '��������', align: "center", width: 135}
                , {field: 'transUom', title: '���ⵥλ', align: "center", width: 135}
                , {field: 'shifts', title: '���', align: "center", width: 135}
                , {field: 'batchName', title: '����', align: "center", width: 135}
                ,{
                field: 'createDate', title: '����ʱ��', align: "center", width: 194, templet: function (d) {
                    return datetimeformat(d.createDate);
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
            where: JSON.parse(JSON.stringify(data.field))

        });
    };

    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });


});
