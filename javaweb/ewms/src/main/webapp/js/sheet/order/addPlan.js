layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var laydate = layui.laydate;

    form.on("submit(commitForm)", function (pvi) {

        $.ajax({
            type: "POST",
            url: "planOthers.json",
            dataType: "json",
            data: JSON.parse(JSON.stringify(pvi.field)),
            success: function (ret) {
                if (ret.status == 1) {
                    layer.msg(ret.message, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else {
                    layer.alert(ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("ÇëÇó³ö´í£º" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
        return false;
    });


    laydate.render({
        elem: '#expirationTime'
        , type: 'datetime'
    });

    laydate.render({
        elem: '#extendDate2'
        , type: 'datetime'
    });
});
