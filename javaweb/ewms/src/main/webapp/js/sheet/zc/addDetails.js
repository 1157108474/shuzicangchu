layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;

    //获取物料信息列表
    $("#materialNameBtn").on("click", function () {
        var url = "/system/material/listMaterial";
        layui.layer.open({
            title: "物料信息"
            , type: 2
            , fixed: false
            , area: ['1000px', '420px']
            , content: url
        });
        return false;
    });
    //库房
    var before = '';
    form.on('select(store)', function (store) {
        var current = store.value;
        if (current != before) {
            before = current;
            $("#locationName").val('');
            $("#locationId").val('');
            $("#locationCode").val('');
        }
    });
    //根据库房信息加载库位
    $("#storeLocation").on("click", function (e) {
        if ($("#storeId").val() == "") {
            layer.alert("请先选择库房");
        } else {
            var url = "/system/ware/location?parentId=" + before;
            layui.layer.open({
                title: "库位"
                , type: 2
                , fixed: false
                , area: ['90%', '90%']
                , content: url
            });
        }
        return false;
    });

    // 保存明细
    $("#add").on("click", function (e) {
        if (Is_DetailValid()) {
            if ($("#sheetDetailId").val() == ""){
                saveSheetDetails();
            }else {
                updateSheetDetails();
            }
        }

        return false;
    });

    //验证明细必填项
    function Is_DetailValid() {
        var is_ok = false;
        if ($("#materialName").val() == "") {
            layer.alert("物料名称不能为空,请选择物料！", "提醒");
            return;
        } else {
            is_ok = true;
        }
        if ($("#materialCode").val() == "") {
            layer.alert("物料编码不能为空！", "提醒");
            return;
        } else {
            is_ok = true;
        }
        var count=$("#detailCount").val()
        if (count == null || count.trim() == '' || isNaN(count.trim())) {
            layer.alert("数量不能为空,请重新填写数量！");
            return;
        } else {
            if (count.trim() == 0) {
                layer.alert("数量不得为0！");
                return;
            } else {
                is_ok = true;
            }
        }
        if ($("#detailUnitName").val() == "") {
            layer.alert("请选择单位！", "提醒");
            return;
        } else {
            is_ok = true;
        }
        var price=$("#noTaxPrice").val()
        if (price == null || price.trim() == '' || isNaN(price.trim())) {
            layer.alert("不含税单价不能为空,请重新填写不含税单价！");
            return;
        } else {
            if (price.trim() == 0) {
                layer.alert("不含税单价不得为0！");
                return;
            } else {
                is_ok = true;
            }
        }
        if ($("#noTaxSum").val() == "") {
            layer.alert("不含税总金额不正确！", "提醒");
            return;
        } else {
            is_ok = true;
        }
        if ($("#storeId").val() == "") {
            layer.alert("请选择库房！", "提醒");
            return;
        } else {
            is_ok = true;
        }
        if ($("#locationId").val() == "") {
            layer.alert("请选择库位！", "提醒");
            return;
        } else {
            is_ok = true;
        }

        return is_ok;
    };
    //保存单据方法
    var saveSheetDetails = function () {
        var loading = layer.msg("保存中", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });
        var sheetId = parent.layui.$("#id").val();
        var userZtId = parent.layui.$("#userZtId").val();
        var details = [];
        var obj = {
            sheetId: sheetId,
            materialId: $("#materialId").val(),
            tagCode:$("#materialCode").val(),
            materialCode: $("#materialCode").val(),
            materialName: $("#materialName").val(),
            detailCount: $("#detailCount").val(),
            detailUnitName: $("#detailUnitName").val(),
            noTaxPrice: $("#noTaxPrice").val(),
            noTaxSum: $("#noTaxSum").val(),
            storeId: $("#storeId").val(),
            storeLocationId: $("#locationId").val(),
            storeLocationName:$("#locationName").val(),
            storeLocationCode:$("#locationCode").val(),
            description: $("#description").val(),
            ztId: userZtId
        };
        details.push(obj);
        $.ajax({
            type: "POST",
            url: "/sheet/detail/addWZZCDetails",
            dataType: "json",
            data: {details: JSON.stringify(details)},
            success: function (ret) {
                if (ret.status == '1') {
                    layer.msg('添加成功', function () {
                        parent.layui.$("#reloadDetailsgrid").val(1);
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
    };
    //编辑保存单据方法
    var updateSheetDetails = function () {
        var loading = layer.msg("保存中", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });
        var sheetId = parent.layui.$("#id").val();
        var userZtId = parent.layui.$("#userZtId").val();
        var details = [];
        var obj;
        var obj = {
            id:$("#sheetDetailId").val(),
            sheetId: sheetId,
            materialId: $("#materialId").val(),
            tagCode:$("#materialCode").val(),
            materialCode: $("#materialCode").val(),
            materialName: $("#materialName").val(),
            detailCount: $("#detailCount").val(),
            detailUnitName: $("#detailUnitName").val(),
            noTaxPrice: $("#noTaxPrice").val(),
            noTaxSum: $("#noTaxSum").val(),
            storeId: $("#storeId").val(),
            storeLocationId: $("#locationId").val(),
            storeLocationName:$("#locationName").val(),
            storeLocationCode:$("#locationCode").val(),
            description: $("#description").val(),
            ztId: userZtId
        };
        details.push(obj);
        $.ajax({
            type: "POST",
            url: "/sheet/zc/editDetail",
            dataType: "json",
            data: {details: JSON.stringify(details)},
            success: function (ret) {
                if (ret.status == 1) {
                    layer.msg('添加成功', function () {
                        parent.layui.$("#reloadStatus").val(1);
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else {
                    layer.alert("保存失败，失败原因：" + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };
    //取消按钮
    $("#reset").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

});

//获取部门页面回传参数
function setMaterialInfo(data) {
    document.getElementById("materialId").value = data.id;
    document.getElementById("materialName").value = data.name;
    document.getElementById("materialCode").value = data.code;
    document.getElementById("detailUnitName").value = data.unit;
    document.getElementById("description").value = data.description;
}
function sumAmount(){
    var detailcount =  $("#detailCount").val();
    var notaxprice = $("#noTaxPrice").val();
    var mn=0;
    if (detailcount!=""&&notaxprice!=""){
        mn = parseFloat(detailcount)*parseFloat(notaxprice);
    }
    $("#noTaxSum").val(mn.toFixed(2));
}