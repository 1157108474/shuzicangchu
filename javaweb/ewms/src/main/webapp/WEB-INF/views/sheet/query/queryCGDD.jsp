<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>采购订单查询</title>
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
                    <table>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">订单编号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" name="ordernum" id="ordernum">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库存组织：</label>
                                    <div class="layui-input-block" style="position:relative;" id="stockorgidDiv">
                                        <select name="stockorgid" id="stockorgid">
                                            <option value=""></option>
                                            <c:forEach items="${departList}" var="d" varStatus="w">
                                                <option value="${d.id}">${d.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">物料编码：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" name="materialcode" id="materialcode">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">状态：</label>
                                    <div class="layui-input-block" id="extendint1Div">
                                        <select name="extendint1" id="extendint1">
                                            <option value=""></option>
                                            <option value="1">正常</option>
                                            <option value="0">失效</option>
                                        </select>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">物料描述：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="description" id="description">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">供应商：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="providerdepname" id="providerName">
                                        <button class="layui-btn bill-button" id="providerBtn" style="margin-left: 28px !important;">...
                                        </button>
                                        <input type="hidden" class="layui-input" name="providerdepid" id="providerId">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">是否寄售：</label>
                                    <div class="layui-input-block" style="position:relative;" id="consignmentDiv">
                                        <select name="consignment" id="consignment">
                                            <option value=""></option>
                                            <c:forEach items="${jsType}" var="j" varStatus="w">
                                                <option value="${j.id}">${j.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">制单日期：</label>
                                    <div class="layui-input-block">
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input startTime" id="startTime"
                                                   name="startTime" placeholder="yyyy-MM-dd">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">至</div>
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input endTime" id="endTime" name="endTime"
                                                   placeholder="yyyy-MM-dd">
                                        </div>
                                    </div>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit
                                        lay-filter="formSubmit" style="margin-left: 36px !important;">查询
                                </button>
                                <button type="reset" class="layui-btn bill-td-button" style="margin-left: 5px !important;">重置
                                </button>
                                <a id="export" class="layui-btn bill-td-button" style="margin-left: 5px !important;">导出</a>
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
                <table id="querygrid" lay-filter="querygrid"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/query/queryCGDD.js"></script>
</html>

