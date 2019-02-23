<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <style type="text/css">
        .form-label {
            width: 150px !important;
        }

        .form-div-input {
            margin-left: 150px !important;
        }
    </style>
</head>
<body class="childrenBody" style="width: 90%;height: 90%;margin-left: 10%">
<form class="layui-form layui-form-pane" enctype="multipart/form-data" action="">

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">文件名称<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input">
            <input type="hidden" id="sheetId" name="attachRelateId" value="${sheetId}">
            <input type="text" maxlength="16" class="layui-input " lay-verify="required|code" name="fileName">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">上传附件<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input" style="position:relative;">
            <input type="hidden" id="fileAliasName" name="fileAliasName">
            <input type="hidden" id="fileExt" name="fileExt">
            <input type="hidden" id="filePath" name="filePath" class="layui-input">
            <input type="text" id="imageName" class="layui-input" lay-verify="required">
            &nbsp;<a class="layui-btn bill-button" id="uploadFiles" style="margin-left: 28px !important;top:4px">
            上传
        </a>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">备注</label>
        <div class="layui-input-block  form-div-input">
            <input type="text" class="layui-input " name="memo">
        </div>
    </div>

    <div class="layui-form-item" style="text-align: center;">
        <button class="layui-btn" lay-filter="attchFile" lay-submit><i class="layui-icon">&#xe609;</i>保存</button>
    </div>
</form>


</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/sheet/order/generalFile.js"></script>


</html>