<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>库存收发存报表</title>
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
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">物料编码：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  type="text" class="layui-input" name="materialcode" id="materialcode">
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
                        </tr>
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">统计日期：</label>
                                    <div class="layui-input-block form-label">
                                        <div style="float: left;width: 80px">
                                            <input type="text"  class="layui-input startTime" id="startTime" name="startTime"
                                                   placeholder="yyyy-MM-dd">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">至</div>
                                        <div style="float: left;width: 80px">
                                            <input type="text"  class="layui-input endTime" id="endTime" name="endTime"
                                                   placeholder="yyyy-MM-dd">
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库房：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select name="storeid" id="storeid">
                                            <option value=""></option>
                                            <c:forEach items="${wareHouseList}" var="wareHouse" varStatus="w">
                                                <option value="${wareHouse.id}">${wareHouse.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit lay-filter="formSubmit"
                                 style="margin-left: 36px !important;">统计</button>
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
                <table class="layui-table" id="querygrid" lay-filter="querygrid" lay-data="{elem: '#querygrid',cellMinWidth:80, url:'listQueryKCSZ.json', height: 'full-125',method: 'post',page: true, limit: 15, limits: [15, 30, 45], id: 'querygridTable'}">
                  <thead>
                    <tr>
                      <th lay-data="{type: 'numbers',width: 50}" rowspan="2">序号</th>
                      <th lay-data="{field: 'materialcode',align: 'center', width: 120}" rowspan="2">物料编码</th>
                      <th lay-data="{field: 'materialname',align: 'center', width: 100}" rowspan="2">物料名称</th>
 <%--                     <th lay-data="{field: 'materialmodel',align: 'center', width: 100}" rowspan="2">物料规格</th>
                      <th lay-data="{field: 'materialspecification',align: 'center', width: 140}" rowspan="2">物料型号</th>--%>
                      <th lay-data="{align: 'center'}" colspan="2">期初库存数据</th>
                      <th lay-data="{align: 'center'}" colspan="2">本期入库数据</th>
                      <th lay-data="{align: 'center'}" colspan="2">本期出库数据</th>
                      <th lay-data="{align: 'center'}" colspan="2">期末库存数据</th>
                    </tr>
                    <tr>
                      <th lay-data="{field: 'startcount',align: 'center', width: 100}">期初库存数量</th>
                      <th lay-data="{field: 'startmoney',align: 'center', width: 100}">期初库存金额</th>
                      <th lay-data="{field: 'bqrcount',align: 'center', width: 100}">本期入库数量</th>
                      <th lay-data="{field: 'bqrkmoney',align: 'center', width: 100}">本期入库金额</th>
                      <th lay-data="{field: 'bqckcount',align: 'center', width: 100}">本期出库数量</th>
                      <th lay-data="{field: 'bqckmoney',align: 'center', width: 100}">本期出库金额</th>
                      <th lay-data="{field: 'qmcount',align: 'center', width: 100}">期末库存数量</th>
                      <th lay-data="{field: 'qmmoney',align: 'center', width: 100}">期末库存金额</th>
                    </tr>
                  </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="bar">
</script>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/query/queryKCSZ.js"></script>
</html>

