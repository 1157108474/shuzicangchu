<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>计划库存管理报表</title>
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
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">计划部门：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input"  placeholder=""  id="deptName" >
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">计划编码：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input"  placeholder=""  id="planCode" >
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">使用单位：</label>
                                    <div class="layui-input-block form-label" style="position:relative;">
                                        <input type="text" class="layui-input"  placeholder=""  id="userDepName" >
                                    </div>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">物料编码：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  type="text" class="layui-input" name="materialCode" id="materialCode">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">物料描述：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  type="text" class="layui-input" name="materialDes" id="materialDes">
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
                <table id="planDetailGrid" lay-filter="planDetailGrid"></table>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/query/queryPlanDetail.js"></script>
</html>

