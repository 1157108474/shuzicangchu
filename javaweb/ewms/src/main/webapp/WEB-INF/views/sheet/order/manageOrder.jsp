<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增物资申领单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>
<div id="formbody" class="formbody">

    <div id="llSheetHead" class="layui-collapse">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">查询条件</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                <input type="hidden" id="taskId" name="taskId" value="${taskId}">
                    <table>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">采购订单：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <%--<input type="text" class="layui-input" name="ordernum" id="orderNum" disabled>
                                        <button class="layui-btn bill-button" id="order"
                                                style="margin-left: 28px !important;">...
                                        </button>--%>
                                        <input type="text" class="layui-input" name="ordernum" id="orderNum">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库存组织：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select id="ztid" name="ztid">
                                            <option value="">请选择...</option>
                                            <c:forEach items="${departList}" var="depart" varStatus="d">
                                                <option value="${depart.id}">${depart.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">制单人：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="creatorName" disabled>
                                        <input type="text" class="layui-hide" name="creator" id="creator">
                                        <button class="layui-btn bill-button" id="creatorBtn"
                                                style="margin-left: 28px !important;">...
                                        </button>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据编号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" name="code">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">接收日期:</label>
                                    <div class="layui-input-block ">
                                        <div class="layui-col-xs5" >
                                            <input type="text" class="layui-input" id="startTime" name="begin">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">至</div>
                                        <div class="layui-col-xs5">
                                            <input type="text" class="layui-input" id="endTime" name="end"
                                            >
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据状态：</label>
                                    <div class="layui-input-block">
                                        <select name="status" id="status">
                                            <option value="">请选择...</option>
                                            <c:forEach items="${orderStatus}" var="d">
                                                <option value="${d.id}">${d.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">供应商：</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="providerName" class="layui-input" disabled>
                                        <input type="text" id="providerId" name="providerdepid" class="layui-hide">
                                        <button class="layui-btn bill-button" id="provider"
                                                style="margin-left: 28px !important;">...
                                        </button>
                                    </div>
                                </div>
                            </td>

                            <td style="width: 100px;">
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit
                                        lay-filter="formSubmit"
                                        style="margin-left: 36px !important;">查询
                                </button>
                                <button type="reset" class="layui-btn bill-td-button"
                                        style="margin-left: 5px !important;">重置
                                </button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <div class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">数据列表</h2>
            <div class="layui-colla-content layui-show">
                <table id="ordergrid" lay-filter="ordergrid"></table>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="bar">
    {{# if( d.statusname == '制单中' && ${userId} == d.creator ) { }}
    <a lay-event="edit" class="layui-btn  layui-btn-xs">编辑</a>
    <a lay-event="delete" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
    {{# }else{ }}

    <a lay-event="show" class="layui-btn  layui-btn-xs">查看</a>
    {{#} }}

</script>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/order/manageOrder.js"></script>
</html>

