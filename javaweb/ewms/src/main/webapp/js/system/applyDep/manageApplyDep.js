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
        elem: '#applyDepList'  //��table id
        , url: 'listApplyDep.json'  //��������·��
        , cellMinWidth: 80
        , height: 'full-75'
        , method: 'post'
        , cols: [[
            {checkbox: true, fixed: 'left',width:30}
            , {field: 'code', align: 'center', title: '����', width: 100, fixed: 'left'}
            , {field: 'name', align: 'center', title: '���뵥λ����', width: 200, fixed: 'left'}
            , {field: 'subjectsGroup', align: 'center', title: '��Ŀ���', width: 120}
            , {field: 'subjectsGroupDescription', align: 'center', title: '��Ŀ���˵��', width: 160}
            , {field: 'departName', align: 'center', title: '�����֯����', width: 100}
            , {field: 'ztName', align: 'center', title: '�����֯����', width: 240}
            , {
                field: 'status', align: 'center', title: '�Ƿ�����', minWidth: 100, templet: function (d) {
                    if (d.status == 1) {
                        return "����";
                    } else {
                        return "����";
                    }
                }
            }
            , {field: 'demo', align: 'center', title: '˵��'}
        ]]
        , page: true   //������ҳ
        , limit: 30   //Ĭ�϶�ʮ������һҳ
        , limits: [10, 20, 30, 40]  //���ݷ�ҳ��
        , id: 'applyDepListReload'
    });
    var reload = function (data) {

        table.reload('applyDepListReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {name:$("#name").val()}

        })
    };

    //�༭
    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('applyDepListReload');
        if (checkStatus.data.length != 1) {
            layer.msg("��ѡ��һ����¼���б༭", {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            layer.open({
                type: 2,
                title: '�༭���뵥λ',
                fixed: false,
                area: ['60%', '90%'],
                content: "/system/applyDep/updateApplyDep.htm?id=" + data[0].id,
                end:function () {
                    resetSearch();
                }
            })
        }
    });

    var resetSearch = function () {
        $("#name").val('');
    };

    //����
    $("#add").on("click", function (e) {
        layer.open({
            type: 2,
            title: '�������뵥λ',
            //shadeClose: true,
            //shade: 0.8,
            fixed: false,
            area: ['60%', '90%'],
            content: "/system/applyDep/updateApplyDep.htm",
            end:function(){
                resetSearch();
            }
        })
    });

    $("#delete").on("click", function (e) {
        var checkStatus = table.checkStatus('applyDepListReload');
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
                var url = "delApplyDep.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('ɾ���ɹ�', {
                            time: 2000,
                            end: function () {
                                table.reload('applyDepListReload');
                            }
                        });

                    } else {
                        layer.alert("ɾ��ʧ��:"+data.message, {
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
