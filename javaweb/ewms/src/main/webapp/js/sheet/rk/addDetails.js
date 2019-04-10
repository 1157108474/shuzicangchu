layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var ran = Math.random();

    //明细列表
    var detailGr = table.render({
        elem: '#detailGrid'
        , url: 'listDetails.json'
        , cellMinWidth: 80
        , height: "full-80"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , where: {ran: ran++, jsCode: $("#num").val()}
        , id: "detailGridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'rkCount', title: '入库数量', align: "center", width: 100, edit: 'text', event: 'countCheck'}
                , {field: 'wareHouseName', title: '库房', align: "center", width: 100, edit: 'text', event: 'wareHouse',templet: function (d) {
                    if(d.jsType=='寄售'){
                        return '寄售库';
                    }else {
                        return '';
                    }
            }}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 156}
                , {field: 'description', title: '物料描述', align: "center", width: 170}
                , {field: 'jsCount', title: '接收数量', align: "center", width: 120}
                , {
                field: 'kyCount', title: '可入库数量', align: "center", width: 120, templet: function (d) {
                    return d.jsCount - d.isCount;
                }
            }
                , {field: 'jsType', title: '是否寄售', align: "center", width: 100}
                , {
                field: 'isEquipment',
                title: '是否设备',
                align: "center",
                width: 100,
                unresize: true,
                templet: function (d) {
                    if (d.isEquipment == '1') {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            }
                , {
                field: 'enableSn', title: '启用序列号', align: "center", width: 120, unresize: true, templet: function (d) {
                    if (d.enableSn == '1') {
                        return "启用";
                    } else {
                        return "不启用";
                    }
                }
            }
                , {field: 'detailUnitName', title: '单位', align: "center", width: 120}
                , {field: 'planDepName', title: '计划部门', align: "center", width: 170}
                , {field: 'expirationTime', title: '质保日期', align: "center", width: 170}
                , {field: 'extendDate2', title: '生产日期', align: "center", width: 170}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('点击此处录入入库数量！', '.laytable-cell-1-rkCount ', {
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

    //监听工具条
    table.on('tool(detailGrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'wareHouse') {
            layer.open({
                title: "库房"
                , type: 2
                , fixed: false
                , area: ['580px', '400px']
                , content: "/system/ware/locationWare"
                , end: function () {
                    obj.update({
                        wareHouseName: $("#newlocationName").val(),
                        wareHouseCode: $("#newlocationCode").val()
                    });
                }
            })
        }

    });
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });


    // 监听单元格编辑
    table.on('edit(detailGrid)', function (obj) {
        var data = obj.data; //所在行的所有相关数据
        var field = obj.field; //当前编辑的字段名
        var value = obj.value; //得到修改后的值

        var kCount = data.jsCount - data.isCount;

        if (value > kCount) {
            data.jscount = 0;
            layer.alert("可入库数量是:" + kCount + ",入库数量不能大于可入库数量,请重新填写入库数量!");
            return;
        }
        if (value > data.jsCount) {
            layer.alert("入库数量不能大于接收数量,请重新填写入库数量！");
            data[field] = 0;
            return;
        }
        if (!/^[0-9]+.?[0-9]*$/.test(value) || value == "0") {
            layer.alert("入库数量不是有效数字,请重新填写入库数量!");
            data[field] = 0;
            return;
        }
        if (value == "" || value == null) {
            layer.alert("入库数量为空,请填写入库数量!");
            data[field] = 0;
            return;
        }
        if(check.isDot(value)){
            if(!check.isAllowDecimal(data.detailUnitName)){
            	if("米"!=data.detailUnitName && "吨"!=data.detailUnitName && "公斤"!=data.detailUnitName && "升"!=data.detailUnitName){
            		layer.alert(data.detailUnitName+"单位不允许填小数");
                    data[field] = 0;
                    return;
            	}
            }
        }
    });

    var bool = true;
    // 添加明细
    $("#add").on("click", function (e) {
        if (bool) {
            bool = false;
            parent.layui.$("#reloadStatus").val(1);
            var checkStatus = table.checkStatus('detailGridTable');
            var length = checkStatus.data.length;
            if (length < 1) {
                //正上方
                layer.msg('请至少选择一条记录添加');
                bool = true;
            } else {
                var data = checkStatus.data;
                var details = [];
                var kfcode;
                for (var i = 0; i < length; i++) {
                    console.log(data[i]);
                    if (data[i].rkCount > data[i].kyCount) {
                        data[i].rkCount = 0;
                        layer.alert("可入库数量是:" + data[i].kyCount + ",入库数量不能大于可入库数量,请重新填写入库数量!");
                        bool = true;
                        return false;
                    }
                    if (data[i].rkCount > data[i].jsCount) {
                        layer.alert("入库数量不能大于接收数量,请重新填写入库数量！");
                        data[i].rkCount = 0;
                        bool = true;
                        return false;
                    }
                    if (!/^[0-9]+.?[0-9]*$/.test(data[i].rkCount) || data[i].rkCount == "0") {
                        layer.alert("入库数量不是有效数字,请重新填写入库数量!");
                        data[i].rkCount = 0;
                        bool = true;
                        return false;
                    }
                    if(data[i].jsType !='寄售'){
                        if (data[i].rkCount == "" || data[i].rkCount == null) {
                            layer.alert("入库数量为空,请填写入库数量!");
                            data[i].rkCount = 0;
                            bool = true;
                            return false;
                        }
                        if (data[i].wareHouseCode == "" || data[i].wareHouseCode == null) {
                            layer.alert("请选择库房！");
                            bool = true;
                            return false;
                        }
                    }

                    if (data[i].isEquipment == "1") {
                        if (!(/^(\+|-)?\d+$/.test(data[i].rkCount))) {
                            layer.alert("设备的入库数量必须是正整数,请重新填写正确的数量！");
                            data[i].rkCount = 0;
                            bool = true;
                            return false;
                        }
                    }

                    /*if (data[i].jsTypeCode == "IsJS") {
                        kfcode = "MDXNY02";
                    } else if (data[i].jsTypeCode == "NoJs") {
                        if (data[i].isEquipment == 1) {
                            kfcode = "MDXNY01";
                        } else {
                            kfcode = "MD159AK";
                        }
                    } else if (data[i].jsTypeCode == "ZSP") {
                        kfcode = data[i].extendString1;
                    }*/
                    var obj = {
                        sheetid: parent.layui.$("#id").val(),
                        sheetdetailid: data[i].id,
                        categoryid: data[i].categoryId,
                        materialid: data[i].materialId,
                        materialcode: data[i].materialCode,
                        materialname: data[i].materialName,
                        materialbrand: data[i].materialBrand,
                        materialmodel: data[i].materialModel,
                        materialspecification: data[i].materialSpecification,
                        detailunitname: data[i].detailUnitName,
                        notaxprice: data[i].notaxPrice,
                        notaxsum: data[i].notaxPrice.mul(data[i].rkCount),
                        taxprice: data[i].taxprice,
                        taxsum: data[i].notaxPrice.mul(1 + data[i].taxRate).mul(data[i].rkCount),
                        taxRate: data[i].taxRate,
                        description: data[i].description,
                        detailcount: data[i].rkCount,
                        isequipment: data[i].isEquipment,
                        enablesn: data[i].enableSn,
                        ztid: data[i].ztId,
                        ownertype: data[i].ownerType,
                        extendstring1: data[i].wareHouseCode,
                        extendint1: data[i].sheetDetailId,
                        extendfloat1: data[i].notaxPrice.mul(1 + data[i].taxRate),
                        providerdepid: data[i].providerDepId,
                        plandepartid: data[i].planDepartId,          //计划部门
                        extenddate2: data[i].extendDate2,              //生产时间
                        extendstring10: data[i].erpRowNum,           //erp行号
                        expirationtime: data[i].expirationTime      //质保时间
                    };
                    details.push(obj);
                }
                $.ajax({
                    type: "POST",
                    url: "../rkDetail/addRKDetails",
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
                            bool = true;
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                        bool = true;
                    }
                });
            }
            return false;
        } else {
            layer.alert("已提交不允许重复提交！");
            bool = true;
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


