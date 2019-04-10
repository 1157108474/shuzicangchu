layui.use(['table', 'layer'], function () {
    var layer = layui.layer;
    var table = layui.table;

    var ran = Math.random();

    //�����б�
    table.render({
        elem: '#wareList'  //��table id
        ,url:'listPrintAllocation'  //��������·��
        ,cellMinWidth: 60
        ,method:'GET'
        ,cols: [[
            // {indexcolumn:true,fixed:'left'},
            {checkbox: true, fixed: 'left',width:30}
             ,{field:'code',align: 'center', title:'����',width:360}
            ,{field:'name',align: 'center', title:'����',width:360}
            ,{field:'ztName',align: 'center', title:'�����֯',width:380}
            // ,{fixed: 'right',title: '����', width:180,      align:'center', toolbar: '#toolBar'}
            //һ��������  ������鿴layui����
        ]]
        ,page: true   //������ҳ
        ,limit:30   //Ĭ��ʮ��������һҳ
        ,limits:[10,20,30,40]   //���ݷ�ҳ��
        ,id: 'wareListReload'
        ,where:{ran:ran,property:4}
        , height: 'full-70'
    });

    var reload = function(){
        table.reload('wareListReload', {
            url:'listPrintAllocation'
            ,page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where:{name:$("#name").val(),
                   begin:$("#begin").val(),
                   end:$("#end").val(),
                   ran:ran++
            }

        })
    };


    $("#search").click( function () {
        reload();
    });


    $("#print").click( function (){
        var checkStatus = table.checkStatus('wareListReload');
        var length = checkStatus.data.length;
        if (length < 1  ){
            //���Ϸ�
            layer.msg('������ѡ��һ����Ϣ', {
                offset: 't',
                anim: 6
            })
        }else{
            //var data = checkStatus.data;
            // var jsonArr=[];
            // var json;
            //
            // for (var i = 0; i < length; i++) {
            //     json = {};
            //     json.code=data[i].code;
            //     json.name=data[i].name;
            //     jsonArr.push(json);
            //     // console.log(JSON.stringify(jsonArr));
            // }

            layer.open({
                type: 2,
                title: '��λ��ǩ',
                fixed: false,
                area: ['60%', '60%'],
                content: "../print/allocation",
                // scrollbar:false
                success: function(layero, index){
                    var body = layer.getChildFrame('body', index);
                    body.find('input').val(length);
             //       body.find('img').attr("alt",data[0].code)
             //       body.find('img').attr("src","print1D/"+data[0].code);
            //        body.find('span').innerHTML = data[0].name;
                   // var iframeWin = window[layero.find('iframe')[0]['name']];//�õ�iframeҳ�Ĵ��ڶ���ִ��iframeҳ�ķ�����


                }

            })

        }
    });



});



