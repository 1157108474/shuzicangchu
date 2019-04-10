layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['form', 'table', 'layer', 'vip_table'], function () {

    var layer = layui.layer;

    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;
    var ran =Math.random();
   // document.getElementById('date').innerHTML= new Date().toLocaleString();

    //��ϸ�б�
    var detailsgrid = table.render({
        elem: '#detailsgrid'
        , url: 'details'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'POST'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20 ,30]  //���ݷ�ҳ��
        ,id: 'detailsgridReload'
        ,where:{ran:ran++,sheetId:$("#id").val()}

        , cols: [
                 [{type: "checkbox", fixed: "left", width: 30}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 110}
                , {field: 'description', title: '��������', align: "center", width: 220}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 50}
                , {field: 'providerDepName', title: '��Ӧ��', align: "center", width: 200}
                , {field: 'noTaxPrice', title: '����˰�۸�', align: "center", width: 200}
                , {field: 'noTaxSum', title: '����˰���', align: "center", width: 200}
                , {field: 'oldHouseCode', title: 'ԭ�ⷿ����', align: "center", width: 120}
                , {field: 'oldHouseName', title: 'ԭ�ⷿ', align: "center", width: 120}
                , {field: 'extendString6', title: 'ԭ��λ', align: "center", width: 120}
                , {field: 'detailCount', title: '�ƶ�����', align: "center",width: 80}
                , {field: 'newHouseCode', title: 'Ŀ��ⷿ����', align: "center", width: 120}
                , {field: 'newHouseName', title: 'Ŀ��ⷿ', align: "center", width: 120}
                , {field: 'storeLocationName', title: 'Ŀ���λ', align: "center", width: 120}
            ]
        ]
    });

    var reload =function(){
        table.reload('detailsgridReload', {
           page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where:{
                ran: ran++,
                sheetId: $("#id").val()
            }

        })
    };


   //���水ť
    $("#saveSheet").on("click", function (e) {
        var result;
        var id = $("#id").val();

        if( id==""){
            result = saveSheet(false);
        }else{
            result = updateSheet(id);
        }
        if(result){
            layer.alert("����ɹ�");
        }
    });



    var open = function () {   //������ϸҳ��
        $("#reloadDetailsgrid").val(0);
        layer.open({
            title: "�����ϸ"
            , type: 2
            , fixed: false
            , area: ["90%", "90%"]
            , content: "/sheet/ykyw/addDetail"
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    reload();
                }
            }
        })
    };

    var saveSheet =function(openFlag){
        $.ajax({
            type: "post",
            url: "/sheet/YKYW",
            dataType: "json",
            data: {"memo": $("#memo").val(), "menuCode": $("#menuCode").val()},
            success: function (ret) {

                if (ret.status == '1') {
                    $("#id").val(ret.data.id);
                    $("#code").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    reloadHis();
                    if(openFlag) {
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

    var updateSheet =function(id){
        $.ajax({
            type: "put",
            url: "/sheet/YKYW/"+id,
            dataType: "json",
            data: {"memo":$("#memo").val()},
            success: function (ret) {
                if (ret.status == '1') {
                    layer.alert('���浥�ݳɹ�' );
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
        if($("#id").val()==""){
            saveSheet(true);
        } else {
            open();
        }
    });
    //ɾ����ϸ
        $("#deleteDetails").on("click", function (e) {
        var checkStatus = table.checkStatus('detailsgridReload');
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


    $("#print").on("click", function (e) {
             layer.alert("δ�ṩ��ӡ����");
            // vipTable.openPage("��ӡ�ƿ���λ��", "/system/print/sheet/YKYW-" + $("#id").val() + "?printType=" + printType, '780px', '400px');
    });


});

