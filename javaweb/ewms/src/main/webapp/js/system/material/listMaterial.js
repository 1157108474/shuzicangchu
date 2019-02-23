layui.use(['table', 'layer'], function () {
    var layer = layui.layer;
    var table = layui.table;

    var setting = {
        view: {
            showIcon: false,
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
        , async: {
            enable: true,
            url: "/system/spare/spares",
            autoParam: ["id", "level"],
//            otherParam:{"ran":ran++},
            dataType: "json",//Ĭ��text
            type: "get",//Ĭ��post
            dataFilter: filter  //�첽���غ󾭹�Filter
        }
    };

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;

        return childNodes;
    }

    function zTreeOnAsyncError(event, treeId, treeNode) {
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

    $(document).ready(function () {
        ztree = $.fn.zTree.init($("#treeDemo"), setting);
    });


    var ran = Math.random();
    //�����б�
    table.render({
        elem: '#materialList'  //��table id
        , url: '/system/material/materials'  //��������·��
        , cellMinWidth: 60
        , method: 'GET'
        , cols: [[
            // {indexcolumn:true,fixed:'left'},
            {field: 'name', align: 'center', title: '��������', width: 110, fixed: 'left'}
            , {field: 'code', align: 'center', title: '���ϱ���', width: 110, fixed: 'left'}
            , {field: 'specifications', align: 'center', title: '���Ϲ��', width: 110}
            , {field: 'models', align: 'center', title: '�����ͺ�', width: 110}
            , {field: 'unit', align: 'center', title: '��λ', width: 45}
            , {field: 'brand', align: 'center', title: '����Ʒ��', width: 110}
            , {field: 'description', align: 'center', title: '��������', width: 400}
            // ,{fixed: 'right',title: '����', width:180,      align:'center', toolbar: '#toolBar'}
            //һ��������  ������鿴layui����
        ]]
        , page: true   //������ҳ
        , limit: 20   //Ĭ�϶�ʮ������һҳ
        , limits: [10, 20, 30, 40]   //���ݷ�ҳ��
        , id: 'materialListReload'
        , where: {ran: ran}
        , height: 'full-80'
        , trclick: function (data, tr) {
            parent.setMaterialInfo(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

    });

    var reload = function () {

        table.reload('materialListReload', {
            url: '/system/material/materials'
            , page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                spareCode: $("#spareCode").val(),
                name: $("#name").val(),
                ran: ran++
            }

        })
    };

    $ = layui.jquery;
    $("#search").on("click", function (e) {
        console.log($("#name").val());
        reload();
    });


    $("#add").on("click", function (e) {

        if ($("#spareId").val() == '' || $("#spareId").val() == '0') {
            layer.alert("��ѡ����������");
            return;
        }
        layer.open({
            type: 2,
            title: '��������',
            fixed: false,
            area: ['70%', '90%'],
            content: "material/add"

            // scrollbar:false
            , end: function () {
                reload();
            }

        })
    });

    $("#edit").on("click", function (e) {
        var checkStatus = table.checkStatus('materialListReload');
        if (checkStatus.data.length != 1) {
            //���Ϸ�
            layer.msg('��ѡ��һ����¼�����޸�', {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            layer.open({
                type: 2,
                title: '������Ϣ',
                fixed: false,
                area: ['70%', '90%'],
                content: "material/" + data[0].id,
                // scrollbar:false
                end: function () {
                    reload();
                }
            })

        }
    });

    $("#delete").on("click", function (e) {
        var checkStatus = table.checkStatus('materialListReload');
        var length = checkStatus.data.length;
        if (length < 1) {
            //���Ϸ�
            layer.msg('������ѡ��һ����¼ɾ��', {
                offset: 't',
                anim: 6
            })
        } else {
            layer.confirm("ȷ��ɾ����" + length + "����¼", function () {

                var data = checkStatus.data;
                var ids = '';
                for (var i = 0; i < length; i++) {
                    ids += data[i].id + ',';
                }

                $.ajax({
                    type: "DELETE",
                    url: "material/" + ids.substr(0, ids.length - 1),
                    dataType: "json",
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



