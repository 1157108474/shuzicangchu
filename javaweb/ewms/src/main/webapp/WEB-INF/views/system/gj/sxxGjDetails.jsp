<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>上下限告警明细</title>
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
					<input type="hidden" class="layui-input " id="id" value="${sxx.id}">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">物料编码</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" value="${sxx.code}" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">物料名称</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" value="${sxx.name}" />
					</div>
				</div>

			</div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">物料型号</label>
                    <div class="layui-input-inline ">
                        <input type="text" class="layui-input" value="${sxx.models}" />
                    </div>
                </div>
				<div class="layui-inline">
					<label class="layui-form-label">库存数量</label>
					<div class="layui-input-inline ">
						<input type="text" class="layui-input" value="${sxx.con}" />
					</div>
				</div>
            </div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">下限数量</label>
					<div class="layui-input-inline ">
						<input type="text" class="layui-input" value="${sxx.stockdown}" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">上限数量</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" value="${sxx.stockup}" />
					</div>
				</div>
			</div>
			<div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">物料描述</label>
                    <div class="layui-input-inline " style="width:512px;" >
            		    <input type="text" class="layui-input" value="${sxx.description}" />
                    </div>
                </div>
			</div>

		</form>
		<script type="text/javascript" src="/plugins/layui/layui.js"></script>
	</body>
</html>