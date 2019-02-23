layui.config({
    base: '/js/static/' // 模块目录
}).use(['form', 'table', 'layer', 'vip_table'], function () {

    var table = layui.table;
    var $ = layui.$;

    var form = layui.form;
    form.on("submit(search)", function (data) {
        reload(data);
        return false;
    });

    var ztId = parent.layui.$("#cOrg").val();
    $("#ztId").val(ztId);


    //明细列表
    var detailGrid = table.render({
        elem: '#detailGrid'
        , url: 'addDetails?ztId='+ztId
        , cellMinWidth: 80
        , height: "full-98"
        , method: "post"
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailGridReload"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'newLocation', title: '调入库位', align: "center", width: 120, event: 'newLocation'}
                , {field: 'detailCount', title: '调拨数量', align: "center", width: 100, edit: "text"}

                , {field: 'materialCode', title: '物料编码', align: "center", width: 140}
                , {field: 'description', title: '物料描述', align: "center", width: 250}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 50}
                , {field: 'storeCount', title: '库存量', align: "center", width: 80}
                , {field: 'isCount', title: '可出库量', align: "center", width: 80}
                , {field: 'houseName', title: '库房', align: "center", width: 150}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('点击选择调入库位并输入调拨数量！', '.laytable-cell-1-newLocation  ', {
                time: 2000
            });
            // layer.tips('点击选择目标库位！', '.laytable-cell-1-newLocation ', {
            //     time: 2000
            // });
            // layer.tips('点击输入移动数量！', '.laytable-cell-1-detailCount ', {
            //     time: 2000
            // });
        }
    });

    var reload = function (data) {
        table.reload('detailGridReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: JSON.parse(JSON.stringify(data.field))

        })
    };

    table.on('tool(detailGrid)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        $("#newlocationCode").val("");
        $("#newlocationId").val("");
        $("#newlocationName").val("")
        if (layEvent === 'newLocation') {


            layui.layer.open({
                title: "库位",
                type: 2,
                fixed: false ,
                area: ['580px', '400px'],
                content: "../../system/ware/location?pre=new&parentCode=" + data.wareHouseCode + "&ztid=" + parent.layui.$("#rOrg").val(),
                end: function () {
                    obj.update({
                          newLocation: $("#newlocationName").val()
                        , newLocationId: $("#newlocationId").val()
                        , newLocationCode: $("#newlocationCode").val()
                        , newStoreId: $("#store").val()
                    });
                }
            })

        }
    });


    $("#add").on("click", function (e) {
        parent.layui.$("#reloadDetailsgrid").val(1);
        var checkStatus = table.checkStatus('detailGridReload');
        var length = checkStatus.data.length;
        var sheetId = parent.layui.$("#id").val();
        var userZtId = parent.layui.$("#userZtId").val();
        if (length < 1  ){
            //正上方
            layer.alert('请至少选择一条记录添加');
        }else{
            var data = checkStatus.data;
            var details = [];
            var obj;
            var count;
            for (var i = 0; i < length; i++) {
                if (data[i].newLocationId == null || data[i].newLocationId == '') {
                    layer.alert("请选择目标库位！");
                    return false;
                }
                count = data[i].detailCount;
                if (count == null || count.trim() == '' || isNaN(count.trim())) {
                    layer.alert("调拨数量不是有效数字,请重新填写调拨数量！");
                    return false;
                }
                if (count.trim() == 0) {
                    layer.alert("调拨数量不得为0！");
                    return false;
                }
                if (count.trim() > data[i].isCount) {
                    layer.alert("调拨数量不得大于可出库量！");
                    return false;
                }
                if (count.trim() > data[i].storeCount) {
                    layer.alert("调拨数量不得大于库存量！");
                    return false;
                }

                if(count.trim()<0){
                    layer.alert("调拨数量不得为负！");
                    return false;
                }
                if(data[i].enableSn=="1"){
                    if(!(/^(\+|-)?\d+$/.test(count))){
                        layer.alert("启用序列的物料调拨数量必须是整数");
                        return false;
                    }
                }

                var obj = {
                    sheetId: sheetId,
                    materialId: data[i].materialId,
                    tagCode:data[i].materialCode,
                    materialCode: data[i].materialCode,
                    materialName: data[i].materialName,
                    detailUnitName: data[i].detailUnitName,
                    detailCount: count.trim(),
                    description: data[i].description,
                    providerDepId: data[i].providerDepId,
                    storeId: data[i].newStoreId,
                    storeLocationId: data[i].newLocationId,
                    storeLocationName: data[i].newLocation,
                    storeLocationCode: data[i].newLocationCode,
                    extendString1: data[i].materialCode,
                    enableSn: data[i].enableSn,
                    ownerType: data[i].ownerType,
                    ztId: userZtId
                };

                details.push(obj);
            }


            $.ajax({
                type: "POST",
                url: "../detail/addWZDBDDetails",
                dataType:"json",
                data: {details: JSON.stringify(details)},
                success: function (ret) {
                    if (ret.status == '1') {
                        layer.msg('添加成功', function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert('添加失败：' + ret.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            });
        }
        return false;
    });
});

