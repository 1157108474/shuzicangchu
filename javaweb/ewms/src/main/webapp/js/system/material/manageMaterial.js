layui.use(['table', 'layer'], function () {
    var layer = layui.layer;
    var table = layui.table;

    var setting = {
        view: {
            showIcon:false,
            // addHoverDom: addHoverDom,
            // removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },

        callback: {
            onClick: onClick,
            onAsyncError: zTreeOnAsyncError    //���ش����fun

        }
        ,async: {
            enable: true,
            url:"spare/spares",
            autoParam:["id","level"],
//            otherParam:{"ran":ran++},
            dataType: "json",//Ĭ��text
            type:"get",//Ĭ��post
            dataFilter: filter  //�첽���غ󾭹�Filter
        }
    };

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;

        return childNodes;
    }
    function zTreeOnAsyncError(event, treeId, treeNode){
        layer.alert("����ʧ��,���Ժ�����!");
    }


    function onClick(event, treeId, treeNode, clickFlag) {

        $("#spareId").val(treeNode.id);
        $("#spareCode").val(treeNode.code);
        $("#spareName").val(treeNode.name);
        $("#code").val('');
        $("#description").val('');
        reload();

    }

    var ztree;

    $(document).ready(function(){
        ztree = $.fn.zTree.init($("#treeDemo"), setting);
    });


    var ran =Math.random();
    //�����б�
    table.render({
        elem: '#materialList'  //��table id
        ,url:'material/materials'  //��������·��
        ,cellMinWidth: 60
        ,method:'GET'
        ,cols: [[
            // {indexcolumn:true,fixed:'left'},
            {checkbox: true, fixed: 'left',width:30}
            ,{field:'code',align: 'center', title:'���ϱ���',width:110,fixed: 'left'}
            ,{field:'description',align: 'center', title:'��������',width:390,fixed: 'left'}
            /*,{field:'price',align: 'center', title:'����',width:50}*/
            ,{field:'unit',align: 'center', title:'��λ',width:50}
            ,{field:'stockUp',align: 'center', title:'�������',width:75}
            ,{field:'stockDown',align: 'center', title:'�������',width:75}
            , {
                field: 'enableSN',
                title: '��������',
                width:75,
                align: 'center',
                templet: function (data) {
                    return yesOrNo(data.enableSN);
                }
            }
            , {
                field: 'status',
                title: '״̬',
                width:50,
                align: 'center',
                templet: function (data) {
                    return statusDesc(data.status);
                }
            }

            // ,{fixed: 'right',title: '����', width:180,      align:'center', toolbar: '#toolBar'}
            //һ��������  ������鿴layui����
        ]]
        ,page: true   //������ҳ
        ,limit:20   //Ĭ�϶�ʮ������һҳ
        ,limits:[10,20,30,40]   //���ݷ�ҳ��
        ,id: 'materialListReload'
        ,where:{ran:ran}
        ,height: 'full-80'

    });

    var reload =function(){

        table.reload('materialListReload', {
            url:'material/materials'
            ,page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where:{spareCode:$("#spareCode").val(),
                   code:$("#code").val(),
                   description:$("#description").val(),
                   ran:ran++
            }

        })
    };

    $ = layui.jquery;
    $("#search").on("click", function (e) {
        reload();
    });


    $("#add").on("click",function(e){

        if($("#spareId").val()==''||$("#spareId").val()=='0'){
            layer.alert("��ѡ����������");
            return ;
        }
        layer.open({
            type: 2,
            title: '��������',
            fixed: false,
            area: ['70%', '90%'],
            content:"material/add?spareCode="+$("#spareCode").val()+"&spareId="+$("#spareId").val()

            // scrollbar:false
            ,end:function () {
                reload();
            }

        })
    });

    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('materialListReload');
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
                area: ['70%', '90%'],
                content: "material/"+data[0].id,
                // scrollbar:false
                end:function () {
                    reload();
                }
            })

        }
    });

    $("#delete").on("click", function (e) {
        var checkStatus = table.checkStatus('materialListReload');
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
                    ids += data[i].id+',';
                }

                $.ajax({
                    type: "DELETE",
                    url: "material/"+ids.substr(0, ids.length - 1),
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

            })


        }
    });





});



