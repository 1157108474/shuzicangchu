<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>�������޸Ĳ˵�</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all" />
	</head>

	<body class="childrenBody" style="margin-top: 15px;margin-left: 2%;width: 98%;height: 90%">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item" style="display:none;">
				<label class="layui-form-label">ID</label>
				<div class="layui-input-block">
					<input type="hidden" class="layui-input " id="id" value="${menu.code}">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label"><span style="color: red">*</span>�˵�����</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input <c:if test="${null != menu.code }">layui-disabled</c:if>"
							   id="code" maxlength="50" placeholder="������˵�����" value="${menu.code}"
							   lay-verify="required" <c:if test="${null != menu.code}">disabled</c:if>>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"><span style="color: red">*</span>�˵�����</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input " id="name" placeholder="������˵�����"
							   lay-verify="required"  value="${menu.name}"  maxlength="100">
					</div>
				</div>

			</div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">�˵�����</label>
                    <div class="layui-input-inline " id="type">
                        <select lay-filter="type">
							<c:forEach items="${typeMaps}" var="typeMap" varStatus="t">
								<option value="${typeMap.key}"
										<c:if test="${menu.type ==typeMap.key}">selected</c:if> >${typeMap.value}</option>
							</c:forEach>
                        </select>
                    </div>
                </div>
				<div class="layui-inline">
					<label class="layui-form-label">�ϼ��˵�</label>
					<div class="layui-input-inline " >
						<input type="hidden" class="layui-input " id="menuCode" value="${menuCode}">
						<input type="hidden" class="layui-input " id="menuName" value="${menuName}">

						<input type="text" id="menutreeselect"  placeholder="${menuName}" value="${menuName}"
							   autocomplete="off" class="layui-input">
					</div>
				</div>
            </div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">��ťģʽ</label>
					<div class="layui-input-inline " id="buttonMode">
						<select>
							<c:forEach items="${buttonModeEnumMaps}" var="buttonModeEnumMap" varStatus="b">
								<option value="${buttonModeEnumMap.key}"
										<c:if test="${menu.buttonMode ==buttonModeEnumMap.key}">selected</c:if> >${buttonModeEnumMap.value}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">״̬</label>
					<div class="layui-input-inline " id="status">
						<select>
							<c:forEach items="${statusMaps}" var="statusMap" varStatus="s">
								<option value="${statusMap.key}"
										<c:if test="${menu.status ==statusMap.key}">selected</c:if> >${statusMap.value}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">�˵�ͼ��</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input " id="icon" placeholder="������˵�ͼ��"
							   value="${menu.icon}" maxlength="50">
					</div>
				</div>
				<%--<div class="layui-inline">--%>
					<%--<button class="layui-btn  layui-btn-sm layui-btn-warm ">ѡ��ͼ��</button>--%>
				<%--</div>--%>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label"><span style="color: red">*</span>�˵�����</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input " id="sort" placeholder="������˵�����"
							   lay-verify="required|number" value="${menu.sort}">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">�˵�URL</label>
					<div class="layui-input-inline" >
						<input type="text" class="layui-input layui-disabled" disabled  id="url" placeholder="������˵�URL"
							    value="${menu.url}">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">Ȩ�ޱ�ʶ</label>
				<div class="layui-input-block" style="width: 78%;">
					<input type="text" class="layui-input  layui-disabled" disabled  maxlength="200" id="authIdentity"
						   placeholder="������Ȩ�ޱ�ʶ������ԡ�;������" value="${menu.authIdentity}">
				</div>
			</div>
			<div class="layui-form-item" style="text-align: center;margin-top: 20px;">
				<a class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit><i class="layui-icon">&#xe609;</i>���� </a>
			</div>
		</form>
		<script type="text/javascript" src="/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="/js/system/menu/editMenu.js"></script>
	</body>
</html>