<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zthc.ewms.system.user.entity.guard.UserEnums" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>角色管理</title>
	<link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="admin-main">
	<blockquote class="layui-elem-quote" style="margin-left: 10px!important;margin-top: 5px;height: 28px">
		<div style="float: left">
			<a href="javascript:void(0);" class="layui-btn add"   id="add" >新增</a>
			<a href="javascript:void(0);" class="layui-btn edit"   id="edit">编辑</a>
			<a href="javascript:void(0);" class="layui-btn layui-btn-danger "  id="delAll">删除</a>
			<a href="javascript:void(0);" class="layui-btn allotMenuButton"   id="allotMenuButton">分配权限</a>
		</div>
		<div style="float: right">
			角色名称:
			<input name="name" id="roleName" autocomplete="on"  class="mini-textbox"
				   style="width:150px;display:inline; " type="text">
			<button class="layui-btn" lay-submit lay-filter="formSubmit">立即查询</button>
		</div>
	</blockquote>
	<div style="margin-left: 10px;width:58%;float: left">
		<table  id="manageRole" lay-filter="manageRole"></table>
	</div>
	<div style="margin-left: 1%;float: left;width:39%;">
		<div class="layui-col-xs12">
			<div class="layui-collapse" >
				<div class="layui-colla-item">
					<h2 class="layui-colla-title" style="background-color: white; " ><div id="tabName">角色用户</div></h2>
					<div class="layui-colla-content layui-show" style="padding:0px!important;">
						<div class="layui-elem-quote" style="bottom: auto" >
							<a href="javascript:void(0);" class="layui-btn addUser" id="addUser">添加用户</a>
							<a href="javascript:void(0);" class="layui-btn delUser" id="delUser">移除用户</a>
						</div>
						<div class="layui-form-item" style="display:none;">
							<label class="layui-form-label">roleCode</label>
							<div class="layui-input-block">
								<input type="hidden" class="layui-input" id="roleCode" value="">
							</div>
						</div>
						<table  id="manageUser" lay-filter="manageUser" style="margin-top: -2px!important;"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/role/manageRole.js"></script>
<!--用户状态-->
<script type="text/html" id="showUserStatus">
	{{#  if(d.status == '<%=UserEnums.StatusEnum.ENABLE.getStatus()%>'){ }}
	<span class="layui-blue"><%=UserEnums.StatusEnum.ENABLE.getMeaning()%></span>
	{{#  } else if(d.status == '<%=UserEnums.StatusEnum.DISABLE.getStatus()%>'){ }}
	<span class="layui-red"><%=UserEnums.StatusEnum.DISABLE.getMeaning()%></span>
	{{#  } else { }}
	<span class="layui-blue"></span>
	{{#  }}}
</script>

</html>