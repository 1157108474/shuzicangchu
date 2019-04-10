layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {
    var table = layui.table;
    var $ = layui.$;
    var form = layui.form;

    form.on("submit(search)", function (data) {
        reload(data);
        return false;
    });

    var ztid = parent.layui.$("#ztId").val();

    //��ϸ�б�
    var detailGrid = table.render({
        elem: '#detailGrid'
        , url: 'getAddDetails'
        , where: {'sheetId': parent.layui.$("#extendInt1").val()}
        , cellMinWidth: 80
        , height: "full-98"
        , method: "post"
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailGridReload"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'tkCount', title: '�˿�����', align: "center", width: 80, edit: "text",event: 'count'}
                , {field: 'storeLocationName', title: 'Ŀ���λ', align: "center", width: 120}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'providerDepName', title: '��Ӧ��', align: "center", width: 200}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 60}
                , {field: 'code', title: '���ⵥ��', align: "center", width: 150}
                , {field: 'detailCount', title: '��������', align: "center", width: 80}
                , {field: 'haveTkCount', title: '���˿�����', align: "center", width: 90}
                , {field: 'hasCj', title: '�Ƿ���', align: "center", width: 100}
            ]
        ], done: function (res, curr, count) {
            layer.tips('��������˻�������', '.laytable-cell-1-tkCount  ', {
                time: 2000
            });

        }
    });
    // ������Ԫ��༭
    table.on('edit(detailGrid)', function (obj) {
        var data = obj.data; //�����е������������
        var field = obj.field; //��ǰ�༭���ֶ���
        var value = obj.value; //�õ��޸ĺ��ֵ

        if(check.isDot(value)){
            if(!check.isAllowDecimal(data.detailUnitName)){
                layer.alert(data.detailUnitName+"��λ��������С��");
                data[field] = 0;
                return;
            }
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
            var count;
            for (var i = 0; i < length; i++) {
                if (data[i].hasCj == "��") {
                    layer.alert("�г�����ϸ�ѳ��������ӣ�");
                    return false;
                }
                count = data[i].tkCount;
                if (count == null || count.trim() == '' || isNaN(count.trim())) {
                    layer.alert("�˿�����������Ч����,��������д�˿�������");
                    return false;
                }
                count = count.trim();
                if (count == 0) {
                    layer.alert("�˿���������Ϊ0��");
                    return false;
                }
                if (count < 0) {
                    layer.alert("�˿���������С��0��");
                    return false;
                }
                if (count > data[i].detailCount) {
                    layer.alert("�˿��������ܴ��ڳ�������,��������д�˿�������");
                    return false;
                }
                var detailCount = data[i].detailCount - data[i].haveTkCount;
                if (count > detailCount) {
                    console.log(count > detailCount);
                    layer.alert("�˿��������ܴ��ڿ��˿�����(��������-���˿�����),��������д�˿�������");
                    return false;
                }

                var obj = {
                    sheetId: sheetId,
                    sheetDetailId: data[i].id,
                    // categoryId: data[i].categoryId,
                    materialId: data[i].materialId,
                    materialCode: data[i].materialCode,
                    materialName: data[i].materialName,
                    // materialBrand: data[i].materialBrand,
                    materialModel: data[i].materialModel,
                    materialSpecification: data[i].materialSpecification,
                    storeId: data[i].storeId,
                    storeLocationId: data[i].storeLocationId,
                    storeLocationName: data[i].storeLocationName,
                    storeLocationCode: data[i].storeLocationCode,
                    description: data[i].description,
                    noTaxPrice: data[i].noTaxPrice,
                    detailUnitName: data[i].detailUnitName,
                    providerDepId: data[i].providerDepId,
                    detailCount: count,
                    noTaxSum: accMul(data[i].noTaxPrice, count),
                    tagCode: data[i].materialCode,
                    ownerType: data[i].ownerType,
                    ztId: ztid
                };
                details.push(obj);
            }

            $.ajax({
                type: "POST",
                url: "../detail/addWZTKDetails",
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
    //�˷������������õ���ȷ�ĳ˷����
    //˵����javascript�ĳ˷������������������������˵�ʱ���Ƚ����ԡ�����������ؽ�Ϊ��ȷ�ĳ˷������
    //���ã�accMul(arg1,arg2)
    //����ֵ��arg1����arg2�ľ�ȷ���
    function accMul(arg1, arg2) {
        var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
        try {
            m += s1.split(".")[1].length
        } catch (e) {
        }
        try {
            m += s2.split(".")[1].length
        } catch (e) {
        }
        return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
    }

});
