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
        , url: '/sheet/ck/listManageCK.json'
        , cellMinWidth: 60
        , height: "full-125"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��20������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , where:{
            ztId: $("#ztId").val()//�����֯
            ,creator: $("#creator").val()//�Ƶ���
        }
        , id: "llsheetgridTable"
        , cols: [
            [{type: 'numbers', title: '���',fixed: "left", width: 50}
                , {field: 'action', title: '����', fixed: "left",  width: 160, toolbar: '#bar'}
                , {field: 'code', title: '���ݱ��', fixed: "left", align: "center", width: 180}
                , {field: 'typeName', title: '��������', align: "center", width: 180}
//                , {field: 'orgName', title: '�����֯', align: "center",width: 260}
                , {field: 'useDepName', title: '���첿��', align: "center", width: 160}
                , {field: 'renewalCostStr', title: '���³ɱ�', align: "center", width: 120}
                , {field: 'statusName', title: '����״̬', align: "center", width: 100}
                , {field: 'personName', title: '�Ƶ���', align: "center", width: 100}
                , {field: 'createDate', title: '�Ƶ�����', align: "center", width: 200, templet: function (d) {
                return vipTable.dateformat(d.createDate);
            }}
            ]
        ]
    });

    //��ѯ�б�
    form.on("submit(formSubmit)", function (data) {
        table.reload('llsheetgridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ztId: $("#ztId").val()//�����֯
                ,creator: $("#creator").val()//�Ƶ���
                ,code: $("#code").val()
                ,typeId:$("#typeId select").val()//��������
                ,status: $("#status select").val()//����״̬
                ,beginDate: $("#beginDate").val()//��ʼʱ��
                ,endDate: $("#endDate").val()//����ʱ��
                ,extendint3: $("#extendint3 select").val()//�Ƿ���³ɱ�
            }
        });
        return false;
    });

    //�����ڿؼ�
    laydate.render({
        elem: '#beginDate'
    });
    //�����ڿؼ�
    laydate.render({
        elem: '#endDate'
    });
    //����������
    table.on('tool(llsheetgrid)', function (obj) {
        var data = obj.data;
        console.log(data);
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: "/sheet/ck/"+data.id+"?oper=edit", //��ַ
                title: "�༭���ⵥ"+data.code
            });
        }else if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: "/sheet/ck/"+data.id+"?oper=show", //��ַ
                title: "�鿴���ⵥ:"+data.code
            });
        }else if(obj.event === 'renewalCost'){
            layer.confirm('ȷ�����³ɱ���', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post("/sheet/ck/renewalCost.json",{id:data.id}, function (data) {
                    llsheetgr.reload();
                    layer.msg(data.message);
                    layer.close(index);
                });
            })
        }else if(obj.event === 'delete'){
            layer.confirm('ȷ��ɾ��ѡ�еļ�¼��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post("/sheet/ck/delSheetCk.json",{id:data.id}, function (data) {
                    llsheetgr.reload();
                    layer.msg(data.message);
                    layer.close(index);
                });
            })
        }
    });

});

