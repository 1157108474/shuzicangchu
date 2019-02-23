layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var vipTable = layui.vip_table;

    var detailData;
    var oldTr;
    //明细列表
    var dbckdetailsGr = table.render({
        elem: '#dbckdetails'
        , url: '/sheet/ck/listDbDetails.json'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20 ,30]  //数据分页条
        , where: {
            code: $("#code").val()
        }
        , id: "dbckdetailsTable"
        , cols: [
            [ {field: 'materialCode', title: '物料编码', align: "center", width: 180}
                , {field: 'ytCount', title: '已调拨数量', align: "center", width: 100}
                , {field: 'detailCount', title: '计划调拨数量', align: "center", width: 120}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 80}
                , {field: 'code', title: '调拨单号', align: "center", width: 150}
                , {field: 'description', title: '物料描述', align: "center",width: 320}
            ]
        ]
        , trclick: function (data,tr) {
            if(oldTr!=null){
                oldTr.removeAttr("bgcolor","#f2f2f2");
            }
            tr.attr("bgcolor","#f2f2f2");
            oldTr = tr;
            $("#g_id").val(data.id);
            $("#g_materialCode").val(data.materialCode);
            $("#g_sncode").val(data.sncode);
            $("#g_sheetDetailId").val(data.sheetDetailId);
            $("#stockDetail").show();
            reloadstock(data);
            detailData =data;
        }
    });
    //调拨出库明细
    form.on("submit(formSubmit)", function (data) {
        table.reload('dbckdetailsTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                code: $("#code").val()
                , materialCode: $("#materialCode").val()
                , description: $("#description").val()
            }
        });
        return false;
    });
    //明细列表
    var cldetailsGrid = table.render({
        elem: '#cldetails'
        , url: '/sheet/ck/listCldetails.json'
        , cellMinWidth: 80
        , height: "180"
        , method: 'post'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20 ,30]  //数据分页条
        , id: "cldetailsTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'detailCount', title: '出库数量', align: "center", width: 120, edit: 'text'}
                , {field: 'storecount', title: '库存数量', align: "center", width: 100}
                , {
                field: 'createDate', title: '入库时间', align: "center", width: 120, templet: function (d) {
                    return vipTable.dateformat(d.createDate);
                }
            }
                , {field: 'storelocationname', title: '库房', align: "center", width: 150}
                , {field: 'storelocationareaname', title: '库位', align: "center", width: 150}
                , {field: 'providername', title: '供应商', align: "center", width: 200}
            ]
        ]
    });
    var reloadstock =function(data){
        table.reload('cldetailsTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                materialCode: data.materialCode,
                // ztId: parent.layui.$("#ztId").val()
            }
        })
    };
    //申领出库add
    $("#add").on("click", function (e)  {
        var rows = table.checkStatus('cldetailsTable').data;
        var dbdetails = [];
        var slsum = 0;
        parent.layui.$("#reloadDetailsgrid").val(1);
        if (rows.length > 0) {
            for (var i = 0; i < rows.length; i++) {
                var count = rows[i].detailCount;
                if (isNaN(count) || count == '0' || count <= 0) {
                    layer.msg("出库数量不是有效数字,请重写填写出库数量！");
                    return;
                } else if (null == count || count == "") {
                    layer.msg("出库数量为空,请填写出库数量！");
                    return;
                } else if (count > rows[i].storecount) {
                    layer.msg("出库数量不能大于库存数量,请重新填写出库数量！");
                    return;
                }
                var slobj = {
                    materialBrand: rows[i].materialbrand,
                    materialModel: rows[i].materialModel,
                    materialSpecification: rows[i].materialspecification,
                    detailUnitName: rows[i].detailunit,
                    storeId: rows[i].storeId,
                    storeLocationId: rows[i].storelocationid,
                    storeLocationName: rows[i].storelocationareaname,
                    storeLocationCode: rows[i].storelocationcode,

                    sheetId: parent.layui.$("#id").val(),
                    sheetDetailId: $("#g_id").val(),
                    extendint2: rows[i].id,
                    categoryId: rows[i].categoryId,
                    materialId: rows[i].materialId,
                    materialCode: $("#g_materialCode").val(),
                    materialName: rows[i].materialName,
                    materialBrand: rows[i].materialbrand,
                    materialModel: rows[i].materialModel,
                    materialSpecification: rows[i].materialspecification,
                    detailUnitName: rows[i].detailunit,

                    storeId: rows[i].storeId,
                    storeLocationId: rows[i].storelocationid,
                    storeLocationName: rows[i].storelocationareaname,
                    storeLocationCode: rows[i].storelocationcode,
                    description: rows[i].description,
                    tagCode: rows[i].tagcode,
                    planDepartId: rows[i].plandepartid,

                    noTaxPrice: "0",
                    taxPrice: "0",
                    taxRate: "0",
                    providerDepId: rows[i].providerDepId,
                    extendstring1: $("#g_sncode").val(),   //计划单号
                    /*sncode: rows[i].SNCODE,    //序列号*/
                    detailUnitName: rows[i].detailUnitName,
                    detailCount: count,
                    // extendint1: slmxid,
                    ownerType: rows[i].ownerType,
                    ZtId:  parent.layui.$("#ztId").val(),
                    extendInt5: $("#g_sheetDetailId").val()
                    // EnableSN: rows[i].ENABLESN
                };
                dbdetails.push(slobj);
            }
            console.log(dbdetails);
            $.ajax({
                url: "/sheet/ck/saveDetail.json",
                type: "post",
                data: {details: JSON.stringify(dbdetails)},
                success: function (ret) {
                    if (ret.status == '1') {
                        layer.msg('添加成功', function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert('添加失败：' + ret.message);
                    }
                }
            });
        } else {
            layer.msg("请选择一条明细");
        }
    });

});