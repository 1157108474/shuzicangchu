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
        elem: '#taskLogList'  //��table id
        , url: 'listTaskLog.json'  //��������·��
        , height: 'full-75'
        , method: 'post'
        , cols: [[
            {checkbox: true, width: 30}
            , {field: 'infTaskdetailn', align: 'center', title: '�ӿ�����', width: 240}
            , {field: 'infTaskdetailn', align: 'center', title: '��������', width: 120}
            , {field: 'syncResult', align: 'center', title: 'ͬ�����', width: 120}
            , {
                field: 'infMode', align: 'center', title: '�ӿڴ�����ʽ', width: 140
            }
            , {
                field: 'infType', align: 'center', title: '�ӿ�����', width: 110
            }
            , {field: 'infPush', align: 'center', title: '�ӿ��ṩ��', width: 140}
            , {field: 'infPull', align: 'center', title: '�ӿڵ��÷�', width: 140}
            , {field: 'infMethod', align: 'center', title: 'ͬ����ʽ', width: 160}
            , {field: 'infContent', align: 'center', title: '�ӿ�����', width: 180}
            , {field: 'infErrorContent', align: 'center', title: '��������', width: 180}
            , {
                field: 'createDate', align: 'center', title: 'ͬ��ʱ��', width: 160, templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }
        ]]
        , page: true   //������ҳ
        , limit: 30   //Ĭ�϶�ʮ������һҳ
        , limits: [10, 20, 30, 40]  //���ݷ�ҳ��
        , id: 'taskLogListReload'
    });
    var reload = function (data) {

        table.reload('taskLogListReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                infTaskdetailn: $("#infTaskdetailn").val(),
                syncResult: $("#syncResult").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val()
            }

        })
    };

    table.on('tool(taskLogList)', function (event) {
        var taskLogId = event.data.id;
        layer.alert("WebService�ӿ�δ��ʼ��ƣ�")
        //TODO:WebService�ӿ����´���
    });

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
        var checkStatus = table.checkStatus('taskLogListReload');
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
                var url = "deleteTaskLog.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('ɾ���ɹ�', {
                            time: 2000,
                            end: function () {
                                location.href = 'manageTaskLog.htm'
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