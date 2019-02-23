layui.use(['laydate', 'form', 'table', 'layer', 'element', 'upload'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var upload = layui.upload;


    var uploadInst = upload.render({
        elem: '#uploadFiles',
        url: '/sheet/wzjs/uploadFile.json?rand=' + Math.random(),
        accept: 'file',
        done: function (res, index, upload) {
            // res.code == 0 上传成功
            if (res.code == 0) {
                var src = res.data.src;
                $("#fileAliasName").val(src.substring(src.lastIndexOf("\/") + 1, src.lastIndexOf(".")));
                $("#filePath").val(src);
                $("#fileExt").val(src.substring(src.lastIndexOf("."), src.length));
                $("#imageName").val(res.data.name);
            }
        },
        error: function () {
        }
    });

    form.on("submit(attchFile)", function (pvi) {

        parent.layui.$("#reloadStatus").val(1);

        $.ajax({
            type: "POST",
            url: "/sheet/wzjs/saveFile.json",
            dataType: "json",
            data: JSON.parse(JSON.stringify(pvi.field)),
            success: function (ret) {
                if (ret.status == 1) {
                    layer.msg(ret.message, function () {
                        parent.layui.table.reload("filegridTable");
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else {
                    layer.alert(ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
        return false;
    })

});
