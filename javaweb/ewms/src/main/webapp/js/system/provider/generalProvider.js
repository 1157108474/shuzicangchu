layui.use(['laydate', 'form', 'table', 'layer','element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var pid = $("#providerId").val();
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //�����б�
    table.render({
        elem: '#providerList'  //��table id
        ,url:'listProviderGeneral.json?id='+pid  //��������·��
        ,cellMinWidth: 80
        ,method:'post'
        ,cols: [[
            {checkbox: true, fixed: 'left'}
            ,{field:'code',align:'center', title: '����',fixed:'left'}
            ,{field:'name',align:'center', title:'��Ӧ������', fixed: 'left'}
        ]]
        ,page: true   //������ҳ
        ,limit:10   //Ĭ��ʮ������һҳ
        ,limits:[10,20,30,50]  //���ݷ�ҳ��
        ,id: 'providerListReload'
    });
    var reload =function(data){

        table.reload('providerListReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: JSON.parse(JSON.stringify(data.field))

        })
    };

    //����
    $("#add").on("click",function(e){
        var checkStatus = table.checkStatus('providerListReload');
        if(checkStatus.data.length !=1){
            layer.msg("��ѡ��һ����¼��������",{
                offset:'t',
                anim:6
            })
        }else {
            var data = checkStatus.data;

            parent.layui.$("#providerId").val(data[0].id);
            parent.layui.$("#providerName").val(data[0].name);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
});