<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="GBK">
	<title>���Ϸ������</title>
	<link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
	<link rel="stylesheet" href="../../plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">

	<style type="text/css">
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</head>
<body>

<div class="admin-main">
	<%--
    <blockquote class="layui-elem-quote" style="margin-left: 10px;margin-top: 5px;">
        <a href="javascript:void(0)" class="layui-btn" id="add">����</a>
        <a href="javascript:void(0)" class="layui-btn" id="edit">�޸�</a>
        <a href="javascript:void(0)" class="layui-btn" id="delete">ɾ��</a>
        <a href="javascript:void(0)" class="layui-btn" id="current">ˢ��</a>
    </blockquote>
    --%>
	<div  style="margin:5px 10px;height:100%;"  >
		<div class="ztree-out" style="width:260px;"  >
			<div class="mini-toolbar">&nbsp;&nbsp;���Ϸ������</div>
			<div class="ztree-in">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>
		<div class="ztree-right" style="margin-left: 270px;" >
			<div class="mini-toolbar">&nbsp;&nbsp;���Ϸ�����ϸ��Ϣ</div>
			<form class="layui-form layui-row layui-col-space10" action="" style="width:50%">

				<input type="hidden" class="layui-input " value="${spare.id}" name="idStr" id="id">
				<input type="hidden" class="layui-input " value="${spare.parentId}" name="parentIdStr" id="parentId">

				<div class="layui-form-item" style="margin-top: 5px;width: 90%;">
					<label class="layui-form-label">������������<span style="color: red">*</span></label>
					<div class="layui-input-block">
						<input type="text" class="layui-input " disabled   value="${spare.parentName}"
							   maxlength="16" id="parentName">
					</div>
				</div>

				<div class="layui-form-item" style="margin-top: 5px;width: 90%;">
					<label class="layui-form-label">�����������</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input " disabled  value="${spare.parentCode}"
							   maxlength="16" id="parentCode">
					</div>
				</div>
				<div class="layui-form-item" style="margin-top: 5px;width: 90%;">
					<label class="layui-form-label">��������<span style="color: red">*</span></label>
					<div class="layui-input-block">
						<input type="text" class="layui-input "  lay-verify="required"  value="${spare.name}"
							   maxlength="16" name="name" id="name">
					</div>
				</div>
				<div class="layui-form-item" style="margin-top: 5px;width: 90%;">
					<label class="layui-form-label">�������<span style="color: red">*</span></label>
					<div class="layui-input-block">
                        <input type="text" class="layui-input " lay-verify="codeverify" value="${spare.code}"
                               maxlength="16" name="code" id="code">
					</div>
				</div>

				<div class="layui-form-item" style="margin-top: 5px;width: 90%;">
					<label class="layui-form-label">״̬<span style="color: red">*</span></label>
					<div class="layui-input-block">

						<select  lay-filter="kind"  lay-verify="required" value="${spare.status}" name="statusStr" id="status">
							<option value=""></option>
							<option value="1" >����</option>
							<option value="0" >����</option>

						</select>
					</div>
				</div>

				<div class="layui-form-item" style="margin-top: 5px;width: 90%;">
					<label class="layui-form-label">����</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input "  lay-verify="sort"  value="${spare.sort}"
							   maxlength="16" name="sortStr" id="sort">
					</div>
				</div>

				<div class="layui-form-item" style="margin-top: 5px;width: 90%;">
					<label class="layui-form-label">��ע</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input "    value="${spare.memo}"
							   maxlength="16" name="memo" id="memo">
					</div>
				</div>

				<div class="layui-form-item" style="text-align: center;">
					<a class="layui-btn" lay-filter="editspare" style="display: none" lay-submit id="edit"><i class="layui-icon">&#xe609;</i>�ύ���� </a>
				</div>
			</form>

		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="../../plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/spare/manageSpare.js"></script>
</html>