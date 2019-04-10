layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;
    var ran = Math.random();

    //明细列表
    var detailsgr = table.render({
        elem: '#detailsgrid'
        , url: 'listRkDetails.json'
        , cellMinWidth: 90
        , height: "full-160"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , where: {ran: ran++, sheetId: $("#id").val()}
        , id: "detailsgridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                /*, {title: '操作', align: "center", fixed: "left", toolbar: '#bar', width: 120}*/
                , {field: 'materialCode', title: '物料编码', align: "center", width: 140}
                , {field: 'description', title: '物料描述', align: "center", width: 140}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 100}
                , {field: 'detailCount', title: '入库数量', align: "center", width: 110}
                , {field: 'subTotalCount', title: '分配数量', align: "center", width: 110}
                , {field: 'jsType', title: '是否寄售', align: "center", width: 110}
                , {field: 'enableSn', title: '是否启用序列号', align: "center", width: 160,templet:function(d){
                    if(d.enableSn == 1){
                        return "启用";
                    }else{
                        return "不启用"
                    }
            }}
                , {field: 'extendString1', title: '库房编码', align: "center", width: 120}
                , {field: 'warehouseName', title: '库房', align: "center", width: 120}
                /*, {field: 'notaxPrice', title: '不含税单价', align: "center", width: 120}
                , {field: 'notaxSum', title: '不含税金额', align: "center", width: 120}
                , {field: 'taxRate', title: '税率', align: "center", width: 120}
                , {field: 'extendFloat1', title: '价格合计', align: "center", width: 120}*/

            ]

        ]
    });

    var reload = function () {
        table.reload('detailsgridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val()
            }

        })
    };

    // 监听select事件 根据入库类型调用不同页面
    form.on('select(isopen)', function (data) {
        var selectValue = data.value;
        var check = $("#id").val();
        if (check != '' && check != null) {
            layer.alert("已保存单据，无法更改！");
            return;
        } else {
            if (selectValue == 789) {
                $("#receiveNum").val("");//接收单号
                $("#orderNum").val("");//采购订单编号
                $("#extendString3").val("");//订单类型
                $("#extendString2").val("");//库存组织名称
                $("#ztId").val("");//库存组织ID
                $("#extendString1").val("");//供应商名称
                $("#providerDepId").val("");//供应商ID
                $("#extendString5").val("");//发放号
                $("#extendInt1").val("");//接收单ID
                $("#extendInt2").val("");//调拨单ID
                $("#dbName").val("");//调拨单编码
                vipTable.openPage("物资入库单", "generalJSD.htm", "90%", "80%");
            }
            if (selectValue == 790) {
                $("#receiveNum").val("");//接收单号
                $("#orderNum").val("");//采购订单编号
                $("#extendString3").val("");//订单类型
                $("#extendString2").val("");//库存组织名称
                $("#ztId").val("");//库存组织ID
                $("#extendString1").val("");//供应商名称
                $("#providerDepId").val("");//供应商ID
                $("#extendString5").val("");//发放号
                $("#extendInt1").val("");//接收单ID
                $("#extendInt2").val("");//调拨单ID
                $("#dbName").val("");//调拨单编码
                var ztId = $("#userZtid").val() == "" ? "0" : $("#userZtid").val();
                vipTable.openPage("物资调拨单", "/sheet/db/dbd?type=rk&ztId=" + ztId, "60%", "80%");
            }
        }
    });

    //保存按钮
    $("#saveSheet").on("click", function (e) {
        var result;
        var typeId = $("#check").val();//入库类型
        var receiveNum = $("#receiveNum").val();//接收单号
        var dbNum = $("#extendInt2").val();//调拨单号
        var rkId = $("#id").val();//入库单据ID
        if (rkId == "" || rkId == null) {
            if (typeId == "") {
                layer.alert("请先选择入库类型！");
            } else {
                if (typeId == 789 && receiveNum == "") {
                    layer.alert("请选择接收单号！");
                } else if (typeId == "790" && dbNum == "") {
                    layer.alert("请选择调拨单号！");
                } else {
                    result = saveSheet();
                }
            }
        } else {
            result = updateSheet();
        }
    });


    //保存方法
    var saveSheet = function () {

        //调用Loading框
        var loading = layer.msg("保存中", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });

        var typeId = $("#check").val();
        if (typeId == "790") {
            //调拨入库时将ZTID设为人员的库存组织，供应商ID设置为0
            $("#ztId").val($("#userZtid").val());
            $("#providerdepid").val(0);
        }
        //获取单据table中所有信息,拼装JSON
        var rkTable = document.getElementById("rkTable");
        var rkTableValue = rkTable.getElementsByTagName("input");
        var param = "{";
        for (var i = 0; i < rkTableValue.length; i++) {
            param += "\"" + rkTableValue[i].name + "\":\"" + rkTableValue[i].value + "\",";
        }
        param += "\"typeId\":" + "\"" + typeId + "\"";
        param += "}";
        var rkSheetJson = JSON.parse(JSON.stringify(param));

        //发送请求
        $.ajax({
            type: "POST",
            url: "/sheet/rk/RK",
            dataType: "json",
            data: JSON.parse(rkSheetJson),
            success: function (ret) {
                if (ret.status == 1) {
                    $("#check").addClass(" layui-disabled");
                    $("#check").attr("disabled", true);
                    form.render('select');
                    //将单据ID和Code赋值到页面上
                    $("#id").val(ret.data.id);
                    $("#sheetcode").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    $("#sheetStatus").val(ret.data.status);
                    reloadHis();
                    layer.msg("保存单据成功！");
                    layer.close(loading);
                } else {
                    layer.alert("保存失败，失败原因：" + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });

    };

    //修改方法
    var updateSheet = function () {

        //调用Loading框
        var loading = layer.msg("保存中", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });

        var typeId = $("#check").val();

        //获取单据table中所有信息,拼装JSON
        var rkTable = document.getElementById("rkTable");
        var rkTableValue = rkTable.getElementsByTagName("input");
        var param = "{";
        for (var i = 0; i < rkTableValue.length; i++) {
            param += "\"" + rkTableValue[i].name + "\":\"" + rkTableValue[i].value + "\",";
        }
        param += "\"typeId\":" + "\"" + typeId + "\"";
        param += "}";
        var rkSheetJson = JSON.parse(JSON.stringify(param));

        //发送请求
        $.ajax({
            type: "POST",
            url: "/sheet/rk/editSheet.json",
            dataType: "json",
            data: JSON.parse(rkSheetJson),
            success: function (ret) {
                if (ret.status == 1) {
                    layer.close(loading);
                    return true;
                } else {
                    layer.alert("保存失败，失败原因：" + ret.message);
                    return false;
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });

    };


    //添加明细按钮
    $("#addDetails").on("click", function (e) {
        var check = $("#check").val();
        var check1 = $("#receiveNum").val();
        var check2 = $("#dbName").val();
        var sheetId = $("#id").val();
        if (check == "" || check == null) {
            //判断是否选中入库类型
            layer.alert("请选择入库类型！");
        } else if (check1 == "" && check2 == "") {
            //判断是否选中单据
            layer.alert("请选择单据编号！");
        } else if (check == "790" && check2 != "") {
            //判断是否选中调拨单号,如果已选中,判断是否已保存
            if (sheetId == "" || sheetId == null) {
                //如果未保存,执行保存
                layer.alert("请先保存单据！");
            } else {
                //如果已保存,直接加载调拨列表
                DBopen();
            }
        } else if (check == "789" && check1 != "") {
            //判断是否选中接收单号,如果已选中,判断是否已保存
            if (sheetId == "" || sheetId == null) {
                //如果未保存,执行保存
                saveSheet();
                JSopen();
            } else {
                JSopen();
            }
        }
    });

    //获取采购订单列表
    var order = true;
    $("#order").on("focus", function (e) {
        if (order) {
            order = false;
            var url = "/sheet/wzjs/generalJSD.htm";
            vipTable.openPage("采购订单列表", url, '680px', '400px');
        }
    }).on("blur", function (e) {
        order = true
    });

    //明细编辑功能
    table.on('tool(detailsgrid)', function (obj) {

        var data = obj.data;
        if (obj.event == 'edit') {
            layer.open({
                title: "编辑明细"
                , type: 2
                , fixed: false
                , area: ["90%", "90%"]
                , content: "getOriginalLocation.htm?rid=" + data.id+"&extendInt1="+$("#extendInt1").val()
                , end: function () {
                    reload();
                }
            });
        }

    });

    //新增明细页面
    var JSopen = function () {
        $("#reloadStatus").val(0);
        var num = $("#receiveNum").val();
        layer.open({
            title: "添加明细"
            , type: 2
            , fixed: false
            , area: ["90%", "80%"]
            , content: "addDetails.htm?jsCode=" + num
            , end: function () {
                if ($("#reloadStatus").val() == 1) {
                    reload();
                }
            }
        });
    };

    var DBopen = function () {

        $.ajax({
            type: "POST",
            url: "../rkDetail/addRKDetails?rid=" + $("#id").val() + "&sid=" + $("#extendInt2").val() + "&appFlag=1",
            dataType: "json",
            success: function (ret) {
                if (ret.status == '1') {
                    layer.msg('添加成功', function () {
                        layer.close(loading);
                        reload();
                    });
                } else {
                    layer.alert('添加失败：' + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });

        //调用Loading框
        var loading = layer.msg("保存中", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });

        $.ajax({
            type: "POST",
            url: "../rkDetail/addRKDetails?rid=" + $("#id").val() + "&sid=" + $("#extendInt2").val() + "&appFlag=1",
            dataType: "json",
            success: function (ret) {
                if (ret.status == '1') {
                    layer.msg('添加成功', function () {
                        layer.close(loading);
                        reload();
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

    // 删除明细按钮
    $("#deleteDetails").on("click", function (e) {
        var checkStatus = table.checkStatus('detailsgridTable');
        var datas = checkStatus.data;
        var arr = [];
        if (datas == "") {
            layer.msg("请选择一条记录进行删除", {
                offset: 't',
                anim: 6
            });
        } else {
            layer.confirm('您确定要删除？', {
                icon: 3, title: '提示信息', offset: '35%',
                btn: ['确定', '取消'] //按钮
            }, function () {
                for (var i = 0; i < datas.length; i++) {
                    arr.push(datas[i].id);
                }
                var url = "deleteSheetRkDetails.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('删除成功', {
                            time: 2000,
                            end: function () {
                                reload();
                            }
                        });
                    } else {
                        layer.alert(data.message, {
                            offset: '35%'
                        });
                    }
                })
            }, function () {
                layer.closeAll();
            });
        }
    });

    $("#print").on("click", function (e) {
    	var taskId = $("#taskId").val();
        var printType = $("#printType").val();
        var typeId = $("#check").val();
        if (typeId == "790") {
            vipTable.openPage("打印入库单", "/system/print/sheet/DBRK-" + $("#id").val() + "?printType=203&taskId="+taskId, '850px', '500px');
        } else {
            if (printType == '') {
                layer.alert("请选择打印类型");
            } else {
                vipTable.openPage("打印入库单", "/system/print/sheet/JSRK-" + $("#id").val() + "?printType=165&taskId="+taskId, '850px', '500px');
            }
        }
    });

});

function JSDGeneral(data) {
    document.getElementById("receiveNum").value = data[0].code;//接收单号
    document.getElementById("orderNum").value = data[0].ordernum;//采购订单编号
    document.getElementById("extendString3").value = data[0].extendstring3;//采购订单类型
    document.getElementById("extendString2").value = data[0].extendstring2;//库存组织名称
    document.getElementById("ztId").value = data[0].ztid;//库存组织ID
    document.getElementById("extendString1").value = data[0].extendstring1;//供应商名称
    document.getElementById("providerDepId").value = data[0].providerdepid;//供应商ID
    document.getElementById("extendString5").value = data[0].extendstring5;//发放号
    document.getElementById("extendInt1").value = data[0].id;//接收单ID（sheetId）
}

function DBDGeneral(data) {
    document.getElementById("extendInt2").value = data.id;//调拨单ID
    document.getElementById("dbName").value = data.code;//调拨单号
    document.getElementById("extendString2").value = data.intoOrgName;//库存组织名称
    document.getElementById("ztId").value = data.extendInt2;//库存组织ID
    document.getElementById("extendString1").value = "";//供应商名称
    document.getElementById("providerDepId").value = "0";//供应商ID
}

