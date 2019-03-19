<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="GBK">
<title>库房库区管理</title>
<link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all" />

<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
</head>
<body>

	<div class="admin-main">

		<div style="margin-left: 5px;">
			<input id="pre" value="${pre}" type="hidden">
			<div class="layui-form-item bill-search">
				<table>
					<tr>
						<td>
							<div class="layui-form-item">
								<label class="layui-form-label">编码：</label>
								<div class="layui-input-block">
									<input type="text" id="code" style="width: 150px"
										class="layui-input">
								</div>
							</div>
						<td>
						<td>
							<div class="layui-form-item">
								<label class="layui-form-label">名称：</label>
								<div class="layui-input-block">
									<input type="text" id="name" style="width: 150px"
										class="layui-input">
								</div>
							</div>
						</td>
						<td class="bill-td">
							<button class="layui-btn layui-btn-warm bill-td-button"
								id="search">查询</button>
						</td>
					</tr>
				</table>
				<div style="margin-left: 10px;">
					<table id="locationList" lay-filter="locationList"></table>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/ewms.js"></script>
<script type="text/javascript" src="/js/system/ware/locationWare.js"></script>


</html>