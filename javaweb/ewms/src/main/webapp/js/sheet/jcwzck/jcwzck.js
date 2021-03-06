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
        , url: 'detailsJCWZCKList.json'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailsgridTable"
        , where: {sheetId: $("#id").val()}
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 200}
                , {field: 'materialName', title: '物料名称', align: "center", width: 200}
                , {field: 'materialSpecification', title: '物料规格', align: "center", width: 150}
                , {field: 'materialModel', title: '物料型号', align: "center", width: 150}
                , {field: 'detailcount', title: '数量', align: "center", width: 100}
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
    //进入领料人页面
    $("#usedManBtn").on("click", function () {
        var type="llr";
        var url = "/system/user/publicDepartUser.htm?type="+type;
        layui.layer.open({
            title: "人员信息"
            , type: 2
            , fixed: false
            , area: ['85%', '90%']
            , content: url
        });
        return false;
    });
    //进入保管员页面
    $("#storeManBtn").on("click", function (e) {
        var type="bgy";
        var url = "/system/user/publicDepartUser.htm?type="+type;
        layui.layer.open({
            title: "人员信息"
            , type: 2
            , fixed: false
            , area: ['85%', '90%']
            , content: url
        });
        return false;
    });
    //进入明细页面
    $("#addDetails").on("click", function () {
        var ownerdepName = $("#ownerdepName").val();
        var useName = $("#useName").val();
        var storeManName = $("#storeManName").val();
        var sheetId = $("#id").val();
        if (ownerdepName == "") {
            layer.alert("请选择寄存单位！");
        }else if (useName == "") {
            layer.alert("请选择领料人！");
        }else if (storeManName == "") {
            layer.alert("请选择保管员！");
        }else {
            if (sheetId == "") {
                // 先判断当前单据是否已保存,如果未保存,先执行保存
                saveSheet();
            } else {
                // 打开明细页面
                open();
            }
        }
    });
    //新增明细页面
    var open = function () {
        layer.open({
            title: "添加明细"
            , type: 2
            , fixed: false
            , area: ["90%", "70%"]
            , content: "addDetails.htm"
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
            layer.msg("请选择一条记录进行删除");
        } else {
            layer.confirm('您确定要删除选中的明细？', {
                icon: 3, title: '提示信息', offset: '35%',
                btn: ['确定', '取消'] //按钮
            }, function () {
                for (var i = 0; i < datas.length; i++) {
                    arr.push(datas[i].id);
                }
                var url = "/sheet/detail/delSheetDetails.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('删除成功', {
                            time: 1000,
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
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            layer.open({
                    title: "修改明细"
                    , type: 2
                    , fixed: false
                    , area: ["90%", "70%"]
                    , content: "editDetail.htm?id="+data.id
                , end: function () {
                    if ($("#reloadStatus").val() == 1) {
                        reload();
                    }
                }
        })
        }
    })
    //保存按钮
    $("#saveSheet").on("click", function (e) {
        var ownerdepName = $("#ownerdepName").val();
        var useName = $("#useName").val();
        var storeManName = $("#storeManName").val();
        if(ownerdepName==""){
            layer.msg('请选择寄存单位');
        }else if(useName==""){
            layer.msg('请选择领料人');
        }else if(storeManName==""){
            layer.msg('请选择保管员');
        }else{
            var result;
            //判断是否存在单据编号,如果没有,执行保存,如果有,执行修改
            var check2 = $("#id").val();
            //如果单据ID不存在,add
            if (check2 == "") {
                result = saveSheet();
            } else {
                result = updateSheet();
            }
            if (result) {
                layer.alert("保存成功");
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

        $.ajax({
            type: "POST",
            url: "/sheet/wzjcckd",
            dataType: "json",
            data: {"menuCode":$("#menuCode").val(),"ownerDep":$("#ownerdepId").val(),"usedManId":$("#usedManId").val(),"storeManId":$("#storeManId").val(),"memo":$("#memo").val()},
            success: function (ret) {
                if (ret.status == 1) {
                    //将单据ID和Code赋值到页面上
                    $("#id").val(ret.data.id);
                    $("#sheetcode").val(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    reloadHis();
                    layer.close(loading);
                    open();
                } else {
                    layer.alert("保存失败，失败原因：" + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
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
        $.ajax({
            type: "POST",
            url: "../jcwzck/editSheet",
            dataType: "json",
            data: {
                "id": $("#id").val(),
                "ownerDep": $("#ownerdepId").val(),
                "usedManId": $("#usedManId").val(),
                "storeManId": $("#storeManId").val(),
                "memo": $("#memo").val()
            },
            success: function (ret) {
                if (ret.status == 1) {
                    layer.msg('保存成功', {
                        anim: 6
                    });
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


});

//获取部门页面回传参数
function obtainPart(id, name, ztId, code) {
    document.getElementById("ownerdepName").value = name;
    document.getElementById("ownerdepId").value = id;
}
//获取领料人员信息页面回传参数
function obtainUser(data,type) {
    if (type=="llr"){
        document.getElementById("useName").value = data.name;
        document.getElementById("usedManId").value = data.id;
    }else if (type=="bgy"){
        document.getElementById("storeManName").value = data.name;
        document.getElementById("storeManId").value = data.id;
    }
}


