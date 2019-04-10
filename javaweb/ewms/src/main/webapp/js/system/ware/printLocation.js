layui.use(['table', 'layer'], function () {
    var layer = layui.layer;
    var table = layui.table;

    var ran = Math.random();

    //数据列表
    table.render({
        elem: '#wareList'  //绑定table id
        ,url:'listPrintAllocation'  //数据请求路径
        ,cellMinWidth: 60
        ,method:'GET'
        ,cols: [[
            // {indexcolumn:true,fixed:'left'},
            {checkbox: true, fixed: 'left',width:30}
             ,{field:'code',align: 'center', title:'编码',width:360}
            ,{field:'name',align: 'center', title:'名称',width:360}
            ,{field:'ztName',align: 'center', title:'库存组织',width:380}
            // ,{fixed: 'right',title: '操作', width:180,      align:'center', toolbar: '#toolBar'}
            //一个工具栏  具体请查看layui官网
        ]]
        ,page: true   //开启分页
        ,limit:30   //默认十五条数据一页
        ,limits:[10,20,30,40]   //数据分页条
        ,id: 'wareListReload'
        ,where:{ran:ran,property:4}
        , height: 'full-70'
    });

    var reload = function(){
        table.reload('wareListReload', {
            url:'listPrintAllocation'
            ,page: {
                curr: 1 //重新从第 1 页开始
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
            //正上方
            layer.msg('请至少选择一条信息', {
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
                title: '货位标签',
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
                   // var iframeWin = window[layero.find('iframe')[0]['name']];//得到iframe页的窗口对象，执行iframe页的方法：


                }

            })

        }
    });



});



