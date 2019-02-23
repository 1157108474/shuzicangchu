layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['form', 'table', 'layer', 'vip_table'], function () {


    var table = layui.table;
    var $ = layui.$;
    var form = layui.form;
    var vipTable = layui.vip_table;

    form.on("submit(search)", function (data) {
        reload(data);
        return false;
    });


    //��ϸ�б�
    var detailGrid = table.render({
        elem: '#detailGrid'
        , url: 'getAddDetails'
        , cellMinWidth: 80
        , height: "full-98"
        , method: "post"
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailGridReload"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 30}
                , {
                field: 'newStore', title: 'Ŀ��ⷿ', align: "center", width: 120,
                event: 'newStore'
                /*templet: function (d) {
                    return d.houseName
                }*/
            }
                , {field: 'newLocation', title: 'Ŀ���λ', align: "center", width: 120, event: 'newLocation'}
                , {field: 'detailCount', title: '�ƶ�����', align: "center", width: 80, edit: "text"}
                , {field: 'isCount', title: '���ƶ�����', align: "center", width: 100}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'description', title: '��������', align: "center", width: 200}
                , {field: 'houseName', title: 'ԭ�ⷿ', align: "center", width: 120}
                , {field: 'storeLocationName', title: 'ԭ��λ', align: "center", width: 120}
                , {field: 'providerDepName', title: '��Ӧ��', align: "center", width: 200}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 50}
            ]
        ], done: function (res, curr, count) {
            layer.tips('���ѡ���λ,�����ƶ�������', '.laytable-cell-1-newLocation  ', {
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


    $("#provider").on("click", function (e) {
        vipTable.openPage("��Ӧ��", "../../system/provider/generalProvider.htm", '580px', '400px');
        return false;
    });


    var before = '';
    form.on('select(store)', function (store) {
        var current = store.value;
        if (current != before) {
            before = current;
            $("#locationId").val('');
            $("#locationName").val('');
            $("#locationCode").val('');
        }
    });


    $("#storeLocation").on("click", function (e) {

        if (before == '') {
            layer.alert("����ѡ��ⷿ");
        } else {
            vipTable.openPage("��λ", "../../system/ware/location?parentId=" + before + "&ztid=" + $("#ztId").val(), '580px', '400px');

        }
        return false;

    });
    table.on('tool(detailGrid)', function (obj) { //ע��tool�ǹ������¼�����test��tableԭʼ���������� lay-filter="��Ӧ��ֵ"
        var data = obj.data; //��õ�ǰ������
        var layEvent = obj.event; //��� lay-event ��Ӧ��ֵ��Ҳ�����Ǳ�ͷ�� event ������Ӧ��ֵ��

        // var tr = obj.tr; //��õ�ǰ�� tr ��DOM����
        $("#newlocationCode").val("");
        $("#newlocationId").val("");
        $("#newlocationName").val("")
        if (layEvent === 'newStore') {
            layui.layer.open({
                title: "�ⷿ"
                , type: 2
                , fixed: false
                , area: ['580px', '420px']
                , content: "../../system/ware/location?pre=new&parentId=0&ztid=" + $("#ztId").val()
                , end: function () {
                    obj.update({
                        newStore: $("#newlocationName").val()
                        , newStoreId: $("#newlocationId").val()
                        , newLocation: ''
                        , newLocationId: ''
                        , newLocationCode: ''
                    });
                }
            })
        } else if (layEvent === 'newLocation') {

            if (data.newStore == null || data.newStore == '') {
                layer.alert("����ѡ��Ŀ��ⷿ");
            } else {

                layui.layer.open({
                    title: "��λ"
                    , type: 2
                    , fixed: false
                    , area: ['580px', '400px']
                    , content: "../../system/ware/location?pre=new&parentId=" + data.newStoreId+"&ztid="+$("#ztId").val()
                   /*,content: "../../system/ware/location?pre=new&parentId=" + data.storeId + "&ztid=" + $("#ztId").val()*/

                    ,end: function () {
                        obj.update({
                            newLocation: $("#newlocationName").val()
                            , newLocationId: $("#newlocationId").val()
                            , newLocationCode: $("#newlocationCode").val()
                        });
                    }
                })
            }
        }
    });

    $("#add").on("click", function (e) {
        parent.layui.$("#reloadDetailsgrid").val(1);
        var checkStatus = table.checkStatus('detailGridReload');
        var length = checkStatus.data.length;
        var sheetId = parent.layui.$("#id").val();
        if (length < 1) {
            //���Ϸ�
            layer.alert('������ѡ��һ����¼���');
        } else {
            var data = checkStatus.data;
            var details = [];
            var obj;
            var count;
            for (var i = 0; i < length; i++) {


                if (data[i].newStoreId == null || data[i].newStoreId == '') {
                    layer.alert("��ѡ��Ŀ��ⷿ��");
                    return false;

                } else if (data[i].newLocationId == null || data[i].newLocationId == '') {
                    layer.alert("��ѡ��Ŀ���λ��");
                    return false;
                }
                if (data[i].newLocationId == data[i].storeLocationId) {
                    layer.alert("λ��û�з����ı�,������ѡ��Ŀ���λ��");
                    return false;
                }
                count = data[i].detailCount;
                if (count == null || count.trim() == '' || isNaN(count.trim())) {
                    layer.alert("�ƶ�����������Ч����,��������д�ƶ�������");
                    return false;
                }
                count = count.trim();
                if (count == 0) {
                    layer.alert("�ƶ���������Ϊ0��");
                    return false;

                }
                if (count < 0) {
                    layer.alert("�ƶ���������С��0��");
                    return false;
                }
                if (count > data[i].isCount) {
                    layer.alert("�ƶ��������ô��ڿ��ƶ�������");
                    return false;
                }
                obj = {
                    sheetId: sheetId,
                    sheetDetailId: data[i].id,
                    categoryId: data[i].categoryId,
                    materialId: data[i].materialId,
                    materialCode: data[i].materialCode,
                    materialName: data[i].materialName,
                    materialBrand: data[i].materialBrand,
                    materialModel: data[i].materialModel,
                    materialSpecification: data[i].materialSpecification,
                    detailCount: count.trim(),
                    tagCode: data[i].materialCode,
                    detailUnitName: data[i].detailUnitName,
                    extendInt4: data[i].storeId,
                    extendInt5: data[i].storeLocationId,
                    extendString5: data[i].storeLocationCode,
                    extendString6: data[i].storeLocationName,
                    providerDepId: data[i].providerDepId,
                    noTaxPrice: data[i].noTaxPrice,
                    taxPrice: data[i].taxPrice,
                    taxRate: data[i].taxRate,
                    description: data[i].description,
                    expirationTime: data[i].expirationTime,
                    planDepartId: data[i].planDepartId,
                    storeId: data[i].newStoreId,
                    // storeId: data[i].storeId,
                    storeLocationId: data[i].newLocationId,
                    storeLocationName: data[i].newLocation,
                    storeLocationCode: data[i].newLocationCode,
                    isEquipment: data[i].isEquipment,
                    enableSn: data[i].enableSn,
                    snCode: data[i].snCode,
                    ownerType: data[i].ownerType,
                    ztId: data[i].ztId
                };
                details.push(obj);
            }


            $.ajax({
                type: "POST",
                url: "../detail/addYKYWDetails",
                dataType: "json",
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

