layui.config({
    base: '/js/static/' // 模块目录
}).use(['form', 'table', 'layer', 'vip_table'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;
    var ran = Math.random();
    var form = layui.form;
    //  document.getElementById('date').innerHTML= new Date().toLocaleString( );
    //明细列表
    // var detailsgrid = table.render({
    //     elem: '#detailsgrid'
    //     , url: 'details'
    //     , cellMinWidth: 80
    //     , height: "full-240"
    //     , method: 'POST'
    //     , page: true   //开启分页
    //     , limit: 20   //默认十五条数据一页
    //     , limits: [10, 20 ,30]  //数据分页条
    //     ,id: 'detailsgridReload'
    //     ,where:{ran:ran++,sheetId:$("#id").val()}
    //
    //     , cols: [
    //         [     {type: "checkbox", fixed: "left", width: 30}
    //             , {field: 'materialCode', title: '物料编码', align: "center", width: 130}
    //             , {field: 'description', title: '物料描述', align: "center", width: 420}
    //             , {field: 'defaultUnitName', title: '单位', align: "center", width: 60}
    //             , {field: 'wareHouseCode', title: '库房', align: "center", width: 110}
    //             , {field: 'detailCount', title: '退货数量', align: "center",width: 80}
    //             , {field: 'noTaxPrice', title: '不含税单价', align: "center", width: 100}
    //             , {field: 'noTaxSum', title: '不含税金额', align: "center", width: 100}
    //             , {fixed: 'right', title: '详情',width:80, align:'center', toolbar: '#barDemo'}
    //
    //         ]
    //     ]
    //     // , trclick: function (data,tr) {
    //     //     subreload(data.materialCode,data.storeId);
    //     //     tr.after($("#showSub").html());
    //     //
    //     // }
    //
    // });

    var subdetailsgrid = table.render({
        // elem: '#subdetailsgrid'
        elem: '#detailsgrid'
        , url: 'subdetails'
        , cellMinWidth: 80
        , method: 'POST'
        , height: "full-260"
        , page: true   //开启分页
        , limit: 20   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: 'subdetailsgridReload'

        // ,where:{ran:ran++}
        , where: {ran: ran++, sheetId: $("#id").val()}
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'houseName', title: '库房', align: "center", width: 160}
                , {field: 'storeLocationCode', title: '库位', align: "center", width: 210}
                , {field: 'detailCount', title: '数量', align: "center", width: 150}
                , {field: 'snCode', title: '序列号', align: "center", width: 180}
                , {field: 'noTaxSumDouble', title: '不含税金额', align: "center", width: 180}
                , {fixed: 'right', title: '查看历史记录', width: 180, align: 'center', toolbar: '#subbarDemo'}

            ]
        ]

    });
    var hisdetailsgrid = table.render({
        elem: '#hisdetailsgrid'
        , url: 'hisdetails'
        , cellMinWidth: 80
        , method: 'POST'
        , page: true   //开启分页
        , limit: 20   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: 'hisdetailsgridReload'
        , where: {ran: ran++}
        , cols: [
            [
                {field: 'houseName', title: '库房', align: "center", width: 100}
                , {field: 'storeLocationCode', title: '库位', align: "center", width: 100}
                , {field: 'detailCount', title: '数量', align: "center", width: 100}
                , {field: 'snCode', title: '序列号', align: "center", width: 120}
                , {field: 'noTaxSumDouble', title: '不含税金额', align: "center", width: 120}
            ]
        ]

    });

    var reload = function () {
        // table.reload('detailsgridReload', {
        table.reload('subdetailsgridReload', {

            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val()
            }

        })
    };

    // var subreload =function(materialCode,storeId){
    //     table.reload('subdetailsgridReload', {
    //         page: {
    //             curr: 1 //重新从第 1 页开始
    //         },
    //         where:{
    //             ran:ran++,
    //             sheetId: $("#id").val(),
    //             materialCode:materialCode,
    //             storeId:storeId
    //         }
    //
    //     })
    // };

    var hisreload = function (materialCode, storeId) {
        table.reload('hisdetailsgridReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val(),
                materialCode: materialCode,
                storeId: storeId
            }

        })
    };

//保存按钮
    $("#saveSheet").on("click", function (e) {
        var result;
        var id = $("#id").val();

        if (id == "") {
            result = saveSheet(false);
        } else {
            result = updateSheet(id);
        }
        if (result) {
            layer.alert("保存成功");
        }
    });

    var open = function () {   //新增明细页面
        $("#reloadDetailsgrid").val(0);
        layer.open({
            title: "添加明细"
            , type: 2
            , fixed: false
            , area: ["95%", "95%"]
            , content: "/sheet/th/openAddDetail"
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    reload();
                }
            }
        })
    };


    var saveSheet = function (openFlag) {
        if ($("#extendInt1").val() == "") {
            layer.alert("请先选择入库单号");
            return;
        }

        $.ajax({
            type: "post",
            url: "/sheet/WZTH",
            dataType: "json",
            data: {
                "menuCode": $("#menuCode").val(),
                "extendString5": $("#rkCode").val(),
                "extendInt1": $("#extendInt1").val(),
                "orderNum": $("#orderNum").val(),
                "extendString6": $("#extendString6").val(),
                "extendString3": $("#extendString3").val(),
                "extendString2": $("#extendString2").val(),
                "ztId": $("#ztId").val(),
                "extendString1": $("#extendString1").val(),
                "extendString4": $("#extendString4").val()
            },
            success: function (ret) {

                if (ret.status == '1') {
                    $("#id").val(ret.data.id);
                    $("#code").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    $("#rkBtn").attr("disabled", true);
                    reloadHis();
                    if (openFlag) {
                        open();
                    }

                } else {
                    layer.alert('保存单据失败：' + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("保存单据请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };

    var updateSheet = function (id) {
        $.ajax({
            type: "put",
            url: "/sheet/WZTH/" + id,
            dataType: "json",
            data: {"memo": $("#memo").val()},
            success: function (ret) {
                if (ret.status == '1') {
                    layer.alert('保存单据成功');
                    return true;
                } else {
                    layer.alert('保存单据失败：' + ret.message);
                    return false;
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("保存单据请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };

    //添加明细按钮
    $("#addDetails").on("click", function (e) {
        if ($("#id").val() == "") {
            saveSheet(true);
        } else {
            open();
        }
    });
    //删除明细
    $("#deleteDetails").on("click", function (e) {
        // var checkStatus = table.checkStatus('detailsgridReload');
        var checkStatus = table.checkStatus('subdetailsgridReload');
        var data = checkStatus.data;
        var ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的明细？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/sheet/detail/delSheetDetails.json?ids=" + ids, function (data) {
                    reload();
                    layer.msg(data.message);
                    layer.close(index);
                });
            })
        } else {
            layer.msg('请选择要删除的记录', {offset: 't', anim: 6});
        }
    });


    $("#rkBtn").on("click", function (e) {
        vipTable.openPage("入库单", "/sheet/rk/generalRKD.htm?creator=" + $("#userId").val(), '90%', '80%');
        //  vipTable.openPage("采购订单列表", "/sheet/wzjs/generalOrder.htm", '680px', '400px');
        return false;

    });

    // //监听工具条
    // table.on('tool(detailsgrid)', function (obj) {
    //     var data = obj.data;
    //     subreload(data.materialCode,data.storeId);
    //
    //     layer.open({
    //         type: 1,
    //         title: "明细详情",
    //         area: ['600px', '400px'],
    //         content: $("#showSub")
    //     });
    // });

    //监听工具条
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        vipTable.openPage("退货/接收历史记录", "/sheet/wzjs/orderHistory.htm?detailsGUID=" + data.jsGuid, "60%", "70%");

        // hisreload(data.materialCode,data.storeId);
        //
        // layer.open({
        //     type: 1,
        //     title: "历史详情",
        //     area: ['600px', '400px'],
        //     content: $("#showhis")
        // });
    });

    $("#doprint").on("click", function (e) {
        vipTable.openPage("打印退货单", "/system/print/sheet/WZTH-" + $("#id").val(), '800px', '560px');
    });

});

function setRkInfo(data) {
    $("#rkCode").val(data.code);
    $("#extendInt1").val(data.id);//入库单号
    $("#orderNum").val(data.orderNum);//采购订单编号
    $("#extendString6").val(data.extendString5);//发放号
    $("#extendString3").val(data.extendString3);//订单类型
    $("#extendString2").val(data.extendString2);//库存组织
    $("#ztId").val(data.ztId);//库存组织
    $("#extendString1").val(data.extendString1);//库存组织

}
