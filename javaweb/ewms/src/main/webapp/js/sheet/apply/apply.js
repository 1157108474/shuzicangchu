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
        , url: '/sheet/apply/listSheetApply.json'
        , cellMinWidth: 80
        , height: "full-70"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailsgridTable"
        , where: {//初始条件
            ran: ran++,
            sheetId: $("#id").val()
        }
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'action', title: '编辑', align: "center", width: 100, toolbar: '#bar'}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 150}
                , {field: 'detailCount', title: '申领数量', align: "center", width: 100}
                , {field: 'ckCount', title: '已出库数量', align: "center", width: 100}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 80}
                , {field: 'extendString2', title: '使用地址', align: "center", width: 160}
                , {field: 'snCode', title: '计划编号', align: "center", width: 150}
                , {field: 'description', title: '物料描述', align: "center", width: 320}
            ]
        ]
    });

    function applyReload() {
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

    //头部保存按钮
    $("#saveSheet").on("click", function (e) {
        var id = $("#id").val();
        if (null == id || id == "") {
            saveSheet(0);
        } else {
            updateSheet(0, id);
        }
    });


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
        vipTable.openPage("打印物资领料单", "/system/print/sheet/WZLLD-" + $("#id").val() + "?taskId=" +taskId  , '95%', '95%');
    });
    //保存、修改单据方法
    //type 0:不打开明细页面；1:打开计划明细页面；2:打开无计划明细页面
    function saveSheet(type) {
        if (checkSheet()) {
            var params = $('#bill').serialize();
            var json = {
                "menuCode": $("#menuCode").val()
                // ,"officesId": $("#officesId").val()
                , "applyDepartId": $("#applyDepartId select").val()
                , "usedDepartId": $("#usedDepartId").val()
                , "extendString2": $("#extendString2").val()
                , "ztId": $("#ztId").val()
                , "fundsSource": $("#fundsSource select").val()
                , "extendString1": $("#extendString1").val()
                , "memo": $("#memo").val()
            };
            $.ajax({
                type: "POST",
                url: "/sheet/WZLLD",
                dataType: "json",
                data: json,
                success: function (ret) {
                    if (ret.status == 1) {
                        top.layer.msg("单据保存成功！", {time: 500});
                        //将单据ID和Code赋值到页面上
                        $("#id").val(ret.data.id);
                        $("#code").html(ret.data.code);
                        $("#taskId").val(ret.data.taskId);
                        $("#reloadDetailsgrid").val(0);
                        reloadHis();
                        hideZTChange();
                        if ('1' == type) {
                            openPlanDetail();//打开计划明细
                        } else if ('2' == type) {
                            openNoPlanDetail();//打开无计划明细
                        }
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

    if (null != $("#id").val() && '' != $("#id").val()) {
        hideZTChange();
    }

    function updateSheet(type, id) {
        if (checkSheet()) {
            var params = $('#bill').serialize();
            var json = {
                "id": id
                // , "officesId": $("#officesId").val()
                , "menuCode": $("#menuCode").val()
                , "applyDepartId": $("#applyDepartId select").val()
                , "usedDepartId": $("#usedDepartId").val()
                , "extendString2": $("#extendString2").val()
                , "ztId": $("#ztId").val()
                , "fundsSource": $("#fundsSource select").val()
                , "ExtendString1": $("#ExtendString1").val()
                , "memo": $("#memo").val()
            };
            $.ajax({
                type: "put",
                url: "/sheet/WZLLD/" + id,
                dataType: "json",
                data: json,
                success: function (ret) {
                    if (ret.status == 1) {
                        top.layer.msg("单据修改成功！", {time: 500});
                        $("#reloadDetailsgrid").val(0);
                        reloadHis();
                        if ('1' == type) {
                            openPlanDetail();//打开计划明细
                        } else if ('2' == type) {
                            openNoPlanDetail();//打开无计划明细
                        }
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

    //库存组织添加隐藏属性
    function hideZTChange() {
        //调拨单号
        $("#extendString2").addClass("layui-disabled");
        $("#openZt").addClass("layui-disabled");
        $("#extendString2").attr("disabled", true);
        $("#openZt").attr("disabled", true);
    }

    //进入上级审批部门页面
    $("#openOffice").on("click", function (e) {
        var url = "/system/useDep/generalUseDepDepart.htm?ztId=" + $("#ztId").val();
        vipTable.openPage("科室", url, "75%", "85%");
    });

    //进入库存组织页面
    $("#openZt").on("click", function (e) {
        var url = "/system/dept/openPublicDepart.htm";
        vipTable.openPage("库存组织页面", url, "480px", "320px");
    });
    //进入计划领料明细页面
    $("#addPlanDetail").on("click", function (e) {
        var id = $("#id").val();
        if (null == id || id == "") {
            saveSheet(1);
        } else {
            updateSheet(1, id);
        }

    });
    //进入无计划领料明细页面
    $("#addNoPlanDetail").on("click", function (e) {
        var id = $("#id").val();
        if (null == id || id == "") {
            saveSheet(2);
        } else {
            updateSheet(2, id);
        }
    });

    //打开计划明细页面
    function openPlanDetail() {
        layer.open({
            title: "计划领料明细"
            , type: 2
            , fixed: false
            , area: ["90%", "90%"]
            , content: "/sheet/apply/openPlanDetailWindow.htm?ztId=" + $("#ztId").val() +
            "&sheetId=" + $("#id").val()+"&usedDepartId="+$("#usedDepartId").val()
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    applyReload();//刷新列表
                }
            }
        });
        // var url = "/sheet/apply/openPlanDetailWindow.htm?ztId=" + $("#ztId").val() + "&sheetId=" + $("#id").val();
        // vipTable.openPage("计划领料明细", url, "90%", "80%");
    }

    //打开无计划明细页面
    function openNoPlanDetail() {
        layer.open({
            title: "无计划领料明细"
            , type: 2
            , fixed: false
            , area: ["90%", "90%"]
            , content: "/sheet/apply/openNoPlanDetailWindow.htm?ztId=" + $("#ztId").val()
            + "&sheetId=" + $("#id").val()+"&usedDepartId="+$("#usedDepartId").val()
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    applyReload();//刷新列表
                }
            }
        });
        // var url = "/sheet/apply/openNoPlanDetailWindow.htm?ztId=" + $("#ztId").val() + "&sheetId=" + $("#id").val();
        // vipTable.openPage("无计划领料明细", url, "90%", "80%");
    }

    //删除明细
    $("#deleteDetails").click(function () {

        var checkStatus = table.checkStatus("detailsgridTable");
        var data = checkStatus.data;
        var ids = [];
        var url = "/sheet/detail/delSheetDetails.json?ids=";

        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的明细？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(url + ids, function (data) {
                    applyReload();
                    layer.msg(data.message);
                    layer.close(index);
                })
            })
        } else {
            layer.msg('请选择要删除的数据。', {offset: 't', anim: 6});
        }
    });
    //监听工具条
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'editDetails') {
            $("#reloadDetailsgrid").val(0);
            layer.open({
                title: "编辑明细"
                , type: 2
                , fixed: false
                , area: ["90%", "80%"]
                , content: '/sheet/apply/openEditApply.htm?id=' + data.id + "&sheetId=" + data.sheetId
                , end: function () {
                    if ($("#reloadDetailsgrid").val() == 1) {
                        applyReload();//刷新列表
                    }
                }
            });
            // var url = '/sheet/apply/openEditApply.htm?id=' + data.id+"&sheetId="+data.sheetId;
            // vipTable.openPage("编辑明细", url, "90%", "80%");
        }
    });

    //单据效验
    function checkSheet() {
        var ret = true;
        // if ($("#officesId").val() == "") {
        //     top.layer.msg("请选择科室！", {time: 500});
        //     return false;
        // }
        if ($("#applyDepartId select").val() == "") {
            top.layer.msg("请选择申请单位！", {time: 500});
            return false;
        }
        if ($("#usedDepartId").val() == "") {
            top.layer.msg("请选择使用单位！", {time: 500});
            return false;
        }
        if ($("#fundsSource select").val() == "") {
            top.layer.msg("请选择资金来源！", {time: 500});
            return false;
        }
        //判断使用单位是否为空
        /*var rows = detailsgrid.getSelecteds();
        for (var i = 0; i < rows.count; i++) {
            if (rows[i].EXTENDSTRING2 == "") {
                top.layer.msg("有明细未填写使用地址！", {
                    time: 500
                });
                return false;
            }
        }*/
        return ret;
    }

});

//上级审批页面返回传参数
function UseDepDepartGeneral(data) {
    // document.getElementById("officesName").value = data[0].name;
    // document.getElementById("officesId").value = data[0].id;
}

//库存组织页面返回传参数
function obtainPart(id, name, ztId, code) {
    document.getElementById("extendString2").value = name;
    document.getElementById("ztId").value = id;
}

