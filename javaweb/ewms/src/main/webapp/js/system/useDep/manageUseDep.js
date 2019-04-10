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
        elem: '#useDepList'  //��table id
        , url: 'listUseDep.json'  //��������·��
        , cellMinWidth: 80
        , height: 'full-75'
        , method: 'post'
        , cols: [[
            {checkbox: true, fixed: 'left',width:30}
            , {field: 'code', align: 'center', title: '����', width: 100, fixed: 'left'}
            , {field: 'name', align: 'center', title: 'ʹ�õ�λ����', width: 200, fixed: 'left'}
            , {field: 'organizationName', align: 'center', title: '��֯����', width: 230}
            , {
                field: 'organizationType', align: 'center', title: '��֯����', width: 70, templet: function (d) {
                    if (d.organizationType == 1) {
                        return "����";
                    } else {
                        return "����";
                    }
                }
            }
            , {field: 'departName', align: 'center', title: '�����֯����', width: 95}
            , {field: 'ztName', align: 'center', title: '�����֯����', width: 230}
            , {
                field: 'status', align: 'center', title: '�Ƿ�����', minWidth: 70, templet: function (d) {
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
        , id: 'useDepListReload'
    });

    var resetSezrch = function () {
        $("#name").val('');
    };
    
    var reload = function (data) {

        table.reload('useDepListReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {name:$("#name").val()}

        })
    };

    //�༭
    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('useDepListReload');
        if (checkStatus.data.length != 1) {
            layer.msg("��ѡ��һ����¼���б༭", {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            layer.open({
                type: 2,
                title: '�༭ʹ�õ�λ',
                fixed: false,
                area: ['60%', '90%'],
                content: "/system/useDep/updateUseDep.htm?id=" + data[0].id,
                end:function(){
                    resetSezrch();
                }
            })
        }
    });

    //����
    $("#add").on("click", function (e) {
        layer.open({
            type: 2,
            title: '����ʹ�õ�λ',
            //shadeClose: true,
            //shade: 0.8,
            fixed: false,
            area: ['60%', '90%'],
            content: "/system/useDep/updateUseDep.htm",
            end:function(){
                resetSezrch()
            }
        })
    });

    //����
    $("#delete").on("click", function (e) {
        var checkStatus = table.checkStatus('useDepListReload');
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
                var url = "delUseDep.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('ɾ���ɹ�', {
                            time: 2000,
                            end: function () {
                                table.reload('useDepListReload');
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
    });

    //���Ϸ�Χ����
    $("#materielRange").on("click", function (e) {
        var checkStatus = table.checkStatus('useDepListReload');
        if (checkStatus.data.length != 1) {
            layer.msg("��ѡ��һ����¼���б༭", {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            var useId = data[0].id;
            var useName = data[0].name;
            layer.open({
                type: 2,
                title: '���Ϸ�Χ����-'+useName,
                fixed: false,
                area: ['90%', '90%'],
                content:"showMaterielRange.htm?id=" + useId + "&name=" + useName
            });
        }
    });

});
