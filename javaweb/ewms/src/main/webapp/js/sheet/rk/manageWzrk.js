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
        elem: '#rkgrid'
        , url: 'listManageRk.json'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 20   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "rkgridTable"
        , cols: [
            [{type: 'numbers', title: '���',fixed: "left", width: 50}
                , {title: '����', fixed: "left", toolbar: '#bar', width: 120}
                , {field: 'code', title: '���ݱ��', fixed: "left", align: "center", width: 170}
                // , {field: 'extendstring6', title: 'ERP���յ���', align: "center", width: 180}
                , {field: 'typename', title: '�������', align: "center", width: 130}
                , {field: 'ordernum', title: '�ɹ��������', align: "center", width: 190}
                , {field: 'receivenum', title: '���յ���', align: "center", width: 170}
                , {field: 'depname', title: '�����֯', align: "center", width: 280}
                , {field: 'extendstring1', title: '��Ӧ������', align: "center", width: 230}
                , {field: 'statusname', title: '����״̬', align: "center", width: 120}
                , {
                field: 'createdate', title: '�Ƶ�����', align: "center", width: 180, templet: function (d) {
                return datetimeformat(d.createdate);
            }}
                , {field: 'personname', title: '�Ƶ���', align: "center", width: 120}
            ]
        ]
    });

    var reload = function (data) {
        table.reload('rkgridTable', {
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

    laydate.render({
        elem: '#startTime'
        , type: 'date'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'date'
    });

    $("#creatorBtn").on("click", function (e) {
        vipTable.openPage("�Ƶ���", "../../system/user/publicDepartUser.htm?ztId=" + $("#ztid").val(), '85%', '90%');
        return false;

    });

    //����������
    table.on('tool(rkgrid)', function (obj) {

        var data = obj.data;
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: data.url + "?oper=edit", //��ַ
                title: "�༭������ⵥ" + data.code
            });
        } else if (obj.event === 'show') {
            parent.tab.tabAdd({
                href: data.url + "?oper=show", //��ַ
                title: "�鿴������ⵥ" + data.code
            });
        } else if (obj.event === 'delete') {
            layer.confirm('ȷ��ɾ��ѡ�еļ�¼��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.ajax({
                    type: "delete",
                    url: "/sheet/delete-" + data.id,
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

function obtainUser(data, type) {
    document.getElementById("creatorName").value = data.name;
    document.getElementById("creator").value = data.id;
}

