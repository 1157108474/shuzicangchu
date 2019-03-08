layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;
    var ran = Math.random();
    //��ϸ�б�
    var detailsgr = table.render({
        elem: '#detailsgrid'
        , url: '/sheet/apply/listSheetApply.json'
        , cellMinWidth: 80
        , height: "full-70"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailsgridTable"
        , where: {//��ʼ����
            ran: ran++,
            sheetId: $("#id").val()
        }
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'action', title: '�༭', align: "center", width: 100, toolbar: '#bar'}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 150}
                , {field: 'detailCount', title: '��������', align: "center", width: 100}
                , {field: 'ckCount', title: '�ѳ�������', align: "center", width: 100}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 80}
                , {field: 'extendString2', title: 'ʹ�õ�ַ', align: "center", width: 160}
                , {field: 'snCode', title: '�ƻ����', align: "center", width: 150}
                , {field: 'description', title: '��������', align: "center", width: 320}
            ]
        ]
    });

    function applyReload() {
        table.reload('detailsgridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val()
            }
        })
    }

    //ͷ�����水ť
    $("#saveSheet").on("click", function (e) {
        var id = $("#id").val();
        if (null == id || id == "") {
            saveSheet(0);
        } else {
            updateSheet(0, id);
        }
    });


    window.reloadHis = function () {
        //���Ա��ⲿ����
        table.reload('activitiHisTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                taskId: $("#taskId").val()
            }
        });
    }
    $("#print").on("click", function (e) {
    	var taskId = $("#taskId").val();
        vipTable.openPage("��ӡ�������ϵ�", "/system/print/sheet/WZLLD-" + $("#id").val() + "?taskId=" +taskId  , '95%', '95%');
    });
    //���桢�޸ĵ��ݷ���
    //type 0:������ϸҳ�棻1:�򿪼ƻ���ϸҳ�棻2:���޼ƻ���ϸҳ��
    function saveSheet(type) {
        if (checkSheet()) {
            var params = $('#bill').serialize();
            var json = {
                "menuCode": $("#menuCode").val()
                // ,"officesId": $("#officesId").val()
                , "applyDepartId": $("#applyDepartId select").val()
                , "usedDepartId": $("#usedDepartId").val()
                , "extendString2": $("#extendString2").val()
                , "ztId": $("#ztId").val()
                , "fundsSource": $("#fundsSource select").val()
                , "extendString1": $("#extendString1").val()
                , "memo": $("#memo").val()
            };
            $.ajax({
                type: "POST",
                url: "/sheet/WZLLD",
                dataType: "json",
                data: json,
                success: function (ret) {
                    if (ret.status == 1) {
                        top.layer.msg("���ݱ���ɹ���", {time: 500});
                        //������ID��Code��ֵ��ҳ����
                        $("#id").val(ret.data.id);
                        $("#code").html(ret.data.code);
                        $("#taskId").val(ret.data.taskId);
                        $("#reloadDetailsgrid").val(0);
                        reloadHis();
                        hideZTChange();
                        if ('1' == type) {
                            openPlanDetail();//�򿪼ƻ���ϸ
                        } else if ('2' == type) {
                            openNoPlanDetail();//���޼ƻ���ϸ
                        }
                    } else {
                        layer.alert("����ʧ�ܣ�ʧ��ԭ��" + ret.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            })
        }
    }

    if (null != $("#id").val() && '' != $("#id").val()) {
        hideZTChange();
    }

    function updateSheet(type, id) {
        if (checkSheet()) {
            var params = $('#bill').serialize();
            var json = {
                "id": id
                // , "officesId": $("#officesId").val()
                , "menuCode": $("#menuCode").val()
                , "applyDepartId": $("#applyDepartId select").val()
                , "usedDepartId": $("#usedDepartId").val()
                , "extendString2": $("#extendString2").val()
                , "ztId": $("#ztId").val()
                , "fundsSource": $("#fundsSource select").val()
                , "ExtendString1": $("#ExtendString1").val()
                , "memo": $("#memo").val()
            };
            $.ajax({
                type: "put",
                url: "/sheet/WZLLD/" + id,
                dataType: "json",
                data: json,
                success: function (ret) {
                    if (ret.status == 1) {
                        top.layer.msg("�����޸ĳɹ���", {time: 500});
                        $("#reloadDetailsgrid").val(0);
                        reloadHis();
                        if ('1' == type) {
                            openPlanDetail();//�򿪼ƻ���ϸ
                        } else if ('2' == type) {
                            openNoPlanDetail();//���޼ƻ���ϸ
                        }
                    } else {
                        layer.alert("�����޸�ʧ�ܣ�ʧ��ԭ��" + ret.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            })
        }
    }

    //�����֯�����������
    function hideZTChange() {
        //��������
        $("#extendString2").addClass("layui-disabled");
        $("#openZt").addClass("layui-disabled");
        $("#extendString2").attr("disabled", true);
        $("#openZt").attr("disabled", true);
    }

    //�����ϼ���������ҳ��
    $("#openOffice").on("click", function (e) {
        var url = "/system/useDep/generalUseDepDepart.htm?ztId=" + $("#ztId").val();
        vipTable.openPage("����", url, "75%", "85%");
    });

    //��������֯ҳ��
    $("#openZt").on("click", function (e) {
        var url = "/system/dept/openPublicDepart.htm";
        vipTable.openPage("�����֯ҳ��", url, "480px", "320px");
    });
    //����ƻ�������ϸҳ��
    $("#addPlanDetail").on("click", function (e) {
        var id = $("#id").val();
        if (null == id || id == "") {
            saveSheet(1);
        } else {
            updateSheet(1, id);
        }

    });
    //�����޼ƻ�������ϸҳ��
    $("#addNoPlanDetail").on("click", function (e) {
        var id = $("#id").val();
        if (null == id || id == "") {
            saveSheet(2);
        } else {
            updateSheet(2, id);
        }
    });

    //�򿪼ƻ���ϸҳ��
    function openPlanDetail() {
        layer.open({
            title: "�ƻ�������ϸ"
            , type: 2
            , fixed: false
            , area: ["90%", "90%"]
            , content: "/sheet/apply/openPlanDetailWindow.htm?ztId=" + $("#ztId").val() +
            "&sheetId=" + $("#id").val()+"&usedDepartId="+$("#usedDepartId").val()
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    applyReload();//ˢ���б�
                }
            }
        });
        // var url = "/sheet/apply/openPlanDetailWindow.htm?ztId=" + $("#ztId").val() + "&sheetId=" + $("#id").val();
        // vipTable.openPage("�ƻ�������ϸ", url, "90%", "80%");
    }

    //���޼ƻ���ϸҳ��
    function openNoPlanDetail() {
        layer.open({
            title: "�޼ƻ�������ϸ"
            , type: 2
            , fixed: false
            , area: ["90%", "90%"]
            , content: "/sheet/apply/openNoPlanDetailWindow.htm?ztId=" + $("#ztId").val()
            + "&sheetId=" + $("#id").val()+"&usedDepartId="+$("#usedDepartId").val()
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    applyReload();//ˢ���б�
                }
            }
        });
        // var url = "/sheet/apply/openNoPlanDetailWindow.htm?ztId=" + $("#ztId").val() + "&sheetId=" + $("#id").val();
        // vipTable.openPage("�޼ƻ�������ϸ", url, "90%", "80%");
    }

    //ɾ����ϸ
    $("#deleteDetails").click(function () {

        var checkStatus = table.checkStatus("detailsgridTable");
        var data = checkStatus.data;
        var ids = [];
        var url = "/sheet/detail/delSheetDetails.json?ids=";

        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('ȷ��ɾ��ѡ�е���ϸ��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post(url + ids, function (data) {
                    applyReload();
                    layer.msg(data.message);
                    layer.close(index);
                })
            })
        } else {
            layer.msg('��ѡ��Ҫɾ�������ݡ�', {offset: 't', anim: 6});
        }
    });
    //����������
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'editDetails') {
            $("#reloadDetailsgrid").val(0);
            layer.open({
                title: "�༭��ϸ"
                , type: 2
                , fixed: false
                , area: ["90%", "80%"]
                , content: '/sheet/apply/openEditApply.htm?id=' + data.id + "&sheetId=" + data.sheetId
                , end: function () {
                    if ($("#reloadDetailsgrid").val() == 1) {
                        applyReload();//ˢ���б�
                    }
                }
            });
            // var url = '/sheet/apply/openEditApply.htm?id=' + data.id+"&sheetId="+data.sheetId;
            // vipTable.openPage("�༭��ϸ", url, "90%", "80%");
        }
    });

    //����Ч��
    function checkSheet() {
        var ret = true;
        // if ($("#officesId").val() == "") {
        //     top.layer.msg("��ѡ����ң�", {time: 500});
        //     return false;
        // }
        if ($("#applyDepartId select").val() == "") {
            top.layer.msg("��ѡ�����뵥λ��", {time: 500});
            return false;
        }
        if ($("#usedDepartId").val() == "") {
            top.layer.msg("��ѡ��ʹ�õ�λ��", {time: 500});
            return false;
        }
        if ($("#fundsSource select").val() == "") {
            top.layer.msg("��ѡ���ʽ���Դ��", {time: 500});
            return false;
        }
        //�ж�ʹ�õ�λ�Ƿ�Ϊ��
        /*var rows = detailsgrid.getSelecteds();
        for (var i = 0; i < rows.count; i++) {
            if (rows[i].EXTENDSTRING2 == "") {
                top.layer.msg("����ϸδ��дʹ�õ�ַ��", {
                    time: 500
                });
                return false;
            }
        }*/
        return ret;
    }

});

//�ϼ�����ҳ�淵�ش�����
function UseDepDepartGeneral(data) {
    // document.getElementById("officesName").value = data[0].name;
    // document.getElementById("officesId").value = data[0].id;
}

//�����֯ҳ�淵�ش�����
function obtainPart(id, name, ztId, code) {
    document.getElementById("extendString2").value = name;
    document.getElementById("ztId").value = id;
}

