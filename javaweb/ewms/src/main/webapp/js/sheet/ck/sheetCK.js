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
        , url: '/sheet/ck/listSheetCK.json'
        , cellMinWidth: 80
        , height: "full-70"
        , method: 'post'
        , where: {sheetId: $("#id").val()}
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailsgridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 120}
                , {field: 'detailUnitName', title: '计量单位', align: "center", width: 180}
                , {field: 'detailCount', title: '出库数量', align: "center", width: 100}
                , {field: 'houseCode', title: '库房编码', align: "center", width: 120}
                , {field: 'houseName', title: '库房', align: "center", width: 150}
                , {field: 'extendstring1', title: '计划编号', align: "center", width: 100}
                , {field: 'noTaxPriceDouble', title: '不含税单价', align: "center", width: 100}
                , {field: 'noTaxSumDouble', title: '不含税金额', align: "center", width: 100}
                , {field: 'description', title: '物料描述', align: "center", width: 320}
            ]
        ]
    });

    function ckReload() {
        table.reload('detailsgridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val()
            }
        })
    }

    window.reloadHis = function () {
        //可以被外部引用
        table.reload('activitiHisTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                taskId: $("#taskId").val()
            }
        });
    }
    $("#print").on("click", function (e) {
    	var taskId = $("#taskId").val();
        vipTable.openPage("打印物资出库单", "/system/print/sheet/WZCK-" + $("#id").val() + "?taskId=" + taskId  , '95%', '95%');
    });
    // 监听select事件
    form.on('select(ckType)', function (data) {
        var selectValue = data.value;
        var selectName = data.elem[data.elem.selectedIndex].text;
        if (selectName == '直接出库') {
            showZjChange();
            hideSlChange();
            hideDbChange();
        } else if (selectName == '申领出库') {
            showSlChange();
            hideDbChange();
            hideZjChange();
        } else if (selectName == '调拨出库') {
            showDbChange();
            hideZjChange();
            hideSlChange();
        } else {
            hideZjChange();
            hideSlChange();
            hideDbChange();
        }
        form.render('select'); //重新渲染select，这个很重要
    });
    if (null != $("#id").val() && '' != $("#id").val()) {
        hideTypeChange();
        form.render();
    }

    //出库类型 添加隐藏属性
    function hideTypeChange() {
        //出库类型
        $("#type").addClass("layui-disabled");
        $("#type").attr("disabled", true);
    }

    //出库类型-调拨出库 添加隐藏属性
    function hideAllChange() {
        //调拨单号
        $("#dbCode").addClass("layui-disabled");
        $("#openDb").addClass("layui-disabled");
        $("#dbCode").attr("disabled", true);
        $("#openDb").attr("disabled", true);
        //申请单号
        $("#applyCode").addClass(" layui-disabled");
        $("#openApply").addClass(" layui-disabled");
        $("#applyCode").attr("disabled", true);
        $("#openApply").attr("disabled", true);
        //使用单位
        $("#useDepName").addClass(" layui-disabled");
        $("#openUseDep").addClass(" layui-disabled");
        $("#useDepName").attr("disabled", true);
        $("#openUseDep").attr("disabled", true);
        // //上级审批部门
        // $("#officesId").addClass(" layui-disabled");
        // $("#departOfficename").addClass(" layui-disabled");
        // $("#openOffice").addClass(" layui-disabled");
        // $("#officesId").attr("disabled", true);
        // $("#departOfficename").attr("disabled", true);
        // $("#openOffice").attr("disabled", true);
        //申请单位
        $("#applyDep").addClass(" layui-disabled");
        $("#applyDep").attr("disabled", true);
        //资金来源
        $("#fundSourcesChange").addClass(" layui-disabled");
        $("#fundSourcesChange").attr("disabled", true);
        //用途
        $("#extendstring1").addClass(" layui-disabled");
        $("#extendstring1").attr("disabled", true);
        //备注
        $("#memo").addClass(" layui-disabled");
        $("#memo").attr("disabled", true);
    }

    //出库类型-直接出库 去掉隐藏属性
    function showZjChange() {
        //使用单位
        $("#useDepName").removeClass(" layui-disabled");
        $("#openUseDep").removeClass(" layui-disabled");
        $("#useDepName").attr("disabled", false);
        $("#openUseDep").attr("disabled", false);
        $("#useDepName").val('');
        $("#openUseDep").val('');
        // //上级审批部门
        // $("#officesId").removeClass(" layui-disabled");
        // $("#departOfficename").removeClass(" layui-disabled");
        // $("#openOffice").removeClass(" layui-disabled");
        // $("#officesId").attr("disabled", false);
        // $("#departOfficename").attr("disabled", false);
        // $("#openOffice").attr("disabled", false);
        // $("#officesId").val('');
        // $("#departOfficename").val('');
        // $("#departOfficename").html('');
        // $("#openOffice").val('');
        //申请单位
        $("#applyDep").removeClass(" layui-disabled");
        $("#applyDep").attr("disabled", false);
        $("#applyDep").val('');
        //资金来源
        $("#fundSourcesChange").removeClass(" layui-disabled");
        $("#fundSourcesChange").attr("disabled", false);
        $("#fundSourcesChange").val('');
        //用途
        $("#extendstring1").removeClass(" layui-disabled");
        $("#extendstring1").attr("disabled", false);
        $("#extendstring1").val('');
        //备注
        $("#memo").removeClass(" layui-disabled");
        $("#memo").attr("disabled", false);
        $("#memo").val('');
    }

    //出库类型-直接出库 添加隐藏属性
    function hideZjChange() {
        //使用单位
        $("#useDepName").addClass(" layui-disabled");
        $("#openUseDep").addClass(" layui-disabled");
        $("#useDepName").attr("disabled", true);
        $("#openUseDep").attr("disabled", true);
        $("#useDepName").val('');
        $("#openUseDep").val('');
        // //上级审批部门
        // $("#officesId").addClass(" layui-disabled");
        // $("#departOfficename").addClass(" layui-disabled");
        // $("#openOffice").addClass(" layui-disabled");
        // $("#officesId").attr("disabled", true);
        // $("#departOfficename").attr("disabled", true);
        // $("#openOffice").attr("disabled", true);
        // $("#officesId").val('');
        // $("#departOfficename").val('');
        // $("#departOfficename").html('');
        // $("#openOffice").val('');
        //申请单位
        $("#applyDep").addClass(" layui-disabled");
        $("#applyDep").attr("disabled", true);
        $("#applyDep").val('');
        //资金来源
        $("#fundSourcesChange").addClass(" layui-disabled");
        $("#fundSourcesChange").attr("disabled", true);
        $("#fundSourcesChange").val('');
        //用途
        $("#extendstring1").addClass(" layui-disabled");
        $("#extendstring1").attr("disabled", true);
        $("#extendstring1").val('');
        //备注
        $("#memo").addClass(" layui-disabled");
        $("#memo").attr("disabled", true);
        $("#memo").val('');
    }

    //出库类型-申领出库 去掉隐藏属性
    function showSlChange() {
        //申请单号
        $("#applyCode").removeClass(" layui-disabled");
        $("#openApply").removeClass(" layui-disabled");
        $("#applyCode").attr("disabled", false);
        $("#openApply").attr("disabled", false);
    }

    //出库类型-申领出库 添加隐藏属性
    function hideSlChange() {
        //申请单号
        $("#applyCode").addClass(" layui-disabled");
        $("#openApply").addClass(" layui-disabled");
        $("#applyCode").attr("disabled", true);
        $("#openApply").attr("disabled", true);
        $("#applyCode").val('');
        $("#openApply").val('');
    }

    //出库类型-调拨出库 去掉隐藏属性
    function showDbChange() {
        //调拨单号
        $("#dbCode").removeClass(" layui-disabled");
        $("#openDb").removeClass(" layui-disabled");
        $("#dbCode").attr("disabled", false);
        $("#openDb").attr("disabled", false);
    }

    //出库类型-调拨出库 添加隐藏属性
    function hideDbChange() {
        //调拨单号
        $("#dbCode").addClass("layui-disabled");
        $("#openDb").addClass("layui-disabled");
        $("#dbCode").attr("disabled", true);
        $("#openDb").attr("disabled", true);
        $("#dbCode").val('');
        $("#openDb").val('');
    }

    //进入上级审批部门页面
    $("#openOffice").on("click", function (e) {
        var url = "/system/useDep/generalUseDepDepart.htm?ztId=" + $("#ztId").val();
        vipTable.openPage("科室", url, "75%", "85%");
    });
    //进入申领单号页面
    $("#openApply").on("click", function (e) {
        if ($("#ckType select").val() == '449') {
            var url = "/sheet/apply/openApply.htm?ztId=" + $("#ztId").val();
            vipTable.openPage("申领单号", url, "1080px", "520px");
        } else {
            return false;
        }
    });
    //进入调拨单号页面
    $("#openDb").on("click", function (e) {
        if ($("#ckType select").val() == '772') {
            var url = "/sheet/db/dbd?type=ck&ztId=" + $("#ztId").val();
            vipTable.openPage("调拨单号", url, "480px", "320px");
        } else {
            return false;
        }
    });
    //进入使用单位页面
    $("#openUseDep").on("click", function (e) {
        if ($("#ckType select").val() == '448') {
            var url = "/system/useDep/generalUseDep.htm?ztId=" + $("#ztId").val();
            vipTable.openPage("使用单位", url, "500px", "80%");
        } else {
            return false;
        }

    });
    //进入添加明细页面
    $("#addDetails").on("click", function (e) {
        var cktype = $("#type option:checked").text();
        var id = $("#id").val();
        if (null == id || id == "") {
            saveSheet(cktype, true);
        } else {
            updateSheet(cktype, true, id);
        }
    });
    //保存按钮
    $("#saveSheet").on("click", function (e) {
        var cktype = $("#type option:checked").text();
        var id = $("#id").val();
        if (null == id || id == "") {
            saveSheet(cktype, false);
        } else {
            updateSheet(cktype, false, id);
        }
    });

    //保存、修改单据方法
    function saveSheet(cktype, bool) {
        if (checkSheet()) {
            var ztId = $("#ztId").val();
            var json = {
                "type": "CKD"//出库类型
                , "typeid": $("#ckType select").val() //出库类型
                , "menuCode": $("#menuCode").val()
                , "ztid": ztId
                , "extendint1": $("#applyId").val()//申领单号id
                , "useddepartid": $("#usedDepartId").val()//使用单位Id
                , "extendstring2": $("#extendString2").val()//库存组织
                , "extendint2": $("#extendInt2").val() //调拨单号
                // , "officesId": $("#officesId").val() //上级审批部门
                , "applydepartid": $("#applyDepId select").val() //申请单位
                , "fundssource": $("#fundSources select").val() //资金来源
                , "extendstring1": $("#extendstring1").val() //用途
                , "memo": $("#memo").val() //备注
            };
            $.ajax({
                type: "POST",
                url: "/sheet/ck/saveSheetCK.json",
                dataType: "json",
                data: json,
                success: function (ret) {
                    if (ret.status == 1) {
                        top.layer.msg(ret.message, {time: 500});
                        console.log(ret);
                        //将单据ID和Code赋值到页面上
                        $("#id").val(ret.data.id);
                        $("#code").html(ret.data.code);
                        $("#taskId").val(ret.data.taskId);
                        $("#reloadDetailsgrid").val(0);
                        reloadHis();
                        if (bool) {
                            open(cktype);
                        }
                        hideTypeChange();
                        hideAllChange();
                        form.render();
                    } else {
                        layer.alert("保存失败，失败原因：" + ret.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            })
        }
    }

    function updateSheet(cktype, bool, id) {
        //TODO 未加入单据效验条件
        if (checkSheet()) {
            var ztId = $("#ztId").val();
            var json = {
                "id": id
                , "type": "CKD"//出库类型
                , "typeid": $("#ckType select").val() //出库类型
                , "menuCode": $("#menuCode").val()
                , "ztid": ztId
                , "extendint1": $("#applyId").val()//申领单号id
                , "useddepartid": $("#usedDepartId").val()//使用单位Id
                , "extendstring2": $("#extendString2").val()//库存组织
                , "extendint2": $("#extendInt2").val() //调拨单号
                // , "officesId": $("#officesId").val() //上级审批部门
                , "applydepartid": $("#applyDepId select").val() //申请单位
                , "fundssource": $("#fundSources select").val() //资金来源
                , "extendstring1": $("#extendstring1").val() //用途
                , "memo": $("#memo").val() //备注
            };

            $.ajax({
                type: "put",
                url: "/sheet/ck/CKD/" + id,
                dataType: "json",
                data: json,
                success: function (ret) {
                    if (ret.status == 1) {
                        top.layer.msg("单据修改成功！", {time: 500});
                        $("#reloadDetailsgrid").val(0);
                        reloadHis();
                        if (bool) {
                            open(cktype);
                        }
                        hideTypeChange();
                        hideAllChange();
                        form.render();
                    } else {
                        layer.alert("单据修改失败，失败原因：" + ret.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            })
        }
    }

    function open(cktype) {
        var ztId = $("#ztId").val();
        if (cktype == '直接出库') {//直接出库
            layer.open({
                title: "直接出库"
                , type: 2
                , fixed: false
                , area: ["90%", "90%"]
                , content: "/sheet/ck/openZJCKDetailWindow.htm?ztId=" + ztId
                , end: function () {
                    if ($("#reloadDetailsgrid").val() == 1) {
                        ckReload();//刷新列表
                    }
                }
            })
            //vipTable.openPage("直接出库", "/sheet/ck/openZJCKDetailWindow.htm?ztId=" + ztId, "90%", "80%");
        } else if (cktype == '申领出库') {//申领出库
            layer.open({
                title: "申领出库"
                , type: 2
                , fixed: false
                , area: ["90%", "90%"]
                , content: "/sheet/ck/openSLCKDetailWindow.htm?ztId=" + ztId + "&code=" + $("#applyCode").val()
                , end: function () {
                    if ($("#reloadDetailsgrid").val() == 1) {
                        ckReload();//刷新列表
                    }
                }
            })
            //vipTable.openPage("申领出库", "/sheet/ck/openSLCKDetailWindow.htm?ztId=" + ztId, "90%", "80%");
        } else if (cktype == '调拨出库') {//调拨出库
            layer.open({
                title: "调拨出库"
                , type: 2
                , fixed: false
                , area: ["90%", "90%"]
                , content: "/sheet/ck/openDBCKDetailWindow.htm?ztId=" + ztId + "&code=" + $("#dbCode").val()
                , end: function () {
                    if ($("#reloadDetailsgrid").val() == 1) {
                        ckReload();//刷新列表
                    }
                }
            })
            //vipTable.openPage("调拨出库", "/sheet/ck/openDBCKDetailWindow.htm?ztId=" + ztId, "90%", "80%");
        }
    }

    function checkSheet() {
        var ret = true;
        var cktype = $("#type option:checked").text();
        if (cktype == '直接出库') {//直接出库
            if (null == $("#usedDepartId").val() || $("#usedDepartId").val() == '') {
                top.layer.msg("使用单位不能为空", {time: 500});
                return false;
            }
            // if(null ==$("#officesId").val() || $("#officesId").val()=='' ){
            //     top.layer.msg("上级审批部门不能为空", {time: 500});
            //     return false;
            // }
            if (null == $("#applyDepId select").val() || $("#applyDepId select").val() == '') {
                top.layer.msg("请选择申请单位", {time: 500});
                return false;
            }
            if (null == $("#fundSources select").val() || $("#fundSources select").val() == '' ) {
                top.layer.msg("请选择资金来源", {time: 500});
                return false;
            }
        } else if (cktype == '申领出库') {//申领出库
            if (null == $("#applyId").val() || $("#applyId").val() == '') {
                top.layer.msg("申领单号不能为空", {time: 500});
                return false;
            }
        } else if (cktype == '调拨出库') {//调拨出库
            if (null == $("#extendInt2").val() || $("#extendInt2").val() == '') {
                top.layer.msg("调拨单号不能为空", {time: 500});
                return false;
            }
        } else {
            top.layer.msg("请选择出库类型", {time: 500});
            return false;
        }
        return ret;
    }

    //删除明细
    $("#deleteDetails").click(function () {
        var checkStatus = table.checkStatus("detailsgridTable");
        var data = checkStatus.data;
        var ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm("确定删除选中的明细？", {icon: 3, title: '提示信息'}, function (index) {
                $.post("/sheet/ck/delSheetDetails.json?ids=" + ids, function (data) {
                    layer.msg(data.message);
                    layer.close(index);
                    ckReload();//刷新列表
                })
            })
        } else {
            layer.msg('请选择要删除的数据。', {offset: 't', anim: 6});
        }
    });
    //拆单
    $("#split").click(function () {
        var checkStatus = table.checkStatus("detailsgridTable");
        var data = checkStatus.data;
        if (data.length > 0) {
            var json = {
                "type": "CKD"//出库类型
                , "typeid": $("#ckType select").val() //出库类型
                , "menuCode": $("#menuCode").val()
                , "ztid": $("#ztId").val()
                , "extendint1": $("#applyId").val()//申领单号id
                , "useddepartid": $("#usedDepartId").val()//使用单位Id
                , "extendstring2": $("#extendString2").val()//库存组织
                , "extendint2": $("#extendInt2").val() //调拨单号
                // , "officesId": $("#officesId").val() //上级审批部门
                , "applydepartid": $("#applyDepId select").val() //申请单位
                , "fundssource": $("#fundSources select").val() //资金来源
                , "extendstring1": $("#extendstring1").val() //用途
                , "memo": $("#memo").val() //备注
            };
            var sheetId = $("#id").val();
            $.ajax({
                type: "POST",
                url: "/sheet/ck/splitSheet.json?sheetId=" + sheetId,
                dataType: "json",
                data: {details: JSON.stringify(json)},
                success: function (ret) {
                    if (ret.status == 1) {
                        top.layer.msg("拆单成功！", {time: 500});
                        ckReload();//刷新列表
                    } else {
                        top.layer.msg("拆单失败！", {time: 500});
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            })
        } else {
            layer.msg('请先添加明细', {offset: 't', anim: 6});
        }
    });

});

//上级审批部门返回值
function UseDepDepartGeneral(data) {
    document.getElementById("departOfficename").value = data[0].name;
    document.getElementById("officesId").value = data[0].id;
}

//获取申领单返回值
function getApply(data) {
    document.getElementById("applyCode").value = data.code;
    document.getElementById("applyId").value = data.id;
    document.getElementById("useDepName").value = data.useddepartname;
    document.getElementById("usedDepartId").value = data.usedDepartId;
    // document.getElementById("departOfficename").value = data.officeName;
    // document.getElementById("officesId").value = data.officesId;
    document.getElementById("applyDep").value = data.applyDepartId;
    document.getElementById("fundSourcesChange").value = data.fundsSource;
    layui.form.render('select'); //重新渲染select，这个很重要
}

//获取使用单位返回值
function UseDepGeneral(data) {
    document.getElementById("useDepName").value = data[0].name;
    document.getElementById("usedDepartId").value = data[0].id;
}

//获取调拨单返回值
function DBDGeneral(data) {
    console.log(data);
    document.getElementById("dbCode").value = data.code;
    document.getElementById("extendInt2").value = data.id;
}