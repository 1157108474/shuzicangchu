layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var vipTable = layui.vip_table;
    //明细列表
    var planDetailGr = table.render({
        elem: '#zjckdetails'
        , url: '/sheet/ck/listCldetails.json'
        , cellMinWidth: 80
        , height: "full-50"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "zjckdetailsTable"
        , ztId: parent.layui.$("#ztId").val()
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'detailCount', title: '出库数量', align: "center", width: 120, edit: 'text'}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 100}
                , {field: 'storecount', title: '库存数量', align: "center", width: 100}
                , {field: 'unsecount', title: '库存可用数量', align: "center", width: 120}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 180}
                , {field: 'ownerTypeName', title: '物资类型', align: "center", width: 100}
                , {field: 'storelocationname', title: '库房', align: "center", width: 100}
                , {field: 'storelocationareaname', title: '库位', align: "center", width: 100}
                , {field: 'providername', title: '供应商', align: "center", width: 180}
                , {
                field: 'createDate', title: '入库时间', align: "center", width: 170, templet: function (d) {
                    return vipTable.datetimeformat(d.createDate);
                }
            }
                , {field: 'materialName', title: '物料描述', align: "center", width: 320}

            ]
        ], done: function (res, curr, count) {
            layer.tips('点击输入出库数量', '.laytable-cell-1-detailCount', {
                time: 2000
            });
        }
    });
    
    //查询计划明细
    form.on("submit(formSubmit)", function (data) {
        table.reload('zjckdetailsTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                materialCode: $("#materialCode").val()
                , storelocationname: $("#storelocationname").val()
            }
        });
        return false;
    });


    //直接出库add
    $("#add").on("click", function (e) {
        var rows = table.checkStatus('zjckdetailsTable').data;
        var zjdetails = [];
        parent.layui.$("#reloadDetailsgrid").val(1);
        if (rows.length > 0) {
            for (var i = 0; i < rows.length; i++) {
                var count = rows[i].detailCount;
                if (isNaN(count) || count == '0' || count <= 0) {
                    layer.msg("出库数量不是有效数字,请重写填写出库数量！");
                    return;
                } else if (null == count || count == "") {
                    layer.msg("出库数量为空,请填写出库数量！");
                    return;
                } else if (count > rows[i].unsecount) {
                    layer.msg("出库数量不能大于库存可用数量,请重新填写出库数量！");
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
                    extendstring1: $("#g_sncode").val(),   //计划单号
                    /*sncode: rows[i].SNCODE,    //序列号*/
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
                        layer.msg('添加成功', function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert('添加失败：' + ret.message);
                    }
                }
            });
        } else {
            layer.msg("请选择一条明细");
        }
    });
});


