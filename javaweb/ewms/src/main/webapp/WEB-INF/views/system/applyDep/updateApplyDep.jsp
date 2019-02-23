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
        <label class="layui-form-label form-label">申请单位编码<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input">
            <input type="hidden" id="id" name="id" value="${applyDepValue.id}">
            <input type="text" maxlength="16" class="layui-input " lay-verify="required|code" name="code" value="${applyDepValue.code}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">申请单位名称<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input">
            <input type="text" maxlength="30" class="layui-input " lay-verify="required" name="name" value="${applyDepValue.name}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">科目组合</label>
        <div class="layui-input-block form-div-input">
            <input type="textarea" class="layui-input " name="subjectsGroup" value="${applyDepValue.subjectsGroup}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">科目组合说明</label>
        <div class="layui-input-block form-div-input">
            <input type="text" class="layui-input " name="subjectsGroupDescription"
                   value="${applyDepValue.subjectsGroupDescription}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">库存组织<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input">
            <input type="text" class="layui-input "  id="departName"
                   lay-verify="required"      value="${depName}">
            <input type="hidden" name = "ztId" id="ztId"
                   value="${applyDepValue.ztId}">
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">是否启用<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input" >
            <select name="status" id="status">
                <option value="">请选择</option>
                <option value="1" <c:if test="${applyDepValue.status == 1}"> selected</c:if>>启用</option>
                <option value="2" <c:if test="${applyDepValue.status == 2}"> selected</c:if>>禁用</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">说明</label>
        <div class="layui-input-block form-div-input">
            <input type="text" class="layui-input " name="demo" value="${applyDepValue.demo}">
        </div>
    </div>

    <div class="layui-form-item" style="text-align: center;">
        <a class="layui-btn" lay-filter="updateApplyDep" lay-submit><i class="layui-icon">&#xe609;</i>提交保存 </a>
    </div>
</form>


</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/applyDep/updateApplyDep.js"></script>



</html>