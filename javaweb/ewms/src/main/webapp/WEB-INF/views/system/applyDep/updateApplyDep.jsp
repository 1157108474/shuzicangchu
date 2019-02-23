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
        <label class="layui-form-label form-label">���뵥λ����<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input">
            <input type="hidden" id="id" name="id" value="${applyDepValue.id}">
            <input type="text" maxlength="16" class="layui-input " lay-verify="required|code" name="code" value="${applyDepValue.code}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">���뵥λ����<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input">
            <input type="text" maxlength="30" class="layui-input " lay-verify="required" name="name" value="${applyDepValue.name}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">��Ŀ���</label>
        <div class="layui-input-block form-div-input">
            <input type="textarea" class="layui-input " name="subjectsGroup" value="${applyDepValue.subjectsGroup}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">��Ŀ���˵��</label>
        <div class="layui-input-block form-div-input">
            <input type="text" class="layui-input " name="subjectsGroupDescription"
                   value="${applyDepValue.subjectsGroupDescription}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">�����֯<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input">
            <input type="text" class="layui-input "  id="departName"
                   lay-verify="required"      value="${depName}">
            <input type="hidden" name = "ztId" id="ztId"
                   value="${applyDepValue.ztId}">
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">�Ƿ�����<span style="color:red">*</span></label>
        <div class="layui-input-block form-div-input" >
            <select name="status" id="status">
                <option value="">��ѡ��</option>
                <option value="1" <c:if test="${applyDepValue.status == 1}"> selected</c:if>>����</option>
                <option value="2" <c:if test="${applyDepValue.status == 2}"> selected</c:if>>����</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">˵��</label>
        <div class="layui-input-block form-div-input">
            <input type="text" class="layui-input " name="demo" value="${applyDepValue.demo}">
        </div>
    </div>

    <div class="layui-form-item" style="text-align: center;">
        <a class="layui-btn" lay-filter="updateApplyDep" lay-submit><i class="layui-icon">&#xe609;</i>�ύ���� </a>
    </div>
</form>


</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/applyDep/updateApplyDep.js"></script>



</html>