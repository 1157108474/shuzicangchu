<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>物资退货单管理</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>

</head>
<body>
<div id="formbody" class="formbody">

    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">查询条件</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table width="100%">
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库存组织:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select id="ztId" lay-filter="ztId" name="ztId">
                                            <option value=""></option>
                                            <c:forEach items="${departList}" var="depart" varStatus="d">
                                                <option value="${depart.id}">${depart.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item bill-search">
                                    <label class="layui-form-label">制单人:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" id="creatorName">
                                        <button class="layui-btn bill-button" id="creatorBtn">...</button>
                                        <input class="layui-hide" name="creator" id="creatorId">

                                    </div>

                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item bill-search">
                                    <label class="layui-form-label">单据编号：</label>
                                    <div class="layui-input-block">
                                        <input name="code" type="text" class="layui-input">
                                    </div>
                                </div>
                            </td>
                            <td></td>


                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">制单日期:</label>
                                    <div class="layui-input-block">

                                        <input name="begin" id="begin" type="text" class="layui-input"
                                               placeholder="yyyy-MM-dd" style="width:46%;display:inline">
                                        至
                                        <input name="end" id="end" type="text" class="layui-input"
                                               placeholder="yyyy-MM-dd" style="width:46%;display:inline">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据状态:</label>
                                    <div class="layui-input-block">
                                        <select id="status" lay-filter="callSystem" name="status">
                                            <option value=""></option>
                                            <c:forEach items="${statusList}" var="status" varStatus="d">
                                                <option value="${status.id}">${status.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">采购订单编号：</label>
                                    <div class="layui-input-block">
                                        <input id="orderNum" name="usedDepartID" type="text" class="layui-input">

                                    </div>
                                </div>
                            </td>
                            <td style="width: 30px;">

                                <button style="margin-left: 5px" class="layui-btn layui-btn-warm" lay-submit=""
                                        lay-filter="search" id="submitBtn">查询
                                </button>

                            </td>

                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">供应商:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" id="providerName">
                                        <button class="layui-btn bill-button" id="providerBtn">...</button>
                                        <input class="layui-hide" name="extendString1" id="providerId">

                                    </div>
                                </div>

                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">入库单号：</label>
                                    <div class="layui-input-block">
                                        <input name="extendString5" type="text" class="layui-input">

                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">EPR接收单号：</label>
                                    <div class="layui-input-block">
                                        <input name="extendString7" type="text" class="layui-input">

                                    </div>
                                </div>
                            </td>

                            <td style="width: 30px;">
                                <button style="margin-left: 5px" class="layui-btn" type="reset">重置</button>
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
                <table id="sheetgrid" lay-filter="sheetgrid"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/th/manageTh.js"></script>
<script type="text/html" id="barDemo">

    {{# if( d.statusName == '制单中'  && ${userId} == d.creator   ) { }}
    <a lay-event="edit" class="layui-btn  layui-btn-xs">编辑</a>
    <a lay-event="delete" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
    {{# }else{ }}

    <a lay-event="show" class="layui-btn  layui-btn-xs" ali>查看</a>
    {{#} }}

</script>
</html>

