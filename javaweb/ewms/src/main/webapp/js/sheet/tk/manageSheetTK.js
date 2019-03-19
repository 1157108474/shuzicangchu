layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;


    //�����б�
    var sheetgr = table.render({
        elem: '#sheetgrid'
        , url: 'tk/sheets'
        , cellMinWidth: 80
        , height: "full-130"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 20   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "sheetgridReload"
        , cols: [
            [{type: 'numbers', title: '���', fixed: "left", width: 50}
                , {field: 'action', title: '����', fixed: "left", width: 150, toolbar: '#bar'}
                , {field: 'code', title: '���ݱ��', fixed: "left", align: "center", width: 170}
//                , {field: 'ztidName', title: '�����֯', align: "center", width: 220}
                , {field: 'usedDepName', title: 'ʹ�ò���', align: "center", width: 170}
                , {field: 'departOfficeName', title: '�ϼ���������', align: "center", width: 170}
                , {field: 'ckCode', title: '���ⵥ��', align: "center", width: 170}
                , {field: 'statusName', title: '����״̬', align: "center", width: 100}
                , {
                field: 'extendInt3', title: '���³ɱ�', align: "center", width: 120, templet: function (d) {
                    if (d.extendInt3 == null || d.extendInt3 == 0) {
                        return 'δ����';
                    } else {
                        return '�Ѹ���';
                    }
                }
            }

                , {field: 'personName', title: '�Ƶ���', align: "center", width: 100}
                , {
                field: 'createDate', title: '�Ƶ�����', align: "center", width: 200, templet: function (d) {
                    return vipTable.datetimeformat(d.createDate);
                }
            }, {field: 'memo', title: '��ע', align: "center", width: 200}

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

    form.on('submit(search)', function (data) {
        reload(data);
        return false;
    });

    var reload = function (data) {
        table.reload('sheetgridReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: data.field

        })
    };

    $("#creatorBtn").on("click", function (e) {
        vipTable.openPage("�Ƶ���", "../../system/user/publicDepartUser.htm?ztId=" + $("#ztid").val(), '90%', '90%');
        return false;

    });


    //����������
    table.on('tool(sheetgrid)', function (obj) {

        var data = obj.data;
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: data.url + "?oper=edit", //��ַ
                title: "�༭�˿ⵥ" + data.code
            });
        } else if (obj.event === 'show') {
            parent.tab.tabAdd({
                href: data.url + "?oper=show", //��ַ
                title: "�鿴�˿ⵥ" + data.code
            });
        } else if (obj.event === 'renewalCost') {
            layer.confirm('ȷ�����³ɱ���', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post("/sheet/tk/renewalCost.json", {id: data.id}, function (data) {
                    sheetgr.reload();
                    layer.msg(data.message);
                    layer.close(index);
                });
            })
        } else if (obj.event === 'delete') {
            layer.confirm('ȷ��ɾ��ѡ�еļ�¼��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.ajax({
                    type: "delete",
                    url: "/sheet/WZTK-" + data.id,
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

});

function obtainUser(data, type) {
    document.getElementById("creatorName").value = data.name;
    document.getElementById("creatorId").value = data.id;
}


