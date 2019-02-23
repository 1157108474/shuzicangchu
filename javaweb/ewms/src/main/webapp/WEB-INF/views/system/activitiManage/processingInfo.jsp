<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/js/system/act/activitiButton.js"></script>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>我的待办</title>
	<style>

	</style>
</head>
<body style="position: relative">
<div>
	<c:forEach items="${premissionButton}" var="pre">
		<a href="javascript:void(0);" onclick="clickButton('${pre.code}')">${pre.name}</a>
	</c:forEach>
</div>

	<div id="next"  style="position: fixed; top:100px;left:30px;">
		<input type="hidden" id="taskId" value="${taskId}">
		<div>
			<select>
			<c:forEach items="${nextPart}" var="nextPart">
				<option>${nextPart}</option>
			</c:forEach>
			</select>
		</div>
		<div>
			<select>
			<c:forEach items="${users}" var="users">
				<option>${users}</option>
			</c:forEach>
			</select>
		</div>
	</div>
</body>
<script>
    function getButton(taskId){
        var id = "#actSave"+taskId;
        $.post("/system/activitiButton/findActPartPermission.htm",{'taskId':taskId},
            function(data) {
                $(data).each(function(index,obj){
                    $(id).append(" | <a href=\""+obj.btnFun+"?taskId="+taskId+"\">"+obj.name+"</a>");
                });
            },"JSON");
    }
    function actSave(taskId){
        var id = "#actSave"+taskId;
        $.post("findOutComeListByTaskId.htm",{'taskId':taskId},
            function(data) {
                $(data).each(function(index,obj){
                    $(id).append(" | <a href=\"completeMyPersonalTask.json?taskId="+taskId+"&outName="+obj+"\">"+obj+"</a>");
                });
            },"JSON");
    }

</script>
</html>