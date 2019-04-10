layui.use(['table', 'layer'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var setting = {
        view: {
            showIcon:false,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }

    };
    function onClick(event, treeId, treeNode, clickFlag) {
        $("#id").val(treeNode.id);
        $("#level").val(treeNode.level);
        $("#code").val(treeNode.code);
        $("#name").val(treeNode.name);
        reload();

    }
    var ran = Math.random();
    var ztree;
    $(document).ready(function(){
        $.ajax({
            type: "GET",
            url: "ware/tree",
            dataType: "json",
            data:{ran:ran++},
            success: function (ret) {
                ztree = $.fn.zTree.init($("#treeDemo"), setting, ret);
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });

    });





    //数据列表
    table.render({
        elem: '#wareList'  //绑定table id
        ,url:'ware/list'  //数据请求路径
        ,cellMinWidth: 60
        ,method:'GET'
        ,height:"full-70"
        ,cols: [[
            // {indexcolumn:true,fixed:'left'},
            {checkbox: true, fixed: 'left',width:30}
            , {field: 'parentCode', align: 'center', title: '上级编码', width: 90, fixed: 'left'}
            ,{field:'parentName',align: 'center', title:'上级名称',width:140,fixed: 'left'}
            , {field: 'code', align: 'center', title: '编码', width: 160, fixed: 'left'}
            , {field: 'name', align: 'center', title: '名称', width: 160, fixed: 'left'}
            , {field: 'levelCount', align: 'center', title: '级别', width: 60}
            , {
                field: 'property',
                title: '性质',
                width: 60,
                align: 'center',
                templet: function (data) {
                    return propertyName(data.property);
                }
            }
            , {field: 'sort', align: 'center', title: '排序', width: 60}
            , {
                field: 'status',
                title: '状态',
                width: 60,
                align: 'center',
                templet: function (data) {
                    return statusDesc(data.status);
                }
            }
            , {field: 'ztName', align: 'center', title: '库存组织', width: 220}

            // ,{fixed: 'right',title: '操作', width:180,      align:'center', toolbar: '#toolBar'}
            //一个工具栏  具体请查看layui官网
        ]]
        ,page: true   //开启分页
        ,limit:30   //默认十五条数据一页
        ,limits:[10,20,30,40]  //数据分页条
        ,id: 'wareListReload'
        ,where:{ran:ran}
        , height: 'full-80'
    });

    var reload = function(){

        table.reload('wareListReload', {
            url:'ware/list'
            ,page: {
                curr: 1 //重新从第 1 页开始
            },
            where:{idStr:$("#id").val(),code:$("#codeSearch").val(),ran:ran++
            }

        })
    };


    $("#search").click( function () {
        reload();
    });

    $("#add").click( function (){
        if($("#id").val()==''){
            $("#id").val('0');
            $("#code").val('');
            $("#level").val("0");
            $("#name").val("仓库信息");
        }
        layer.open({
            type: 2,
            title: '新增库房库位',
            fixed: false,
            area: ['60%', '90%'],
            content:"ware/add",
            // scrollbar:false
            end:function () {
                reload();
            }
        })
    });

    $("#edit").click( function (){
        var checkStatus = table.checkStatus('wareListReload');
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
                title: '库房库位信息',
                fixed: false,
                area: ['60%', '92%'],
                content: "ware/"+data[0].id,
                // scrollbar:false
                end:function () {
                    reload();

                }
            })

        }
    });

    $("#delete").click( function (){
        var enableDeleteHouse = true;
        if($("#id").val()!="" && $("#id").val()!=0){
            enableDeleteHouse = false;

        }
         var checkStatus = table.checkStatus('wareListReload');
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
                var ztreeIds=new Array();
                var j = 0;
                for (var i = 0; i < length; i++) {
                    ids +=data[i].id+',';

                    if(!enableDeleteHouse){
                        if(data[i].levelCount==1){
                            layer.alert("库房级别下不得删除库房");
                            return;
                        }
                    }else{
                        if(data[i].levelCount==1){
                            ztreeIds[j++] = data[i].id;
                        }
                    }
                }

                $.ajax({
                    type: "DELETE",
                    url: "ware/"+ids.substr(0, ids.length - 1),
                    dataType:"json",
                    success: function (ret) {
                        if (ret.status == '1') {
                            layer.msg('删除成功', function () {
                                //关闭后的操作
                                // parent.location.reload();
                               reload();
                               for(var i=0;i<j;i++){
                                   ztree.removeNode(ztree.getNodeByParam("id", ztreeIds[i], null));
                               }
                            });
                        } else {
                            layer.alert('删除失败：' + ret.message);
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                    }
                });

            })


        }
    });





});



