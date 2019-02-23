<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>�����ڸ澯��ϸ</title>
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
					<input type="hidden" class="layui-input " id="id" value="${bzq.id}">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">���ϱ���</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" value="${bzq.materialcode}" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">��������</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" value="${bzq.materialname}" />
					</div>
				</div>

			</div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">��������</label>
                    <div class="layui-input-inline " >
            		    <input type="text" class="layui-input" value="${bzq.description}" />
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">�����ͺ�</label>
                    <div class="layui-input-inline ">
                        <input type="text" class="layui-input" value="${bzq.materialmodel}" />
                    </div>
                </div>
            </div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">�������</label>
					<div class="layui-input-inline ">
						<input type="text" class="layui-input" value="${bzq.storecount}" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">���ʱ��</label>
					<div class="layui-input-inline ">
						<input type="text" class="layui-input" value="${bzq.createdate}" />
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">������ʱ��</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" value="${bzq.expirationtime}" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">ʣ������</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" value="${bzq.syday}" />
					</div>
				</div>
			</div>
		</form>
		<script type="text/javascript" src="/plugins/layui/layui.js"></script>
	</body>
</html>