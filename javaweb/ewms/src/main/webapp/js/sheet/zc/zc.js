layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['form', 'table', 'layer', 'vip_table'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;
    var ran =Math.random();
    var form = layui.form;
  //  document.getElementById('date').innerHTML= new Date().toLocaleString( );
    //��ϸ�б�
    var detailsgrid = table.render({
        elem: '#detailsgrid'
        , url: 'details'
        , cellMinWidth: 80
        , height: "full-240"
        , method: 'POST'
        , page: true   //������ҳ
        , limit: 20   //Ĭ��ʮ��������һҳ
        , limits: [10, 20 ,30]  //���ݷ�ҳ��
        ,id: 'detailsgridReload'
        ,where:{ran:ran++,sheetId:$("#id").val()}

        , cols: [
            [     {type: "checkbox", fixed: "left", width: 30}
                , {title: '����', align: "center",fixed:'left', toolbar: '#bar', width: 100}
                , {field: 'materialName', title: '��������', align: "center", width: 130}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 130}
                , {field: 'description', title: '��������', align: "center", width: 420}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 60}
                , {field: 'houseName', title: '�ⷿ', align: "center", width: 110}
                , {field: 'storeLocationName', title: '��λ', align: "center", width: 120}
                , {field: 'detailCount', title: '����', align: "center",width: 80}
                , {field: 'noTaxPriceDouble', title: '����˰����', align: "center", width: 100}
                , {field: 'noTaxSumDouble', title: '����˰���', align: "center", width: 100}

            ]
        ]

    });

    var reload =function(){
        table.reload('detailsgridReload', {
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

    //������ϸҳ��
    var open = function () {
        $("#reloadDetailsgrid").val(0);
        layer.open({
            title: "�����ϸ"
            , type: 2
            , fixed: false
            , area: ["90%", "90%"]
            , content: "addDetails.htm"
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    reload();
                }
            }
        });
    };



    var saveSheet =function(openFlag){
        if($("#ownerdepName").val()==""){
            layer.alert("����ѡ������֯");
            return;
        }

        $.ajax({
            type: "post",
            url: "/sheet/WZZC",
            dataType: "json",
            data: {"menuCode":$("#menuCode").val(),"extendString2":$("#ownerdepName").val(),"ztId":$("#ownerdepId").val(),"extendString1":$("#providerName").val(),"providerDepId":$("#providerId").val(),"extendString4":$("#extendString4").val(),"memo":$("#memo").val()},
            success: function (ret) {

                if (ret.status == '1') {
                    $("#id").val(ret.data.id);
                    $("#code").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    $("#rkBtn").attr("disabled",true);
                    reloadHis();
                    if(openFlag){
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
            url: "/sheet/WZZC/"+id,
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

    //����������
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        subreload(data.materialCode,data.storeId);

        layer.open({
            type: 1,
            title: "��ϸ����",
            area: ['600px', '400px'],
            content: $("#showSub")
        });
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
                    reload();
                }
            })
        }
    })
    $("#doprint").on("click", function (e) {
        var printType = $("#printType").val();
        if (printType == '') {
           layer.alert("��ѡ���ӡ����");
       }else{

            vipTable.openPage("��ӡ�˻���", "/system/print/sheet/WZTH-" + $("#id").val() + "?printType=" + printType, '780px', '400px');

       }
    });

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

    //��ȡ��Ӧ���б�
    $("#providerBtn").on("click", function () {
        layui.layer.open({
            type: 2,
            title: '��Ӧ��',
            fixed: false,
            area: ['38%', '75%'],
            content: "../../system/provider/generalProvider.htm",
            // scrollbar:false
        });
        return false;
    });
});
function setRkInfo(data) {
    $("#rkCode").val(data.code);
    $("#extendString2").val(data.extendString2);//�����֯
    $("#ztId").val(data.ztId);//�����֯
    $("#extendString1").val(data.extendString1);//�����֯

}
//��ȡ����ҳ��ش�����
function obtainPart(id, name, ztId, code) {
    document.getElementById("ownerdepName").value = name;
    document.getElementById("ownerdepId").value = id;
}