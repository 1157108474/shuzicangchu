<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>退回环节</title>
	<link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
	<style type="text/css">
		select{
			font-family: inherit;
			font-size: inherit;
			font-style: inherit;
			font-weight: inherit;
			outline: 0;
		}
		select {
			-webkit-appearance: menulist;
			box-sizing: border-box;
			align-items: center;
			white-space: pre;
			-webkit-rtl-ordering: logical;
			color: black;
			background-color: white;
			cursor: default;
			border-width: 1px;
			border-style: solid;
			border-color: initial;
			border-image: initial;
		}
		select{
			border-radius: 5px;
			text-rendering: auto;
			color: initial;
			letter-spacing: normal;
			word-spacing: normal;
			text-transform: none;
			text-indent: 0px;
			text-shadow: none;
			display: inline-block;
			text-align: start;
			margin: 0em 0em 0em 0em;
			font: 13.3333px Arial;
		}
	</style>
</head>
<body class="childrenBody" style="width: 90%;">
<form class="" action="">
	<input type="hidden"  class="layui-input " value="${taskId}" name="taskId" id="taskId">
	<input type="hidden"  class="layui-input " value="${comment}" name="comment" id="comment">
	<div class="layui-form-item" style="width: 90%;margin: 6% 10%;">
		<label class="layui-form-label" style="font-size: 14px">退回环节选择:</label>
		<div style="padding-left: 120px">
			<select class="formTemSta" name="formTemSta" style="width: 70%">
				<c:forEach var="hisPart" items="${requestScope.list}">
					<option value="${hisPart.id}">${hisPart.name}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<a class="layui-btn"  id="gotoNext"  style="float: left;margin-top: 6px;margin-left: 35%;height: 30px;line-height: 30px;width: 50px">确定</a>
	<a class="layui-btn" type="reset"  id="reset"  style="float: left;margin-left: 10%;margin-top: 6px;height: 30px;line-height: 30px;width: 50px">取消</a>
</form>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/js/system/act/processNextPart.js"></script>
</body>
<script>
    $("#reset").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    $("#gotoNext").click(function () {
        var taskId = $("#taskId").val();
        $.post("taskRollBackByHis.json",{'taskId':taskId,"activitiBackId":$(".formTemSta").val(),"comment":$("#comment").val()},
            function(data) {
            	if(data){
                    layer.alert(
                        "驳回成功",
                        {icon: 1,closeBtn: 0 },
                        function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
				}else{
                    layer.alert(
                        "驳回失败",
                        {icon: 2,closeBtn: 0 },
                        function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
				}
            },"JSON");
        /*var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);*/
    });
</script>
</html>