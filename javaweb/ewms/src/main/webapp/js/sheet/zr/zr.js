layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'upload','vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var upload = layui.upload;
    var element = layui.element;
    var ran = Math.random();

    //明细列表
    var detailsgr = table.render({
        elem: '#detailsgrid'
        , url: 'listZrDetails.json'
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
                , {title: '操作', align: "center", fixed: "left", toolbar: '#bar', width: 100}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 140}
                , {field: 'description', title: '物料描述', align: "center", width: 120}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 80}
                , {field: 'detailCount', title: '入库数量', align: "center", width: 90}
                , {field: 'subTotalCount', title: '分配数量', align: "center", width: 90}
                , {field: 'jsType', title: '是否寄售', align: "center", width: 90}
                , {field: 'enableSn', title: '是否启用序列号', align: "center", width: 140}
                /*, {field: 'extendString1', title: '库房', align: "center", width: 100}*/
                , {field: 'name', title: '库房', align: "center", width: 100}
                , {field: 'locationName', title: '库位', align: "center", width: 100}
                , {field: 'notaxPriceDouble', title: '不含税单价', align: "center", width: 100}
                , {field: 'notaxSumDouble', title: '不含税金额', align: "center", width: 100}
                , {field: 'taxRate', title: '税率', align: "center", width: 100}
                , {field: 'extendFloat1', title: '价税合计', align: "center", width: 100}
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

    //获取寄存单位列表
    $("#ownerdepBtn").on("click", function () {
        var url = "/system/user/publicDepart.htm";
        layui.layer.open({
            title: "组织机构"
            , type: 2
            , fixed: false
            , area: ['50%', '85%']
            , content: url
        });
        return false;
    });


    $("#provider").on("click", function (e) {
        vipTable.openPage("供应商列表", "/system/provider/generalProvider.htm", '60%', '90%');
        return false;

    });

    //保存按钮
    $("#saveSheet").on("click", function (e) {
        var rkId = $("#id").val();//入库单据ID
        var ztId = $("#ztId").val();
        var providerId = $("#providerId").val();
        if (rkId == "" || rkId == null) {
            /*if (providerId == "") {
                layer.alert("供应商未选择！请选择后再保存！")
            } else */
            if (ztId == "") {
                layer.alert("库存组织未选择！请选择后再保存！")
            } else {
                saveSheet();
            }
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

        //获取单据table中所有信息,拼装JSON
        var rkTable = document.getElementById("rkTable");
        var rkTableValue = rkTable.getElementsByTagName("input");
        var param = "{";
        for (var i = 0; i < rkTableValue.length; i++) {
            param += "\"" + rkTableValue[i].name + "\":\"" + rkTableValue[i].value + "\",";
        }
        param = param.substring(0, param.length - 1);
        param += "}";
        var rkSheetJson = JSON.parse(JSON.stringify(param));
        //发送请求
        $.ajax({
            type: "POST",
            url: "/sheet/rk/ZR",
            dataType: "json",
            data: JSON.parse(rkSheetJson),
            success: function (ret) {
                if (ret.status == 1) {
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

    //添加明细按钮
    $("#addDetails").on("click", function (e) {
        var rkId = $("#id").val();

        if (rkId == "" || rkId == null) {
            layer.alert("请先保存单据！");
        } else {
            JSopen();
        }

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
                , content: "getOriginalLocation.htm?rid=" + data.id
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
            , content: "addDetails.htm?providerId="+$("#providerId").val()
            , end: function () {
                if ($("#reloadStatus").val() == 1) {
                    reload();
                }
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
                var url = "deleteSheetZRDetails.json?ids=" + arr;
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

    //文件上传
    upload.render({
        elem: '#importKCResult'
        , url: '/sheet/zr/importKCResult.json'
        , exts: 'xls|xlsx' //Excel
        , done: function (res) {
            alert(res.msg)
            //layer.msg(res.msg);
            //reload();
        }
    });
});

//获取部门页面回传参数
function obtainPart(id, name, ztId, code) {
    document.getElementById("ownerdepName").value = name;
    document.getElementById("ownerdepId").value = id;
}



