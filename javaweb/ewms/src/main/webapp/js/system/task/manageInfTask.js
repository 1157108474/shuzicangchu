layui.use(['table', 'layer'], function () {

    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.jquery;
    var ran = Math.random();
    //�����б�
    table.render({
        elem: '#taskList'  //��table id
        ,url:'task/tasks'  //��������·��
        ,cellMinWidth: 100
        ,method:'GET'
        ,cols: [[
            {checkbox: true, fixed: 'left',width:30}
            ,{field:'name',align: 'center', title:'�ӿ���������',width:180,fixed: 'left'}
            , {
                field: 'triggerKind',
                title: '�ӿڴ�����ʽ',
                width:130,
                align: 'center',
                templet: function (data) {
                    return triggerName(data.triggerKind);
                }
            }
            , {
                field: 'kind',
                title: '�ӿ�����',
                align: 'center',
                templet: function (data) {
                    return kindName(data.kind);
                }
            }
            ,{field:'supplyName', align: 'center',title: '�ӿ��ṩ��'}
            ,{field:'callName',align: 'center', title:'�ӿڵ��÷�'}
            , {
                field: 'syncKind',
                title: 'ͬ����ʽ',
                align: 'center',
                templet: function (data) {
                    return syncName(data.syncKind);
                }
            }
            ,{field:'remark', align: 'center',title:'��ע'}

            // ,{fixed: 'right',title: '����', width:180,      align:'center', toolbar: '#toolBar'}
            //һ��������  ������鿴layui����
        ]]
        ,page: true   //������ҳ
        ,limit:20   //Ĭ��ʮ��������һҳ
        ,limits:[10,20,30,40]   //���ݷ�ҳ��
        ,id: 'taskListReload'
        ,where:{ran:ran}
        ,height: 'full-80'
    });

    var reload =function(){
        ran +=1;
        table.reload('taskListReload', {
            url:"task/tasks",
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
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
            //���Ϸ�
            layer.msg('��ѡ��һ����¼�����޸�', {
                offset: 't',
                anim: 6
            })
        }else{
            var data = checkStatus.data;
            layer.open({
                type: 2,
                title: '������Ϣ',
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
            //���Ϸ�
            layer.msg('������ѡ��һ����¼ɾ��', {
                offset: 't',
                anim: 6
            })
        }else{

            layer.confirm("ȷ��ɾ����"+length+"����¼",function(){
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
                                layer.msg('ɾ���ɹ�', function () {
                                    //�رպ�Ĳ���
                                    // parent.location.reload();
                                    reload();
                                });
                            } else {
                                layer.alert('ɾ��ʧ�ܣ�' + ret.message);
                            }
                        },
                        error: function (XMLHttpRequest) {
                            layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);

                        }
                    });
                });



        }
    });

    $("#add").on("click",function(e){
        layer.open({
            type: 2,
            title: '��������',
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



