layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['table', 'layer', 'vip_table'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var ran = Math.random();
    //�����б�
    table.render({
        elem: '#cKOrderList'  //��table id
        , url: '/sheet/ck/listCKOder.json'  //��������·��
        , cellMinWidth: 60
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]   //���ݷ�ҳ��
        , id: 'cKOrderReload'
        , height: 'full-50'
        , method: 'POST'
        , cols: [[

            {field: 'cksheetCode', align: 'center', title: '���ⵥ��', width: 150}
            , {
                field: 'createDate', align: 'center', title: '����ʱ��', width: 150, templet: function (d) {
                    return vipTable.dateformat(d.createDate);
                }
            }
            , {field: 'llSheetCode', align: 'center', title: '���뵥��', width: 150}
            , {field: 'useddepartname', align: 'center', title: 'ʹ�õ�λ', width: 260}
        ]]

        , trclick: function (data, tr) {
            parent.getCkOrder(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });

    var reload = function () {
        table.reload('cKOrderReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                cksheetCode: $("#code").val(),
                ran: ran++
            }
        })
    };
    $("#search").click(function () {
        reload();
    });


});



