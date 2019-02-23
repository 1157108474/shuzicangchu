<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gbk" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>重置密码</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <style type="text/css">
        .form-div{
            width: 100%;
            margin-bottom: -3px!important;
        }
        .form-label{
            width: 150px!important;
        }
        .form-div-input{
            margin-left: 150px!important;
        }
    </style>
</head>

<body style="margin-top: 10px;width: 90%;margin-left: 5%">
<form class="layui-form layui-form-pane " action="">
    <div class="layui-form-item" style="display:none;">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="hidden" class="layui-input" id="id" value="${us.id}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label form-label">员工编码</label>
        <div class="layui-input-block form-div-input">
            <input type="text" class="layui-input layui-disabled" id="code" value="${us.code}" disabled>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label form-label">姓名</label>
        <div class="layui-input-block form-div-input">
            <input type="text" class="layui-input layui-disabled" id="name" value="${us.name}" disabled>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label form-label"><span style="color: red">*</span>新密码</label>
        <div class="layui-input-block  form-div-input">
            <input type="password" class="layui-input " id="newPWD" maxlength="50" lay-verify="newPWD">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-form-item">

            <label class="layui-form-label form-label" ><span style="color: red">*</span>确认新密码</label>
            <div class="layui-input-block  form-div-input">
                <input type="password" class="layui-input" id="confirmPWD" maxlength="50" lay-verify="confirmPWD">
            </div>
        </div>

        <div class="layui-form-item" style="text-align: center;margin-top:20px ">
            <a class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit><i class="layui-icon">&#xe609;</i>保存 </a>
        </div>

        <input type="hidden" id="source" value="${source}">
</form>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/user/resetPWD.js"></script>
</body>
</html>