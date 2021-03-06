layui.use(['form', 'layer', 'table'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.$,
        table = layui.table;
    form.on("submit(updateUseDep)", function (pvi) {
        var selects = $("#status").val();
        if(selects == ""){
            layer.msg("请选择是否启用", {
                offset:'t'
            });
            return false;
        }else {
            $.ajax({
                type: "POST",
                url: "/system/useDep/editUseDep.json",
                dataType: "json",
                data: JSON.parse(JSON.stringify(pvi.field)),
                success: function (ret) {
                    if (ret.status == 1) {
                        layer.msg(ret.message, function () {
                            parent.layui.table.reload("useDepListReload");
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
        }
        return false;
    });
    var zfocus = true;

    $("#organizationName").on("focus",function (e) {
        if(zfocus){
            zfocus = false;
            layer.open({
                type: 2,
                title: '组织机构列表',
                fixed: false,
                area: ['60%', '90%'],
                content: "/system/dept/openPublicDepart.htm"
            });
        }
    }).on("blur",function(e) {
        zfocus = true
    });

    form.verify({
            code: function (value, item) {
                if (value != ''&& !/^[0-9a-zA-Z]+$/.test(value)){
                    return '您输入的编码不是字母或者数字'
                }
            },
            zipcode: function (value, item) {
                if (value != '' && !/^\d{6}\b/.test(value)) {
                    return '请填写正确的邮政编码';
                }
            },
            contractPhone: function (value, item) {
                if (value != '' && !/^[0-9]+.?[0-9]*$/.test(value)) {
                    return '请填写正确的电话号码';
                }
            },
            emails: function (value, item) {
                if (value != '' && !/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(value)) {
                    return '请填写正确的邮箱地址';
                }
            },
            sort: function (value, item) {
                if (value != '' && !/^[0-9]+.?[0-9]*$/.test(value)) {
                    return '请填写纯数字';
                }
            }


        }
    )

});

function obtainPart(id,name,ztid,code){
    //库存组织
    document.getElementById("ztId").value = ztid;
    document.getElementById("organizationId").value = id;
    document.getElementById("organizationName").value = name;
}
