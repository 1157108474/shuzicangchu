<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.zthc.ewms.system.menu.entity.guard.MenuButtonEnums" %>
<!DOCTYPE html>
<html lang="en">

	<head>
		<title>功能按钮</title>
		<link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all" />
	</head>

	<body>
		<div class="admin-main">
			<blockquote class="layui-elem-quote" style="margin-left: 10px!important;margin-top: 10px!important;margin-right: 10px!important;">
				<a href="javascript:;" class="layui-btn  layui-btn-small" id="add">新增</a>
				<a href="javascript:;" class="layui-btn  layui-btn-small" id="edit">编辑</a>
				<a href="javascript:;" class="layui-btn  layui-btn-danger" id="delAll">批量删除</a>
			</blockquote>
			<input type="hidden" class="layui-input menuCode" id="menuCode" value="${m.code}">
			<div style="margin-left: 10px;margin-right: 10px;">
				<table id="manageButton" lay-filter="manageButton"></table>
			</div>
		</div>
	</body>
	<!--状态-->
	<script type="text/html" id="showStatus">
		{{# if(d.status == <%=MenuButtonEnums.StatusEnum.ENABLE.getStatus()%>){ }}
		<span class="layui-blue"><%=MenuButtonEnums.StatusEnum.ENABLE.getMeaning()%></span>
		{{# } else if(d.status == <%=MenuButtonEnums.StatusEnum.DISABLE.getStatus()%>){ }}
		<span class="layui-red"><%=MenuButtonEnums.StatusEnum.DISABLE.getMeaning()%></span>
		{{# } else { }}
		<span class="layui-blue"></span>
		{{# }}}
	</script>
	<script type="text/javascript" src="/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="/js/system/menu/manageButton.js"></script>

</html>