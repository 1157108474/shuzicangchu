<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title></title>
		<link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
		<link href="/css/public.css" rel="stylesheet" />
		<link rel="stylesheet" href="/plugins/font-awesome/css/font-awesome.min.css">
	</head>
	<body>
	<div class="admin-main">
		<blockquote class="layui-elem-quote" style="margin-left: 10px!important;margin-top: 10px">
			<a href="javascript:void(0);" class="layui-btn add"   id="add" >新增</a>
			<a href="javascript:void(0);" class="layui-btn edit"   id="edit">编辑</a>
			<a href="javascript:void(0);" class="layui-btn layui-btn-danger "  id="delAll">删除</a>
			<a href="javascript:void(0);" class="layui-btn manageButton"   id="manageButton">功能按钮</a>
		</blockquote>
		<div style="margin-left: 10px;">
			<div id="demo" ></div>
		</div>
	</div>
	</body>
	<script type="text/javascript" src="/plugins/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="/js/system/menu/manageMenu.js"></script>
</html>