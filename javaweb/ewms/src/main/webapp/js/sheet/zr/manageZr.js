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
        , url: 'listManageZr.json'
        , cellMinWidth: 80
        , height: "full-135"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "rkgridTable"
        , cols: [
            [{type: 'numbers', title: '���',fixed: 'left', width: 50}
                , {title: '����',fixed: 'left', toolbar: '#bar', width: 100}
                , {field: 'code', title: '���ݱ��', fixed: 'left',align: "center", width: 150}
                , {field: 'depname', title: '�����֯', align: "center", width: 300}
                , {field: 'extendstring1', title: '��Ӧ������', align: "center", width: 245}
                , {field: 'statusname', title: '����״̬', align: "center", width: 100}
                , {
                field: 'createdate', title: '�Ƶ�����', align: "center", width: 195, templet: function (d) {
                    return datetimeformat(d.createdate);
                }
            }
                , {field: 'personname', title: '�Ƶ���', align: "center", width: 100}
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
                title: "�༭�������뵥" + data.code
            });
        } else if (obj.event === 'show') {
            parent.tab.tabAdd({
                href: data.url + "?oper=show", //��ַ
                title: "�鿴�������뵥" + data.code
            });
        } else if (obj.event === 'delete') {
            layer.confirm('ȷ��ɾ��ѡ�еļ�¼��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.ajax({
                    type: "delete",
                    url: "/sheet/zr/delete-" + data.id,
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

