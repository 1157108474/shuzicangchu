layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var laydate = layui.laydate;
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
        elem: '#logList'  //��table id
        , url: 'listLog.json'  //��������·��
        , cellMinWidth: 80
        , height: 'full-75'
        , method: 'post'
        , cols: [[
            {checkbox: true, fixed: 'left',width:30}
            , {
                field: 'logType', align: 'center', title: '��־����', width: 100, fixed: 'left', templet: function (d) {
                    if (d.logType == 0) {
                        return "ϵͳ��־";
                    } else {
                        return "ҵ����־";
                    }
                }
            }
            , {
                field: 'logObject',
                align: 'center',
                title: '��־����',
                width: 160,
                fixed: 'left',
                templet: function (d) {
                    switch (d.logObject) {
                        case 100:
                            return "���ʽ���";
                        case 200:
                            return "�������";
                        case 300:
                            return "��������";
                        case 400:
                            return "���ʳ���";
                        case 500:
                            return "�ƿ���λ";
                        case 600:
                            return "�����˿�";
                        case 700:
                            return "�����˻�";
                        case 800:
                            return "���ʵ���";
                        case 900:
                            return "�̵㵥";
                        case 1000:
                            return "�����֧����";
                        case 1100:
                            return "��֯����";
                        case 1200:
                            return "��Ա����";
                        case 1300:
                            return "�˵�����";
                        case 1400:
                            return "��ɫ����";
                        case 1500:
                            return "���Ϸ���";
                        case 1600:
                            return "���Ϲ���";
                        case 1700:
                            return "�ⷿ��������";
                        case 1800:
                            return "��λ��ǩ��ӡ";
                        case 1900:
                            return "��Ӧ�̹���";
                        case 2000:
                            return "ʹ�õ�λ����";
                        case 2100:
                            return "���뵥λ����";
                        case 2200:
                            return "���ݹ���";
                        case 2300:
                            return "���̹���";
                        case 2400:
                            return "�������";
                        case 2500:
                            return "�����ֵ�";
                        case 2600:
                            return "�ֿ�ϵͳ";

                    }
                }
            }
            , {
                field: 'logAction', align: 'center', title: '��������', width: 100, templet: function (d) {
                    switch (d.logAction) {
                        case 100:
                            return "����";
                        case 200:
                            return "�༭";
                        case 300:
                            return "ɾ��";
                        case 400:
                            return "�鿴";
                        case 500:
                            return "����";
                        case 600:
                            return "����";
                        case 700:
                            return "ά��";
                        case 800:
                            return "�Ƶ�";
                        case 900:
                            return "δ����";
                        case 1000:
                            return "�������";
                        case 1100:
                            return "ͳ��";
                        case 1200:
                            return "�����ɫ";
                        case 1300:
                            return "����Ȩ��";
                        case 1400:
                            return "���͵�ERP";
                        case 1500:
                            return "����ERP����";
                        case 1600:
                            return "�û���¼";
                        case 1700:
                            return "�����û�";
                        case 1800:
                            return "�Ƴ��û�";
                        case 1900:
                            return "�ϴ�����";
                    }
                }
            }
            , {field: 'logDesc', align: 'center', title: '����', width: 300}
            , {field: 'logIp', align: 'center', title: 'IP��ַ', width: 120}
            , {field: 'createName', align: 'center', title: '������', width: 100}
            , {
                field: 'createDate', align: 'center', title: '����ʱ��', templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }

        ]]
        , page: true   //������ҳ
        , limit: 30   //Ĭ�϶�ʮ������һҳ
        , limits: [10, 20, 30, 40]  //���ݷ�ҳ��
        , id: 'logListReload'
    });
    var reload = function (data) {

        table.reload('logListReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                logType: $("#logType").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val()
            }

        })
    };


    laydate.render({
        elem: '#startTime'
        , type: 'datetime'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'datetime'
    });


    $("#delete").on("click", function (e) {
//            layer.alert(returnCitySN.cip);
        var checkStatus = table.checkStatus('logListReload');
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
                var url = "deleteLog.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('ɾ���ɹ�', {
                            time: 2000,
                            end: function () {
                                location.href = 'manageLog.htm'
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
