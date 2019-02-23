<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="GBK">
    <title>�������</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
</head>
<body class="childrenBody" >
<form class="layui-form " action="">

    <input type="hidden"  class="layui-input " value="${task.id}" name="idStr" id="id">

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">��������<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input "  lay-verify="required" placeholder="��������" value="${task.name}"
                   maxlength="16" name="name">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">������ʽ<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <select  lay-filter="triggerKind"  lay-verify="required" value="${task.triggerKind}" name="triggerKindStr">
                <option value=""></option>
                <option value="3"  <c:if test="${task.triggerKind==3}">selected</c:if>>��ʱ</option>
                <option value="1"  <c:if test="${task.triggerKind==1}">selected</c:if>>ʵʱ</option>
                <option value="2"  <c:if test="${task.triggerKind==2}">selected</c:if>>�ֶ�</option>
                <option value="4"  <c:if test="${task.triggerKind==4}">selected</c:if>>����</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">�ӿ�����<span style="color: red">*</span></label>
        <div class="layui-input-block">

            <select  lay-filter="kind"  lay-verify="required" value="${task.kind}" name="kindStr"   id="kind">
                <option value=""></option>
                <option value="1" <c:if test="${task.kind==1}">selected</c:if> >�ṩ��</option>
                <option value="2" <c:if test="${task.kind==2}">selected</c:if>>���÷�</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">�ӿ��ṩ��<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <select  id="supplySystem" lay-filter="supplySystem" lay-verify="required"  name="supplyStr" >
                <option value=""></option>
                <c:forEach items="${systems}" var="sys" varStatus="d">
                    <option value="${sys.id}" <c:if test="${task.supply.id==sys.id}">selected</c:if>>${sys.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">�ӿڵ��÷�<span style="color: red">*</span></label>
        <div class="layui-input-block" >
            <select id="callSystem" lay-filter="callSystem" lay-verify="required" name="callStr" >
                <option value=""></option>
                <c:forEach items="${systems}" var="sys" varStatus="d">
                    <option value="${sys.id}" <c:if test="${task.call.id==sys.id}">selected</c:if>>${sys.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>


    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">ͬ����ʽ<span style="color: red">*</span></label>
        <div class="layui-input-block">

            <select  lay-filter="syncKind"  lay-verify="required" value="${task.syncKind}" name="syncKindStr">
                <option value=""></option>
                <option value="1" <c:if test="${task.syncKind==1}">selected</c:if>>WebService</option>
                <option value="2" <c:if test="${task.syncKind==2}">selected</c:if>>WebApi</option>
                <option value="3" <c:if test="${task.syncKind==3}">selected</c:if>>FileExport</option>
                <option value="4" <c:if test="${task.syncKind==4}">selected</c:if>>dbLink</option>
                <option value="5" <c:if test="${task.syncKind==5}">selected</c:if>>other</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">��ע</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input "  value="${task.remark}"
                   maxlength="16" name="remark">
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
        <a class="layui-btn" lay-filter="editInfTask" lay-submit id="edit"><i class="layui-icon">&#xe609;</i>�ύ���� </a>
    </div>
</form>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/task/editInfTask.js"></script>
</body>
<script type="text/javascript">

</script>
</html>