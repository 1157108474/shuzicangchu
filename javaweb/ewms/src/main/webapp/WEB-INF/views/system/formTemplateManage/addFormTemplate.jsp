<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%><!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增单据</title>
    <link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
</head>
<body class="childrenBody" style="width: 90%">
<form class="layui-form layui-row layui-col-space10" action="">

    <%--<input  class="layui-input " value="${task.id}" name="idStr">
    <input type="hidden" class="layui-input " value="${task.guid}" name="guid">--%>


    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">单据编码<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="空"
                   maxlength="16" name="formTemCard">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">单据名称<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="空"
                   maxlength="16" name="formTemName">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">单据种类<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" value="${dictionary.name}"
                   maxlength="16" disabled="disabled">
            <input type="hidden" class="layui-input "
                   maxlength="16" name="formTemDic" value="${dictionary.id}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">单据前缀<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="空"
                   maxlength="16" name="formTemPre">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">流程名称<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <%--<select  lay-filter="kind"  lay-verify="required" value="" name="processDefinitionKey" id="actDics">
            <option value=""></option>
            <c:forEach var="actDics" items="${requestScope.actDics}">
            <option value="${actDics.id}">${actDics.name}</option>
            </c:forEach>
            </select>--%>
            <input type="text" class="layui-input " id="templateName" lay-verify="required"
                   lay-filter="templateName" autocomplete="off" value="">
            <input type="hidden" name="processDefinitionKey" id="processDefinitionKey"
                   value="">
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
            <input type="text" class="layui-input " id="formTemMenu" name="formTemMenu" lay-verify="required"
             autocomplete="off" value="">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">排序<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " lay-verify="required" placeholder="空"
                   maxlength="16" name="ext1">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">状态<span style="color: red">*</span></label>
        <div class="layui-input-block">
            <select lay-filter="kind" lay-verify="required" value="" name="formTemSta">
                <option value="1">禁用</option>
                <option value="2" selected="selected">启用</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label">备注<%--<span style="color: red">*</span>--%></label>
        <div class="layui-input-block">
            <input type="text" class="layui-input " placeholder="空"
                   maxlength="16" name="formTemCom">
        </div>
    </div>

    <div class="layui-form-item" style="text-align: center;">
        <a class="layui-btn" lay-filter="addFormTemplate" lay-submit><i class="layui-icon">&#xe609;</i>提交保存 </a>
    </div>
</form>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>


</body>
<script type="text/javascript">
    layui.config({
        base: '/plugins/static/js/'
    }).use(['form', 'layer', 'treeselect'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;
        var treeselect = layui.treeselect;



        $.get('/system/menu/listMenu.json',function (ret) {
            treeselect.render({
                elem:"#formTemMenu",
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


        form.on("submit(addFormTemplate)", function (lab) {
            if($("#processDefinitionKey").val()==""){
                layer.alert('所选流程尚未部署！');
            }else{
                $.ajax({
                    type: "POST",
                    url: "/formTemplate/formTemplateManage/addFormTem.json",
                    dataType: "json",
                    data: JSON.parse(JSON.stringify(lab.field)),
                    success: function (ret) {
                        if (ret.status == 1) {
                            layer.msg('新增成功！', function () {
                                parent.layui.table.reload("manageFromTemTable");
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
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
            return false;
        });

    });


    function obtainPart(dataId, dataName) {
        document.getElementById("templateName").value = dataName;
        document.getElementById("processDefinitionKey").value = dataId;
    }


</script>
</html>