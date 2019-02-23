layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var form = layui.form;
    var table = layui.table;
    var $ = layui.jquery;
    var laydate = layui.laydate;

    //获取物料信息列表
    $("#materialNameBtn").on("click", function () {
        var url = "/system/material/listMaterial";
        layui.layer.open({
            title: "物料信息"
            , type: 2
            , fixed: false
            , area: ['1000px', '360px']
            , content: url
        });
        return false;
    });


    // 保存明细
    $("#add").on("click", function (e) {
        if (Is_DetailValid()) {
            if ($("#sheetDetailId").val() == "") {
                saveSheetDetails();
            } else {
                updateSheetDetails();
            }
        }

        return false;
    });

    // 监听select事件
    form.on('select(isopen)', function (data) {
        var selectValue = data.value;
        if (selectValue == 1) {
            $("#enablesn").removeAttr("disabled");
            form.render();
        } else {
            $("#enablesn").attr("disabled", true);
            form.render();
        }
    });

    laydate.render({
        elem: '#expirationtime'
        , type: 'datetime'
    });

    laydate.render({
        elem: '#extenddate2'
        , type: 'datetime'
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
        var count = $("#detailCount").val();
        if (count == null || count.trim() == '' ) {
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
        var taxRate = $("#taxRate").val();
        if (taxRate == null || taxRate.trim() == '' || isNaN(taxRate)) {
            layer.alert("数量不能为空,请重新填写数量！");
            return;
        } else {
            if (!(/0\.[0-9]+/).test(taxRate)) {
                parent.layer.alert("税率不得大于1小于0，请输入两位小数！");
                return;
            } else {
                is_ok = true;
            }
        }
        var price = $("#noTaxPrice").val();
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
        var isEquipment = $("#isequipment").val();
        if (isEquipment == null || isEquipment.trim() == '') {
            layer.alert("请选择是否设备！");
            return;
        } else {
            is_ok = true;
        }
        var enablesn = $("#enablesn").val();
        /*if (enablesn == null || enablesn.trim() == '') {
            layer.alert("请选择是否启用序列号！");
            return;
        }*/
        if ($("#storeId").val() == "") {
            layer.alert("请选择库房！", "提醒");
            return;
        } else {
            is_ok = true;
        }

        var expirationtime = $("#expirationtime").val();
        if (expirationtime == null || expirationtime.trim() == '') {
            layer.alert("请选择保质期限！");
            return;
        } else {
            is_ok = true;
        }
        var extenddate2 = $("#extenddate2").val();
        if (extenddate2 == null || extenddate2.trim() == '') {
            layer.alert("请选择生产时间！");
            return;
        } else {
            is_ok = true;
        }
        var extendint7 = $("#plandepartid").val();
        if (extendint7 == null || extendint7.trim() == '') {
            layer.alert("请选择计划部门！");
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
        var details = [];
        var noTaxPrice = parseFloat($("#noTaxPrice").val());
        var taxRate = parseFloat($("#taxRate").val());
        var count = parseFloat($("#detailCount").val());
        var ztId = parent.layui.$("#userZtid").val();
        var obj;
        obj = {
            sheetid: sheetId,
            materialid: $("#materialId").val(),
            categoryid: $("#sparescateId").val(),
            materialcode: $("#materialCode").val(),
            materialname: $("#materialName").val(),
            materialbrand: $("#brand").val(),
            materialmodel: $("#model").val(),
            materialspecification: $("#specifications").val(),
            detailcount: $("#detailCount").val(),
            detailunitname: $("#detailUnitName").val(),
            notaxprice: $("#noTaxPrice").val(),
            notaxsum: $("#noTaxSum").val(),
            taxprice: 0,
            taxRate: $("#taxRate").val(),
            taxsum: noTaxPrice.mul(1 + taxRate).mul(count),
            ztid: ztId,
            ownertype: 610,
            description: $("#description").val(),
            isequipment: $("#isequipment").val(),
            enablesn: $("#enablesn").val(),
            extendstring1: $("#storeId").val(),
            extendfloat1: noTaxPrice.mul(1 + taxRate),
            providerdepid: $("#providerId").val(),
            plandepartid: $("#plandepartid").val(),
            expirationtime: $("#expirationtime").val(),
            extenddate2: $("#extenddate2").val()
        };
        details.push(obj);
        console.log(details);
        $.ajax({
            type: "POST",
            url: "../rkDetail/addZRDetails",
            dataType: "json",
            data: {details: JSON.stringify(details)},
            success: function (ret) {
                if (ret.status == '1') {
                    layer.msg('添加成功', function () {
                        parent.layui.$("#reloadStatus").val(1);
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
    document.getElementById("sparescateId").value = data.sparescateId;
    document.getElementById("brand").value = data.brand;
    document.getElementById("specifications").value = data.specifications;
    /*document.getElementById("providerId").value = data.providerId;*/
}

function sumAmount(value) {
    if (!(/^(?:[1-9]+\d*|0)(\.\d+)?$/).test(value)) {
        parent.layer.alert("只能输入正整数和小数！");
    }
    var detailcount = $("#detailCount").val();
    var notaxprice = $("#noTaxPrice").val();
    var mn = 0;
    if (detailcount != "" && notaxprice != "") {
        mn = parseFloat(detailcount) * parseFloat(notaxprice);
    }
    $("#noTaxSum").val(mn.toFixed(2));
}

function rateAmount(value) {
    if (!(/0\.[0-9]+/).test(value)) {
        parent.layer.alert("税率不得大于1小于0，请输入两位小数！");
    }
}

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

Number.prototype.mul = function (arg) {
    return accMul(arg, this);
};