<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>编辑单据</title>
	<link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
</head>
<body class="childrenBody" style="width: 90%">
<form class="layui-form layui-row layui-col-space10" action="">

	<input type="hidden"  class="layui-input " value="${formTemplate.id}" name="id" id="id">
	<div class="layui-form-item" style="margin-top: 10px;width: 90%;">
		<label class="layui-form-label">单据编码<span style="color: red">*</span></label>
		<div class="layui-input-block">
			<input type="text" class="layui-input "  lay-verify="required" value="${formTemplate.formTemCard}"
				   maxlength="16" name="formTemCard" id="formTemCard">
		</div>
	</div>
	<div class="layui-form-item" style="margin-top: 10px;width: 90%;">
		<label class="layui-form-label">单据名称<span style="color: red">*</span></label>
		<div class="layui-input-block">
			<input type="text" class="layui-input "  lay-verify="required" value="${formTemplate.formTemName}"
				   maxlength="16" name="formTemName" id="formTemName">
		</div>
	</div>
		<div class="layui-form-item" style="margin-top: 10px;width: 90%;">
			<label class="layui-form-label">单据种类<span style="color: red">*</span></label>
			<div class="layui-input-block">
				<input type="text" class="layui-input "  lay-verify="required" value="${dictionary.name}"
					   maxlength="16" disabled="disabled">
				<input type="hidden" class="layui-input "
					   maxlength="16" name="formTemDic" value="${dictionary.id}" id="formTemDic">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top: 10px;width: 90%;">
			<label class="layui-form-label">单据前缀<span style="color: red">*</span></label>
			<div class="layui-input-block">
				<input type="text" class="layui-input "  lay-verify="required" value="${formTemplate.formTemPre}"
					   maxlength="16" name="formTemPre" id="formTemPre">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top: 10px;width: 90%;">
			<label class="layui-form-label">流程名称<span style="color: red">*</span></label>
				<div class="layui-input-block">
					<input type="text" class="layui-input " id="templateName" name="templateName"
						   lay-filter="templateName" autocomplete="off" value="${processDefinition.name}">
					<input type="hidden" name="processDefinitionKey" id="processDefinitionKey"
						   value="${formTemplate.processDefinitionKey}">
			</div>
		</div>
		<%--<div class="layui-form-item" style="margin-top: 10px;width: 90%;">
			<label class="layui-form-label">报表名称<span style="color: red">*</span></label>
			<div class="layui-input-block">
			</div>
		</div>--%>
		<div class="layui-form-item" style="margin-top: 10px;width: 90%;">
			<label class="layui-form-label">关联菜单<span style="color: red">*</span></label>
			<div class="layui-input-block">
				<input type="hidden" class="layui-input " id="formTemMenu" name="formTemMenu" value="${formTemplate.formTemMenu}">
				<input type="hidden" class="layui-input " id="menuName" value="${menu.name}">

				<input type="text" class="layui-input " id="menuNameChoose" name="menuNameChoose" placeholder="${menu.name}"
					   autocomplete="off" value="${menu.name}">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top: 10px;width: 90%;">
			<label class="layui-form-label">排序<span style="color: red">*</span></label>
			<div class="layui-input-block">
				<input type="text" class="layui-input "  lay-verify="required" value="${formTemplate.ext1}"
					   maxlength="16" name="ext1" id="ext1">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top: 10px;width: 90%;">
			<label class="layui-form-label">状态<span style="color: red">*</span></label>
			<div class="layui-input-block">
				<select  lay-filter="kind"  lay-verify="required" value="" name="formTemSta" id="formTemSta">
					<c:if test="${formTemplate.formTemSta==1}">
						<option value="1" selected="selected">禁用</option>
					</c:if>
					<c:if test="${formTemplate.formTemSta!=1}">
						<option value="1">禁用</option>
					</c:if>
					<c:if test="${formTemplate.formTemSta==2}">
						<option value="2" selected="selected">启用</option>
					</c:if>
					<c:if test="${formTemplate.formTemSta!=2}">
						<option value="2">启用</option>
					</c:if>
				</select>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top: 10px;width: 90%;">
			<label class="layui-form-label">备注<span style="color: red">*</span></label>
			<div class="layui-input-block">
				<input type="text" class="layui-input "   value="${formTemplate.formTemCom}"
					   maxlength="16" name="formTemCom" id="formTemCom">
			</div>
		</div>


	<div class="layui-form-item" style="text-align: center;">
		<a class="layui-btn" lay-filter="editFormTemplate" lay-submit><i class="layui-icon">&#xe609;</i>提交保存 </a>
	</div>
</form>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>


</body>
<script type="text/javascript">
    layui.config({
        base:  '/plugins/static/js/'
	}).use(['form', 'layer','treeselect'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;

        var treeselect = layui.treeselect;
    		$.get('/system/menu/listMenu.json',function (ret) {
        		treeselect.render({
            		elem:"#menuNameChoose",
					data:ret.data,
					method:"post"
        		})
    		});


        var zfocus = true;
        $("#templateName").on("focus", function (e) {
            if (zfocus) {
                zfocus = false;
                layer.open({
                    type: 2,
                    title: '流程名称列表',
                    fixed: false,
                    area: ['60%', '90%'],
                    content: "generalTemplateName.htm"
                });
            }
        }).on("blur", function (e) {
            zfocus = true
        });

        form.on("submit(editFormTemplate)", function (lab) {
            if($("#processDefinitionKey").val()==""){
                layer.alert('所选流程尚未部署！');
            }else {
                var menuNameChoose = $("#menuNameChoose").val();
                var menuName = $("#menuName").val();
                var formTemMenu = (menuName == menuNameChoose? $("#formTemMenu").val():menuNameChoose);
                formTemMenu =(''==formTemMenu?'0':formTemMenu);
                console.log(formTemMenu);
                var data = {'id':$("#id").val(),
                    'processDefinitionKey':$("#processDefinitionKey").val(),
                    'formTemCard':$("#formTemCard").val(),
                    'formTemName':$("#formTemName").val(),
                    'formTemPre':$("#formTemPre").val(),
                    'formTemSta':$("#formTemSta").val(),
                    'formTemCom':$("#formTemCom").val(),
                    'formTemMenu':formTemMenu,
                    'formTemDic':$("#formTemDic").val(),
                    'ext1':$("#ext1").val()
                			};
                $.ajax({
                    type: "POST",
                    url: "editFormTem.json",
                    dataType: "json",
                    data: data,
                    success: function (ret) {
                        if (ret.status == 1) {
                            layer.msg('修改成功！', function () {
                                parent.layui.table.reload("manageFromTemTable");
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                // parent.location.reload();
                            });
                        } else if(ret.status == 0) {
                            layer.alert(ret.message);
                        }else{
                            layer.alert('修改失败！');
						}
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                    }
                });
            }
            //return false;
        })

    });
    function obtainPart(dataId, dataName) {
        document.getElementById("templateName").value = dataName;
        document.getElementById("processDefinitionKey").value = dataId;
    };

</script>
</html>