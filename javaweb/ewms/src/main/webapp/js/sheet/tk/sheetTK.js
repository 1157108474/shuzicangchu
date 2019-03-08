layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;

    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var ran = Math.random();

    //��ϸ�б�
    var detailsgr = table.render({
        elem: '#detailsgrid'
        , url: '/sheet/tk/details'
        ,where:{ran:ran++,sheetId:$("#id").val()}
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailsgridTable"
        , cols: [
            [ {type: "checkbox", fixed: "left", width: 50}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'description', title: '��������', align: "center", width: 300}
                , {field: 'detailCount', title: '�˿�����', align: "center",width: 80}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 80}
                , {field: 'houseCode', title: '�ⷿ����', align: "center", width: 120}
                , {field: 'houseName', title: '�ⷿ', align: "center", width: 120}
                , {field: 'providerName', title: '��Ӧ��', align: "center", width: 160}
                , {field: 'noTaxPriceDouble', title: '����˰����', align: "center", width: 100}
                , {field: 'noTaxSumDouble', title: '����˰���', align: "center", width: 100}
            ]
        ]
    });


    var reload =function(){
        table.reload('detailsgridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where:{
                ran:ran++,
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
            , content: "/sheet/tk/openAddDetail"
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    reload();
                }
            }
        })
    };

    var saveSheet =function(openFlag){
        if($("#extendInt1").val()==""){
            layer.alert("����ѡ����ⵥ��");
            return;
        }

        $.ajax({
            type: "post",
            url: "/sheet/WZTK",
            dataType: "json",
            data: {"menuCode":$("#menuCode").val(),"extendInt1":$("#extendInt1").val(),"usedDepartId":$("#usedDepartId").val(),
                "officesId":$("#officesId").val(), "extendString1":$("#extendString1").val(),"ztId":$("#ztId").val(),
                "fundsSource":$("#fundsSource").val(),"memo":$("#memo").val(),"typeId":189,"extendInt3":0},
            success: function (ret) {
                if (ret.status == '1') {
                    $("#id").val(ret.data.id);
                    $("#code").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    $("#ckBtn").attr("disabled",true);
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
            url: "/sheet/WZTK/"+id,
            dataType: "json",
            data: {"memo":$("#memo").val(),"extendString1":$("#extendString1").val()},
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

        var checkStatus = table.checkStatus('detailsgridTable');
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


    $("#ckBtn").on("click", function (e) {
        vipTable.openPage("���ⵥ", "/sheet/ck/openCKOder.htm", '70%', '80%');
        //  vipTable.openPage("�ɹ������б�", "/sheet/wzjs/generalOrder.htm", '680px', '400px');
        return false;

    });

    $("#print").on("click", function (e) {
    	var taskId = $("#taskId").val();
            vipTable.openPage("��ӡ�˻���", "/system/print/sheet/WZTK-" + $("#id").val() + "?taskId=" + taskId  , '95%', '95%');
    });


});
function getCkOrder(data){
    $("#ckCode").val(data.cksheetCode);
    // $("#ckCode").val(data.code);
    $("#extendInt1").val(data.id);
   $("#usedDepartId").val(data.usedDepartId);
    $("#usedDepartName").val(data.useddepartname);
    $("#officesId").val(data.officesId);
    $("#officesName").val(data.officesName);
    $("#fundsSource").val(data.fundsSource);
    $("#fundsSourceName").val(data.fundsSourceName);
    $("#extendString1").val(data.extendString1);
    layui.form.render('select');


}


