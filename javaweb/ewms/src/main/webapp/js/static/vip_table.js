/**
 * Created by Administrator on 2018/05/30.
 * @name:    后台表格JS功能
 * @author: yyb
 */
layui.define(['table', 'layer', 'element'], function (exports) {
    var table = layui.table;
    var $ = layui.jquery;

    // 封装方法
    var mod = {
        //打开全屏页面
        openWholePage: function (tit, url) {
            var index = layui.layer.open({
                title: tit
                , type: 2
                , content: url
                , success: function (layero, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回上一层', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
            layui.layer.full(index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
            $(window).on("resize", function () {
                layui.layer.full(index);
            })
        }
        //打开固定大小页面
        , openPage: function (tit, url, width, height) {
            layui.layer.open({
                title: tit
                , type: 2
                , fixed: false
                , area: [width, height]
                , content: url
            })
        }


        // 删除公共方法 tableId:列表Id;tableIns:绑定列表的方法,paramName:获取参数的名称;
        //             slogan:提示语;url:地址+后台接收参数如（/system/role/delRoles.json?ids=）;
        , delAll: function (tableId, tableIns, paramName, slogan, url) {
            var checkStatus = table.checkStatus(tableId);
            var data = checkStatus.data;
            var ids = [];
            if (data.length > 0) {
                for (var i in data) {
                    ids.push(data[i][paramName]);
                }
                layer.confirm(slogan, {icon: 3, title: '提示信息'}, function (index) {
                    $.post(url + ids, function (data) {
                        if (tableIns != null || tableIns != '') {
                            tableIns.reload();
                        }
                        layer.msg(data.message);
                        layer.close(index);
                    })
                })
            } else {
                layer.msg('请选择要删除的数据。', {offset: 't', anim: 6});
            }
        }
        //返回年-月-日型的日期
        , dateformat: function (dateTime) {
            if (null == dateTime || '' == dateTime) {
                return '';
            } else {
                var date = new Date(dateTime);
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var day = date.getDate();
                return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
            }
        }
        //返回年-月-日 时：分：秒型的日期
        , datetimeformat: function (dateTime) {
            if (null == dateTime || '' == dateTime) {
                return '';
            } else {
                var date = new Date(dateTime);
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var day = date.getDate();
                var hour = date.getHours();
                var minute = date.getMinutes();
                var seconds = date.getSeconds();
                return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + " " + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (seconds < 10 ? "0" + seconds : seconds);
            }
        }
    };
    // 输出
    exports('vip_table', mod);
});

