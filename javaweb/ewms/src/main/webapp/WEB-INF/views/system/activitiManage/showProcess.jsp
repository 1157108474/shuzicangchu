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
		<a href="<%=path %>/model/create.htm?" class="layui-btn" id="add">新增</a>
		<a href="<%=path %>/modeler.html?modelId=${model.id}" target="_blank" class="layui-btn" id="edit">修改</a>
		<a href="<%=path %>/activiti/modelDel.do?id=${model.id}" class="layui-btn" id="delete">删除</a>
	</blockquote>
	<div style="margin-left: 20px;">
		<div id="tree" class="ztree"></div>
	</div>
</div>

<table width="100%" class="need-border">
	<thead>
	<tr>
		<th>ProcessDefinitionId</th>
		<th>DeploymentId</th>
		<th>名称</th>
		<th>KEY</th>
		<th>版本号</th>
		<th>XML</th>
		<th>图片</th>
		<th>部署时间</th>
		<th>是否挂起</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${requestScope.processDefinitions}" var="process">
		<tr>
			<td>${process.id }</td>
			<td>${process.deploymentId }</td>
			<td>${process.name }</td>
			<td>${process.key }</td>
			<td>${process.version }</td>
			<td><a target="_blank" href='/workflow/resource/read?processDefinitionId=${process.id}&resourceType=xml'>${process.resourceName }</a></td>
			<td><a target="_blank" href='/workflow/resource/read?processDefinitionId=${process.id}&resourceType=image'>${process.diagramResourceName }</a></td>
				<%-- <td>${deployment.deploymentTime }</td> --%>
			<c:forEach items="${requestScope.deployments}" var="deployment">
				<c:if test="${deployment.id== process.deploymentId}">
					<td><fmt:formatDate value="${deployment.deploymentTime }" pattern="yyyy-MM-dd" /></td>
				</c:if>
			</c:forEach>
			<td>${process.suspended} |
				<c:if test="${process.suspended }">
					<a href="processdefinition/update/active/${process.id}">激活</a>
				</c:if>
				<c:if test="${!process.suspended }">
					<a href="processdefinition/update/suspend/${process.id}">挂起</a>
				</c:if>
			</td>
			<td>
				<a href='<%=path %>/system/processManage/processDelete.htm?deploymentId=${process.deploymentId}'>删除</a>
				<a href='<%=path %>/system/processManage/processDeleteProcessInstance.htm?deploymentId=${process.deploymentId}'>删除实例</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

</body>

</html>