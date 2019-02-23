<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="GBK">
    <title>库房库区信息</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form " action="">

    <input type="hidden" class="layui-input " value="${ware.id}" name="idStr" id="id">
    <input type="hidden" class="layui-input " value="${ware.parentId}" name="parentIdStr" id="parentId">

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">上级编码</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " disabled placeholder="上级编码"
                   maxlength="16" id="parentCode" value="${ware.parentCode}" >
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">上级名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " disabled placeholder="上级名称"
                   maxlength="16" id="parentName" value="${ware.parentName}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">编码<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="编码" value="${ware.code}"
                   maxlength="16" name="code" id="code">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">名称<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="名称" value="${ware.name}"
                   maxlength="16" name="name">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">级别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="级别" value="${ware.levelCount}"
                   maxlength="16"  disabled name="levelCountStr" id="level">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">性质<span style="color: red">*</span></label>
        <div class="layui-input-block">

            <select lay-filter="kind" lay-verify="required" value="${ware.property}" name="propertyStr">
                <option value=""></option>
                <option value="1"
                        <c:if test="${ware.property==1}">selected</c:if> >库房
                </option>
                <option value="2" <c:if test="${ware.property==2}">selected</c:if>>库区</option>
                <option value="3" <c:if test="${ware.property==3}">selected</c:if>>货架</option>
                <option value="4" <c:if test="${ware.property==4}">selected</c:if>>货位</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">库存组织<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" required="required" placeholder="库存组织" value="${ware.ztName}"
                   maxlength="16" id="ztname">

            <input id="ztid" name="ztIdStr" type="hidden" class="mini-input" value="${ware.ztId}"/>
        </div>
    </div>


    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">排序</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="sort" placeholder="排序" value="${ware.sort}"
                   maxlength="16" name="sort">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">状态<span style="color: red">*</span></label>
        <div class="layui-input-block">

            <select lay-filter="status" lay-verify="required" value="${ware.status}" name="statusStr">
                <option value=""></option>
                <option value="1"
                        <c:if test="${ware.status==1}">selected</c:if> >启用
                </option>
                <option value="0" <c:if test="${ware.status==0}">selected</c:if>>禁用</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " value="${ware.memo}"
                   maxlength="16" name="memo">
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
        <a class="layui-btn" lay-filter="editware" lay-submit id="edit"><i class="layui-icon">&#xe609;</i>提交保存 </a>
    </div>
</form>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/ware/editWare.js"></script>
</body>
<script type="text/javascript">

</script>
</html>