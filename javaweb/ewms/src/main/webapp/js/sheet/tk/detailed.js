layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {
    var table = layui.table;
    var $ = layui.$;
    var form = layui.form;

    form.on("submit(search)", function (data) {
        reload(data);
        return false;
    });

    var ztid = parent.layui.$("#ztId").val();

    //明细列表
    var detailGrid = table.render({
        elem: '#detailGrid'
        , url: 'getAddDetails'
        , where: {'sheetId': parent.layui.$("#extendInt1").val()}
        , cellMinWidth: 80
        , height: "full-98"
        , method: "post"
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailGridReload"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'tkCount', title: '退库数量', align: "center", width: 80, edit: "text",event: 'count'}
                , {field: 'storeLocationName', title: '目标库位', align: "center", width: 120}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 120}
                , {field: 'providerDepName', title: '供应商', align: "center", width: 200}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 60}
                , {field: 'code', title: '出库单号', align: "center", width: 150}
                , {field: 'detailCount', title: '出库数量', align: "center", width: 80}
                , {field: 'haveTkCount', title: '已退库数量', align: "center", width: 90}
                , {field: 'hasCj', title: '是否冲减', align: "center", width: 100}
            ]
        ], done: function (res, curr, count) {
            layer.tips('点击输入退货数量！', '.laytable-cell-1-tkCount  ', {
                time: 2000
            });

        }
    });
    // 监听单元格编辑
    table.on('edit(detailGrid)', function (obj) {
        var data = obj.data; //所在行的所有相关数据
        var field = obj.field; //当前编辑的字段名
        var value = obj.value; //得到修改后的值

        if(check.isDot(value)){
            if(!check.isAllowDecimal(data.detailUnitName)){
                layer.alert(data.detailUnitName+"单位不允许填小数");
                data[field] = 0;
                return;
            }
        }
    });
    var reload = function (data) {

        table.reload('detailGridReload', {
            page: {
                curr: 1 //重新从第 1 页开始
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
            //正上方
            layer.alert('请至少选择一条记录添加');
        } else {
            var data = checkStatus.data;
            var details = [];
            var count;
            for (var i = 0; i < length; i++) {
                if (data[i].hasCj == "是") {
                    layer.alert("有出库明细已冲减不能添加！");
                    return false;
                }
                count = data[i].tkCount;
                if (count == null || count.trim() == '' || isNaN(count.trim())) {
                    layer.alert("退库数量不是有效数字,请重新填写退库数量！");
                    return false;
                }
                count = count.trim();
                if (count == 0) {
                    layer.alert("退库数量不得为0！");
                    return false;
                }
                if (count < 0) {
                    layer.alert("退库数量不得小于0！");
                    return false;
                }
                if (count > data[i].detailCount) {
                    layer.alert("退库数量不能大于出库数量,请重新填写退库数量！");
                    return false;
                }
                var detailCount = data[i].detailCount - data[i].haveTkCount;
                if (count > detailCount) {
                    console.log(count > detailCount);
                    layer.alert("退库数量不能大于可退库数量(出库数量-已退库数量),请重新填写退库数量！");
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
    //乘法函数，用来得到精确的乘法结果
    //说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
    //调用：accMul(arg1,arg2)
    //返回值：arg1乘以arg2的精确结果
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
