layui.use(['form', 'layer', 'table'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.$,
        table = layui.table;
    form.on("submit(updateUseDep)", function (pvi) {
        var selects = $("#status").val();
        if(selects == ""){
            layer.msg("��ѡ���Ƿ�����", {
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
                    layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
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
                title: '��֯�����б�',
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
                    return '������ı��벻����ĸ��������'
                }
            },
            zipcode: function (value, item) {
                if (value != '' && !/^\d{6}\b/.test(value)) {
                    return '����д��ȷ����������';
                }
            },
            contractPhone: function (value, item) {
                if (value != '' && !/^[0-9]+.?[0-9]*$/.test(value)) {
                    return '����д��ȷ�ĵ绰����';
                }
            },
            emails: function (value, item) {
                if (value != '' && !/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(value)) {
                    return '����д��ȷ�������ַ';
                }
            },
            sort: function (value, item) {
                if (value != '' && !/^[0-9]+.?[0-9]*$/.test(value)) {
                    return '����д������';
                }
            }


        }
    )

});

function obtainPart(id,name,ztid,code){
    //�����֯
    document.getElementById("ztId").value = ztid;
    document.getElementById("organizationId").value = id;
    document.getElementById("organizationName").value = name;
}
