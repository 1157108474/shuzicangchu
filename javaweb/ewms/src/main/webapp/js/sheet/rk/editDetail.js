layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var ran = Math.random();

    var subDetail = table.render({
        elem: '#subDetail'
        , url: 'getSonDetail.json'
        , method: 'post'
        , id: "subDetailReload"
        , where: {ran: ran++, detailId: $("#rid").val()}
        , cols: [[{type: 'numbers', title: '序号', width: 50}
            , {field: 'storeLocationName', title: '库位', align: "center"}
            , {field: 'subDetailCount', title: '分配数量', align: "center"}
            , {field: 'snCode', title: '序列号', align: "center"}
            , {title: '操作', align: "center", toolbar: '#subBar'}
        ]
        ]
        , done: function (res, curr, count) {
            layer.tips('请依次点击库位 / 分配数量 / 序列号 输入信息！', '.laytable-cell-1-fpkw', {
                time: 2000
            });
            /*layer.alert('请依次点击库位 / 分配数量 / 序列号 输入信息！');*/
        }
    });

    var subReload = function () {
        table.reload('subDetailReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                ran: ran++,
                sheetId: $("#rId").val()
            }

        })
    };


    //监听工具条
    table.on('tool(detailList)', function (obj) {

        var data = obj.data;
        var storeID = $("#storeId").val();
        if (obj.event === 'location') {
            if (storeID == "" || storeID == 0 || storeID == undefined) {
                layer.alert("库房信息不正确，无法分配！");
                return false;
            } else {
                layer.open({
                    title: "库位"
                    , type: 2
                    , fixed: false
                    , area: ['580px', '400px']
                    , content: "../../system/ware/location?pre=new&ztId=" + $("#ztId").val() + "&parentId=" + storeID
                    , end: function () {
                        obj.update({
                            fpkw: $("#newlocationName").val()
                        });
                    }
                })
            }
        }
    });

    //监听工具条
    table.on('tool(subDetail)', function (obj) {

        var data = obj.data;
        if (obj.event === 'delete') {
            layer.confirm('您确定要删除？', {
                icon: 3, title: '提示信息', offset: '35%',
                btn: ['确定', '取消'] //按钮
            }, function () {
                var url = "../rkDetail/deleteSonDetail.json?id=" + data.id;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('删除成功', {
                            time: 2000,
                            end: function () {

                                location.reload(true);
                            }
                        });

                    } else {
                        layer.alert(data.message, {
                            offset: '35%'
                        });
                    }
                })
            })
        }
    });
    var bool = true;
    //添加
    $("#addSon").on("click", function (e) {
        if (bool) {
            bool = false;
            var checkStatus = table.checkStatus('detailList');
            var data = checkStatus.data;
            var length = data.length;
            if (length != 1) {
                layer.msg("请选择数据进行添加");
                bool = true;
            } else {
                var details = [];
                //校验
                for (var i = 0; i < length; i++) {
                    if (data[i].kfpsl == 0) {
                        layer.alert("没有物料可以分配！");
                        bool = true;
                        return false;
                    }
                    if (data[i].fpkw == "" || data[i].fpkw == "0") {
                        layer.alert("请选择库位！");
                        bool = true;
                        return false;
                    }
                    if (isNaN(data[i].fpsl) || data[i].fpsl == "0") {
                        layer.alert("数量不是有效数字，请重新填写数量！");
                        bool = true;
                        return false;
                    }
                    if (data[i].fpsl == "") {
                        layer.alert("数量为空，请填写数量！");
                        bool = true;
                        return false;
                    }
                    // if (parseFloat(data[i].fpsl) > parseFloat(data[i].kfpsl) || data[i].kfpsl - data[i].fpsl < 0) {
                    if (parseFloat(data[i].fpsl) > parseFloat(data[i].kfpsl)) {
                        layer.alert("分配数量不能超过可分配数量，请重新填写！");
                        bool = true;
                        return false;
                    }
                    if (!/^[0-9]+.?[0-9]*$/.test(data[i].fpsl) || data[i].fpsl < 0) {
                        layer.alert("分配数量不能为负数！");
                        bool = true;
                        return false;
                    }
                    if (data[i].isEquipment == "是") {
                        if (!(/^(\+|-)?\d+$/.test(data[i].fpsl))) {
                            layer.alert("设备的分配数量必须是正整数，请重新填写正确的数量！")
                            bool = true;
                            return false;
                        }
                    }

                    // 截取库位信息
                    var lationCode = $("#newlocationCode").val();
                    var lationName = $("#newlocationName").val();

                    var obj = {
                        detailId: $("#rid").val(),
                        subDetailCount: data[i].fpsl,
                        subStock: 0,
                        unitName: data[i].detailUnitName,
                        storeID: $("#storeId").val(),
                        storeLocationId: $("#newlocationId").val(),
                        storeLocationName: lationName,
                        storeLocationCode: lationCode,
                        extendInt1: $("#extendInt1").val(),
                        snCode: data[i].xlh + ""
                    };
                    details.push(obj);
                }
                $.ajax({
                    type: "POST",
                    url: "../rkDetail/addSonDetail.json",
                    dataType: "json",
                    data: {details: JSON.stringify(details)},
                    success: function (ret) {
                        if (ret.status == '1') {
                            layer.msg('添加成功', function () {
                                location.reload(true);
                            });
                        } else {
                            layer.alert('添加失败：' + ret.message);
                            bool = true;
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                        bool = true;
                    }
                });
            }
        } else {
            layer.alert("已提交不允许重复提交！");
            bool = true;
        }
    });
});
