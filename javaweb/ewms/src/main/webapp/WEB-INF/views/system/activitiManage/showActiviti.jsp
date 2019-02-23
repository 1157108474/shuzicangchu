<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>流程管理</title>
	<link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
</head>
<body>
<div class="admin-main">
	<blockquote class="layui-elem-quote" style="margin-left: 20px;margin-top: 5px;">
		<a href="<%=path %>/model/create.htm?name=物资盘点&&key=物资盘点&&description=物资盘点审批流程" class="layui-btn" id="add">新增</a>
		<a href="<%=path %>/modeler.html?modelId=${model.id}" target="_blank" class="layui-btn" id="edit">修改</a>
		<a href="<%=path %>/activiti/modelDel.do?id=${model.id}" class="layui-btn" id="delete">删除</a>
		<a href="<%=path %>/activiti/modelDeploy.do?id=${model.id}" class="layui-btn" id="current">部署</a>

	</blockquote>
	<div style="margin-left: 20px;">
		<div id="tree" class="ztree"></div>
	</div>
</div>
<table width="100%" class="need-border">
	<thead>
	<tr>
		<th>ID</th>
		<th>KEY</th>
		<th>Name</th>
		<th>Version</th>
		<th>创建时间</th>
		<th>最后更新时间</th>
		<th>元数据</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${list}" var="model">
		<tr>
			<td>${model.id }</td>
			<td>${model.key }</td>
			<td>${model.name}</td>
			<td>${model.version}</td>
			<td><fmt:formatDate value="${model.createTime}" pattern="yyyy-MM-dd" /></td>
			<td><fmt:formatDate value="${model.lastUpdateTime}" pattern="yyyy-MM-dd" /></td>
			<td>${model.metaInfo}</td>
			<td>
				<a href="<%=path %>/modeler.html?modelId=${model.id}" target="_blank">编辑</a>
				<a href="<%=path %>/system/activitiManage/modelDeploy.htm?id=${model.id}">部署</a>
				导出(<a href="<%=path %>/system/activitiManage/export/${model.id}/bpmn" target="_blank">BPMN</a>
					 |&nbsp;<a href="${ctx}/workflow/model/export/${model.id}/json" target="_blank">JSON</a>
				|&nbsp;<a href="javascript:;" onclick='showSvgTip()'>SVG</a>)
				<a href="<%=path %>/system/activitiManage/modelDel.htm?id=${model.id}">删除</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

</body>

</html>