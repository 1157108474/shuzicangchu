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
                    <table   >
                        <tr>
                            <td >
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
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">制单人：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" id="creatorName" class="layui-input" disabled>
                                        <input type="text" id="creator" name="creator" class="layui-hide">
                                        <button class="layui-btn bill-button" id="creatorBtn">...</button>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">采购订单编号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="ordernum" name="ordernum">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据编号:</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="code" name="code">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据状态：</label>
                                    <div class="layui-input-block">
                                        <select  name="status" id="status">
                                            <option value="">请选择...</option>
                                            <c:forEach items="${orderStatus}" var="d" >
                                                <option value="${d.id}">${d.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">ERP接收单号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="extendstring6" name="extendstring6">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">入库类型：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select id="check" lay-filter="isopen" name="typeid">
                                            <option value="">请选择...</option>
                                            <c:forEach items="${rkType}" var="r">
                                                <option value="${r.id}">${r.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">接收单号：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" id="receivenum" name="receivenum">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">制单日期：</label>
                                    <div class="layui-input-block">
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input startTime" id="startTime" name="begin"
                                            >
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">至</div>
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input endTime" id="endTime" name="end"
                                            >
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit lay-filter="formSubmit"
                                        >查询</button>
                                <button type="reset" class="layui-btn bill-td-button" >重置</button>
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
                <table id="rkgrid" lay-filter="rkgrid"></table>
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
<script type="text/javascript" src="/js/sheet/rk/manageWzrk.js"></script>
</html>

