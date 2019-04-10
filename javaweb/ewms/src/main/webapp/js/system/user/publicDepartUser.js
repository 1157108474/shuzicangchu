layui.use(['form', 'table', 'layer'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;

       var setting = {
        view: {
            showIcon: false,
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
    //拼装树形结构
    $(document).ready(function () {
        $.ajax({
            type: "post",
            url: "/system/dept/publicDepart.json",
            dataType: "json",
            success: function (ret) {
                var treeObj=$.fn.zTree.init($("#treeDemo"), setting, ret);
                //展开tree
                treeObj.expandAll(true);
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    });

    //用户数据列表
    var tableIns =table.render({
        elem: '#manageUser'  //绑定table id
        , url: '/system/user/listUser.json'  //数据请求路径
        , cellMinWidth: 80
        , height:"270"  //自适应高度
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十条数据一页
        , limits: [10, 20, 30, 50]  //数据分页条
        , id: 'manageUserTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'code', align: 'center', title: '员工编号', width: 100}
            , {field: 'name', align: 'center', title: '姓名', width: 100}
            , {field: 'parent', align: 'center', title: '所属部门', minWidth: 180,templet: function (d) {
                if (null==d.parent ) {
                    return '';
                } else {
                    return d.parent.name;
                }
            }}
            , {field: 'status', align: 'center', title: '状态', width: 80 ,templet: "#showStatus"}
        ]]
    });
    //搜索
    $("#btnSearch").click(function () {
        var json = [];
        if("name"==$("#select select").val()){
            json['name'] = $("#content").val() ;
        }else if("code"==$("#select select").val()){
            json['code'] = $("#content").val() ;
        }else if("parentName"==$("#select select").val()){
            json['departName'] = $("#content").val();
        }
        table.reload("manageUserTable", {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: json
        })
    });
    //tree-点击查看方法
    function onClick(event, treeId, treeNode, clickFlag) {
         table.reload("manageUserTable", {
             page: {
                 curr: 1 //重新从第 1 页开始
             }
             , where:{
                 departId:treeNode.id
             }
         })
    }
    //保存
    $("#addNews").click(function () {
        var checkStatus = table.checkStatus('manageUserTable');
        var data = checkStatus.data;
        if (data.length != 1) {
            layer.msg("请选择一条记录", {
                offset: 't',
                anim: 6
            })
        } else {
            window.parent.obtainUser(data[0],$("#type").val());
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
});







