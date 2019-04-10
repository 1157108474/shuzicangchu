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
        elem: '#jsdList'  //��table id
        , url: 'listJSDList.json'  //��������·��
        , cellMinWidth: 80
        , method: 'post'
        , cols: [[
            {type: "checkbox", fixed: "left", width: 50}
            , {field: 'code', align: 'center', title: '���յ���', width: 190}
            , {field: 'ordernum', align: 'center', title: '�������', width: 250}
            , {field: 'extendstring1', align: 'center', title: '��Ӧ��', width: 290}
            , {field: 'extendstring3', align: 'center', title: '��������', width: 126}
            , {field: 'extendstring5', align: 'center', title: '���ź�'}
            , {
                field: 'createdate', align: 'center', title: '����ʱ��', width: 190, templet: function (d) {
                    return datetimeformat(d.createdate);
                }
            }
        ]]
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: 'listJSDGeneral'
    });
    var reload = function (data) {

        table.reload('listJSDGeneral', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: JSON.parse(JSON.stringify(data.field))

        })
    };

    //���
    $("#add").on("click", function (e) {
        var checkStatus = table.checkStatus('listJSDGeneral');
        if (checkStatus.data.length != 1) {
            layer.msg("ֻ��ѡ��һ������", {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            parent.JSDGeneral(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
});
