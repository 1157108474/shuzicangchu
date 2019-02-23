<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="GBK">
    <title>物料信息</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form " action="">

    <input type="hidden" class="layui-input " value="${material.id}" name="idStr" id="id">
    <input type="hidden" class="layui-input " value="${material.sparescateId}" name="sparescateIdStr" id="spareId">


    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">分类编码<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="分类编码" value="${material.spareCode}"
                   maxlength="16"  id="spareCode">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">物料编码<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="codeverify" placeholder="物料编码" value="${material.code}"
                   maxlength="16" name="code" id="code">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">物料名称<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="物料名称" value="${material.name}"
                   maxlength="16" name="name">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">物料规格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="物料规格" value="${material.specifications}"
                   maxlength="16" name="specifications">
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">物料型号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="物料型号" value="${material.models}"
                   maxlength="16" name="models">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">物料品牌</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="物料品牌" value="${material.brand}"
                   maxlength="16" name="brand">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">单价</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="price" placeholder="单价" value="${material.price}"
                   maxlength="16" name="priceStr">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">计量单位<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <select id="unit" lay-filter="unit" lay-verify="required" name="unit">
                <option value=""></option>
                <c:forEach items="${units}" var="u" varStatus="d">
                    <option value="${u.name}"
                            <c:if test="${material.unit==u.name}">selected</c:if>>${u.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">库存上限</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="price" placeholder="库存上限" value="${material.stockUp}"
                   maxlength="16" name="stockUpStr" id="stockUp">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">库存下限</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="price" placeholder="库存下限" value="${material.stockDown}"
                   maxlength="16" name="stockDownStr" id="stockDown">
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">是否启用预警<span style="color: red">*</span></label>
        <div class="layui-input-block">

            <select lay-filter="kind" lay-verify="required" value="${material.isUseAlarm}" name="isUseAlarmStr">
                <option value=""></option>
                <option value="1"
                        <c:if test="${material.isUseAlarm==1}">selected</c:if> >是
                </option>
                <option value="0" <c:if test="${material.isUseAlarm==0}">selected</c:if>>否</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">供应商</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  placeholder="供应商"
                   value="${material.providerName}"
                   maxlength="16" id="providerName">

            <input id="providerId" name="providerIdStr" type="hidden" class="mini-input"/>
        </div>
    </div>
    <%--<div class="layui-form-item" style="margin-top: 10px;width: 90%;">--%>
        <%--<label class="layui-form-label">库存组织<span style="color: red">*</span></label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="text" class="layui-input" lay-verify="required"  placeholder="库存组织" value="${material.ztName}"--%>
                   <%--maxlength="16" id="ztname">--%>

            <%--<input id="ztid" name="ztidStr" type="hidden" class="mini-input" value="${material.ztid}"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">配置信息</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="配置信息" value="${material.configMemo}"
                   maxlength="16" name="configMemo">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">物料描述</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="物料描述" value="${material.description}"
                   maxlength="16" name="description">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">排序</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="sort" placeholder="排序" value="${material.sort}"
                   maxlength="16" name="sortStr">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">状态<span style="color: red">*</span></label>
        <div class="layui-input-block">

            <select lay-filter="status" lay-verify="required" value="${material.status}" name="statusStr">
                <option value=""></option>
                <option value="1"
                        <c:if test="${material.status==1}">selected</c:if> >启用
                </option>
                <option value="0" <c:if test="${material.status==0}">selected</c:if>>禁用</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " value="${material.memo}"
                   maxlength="16" name="memo">
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
        <a class="layui-btn" lay-filter="editMaterial" lay-submit id="edit"><i class="layui-icon">&#xe609;</i>提交保存 </a>
    </div>
</form>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/material/editMaterial.js"></script>

</body>

</html>