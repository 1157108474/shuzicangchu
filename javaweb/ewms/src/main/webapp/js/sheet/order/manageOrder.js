layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;
    var vipTable = layui.vip_table;

    //�����б�
    var ordergr = table.render({
        elem: '#ordergrid'
        , url: 'listManageOrder.json'
        , cellMinWidth: 80
        , height: "full-135"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "ordergridTable"
        , cols: [
            [{type: 'numbers', title: '���', fixed: "left", width: 50}
                , {title: '����',fixed: "left", toolbar: '#bar', width: 120}
                , {field: 'code', title: '���ݱ��', fixed: "left", align: "center", width: 175}
                , {field: 'ordernum', title: '�ɹ��������', align: "center", width: 200}
                , {field: 'depname', title: '�����֯', align: "center", width: 300}
                , {field: 'extendstring1', title: '��Ӧ������', align: "center", width: 220}
                , {field: 'extendstring3', title: '��������', align: "center", width: 120}
                , {field: 'statusname', title: '����״̬', align: "center", width: 120}
                , {field: 'personname', title: '�Ƶ���', align: "center", width: 120}
                , {
                field: 'createdate', title: '��������', align: "center", width: 200, templet: function (d) {
                    return datetimeformat(d.createdate);
                }
            }
            ]
        ]
    });

    var reload = function (data) {

        table.reload('ordergridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: JSON.parse(JSON.stringify(data.field))
        });
    };

    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });

   /* $("#order").on("click", function (e) {
        vipTable.openPage("�ɹ������б�", "/sheet/wzjs/generalOrder.htm", '90%', '80%');
        return false;
    });*/

    $("#provider").on("click", function (e) {
        vipTable.openPage("��Ӧ���б�", "/system/provider/generalProvider.htm", '60%', '90%');
        return false;

    });

    $("#creatorBtn").on("click", function (e) {
        vipTable.openPage("�Ƶ���", "../../system/user/publicDepartUser.htm?ztId=" + $("#ztid").val(), '85%', '90%');
        return false;

    });

    laydate.render({
        elem: '#startTime'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'date'
    });



    table.on('tool(ordergrid)', function (obj) {
    	var taskId = $("#taskId").val();
        var data = obj.data;
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: data.url + "?oper=edit"+"&taskId="+taskId, //��ַ
                title: "�༭���ʽ��յ�" + data.code
            });
        } else if (obj.event === 'show') {
            parent.tab.tabAdd({
                href: data.url + "?oper=show"+"&taskId="+taskId, //��ַ
                title: "�鿴���ʽ��յ�" + data.code
            });
        } else if (obj.event === 'delete') {
            layer.confirm('ȷ��ɾ��ѡ�еļ�¼��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.ajax({
                    type: "delete",
                    url: "/sheet/WZJS-" + data.id,
                    dataType: "json",
                    success: function (ret) {
                        if (ret.status == '1') {
                            ordergr.reload();
                            layer.msg("ɾ���ɹ���");
                            layer.close(index);
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

});

function orderGeneral(data) {
    document.getElementById("orderNum").value = data[0].ordernum;
}

function obtainUser(data, type) {
    document.getElementById("creatorName").value = data.name;
    document.getElementById("creator").value = data.id;
}




