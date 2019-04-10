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
        elem: '#logList'  //绑定table id
        , url: 'listLog.json'  //数据请求路径
        , cellMinWidth: 80
        , height: 'full-75'
        , method: 'post'
        , cols: [[
            {checkbox: true, fixed: 'left',width:30}
            , {
                field: 'logType', align: 'center', title: '日志类型', width: 100, fixed: 'left', templet: function (d) {
                    if (d.logType == 0) {
                        return "系统日志";
                    } else {
                        return "业务日志";
                    }
                }
            }
            , {
                field: 'logObject',
                align: 'center',
                title: '日志对象',
                width: 160,
                fixed: 'left',
                templet: function (d) {
                    switch (d.logObject) {
                        case 100:
                            return "物资接收";
                        case 200:
                            return "物资入库";
                        case 300:
                            return "领用申请";
                        case 400:
                            return "物资出库";
                        case 500:
                            return "移库移位";
                        case 600:
                            return "物资退库";
                        case 700:
                            return "物资退货";
                        case 800:
                            return "物资调拨";
                        case 900:
                            return "盘点单";
                        case 1000:
                            return "库存收支报表";
                        case 1100:
                            return "组织机构";
                        case 1200:
                            return "人员管理";
                        case 1300:
                            return "菜单管理";
                        case 1400:
                            return "角色管理";
                        case 1500:
                            return "物料分类";
                        case 1600:
                            return "物料管理";
                        case 1700:
                            return "库房库区管理";
                        case 1800:
                            return "货位标签打印";
                        case 1900:
                            return "供应商管理";
                        case 2000:
                            return "使用单位管理";
                        case 2100:
                            return "申请单位管理";
                        case 2200:
                            return "单据管理";
                        case 2300:
                            return "流程管理";
                        case 2400:
                            return "任务管理";
                        case 2500:
                            return "数据字典";
                        case 2600:
                            return "仓库系统";

                    }
                }
            }
            , {
                field: 'logAction', align: 'center', title: '操作类型', width: 100, templet: function (d) {
                    switch (d.logAction) {
                        case 100:
                            return "新增";
                        case 200:
                            return "编辑";
                        case 300:
                            return "删除";
                        case 400:
                            return "查看";
                        case 500:
                            return "导入";
                        case 600:
                            return "导出";
                        case 700:
                            return "维护";
                        case 800:
                            return "制单";
                        case 900:
                            return "未审批";
                        case 1000:
                            return "审批完成";
                        case 1100:
                            return "统计";
                        case 1200:
                            return "分配角色";
                        case 1300:
                            return "分配权限";
                        case 1400:
                            return "推送到ERP";
                        case 1500:
                            return "接收ERP数据";
                        case 1600:
                            return "用户登录";
                        case 1700:
                            return "分配用户";
                        case 1800:
                            return "移除用户";
                        case 1900:
                            return "上传附件";
                    }
                }
            }
            , {field: 'logDesc', align: 'center', title: '描述', width: 300}
            , {field: 'logIp', align: 'center', title: 'IP地址', width: 120}
            , {field: 'createName', align: 'center', title: '操作人', width: 100}
            , {
                field: 'createDate', align: 'center', title: '操作时间', templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }

        ]]
        , page: true   //开启分页
        , limit: 30   //默认二十条数据一页
        , limits: [10, 20, 30, 40]  //数据分页条
        , id: 'logListReload'
    });
    var reload = function (data) {

        table.reload('logListReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                logType: $("#logType").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val()
            }

        })
    };


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
        var checkStatus = table.checkStatus('logListReload');
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
                var url = "deleteLog.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('删除成功', {
                            time: 2000,
                            end: function () {
                                location.href = 'manageLog.htm'
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
