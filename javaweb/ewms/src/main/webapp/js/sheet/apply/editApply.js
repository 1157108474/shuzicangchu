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
        elem: '#detailsgrid'
        , url: '/sheet/apply/listSheetApply.json'
        , cellMinWidth: 80
        , height: "full-50"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20 ,30]  //���ݷ�ҳ��
        , where: {
            id: $("#id").val()
            , sheetId: $("#sheetId").val()
        }
        , id: "detailsgridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'extendString2', title: 'ʹ�õ�ַ', align: "center", width: 160, edit: 'text'}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 80}
                , {field: 'detailCount', title: '��������', align: "center", width: 80}
                , {field: 'ckCount', title: '�ѳ�������', align: "center", width: 100}
                , {field: 'snCode', title: '�ƻ����', align: "center", width: 150}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'description', title: '��������', align: "center", width: 320}

            ]
        ]
    });
    //�޸�
    $("#detail_edit").on("click", function (e) {
        var rows = table.checkStatus('detailsgridTable').data;
        if (rows.length > 0) {
            var extendString2 = rows[0].extendString2;//��ȡ��ַ
            $("#reloadDetailsgrid").val(1);
            if (null == extendString2 || extendString2 == "") {
                layer.msg("����дʹ�õ�ַ");
                return;
            }
            $.ajax({
                url: "/sheet/apply/updateApplySheet.json",
                type: "post",
                data: {id: rows[0].id, extendString2: extendString2},
                success: function (result) {
                    top.layer.msg(result.message, {time: 500});
                    if ('1' == data.status) {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }
                }
            });
        } else {
            layer.msg("��ѡ��һ����ϸ");
        }

    })
});

