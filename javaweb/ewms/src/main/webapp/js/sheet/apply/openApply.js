layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['table', 'layer','vip_table'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;
    var ran = Math.random();
    //�����б�
    table.render({
        elem: '#applyList'  //��table id
        , url: '/sheet/apply/listSqNum.json'  //��������·��
        , cellMinWidth: 60
        , method: 'POST'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]   //���ݷ�ҳ��
        , id: 'applyReload'
        , height: 'full-50'
        , where: {ztId: $("#ztId").val()}//��ʼ����
        , cols: [[
            {field: 'code', align: 'center', title: '���쵥��', width: 180}
            , {field: 'usedDep', align: 'center', title: 'ʹ�õ�λ', width: 150}
            , {field: 'applyDepartName', align: 'center', title: '���뵥λ������/���ţ�', width: 150}
            , {field: 'extendString1', align: 'center', title: '��;', width: 150}
            , {field: 'memo', align: 'center', title: '��ע', width: 150}
            , {field: 'createDate', align: 'center', title: '����ʱ��', width: 150, templet: function (d) {
                return vipTable.dateformat(d.createDate);
            }
            }
        ]]

        , trclick: function (data, tr) {
            parent.getApply(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });

    var reload = function () {
        table.reload('applyReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                code: $("#code").val(),
                ran: ran++
            }
        })
    };
    $("#search").click(function () {
        reload();
    });
});



