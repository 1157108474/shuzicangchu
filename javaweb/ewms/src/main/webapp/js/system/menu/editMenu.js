layui.config({
    base: '/plugins/static/js/' // ģ��Ŀ¼
}).use(['form', 'layer', 'treeselect'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        $ = layui.jquery;
    var treeselect = layui.treeselect;

    $.get('/system/menu/listMenuStatus.json', function (ret) {
        treeselect.render({
            elem: "#menutreeselect",
            data: ret.data,
            method: "post"
        });
    });

    // ����select�¼�
    form.on('select(type)', function (data) {
        var selectValue = data.value;
        var selectName = data.elem[data.elem.selectedIndex].text;
        console.log(selectName);
        if (selectName == 'Ŀ¼') {
            hideChange();
        } else if (selectName == 'ҳ��') {
            showChange();
        }
        form.render('select'); //������Ⱦselect���������Ҫ
    });


    //ҳ��ȥ����������
    function showChange() {
        //ҳ��
        $("#url").removeClass(" layui-disabled");
        $("#authIdentity").removeClass(" layui-disabled");
        $("#menuCode").removeClass(" layui-disabled");
        $("#menutreeselect").removeClass(" layui-disabled");
        $("#url").attr("disabled", false);
        $("#authIdentity").attr("disabled", false);
        $("#menuCode").attr("disabled", false);
        $("#menutreeselect").attr("disabled", false);
    }

    //Ŀ¼
    function hideChange() {
        //Ŀ¼
        $("#url").addClass(" layui-disabled");
        $("#authIdentity").addClass(" layui-disabled");
        $("#url").attr("disabled", true);
        $("#authIdentity").attr("disabled", true);
        $("#url").val('');
        $("#authIdentity").val('');
    }


    form.verify({});
    form.on("submit(addNews)", function (data) {
        var menutreeselect = $("#menutreeselect").val();
        var menuName = $("#menuName").val();
        var menuCode = (menuName == menutreeselect? $("#menuCode").val():menutreeselect);
        menuCode =(''==menuCode?'0':menuCode);
        var type = $("#type select").val();

        if (type == '2' && '0' == menuCode) {
            top.layer.msg("ҳ��������ϼ��˵�", {time: 500});
            return false;
        }
        var url = "/system/menu/saveMenu.json";
        var json = {
            menuCode: menuCode //�ϼ��˵�
            , type: $("#type select").val() //�˵�����
            , code: $("#code").val()	//�˵�����
            , name: $("#name").val()	//�˵�����
            , sort: $("#sort").val()	//�˵�����
            , buttonMode: $("#buttonMode select").val()  //��ťģʽ
            , status: $("#status select").val()  //״̬
            , icon: $("#icon").val()  //�˵�ͼ��
            , url: $("#url").val()  //�˵���ַ
            , authIdentity: $("#authIdentity").val()  //Ȩ�ޱ�ʶ
        };
        var id = $("#id").val();
        if ('' != id) {
            json['id'] = id;
            url = "/system/menu/updateMenu.json";
        }
        //����loading
        var index = top.layer.msg('�����ύ�У����Ժ�', {
            icon: 16,
            time: false,
            shade: 0.8
        });
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