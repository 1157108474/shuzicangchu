layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var creator = $("#creator").val();
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //�����б�
    table.render({
        elem: '#rkdList'  //��table id
        , url: 'listRKDList.json?creator=' + creator  //��������·��
        , cellMinWidth: 100
        , method: 'post'
        , cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'code', align: 'center', title: '��ⵥ��', width: 170}
            , {field: 'orderNum', align: 'center', title: '�ɹ��������', width: 170}
            , {field: 'extendString1', align: 'center', title: '��Ӧ��'}
            , {field: 'extendString3', align: 'center', title: '��������'}
            , {field: 'extendString2', align: 'center', title: '�����֯����'}
            , {field: 'extendString5', align: 'center', title: '���ź�'}
        ]]
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: 'listRKDGeneral'
        , trclick: function (data,tr) {
           
            parent.setRkInfo(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
    var reload = function (data) {

        table.reload('listRKDGeneral', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: JSON.parse(JSON.stringify(data.field))

        })
    };

    // //���
    // $("#add").on("click", function (e) {
    //     var checkStatus = table.checkStatus('listRKDGeneral');
    //     if (checkStatus.data.length != 1) {
    //         layer.msg("ֻ��ѡ��һ������", {
    //             offset: 't',
    //             anim: 6
    //         })
    //     } else {
    //         var data = checkStatus.data;
    //         parent.RKDGeneral(data);
    //         var index = parent.layer.getFrameIndex(window.name);
    //         parent.layer.close(index);
    //     }
    // });
});
