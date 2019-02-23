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
            onAsyncError: zTreeOnAsyncError    //加载错误的fun

        }
        , async: {
            enable: true,
            url: "/system/spare/spares",
            autoParam: ["id", "level"],
//            otherParam:{"ran":ran++},
            dataType: "json",//默认text
            type: "get",//默认post
            dataFilter: filter  //异步返回后经过Filter
        }
    };

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;

        return childNodes;
    }

    function zTreeOnAsyncError(event, treeId, treeNode) {
        layer.alert("加载失败,请稍候重试!");
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
    //数据列表
    table.render({
        elem: '#materialList'  //绑定table id
        , url: '/system/material/materials'  //数据请求路径
        , cellMinWidth: 60
        , method: 'GET'
        , cols: [[
            // {indexcolumn:true,fixed:'left'},
            {field: 'name', align: 'center', title: '物料名称', width: 110, fixed: 'left'}
            , {field: 'code', align: 'center', title: '物料编码', width: 110, fixed: 'left'}
            , {field: 'specifications', align: 'center', title: '物料规格', width: 110}
            , {field: 'models', align: 'center', title: '物料型号', width: 110}
            , {field: 'unit', align: 'center', title: '单位', width: 45}
            , {field: 'brand', align: 'center', title: '物料品牌', width: 110}
            , {field: 'description', align: 'center', title: '物料描述', width: 400}
            // ,{fixed: 'right',title: '操作', width:180,      align:'center', toolbar: '#toolBar'}
            //一个工具栏  具体请查看layui官网
        ]]
        , page: true   //开启分页
        , limit: 20   //默认二十条数据一页
        , limits: [10, 20, 30, 40]   //数据分页条
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
                curr: 1 //重新从第 1 页开始
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
            layer.alert("请选择物料类型");
            return;
        }
        layer.open({
            type: 2,
            title: '新增物料',
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
            //正上方
            layer.msg('请选择一条记录进行修改', {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            layer.open({
                type: 2,
                title: '物料信息',
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
            //正上方
            layer.msg('请至少选择一条记录删除', {
                offset: 't',
                anim: 6
            })
        } else {
            layer.confirm("确认删除这" + length + "条记录", function () {

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

            })


        }
    });


});



