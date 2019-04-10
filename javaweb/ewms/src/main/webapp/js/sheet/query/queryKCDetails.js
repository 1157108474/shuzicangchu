layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    //�����б�
    table.render({
        elem: '#querygrid'
        , url: 'listQueryKCDetails.json'
        , where: {
            materialcode: $("#materialcode").val(),
            storeid: $("#storeid").val(),
            storelocationcode: $("#storelocationcode").val(),
        }
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '���', width: 50}
                , {field: 'housename', title: '�ⷿ', align: "center", width: 120}
                , {field: 'storelocationname', title: '��λ', align: "center"}
                , {field: 'detailunitname', title: '��λ', align: "center", width: 100}
                , {field: 'storecount', title: '�������', align: "center", width: 100}
                , {field: 'tagcode', title: '���к�', align: "center", width: 100}
            ]
        ]
    });
});
