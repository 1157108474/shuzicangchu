<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title></title>
		<link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
		<link rel="stylesheet" href="/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
	</head>
	<body>
	<div class="admin-main">
		<div class="layui-form-item" style="display:none;">
			<label class="layui-form-label">ID</label>
			<div class="layui-input-block">
				<input type="hidden" id="roleCode" class="layui-input" value="${role.roleCode}">
			</div>
		</div>
		<form class="layui-form"  style="width: 90%;">
			<div id="treeDemo" class="ztree" style="width: 70%;margin-left: 30%"></div>
			<div class="layui-form-item" style="text-align: center;margin-top: 10px;margin-bottom: 10px">
				<a class="layui-btn" id="addNews"><i class="layui-icon">&#xe609;</i>±£´æ </a>
			</div>
		</form>
	</div>
	</body>
	<script type="text/javascript" src="/plugins/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/plugins/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="/plugins/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="/plugins/ztree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="/js/system/role/allotMenuButton.js"></script>
</html>