layui.use(['table', 'layer'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    var ran = Math.random();
    //�����б�
    table.render({
        elem: '#locationList'  //��table id
        ,url:'listLocation'  //��������·��
        ,cellMinWidth: 60
        ,method:'POST'
        ,cols: [[
            // {indexcolumn:true,fixed:'left'},
           // {checkbox: true, fixed: 'left',width:30},
             {field: 'code', align: 'center', title: '����', width: 150}
            , {field: 'name', align: 'center', title: '����', width: 150}
            , {field: 'ztName', align: 'center', title: '�����֯', width: 260}
            // ,{fixed: 'right',title: '����', width:180,      align:'center', toolbar: '#toolBar'}
            //һ��������  ������鿴layui����
        ]]
        ,page: true   //������ҳ
        ,limit:30   //Ĭ��ʮ��������һҳ
        ,limits:[10,20,30]   //���ݷ�ҳ��
        ,id: 'locationListReload'
        , where: {parentId: 0}
        , height: 'full-90'
        , trclick: function (data,tr) {
            parent.layui.$("#"+$("#pre").val()+"locationId").val(data.id);
            parent.layui.$("#"+$("#pre").val()+"locationName").val(data.name);
            parent.layui.$("#"+$("#pre").val()+"locationCode").val(data.code);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });

    var reload = function(){

        table.reload('locationListReload', {
            url:"listLocation"
            ,page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where:{
                name:$("#name").val(),
                code:$("#code").val(),
                parentId: 0,
                ran:ran++
            }

        })
    };
    $("#search").click( function () {
        reload();
    });

});



