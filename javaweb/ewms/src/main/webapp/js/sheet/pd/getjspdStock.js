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
        , url: 'getjspdStockDetail.json?storeID=' + $("#storeID").val()
        , cellMinWidth: 80
        , height: "full-98"
        , method: "post"
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "detailGridReload"
        , cols: [[
            {type: "checkbox", fixed: "left", width: 50}
            , {field: 'materialcode', title: '物料编码', align: "center", width: 120}
            , {field: 'description', title: '材料描述', align: "center", width: 150}
            , {field: 'detailunitname', title: '单位', align: "center", width: 80}
            , {field: 'housename', title: '库房', align: "center", width: 120}
            , {field: 'storelocationname', title: '库位', align: "center", width: 100}
            , {field: 'storecount', title: '库存数量', align: "center", width: 100}
            , {field: 'providername', title: '供应商', align: "center", width: 100}
        ]
        ]
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

    $("#reset").on("click", function () {
        $("#providerName").val("");
        $("#providerId").val("");
        $("#description").val("");
        $("#materialCode").val("");
    });
    $("#add").on("click", function (e) {
        parent.layui.$("#reloadDetailsgrid").val(1);
        var sheetId = parent.layui.$("#id").val();
        var details = [];
        if ($("#extendInt2").val() == 251) {//期末盘点
            var data = table.cache.detailGridReload;
            var obj;
            for (var i = 0; i < data.length; i++) {
                var obj = {
                    sheetId: sheetId,
                    description: data[i].description,
                    materialcode: data[i].materialcode,
                    detailunitname: data[i].detailunitname,
                    storelocationname: data[i].storelocationname,
                    storelocationcode: data[i].storelocationcode,
                    housename: data[i].housename,
                    providername: data[i].providername,
                    storecount: data[i].storecount,
                    storeid: data[i].storeid,
                    storelocationid: data[i].storelocationid,
                    ztid: data[i].ztid,
                    enablesn: data[i].enablesn,
                    ypcount: data[i].ypcount,
                    providerdepid: data[i].providerdepid
                };
                details.push(obj);
            }
        } else {
            var checkStatus = table.checkStatus('detailGridReload');
            var length = checkStatus.data.length;
            if (length < 1) {
                //正上方
                layer.alert('请至少选择一条记录添加');
                return false;
            } else {
                var data = checkStatus.data;
                var obj;
                for (var i = 0; i < length; i++) {
                    var obj = {
                        sheetId: sheetId,
                        description: data[i].description,
                        materialcode: data[i].materialcode,
                        detailunitname: data[i].detailunitname,
                        storelocationname: data[i].storelocationname,
                        storelocationcode: data[i].storelocationcode,
                        housename: data[i].housename,
                        providername: data[i].providername,
                        storecount: data[i].storecount,
                        storeid: data[i].storeid,
                        storelocationid: data[i].storelocationid,
                        ztid: data[i].ztid,
                        enablesn: data[i].enablesn,
                        ypcount: data[i].ypcount,
                        providerdepid: data[i].providerdepid
                    };
                    details.push(obj);
                }
            }
        }
        $.ajax({
            type: "POST",
            url: "addKCPDDetails.json",
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

        return false;
    });
});

