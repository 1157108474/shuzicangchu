<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>流程监控</title>
    <link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="admin-main">
    <fieldset class="layui-elem-field" style="margin-left: 10px;margin-right: 10px;">
        <legend>查询条件</legend>
        <div class="layui-field-box">
            <form class="layui-form" action="">
                <table>
                    <tr>
                        <td style="width: 380px">
                            <div class="layui-form-item">
                                <label class="layui-form-label">单据编号：</label>
                                <div class="layui-input-block">
                                    <input name="infTaskdetailn" autocomplete="on" placeholder="单据编号" id="temCode"
                                           class="layui-input infTaskdetailn" type="text">
                                </div>
                            </div>
                        </td>
                        <td style="width: 380px">
                            <div class="layui-form-item">
                                <label class="layui-form-label">单据名称：</label>
                                <div class="layui-input-block">
                                    <input name="infTaskdetailn" autocomplete="on" placeholder="单据名称" id="temName"
                                           class="layui-input infTaskdetailn" type="text">
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item" style="margin-left: 20px">
                                <button type="reset" class="layui-btn layui-btn-primary" id="reset">重置条件</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                <label class="layui-form-label">提交时间：</label>
                                <div class="layui-input-inline" style="width:100px;">
                                    <input type="text" class="layui-input startTime" placeholder="开始日期" id="startTime"
                                           placeholder="yyyy-MM-dd">
                                </div>
                                <div class="layui-form-mid" style="width:20px">至</div>
                                <div class="layui-input-inline" style="width:100px;">
                                    <input type="text" class="layui-input endTime" placeholder="结束日期" id="endTime"
                                           placeholder="yyyy-MM-dd">
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                <label class="layui-form-label">单据状态：</label>
                                <div class="layui-input-block" id="status">
                                    <select>
                                        <option value="0">请选择...</option>
                                        <c:forEach items="${dictionaries}" var="dictionarie" varStatus="d">
                                            <option value="${dictionarie.id}">${dictionarie.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item" style="margin-left: 20px">
                                <button class="layui-btn" lay-submit lay-filter="formSubmit">立即查询</button>
                            </div>
                        </td>
                    </tr>
                </table>
                <%--<div style="width: 49%;float: left">
                    <div class="layui-form-item">
                        <label class="layui-form-label">单据编号</label>
                        <div class="layui-input-block" style="width: 57%">
                            <input name="infTaskdetailn" autocomplete="on" placeholder="单据编号" id="temCode"
                                   class="layui-input infTaskdetailn" type="text">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">提交时间</label>
                        <div class="layui-input-inline" style="width:26%;margin-left: 12px">
                            <input type="text" class="layui-input startTime" placeholder="开始日期" id="startTime"
                                   placeholder="yyyy-MM-dd">
                        </div>
                        <div class="layui-form-mid" style="width: 2%">至</div>
                        <div class="layui-input-inline" style="width:26%">
                            <input type="text" class="layui-input endTime" placeholder="结束日期" id="endTime"
                                   placeholder="yyyy-MM-dd">
                        </div>
                    </div>
                </div>
                <div style="width: 48%;float: right">
                    <div class="layui-form-item">
                        <label class="layui-form-label">单据名称</label>
                        <div class="layui-input-block" style="width: 57%">
                            <input name="infTaskdetailn" autocomplete="on" placeholder="单据名称" id="temName"
                                   class="layui-input infTaskdetailn" type="text">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="formSubmit">立即查询</button>
                            <button type="reset" class="layui-btn layui-btn-primary" id="reset">重置条件</button>
                        </div>
                    </div>
                </div>--%>
            </form>
        </div>
    </fieldset>
    <blockquote class="layui-elem-quote" style="margin-left: 10px;margin-top: 5px;margin-right: 10px;">
        <a href="javascript:void(0)" class="layui-btn" id="audit">审核</a>
        <a href="javascript:void(0)" class="layui-btn" id="another">另派</a>
        <a href="javascript:void(0)" class="layui-btn" id="export">导出</a>
        <%--<a href="javascript:void(0)" class="layui-btn" id="current">查询</a>--%>
    </blockquote>
    <div style="margin:-5px 10px;height:100%;">
        <div style="float:left;width:100%;height:100%;">
            <table id="activitiListen" lay-filter="activitiListen"></table>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/js/system/act/activitiListen.js"></script>
<script type="text/javascript">
    function getReturnBut(data) {
    }
</script>

</html>