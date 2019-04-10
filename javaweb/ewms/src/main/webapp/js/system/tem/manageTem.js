
layui.use(['laydate', 'form', 'layer', 'table'], function () {

    var form = layui.form,
        $ = layui.jquery,
        table = layui.table;

    //�����б�
    var tableIns = table.render({
        elem: '#manageFromTem',
        url: 'formTemplateList.json',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 30,
        id: "manageFromTemTable",
        method: 'post',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {title: '���', templet: '#indexTpl', align: "center", width: 60},
            {field: 'formTemCard', title: '���ݱ���', align: 'center', width: 180},
            {field: 'formTemName', title: '��������', align: 'center', width: 220},
            {field: 'formTemPre', title: '����ǰ׺', align: 'center', width: 180},
            {
                field: 'formTemSta', title: '״̬', align: 'center', width: 100, templet: function (d) {
                if (d.formTemSta == 1) {
                    return "����"
                } else {
                    return "����"
                }
            }},
            {field: 'formTemCom', title: '˵��', align: 'center', width: 120}
        ]],
        page: true
    });


    //���
    function addNews(tem_dic) {
        var tit = '��ӵ���',
            url = 'addFormTemplate.htm?tem_dic='+tem_dic;
        layui.layer.open({
            type: 2,
            title: tit,
            area: ['550px', '400px'],
            fixed: false,
            content: url
        });
    }
    //���
    $("#add").on("click", function () {
        var tem_dic = $("#tem_dicid").val();
        if(tem_dic==null||tem_dic==undefined||tem_dic==""){
            layer.msg('����ѡ�����ĵ��ݡ�', {
                anim: 6
            });
        }else{
            addNews(tem_dic);
        }
    });

    //�༭
    $("#edit").on("click", function () {
        var checkStatus = table.checkStatus('manageFromTemTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            tit = '�޸ĵ���';
            url = 'editFormTemplate.htm?id='+data[0].id;
            layui.layer.open({
                type: 2,
                title: tit,
                area: ['550px', '400px'],
                fixed: false,
                content: url
            });
        } else {
            layer.msg('��ѡ��һ�����ݡ�', {
                anim: 6
            });
        }
    });
    //�鿴
    $("#showRole").on("click", function (e) {
        var checkStatus = table.checkStatus('manageFromTemTable');
        data = checkStatus.data;
        if (data.length == 1) {
            layer.open({
                type: 2,
                title: '�鿴��Ϣ',
                area: ['450px', '450px'],
                fixed: false,
                content: '/system/role/showRole.htm?r.id=' + data[0].id
            });
        } else {
            layer.msg('��ѡ��һ�����ݡ�', {
                offset: 't',
                anim: 6
            });
        }
    });
    window.clickDic = function(){
        //���Ա��ⲿ����
        table.reload('manageFromTemTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                id: $("#tem_dicid").val()
            }
        });
    }
    //����ɾ��
    $("#delete").click(function () {
        var checkStatus = table.checkStatus('manageFromTemTable');
        var  data = checkStatus.data;
        var ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('ȷ��ɾ��ѡ�еĵ��ݣ�', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post("delFromTem.json?ids=" + ids , function (data) {
                    tableIns.reload();
                    layer.msg(data);
                    layer.close(index);
                })
            })
        } else {
            layer.msg('��ѡ��һ�����ݡ�', {
                offset: 't',
                anim: 6
            });
        }
    });
});
