layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    form.verify({

    });
    form.on("submit(addNews)", function (data) {
        var url = "/system/button/saveButton.json";
        var json = {
            code: $("#code").val()  //��ť����
            ,menuCode: $("#menuCode").val()  //�����˵�
            ,name: $("#name").val()  //��ť����
            ,sort: $("#sort").val()  //��ť����
            ,status: $("#status select").val()  //��ť״̬
            ,url: $("#url").val()  //�����ַ
            ,authIdentity: $("#authIdentity").val()  //Ȩ�ޱ�ʶ
            ,menuId: $("#menuId").val()  //�����˵�ID
        };
        var id = $("#id").val();
        if ('' != id) {
            url = "/system/button/updateButton.json";
        }
        //����loading
        var index = top.layer.msg('�����ύ�У����Ժ�', {icon: 16, time: false, shade: 0.8});
        // ʵ��ʹ��ʱ���ύ��Ϣ
        $.post(url, json, function (data) {
            top.layer.close(index);
            top.layer.msg(data.message, {
                time: 500
            });
            if ('1' == data.status) {
                parent.location.reload();
            }
        });
        return false;
    })
});