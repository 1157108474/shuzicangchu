layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;


    //�����б�
    var llsheetgr = table.render({
        elem: '#llsheetgrid'
        , url: '/sheet/apply/listManageApply.json'
        , where: {
            usedDepartId: $("#usedDepartId").val()//ʹ�õ�λ
        }
        , cellMinWidth: 80
        , height: "full-150"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ�϶�ʮ������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "llsheetgridTable"
        , cols: [
            [{type: 'numbers', title: '���', fixed: "left", width: 50}
                , {field: 'action', title: '����', fixed: "left", width: 120, toolbar: '#bar'}
                , {field: 'code', title: '���ݱ��', fixed: "left", align: "center", width: 170}
                // , {field: 'officeName', title: '�ϼ���������', align: "center", width: 180}
                , {field: 'applyunitName', title: '���뵥λ', align: "center", width: 180}
                , {field: 'statusName', title: '����״̬', align: "center", width: 100}
                , {field: 'useUnitName', title: 'ʹ�õ�λ', align: "center", width: 180}
                , {field: 'extendString2', title: '�����֯', align: "center", width: 260}
                , {field: 'personName', title: '�Ƶ���', align: "center", width: 100}
                , {
                field: 'createDate', title: '�Ƶ�����', align: "center", width: 200, templet: function (d) {
                    return vipTable.dateformat(d.createDate);
                }
            }
                , {field: 'extendString1', title: '��;', align: "center", width: 100}
            ]
        ]
    });

    //�����ڿؼ�
    laydate.render({
        elem: '#beginDate'
    });
    //�����ڿؼ�
    laydate.render({
        elem: '#endDate'
    });
    //��ѯ�б�
    form.on("submit(formSubmit)", function (data) {
        table.reload('llsheetgridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ztId: $("#ztId select").val()//�����֯
                , creator: $("#workManId").val()//�Ƶ���
                , code: $("#code").val()
                , status: $("#status select").val()//����״̬
                , beginDate: $("#beginDate").val()//��ʼʱ��
                , endDate: $("#endDate").val()//����ʱ��
                , usedDepartId: $("#usedDepartId").val()//ʹ�õ�λ
                , applyDepartId: $("#applyDepartId").val()//���뵥λ
                // ,officesId: $("#officesId").val()//�ϼ���������
            }
        });
        return false;
    });

    //�����ϼ���������ҳ��
    $("#openOffice").on("click", function (e) {
        var url = "/system/useDep/generalUseDepDepart.htm";
        vipTable.openPage("����", url, "75%", "85%");
    });
    //�����Ƶ���ҳ��
    $("#openWorkMan").on("click", function (e) {
        var url = "/sheet/apply/publicDepartUser.htm";
        vipTable.openPage("��Ա��Ϣ", url, "85%", "90%");
    });
    //����������
    table.on('tool(llsheetgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: "/sheet/apply/" + data.id + "?oper=edit", //��ַ
                title: "�༭���쵥" + data.code
            });
        } else if (obj.event === 'show') {
            parent.tab.tabAdd({
                href: "/sheet/apply/" + data.id + "?oper=show", //��ַ
                title: "�鿴���쵥:" + data.code
            });
        } else if (obj.event === 'delete') {
            layer.confirm('ȷ��ɾ��ѡ�еļ�¼��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post("/sheet/apply/delSheet.json", {id: data.id}, function (data) {
                    llsheetgr.reload();
                    layer.msg(data.message);
                    layer.close(index);
                });
            })
        }
    });
});

//��ȡ��Ա��Ϣҳ��ش�����
function obtainUser(data, type) {
    document.getElementById("workManName").value = data.name;
    document.getElementById("workManId").value = data.id;
}

//�ϼ�����ҳ�淵�ش�����
function UseDepDepartGeneral(data) {
    document.getElementById("officesName").value = data[0].name;
    document.getElementById("officesId").value = data[0].id;
}
