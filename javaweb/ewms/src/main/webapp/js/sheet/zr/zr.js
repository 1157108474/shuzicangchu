layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'upload','vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var upload = layui.upload;
    var element = layui.element;
    var ran = Math.random();

    //��ϸ�б�
    var detailsgr = table.render({
        elem: '#detailsgrid'
        , url: 'listZrDetails.json'
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
                , {title: '����', align: "center", fixed: "left", toolbar: '#bar', width: 100}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 140}
                , {field: 'description', title: '��������', align: "center", width: 120}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 80}
                , {field: 'detailCount', title: '�������', align: "center", width: 90}
                , {field: 'subTotalCount', title: '��������', align: "center", width: 90}
                , {field: 'jsType', title: '�Ƿ����', align: "center", width: 90}
                , {field: 'enableSn', title: '�Ƿ��������к�', align: "center", width: 140}
                /*, {field: 'extendString1', title: '�ⷿ', align: "center", width: 100}*/
                , {field: 'name', title: '�ⷿ', align: "center", width: 100}
                , {field: 'locationName', title: '��λ', align: "center", width: 100}
                , {field: 'notaxPriceDouble', title: '����˰����', align: "center", width: 100}
                , {field: 'notaxSumDouble', title: '����˰���', align: "center", width: 100}
                , {field: 'taxRate', title: '˰��', align: "center", width: 100}
                , {field: 'extendFloat1', title: '��˰�ϼ�', align: "center", width: 100}
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

    //��ȡ�Ĵ浥λ�б�
    $("#ownerdepBtn").on("click", function () {
        var url = "/system/user/publicDepart.htm";
        layui.layer.open({
            title: "��֯����"
            , type: 2
            , fixed: false
            , area: ['50%', '85%']
            , content: url
        });
        return false;
    });


    $("#provider").on("click", function (e) {
        vipTable.openPage("��Ӧ���б�", "/system/provider/generalProvider.htm", '60%', '90%');
        return false;

    });

    //���水ť
    $("#saveSheet").on("click", function (e) {
        var rkId = $("#id").val();//��ⵥ��ID
        var ztId = $("#ztId").val();
        var providerId = $("#providerId").val();
        if (rkId == "" || rkId == null) {
            /*if (providerId == "") {
                layer.alert("��Ӧ��δѡ����ѡ����ٱ��棡")
            } else */
            if (ztId == "") {
                layer.alert("�����֯δѡ����ѡ����ٱ��棡")
            } else {
                saveSheet();
            }
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

        //��ȡ����table��������Ϣ,ƴװJSON
        var rkTable = document.getElementById("rkTable");
        var rkTableValue = rkTable.getElementsByTagName("input");
        var param = "{";
        for (var i = 0; i < rkTableValue.length; i++) {
            param += "\"" + rkTableValue[i].name + "\":\"" + rkTableValue[i].value + "\",";
        }
        param = param.substring(0, param.length - 1);
        param += "}";
        var rkSheetJson = JSON.parse(JSON.stringify(param));
        //��������
        $.ajax({
            type: "POST",
            url: "/sheet/rk/ZR",
            dataType: "json",
            data: JSON.parse(rkSheetJson),
            success: function (ret) {
                if (ret.status == 1) {
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

    //�����ϸ��ť
    $("#addDetails").on("click", function (e) {
        var rkId = $("#id").val();

        if (rkId == "" || rkId == null) {
            layer.alert("���ȱ��浥�ݣ�");
        } else {
            JSopen();
        }

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
                , content: "getOriginalLocation.htm?rid=" + data.id
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
            , content: "addDetails.htm?providerId="+$("#providerId").val()
            , end: function () {
                if ($("#reloadStatus").val() == 1) {
                    reload();
                }
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
                var url = "deleteSheetZRDetails.json?ids=" + arr;
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

    //�ļ��ϴ�
    upload.render({
        elem: '#importKCResult'
        , url: '/sheet/zr/importKCResult.json'
        , exts: 'xls|xlsx' //Excel
        , done: function (res) {
            alert(res.msg)
            //layer.msg(res.msg);
            //reload();
        }
    });
});

//��ȡ����ҳ��ش�����
function obtainPart(id, name, ztId, code) {
    document.getElementById("ownerdepName").value = name;
    document.getElementById("ownerdepId").value = id;
}



