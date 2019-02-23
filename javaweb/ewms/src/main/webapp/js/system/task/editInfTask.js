layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;


    form.on("submit(editInfTask)", function (task) {
        var type;
        $("#edit").hide();
        if($("#supplySystem").val()==$("#callSystem").val()){
            layer.alert("接口提供方与接口调用方不能是同一个，请重新选择");
            return ;
        }
        if($("#id").val() =='' ){
            type = "POST";
        }else{
            type="PUT";
        }
        $.ajax({
            type: type,
            url: "../task",
            dataType:"json",
            data: JSON.parse(JSON.stringify(task.field)),
            success: function (ret) {
                if (ret.status == '1') {
                    layer.msg('提交成功', function () {
                        //关闭后的操作
                        // parent.location.reload();
                        //parent.layui.table.reload("taskListReload");
                       // parent.layer.search.value="===";
                      //  parent.resetSearch();
                      //  parent.layui.table.reload("taskListReload");
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


    form.on('select(kind)', function (kind) {
        var kindVal = kind.value;
        if(kindVal == '1'){
            $("#supplySystem").attr("disabled",true);
            $("#supplySystem").val('350');
            $("#callSystem").attr("disabled",false);
            form.render();
        }else if(kindVal == '2'){
            $("#supplySystem").attr("disabled",false);
            $("#callSystem").attr("disabled",true);
            $("#callSystem").val('350');
            form.render();
        }else{

            $("#supplySystem").attr("disabled",false);
            $("#callSystem").attr("disabled",false);
            form.render();
        }
    });


});
