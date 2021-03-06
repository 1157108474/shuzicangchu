<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>移库移位单管理</title>
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
                    <table  width="100%">
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库存组织:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select id="ztId" lay-filter="ztId"  name="ztId" >
                                            <option value=""></option>
                                            <c:forEach items="${departList}" var="depart" varStatus="d">
                                                <option value="${depart.id}" >${depart.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">制单人:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" id="creatorName" >
                                        <button class="layui-btn bill-button" id="creatorBtn">...</button>
                                        <input class="layui-hide" name="creator" id="creatorId" >
                                    </div>

                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据编号:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  name="code" type="text" class="layui-input" >
                                    </div>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">制单日期:</label>
                                    <div class="layui-input-block">

                                        <input  name="begin" id="begin" type="text" class="layui-input" placeholder="yyyy-MM-dd" style="width:46%;display:inline">
                                    至
                                        <input  name="end" id="end" type="text" class="layui-input" placeholder="yyyy-MM-dd" style="width:46%;display:inline">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据状态:</label>
                                    <div class="layui-input-block" >
                                        <select id="status" lay-filter="callSystem"  name="status" >
                                            <option value=""></option>
                                            <c:forEach items="${statusList}" var="status" varStatus="d">
                                                <option value="${status.id}" >${status.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>


                            <td >
                                <div class="layui-form-item">

                                <button style="margin-left: 40px" class="layui-btn layui-btn-warm" lay-submit="" lay-filter="search" id="submitBtn">查询</button>
                                     <button style="margin-left: 5px" class="layui-btn" type="reset">重置</button>

                                </div>
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
<script type="text/javascript" src="/js/sheet/ykyw/manageYkyw.js"></script>

<script type="text/html" id="barDemo" >
    {{# if( (d.extendInt3 == null||d.extendInt3 == 0) && d.statusName == '已完成') { }}
    <a lay-event="renewalCost" class="layui-btn layui-btn-xs">更新成本</a>
    {{# } }}
    {{# if( d.statusName == '制单中' && ${userId} == d.creator ) { }}
    <a  class="layui-btn  layui-btn-xs" lay-event="edit" >编辑</a>
    <a  class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete" >删除</a>
    {{# }else{ }}

    <a lay-event="show" class="layui-btn layui-btn-xs">查看</a>
    {{#} }}

</script>
</html>

