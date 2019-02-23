<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>

</head>
<body class="childrenBody" style="width: 90%;height: 90%;margin-left: 10%">
<form class="layui-form layui-form-pane" action="">

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">编码<span style="color:red">*</span></label>
        <div class="layui-input-block">
            <input type="hidden" id="id" name="id" value="${ProviderValue.id}">
            <input type="text" maxlength="10" class="layui-input " lay-verify="required|code" name="code" value="${ProviderValue.code}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">名称<span style="color:red">*</span></label>
        <div class="layui-input-block">
            <input type="text" maxlength="30" class="layui-input " lay-verify="required" name="name" value="${ProviderValue.name}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="textarea" maxlength="50" class="layui-input " name="address" value="${ProviderValue.address}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">邮政编码</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="zipcode" name="zipCode"
                   value="${ProviderValue.zipCode}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">联系人</label>
        <div class="layui-input-block">
            <input type="text" maxlength="10" class="layui-input " name="contactPerson" value="${ProviderValue.contactPerson}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="contractPhone" name="contractPhone"
                   value="${ProviderValue.contractPhone}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">传真</label>
        <div class="layui-input-block">
            <input type="text" maxlength="15" class="layui-input " name="fax" value="${ProviderValue.fax}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">电子邮箱</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="emails" name="email" value="${ProviderValue.email}">
        </div>
    </div>
    <%--<div class="layui-form-item" style="margin-top: 10px;width: 90%;">--%>
        <%--<label class="layui-form-label">排序</label>--%>
        <%--<div class="layui-input-block" >--%>
            <%--<input type="text" class="layui-input " lay-verify="sort" name="sort" value="${ProviderValue.sort}">--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">是否启用<span style="color:red">*</span></label>
        <div class="layui-input-block" >
            <select name="status" id="status" >
                <option value="">请选择</option>
                <option value="1" <c:if test="${ProviderValue.status == 1}"> selected</c:if>>启用</option>
                <option value="2" <c:if test="${ProviderValue.status == 2}"> selected</c:if>>禁用</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " name="memo" value="${ProviderValue.memo}">
        </div>
    </div>

    <div class="layui-form-item" style="text-align: center;">
        <a class="layui-btn" lay-filter="updateProvider" lay-submit><i class="layui-icon">&#xe609;</i>提交保存 </a>
    </div>
</form>


</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/provider/updateProvider.js"></script>
</html>