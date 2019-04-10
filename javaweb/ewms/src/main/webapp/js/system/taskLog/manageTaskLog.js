layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var laydate = layui.laydate;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //数据列表
    table.render({
        elem: '#taskLogList'  //绑定table id
        , url: 'listTaskLog.json'  //数据请求路径
        , height: 'full-75'
        , method: 'post'
        , cols: [[
            {checkbox: true, width: 30}
            , {field: 'infTaskdetailn', align: 'center', title: '接口名称', width: 240}
            , {field: 'infTaskdetailn', align: 'center', title: '任务名称', width: 120}
            , {field: 'syncResult', align: 'center', title: '同步结果', width: 120}
            , {
                field: 'infMode', align: 'center', title: '接口触发方式', width: 140
            }
            , {
                field: 'infType', align: 'center', title: '接口种类', width: 110
            }
            , {field: 'infPush', align: 'center', title: '接口提供方', width: 140}
            , {field: 'infPull', align: 'center', title: '接口调用方', width: 140}
            , {field: 'infMethod', align: 'center', title: '同步方式', width: 160}
            , {field: 'infContent', align: 'center', title: '接口详情', width: 180}
            , {field: 'infErrorContent', align: 'center', title: '错误详情', width: 180}
            , {
                field: 'createDate', align: 'center', title: '同步时间', width: 160, templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }
        ]]
        , page: true   //开启分页
        , limit: 30   //默认二十条数据一页
        , limits: [10, 20, 30, 40]  //数据分页条
        , id: 'taskLogListReload'
    });
    var reload = function (data) {

        table.reload('taskLogListReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                infTaskdetailn: $("#infTaskdetailn").val(),
                syncResult: $("#syncResult").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val()
            }

        })
    };

    table.on('tool(taskLogList)', function (event) {
        var taskLogId = event.data.id;
        layer.alert("WebService接口未开始设计！")
        //TODO:WebService接口重新触发
    });

    laydate.render({
        elem: '#startTime'
        , type: 'datetime'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'datetime'
    });

    $("#delete").on("click", function (e) {
//            layer.alert(returnCitySN.cip);
        var checkStatus = table.checkStatus('taskLogListReload');
        var datas = checkStatus.data;
        var arr = [];
        if (datas == "") {
            layer.msg("请选择一条记录进行删除", {
                offset: 't',
                anim: 6
            });
        } else {
            layer.confirm('您确定要删除？', {
                icon: 3, title: '提示信息', offset: '35%',
                btn: ['确定', '取消'] //按钮
            }, function () {
                for (var i = 0; i < datas.length; i++) {
                    arr.push(datas[i].id);
                }
                var url = "deleteTaskLog.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('删除成功', {
                            time: 2000,
                            end: function () {
                                location.href = 'manageTaskLog.htm'
                            }
                        });

                    } else {
                        layer.alert(data.message, {
                            offset: '35%'
                        });
                    }
                })
            }, function () {
                layer.closeAll();
            });
        }
    })
});