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
        , url: 'detailsJCWZRKList.json'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailsgridTable"
        , where: {sheetId: $("#id").val()}
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {title: '����', align: "center",fixed:'left', toolbar: '#bar', width: 100}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'materialName', title: '��������', align: "center", width: 120}
                , {field: 'materialModel', title: '�����ͺ�', align: "center", width: 120}
                , {field: 'typeName', title: '��������', align: "center", width: 120}
                , {field: 'materialSpecification', title: '���Ϲ��', align: "center", width: 120}
                , {field: 'materialBrand', title: '����Ʒ��', align: "center", width: 120}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 120}
                , {field: 'houseName', title: '�ⷿ', align: "center", width: 120}
                , {field: 'storeLocationName', title: '��λ', align: "center", width: 120}
                , {field: 'detailcount', title: '����', align: "center", width: 100}
                , {field: 'notaxpriceDouble', title: '����˰����', align: "center", width: 100}
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


    //������ϸҳ��
    $("#addDetails").on("click", function () {
        var check = $("#ownerdepName").val();
        var sheetId = $("#id").val();
        if (check == "") {
            layer.alert("��ѡ��Ĵ浥λ��");
        } else {
            if (sheetId == "") {
                // ���жϵ�ǰ�����Ƿ��ѱ���,���δ����,��ִ�б���
                saveSheet();
            } else {
                // ����ϸҳ��
                open();
            }
        }
    });
    //������ϸҳ��
    var open = function () {
        layer.open({
            title: "�����ϸ"
            , type: 2
            , fixed: false
            , area: ["90%", "90%"]
            , content: "addDetails.htm"
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
            layer.msg("��ѡ��һ����¼����ɾ��");
        } else {
            layer.confirm('��ȷ��Ҫɾ��ѡ�е���ϸ��', {
                icon: 3, title: '��ʾ��Ϣ', offset: '35%',
                btn: ['ȷ��', 'ȡ��'] //��ť
            }, function () {
                for (var i = 0; i < datas.length; i++) {
                    arr.push(datas[i].id);
                }
                var url = "/sheet/detail/delSheetDetails.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('ɾ���ɹ�', {
                            time: 1000,
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
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            layer.open({
                    title: "�޸���ϸ"
                    , type: 2
                    , fixed: false
                    , area: ["90%", "90%"]
                    , content: "editDetail.htm?id="+data.id
                , end: function () {
                    if ($("#reloadStatus").val() == 1) {
                        reload();
                    }
                }
        })
        }
    })
    //���水ť
    $("#saveSheet").on("click", function (e) {
        var ownerdepName = $("#ownerdepName").val();
        if(ownerdepName==""){
            layer.msg('��ѡ��Ĵ浥λ�Լ���ϸ�б�', {
                anim: 6
            });
        }else {
            var result;
            //�ж��Ƿ���ڵ��ݱ��,���û��,ִ�б���,�����,ִ���޸�
            var check2 = $("#id").val();
            //�������ID������,add
            if (check2 == "") {
                result = saveSheet();
            } else {
                result = updateSheet();
            }
            if (result) {
                layer.alert("����ɹ�");
            }
        }

    });

    //���浥�ݷ���
    var saveSheet = function () {
        var loading = layer.msg("������", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });

        $.ajax({
            type: "POST",
            url: "/sheet/wzjcrkd",
            dataType: "json",
            data: {"menuCode":$("#menuCode").val(),"ownerDep":$("#ownerdepId").val(),"memo":$("#memo").val()},
            success: function (ret) {
                if (ret.status == 1) {
                    //������ID��Code��ֵ��ҳ����
                    $("#id").val(ret.data.id);
                    $("#sheetcode").val(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    reloadHis();
                    layer.close(loading);
                    open();
                } else {
                    layer.alert("����ʧ�ܣ�ʧ��ԭ��" + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };

    //�޸ĵ��ݷ���
    var updateSheet = function () {
        var loading = layer.msg("������", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });
        $.ajax({
            type: "POST",
            url: "../jcwzrk/editSheet",
            dataType: "json",
            data: {"id": $("#id").val(), "ownerDep": $("#ownerdepId").val(), "memo": $("#memo").val()},
            success: function (ret) {
                if (ret.status == 1) {
                    layer.msg('����ɹ�', {
                        anim: 6
                    });
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


});

//��ȡ����ҳ��ش�����
function obtainPart(id, name, ztId, code) {
    document.getElementById("ownerdepName").value = name;
    document.getElementById("ownerdepId").value = id;
}


