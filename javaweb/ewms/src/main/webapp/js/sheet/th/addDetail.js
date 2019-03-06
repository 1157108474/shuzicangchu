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
    //明细列表
    var detailGrid = table.render({
        elem: '#detailGrid'
        , url: 'addDetails?sheetId=' + parent.layui.$("#extendInt1").val()
        , cellMinWidth: 80
        , height: "full-220"
        , method: "post"
        , page: true   //开启分页
        , limit: 5   //默认十五条数据一页
        , limits: [5, 10, 15]  //数据分页条
        , id: "detailGridReload"
        , cols: [
            [   //{type: "checkbox", fixed: "left", width: 50},
                {field: 'materialCode', title: '物料编码', align: "center", width: 130}
                , {field: 'description', title: '物料描述', align: "center", width: 200}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 50}
                , {field: 'sheetCode', title: '入库单号', align: "center", width: 140}
                , {field: 'orderNum', title: '采购订单', align: "center", width: 160}
                , {field: 'extendString1', title: '库房', align: "center", width: 100}
                , {field: 'subDetailCount', title: '入库数量', align: "center", width: 80}
                , {
                field: 'action', title: '可退货数量', align: "center", width: 90,
                templet: function (d) {
                    return d.subDetailCount - d.ytCount
                }
            }
                , {field: 'storeUsedCount', title: '库存可用量', align: "center", width: 90}
                , {field: 'snCode', title: '序列号', align: "center", width: 100}
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
                curr: 1 //重新从第 1 页开始
            },
            where: data.field
        })
    };

    //明细列表
    var detailstock = table.render({
        elem: '#detailstock'
        , url: 'getStockDetails'
        , cellMinWidth: 80
        , height: "full-180"
        , method: "post"
        , page: true   //开启分页
        , limit: 5   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailstockReload"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 30},
                {field: 'detailCount', title: '退货数量', align: "center", width: 120, edit: "text"}
                , {field: 'wareHouseCode', title: '库房', align: "center", width: 150}
                , {field: 'storeLocationCode', title: '库位', align: "center", width: 150}
                , {field: 'storeCount', title: '库存数量', align: "center", width: 120}
                , {field: 'snCode', title: '序列号', align: "center", width: 120}
                , {field: 'providerName', title: '供应商', align: "center", width: 200}

            ]
        ]
    });
    var reloadstock = function (data) {
        var provider = "";
        if (data.ownerType == "609") {//判断是否是寄售物资
            provider = data.providerDepId;
        }
        table.reload('detailstockReload', {
            page: {
                curr: 1 //重新从第 1 页开始
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
            //正上方
            layer.alert('请至少选择一条记录添加');
        } else {
            var data = checkStatus.data;
            var details = [];
            var obj;
            var count;
            var thTotalCount = 0.00;
            for (var i = 0; i < length; i++) {
                count = data[i].detailCount;
                if (count == null || count.trim() == '' || isNaN(count.trim())) {
                    layer.alert("退货数量不是有效数字,请重新填写退货数量！");
                    return false;
                }
                count = count.trim();
                thTotalCount += count * 1;
                if (count == 0) {
                    layer.alert("退货数量不得为0！");
                    return false;
                }
                if (count < 0) {
                    layer.alert("退货数量不得小于0！");
                    return false;
                }

                if (count > detailData.subDetailCount) {
                    layer.alert("退货数量必须小于入库数量,请重新填写退货数量！！");
                    return false;
                }

                if (count > detailData.subDetailCount - detailData.ytCount) {
                    var msg = "物料【" + detailData.MATERIALCODE + "】的可退货数量是：【" + (parseFloat(detailData.subDetailCount) - parseFloat(detailData.ytCount)) + "】！退货数量必须小于可退货数量,请重新填写退货数量！";
                    layer.alert(msg);
                    return false;
                }
                if (detailData.isEquipment == "1") {
                    if (!(/^(\+|-)?\d+$/.test(count))) {
                        layer.alert("设备的退货数量必须是正整数,请重新填写正确的数量！");
                        return;
                    }
                }
                if (thTotalCount > detailData.subDetailCount - detailData.ytCount) {
                    var msg = "退货数量必须小于可退货数量,请重新填写退货数量！";
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
                    extendInt8: detailData.sonId//入库行ID
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
                        layer.msg('添加成功', function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert('添加失败：' + ret.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            });
        }
        return false;
    });

});

