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
        , url: 'listRkDetails.json'
        , cellMinWidth: 90
        , height: "full-160"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , where: {ran: ran++, sheetId: $("#id").val()}
        , id: "detailsgridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                /*, {title: '����', align: "center", fixed: "left", toolbar: '#bar', width: 120}*/
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 140}
                , {field: 'description', title: '��������', align: "center", width: 140}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 100}
                , {field: 'detailCount', title: '�������', align: "center", width: 110}
                , {field: 'subTotalCount', title: '��������', align: "center", width: 110}
                , {field: 'jsType', title: '�Ƿ����', align: "center", width: 110}
                , {field: 'enableSn', title: '�Ƿ��������к�', align: "center", width: 160,templet:function(d){
                    if(d.enableSn == 1){
                        return "����";
                    }else{
                        return "������"
                    }
            }}
                , {field: 'extendString1', title: '�ⷿ����', align: "center", width: 120}
                , {field: 'warehouseName', title: '�ⷿ', align: "center", width: 120}
                /*, {field: 'notaxPrice', title: '����˰����', align: "center", width: 120}
                , {field: 'notaxSum', title: '����˰���', align: "center", width: 120}
                , {field: 'taxRate', title: '˰��', align: "center", width: 120}
                , {field: 'extendFloat1', title: '�۸�ϼ�', align: "center", width: 120}*/

            ]

        ]
    });

    var reload = function () {
        table.reload('detailsgridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val()
            }

        })
    };

    // ����select�¼� ����������͵��ò�ͬҳ��
    form.on('select(isopen)', function (data) {
        var selectValue = data.value;
        var check = $("#id").val();
        if (check != '' && check != null) {
            layer.alert("�ѱ��浥�ݣ��޷����ģ�");
            return;
        } else {
            if (selectValue == 789) {
                $("#receiveNum").val("");//���յ���
                $("#orderNum").val("");//�ɹ��������
                $("#extendString3").val("");//��������
                $("#extendString2").val("");//�����֯����
                $("#ztId").val("");//�����֯ID
                $("#extendString1").val("");//��Ӧ������
                $("#providerDepId").val("");//��Ӧ��ID
                $("#extendString5").val("");//���ź�
                $("#extendInt1").val("");//���յ�ID
                $("#extendInt2").val("");//������ID
                $("#dbName").val("");//����������
                vipTable.openPage("������ⵥ", "generalJSD.htm", "90%", "80%");
            }
            if (selectValue == 790) {
                $("#receiveNum").val("");//���յ���
                $("#orderNum").val("");//�ɹ��������
                $("#extendString3").val("");//��������
                $("#extendString2").val("");//�����֯����
                $("#ztId").val("");//�����֯ID
                $("#extendString1").val("");//��Ӧ������
                $("#providerDepId").val("");//��Ӧ��ID
                $("#extendString5").val("");//���ź�
                $("#extendInt1").val("");//���յ�ID
                $("#extendInt2").val("");//������ID
                $("#dbName").val("");//����������
                var ztId = $("#userZtid").val() == "" ? "0" : $("#userZtid").val();
                vipTable.openPage("���ʵ�����", "/sheet/db/dbd?type=rk&ztId=" + ztId, "60%", "80%");
            }
        }
    });

    //���水ť
    $("#saveSheet").on("click", function (e) {
        var result;
        var typeId = $("#check").val();//�������
        var receiveNum = $("#receiveNum").val();//���յ���
        var dbNum = $("#extendInt2").val();//��������
        var rkId = $("#id").val();//��ⵥ��ID
        if (rkId == "" || rkId == null) {
            if (typeId == "") {
                layer.alert("����ѡ��������ͣ�");
            } else {
                if (typeId == 789 && receiveNum == "") {
                    layer.alert("��ѡ����յ��ţ�");
                } else if (typeId == "790" && dbNum == "") {
                    layer.alert("��ѡ��������ţ�");
                } else {
                    result = saveSheet();
                }
            }
        } else {
            result = updateSheet();
        }
    });


    //���淽��
    var saveSheet = function () {

        //����Loading��
        var loading = layer.msg("������", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });

        var typeId = $("#check").val();
        if (typeId == "790") {
            //�������ʱ��ZTID��Ϊ��Ա�Ŀ����֯����Ӧ��ID����Ϊ0
            $("#ztId").val($("#userZtid").val());
            $("#providerdepid").val(0);
        }
        //��ȡ����table��������Ϣ,ƴװJSON
        var rkTable = document.getElementById("rkTable");
        var rkTableValue = rkTable.getElementsByTagName("input");
        var param = "{";
        for (var i = 0; i < rkTableValue.length; i++) {
            param += "\"" + rkTableValue[i].name + "\":\"" + rkTableValue[i].value + "\",";
        }
        param += "\"typeId\":" + "\"" + typeId + "\"";
        param += "}";
        var rkSheetJson = JSON.parse(JSON.stringify(param));

        //��������
        $.ajax({
            type: "POST",
            url: "/sheet/rk/RK",
            dataType: "json",
            data: JSON.parse(rkSheetJson),
            success: function (ret) {
                if (ret.status == 1) {
                    $("#check").addClass(" layui-disabled");
                    $("#check").attr("disabled", true);
                    form.render('select');
                    //������ID��Code��ֵ��ҳ����
                    $("#id").val(ret.data.id);
                    $("#sheetcode").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    $("#sheetStatus").val(ret.data.status);
                    reloadHis();
                    layer.msg("���浥�ݳɹ���");
                    layer.close(loading);
                } else {
                    layer.alert("����ʧ�ܣ�ʧ��ԭ��" + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });

    };

    //�޸ķ���
    var updateSheet = function () {

        //����Loading��
        var loading = layer.msg("������", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });

        var typeId = $("#check").val();

        //��ȡ����table��������Ϣ,ƴװJSON
        var rkTable = document.getElementById("rkTable");
        var rkTableValue = rkTable.getElementsByTagName("input");
        var param = "{";
        for (var i = 0; i < rkTableValue.length; i++) {
            param += "\"" + rkTableValue[i].name + "\":\"" + rkTableValue[i].value + "\",";
        }
        param += "\"typeId\":" + "\"" + typeId + "\"";
        param += "}";
        var rkSheetJson = JSON.parse(JSON.stringify(param));

        //��������
        $.ajax({
            type: "POST",
            url: "/sheet/rk/editSheet.json",
            dataType: "json",
            data: JSON.parse(rkSheetJson),
            success: function (ret) {
                if (ret.status == 1) {
                    layer.close(loading);
                    return true;
                } else {
                    layer.alert("����ʧ�ܣ�ʧ��ԭ��" + ret.message);
                    return false;
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });

    };


    //�����ϸ��ť
    $("#addDetails").on("click", function (e) {
        var check = $("#check").val();
        var check1 = $("#receiveNum").val();
        var check2 = $("#dbName").val();
        var sheetId = $("#id").val();
        if (check == "" || check == null) {
            //�ж��Ƿ�ѡ���������
            layer.alert("��ѡ��������ͣ�");
        } else if (check1 == "" && check2 == "") {
            //�ж��Ƿ�ѡ�е���
            layer.alert("��ѡ�񵥾ݱ�ţ�");
        } else if (check == "790" && check2 != "") {
            //�ж��Ƿ�ѡ�е�������,�����ѡ��,�ж��Ƿ��ѱ���
            if (sheetId == "" || sheetId == null) {
                //���δ����,ִ�б���
                layer.alert("���ȱ��浥�ݣ�");
            } else {
                //����ѱ���,ֱ�Ӽ��ص����б�
                DBopen();
            }
        } else if (check == "789" && check1 != "") {
            //�ж��Ƿ�ѡ�н��յ���,�����ѡ��,�ж��Ƿ��ѱ���
            if (sheetId == "" || sheetId == null) {
                //���δ����,ִ�б���
                saveSheet();
                JSopen();
            } else {
                JSopen();
            }
        }
    });

    //��ȡ�ɹ������б�
    var order = true;
    $("#order").on("focus", function (e) {
        if (order) {
            order = false;
            var url = "/sheet/wzjs/generalJSD.htm";
            vipTable.openPage("�ɹ������б�", url, '680px', '400px');
        }
    }).on("blur", function (e) {
        order = true
    });

    //��ϸ�༭����
    table.on('tool(detailsgrid)', function (obj) {

        var data = obj.data;
        if (obj.event == 'edit') {
            layer.open({
                title: "�༭��ϸ"
                , type: 2
                , fixed: false
                , area: ["90%", "90%"]
                , content: "getOriginalLocation.htm?rid=" + data.id+"&extendInt1="+$("#extendInt1").val()
                , end: function () {
                    reload();
                }
            });
        }

    });

    //������ϸҳ��
    var JSopen = function () {
        $("#reloadStatus").val(0);
        var num = $("#receiveNum").val();
        layer.open({
            title: "�����ϸ"
            , type: 2
            , fixed: false
            , area: ["90%", "80%"]
            , content: "addDetails.htm?jsCode=" + num
            , end: function () {
                if ($("#reloadStatus").val() == 1) {
                    reload();
                }
            }
        });
    };

    var DBopen = function () {

        $.ajax({
            type: "POST",
            url: "../rkDetail/addRKDetails?rid=" + $("#id").val() + "&sid=" + $("#extendInt2").val() + "&appFlag=1",
            dataType: "json",
            success: function (ret) {
                if (ret.status == '1') {
                    layer.msg('��ӳɹ�', function () {
                        layer.close(loading);
                        reload();
                    });
                } else {
                    layer.alert('���ʧ�ܣ�' + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });

        //����Loading��
        var loading = layer.msg("������", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });

        $.ajax({
            type: "POST",
            url: "../rkDetail/addRKDetails?rid=" + $("#id").val() + "&sid=" + $("#extendInt2").val() + "&appFlag=1",
            dataType: "json",
            success: function (ret) {
                if (ret.status == '1') {
                    layer.msg('��ӳɹ�', function () {
                        layer.close(loading);
                        reload();
                    });
                } else {
                    layer.alert('���ʧ�ܣ�' + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };

    // ɾ����ϸ��ť
    $("#deleteDetails").on("click", function (e) {
        var checkStatus = table.checkStatus('detailsgridTable');
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
                var url = "deleteSheetRkDetails.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('ɾ���ɹ�', {
                            time: 2000,
                            end: function () {
                                reload();
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

    $("#print").on("click", function (e) {
    	var taskId = $("#taskId").val();
        var printType = $("#printType").val();
        var typeId = $("#check").val();
        if (typeId == "790") {
            vipTable.openPage("��ӡ��ⵥ", "/system/print/sheet/DBRK-" + $("#id").val() + "?printType=203&taskId="+taskId, '850px', '500px');
        } else {
            if (printType == '') {
                layer.alert("��ѡ���ӡ����");
            } else {
                vipTable.openPage("��ӡ��ⵥ", "/system/print/sheet/JSRK-" + $("#id").val() + "?printType=165&taskId="+taskId, '850px', '500px');
            }
        }
    });

});

function JSDGeneral(data) {
    document.getElementById("receiveNum").value = data[0].code;//���յ���
    document.getElementById("orderNum").value = data[0].ordernum;//�ɹ��������
    document.getElementById("extendString3").value = data[0].extendstring3;//�ɹ���������
    document.getElementById("extendString2").value = data[0].extendstring2;//�����֯����
    document.getElementById("ztId").value = data[0].ztid;//�����֯ID
    document.getElementById("extendString1").value = data[0].extendstring1;//��Ӧ������
    document.getElementById("providerDepId").value = data[0].providerdepid;//��Ӧ��ID
    document.getElementById("extendString5").value = data[0].extendstring5;//���ź�
    document.getElementById("extendInt1").value = data[0].id;//���յ�ID��sheetId��
}

function DBDGeneral(data) {
    document.getElementById("extendInt2").value = data.id;//������ID
    document.getElementById("dbName").value = data.code;//��������
    document.getElementById("extendString2").value = data.intoOrgName;//�����֯����
    document.getElementById("ztId").value = data.extendInt2;//�����֯ID
    document.getElementById("extendString1").value = "";//��Ӧ������
    document.getElementById("providerDepId").value = "0";//��Ӧ��ID
}

