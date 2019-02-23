<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=gbk" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<title>新增、编辑角色</title>
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
	        <label class="layui-form-label"><span style="color: red">*</span>角色代码</label>
	        <div class="layui-input-block">
	            <input type="text" class="layui-input <c:if test="${r.roleCode !=null}">layui-disabled</c:if>"
					   id="roleCode" maxlength="50" placeholder="请输入角色代码" value="${r.roleCode}"
					   lay-verify="code" <c:if test="${r.roleCode !=null}">disabled</c:if>>
	        </div>
	</div>
	<div class="layui-form-item" >
		<label class="layui-form-label"><span style="color: red">*</span>角色名称</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" id="roleName" maxlength="50" placeholder="请输入角色名称"
				   value="${r.roleName}" lay-verify="required" >
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">角色类型</label>
		<div class="layui-input-block" id="roleType">
			<select>
				<option value="1" >管理员角色</option>
				<option value="2">业务角色</option>
				<option value="3">其他</option>
			</select>
		</div>
	</div>
	<div class="layui-form-item" >
		<label class="layui-form-label"><span style="color: red">*</span>排序</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input" id="sort" maxlength="50" placeholder="请输入排序"
				   value="${r.sort}" lay-verify="required|number" >
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">状态</label>
		<div class="layui-input-block" id="enabled">
			<select>
				<option value="1">启用</option>
				<option value="-1">禁用</option>
			</select>
		</div>
	</div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
			<input type="text" class="layui-input" id="remark" maxlength="50" placeholder="请输入备注"
				   value="${r.remark}"  >
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;margin-top: 20px">
    		<a class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit><i class="layui-icon">&#xe609;</i>保存 </a>
    </div>
</form>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/role/editRole.js"></script>
</body>
</html>