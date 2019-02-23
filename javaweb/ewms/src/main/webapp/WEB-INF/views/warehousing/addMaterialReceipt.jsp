<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增物料接收单</title>
    <link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote" style="margin-left:10px;margin-top: 10px">
        <%--<a href="javascript:void(0);" class="layui-btn save"   id="save" >保存</a>
        <a href="javascript:void(0);" class="layui-btn commit"   id="commit">提交</a>
        <a href="javascript:void(0);" class="layui-btn layui-btn-danger print"  id="print">标签打印</a>--%>
        <c:forEach items="${button}" var="button">
            <a href="javascript:void(0);" class="layui-btn" id="${button.code}">${button.name}</a>
        </c:forEach>
    </blockquote>
    <input id="taskId" value="${taskId}" type="hidden"><%--流程用隐藏域--%>
    <input id="menuCode" value="${menuCode}" type="hidden">
    <span style="font-weight: bold;margin-left: 10px;margin-top: 10px">物料接收单</span>
    <hr/>
    <div style="width:33%;float: left">
        <div class="layui-form-item">
            <label class="layui-form-label">采购订单编号</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="采购订单编号" class="layui-input" type="text">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">发放号</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="发放号" class="layui-input" type="text" required>
            </div>
        </div>
    </div>
    <div style="width: 33%;float: left">
        <div class="layui-form-item">
            <label class="layui-form-label">供应商</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="供应商" class="layui-input" type="text" required>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">订单类型</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="订单类型" class="layui-input" type="text" required>
            </div>
        </div>
    </div>
    <div style="width: 33%;float: left">
        <div class="layui-form-item">
            <label class="layui-form-label">业务实体</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="业务实体" class="layui-input" type="text" required>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">库存组织</label>
            <div class="layui-input-block" style="width: 57%">
                <input name="name" autocomplete="on" placeholder="库存组织" class="layui-input" type="text">
            </div>
        </div>
    </div>
    <div style="clear: both;"></div>

    <span style="font-weight: bold;margin-left: 10px">明细列表</span>
    <hr/>

    <div class="layui-tab" style="margin-left: 20px">
        <ul class="layui-tab-title">
            <li class="layui-this">明细列表</li>
            <li>单据附件</li>
            <li>操作履历</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <blockquote class="layui-elem-quote">
                    <a href="javascript:void(0);" class="layui-btn">添加明细</a>
                    <a href="javascript:void(0);" class="layui-btn">删除明细</a>
                </blockquote>
                <table id="providerList" lay-filter="providerList"></table>
            </div>
            <div class="layui-tab-item">内容2</div>
            <div class="layui-tab-item">内容3</div>
        </div>
    </div>

</div>
</body>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/js/system/act/processDetail.js"></script>
<script>
    layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

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
            elem: '#providerList'  //绑定table id
            , url: 'listProvider.json'  //数据请求路径
            , cellMinWidth: 80
            , height: 'full-200'
            , method: 'post'
            , cols: [[
                {checkbox: true, fixed: 'left'}
                , {field: 'code', align: 'center', title: '编码', width: 100, fixed: 'left'}
                , {field: 'name', align: 'center', title: '供应商名称', width: 160, fixed: 'left'}
                , {field: 'address', align: 'center', title: '供应商地址', width: 160}
                , {field: 'zipCode', align: 'center', title: '邮政编码', width: 100}
                , {field: 'contactPerson', align: 'center', title: '联系人', width: 80}
                , {field: 'contractPhone', align: 'center', title: '联系电话', width: 140}
                , {field: 'fax', align: 'center', title: '传真', width: 100}
                , {field: 'email', align: 'center', title: '电子邮箱', width: 140}
                , {
                    field: 'status', align: 'center', title: '是否启用', minWidth: 100, templet: function (d) {
                        if (d.status == 1) {
                            return "启用";
                        } else {
                            return "禁用";
                        }
                    }
                }
                , {field: 'memo', align: 'center', title: '备注'}
            ]]
            , page: true   //开启分页
            , limit: 10   //默认十条数据一页
            , limits: [10, 20, 30, 50]  //数据分页条
            , id: 'providerListReload'
        });
        var reload = function (data) {

            table.reload('providerListReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: JSON.parse(JSON.stringify(data.field))

            })
        };

        //编辑
        $("#edit").on("click", function (e) {
            var checkStatus = table.checkStatus('providerListReload');
            if (checkStatus.data.length != 1) {
                layer.msg("请选择一条记录进行编辑", {
                    offset: 't',
                    anim: 6
                })
            } else {
                var data = checkStatus.data;
                layer.open({
                    type: 2,
                    title: '编辑供应商',
                    fixed: false,
                    area: ['60%', '90%'],
                    content: "/system/provider/updateProvider.htm?id=" + data[0].id
                })
            }
        });

        //新增
        $("#add").on("click", function (e) {
            layer.open({
                type: 2,
                title: '新增供应商',
                //shadeClose: true,
                //shade: 0.8,
                fixed: false,
                area: ['60%', '90%'],
                content: "/system/provider/updateProvider.htm"
            })
        });

        $("#delete").on("click", function (e) {
            var checkStatus = table.checkStatus('providerListReload');
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
                    var url = "delProvider.json?ids=" + arr;
                    $.post(url, function (data) {
                        if (data.success == true) {
                            layer.msg('删除成功', {
                                time: 2000,
                                end: function () {
                                    location.href = 'manageProvider.htm'
                                }
                            });

                        } else {
                            layer.alert("删除失败!", {
                                offset: '35%'
                            });
                        }
                    })
                }, function () {
                    layer.closeAll();
                });
            }
        })
    })


</script>

</html>