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
        , url: 'listQueryInventory.json'
        , cellMinWidth: 80
        , height: "full-186"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "querygridTable"
        , cols: [
            [{type: 'checkbox', title: 'ѡ��', fixed: "left",width: 50}
                ,{type: 'numbers', title: '���',fixed: "left", width: 50}
                , {title: '����', align: "center",fixed: "left", toolbar: '#bar', width: 80}
                , {field: 'ztidname', title: '�����֯', align: "center", width: 240}
                , {field: 'materialcode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'description', title: '��������', align: "center"}
                , {field: 'housename', title: '�ⷿ', align: "center", width: 100}
                , {field: 'detailunitname', title: '��λ', align: "center", width: 100}
                , {field: 'ownertypename', title: '�Ƿ����', align: "center", width: 120}
                /*, {field: 'providername', title: '��Ӧ��', align: "center", width: 200}*/
                , {field: 'storecount', title: '�������', align: "center", width: 120}
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ztid: $("#ztid").val(),
                materialcode:$("#materialcode").val(),
                /*materialname:$("#materialname").val(),*/
                flname: $("#flname").val(),
                /*providerdepid: $("#providerId").val(),*/
                description: $("#description").val(),
                storeid: $("#storeid").val(),
                ownertype: $("#ownertype").val(),
                startarea: $("#startarea").val(),
                endarea: $("#endarea").val()
            }
        });
    };

    //��ȡ��Ӧ���б�
    $("#providerBtn").on("click", function () {
        layui.layer.open({
            type: 2,
            title: '��Ӧ��',
            fixed: false,
            area: ['60%', '90%'],
            content: "../../system/provider/generalProvider.htm",
            // scrollbar:false
        });
        return false;
    });

    $("#reset").click(function () {
        $("#providerId").val("");
    });

    $("#print").click(function () {
        var checkStatus = table.checkStatus('querygridTable');
        var length = checkStatus.data.length;
        if (length < 1) {
            //���Ϸ�
            layer.msg('������ѡ��һ����Ϣ', {
                offset: 't',
                anim: 6
            })
        } else {
            layer.open({
                type: 2,
                title: '��ӡ',
                fixed: false,
                area: ['60%', '60%'],
                content: "/system/print/tag?length="+length,
                // scrollbar:false
                success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);
                    body.find('input').val(length);
                }
            })
        }
    });
    $("#export").click(function () {

        var data ="ztid="+ $("#ztid").val()+"&materialcode="+$("#materialcode").val()+
        "&flname="+ $("#flname").val()+
        /*"&providerdepid="+$("#providerId").val()+*/
        "&description="+$("#description").val()+"&storeid="+$("#storeid").val()+"&ownertype="+$("#ownertype").val()+
        "&startarea="+$("#startarea").val()+"&endarea="+$("#endarea").val();
        console.log(data);
        window.location.href="/sheet/query/exportKCExcel.htm?"+data
    });
});
function showSheet(id,storelocationcode) {
    parent.layer.open({
        type: 2,
        title: "�����ϸ",
        area: ['90%', '80%'],
        resize: true,
        fixed: false, //���̶�
        maxmin: true,
        content: "/sheet/query/queryKCDetails.htm?id="+id+"&storelocationcode="+storelocationcode
    });
};