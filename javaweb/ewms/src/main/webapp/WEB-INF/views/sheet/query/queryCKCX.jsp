<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>物资出库查询</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <style type="text/css">
        .form-label {
            width: 180px !important;
        }

        .form-div-input {
            margin-left: 180px !important;
        }
    </style>
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
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据编号：</label>
                                    <div class="layui-input-block form-label" style="position:relative;">
                                        <input  type="text" class="layui-input" name="code" id="code">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">部门：</label>
                                    <div class="layui-input-block form-label" style="position:relative;">
                                        <select name="ztid" id="ztid">
                                            <option value=""></option>
                                            <c:forEach items="${departList}" var="depart" varStatus="d">
                                                <option value="${depart.id}">${depart.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">申领单位：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="hidden" class="layui-input" name="usedDepartId"  id="usedDepartId" >
                                        <button class="layui-btn bill-button" id="openUseDep">...</button>
                                        <input type="text" class="layui-input" name="useDepName"  id="useDepName">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库房：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select name="storeid" id="storeid">
                                            <option value=""></option>
                                            <c:forEach items="${wareHouseList}" var="wareHouse" varStatus="w">
                                                <option value="${wareHouse.key}">${wareHouse.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">物料名称：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  type="text" class="layui-input" name="materialname" id="materialname">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">物料编码：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  type="text" class="layui-input" name="materialcode" id="materialcode">
                                    </div>
                                </div>
                            </td>

                            <td >
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit lay-filter="formSubmit"
                                 style="margin-left: 36px !important;">查询</button>
                                <button type="reset" id="reset" class="layui-btn bill-td-button" style="margin-left: 5px !important;">重置</button>
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
<script type="text/html" id="bar">
    <a lay-event="show" class="layui-btn layui-btn-xs">查看</a>
</script>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/query/queryCKCX.js"></script>
</html>

