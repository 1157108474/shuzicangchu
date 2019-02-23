<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="GBK">
    <title>������Ϣ</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form " action="">

    <input type="hidden" class="layui-input " value="${material.id}" name="idStr" id="id">
    <input type="hidden" class="layui-input " value="${material.sparescateId}" name="sparescateIdStr" id="spareId">


    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">�������<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="�������" value="${material.spareCode}"
                   maxlength="16"  id="spareCode">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">���ϱ���<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="codeverify" placeholder="���ϱ���" value="${material.code}"
                   maxlength="16" name="code" id="code">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">��������<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="��������" value="${material.name}"
                   maxlength="16" name="name">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">���Ϲ��</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="���Ϲ��" value="${material.specifications}"
                   maxlength="16" name="specifications">
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">�����ͺ�</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="�����ͺ�" value="${material.models}"
                   maxlength="16" name="models">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">����Ʒ��</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="����Ʒ��" value="${material.brand}"
                   maxlength="16" name="brand">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">����</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="price" placeholder="����" value="${material.price}"
                   maxlength="16" name="priceStr">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">������λ<span style="color: red">*</span></label>
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
        <label class="layui-form-label">�������</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="price" placeholder="�������" value="${material.stockUp}"
                   maxlength="16" name="stockUpStr" id="stockUp">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">�������</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="price" placeholder="�������" value="${material.stockDown}"
                   maxlength="16" name="stockDownStr" id="stockDown">
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">�Ƿ�����Ԥ��<span style="color: red">*</span></label>
        <div class="layui-input-block">

            <select lay-filter="kind" lay-verify="required" value="${material.isUseAlarm}" name="isUseAlarmStr">
                <option value=""></option>
                <option value="1"
                        <c:if test="${material.isUseAlarm==1}">selected</c:if> >��
                </option>
                <option value="0" <c:if test="${material.isUseAlarm==0}">selected</c:if>>��</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">��Ӧ��</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  placeholder="��Ӧ��"
                   value="${material.providerName}"
                   maxlength="16" id="providerName">

            <input id="providerId" name="providerIdStr" type="hidden" class="mini-input"/>
        </div>
    </div>
    <%--<div class="layui-form-item" style="margin-top: 10px;width: 90%;">--%>
        <%--<label class="layui-form-label">�����֯<span style="color: red">*</span></label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="text" class="layui-input" lay-verify="required"  placeholder="�����֯" value="${material.ztName}"--%>
                   <%--maxlength="16" id="ztname">--%>

            <%--<input id="ztid" name="ztidStr" type="hidden" class="mini-input" value="${material.ztid}"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">������Ϣ</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="������Ϣ" value="${material.configMemo}"
                   maxlength="16" name="configMemo">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">��������</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="��������" value="${material.description}"
                   maxlength="16" name="description">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">����</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="sort" placeholder="����" value="${material.sort}"
                   maxlength="16" name="sortStr">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">״̬<span style="color: red">*</span></label>
        <div class="layui-input-block">

            <select lay-filter="status" lay-verify="required" value="${material.status}" name="statusStr">
                <option value=""></option>
                <option value="1"
                        <c:if test="${material.status==1}">selected</c:if> >����
                </option>
                <option value="0" <c:if test="${material.status==0}">selected</c:if>>����</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">��ע</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " value="${material.memo}"
                   maxlength="16" name="memo">
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
        <a class="layui-btn" lay-filter="editMaterial" lay-submit id="edit"><i class="layui-icon">&#xe609;</i>�ύ���� </a>
    </div>
</form>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/material/editMaterial.js"></script>

</body>

</html>