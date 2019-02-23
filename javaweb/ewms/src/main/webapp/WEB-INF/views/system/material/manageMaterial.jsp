<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>物料管理</title>
	<link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
	<link rel="stylesheet" href="../../plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">

	<style type="text/css">
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</head>
<body>

<div class="admin-main" >
	<div  style="margin-left:5px;height:100%;"  >
		<div class="ztree-out" style="width:230px;" >
			<div class="mini-toolbar">&nbsp;&nbsp;物料分类管理</div>
			<div class="ztree-in">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>
		<div class="ztree-right" style="margin-left:235px;">
				<blockquote class="layui-elem-quote layui-row" style="margin-left: 10px!important;margin-top: 5px;height: 52px ">
					<div class="layui-col-xs3 left-div" >
						<a href="javascript:void(0)" class="layui-btn" id="add">新增</a>
						<a href="javascript:void(0)" class="layui-btn" id="edit">修改</a>
						<a href="javascript:void(0)" class="layui-btn" id="delete">删除</a>
					</div>

					<div class="layui-col-xs9 left-div"  style="float: left;width:800px" >
						    <input id="spareId" name="spareId" class="mini-textbox" type="hidden"  />
						   分类编码:
							<input id="spareCode" name="spareCode" class="mini-textbox" disabled  />
							分类:
							<input id="spareName" name="spareName" class="mini-textbox" disabled />
							物料编码：
							<input id="code" name="code" class="mini-textbox" />
							物料描述：
							<input id="description" name="description" class="mini-textbox" />
						<a href="javascript:void(0)" class="layui-btn" id="search">查找</a>
					</div>
				</blockquote>

				<div style="margin-left: 10px;">
					<table id="materialList" lay-filter="materialList"></table>
				</div>

		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="../../plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/material/manageMaterial.js"></script>
<script type="text/javascript" src="../../js/ewms.js"></script>

</html>