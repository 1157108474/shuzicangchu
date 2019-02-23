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
        , url: 'listJSDDetails.json'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailsgridTable"
        , where: {ran: ran++, sheetId: $("#id").val()}
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {title: '查看历史记录', align: "center", fixed: "left", toolbar: '#bar', width: 148}
                /*, {
                field: 'planDepartName', title: '计划部门', align: "center", width: 140, templet: function (d) {
                    if (d.planDepartId == 0 || d.planDepartId == null) {
                        return "未填写";
                    }
                }
            }*/
                , {field: 'materialCode', title: '物料编码', align: "center", width: 140}
                , {field: 'description', title: '物料描述', align: "center", width: 140}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 140}
                , {field: 'detailCount', title: '接收数量', align: "center", width: 120}
                , {field: 'thCount', title: '退货数量', align: "center", width: 120}
                , {field: 'ownerName', title: '是否寄售', align: "center", width: 120}
                , {
                field: 'isEquipment', title: '是否设备', align: "center", width: 120, templet: function (d) {
                    if (d.isEquipment == 1) {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            }
                , {
                field: 'enableSn', title: '是否启用序列号', align: "center", width: 145, templet: function (d) {
                    if (d.enableSn == 1) {
                        return "启用";
                    } else {
                        return "不启用";
                    }
                }
            }
                , {field: 'noTaxPriceDuble', title: '单价', align: "center", width: 120}

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


    //单据附件列表
    var filegr = table.render({
        elem: "#filegrid"
        , url: 'listFile.json'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , where: {ran: ran++, sheetId: $("#id").val()}
        , id: "filegridTable"
        , cols: [
            [
                {title: '操作', align: "center", toolbar: '#filebar', width: 100}
                , {field: 'fileName', title: '文件名', align: "center"}
                , {field: 'fileExt', title: '文件后缀', align: "center"}
                , {field: 'creatorName', title: '操作人', align: "center"}
                , {
                field: 'createDate', title: '操作时间', align: 'center', templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }
            ]]
    });

    var reloadFile = function () {
        table.reload('filegridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val()
            }

        })
    };


    //进入新增附件页面
    $("#importfiles").on("click", function (e) {
        $("#reloadStatus").val(0);
        var check = $("#id").val();
        if (check == "") {
            layer.alert("请先保存单据！");
        } else {

            layer.open({
                title: "上传附件"
                , type: 2
                , fixed: false
                , area: ["40%", "80%"]
                , content: "addFile.htm?sheetId=" + check
                , end: function () {
                    if ($("#reloadStatus").val() == 1) {
                        reloadFile();
                    }
                }
            });
        }
    });

    //打开填写计划页面
    $("#addPlanOther").on("click", function (e) {
        var checkStatus = table.checkStatus('detailsgridTable');
        var datas = checkStatus.data;
        var length = datas.length;
        if (length != 1) {
            layer.alert("请选择一条明细");
        } else {
            vipTable.openPage("填写计划", "addPlanOther.htm?sheetId=" + datas[0].id, "40%", "90%");
        }
    });

    //查看历史记录
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        if (obj.event == 'showLog') {
            vipTable.openPage("退货/接收历史记录", "orderHistory.htm?detailsGUID=" + data.guid, "60%", "70%");
        }
    });


    $("#order").on("click", function (e) {
        vipTable.openPage("采购订单列表", "/sheet/wzjs/generalOrder.htm", '90%', '90%');
        return false;

    });

    //保存按钮
    $("#saveSheet").on("click", function (e) {
        var result;
        //判断是否选中采购订单,如果没选中,不给于保存
        var check = $("#orderId").val();
        //判断是否存在单据编号,如果没有,执行保存,如果有,执行修改
        var check2 = $("#id").val();
        if (check == "") {
            layer.alert("请先填写订单编号！");
        } else {
            //如果单据ID不存在,add
            if (check2 == "") {
                result = saveSheet();
            } else {
                result = updateSheet();
            }

        }
    });

    //保存单据方法
    var saveSheet = function () {

        var loading = layer.msg("保存中", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });
        //获取单据table中所有信息,拼装JSON
        var orderTable = document.getElementById("orderTable");
        var orderTableValue = orderTable.getElementsByTagName("input");
        var param1 = "{";
        for (var i = 0; i < orderTableValue.length; i++) {
            param1 += "\"" + orderTableValue[i].name + "\":\"" + orderTableValue[i].value + "\",";
        }
        param1 = param1.substring(0, param1.length - 1);
        param1 += "}";
        var orderJson = JSON.parse(JSON.stringify(param1));
        $.ajax({
            type: "POST",
            url: "/sheet/WZJS",
            dataType: "json",
            data: JSON.parse(orderJson),
            success: function (ret) {
                if (ret.status == 1) {
                    //将单据ID和Code赋值到页面上
                    $("#id").val(ret.data.id);
                    $("#sheetcode").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    reloadHis();
                    $("#sheetStatus").val(39);
                    layer.close(loading);
                    layer.msg("保存单据成功！");
                } else {
                    layer.alert("保存失败，失败原因：" + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };

    //进入明细页面
    $("#addDetails").on("click", function (e) {
        var check = $("#orderId").val();
        var sheetId = $("#id").val();
        if (check == "") {
            layer.alert("请先填写订单编号！");
        } else {
            if (sheetId == "") {
                // 先判断当前单据是否已保存,如果未保存,先执行保存
                saveSheet();
                open();
            } else {
                // 打开明细页面
                open();
            }
        }
    });

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
                var url = "deleteSheetDetails.json?ids=" + arr;
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


    //新增明细页面
    var open = function () {
        var num = $("#ordernum").val();
        layer.open({
            title: "添加明细"
            , type: 2
            , fixed: false
            , area: ["90%", "80%"]
            , content: "addDetails.htm?orderNum=" + num
            , end: function () {
                if ($("#reloadStatus").val() == 1) {
                    updateSheet();
                    reload();
                }
            }
        });
    };

    //修改单据方法
    var updateSheet = function () {

        var loading = layer.msg("保存中", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });

        //获取单据table中所有信息,拼装JSON
        var orderTable = document.getElementById("orderTable");
        var orderTableValue = orderTable.getElementsByTagName("input");
        var param1 = "{";
        for (var i = 0; i < orderTableValue.length; i++) {
            param1 += "\"" + orderTableValue[i].name + "\":\"" + orderTableValue[i].value + "\",";
        }
        param1 = param1.substring(0, param1.length - 1);
        param1 += "}";
        var orderJson = JSON.parse(JSON.stringify(param1));

        $.ajax({
            type: "POST",
            url: "/sheet/wzjs/editSheet.json",
            dataType: "json",
            data: JSON.parse(orderJson),
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
    //打印
    $("#print").on("click", function (e) {
        var printType = $("#printType").val();
        if ($("#id").val() == "" || $("#id").val() == "0") {
            layer.alert("请先保存单据");
        } else if (printType == '') {
            layer.alert("请选择打印类型");
        } else {
            var url = "/system/print/sheet/WZJS-" + $("#id").val() + "?ordernum=" + $("#ordernum").val() + "&printType=" + printType;
            vipTable.openPage("打印接收单", url, '800px', '500px');
        }
    });
    $("#labelPrint").on("click", function (e) {
        var checkStatus = table.checkStatus('detailsgridTable');
        var datas = checkStatus.data;
        var length = datas.length;
        if (datas == "") {
            layer.alert("请至少选择一条明细");
        } else {
            layer.open({
                type: 2,
                title: '打印',
                fixed: false,
                area: ['60%', '60%'],
                content: "/system/print/sheet/BQDY-" + $("#id").val() + "?num=" + length,
                success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);
                    body.find('input').val(length);
                }
            });
        };
    });

});

function orderGeneral(data) {
    document.getElementById("orderId").value = data[0].id;
    document.getElementById("ordernum").value = data[0].ordernum;
    document.getElementById("ordertype").value = data[0].ordertype;
    document.getElementById("issuecode").value = data[0].issuecode;
    document.getElementById("providerdepid").value = data[0].providerdepid;
    document.getElementById("providerdepname").value = data[0].providerdepname;
    document.getElementById("stockorgname").value = data[0].stockorgname;
    document.getElementById("stockorgid").value = data[0].stockorgid;
    document.getElementById("extendString10").value = data[0].erpRowNum;
}
function deleteFile(id, path) {
    parent.layer.confirm('您确定要删除？', {
        icon: 3, title: '提示信息', offset: '35%',
        btn: ['确定', '取消'] //按钮
    }, function () {
        var url = "/sheet/wzjs/deleteFile.json?id=" + id + "&filePath=" + path;
        parent.layui.$.post(url, function (data) {
            if (data.status == 1) {
                parent.layer.msg('删除成功', {
                    time: 2000,
                    end: function () {
                        layui.table.reload('filegridTable');
                    }
                });

            } else {
                parent.layer.alert("删除失败:" + data.message, {
                    offset: '35%'
                });
            }
        })
    })
}

