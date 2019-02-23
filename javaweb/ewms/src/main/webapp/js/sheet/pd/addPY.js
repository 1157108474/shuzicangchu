layui.config({
    base: '/js/static/' // 模块目录
}).use(['form', 'table', 'layer', 'vip_table'], function () {
    var table = layui.table;
    var $ = layui.$;
    var form = layui.form;
    var vipTable = layui.vip_table;



    $("#provider").on("click", function (e) {
        vipTable.openPage("供应商", "../../system/provider/generalProvider.htm", '90%', '70%');
        return false;
    });

    $("#reset").on("click", function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        return false;
    });
    $("#add").on("click", function () {
        if($("#locationId").val()==""){
            layer.alert("请选择库位！");
        }else{
            $.post("addPYdata.json",
                {'sheetId':$("#sheetId").val(),'ztId':$("#ztId").val(),'tagCode':$("#tagCode").val(),
                    'detailUnitName':$("#detailUnitName").val(),'description':$("#description").val(),'sysCount':$("#sysCount").val(),
                    'providerId':$("#providerId").val(),'storeId':$("#storeId").val(),'storeLocationId':$("#locationId").val(),
                    'storeLocationCode':$("#locationCode").val(),'storeLocationName':$("#locationName").val(),'enableSN':$("#enableSN").val(), 'snCode':$("#snCode").val(),},
                function(ret) {
                    layer.msg( ret.message, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                },"JSON");
        }
        return false;
    })
    form.on('select(enableSN)', function(data){
        if(data.value == 0){
            $("#snCode").attr("disabled", true);
            $("#snCode").val("");
        }else{
            $("#snCode").removeAttr("disabled");
        }

    });
    var before = '';
    form.on('select(storeId)', function (store) {
        var current = store.value;
        if (current != before) {
            before = current;
            $("#locationName").val('');
            $("#locationId").val('');
            $("#locationCode").val('');
        }
    });
    $("#storeLocation").on("click", function (e) {
        if (before == '') {
            layer.alert("请先选择库房");
        } else {
            vipTable.openPage("库位", "../../system/ware/location?parentId=" + before, '580px', '400px');

        }
        return false;

    });
});

