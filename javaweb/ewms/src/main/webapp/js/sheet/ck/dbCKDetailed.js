layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var vipTable = layui.vip_table;

    var detailData;
    var oldTr;
    //��ϸ�б�
    var dbckdetailsGr = table.render({
        elem: '#dbckdetails'
        , url: '/sheet/ck/listDbDetails.json'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20 ,30]  //���ݷ�ҳ��
        , where: {
            code: $("#code").val()
        }
        , id: "dbckdetailsTable"
        , cols: [
            [ {field: 'materialCode', title: '���ϱ���', align: "center", width: 180}
                , {field: 'ytCount', title: '�ѵ�������', align: "center", width: 100}
                , {field: 'detailCount', title: '�ƻ���������', align: "center", width: 120}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 80}
                , {field: 'code', title: '��������', align: "center", width: 150}
                , {field: 'description', title: '��������', align: "center",width: 320}
            ]
        ]
        , trclick: function (data,tr) {
            if(oldTr!=null){
                oldTr.removeAttr("bgcolor","#f2f2f2");
            }
            tr.attr("bgcolor","#f2f2f2");
            oldTr = tr;
            $("#g_id").val(data.id);
            $("#g_materialCode").val(data.materialCode);
            $("#g_sncode").val(data.sncode);
            $("#g_sheetDetailId").val(data.sheetDetailId);
            $("#stockDetail").show();
            reloadstock(data);
            detailData =data;
        }
    });
    //����������ϸ
    form.on("submit(formSubmit)", function (data) {
        table.reload('dbckdetailsTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                code: $("#code").val()
                , materialCode: $("#materialCode").val()
                , description: $("#description").val()
            }
        });
        return false;
    });
    //��ϸ�б�
    var cldetailsGrid = table.render({
        elem: '#cldetails'
        , url: '/sheet/ck/listCldetails.json'
        , cellMinWidth: 80
        , height: "180"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20 ,30]  //���ݷ�ҳ��
        , id: "cldetailsTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'detailCount', title: '��������', align: "center", width: 120, edit: 'text'}
                , {field: 'storecount', title: '�������', align: "center", width: 100}
                , {
                field: 'createDate', title: '���ʱ��', align: "center", width: 120, templet: function (d) {
                    return vipTable.dateformat(d.createDate);
                }
            }
                , {field: 'storelocationname', title: '�ⷿ', align: "center", width: 150}
                , {field: 'storelocationareaname', title: '��λ', align: "center", width: 150}
                , {field: 'providername', title: '��Ӧ��', align: "center", width: 200}
            ]
        ]
    });
    var reloadstock =function(data){
        table.reload('cldetailsTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                materialCode: data.materialCode,
                // ztId: parent.layui.$("#ztId").val()
            }
        })
    };
    //�������add
    $("#add").on("click", function (e)  {
        var rows = table.checkStatus('cldetailsTable').data;
        var dbdetails = [];
        var slsum = 0;
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
                } else if (count > rows[i].storecount) {
                    layer.msg("�����������ܴ��ڿ������,��������д����������");
                    return;
                }
                var slobj = {
                    materialBrand: rows[i].materialbrand,
                    materialModel: rows[i].materialModel,
                    materialSpecification: rows[i].materialspecification,
                    detailUnitName: rows[i].detailunit,
                    storeId: rows[i].storeId,
                    storeLocationId: rows[i].storelocationid,
                    storeLocationName: rows[i].storelocationareaname,
                    storeLocationCode: rows[i].storelocationcode,

                    sheetId: parent.layui.$("#id").val(),
                    sheetDetailId: $("#g_id").val(),
                    extendint2: rows[i].id,
                    categoryId: rows[i].categoryId,
                    materialId: rows[i].materialId,
                    materialCode: $("#g_materialCode").val(),
                    materialName: rows[i].materialName,
                    materialBrand: rows[i].materialbrand,
                    materialModel: rows[i].materialModel,
                    materialSpecification: rows[i].materialspecification,
                    detailUnitName: rows[i].detailunit,

                    storeId: rows[i].storeId,
                    storeLocationId: rows[i].storelocationid,
                    storeLocationName: rows[i].storelocationareaname,
                    storeLocationCode: rows[i].storelocationcode,
                    description: rows[i].description,
                    tagCode: rows[i].tagcode,
                    planDepartId: rows[i].plandepartid,

                    noTaxPrice: "0",
                    taxPrice: "0",
                    taxRate: "0",
                    providerDepId: rows[i].providerDepId,
                    extendstring1: $("#g_sncode").val(),   //�ƻ�����
                    /*sncode: rows[i].SNCODE,    //���к�*/
                    detailUnitName: rows[i].detailUnitName,
                    detailCount: count,
                    // extendint1: slmxid,
                    ownerType: rows[i].ownerType,
                    ZtId:  parent.layui.$("#ztId").val(),
                    extendInt5: $("#g_sheetDetailId").val()
                    // EnableSN: rows[i].ENABLESN
                };
                dbdetails.push(slobj);
            }
            console.log(dbdetails);
            $.ajax({
                url: "/sheet/ck/saveDetail.json",
                type: "post",
                data: {details: JSON.stringify(dbdetails)},
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