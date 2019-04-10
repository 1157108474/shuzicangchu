layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //�����б�
    table.render({
        elem: '#querygrid'
        , url: 'listQueryJCCX.json'
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '���',fixed: "left", width: 50}
                , {field: 'materialcode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'materialname', title: '��������', align: "center", width: 120}
                , {field: 'flname', title: '���Ϸ���', align: "center", width: 120}
                , {field: 'materialspecification', title: '���Ϲ��', align: "center", width: 120}
                , {field: 'materialmodel', title: '�����ͺ�', align: "center",width: 120}
                , {field: 'storecount', title: '�������', align: "center", width: 120}
                , {field: 'housename', title: '�ⷿ', align: "center", width: 120}
                , {field: 'storelocationname', title: '��λ', align: "center", width: 120}
                , {field: 'description', title: '����˵��', align: "center", width: 120}
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                materialcode:$("#materialcode").val(),
                materialname:$("#materialname").val(),
                storeid: $("#storeid").val(),
                storelocationid: $("#locationId").val(),
            }
        });
    };

    $("#locationBtn").on("click", function () {
        if($("#storeid").val()==""){
            alert("����ѡ��ⷿ��");
        }else{
            layui.layer.open({
                type: 2,
                title: '��λ',
                fixed: false,
                area: ['60%', '90%'],
                content: "../../system/ware/location?parentId=" + $("#storeid").val()
                // scrollbar:false
            });
            return false;
        }
    });

    $("#reset").click(function () {
        $("#locationId").val("");
    });

});