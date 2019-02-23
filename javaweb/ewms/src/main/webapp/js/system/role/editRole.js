layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    form.verify({
        code: function(value, item){
            var regex = /^[a-zA-Z0-9_]{0,}$/;
            if(value.replace(/^\s+|\s+$/g,"") ==''){
                return '�������Ϊ��';
            }
            if (!value.match(regex)) {
                return '���벻�ܺ������Ļ������ַ�';
            }
        }
    });
    form.on("submit(addNews)", function (data) {
        var url = "/system/role/saveRole.json";
        var json = {
            roleCode: $("#roleCode").val()  //��ɫ����
            ,roleName: $("#roleName").val()  //��ɫ����
            ,roleType: $("#roleType select").val() //��ɫ����
            ,sort: $("#sort").val() //����
            ,enabled: $("#enabled select").val() //״̬
            ,remark: $("#remark").val()
        };
        if ('' != $("#id").val()) {
            url = "/system/role/updateRole.json";
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