 layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;
    // if ($("#id").val() == ''){
    //     $("#spareId").val(parent.layui.$("#spareId").val());
    // }
    var spareCode = $("#spareCode").val();
    form.verify({
        sort: function(value){
            var reg =/^[0-9]*$/;
            if (value != '' && isNaN(value) && !reg.test(value)) {
                return '排序必须是正整数';
            }
        },
        price: function(value){
            var reg=/^([0-9]+)((.\d{1,2})?)$/;
            if(value!='' && !reg.test(value)){
                return '单价及库存上下限只能是整数或两位小数';
            }
        },

        codeverify: function(value){
            if(value.length <= spareCode.length ||value.substr(0,spareCode.length)!= spareCode) {
                return '物料编码应以物料分类编码开始';
            }

        }

    });

 //   $("#code").val(spareCode);
    form.on("submit(editMaterial)", function (material) {

            var type;
            $("#edit").hide();
            if ($("#id").val() == '') {
                type = "POST";
            } else {
                type = "PUT";
            }
        if ($("#stockUp").val() != '' && $("#stockDown").val() != '' && eval($("#stockUp").val()) < eval($("#stockDown").val())) {
                layer.alert("物料上限不得小于物料下限");
            $("#edit").show();
                return;
            }
            $.ajax({
                type: type,
                url: "../material",
                dataType: "json",
                data: JSON.parse(JSON.stringify(material.field)),
                success: function (ret) {
                    if (ret.status == '1') {
                        layer.msg('提交成功', function () {
                            //关闭后的操作
                            // parent.location.reload();
                          //  parent.layer.reload();
                            //table.reload("materialListReload");

                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert('提交失败：' + ret.message);
                        $("#edit").show();
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                    $("#edit").show();
                }
            });
            return false;

    });

    var providerFocus = true;
    $("#providerName").on("focus",function(e){
        if(providerFocus) {
            providerFocus = false;
            layer.open({
                type: 2,
                title: '供应商',
                fixed: false,
                area: ['80%', '90%'],
                content: "../provider/generalProvider.htm",
                // scrollbar:false
            });
        }
    }).on("blur",function(e) {
        providerFocus = true
     });


    var ztFocus = true;
    $("#ztname").on("focus",function(e){
        if(ztFocus) {
            ztFocus = false;
            layer.open({
                type: 2,
                title: '库存组织',
                fixed: false,
                area: ['60%', '90%'],
                content: "../dept/openPublicDepart.htm",
                // scrollbar:false
            });
        }

    }).on("blur",function(e) {
        ztFocus = true
    });



});
var obtainPart = function(id,name,ztid,code){
    document.getElementById("ztname").value = name;
    document.getElementById("ztid").value = id;

};
