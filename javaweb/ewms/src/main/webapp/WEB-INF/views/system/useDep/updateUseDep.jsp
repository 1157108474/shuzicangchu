<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
    <style type="text/css">
        .form-label{
            width: 150px!important;
        }
        .form-div-input{
            margin-left: 150px!important;
        }
    </style>
</head>
<body class="childrenBody" style="width: 90%;height: 90%;margin-left: 10%">
<form class="layui-form layui-form-pane" action="">

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">使用单位编码<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input">
            <input type="hidden" id="id" name="id" value="${useDepValue.id}">
            <input type="text" maxlength="16" class="layui-input " lay-verify="required|code" name="code" value="${useDepValue.code}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">使用单位名称<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input">
            <input type="text" maxlength="30" class="layui-input " lay-verify="required" name="name" value="${useDepValue.name}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">组织机构<span style="color:red">*</span></label>
        <div class="layui-input-block  form-div-input">
            <input type="text" class="layui-input " id="organizationName"
                   lay-verify="required" value="${depName}">
            <input type="hidden" name = "organizationId" id="organizationId"
                   value="${useDepValue.organizationId}">
            <input type="hidden" name = "ztId" id="ztId"
                   value="${useDepValue.ztId}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">组织类型</label>
        <div class="layui-input-block  form-div-input">
            <select name="organizationType">
                <option value="">请选择</option>
                <option value="1" <c:if test="${useDepValue.organizationType == 1}"> selected</c:if>>区队</option>
                <option value="2" <c:if test="${useDepValue.organizationType == 2}"> selected</c:if>>科室</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">是否启用<span style="color:red">*</span></label>
        <div class="layui-input-block  form-div-input" >
            <select name="status" id="status">
                <option value="">请选择</option>
                <option value="1" <c:if test="${useDepValue.status == 1}"> selected</c:if>>启用</option>
                <option value="2" <c:if test="${useDepValue.status == 2}"> selected</c:if>>禁用</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">备注</label>
        <div class="layui-input-block  form-div-input">
            <input type="text" class="layui-input " name="memo" value="${useDepValue.memo}">
        </div>
    </div>

    <div class="layui-form-item" style="text-align: center;">
        <a class="layui-btn" lay-filter="updateUseDep" lay-submit><i class="layui-icon">&#xe609;</i>提交保存 </a>
    </div>
</form>
</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/useDep/updateUseDep.js"></script>
</html>