layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //�����б�
    table.render({
        elem: '#orderList'  //��table id
        , url: 'listOrderGeneral.json'  //��������·��
        , cellMinWidth: 50
        , method: 'post'
        , cols: [[
            {type: "checkbox", fixed: "left", width: 50}
            , {field: 'ordernum', align: 'center', title: '��������', width: 230}
            , {field: 'providerdepname', align: 'center', title: '��Ӧ��', width: 280}
            , {field: 'stockorgname', align: 'center', title: '�����֯', width: 370}
            , {field: 'ordertype', align: 'center', title: '��������', width: 120}
            , {field: 'issuecode', align: 'center', title: '���ź�', width: 100}
        ]]
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ������һҳ
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

    //���
    $("#add").on("click", function (e) {
        var checkStatus = table.checkStatus('orderListReload');
        if (checkStatus.data.length != 1) {
            layer.msg("ֻ��ѡ��һ������");
        } else {
            var data = checkStatus.data;
            parent.orderGeneral(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
});
