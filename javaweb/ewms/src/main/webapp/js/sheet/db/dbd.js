layui.use(['form', 'table', 'layer'], function () {


    var table = layui.table;
    var $ = layui.$;



    //�����б�
    var sheetgrid = table.render({
        elem: '#sheetgrid'
        , url: 'dbdSheets?ztId=' + $("#ztId").val() + "&type=" + $("#type").val()
        , cellMinWidth: 80
        , height: "full-110"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "sheetgridReload"
        , cols: [
            [
                {field: 'code', title: '��������', align: "center"}
            ]
        ], trclick: function (data,tr) {
            parent.layui.$("#dbId").val(data.id);
            parent.layui.$("#dbCode").val(data.code);
            parent.DBDGeneral(data);
            var index = parent.layer.getFrameIndex(window.name);
            tr.append($("#dbId"));
            parent.layer.close(index);
        }
    });



    var reload =function(data){
        table.reload('sheetgridReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {"ztId":$("#ztId").val(),"type":$("type").val(),"code":$("code").val()}


        })
    };

    $("#search").click( function () {
        reload();
    });

});

