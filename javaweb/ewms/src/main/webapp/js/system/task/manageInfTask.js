layui.use(['table', 'layer'], function () {

    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.jquery;
    var ran = Math.random();
    //数据列表
    table.render({
        elem: '#taskList'  //绑定table id
        ,url:'task/tasks'  //数据请求路径
        ,cellMinWidth: 100
        ,method:'GET'
        ,cols: [[
            {checkbox: true, fixed: 'left',width:30}
            ,{field:'name',align: 'center', title:'接口任务名称',width:180,fixed: 'left'}
            , {
                field: 'triggerKind',
                title: '接口触发方式',
                width:130,
                align: 'center',
                templet: function (data) {
                    return triggerName(data.triggerKind);
                }
            }
            , {
                field: 'kind',
                title: '接口种类',
                align: 'center',
                templet: function (data) {
                    return kindName(data.kind);
                }
            }
            ,{field:'supplyName', align: 'center',title: '接口提供方'}
            ,{field:'callName',align: 'center', title:'接口调用方'}
            , {
                field: 'syncKind',
                title: '同步方式',
                align: 'center',
                templet: function (data) {
                    return syncName(data.syncKind);
                }
            }
            ,{field:'remark', align: 'center',title:'备注'}

            // ,{fixed: 'right',title: '操作', width:180,      align:'center', toolbar: '#toolBar'}
            //一个工具栏  具体请查看layui官网
        ]]
        ,page: true   //开启分页
        ,limit:20   //默认十五条数据一页
        ,limits:[10,20,30,40]   //数据分页条
        ,id: 'taskListReload'
        ,where:{ran:ran}
        ,height: 'full-80'
    });

    var reload =function(){
        ran +=1;
        table.reload('taskListReload', {
            url:"task/tasks",
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where:{name:$("#name").val(),ran:ran}

        })
    };

    var resetSearch = function(){
        $("#name").val('');
    };


    $("#search").on("click", function (e) {
        reload();
    });





    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('taskListReload');
        if (checkStatus.data.length!=1  ){
            //正上方
            layer.msg('请选择一条记录进行修改', {
                offset: 't',
                anim: 6
            })
        }else{
            var data = checkStatus.data;
            layer.open({
                type: 2,
                title: '任务信息',
                fixed: false,
                area: ['60%', '90%'],
                content: "task/"+data[0].id,
                // scrollbar:false
                end:function(){
                        //resetSearch();
                        reload();
                }

            })

        }
    });

    $("#delete").on("click", function (e) {
        var checkStatus = table.checkStatus('taskListReload');
        var length = checkStatus.data.length;
        if (length<1  ){
            //正上方
            layer.msg('请至少选择一条记录删除', {
                offset: 't',
                anim: 6
            })
        }else{

            layer.confirm("确认删除这"+length+"条记录",function(){
                    var data = checkStatus.data;
                    var ids='';
                    for (var i = 0; i < length; i++) {
                        ids +=data[i].id+',';
                    }

                    $.ajax({
                        type: "DELETE",
                        url: "task/"+ids.substr(0, ids.length - 1),
                        dataType:"json",
                        success: function (ret) {
                            if (ret.status == '1') {
                                layer.msg('删除成功', function () {
                                    //关闭后的操作
                                    // parent.location.reload();
                                    reload();
                                });
                            } else {
                                layer.alert('删除失败：' + ret.message);
                            }
                        },
                        error: function (XMLHttpRequest) {
                            layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);

                        }
                    });
                });



        }
    });

    $("#add").on("click",function(e){
        layer.open({
            type: 2,
            title: '新增任务',
            fixed: false,
            area: ['60%', '90%'],
            content:"task/add",
            // scrollbar:false
            end:function(){
                resetSearch();
                reload();
            }
        })
    });
});



