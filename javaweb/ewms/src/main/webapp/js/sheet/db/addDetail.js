layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['form', 'table', 'layer', 'vip_table'], function () {

    var table = layui.table;
    var $ = layui.$;

    var form = layui.form;
    form.on("submit(search)", function (data) {
        reload(data);
        return false;
    });

    var ztId = parent.layui.$("#cOrg").val();
    $("#ztId").val(ztId);


    //��ϸ�б�
    var detailGrid = table.render({
        elem: '#detailGrid'
        , url: 'addDetails?ztId='+ztId
        , cellMinWidth: 80
        , height: "full-98"
        , method: "post"
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailGridReload"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'newLocation', title: '�����λ', align: "center", width: 120, event: 'newLocation'}
                , {field: 'detailCount', title: '��������', align: "center", width: 100, edit: "text"}

                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 140}
                , {field: 'description', title: '��������', align: "center", width: 250}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 50}
                , {field: 'storeCount', title: '�����', align: "center", width: 80}
                , {field: 'isCount', title: '�ɳ�����', align: "center", width: 80}
                , {field: 'houseName', title: '�ⷿ', align: "center", width: 150}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('���ѡ������λ���������������', '.laytable-cell-1-newLocation  ', {
                time: 2000
            });
            // layer.tips('���ѡ��Ŀ���λ��', '.laytable-cell-1-newLocation ', {
            //     time: 2000
            // });
            // layer.tips('��������ƶ�������', '.laytable-cell-1-detailCount ', {
            //     time: 2000
            // });
        }
    });

    var reload = function (data) {
        table.reload('detailGridReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: JSON.parse(JSON.stringify(data.field))

        })
    };

    table.on('tool(detailGrid)', function (obj) { //ע��tool�ǹ������¼�����test��tableԭʼ���������� lay-filter="��Ӧ��ֵ"
        var data = obj.data; //��õ�ǰ������
        var layEvent = obj.event; //��� lay-event ��Ӧ��ֵ��Ҳ�����Ǳ�ͷ�� event ������Ӧ��ֵ��
        var tr = obj.tr; //��õ�ǰ�� tr ��DOM����
        $("#newlocationCode").val("");
        $("#newlocationId").val("");
        $("#newlocationName").val("")
        if (layEvent === 'newLocation') {


            layui.layer.open({
                title: "��λ",
                type: 2,
                fixed: false ,
                area: ['580px', '400px'],
                content: "../../system/ware/location?pre=new&parentCode=" + data.wareHouseCode + "&ztid=" + parent.layui.$("#rOrg").val(),
                end: function () {
                    obj.update({
                          newLocation: $("#newlocationName").val()
                        , newLocationId: $("#newlocationId").val()
                        , newLocationCode: $("#newlocationCode").val()
                        , newStoreId: $("#store").val()
                    });
                }
            })

        }
    });


    $("#add").on("click", function (e) {
        parent.layui.$("#reloadDetailsgrid").val(1);
        var checkStatus = table.checkStatus('detailGridReload');
        var length = checkStatus.data.length;
        var sheetId = parent.layui.$("#id").val();
        var userZtId = parent.layui.$("#userZtId").val();
        if (length < 1  ){
            //���Ϸ�
            layer.alert('������ѡ��һ����¼���');
        }else{
            var data = checkStatus.data;
            var details = [];
            var obj;
            var count;
            for (var i = 0; i < length; i++) {
                if (data[i].newLocationId == null || data[i].newLocationId == '') {
                    layer.alert("��ѡ��Ŀ���λ��");
                    return false;
                }
                count = data[i].detailCount;
                if (count == null || count.trim() == '' || isNaN(count.trim())) {
                    layer.alert("��������������Ч����,��������д����������");
                    return false;
                }
                if (count.trim() == 0) {
                    layer.alert("������������Ϊ0��");
                    return false;
                }
                if (count.trim() > data[i].isCount) {
                    layer.alert("�����������ô��ڿɳ�������");
                    return false;
                }
                if (count.trim() > data[i].storeCount) {
                    layer.alert("�����������ô��ڿ������");
                    return false;
                }

                if(count.trim()<0){
                    layer.alert("������������Ϊ����");
                    return false;
                }
                if(data[i].enableSn=="1"){
                    if(!(/^(\+|-)?\d+$/.test(count))){
                        layer.alert("�������е����ϵ�����������������");
                        return false;
                    }
                }

                var obj = {
                    sheetId: sheetId,
                    materialId: data[i].materialId,
                    tagCode:data[i].materialCode,
                    materialCode: data[i].materialCode,
                    materialName: data[i].materialName,
                    detailUnitName: data[i].detailUnitName,
                    detailCount: count.trim(),
                    description: data[i].description,
                    providerDepId: data[i].providerDepId,
                    storeId: data[i].newStoreId,
                    storeLocationId: data[i].newLocationId,
                    storeLocationName: data[i].newLocation,
                    storeLocationCode: data[i].newLocationCode,
                    extendString1: data[i].materialCode,
                    enableSn: data[i].enableSn,
                    ownerType: data[i].ownerType,
                    ztId: userZtId
                };

                details.push(obj);
            }


            $.ajax({
                type: "POST",
                url: "../detail/addWZDBDDetails",
                dataType:"json",
                data: {details: JSON.stringify(details)},
                success: function (ret) {
                    if (ret.status == '1') {
                        layer.msg('��ӳɹ�', function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert('���ʧ�ܣ�' + ret.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            });
        }
        return false;
    });
});

