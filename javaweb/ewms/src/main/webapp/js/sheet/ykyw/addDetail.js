layui.config({
    base: '/js/static/' // 模块目录
}).use(['form', 'table', 'layer', 'vip_table'], function () {


    var table = layui.table;
    var $ = layui.$;
    var form = layui.form;
    var vipTable = layui.vip_table;

    form.on("submit(search)", function (data) {
        reload(data);
        return false;
    });


    //明细列表
    var detailGrid = table.render({
        elem: '#detailGrid'
        , url: 'getAddDetails'
        , cellMinWidth: 80
        , height: "full-98"
        , method: "post"
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailGridReload"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 30}
                , {
                field: 'newStore', title: '目标库房', align: "center", width: 120,
                event: 'newStore'
                /*templet: function (d) {
                    return d.houseName
                }*/
            }
                , {field: 'newLocation', title: '目标库位', align: "center", width: 120, event: 'newLocation'}
                , {field: 'detailCount', title: '移动数量', align: "center", width: 80, edit: "text"}
                , {field: 'isCount', title: '可移动数量', align: "center", width: 100}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 120}
                , {field: 'description', title: '物料描述', align: "center", width: 200}
                , {field: 'houseName', title: '原库房', align: "center", width: 120}
                , {field: 'storeLocationName', title: '原库位', align: "center", width: 120}
                , {field: 'providerDepName', title: '供应商', align: "center", width: 200}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 50}
            ]
        ], done: function (res, curr, count) {
            layer.tips('点击选择库位,输入移动数量！', '.laytable-cell-1-newLocation  ', {
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


    $("#provider").on("click", function (e) {
        vipTable.openPage("供应商", "../../system/provider/generalProvider.htm", '580px', '400px');
        return false;
    });


    var before = '';
    form.on('select(store)', function (store) {
        var current = store.value;
        if (current != before) {
            before = current;
            $("#locationId").val('');
            $("#locationName").val('');
            $("#locationCode").val('');
        }
    });


    $("#storeLocation").on("click", function (e) {

        if (before == '') {
            layer.alert("请先选择库房");
        } else {
            vipTable.openPage("库位", "../../system/ware/location?parentId=" + before + "&ztid=" + $("#ztId").val(), '580px', '400px');

        }
        return false;

    });
    table.on('tool(detailGrid)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        // var tr = obj.tr; //获得当前行 tr 的DOM对象
        $("#newlocationCode").val("");
        $("#newlocationId").val("");
        $("#newlocationName").val("")
        if (layEvent === 'newStore') {
            layui.layer.open({
                title: "库房"
                , type: 2
                , fixed: false
                , area: ['580px', '420px']
                , content: "../../system/ware/location?pre=new&parentId=0&ztid=" + $("#ztId").val()
                , end: function () {
                    obj.update({
                        newStore: $("#newlocationName").val()
                        , newStoreId: $("#newlocationId").val()
                        , newLocation: ''
                        , newLocationId: ''
                        , newLocationCode: ''
                    });
                }
            })
        } else if (layEvent === 'newLocation') {

            if (data.newStore == null || data.newStore == '') {
                layer.alert("请先选择目标库房");
            } else {

                layui.layer.open({
                    title: "库位"
                    , type: 2
                    , fixed: false
                    , area: ['580px', '400px']
                    , content: "../../system/ware/location?pre=new&parentId=" + data.newStoreId+"&ztid="+$("#ztId").val()
                   /*,content: "../../system/ware/location?pre=new&parentId=" + data.storeId + "&ztid=" + $("#ztId").val()*/

                    ,end: function () {
                        obj.update({
                            newLocation: $("#newlocationName").val()
                            , newLocationId: $("#newlocationId").val()
                            , newLocationCode: $("#newlocationCode").val()
                        });
                    }
                })
            }
        }
    });

    $("#add").on("click", function (e) {
        parent.layui.$("#reloadDetailsgrid").val(1);
        var checkStatus = table.checkStatus('detailGridReload');
        var length = checkStatus.data.length;
        var sheetId = parent.layui.$("#id").val();
        if (length < 1) {
            //正上方
            layer.alert('请至少选择一条记录添加');
        } else {
            var data = checkStatus.data;
            var details = [];
            var obj;
            var count;
            for (var i = 0; i < length; i++) {


                if (data[i].newStoreId == null || data[i].newStoreId == '') {
                    layer.alert("请选择目标库房！");
                    return false;

                } else if (data[i].newLocationId == null || data[i].newLocationId == '') {
                    layer.alert("请选择目标库位！");
                    return false;
                }
                if (data[i].newLocationId == data[i].storeLocationId) {
                    layer.alert("位置没有发生改变,请重新选择目标库位！");
                    return false;
                }
                count = data[i].detailCount;
                if (count == null || count.trim() == '' || isNaN(count.trim())) {
                    layer.alert("移动数量不是有效数字,请重新填写移动数量！");
                    return false;
                }
                count = count.trim();
                if (count == 0) {
                    layer.alert("移动数量不得为0！");
                    return false;

                }
                if (count < 0) {
                    layer.alert("移动数量不得小于0！");
                    return false;
                }
                if (count > data[i].isCount) {
                    layer.alert("移动数量不得大于可移动数量！");
                    return false;
                }
                obj = {
                    sheetId: sheetId,
                    sheetDetailId: data[i].id,
                    categoryId: data[i].categoryId,
                    materialId: data[i].materialId,
                    materialCode: data[i].materialCode,
                    materialName: data[i].materialName,
                    materialBrand: data[i].materialBrand,
                    materialModel: data[i].materialModel,
                    materialSpecification: data[i].materialSpecification,
                    detailCount: count.trim(),
                    tagCode: data[i].materialCode,
                    detailUnitName: data[i].detailUnitName,
                    extendInt4: data[i].storeId,
                    extendInt5: data[i].storeLocationId,
                    extendString5: data[i].storeLocationCode,
                    extendString6: data[i].storeLocationName,
                    providerDepId: data[i].providerDepId,
                    noTaxPrice: data[i].noTaxPrice,
                    taxPrice: data[i].taxPrice,
                    taxRate: data[i].taxRate,
                    description: data[i].description,
                    expirationTime: data[i].expirationTime,
                    planDepartId: data[i].planDepartId,
                    storeId: data[i].newStoreId,
                    // storeId: data[i].storeId,
                    storeLocationId: data[i].newLocationId,
                    storeLocationName: data[i].newLocation,
                    storeLocationCode: data[i].newLocationCode,
                    isEquipment: data[i].isEquipment,
                    enableSn: data[i].enableSn,
                    snCode: data[i].snCode,
                    ownerType: data[i].ownerType,
                    ztId: data[i].ztId
                };
                details.push(obj);
            }


            $.ajax({
                type: "POST",
                url: "../detail/addYKYWDetails",
                dataType: "json",
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

