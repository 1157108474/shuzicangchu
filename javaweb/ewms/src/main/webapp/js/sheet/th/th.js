layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['form', 'table', 'layer', 'vip_table'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;
    var ran = Math.random();
    var form = layui.form;
    //  document.getElementById('date').innerHTML= new Date().toLocaleString( );
    //��ϸ�б�
    // var detailsgrid = table.render({
    //     elem: '#detailsgrid'
    //     , url: 'details'
    //     , cellMinWidth: 80
    //     , height: "full-240"
    //     , method: 'POST'
    //     , page: true   //������ҳ
    //     , limit: 20   //Ĭ��ʮ��������һҳ
    //     , limits: [10, 20 ,30]  //���ݷ�ҳ��
    //     ,id: 'detailsgridReload'
    //     ,where:{ran:ran++,sheetId:$("#id").val()}
    //
    //     , cols: [
    //         [     {type: "checkbox", fixed: "left", width: 30}
    //             , {field: 'materialCode', title: '���ϱ���', align: "center", width: 130}
    //             , {field: 'description', title: '��������', align: "center", width: 420}
    //             , {field: 'defaultUnitName', title: '��λ', align: "center", width: 60}
    //             , {field: 'wareHouseCode', title: '�ⷿ', align: "center", width: 110}
    //             , {field: 'detailCount', title: '�˻�����', align: "center",width: 80}
    //             , {field: 'noTaxPrice', title: '����˰����', align: "center", width: 100}
    //             , {field: 'noTaxSum', title: '����˰���', align: "center", width: 100}
    //             , {fixed: 'right', title: '����',width:80, align:'center', toolbar: '#barDemo'}
    //
    //         ]
    //     ]
    //     // , trclick: function (data,tr) {
    //     //     subreload(data.materialCode,data.storeId);
    //     //     tr.after($("#showSub").html());
    //     //
    //     // }
    //
    // });

    var subdetailsgrid = table.render({
        // elem: '#subdetailsgrid'
        elem: '#detailsgrid'
        , url: 'subdetails'
        , cellMinWidth: 80
        , method: 'POST'
        , height: "full-260"
        , page: true   //������ҳ
        , limit: 20   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: 'subdetailsgridReload'

        // ,where:{ran:ran++}
        , where: {ran: ran++, sheetId: $("#id").val()}
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'houseName', title: '�ⷿ', align: "center", width: 160}
                , {field: 'storeLocationCode', title: '��λ', align: "center", width: 210}
                , {field: 'detailCount', title: '����', align: "center", width: 150}
                , {field: 'snCode', title: '���к�', align: "center", width: 180}
                , {field: 'noTaxSumDouble', title: '����˰���', align: "center", width: 180}
                , {fixed: 'right', title: '�鿴��ʷ��¼', width: 180, align: 'center', toolbar: '#subbarDemo'}

            ]
        ]

    });
    var hisdetailsgrid = table.render({
        elem: '#hisdetailsgrid'
        , url: 'hisdetails'
        , cellMinWidth: 80
        , method: 'POST'
        , page: true   //������ҳ
        , limit: 20   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: 'hisdetailsgridReload'
        , where: {ran: ran++}
        , cols: [
            [
                {field: 'houseName', title: '�ⷿ', align: "center", width: 100}
                , {field: 'storeLocationCode', title: '��λ', align: "center", width: 100}
                , {field: 'detailCount', title: '����', align: "center", width: 100}
                , {field: 'snCode', title: '���к�', align: "center", width: 120}
                , {field: 'noTaxSumDouble', title: '����˰���', align: "center", width: 120}
            ]
        ]

    });

    var reload = function () {
        // table.reload('detailsgridReload', {
        table.reload('subdetailsgridReload', {

            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val()
            }

        })
    };

    // var subreload =function(materialCode,storeId){
    //     table.reload('subdetailsgridReload', {
    //         page: {
    //             curr: 1 //���´ӵ� 1 ҳ��ʼ
    //         },
    //         where:{
    //             ran:ran++,
    //             sheetId: $("#id").val(),
    //             materialCode:materialCode,
    //             storeId:storeId
    //         }
    //
    //     })
    // };

    var hisreload = function (materialCode, storeId) {
        table.reload('hisdetailsgridReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val(),
                materialCode: materialCode,
                storeId: storeId
            }

        })
    };

//���水ť
    $("#saveSheet").on("click", function (e) {
        var result;
        var id = $("#id").val();

        if (id == "") {
            result = saveSheet(false);
        } else {
            result = updateSheet(id);
        }
        if (result) {
            layer.alert("����ɹ�");
        }
    });

    var open = function () {   //������ϸҳ��
        $("#reloadDetailsgrid").val(0);
        layer.open({
            title: "�����ϸ"
            , type: 2
            , fixed: false
            , area: ["95%", "95%"]
            , content: "/sheet/th/openAddDetail"
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    reload();
                }
            }
        })
    };


    var saveSheet = function (openFlag) {
        if ($("#extendInt1").val() == "") {
            layer.alert("����ѡ����ⵥ��");
            return;
        }

        $.ajax({
            type: "post",
            url: "/sheet/WZTH",
            dataType: "json",
            data: {
                "menuCode": $("#menuCode").val(),
                "extendString5": $("#rkCode").val(),
                "extendInt1": $("#extendInt1").val(),
                "orderNum": $("#orderNum").val(),
                "extendString6": $("#extendString6").val(),
                "extendString3": $("#extendString3").val(),
                "extendString2": $("#extendString2").val(),
                "ztId": $("#ztId").val(),
                "extendString1": $("#extendString1").val(),
                "extendString4": $("#extendString4").val()
            },
            success: function (ret) {

                if (ret.status == '1') {
                    $("#id").val(ret.data.id);
                    $("#code").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    $("#rkBtn").attr("disabled", true);
                    reloadHis();
                    if (openFlag) {
                        open();
                    }

                } else {
                    layer.alert('���浥��ʧ�ܣ�' + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("���浥���������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };

    var updateSheet = function (id) {
        $.ajax({
            type: "put",
            url: "/sheet/WZTH/" + id,
            dataType: "json",
            data: {"memo": $("#memo").val()},
            success: function (ret) {
                if (ret.status == '1') {
                    layer.alert('���浥�ݳɹ�');
                    return true;
                } else {
                    layer.alert('���浥��ʧ�ܣ�' + ret.message);
                    return false;
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("���浥���������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };

    //�����ϸ��ť
    $("#addDetails").on("click", function (e) {
        if ($("#id").val() == "") {
            saveSheet(true);
        } else {
            open();
        }
    });
    //ɾ����ϸ
    $("#deleteDetails").on("click", function (e) {
        // var checkStatus = table.checkStatus('detailsgridReload');
        var checkStatus = table.checkStatus('subdetailsgridReload');
        var data = checkStatus.data;
        var ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('ȷ��ɾ��ѡ�е���ϸ��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post("/sheet/detail/delSheetDetails.json?ids=" + ids, function (data) {
                    reload();
                    layer.msg(data.message);
                    layer.close(index);
                });
            })
        } else {
            layer.msg('��ѡ��Ҫɾ���ļ�¼', {offset: 't', anim: 6});
        }
    });


    $("#rkBtn").on("click", function (e) {
        vipTable.openPage("��ⵥ", "/sheet/rk/generalRKD.htm?creator=" + $("#userId").val(), '90%', '80%');
        //  vipTable.openPage("�ɹ������б�", "/sheet/wzjs/generalOrder.htm", '680px', '400px');
        return false;

    });

    // //����������
    // table.on('tool(detailsgrid)', function (obj) {
    //     var data = obj.data;
    //     subreload(data.materialCode,data.storeId);
    //
    //     layer.open({
    //         type: 1,
    //         title: "��ϸ����",
    //         area: ['600px', '400px'],
    //         content: $("#showSub")
    //     });
    // });

    //����������
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        vipTable.openPage("�˻�/������ʷ��¼", "/sheet/wzjs/orderHistory.htm?detailsGUID=" + data.jsGuid, "60%", "70%");

        // hisreload(data.materialCode,data.storeId);
        //
        // layer.open({
        //     type: 1,
        //     title: "��ʷ����",
        //     area: ['600px', '400px'],
        //     content: $("#showhis")
        // });
    });

    $("#doprint").on("click", function (e) {
        vipTable.openPage("��ӡ�˻���", "/system/print/sheet/WZTH-" + $("#id").val(), '800px', '560px');
    });

});

function setRkInfo(data) {
    $("#rkCode").val(data.code);
    $("#extendInt1").val(data.id);//��ⵥ��
    $("#orderNum").val(data.orderNum);//�ɹ��������
    $("#extendString6").val(data.extendString5);//���ź�
    $("#extendString3").val(data.extendString3);//��������
    $("#extendString2").val(data.extendString2);//�����֯
    $("#ztId").val(data.ztId);//�����֯
    $("#extendString1").val(data.extendString1);//�����֯

}
