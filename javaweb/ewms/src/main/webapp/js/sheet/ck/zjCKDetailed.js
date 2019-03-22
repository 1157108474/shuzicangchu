layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var vipTable = layui.vip_table;
    //��ϸ�б�
    var planDetailGr = table.render({
        elem: '#zjckdetails'
        , url: '/sheet/ck/listCldetails.json'
        , cellMinWidth: 80
        , height: "full-50"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "zjckdetailsTable"
        , ztId: parent.layui.$("#ztId").val()
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'detailCount', title: '��������', align: "center", width: 120, edit: 'text'}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 100}
                , {field: 'storecount', title: '�������', align: "center", width: 100}
                , {field: 'unsecount', title: '����������', align: "center", width: 120}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 180}
                , {field: 'ownerTypeName', title: '��������', align: "center", width: 100}
                , {field: 'storelocationname', title: '�ⷿ', align: "center", width: 100}
                , {field: 'storelocationareaname', title: '��λ', align: "center", width: 100}
                , {field: 'providername', title: '��Ӧ��', align: "center", width: 180}
                , {
                field: 'createDate', title: '���ʱ��', align: "center", width: 170, templet: function (d) {
                    return vipTable.datetimeformat(d.createDate);
                }
            }
                , {field: 'materialName', title: '��������', align: "center", width: 320}

            ]
        ], done: function (res, curr, count) {
            layer.tips('��������������', '.laytable-cell-1-detailCount', {
                time: 2000
            });
        }
    });
    
    //��ѯ�ƻ���ϸ
    form.on("submit(formSubmit)", function (data) {
        table.reload('zjckdetailsTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                materialCode: $("#materialCode").val()
                , storelocationname: $("#storelocationname").val()
            }
        });
        return false;
    });


    //ֱ�ӳ���add
    $("#add").on("click", function (e) {
        var rows = table.checkStatus('zjckdetailsTable').data;
        var zjdetails = [];
        parent.layui.$("#reloadDetailsgrid").val(1);
        if (rows.length > 0) {
            for (var i = 0; i < rows.length; i++) {
                var count = rows[i].detailCount;
                if (isNaN(count) || count == '0' || count <= 0) {
                    layer.msg("��������������Ч����,����д��д����������");
                    return;
                } else if (null == count || count == "") {
                    layer.msg("��������Ϊ��,����д����������");
                    return;
                } else if (count > rows[i].unsecount) {
                    layer.msg("�����������ܴ��ڿ���������,��������д����������");
                    return;
                }
                var slobj = {
                    sheetId: parent.layui.$("#id").val(),
                    sheetDetailId: 0,
                    extendint2: rows[i].id,
                    categoryId: rows[i].categoryid,
                    materialId: rows[i].materialid,
                    materialCode: rows[i].materialCode,
                    materialName: rows[i].materialName,
                    materialBrand: rows[i].materialbrand,
                    materialModel: rows[i].materialModel,
                    materialSpecification: rows[i].materialspecification,
                    detailUnit: rows[i].detailunit,
                    storeId: rows[i].storeId,
                    storeLocationId: rows[i].storelocationid,
                    storeLocationName: rows[i].storelocationareaname,
                    storeLocationCode: rows[i].storelocationcode,
                    description: rows[i].description,
                    tagCode: rows[i].tagcode,
                    planDepartId: rows[i].plandepartid,
                    noTaxPrice: rows[i].notaxprice,
                    taxPrice: rows[i].taxprice,
                    taxRate: rows[i].taxrate,
                    providerDepId: rows[i].providerdepid,
                    extendstring1: $("#g_sncode").val(),   //�ƻ�����
                    /*sncode: rows[i].SNCODE,    //���к�*/
                    detailUnitName: rows[i].detailUnitName,
                    detailCount: count,
                    extendint1: 0,
                    ownerType: rows[i].ownerType,
                    ZtId: parent.layui.$("#ztId").val(),
                    extendInt5: $("#g_sheetDetailId").val()
                };
                zjdetails.push(slobj);
            }
            $.ajax({
                url: "/sheet/ck/saveDetail.json",
                type: "post",
                data: {details: JSON.stringify(zjdetails)},
                success: function (ret) {
                    if (ret.status == '1') {
                        layer.msg('��ӳɹ�', function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert('���ʧ�ܣ�' + ret.message);
                    }
                }
            });
        } else {
            layer.msg("��ѡ��һ����ϸ");
        }
    });
});


