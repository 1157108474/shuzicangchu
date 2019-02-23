<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>采购计划查询</title>
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
                                    <label class="layui-form-label">计划编号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" name="planCode" id="planCode">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">物料编码：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="materialCode" id="materialCode">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">物料描述：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" name="materIaldes" id="materIaldes">
                                    </div>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">申报单位：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="applyDepName" id="applyDepName">
                                        <input type="hidden" class="layui-input" name="applyDepId" id="applyDepId">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">使用单位：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="useDepName" id="useDepName">
                                        <input type="hidden" class="layui-input" name="useDepId" id="useDepId">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">状态：</label>
                                    <div class="layui-input-block" style="position:relative;" id="statusDiv">
                                        <select name="status" id="status">
                                            <option value=""></option>
                                            <option value="正常">正常</option>
                                            <option value="失效">失效</option>
                                        </select>
                                    </div>
                                </div>
                            </td>


                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">计划需求日期：</label>
                                    <div class="layui-input-block">
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input startTime" id="startTime1"
                                                   name="startTime1" placeholder="yyyy-MM-dd">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">至</div>
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input endTime" id="endTime1"
                                                   name="endTime1" placeholder="yyyy-MM-dd">
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">计划制定日期：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input startTime" id="startTime2"
                                                   name="startTime2" placeholder="yyyy-MM-dd">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">至</div>
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input endTime" id="endTime2"
                                                   name="endTime2" placeholder="yyyy-MM-dd">
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit
                                        lay-filter="formSubmit" style="margin-left: 36px !important;">查询
                                </button>
                                <button type="reset" class="layui-btn bill-td-button" style="margin-left: 5px !important;">重置</button>
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
<script type="text/javascript" src="/js/sheet/query/queryCGJH.js"></script>
</html>

