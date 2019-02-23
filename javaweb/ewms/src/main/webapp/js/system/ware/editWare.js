layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;
    if($("#id").val() == '') {
        $("#parentId").val(parent.layui.$("#id").val());
        $("#parentCode").val(parent.layui.$("#code").val());
        $("#parentName").val(parent.layui.$("#name").val());
        var level = parent.layui.$("#level").val();
        $("#level").val(Number(level) + 1);
    }
    form.verify({
        sort: function(value){
            var reg =/^[0-9]*$/;
            if (value != '' && isNaN(value) && !reg.test(value)) {
                return '���������������';
            }
        },
        codeverify: [/^[a-zA-Z0-9|-]{4,64}$/, '����ֻ����4��64λӢ���ַ������ֻ�-']
    });
    form.on("submit(editware)", function (ware) {
        var code = $("#code").val();

            var type;
            $("#edit").hide();
            if ($("#id").val() == '') {
                type = "POST";
            } else {
                type = "PUT";
            }
            $.ajax({
                type: type,
                url: "../ware",
                dataType: "json",
                data: JSON.parse(JSON.stringify(ware.field)),
                success: function (ret) {
                    if (ret.status == '1') {
                        layer.msg('�ύ�ɹ�', function () {
                            //�رպ�Ĳ���
                            // parent.location.reload();
                            //parent.layui.$("#codeSearch").val("");
                            //parent.layui.table.reload("wareListReload");
                            var ztree = parent.$.fn.zTree.getZTreeObj("treeDemo");
                            //parent.layui.$("#treeDemo").getZTreeObj("#treeDemo");
                           if($("#level").val()==1){

                               if (type == "POST") {
                                    ztree.addNodes(ztree.getNodeByParam("id", 0, null), {id:ret.data.id, pId:0, name:ret.data.name,code:ret.data.code});
                                //location.reload();
                            }else{
                                var node =  ztree.getNodeByParam("id", ret.data.id, null);
                                node.name = ret.data.name;
                                ztree.updateNode(node);

                            }
                           }

                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert('�ύʧ�ܣ�' + ret.message);
                        $("#edit").show();
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
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
                title: '��Ӧ��',
                fixed: false,
                area: ['60%', '90%'],
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
                title: '�����֯',
                fixed: false,
                area: ['60%', '90%'],
                content: "../dept/openPublicDepart.htm",

            });
        }

    }).on("blur",function(e) {
        ztFocus = true
    });

});
var obtainPart = function(id,name,ztid,code){
    document.getElementById("ztname").value = name;
    document.getElementById("ztid").value = id;

}