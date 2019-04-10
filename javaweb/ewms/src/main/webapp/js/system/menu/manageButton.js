layui.use(['form', 'layer', 'table', 'laytpl', 'element'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
    //数据列表
    var tableIns = table.render({
        elem: '#manageButton'
        ,url: '/system/button/listButton.json'
        ,cellMinWidth: 80
        ,height: "full-60"
        ,method: 'post'
        ,page: true   //开启分页
        ,limit:30   //默认十五条数据一页
        ,limits:[15,30,45]  //数据分页条
        ,id: "manageButtonTable"
        ,where:{menuCode:$("#menuCode").val()}//初始条件
        ,cols: [
            [{type: "checkbox",fixed: "left",width: 50}
            ,{field: 'code', title: '按钮代码',align: "center",width:120}
            , {field: 'name', title: '按钮名称',align: "center",width:120}
            , {field: 'url', title: '按钮URL',align: "center",width:180}
            , {field: 'identifying',title: '权限标识',align: "center"}
            , {field: 'sort',title: '排序',align: "center",width:100}
            , {field: 'status',title: '状态',align: "center",width:100,templet: "#showStatus"}
            ]
        ]
    });
    //添加
    function addNews(edit) {
        var tit = '新增按钮';
        var menuCode = $("#menuCode").val();
        var url = '/system/button/addButton.htm?menuCode=' +menuCode ;
        if (edit) {
            tit = '编辑按钮';
			url = '/system/button/editButton.htm?code=' + edit.code+'&menuCode=' +menuCode;
        }
        layui.layer.open({
            type: 2,
            title: tit,
            area: ['500px', '320px'],
            fixed: false,
            content: url
        });
    }
    //添加
    $("#add").click(function () {
        addNews();
    });
    //编辑
    $("#edit").click(function () {
        var checkStatus = table.checkStatus('manageButtonTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            addNews(data[0]);
        } else {
            layer.msg('请选择一条数据。', {
                offset: 't',
                anim: 6
            });
        }
    });

    //批量删除
    $("#delAll").on("click", function(e) {
        var checkStatus = table.checkStatus('manageButtonTable'),
            data = checkStatus.data;
        var codes = [];
        if(data.length > 0) {
            for(var i in data) {
                codes.push(data[i].code);
            }
            layer.confirm('确定删除选中的按钮？', {
                icon: 3,
                title: '提示信息'
            }, function(index) {
                $.ajax({
                    url: "/system/button/delButton.json?codes=" + codes ,
                    type: 'post',
                    contentType: 'application/x-www-form-urlencoded; charset=GBK',
                    success: function(data) {
                        top.layer.msg(data.message, {
                            time: 500
                        });
                        if ('1' == data.status) {
                            location.reload();
                        }
                    }
                });
            })
        } else {
            layer.msg('请选择一条数据。', {
                offset: 't',
                anim: 6
            });
        }
    });
});