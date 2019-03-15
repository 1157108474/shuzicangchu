layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;
    //明细列表
    var noPlanDetailGr = table.render({
        elem: '#noPlanDetailGrid'
        , url: '/sheet/apply/listNoPlanDetail.json'
        , cellMinWidth: 80
        , height: "full-150"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , where: {
            ztId: $("#ztId").val(),
            officesId : $("#officeId").val()
        }
        , id: "noPlanDetailGridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'applyCount', title: '申领数量', align: "center", width: 100, edit: 'text',event: 'count'}
                , {field: 'address', title: '使用地址', align: "center", width: 120, edit: 'text'}
                , {field: 'storeCount', title: '库存数量', align: "center", width: 100}
                , {field: 'storeuseCount', title: '库存可用数量', align: "center", width: 120}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 80}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 180}
                , {field: 'descripTion', title: '物料描述', align: "center", width: 320}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('点击输入申领数量、使用地址！', '.laytable-cell-1-applyCount', {
                time: 2000
            });
        }
    });
    //查询计划明细
    form.on("submit(formSubmit)", function (data) {
        table.reload('noPlanDetailGridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                materialCode: $("#materialCode").val()
                , descripTion: $("#descripTion").val()
            }
        });
        return false;
    });
    // 监听单元格编辑
    table.on('edit(noPlanDetailGrid)', function (obj) {
        var data = obj.data; //所在行的所有相关数据
        var field = obj.field; //当前编辑的字段名
        var value = obj.value; //得到修改后的值

        if(check.isDot(value)){
            if(!check.isAllowDecimal(data.detailUnitName)){
                layer.alert(data.detailUnitName+"单位不允许填小数");
                data[field] = 0;
                return;
            }
        }
    });

    //无计划明细保存
    $("#detail_add").on("click", function (e) {
        var applyCount, address;
        var details = [];
        var rows = table.checkStatus('noPlanDetailGridTable').data;
        parent.layui.$("#reloadDetailsgrid").val(1);
        if (rows.length > 0) {

            for (var i = 0; i < rows.length; i++) {
                applyCount = rows[i].applyCount;//获取申领数量
                address = rows[i].address;//获取地址
                if (null == address || address == "") {
                    layer.msg("请填写使用地址");
                    return;
                }
                if (null == applyCount || applyCount == "") {
                    layer.msg("申领数量为空,请填写申领数量！");
                    return;
                } else if (isNaN(applyCount) || applyCount == '0' || applyCount <= 0) {
                    layer.msg("申领数量不是有效数字,请重写填写申领数量！");
                    return;
                } else if (applyCount > rows[i].storeCount) {
                    layer.msg("申领数量不能大于库存数量,请重新填写申领数量！");
                    return;
                } else if (applyCount > rows[i].storeuseCount) {
                    layer.msg("申领数量不能大于库存可用数量,请重新填写申领数量！");
                    return;
                }
                var obj = {
                    sheetId: $("#sheetId").val(),
                    materialCode: rows[i].materialCode,
                    materialId: rows[i].materiaId,
                    description: rows[i].descripTion,
                    detailUnitName: rows[i].detailUnitName,
                    //detailunit: rows[i].DETAILUNIT,
                    detailCount: applyCount,//申领数量
                    extendString2: address,//使用地址
                    ztId: $("#ztId").val()
                };
                details.push(obj);
            }
            console.log(details);
            $.ajax({
                type: "POST",
                url: "/sheet/apply/saveDetail.json",
                dataType: "json",
                data: {details: JSON.stringify(details)},
                success: function (result) {
                    top.layer.msg(result.message, {time: 500});
                    if ('1' == result.status) {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }
                }
            });
        } else {
            layer.msg("请选择一条明细");
        }

    })


});

