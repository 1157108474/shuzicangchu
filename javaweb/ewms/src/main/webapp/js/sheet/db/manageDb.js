layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['form', 'table', 'layer', 'laydate', 'vip_table'], function () {

    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;


    //�����б�
    var sheetgr = table.render({
        elem: '#sheetgrid'
        , url: 'db/sheets'
        , cellMinWidth: 80
        , height: "full-140"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 20   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "sheetgridReload"
        , cols: [
            [{type: 'numbers', title: '���',fixed: "left", width: 50}
                ,{field: 'action', title: '����', fixed: "left",  width: 110, toolbar: '#barDemo'}
                , {field: 'code', title: '���ݱ��', fixed: "left", align: "center", width: 145}
                , {field: 'outOrgName', title: '���������֯', align: "center", width: 250}
                , {field: 'intoOrgName', title: '��������֯', align: "center", width: 250}
                , {field: 'statusName', title: '����״̬', align: "center", width: 90}
                , {field: 'personName', title: '�Ƶ���', align: "center", width: 95}
                , {field: 'createDate', title: '�Ƶ�����', align: "center", width: 155,
                templet: function (d) {
                    return datetimeformat(d.createDate)
                }}
                , {field: 'memo', title: '��ע', align: "center"}

            ]
        ]
    });

    var laydate = layui.laydate;
    //�����÷�
    laydate.render({
        elem: '#begin'
    });
    laydate.render({
        elem: '#end'
    });
    form.on('submit(search)', function(data){
        reload(data);
        return false;
    });

    var reload =function(data){
        table.reload('sheetgridReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: data.field
                //JSON.parse(JSON.stringify(data.field))

        })
    };

    //����������
    table.on('tool(sheetgrid)', function (obj) {

        var data = obj.data;

        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: data.url+"?oper=edit", //��ַ
                title: "�༭������" + data.code
            });
        }else if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: data.url+"?oper=show", //��ַ
                title: "�鿴������" + data.code
            });
        }else if(obj.event === 'delete'){
            layer.confirm('ȷ��ɾ��ѡ�еļ�¼��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.ajax({
                    type: "delete",
                    url: "/sheet/WZDBD-"+data.id,
                    dataType: "json",
                    success: function (ret) {
                        if (ret.status == '1') {
                            layer.alert("ɾ���ɹ�");
                            $("#submitBtn").click();
                        } else {
                            layer.alert('ɾ��ʧ�ܣ�' + ret.message);
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("ɾ���������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                    }
                });

            })
        }
    });

    $("#creatorBtn").on("click", function (e) {
        vipTable.openPage("�Ƶ���", "../../system/user/publicDepartUser.htm?ztId=" + $("#ztid").val(), '90%', '90%');
        return false;

    });
});

function obtainUser(data,type) {
    document.getElementById("creatorName").value = data.name;
    document.getElementById("creatorId").value = data.id;
}

