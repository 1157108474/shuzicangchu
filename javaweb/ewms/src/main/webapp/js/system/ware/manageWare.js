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
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });

    });





    //�����б�
    table.render({
        elem: '#wareList'  //��table id
        ,url:'ware/list'  //��������·��
        ,cellMinWidth: 60
        ,method:'GET'
        ,height:"full-70"
        ,cols: [[
            // {indexcolumn:true,fixed:'left'},
            {checkbox: true, fixed: 'left',width:30}
            , {field: 'parentCode', align: 'center', title: '�ϼ�����', width: 90, fixed: 'left'}
            ,{field:'parentName',align: 'center', title:'�ϼ�����',width:140,fixed: 'left'}
            , {field: 'code', align: 'center', title: '����', width: 160, fixed: 'left'}
            , {field: 'name', align: 'center', title: '����', width: 160, fixed: 'left'}
            , {field: 'levelCount', align: 'center', title: '����', width: 60}
            , {
                field: 'property',
                title: '����',
                width: 60,
                align: 'center',
                templet: function (data) {
                    return propertyName(data.property);
                }
            }
            , {field: 'sort', align: 'center', title: '����', width: 60}
            , {
                field: 'status',
                title: '״̬',
                width: 60,
                align: 'center',
                templet: function (data) {
                    return statusDesc(data.status);
                }
            }
            , {field: 'ztName', align: 'center', title: '�����֯', width: 220}

            // ,{fixed: 'right',title: '����', width:180,      align:'center', toolbar: '#toolBar'}
            //һ��������  ������鿴layui����
        ]]
        ,page: true   //������ҳ
        ,limit:30   //Ĭ��ʮ��������һҳ
        ,limits:[10,20,30,40]  //���ݷ�ҳ��
        ,id: 'wareListReload'
        ,where:{ran:ran}
        , height: 'full-80'
    });

    var reload = function(){

        table.reload('wareListReload', {
            url:'ware/list'
            ,page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
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
            $("#name").val("�ֿ���Ϣ");
        }
        layer.open({
            type: 2,
            title: '�����ⷿ��λ',
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
            //���Ϸ�
            layer.msg('��ѡ��һ����¼�����޸�', {
                offset: 't',
                anim: 6
            })
        }else{
            var data = checkStatus.data;
            layer.open({
                type: 2,
                title: '�ⷿ��λ��Ϣ',
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
            //���Ϸ�
            layer.msg('������ѡ��һ����¼ɾ��', {
                offset: 't',
                anim: 6
            })
        }else{
            layer.confirm("ȷ��ɾ����"+length+"����¼",function(){
                var data = checkStatus.data;
                var ids='';
                var ztreeIds=new Array();
                var j = 0;
                for (var i = 0; i < length; i++) {
                    ids +=data[i].id+',';

                    if(!enableDeleteHouse){
                        if(data[i].levelCount==1){
                            layer.alert("�ⷿ�����²���ɾ���ⷿ");
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
                            layer.msg('ɾ���ɹ�', function () {
                                //�رպ�Ĳ���
                                // parent.location.reload();
                               reload();
                               for(var i=0;i<j;i++){
                                   ztree.removeNode(ztree.getNodeByParam("id", ztreeIds[i], null));
                               }
                            });
                        } else {
                            layer.alert('ɾ��ʧ�ܣ�' + ret.message);
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                    }
                });

            })


        }
    });





});



