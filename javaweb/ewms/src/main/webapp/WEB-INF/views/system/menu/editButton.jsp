<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增、编辑功能按钮</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
</head>

<body class="childrenBody" style="margin-top: 15px;margin-left: 2%;width: 95%;height: 90%">
<form class="layui-form layui-form-pane">
    <div class="layui-form-item" style="display:none;">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="hidden" class="layui-input " id="id" value="${bu.code}">
            <input type="hidden" class="layui-input " id="menuCode" value="${bu.menuCode}">

        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red">*</span>按钮编码</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input <c:if test="${null != bu.code }">layui-disabled</c:if>"
                       placeholder="请输入按钮编码" <c:if test="${null != bu.code}">disabled</c:if>
                       value="${bu.code}" id="code" lay-verify="required" maxlength="16">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red">*</span>按钮名称</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input " id="name" placeholder="请输入按钮名称"
                       value="${bu.name}" lay-verify="required" maxlength="128">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red">*</span>按钮排序</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input " id="sort" placeholder="请输入按钮排序"
                       value="${bu.sort}" lay-verify="required|number">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline " id="status">
                <select>
                    <c:forEach items="${statusMaps}" var="statusMap" varStatus="s">
                        <option value="${statusMap.key}"
                                <c:if test="${bu.status ==statusMap.key}">selected</c:if> >${statusMap.value}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"><span style="color: red">*</span>按钮URL</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input " id="url" placeholder="请输入按钮URL" value="${bu.url}"
                       lay-verify="required" maxlength="128">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"><span style="color: red">*</span>权限标识</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input "  id="authIdentity" placeholder="请输入权限标识，多的以‘;’隔开"
                   lay-verify="required" maxlength="128" value="${bu.authIdentity}">
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;margin-top: 20px;">
        <a class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit><i class="layui-icon">&#xe609;</i>保存 </a>
    </div>
</form>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/menu/editButton.js"></script>
</body>
</html>