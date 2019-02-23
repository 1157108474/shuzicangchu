layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var ran = Math.random();

    //�����б�
    table.render({
        elem: '#orderList'  //��table id
        , url: 'listOrderHistory.json'  //��������·��
        , cellMinWidth: 80
        , method: 'post'
        , where: {ran: ran++, relationGuid: $("#detailsGUID").val()}
        , cols: [[
            {field: 'materialCode', align: 'center', title: '���ϱ���'}
            , {
                field: 'operationType', align: 'center', title: '����', templet: function (d) {
                    if (d.operationType == '1') {
                        return "����";
                    } else {
                        return "�˻�";
                    }
                }
            }
            , {field: 'count', align: 'center', title: '����'}
            , {field: 'personname', align: 'center', title: '������'}
            , {
                field: 'createDate', align: 'center', title: '��������', templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }
        ]]
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: 'orderListReload'
    });
    var reload = function (data) {

        table.reload('orderListReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: JSON.parse(JSON.stringify(data.field))

        })
    };
});
