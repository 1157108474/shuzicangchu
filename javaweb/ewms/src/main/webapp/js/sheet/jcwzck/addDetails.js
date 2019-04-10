layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var ran = Math.random();
    var vipTable = layui.vip_table;

    //明细列表
    var detailGr = table.render({
        elem: '#detailGrid'
        , url: 'listDetails.json'
        , cellMinWidth: 80
        , height: "full-80"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailGridTable"
        , cols: [
            [
                {type: "checkbox", fixed: "left", width: 50}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 120}
                , {field: 'materialName', title: '物料名称', align: "center", width: 120}
                , {field: 'materialSpecification', title: '物料规格', align: "center", width: 120}
                , {field: 'materialModel', title: '物料型号', align: "center", width: 120}
                , {field: 'unitName', title: '单位', align: "center", width: 60}
                , {field: 'ownerdepName', title: '寄存部门', align: "center", width: 120}
                , {field: 'storeCount', title: '库存数量', align: "center", width: 80}
                , {field: 'unseCount', title: '可出库数量', align: "center", width: 100}
                , {field: 'detailCount', title: '出库数量', align: "center", width: 80, edit: 'text', event: 'countCheck'}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('点击此处录入出库数量！', '.laytable-cell-1-detailCount', {
                time: 2000
            });
        }
    });

    var reload = function (data) {
        table.reload('detailGridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: JSON.parse(JSON.stringify(data.field))
        });
    };

    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });

    // 监听单元格编辑
    table.on('edit(detailGrid)', function (obj) {
        var data = obj.data; //所在行的所有相关数据
        var field = obj.field; //当前编辑的字段名
        var value = obj.value; //得到修改后的值
        if (value > data.unseCount) {
            data.jscount = 0;
            layer.alert("可出库数量是:" + data.unseCount + ",出库数量不能大于可出库数量,请重新填写出库数量!");
            return false;
        }

        if (!/^[0-9]+.?[0-9]*$/.test(value) || value == "0") {
            layer.alert("出库数量不是有效数字,请重新填写出库数量!");
            data[field] = 0;
            return false;
        }
        if (value == "" || value == null) {
            layer.alert("出库数量为空,请填写出库数量!");
            data[field] = 0;
            return false;
        }
    });

    // 添加明细
    $("#add").on("click", function (e) {
        parent.layui.$("#reloadStatus").val(1);
        var checkStatus = table.checkStatus('detailGridTable');
        var length = checkStatus.data.length;
        if (length < 1) {
            //正上方
            layer.msg('请至少选择一条记录添加');
        } else {
            var data = checkStatus.data;
            var details = [];
            for (var i = 0; i < length; i++) {
                //提交前验证
                if (data[i].detailCount > data[i].unseCount) {
                    layer.alert("出库数量不能大于可出库数量,请重新填写出库数量!");
                    return;
                }
                if (isNaN(data[i].detailCount || data[i].detailCount == '0')) {
                    layer.alert("出库数量不是有效数字,请重新填写出库数量!");
                    return;
                }
                if (data[i].detailCount == "" || data[i].detailCount == null) {
                    layer.alert("出库数量为空,请填写出库数量!");
                    return;
                }

                var obj = {
                    sheetId: parent.layui.$("#id").val(),
                    sheetDetailId: data[i].id,
                    materialId: data[i].materialId,
                    materialCode: data[i].materialCode,
                    materialName: data[i].materialName,
                    materialModel: data[i].materialModel,
                    materialSpecification: data[i].materialSpecification,
                    detailUnitName: data[i].unitName,
                    /*ownerdep: data[i].ownerdep,
                    storeCount: data[i].storeCount,
                    unseCount: data[i].unseCount,*/
                    detailCount: data[i].detailCount
                };
                details.push(obj);
            }

            $.ajax({
                type: "POST",
                url: "../detail/addwzjcckdDetails",
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


    //设备checkbox发生变化时，启动/禁用序列号checkbox
    form.on('checkbox(singleCheck)', function (obj) {
        // 获取当前checkbox是否为选中状态
        var isEq = obj.elem.checked;
        // 获取当前name
        var currentId = obj.elem.name;
        // 获取当前ID
        var checkId = obj.elem.id;
        if (isEq == true) {
            // isEq = true(选中状态)  解锁序列号
            $("#" + checkId).attr("value", 1);
            $("#enableSn" + currentId).removeAttr("disabled");
            form.render();
        } else {
            // isEq = false(未选中状态) 加锁序列号  并且取消已选择
            $("#" + checkId).attr("value", 0);
            $("#enableSn" + currentId).attr("disabled", !isEq);
            $("#enableSn" + currentId).removeAttr("checked");
            form.render();
        }

    });

    //启用序列号 点击事件
    form.on('checkbox(singCheck)', function (obj) {
        // 获取当前checkbox是否为选中状态
        var isSn = obj.elem.checked;
        // 获取当前ID
        var checkId = obj.elem.id;
        if (isSn == true) {
            $("#" + checkId).attr("value", 1);
            form.render();
        } else {
            $("#" + checkId).attr("value", 0);
            form.render();
        }
    })


});


