layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var vipTable = layui.vip_table;
    var detailData;
    var oldTr;
    //明细列表
    var slckDetailGr = table.render({
        elem: '#slckdetails'
        , url: '/sheet/ck/listSQCKDetail.json'
        , cellMinWidth: 80
        , height: "full-150"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , where: {
            code: $("#code").val()
        }
        , id: "slckdetailsTable"
        , cols: [
            [{field: 'materialCode', title: '物料编码', align: "center", width: 180}
                , {field: 'allowCount', title: '未出库量', align: "center", width: 100}
                , {field: 'detailCount', title: '申领数量', align: "center", width: 100}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 100}
                , {field: 'code', title: '领料单号', align: "center", width: 150}
                , {field: 'name', title: '使用部门', align: "center", width: 150}
                , {field: 'description', title: '物料描述', align: "center", width: 320}
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
    //查询计划明细
    form.on("submit(formSubmit)", function (data) {
        table.reload('slckdetailsTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                code: $("#code").val()
                , materialCode: $("#materialCode").val()
                , description: $("#description").val()
            }
        });
        return false;
    });

    //明细列表
    var cldetailsGrid = table.render({
        elem: '#cldetails'
        , url: '/sheet/ck/listCldetails.json'
        , cellMinWidth: 60
        , height: "180"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "cldetailsTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'detailCount', title: '出库数量', align: "center", width: 120, edit: 'text',event: 'count'}
                , {field: 'unsecount', title: '可出库数量', align: "center", width: 100}
                , {field: 'detailUnitName', title: '计量单位', align: "center", width: 80}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 150}
                , {field: 'materialName', title: '物料名称', align: "center", width: 150}
                , {field: 'materialspecification', title: '物料规格', align: "center", width: 150}
                , {field: 'materialModel', title: '物料型号', align: "center", width: 100}
                , {field: 'planName', title: '计划部门', align: "center", width: 120}
                , {field: 'storelocationname', title: '库房', align: "center", width: 150}
                , {field: 'storelocationareaname', title: '库位', align: "center", width: 120}
                , {field: 'providername', title: '供应商', align: "center", width: 220}
                , {
                field: 'createDate', title: '入库时间', align: "center", width: 120, templet: function (d) {
                    return vipTable.dateformat(d.createDate);
                }
            }
            ]
        ]
    });
    var reloadstock = function (data) {
        table.reload('cldetailsTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                materialCode: data.materialCode,

            }
        })
    };
    // 监听单元格编辑
    table.on('edit(cldetails)', function (obj) {
        var data = obj.data; //所在行的所有相关数据
        var field = obj.field; //当前编辑的字段名
        var value = obj.value; //得到修改后的值

        if(check.isDot(value)){
            if(check.isAllowDecimal(data.detailUnitName)){
                layer.alert(data.detailUnitName+"单位不允许填小数");
                data[field] = 0;
                return;
            }
        }
    });
    var bool = true;
    //申领出库add
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
                        layer.msg('出库数量为空,请填写出库数量！');
                        bool = true;
                        return;
                    } else if (isNaN(count) || count == '0' || count <= 0) {
                        layer.msg('出库数量不是有效数字,请重写填写出库数量！');
                        bool = true;
                        return;
                    } else if (count > rows[i].unsecount) {
                        layer.msg('出库数量不能大于可出库数量,请重新填写出库数量！');
                        bool = true;
                        return;
                    }
                    ckTotalCount += count * 1;
                    if (ckTotalCount > allowCount) {
                        layer.msg('出库数量不能大于未出库量,请重新填写出库数量！');
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
                        extendstring1: $("#g_sncode").val(),   //计划单号
                        /*sncode: rows[i].SNCODE,    //序列号*/
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
                            layer.msg('添加成功', function () {
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            });
                        } else {
                            layer.alert('添加失败：' + ret.message);
                            bool = true;
                        }
                    }
                });
            } else {
                layer.msg("请选择一条明细", {offset: 't', anim: 6});
                bool = true;
            }
        } else {
            layer.alert("已提交不允许重复提交！");
            bool = true;
        }
    });
});

