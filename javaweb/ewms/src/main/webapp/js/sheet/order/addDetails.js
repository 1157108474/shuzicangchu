layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var ordernum = $("#ordernum").val();

    //明细列表
    var detailGr = table.render({
        elem: '#detailGrid'
        , url: 'listDetails.json?ordernum=' + ordernum
        , cellMinWidth: 80
        , height: "full-80"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailGridTable"
        , cols: [
            [
                {type: "checkbox", fixed: "left", width: 50}
                , {field: 'jscount', title: '接收数量', align: "center", width: 110, edit: 'text', event: 'countCheck'}
                , {field: 'baseunitcount', title: '采购数量', align: "center", width: 115}
                , {field: 'isCount', title: '可接收数量', align: "center", width: 110}
                , {field: 'jstype', title: '是否寄售', align: "center",width:110}
                , {
                field: 'isEquipment',
                title: '是否设备',
                align: "center",
                width: 100,
                templet: '#orEquipment',
                unresize: true
            }, {
                field: 'enableSn',
                title: '启用序列号',
                align: "center",
                width: 120,
                templet: '#orEnableSn',
                unresize: true
            }
                , {field: 'materialCode', title: '物料编码', align: "center", width: 130}
                , {field: 'description', title: '物料描述', align: "center", width: 120}
                , {field: 'detailunit', title: '单位', align: "center", width: 120}
                , {field: 'providerdepname', title: '供应商', align: "center", width: 220}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('点击此处录入接收数量！', '.laytable-cell-1-jscount ', {
                time: 2000
            });
        }
    });


    var reload = function (data) {

        table.reload('detailGridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: JSON.parse(JSON.stringify(data.field))
        });
    };

    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });


    // 监听单元格编辑
    table.on('edit(detailGrid)', function (obj) {
        var data = obj.data; //所在行的所有相关数据
        var field = obj.field; //当前编辑的字段名
        var value = obj.value; //得到修改后的值
        if (value > data.isCount) {
            layer.alert("可接收数量是:" + data.isCount + ",接收数量不能大于可接收数量,请重新填写接收数量!");
            return false;
        }
        if (value > data.baseunitcount) {
            layer.alert("接收数量不能大于采购数量,请重新填写接收数量!");
            return false;
        }
        if (!/^[0-9]+.?[0-9]*$/.test(value) || value == "0") {
            layer.alert("接收数量不是有效数字,请重新填写接收数量!");
            return false;
        }
        if (value == "" || value == null) {
            layer.alert("接收数量为空,请填写接收数量!");
            return false;
        }
        if(check.isDot(value)){
            if(!check.isAllowDecimal(data.detailunit)){
                layer.alert(data.detailunit+"单位不允许填小数");
                data[field] = 0;
                return;
            }
        }
    });
    var flag = true;
    // 添加明细
    $("#add").on("click", function (e) {
//    	alert(flag);
    	if(flag){
    		flag = false;
    		parent.layui.$("#reloadStatus").val(1);
            var checkStatus = table.checkStatus('detailGridTable');
            var length = checkStatus.data.length;
            if (length < 1) {
                //正上方
                layer.msg('请至少选择一条记录添加');
                flag = true;
            } else {
                var data = checkStatus.data;
                var details = [];
                for (var i = 0; i < length; i++) {

                    //提交前验证
                    if (data[i].jscount > data[i].isCount) {
                        layer.alert("接收数量不能大于可接收数量,请重新填写接收数量!");
                        flag = true;
                        return false;
                    }
                    if (isNaN(data[i].jscount || data[i].jscount == '0')) {
                        layer.alert("接收数量不是有效数字,请重新填写接收数量!");
                        flag = true;
                        return false;
                    }
                    if (data[i].jscount == "" || data[i].jscount == null) {
                        layer.alert("接收数量为空,请填写接收数量!");
                        flag = true;
                        return false;
                    }
                    if (!/^[0-9]+.?[0-9]*$/.test(data[i].jscount) || data[i].jscount == "0") {
                        layer.alert("接收数量不是有效数字,请重新填写接收数量!");
                        flag = true;
                        return false;
                    }

                    var obj = {
                        sheetId: parent.layui.$("#id").val(),
                        sheetDetailId: data[i].id,
                        categoryId: data[i].sparescateId,
                        materialId: data[i].materialId,
                        materialCode: data[i].materialCode,
                        materialName: data[i].materialName,
                        materialBrand: data[i].materialBrand,
                        materialModel: data[i].materialModel,
                        materialSpecification: data[i].materialSpecification,
                        description: data[i].description,
                        detailUnitName: data[i].baseunit,
                        noTaxPrice: data[i].baseunitprice,
                        noTaxSum: data[i].baseunitprice.mul(data[i].jscount),
                        taxPrice: data[i].baseunitprice.mul(1 + data[i].taxRate),
                        taxSum: data[i].baseunitprice.mul(1 + data[i].taxRate).mul(data[i].jscount),
                        taxRate: data[i].taxRate,
                        detailCount: data[i].jscount,
                        isEquipment: $("#isEquipment" + data[i].id).val(),
                        enableSn: $("#enableSn" + data[i].id).val(),
                        ztId: data[i].stockorgid,
                        ownerType: data[i].consignment,
                        providerDepId: data[i].providerdepid,
                        extendString10:data[i].erprownum,
                        extendString1: data[i].extendstring1
                    };
                    details.push(obj);
                }


                $.ajax({
                    type: "POST",
                    url: "../detail/addWZJSDetails",
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
    	}else{
//            flag = true;
            return false;
    	}
        

    });

    form.verify({
            sort: function (value, item) {
                if (value != '' && !/^[0-9]+.?[0-9]*$/.test(value)) {
                    return '请填写纯数字';
                }
            }
        }
    );

    //设备checkbox发生变化时，启动/禁用序列号checkbox
    form.on('checkbox(singleCheck)', function (obj) {
        // 获取当前checkbox是否为选中状态
        var isEq = obj.elem.checked;
        // 获取当前name
        var currentId = obj.elem.name;
        // 获取当前ID
        var checkId = obj.elem.id;
        if (isEq == true) {
            // isEq = true(选中状态)  解锁序列号
            $("#" + checkId).attr("value", 1);
            $("#enableSn" + currentId).removeAttr("disabled");
            form.render();
        } else {
            // isEq = false(未选中状态) 加锁序列号  并且取消已选择
            $("#" + checkId).attr("value", 0);
            $("#enableSn" + currentId).attr("disabled", !isEq);
            $("#enableSn" + currentId).removeAttr("checked");
            form.render();
        }
    });

    //启用序列号 点击事件
    form.on('checkbox(singCheck)', function (obj) {
        // 获取当前checkbox是否为选中状态
        var isSn = obj.elem.checked;
        // 获取当前ID
        var checkId = obj.elem.id;
        if (isSn == true) {
            $("#" + checkId).attr("value", 1);
            form.render();
        } else {
            $("#" + checkId).attr("value", 0);
            form.render();
        }
    })
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

Number.prototype.mul = function (arg) {
    return accMul(arg, this);
};


