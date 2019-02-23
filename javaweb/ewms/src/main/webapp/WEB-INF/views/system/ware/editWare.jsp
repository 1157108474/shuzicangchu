<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="GBK">
    <title>�ⷿ������Ϣ</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form " action="">

    <input type="hidden" class="layui-input " value="${ware.id}" name="idStr" id="id">
    <input type="hidden" class="layui-input " value="${ware.parentId}" name="parentIdStr" id="parentId">

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">�ϼ�����</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " disabled placeholder="�ϼ�����"
                   maxlength="16" id="parentCode" value="${ware.parentCode}" >
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">�ϼ�����</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " disabled placeholder="�ϼ�����"
                   maxlength="16" id="parentName" value="${ware.parentName}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">����<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="����" value="${ware.code}"
                   maxlength="16" name="code" id="code">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">����<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="����" value="${ware.name}"
                   maxlength="16" name="name">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">����</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="����" value="${ware.levelCount}"
                   maxlength="16"  disabled name="levelCountStr" id="level">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">����<span style="color: red">*</span></label>
        <div class="layui-input-block">

            <select lay-filter="kind" lay-verify="required" value="${ware.property}" name="propertyStr">
                <option value=""></option>
                <option value="1"
                        <c:if test="${ware.property==1}">selected</c:if> >�ⷿ
                </option>
                <option value="2" <c:if test="${ware.property==2}">selected</c:if>>����</option>
                <option value="3" <c:if test="${ware.property==3}">selected</c:if>>����</option>
                <option value="4" <c:if test="${ware.property==4}">selected</c:if>>��λ</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">�����֯<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" required="required" placeholder="�����֯" value="${ware.ztName}"
                   maxlength="16" id="ztname">

            <input id="ztid" name="ztIdStr" type="hidden" class="mini-input" value="${ware.ztId}"/>
        </div>
    </div>


    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">����</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="sort" placeholder="����" value="${ware.sort}"
                   maxlength="16" name="sort">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">״̬<span style="color: red">*</span></label>
        <div class="layui-input-block">

            <select lay-filter="status" lay-verify="required" value="${ware.status}" name="statusStr">
                <option value=""></option>
                <option value="1"
                        <c:if test="${ware.status==1}">selected</c:if> >����
                </option>
                <option value="0" <c:if test="${ware.status==0}">selected</c:if>>����</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">��ע</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " value="${ware.memo}"
                   maxlength="16" name="memo">
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
        <a class="layui-btn" lay-filter="editware" lay-submit id="edit"><i class="layui-icon">&#xe609;</i>�ύ���� </a>
    </div>
</form>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/ware/editWare.js"></script>
</body>
<script type="text/javascript">

</script>
</html>