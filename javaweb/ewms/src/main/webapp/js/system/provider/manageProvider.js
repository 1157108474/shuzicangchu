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
        elem: '#providerList'  //��table id
        , url: 'listProvider.json'  //��������·��
        , cellMinWidth: 80
        , height: 'full-70'
        , method: 'post'
        , cols: [[
            {checkbox: true, fixed: 'left',width:30}
            , {field: 'code', align: 'center', title: '����', width: 100, fixed: 'left'}
            , {field: 'name', align: 'center', title: '��Ӧ������', width: 200, fixed: 'left'}
            , {field: 'address', align: 'center', title: '��Ӧ�̵�ַ', width: 160}
            , {field: 'zipCode', align: 'center', title: '��������', width: 100,templet:function(d){
                if(d.zipCode == 0){
                    return "000000";
                }
            }}
            , {field: 'contactPerson', align: 'center', title: '��ϵ��', width: 80}
            , {field: 'contractPhone', align: 'center', title: '��ϵ�绰', width: 100}
            , {field: 'fax', align: 'center', title: '����', width: 100}
            , {field: 'email', align: 'center', title: '��������', width: 140}
            , {
                field: 'status', align: 'center', title: '�Ƿ�����', minWidth: 100, templet: function (d) {
                    if (d.status == 1) {
                        return "����";
                    } else {
                        return "����";
                    }
                }
            }
            , {field: 'memo', align: 'center', title: '��ע'}
        ]]
        , page: true   //������ҳ
        , limit: 30   //Ĭ�϶�ʮ������һҳ
        , limits: [10, 20, 30, 40]  //���ݷ�ҳ��
        , id: 'providerListReload'
    });
    var reload = function (data) {

        table.reload('providerListReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {name: $("#name").val()}

        })
    };

    var resetSearch = function () {
        $("#name").val('');
    };

    //�༭
    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('providerListReload');
        if (checkStatus.data.length != 1) {
            layer.msg("��ѡ��һ����¼���б༭", {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            layer.open({
                type: 2,
                title: '�༭��Ӧ��',
                fixed: false,
                area: ['40%', '90%'],
                content: "/system/provider/updateProvider.htm?id=" + data[0].id,
                end:function(){
                    resetSearch();
                }
            })
        }
    });

    //����
    $("#add").on("click", function (e) {
        layer.open({
            type: 2,
            title: '������Ӧ��',
            //shadeClose: true,
            //shade: 0.8,
            fixed: false,
            area: ['70%', '90%'],
            content: "/system/provider/updateProvider.htm",
            end:function(){
                resetSearch();
            }
        })
    });

    $("#delete").on("click", function (e) {
        var checkStatus = table.checkStatus('providerListReload');
        var datas = checkStatus.data;
        var arr = [];
        if (datas == "") {
            layer.msg("��ѡ��һ����¼����ɾ��", {
                offset: 't',
                anim: 6
            });
        } else {
            layer.confirm('��ȷ��Ҫɾ����', {
                icon: 3, title: '��ʾ��Ϣ', offset: '35%',
                btn: ['ȷ��', 'ȡ��'] //��ť
            }, function () {
                for (var i = 0; i < datas.length; i++) {
                    arr.push(datas[i].id);
                }
                var url = "delProvider.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('ɾ���ɹ�', {
                            time: 2000,
                            end: function () {
                                table.reload('providerListReload');
                            }
                        });

                    } else {
                        layer.alert(data.message, {
                            offset: '35%'
                        });
                    }
                })
            }, function () {
                layer.closeAll();
            });
        }
    })
});
