layui.use(['form', 'table', 'layer'], function () {

    var table = layui.table;
    var $ = layui.$;
    var form = layui.form;

    form.on("submit(search)", function (data) {
        $("#stockDetail").hide();
        reload(data);
        return false;
    });
    form.on("submit(reset)", function (data) {
        $("#stockDetail").hide();
        return;
    });
    var detailData;
    var oldTr;
    //��ϸ�б�
    var detailGrid = table.render({
        elem: '#detailGrid'
        , url: 'addDetails?sheetId=' + parent.layui.$("#extendInt1").val()
        , cellMinWidth: 80
        , height: "full-220"
        , method: "post"
        , page: true   //������ҳ
        , limit: 5   //Ĭ��ʮ��������һҳ
        , limits: [5, 10, 15]  //���ݷ�ҳ��
        , id: "detailGridReload"
        , cols: [
            [   //{type: "checkbox", fixed: "left", width: 50},
                {field: 'materialCode', title: '���ϱ���', align: "center", width: 130}
                , {field: 'description', title: '��������', align: "center", width: 200}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 50}
                , {field: 'sheetCode', title: '��ⵥ��', align: "center", width: 140}
                , {field: 'orderNum', title: '�ɹ�����', align: "center", width: 160}
                , {field: 'extendString1', title: '�ⷿ', align: "center", width: 100}
                , {field: 'subDetailCount', title: '�������', align: "center", width: 80}
                , {
                field: 'action', title: '���˻�����', align: "center", width: 90,
                templet: function (d) {
                    return d.subDetailCount - d.ytCount
                }
            }
                , {field: 'storeUsedCount', title: '��������', align: "center", width: 90}
                , {field: 'snCode', title: '���к�', align: "center", width: 100}
            ]
        ]
        , trclick: function (data, tr) {
            // tr.parent().removeAttr("bgcolor","#666");
            if (oldTr != null) {
                oldTr.removeAttr("bgcolor", "#f2f2f2");
            }
            tr.attr("bgcolor", "#f2f2f2");
            oldTr = tr;
            // tr.after("<tr><td><span>111133333</span></td></tr>");
            $("#stockDetail").show();
            reloadstock(data);
            detailData = data;
        }
    });
    var reload = function (data) {
        table.reload('detailGridReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: data.field
        })
    };

    //��ϸ�б�
    var detailstock = table.render({
        elem: '#detailstock'
        , url: 'getStockDetails'
        , cellMinWidth: 80
        , height: "full-180"
        , method: "post"
        , page: true   //������ҳ
        , limit: 5   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailstockReload"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 30},
                {field: 'detailCount', title: '�˻�����', align: "center", width: 120, edit: "text"}
                , {field: 'wareHouseCode', title: '�ⷿ', align: "center", width: 150}
                , {field: 'storeLocationCode', title: '��λ', align: "center", width: 150}
                , {field: 'storeCount', title: '�������', align: "center", width: 120}
                , {field: 'snCode', title: '���к�', align: "center", width: 120}
                , {field: 'providerName', title: '��Ӧ��', align: "center", width: 200}

            ]
        ]
    });
    var reloadstock = function (data) {
        var provider = "";
        if (data.ownerType == "609") {//�ж��Ƿ��Ǽ�������
            provider = data.providerDepId;
        }
        table.reload('detailstockReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                search: 1,
                materialCode: data.materialCode,
                sheetCode:data.sheetCode,
                ownerType: data.ownerType,
                //sonId: data.sonId,
                storeId: data.storeId,
                providerDepId: provider,
                snCode: data.snCode,
                ztId: parent.layui.$("#ztId").val()
            }
        })
    };


    $("#add").on("click", function (e) {
        parent.layui.$("#reloadDetailsgrid").val(1);
        var checkStatus = table.checkStatus('detailstockReload');
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
            var thTotalCount = 0.00;
            for (var i = 0; i < length; i++) {
                count = data[i].detailCount;
                if (count == null || count.trim() == '' || isNaN(count.trim())) {
                    layer.alert("�˻�����������Ч����,��������д�˻�������");
                    return false;
                }
                count = count.trim();
                thTotalCount += count * 1;
                if (count == 0) {
                    layer.alert("�˻���������Ϊ0��");
                    return false;
                }
                if (count < 0) {
                    layer.alert("�˻���������С��0��");
                    return false;
                }

                if (count > detailData.subDetailCount) {
                    layer.alert("�˻���������С���������,��������д�˻���������");
                    return false;
                }

                if (count > detailData.subDetailCount - detailData.ytCount) {
                    var msg = "���ϡ�" + detailData.MATERIALCODE + "���Ŀ��˻������ǣ���" + (parseFloat(detailData.subDetailCount) - parseFloat(detailData.ytCount)) + "�����˻���������С�ڿ��˻�����,��������д�˻�������";
                    layer.alert(msg);
                    return false;
                }
                if (detailData.isEquipment == "1") {
                    if (!(/^(\+|-)?\d+$/.test(count))) {
                        layer.alert("�豸���˻�����������������,��������д��ȷ��������");
                        return;
                    }
                }
                if (thTotalCount > detailData.subDetailCount - detailData.ytCount) {
                    var msg = "�˻���������С�ڿ��˻�����,��������д�˻�������";
                    layer.alert(msg);
                    return false;
                }
                obj = {
                    sheetId: sheetId,
                    sheetDetailId: detailData.id,
                    materialId: detailData.materialId,
                    materialCode: detailData.materialCode,
                    materialName: detailData.materialName,
                    materialBrand: detailData.materialBrand,
                    materialModel: detailData.materialModel,
                    detailUnitName: detailData.detailUnitName,
                    taxSum: parseFloat(detailData.noTaxPrice) * (1 + parseFloat(detailData.taxRate)) * count,
                    taxPrice: detailData.taxPrice,
                    noTaxPrice: detailData.noTaxPrice,
                    taxRate: detailData.taxRate,
                    tagCode: detailData.materialCode,
                    noTaxSum: parseFloat(detailData.noTaxPrice) * count,
                    materialSpecification: detailData.materialSpecification,
                    description: detailData.description,
                    categoryId: detailData.categoryId,
                    isEquipment: detailData.isEquipment,
                    enableSn: detailData.enableSn,
                    snCode: data[i].snCode,
                    ztId: detailData.ztId,
                    ownerType: data[i].ownerType,
                    detailCount: count,
                    storeId: data[i].storeId,
                    storeLocationId: data[i].storeLocationId,
                    storeLocationCode: data[i].storeLocationCode,
                    storeLocationName: data[i].storeLocationName,
                    providerDepId: data[i].providerDepId,
                    extendFloat1: (parseFloat(detailData.noTaxPrice) * count) * (1 + parseFloat(detailData.taxRate)),
                    extendString10:detailData.erpRowNum,
                    extendInt8: detailData.sonId//�����ID
                };
                details.push(obj);
            }
            $.ajax({
                type: "POST",
                url: "../detail/addWZTHDetails",
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

