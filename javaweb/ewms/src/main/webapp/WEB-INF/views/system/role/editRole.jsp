<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gbk" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<title>�������༭��ɫ</title>
	<link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
</head>
<body  style="margin-top: 10px;width: 90%;margin-left: 5%" >
<form class="layui-form layui-form-pane " action="">
	<div class="layui-form-item" style="display:none;">
		<label class="layui-form-label">ID</label>
		<div class="layui-input-block">
			<input type="hidden" class="layui-input " id="id" value="${r.roleCode}">
		</div>
	</div>
    <div class="layui-form-item" >
	        <label class="layui-form-label"><span style="color: red">*</span>��ɫ����</label>
	        <div class="layui-input-block">
	            <input type="text" class="layui-input <c:if test="${r.roleCode !=null}">layui-disabled</c:if>"
					   id="roleCode" maxlength="50" placeholder="�������ɫ����" value="${r.roleCode}"
					   lay-verify="code" <c:if test="${r.roleCode !=null}">disabled</c:if>>
	        </div>
	</div>
	<div class="layui-form-item" >
		<label class="layui-form-label"><span style="color: red">*</span>��ɫ����</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" id="roleName" maxlength="50" placeholder="�������ɫ����"
				   value="${r.roleName}" lay-verify="required" >
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">��ɫ����</label>
		<div class="layui-input-block" id="roleType">
			<select>
				<option value="1" >����Ա��ɫ</option>
				<option value="2">ҵ���ɫ</option>
				<option value="3">����</option>
			</select>
		</div>
	</div>
	<div class="layui-form-item" >
		<label class="layui-form-label"><span style="color: red">*</span>����</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" id="sort" maxlength="50" placeholder="����������"
				   value="${r.sort}" lay-verify="required|number" >
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">״̬</label>
		<div class="layui-input-block" id="enabled">
			<select>
				<option value="1">����</option>
				<option value="-1">����</option>
			</select>
		</div>
	</div>
    <div class="layui-form-item">
        <label class="layui-form-label">��ע</label>
        <div class="layui-input-block">
			<input type="text" class="layui-input" id="remark" maxlength="50" placeholder="�����뱸ע"
				   value="${r.remark}"  >
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;margin-top: 20px">
    		<a class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit><i class="layui-icon">&#xe609;</i>���� </a>
    </div>
</form>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/role/editRole.js"></script>
</body>
</html>