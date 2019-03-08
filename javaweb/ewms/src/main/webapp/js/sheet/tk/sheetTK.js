layui.config({
    base: '/js/static/' // 模块目录
}).use(['table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;

    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var ran = Math.random();

    //明细列表
    var detailsgr = table.render({
        elem: '#detailsgrid'
        , url: '/sheet/tk/details'
        ,where:{ran:ran++,sheetId:$("#id").val()}
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailsgridTable"
        , cols: [
            [ {type: "checkbox", fixed: "left", width: 50}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 120}
                , {field: 'description', title: '物料描述', align: "center", width: 300}
                , {field: 'detailCount', title: '退库数量', align: "center",width: 80}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 80}
                , {field: 'houseCode', title: '库房编码', align: "center", width: 120}
                , {field: 'houseName', title: '库房', align: "center", width: 120}
                , {field: 'providerName', title: '供应商', align: "center", width: 160}
                , {field: 'noTaxPriceDouble', title: '不含税单价', align: "center", width: 100}
                , {field: 'noTaxSumDouble', title: '不含税金额', align: "center", width: 100}
            ]
        ]
    });


    var reload =function(){
        table.reload('detailsgridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where:{
                ran:ran++,
                sheetId: $("#id").val()
            }

        })
    };
    //保存按钮
    $("#saveSheet").on("click", function (e) {
        var result;
        var id = $("#id").val();

        if( id==""){
            result = saveSheet(false);
        }else{
            result = updateSheet(id);
        }
        if(result){
            layer.alert("保存成功");
        }
    });

    var open = function () {   //新增明细页面

        $("#reloadDetailsgrid").val(0);
        layer.open({
            title: "添加明细"
            , type: 2
            , fixed: false
            , area: ["90%", "90%"]
            , content: "/sheet/tk/openAddDetail"
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    reload();
                }
            }
        })
    };

    var saveSheet =function(openFlag){
        if($("#extendInt1").val()==""){
            layer.alert("请先选择出库单号");
            return;
        }

        $.ajax({
            type: "post",
            url: "/sheet/WZTK",
            dataType: "json",
            data: {"menuCode":$("#menuCode").val(),"extendInt1":$("#extendInt1").val(),"usedDepartId":$("#usedDepartId").val(),
                "officesId":$("#officesId").val(), "extendString1":$("#extendString1").val(),"ztId":$("#ztId").val(),
                "fundsSource":$("#fundsSource").val(),"memo":$("#memo").val(),"typeId":189,"extendInt3":0},
            success: function (ret) {
                if (ret.status == '1') {
                    $("#id").val(ret.data.id);
                    $("#code").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    $("#ckBtn").attr("disabled",true);
                    reloadHis();
                    if(openFlag) {
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

    var updateSheet =function(id){
        $.ajax({
            type: "put",
            url: "/sheet/WZTK/"+id,
            dataType: "json",
            data: {"memo":$("#memo").val(),"extendString1":$("#extendString1").val()},
            success: function (ret) {

                if (ret.status == '1') {
                    layer.alert('保存单据成功' );
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
        if($("#id").val()==""){
            saveSheet(true);
        } else {
            open();
        }
    });

    //删除明细
    $("#deleteDetails").on("click", function (e) {

        var checkStatus = table.checkStatus('detailsgridTable');
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


    $("#ckBtn").on("click", function (e) {
        vipTable.openPage("出库单", "/sheet/ck/openCKOder.htm", '70%', '80%');
        //  vipTable.openPage("采购订单列表", "/sheet/wzjs/generalOrder.htm", '680px', '400px');
        return false;

    });

    $("#print").on("click", function (e) {
    	var taskId = $("#taskId").val();
            vipTable.openPage("打印退货单", "/system/print/sheet/WZTK-" + $("#id").val() + "?taskId=" + taskId  , '95%', '95%');
    });


});
function getCkOrder(data){
    $("#ckCode").val(data.cksheetCode);
    // $("#ckCode").val(data.code);
    $("#extendInt1").val(data.id);
   $("#usedDepartId").val(data.usedDepartId);
    $("#usedDepartName").val(data.useddepartname);
    $("#officesId").val(data.officesId);
    $("#officesName").val(data.officesName);
    $("#fundsSource").val(data.fundsSource);
    $("#fundsSourceName").val(data.fundsSourceName);
    $("#extendString1").val(data.extendString1);
    layui.form.render('select');


}


