layui.config({
    base: '/js/static/' // 模块目录
}).use(['form', 'table', 'layer', 'vip_table'], function () {

    var layer = layui.layer;

    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;
    var ran =Math.random();
   // document.getElementById('date').innerHTML= new Date().toLocaleString();

    //明细列表
    var detailsgrid = table.render({
        elem: '#detailsgrid'
        , url: 'details'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'POST'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20 ,30]  //数据分页条
        ,id: 'detailsgridReload'
        ,where:{ran:ran++,sheetId:$("#id").val()}

        , cols: [
                 [{type: "checkbox", fixed: "left", width: 30}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 110}
                , {field: 'description', title: '物料描述', align: "center", width: 220}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 50}
                , {field: 'providerDepName', title: '供应商', align: "center", width: 200}
                , {field: 'noTaxPrice', title: '不含税价格', align: "center", width: 200}
                , {field: 'noTaxSum', title: '不含税金额', align: "center", width: 200}
                , {field: 'oldHouseCode', title: '原库房编码', align: "center", width: 120}
                , {field: 'oldHouseName', title: '原库房', align: "center", width: 120}
                , {field: 'extendString6', title: '原库位', align: "center", width: 120}
                , {field: 'detailCount', title: '移动数量', align: "center",width: 80}
                , {field: 'newHouseCode', title: '目标库房编码', align: "center", width: 120}
                , {field: 'newHouseName', title: '目标库房', align: "center", width: 120}
                , {field: 'storeLocationName', title: '目标库位', align: "center", width: 120}
            ]
        ]
    });

    var reload =function(){
        table.reload('detailsgridReload', {
           page: {
                curr: 1 //重新从第 1 页开始
            },
            where:{
                ran: ran++,
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
            , content: "/sheet/ykyw/addDetail"
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    reload();
                }
            }
        })
    };

    var saveSheet =function(openFlag){
        $.ajax({
            type: "post",
            url: "/sheet/YKYW",
            dataType: "json",
            data: {"memo": $("#memo").val(), "menuCode": $("#menuCode").val()},
            success: function (ret) {

                if (ret.status == '1') {
                    $("#id").val(ret.data.id);
                    $("#code").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
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
            url: "/sheet/YKYW/"+id,
            dataType: "json",
            data: {"memo":$("#memo").val()},
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
        var checkStatus = table.checkStatus('detailsgridReload');
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


    $("#print").on("click", function (e) {
             layer.alert("未提供打印功能");
            // vipTable.openPage("打印移库移位单", "/system/print/sheet/YKYW-" + $("#id").val() + "?printType=" + printType, '780px', '400px');
    });


});

