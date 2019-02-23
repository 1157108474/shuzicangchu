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
    var slckDetailGr = table.render({
        elem: '#slckdetails'
        , url: '/sheet/ck/listSQCKDetail.json'
        , cellMinWidth: 80
        , height: "full-150"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , where: {
            code: $("#code").val()
        }
        , id: "slckdetailsTable"
        , cols: [
            [{field: 'materialCode', title: '���ϱ���', align: "center", width: 180}
                , {field: 'allowCount', title: 'δ������', align: "center", width: 100}
                , {field: 'detailCount', title: '��������', align: "center", width: 100}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 100}
                , {field: 'code', title: '���ϵ���', align: "center", width: 150}
                , {field: 'name', title: 'ʹ�ò���', align: "center", width: 150}
                , {field: 'description', title: '��������', align: "center", width: 320}
            ]
        ]
        , trclick: function (data, tr) {
            if (oldTr != null) {
                oldTr.removeAttr("bgcolor", "#f2f2f2");
            }
            tr.attr("bgcolor", "#f2f2f2");
            oldTr = tr;
            $("#g_id").val(data.id);
            $("#g_materialCode").val(data.materialCode);
            $("#g_sncode").val(data.sncode);
            $("#g_sheetDetailId").val(data.sheetDetailId);
            $("#g_allowCount").val(data.allowCount);
            $("#stockDetail").show();

            reloadstock(data);
            detailData = data;
        }
    });
    //��ѯ�ƻ���ϸ
    form.on("submit(formSubmit)", function (data) {
        table.reload('slckdetailsTable', {
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
        , cellMinWidth: 60
        , height: "180"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "cldetailsTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'detailCount', title: '��������', align: "center", width: 120, edit: 'text',event: 'count'}
                , {field: 'unsecount', title: '�ɳ�������', align: "center", width: 100}
                , {field: 'detailUnitName', title: '������λ', align: "center", width: 80}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 150}
                , {field: 'materialName', title: '��������', align: "center", width: 150}
                , {field: 'materialspecification', title: '���Ϲ��', align: "center", width: 150}
                , {field: 'materialModel', title: '�����ͺ�', align: "center", width: 100}
                , {field: 'planName', title: '�ƻ�����', align: "center", width: 120}
                , {field: 'storelocationname', title: '�ⷿ', align: "center", width: 150}
                , {field: 'storelocationareaname', title: '��λ', align: "center", width: 120}
                , {field: 'providername', title: '��Ӧ��', align: "center", width: 220}
                , {
                field: 'createDate', title: '���ʱ��', align: "center", width: 120, templet: function (d) {
                    return vipTable.dateformat(d.createDate);
                }
            }
            ]
        ]
    });
    var reloadstock = function (data) {
        table.reload('cldetailsTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                materialCode: data.materialCode,

            }
        })
    };
    // ������Ԫ��༭
    table.on('edit(cldetails)', function (obj) {
        var data = obj.data; //�����е������������
        var field = obj.field; //��ǰ�༭���ֶ���
        var value = obj.value; //�õ��޸ĺ��ֵ

        if(check.isDot(value)){
            if(check.isAllowDecimal(data.detailUnitName)){
                layer.alert(data.detailUnitName+"��λ��������С��");
                data[field] = 0;
                return;
            }
        }
    });
    var bool = true;
    //�������add
    $("#add").on("click", function (e) {
        if (bool) {
            bool = false;
            var rows = table.checkStatus('cldetailsTable').data;
            var sldetails = [];
            var slsum = 0;
            parent.layui.$("#reloadDetailsgrid").val(1);

            var allowCount = $("#g_allowCount").val();
            if (rows.length > 0) {
                var ckTotalCount = 0.00;
                for (var i = 0; i < rows.length; i++) {
                    var count = rows[i].detailCount;
                    if (null == count || count == "") {
                        layer.msg('��������Ϊ��,����д����������');
                        bool = true;
                        return;
                    } else if (isNaN(count) || count == '0' || count <= 0) {
                        layer.msg('��������������Ч����,����д��д����������');
                        bool = true;
                        return;
                    } else if (count > rows[i].unsecount) {
                        layer.msg('�����������ܴ��ڿɳ�������,��������д����������');
                        bool = true;
                        return;
                    }
                    ckTotalCount += count * 1;
                    if (ckTotalCount > allowCount) {
                        layer.msg('�����������ܴ���δ������,��������д����������');
                        bool = true;
                        return;
                    }
                    var slobj = {
                        sheetId: parent.layui.$("#id").val(),
                        sheetDetailId: $("#g_id").val(),
                        extendint2: rows[i].id,
                        categoryId: rows[i].categoryid,
                        materialId: rows[i].materialid,
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
                        providerDepId: rows[i].providerdepid,
                        extendstring1: $("#g_sncode").val(),   //�ƻ�����
                        /*sncode: rows[i].SNCODE,    //���к�*/
                        detailUnitName: rows[i].detailUnitName,
                        detailCount: count,
                        // extendint1: slmxid,
                        ownerType: rows[i].ownerType,
                        ztId: parent.layui.$("#ztId").val(),
                        extendInt5: $("#g_sheetDetailId").val()
                        // EnableSN: rows[i].ENABLESN
                    };
                    sldetails.push(slobj);
                }
                $.ajax({
                    url: "/sheet/ck/saveDetail.json",
                    type: "post",
                    data: {details: JSON.stringify(sldetails)},
                    success: function (ret) {
                        if (ret.status == '1') {
                            layer.msg('��ӳɹ�', function () {
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            });
                        } else {
                            layer.alert('���ʧ�ܣ�' + ret.message);
                            bool = true;
                        }
                    }
                });
            } else {
                layer.msg("��ѡ��һ����ϸ", {offset: 't', anim: 6});
                bool = true;
            }
        } else {
            layer.alert("���ύ�������ظ��ύ��");
            bool = true;
        }
    });
});

