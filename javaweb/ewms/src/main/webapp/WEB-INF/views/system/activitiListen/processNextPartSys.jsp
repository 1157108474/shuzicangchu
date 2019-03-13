<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>环节选择</title>
	<style type="text/css">
		.userNames li{
			margin-left: 5px;
		}
		li .li-tab-cha:hover {
			border-radius: 2px;
			background-color: #FF5722;
			color: #fff;
		}
		li .li-tab-cha{
			position: relative;
			display: inline-block;
			width: 18px;
			height: 18px;
			line-height: 20px;
			margin-left: 1px;
			top: 1px;
			text-align: center;
			font-size: 14px;
			color: #FF5722;
			transition: all .2s;
			-webkit-transition: all .2s;
		}
		.user-title {
			position: relative;
			height: 42px;
			line-height: 42px;
			padding: 0 15px 0 15px;
			color: #333;
			background-color: #f2f2f2;
			cursor: pointer;
			font-size: 14px;
		}
		td{
			align:center;
			text-align: center;
		}
	</style>
	<link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
</head>
<body>
<input id="taskId" value="${taskId}" type="hidden">
<input id="outcome" value="${outcome}" type="hidden">
<%-- <input id="comment" value="${comment}" type="hidden"> --%>
<div class="admin-main">
	<div style="margin-left: 10px;width:28%;float: left;margin-top: 10px;">
		<table  id="processNextpart" lay-filter="processNextpart"></table>
	</div>
	<div style="margin-left: 1%;float: left;width:58%;margin-top: 10px;">
		<div class="layui-col-xs12">
			<div class="layui-collapse">
				<div class="layui-colla-item">
					<h2 class="user-title" style="background-color: white; " >
						<div style="float: left;">选中用户：</div>
						<form>
							<div  class="userNames" name="userNames">
							</div>
							<a class="layui-btn" type="reset"  id="reset"  style="float: right;margin-left: 3px;margin-top: 6px;">取消</a>
							<a class="layui-btn"  id="gotoNext"  style="float: right;margin-top: 6px;">确定</a>
						</form>
					</h2>
					<div class="layui-colla-content layui-show" style="padding:0px;">
						<%--<table  id="partUser" lay-filter="partUser" style="margin-top: -10px"></table>--%>
							<div class="layui-form">
								<table class="layui-table"  id="partUser" lay-filter="partUser">
									<colgroup>
										<col width="3">
										<col width="180">
										<col width="80">
										<col>
									</colgroup>
									<thead>
									<tr>
										<th></th>
										<th style="text-align:center;">编号</th>
										<th style="text-align:center;">姓名</th>
									</tr>
									</thead>
									<tbody id="tableCon">
									</tbody>
								</table>

							</div>
							<div id="demo1"><input id="userCount" type="hidden" value="${count}"></div>
					</div>
				</div>
			</div>
		</div>
		<div>
		<br/><br/>
		<label class="layui-form-label" style="font-size: 14px;padding-top: 10px;">操作意见:</label>
		<div style="padding-left: 120px">
			<textarea name="comment" id="comment" style="width: 99%;height: 70px;"></textarea>
		</div>
	</div>
	</div>
	
	</div>
</div>
</body>
<script type="text/html" id="indexTpl">
	{{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/html" id="radioTpl">
	<input type="radio" value="" id="isda" name="p"/>
</script>
<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/js/system/act/processNextPartSys.js"></script>

<script>

    $("#reset").click(function () {
        //parent.global_index = 110;
        //window.parent.frames["childPage"].getReturnBut(true);
		//window.opener.getReturnBut(true);
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    var bool = true;
    $("#gotoNext").click(function () {
        if(bool){
            bool = false;
            var outcome = $("#outcome").val();
            var taskId = $("#taskId").val();
            var comment = $("#comment").val();
            $.post("isEndedByOut.json",{'taskId':taskId,'outcome':outcome,'comment':comment},
                function(data) {
                    if(data==1){//最后环节
                        $.ajax({
                            type: "POST",
                            url: "completeTask.json",
                            dataType: "json",
                            data: {'taskId':taskId,'comment':comment,'outcome':outcome},
                            success: function (ret) {
                                if (ret.status == 1) {
                                    layer.alert(
                                        "办结成功",
                                        {icon: 1,closeBtn: 0 },
                                        function () {
                                            var index = parent.layer.getFrameIndex(window.name);
                                            parent.layer.close(index);
                                        });
                                } else if(ret.status == 0) {
                                    layer.alert(
                                        ret.message,
                                        {icon: 2,closeBtn: 0 },
                                        function () {
                                            var index = parent.layer.getFrameIndex(window.name);
                                            parent.layer.close(index);
                                        });
                                }else{
                                    bool = true;
                                    layer.alert(
                                        "办结失败",
                                        {icon: 1,closeBtn: 0 },
                                        function () {
                                            var index = parent.layer.getFrameIndex(window.name);
                                            parent.layer.close(index);
                                        });
                                   /* layer.alert('办结失败！');*/
                                }
                            },
                            error: function (XMLHttpRequest) {
                                bool = true;
                                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                            }
                        });
                        /* $.post("completeTask.json",{'taskId':taskId,'comment':comment,'outcome':outcome},
                             function(data) {
                             console.log(data.message);
                                 if(data.status=='0'){
                                     layer.alert(
                                         "办结成功",
                                         {icon: 1,closeBtn: 0 },
                                         function () {
                                             var index = parent.layer.getFrameIndex(window.name);
                                             parent.layer.close(index);
                                         });
                                 }else{
                                     layer.alert(
                                         data.message,
                                         {icon: 2,closeBtn: 0 },
                                         function () {
                                             var index = parent.layer.getFrameIndex(window.name);
                                             parent.layer.close(index);
                                         });
                                 }
                             },"JSON");*/
                    }else{
                        var userId = $(".userId").val();
                        if(userId != undefined){
                            $.ajax({
                                type: "POST",
                                url: "completeMyPersonalTask.json",
                                dataType: "json",
                                data: {'userId':userId,'outcome':outcome,'taskId':taskId,'comment':comment},
                                success: function (ret) {
                                    if (ret.status == 1) {
                                        layer.alert(
                                            "完成任务",
                                            {icon: 1,closeBtn: 0 },
                                            function () {
                                                var index = parent.layer.getFrameIndex(window.name);
                                                parent.layer.close(index);
                                            });
                                    } else if(ret.status == 0) {
                                        layer.alert(
                                            ret.message,
                                            {icon: 2,closeBtn: 0 },
                                            function () {
                                                var index = parent.layer.getFrameIndex(window.name);
                                                parent.layer.close(index);
                                            });
                                    }else{
                                        layer.alert('完成任务失败！');
                                        bool = true;
                                    }
                                },
                                error: function (XMLHttpRequest) {
                                    bool = true;
                                    layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                                }
                            });

                        }else{
                            layer.alert("请选择人员");
                        }
                    }
                },"JSON")
		}else {
            layer.alert("已提交不允许重复提交！");
		}

    });
    function cha(){
        emptyRadio();
    }
</script>
</html>