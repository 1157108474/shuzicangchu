<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>��֯���Ź���</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">

    <style type="text/css">
        .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
    </style>
</head>
<body>

<div class="admin-main">
    <div  style="margin:5px 10px;height:100%;"  >
        <div class="ztree-out" style="width:340px;"  >
            <div class="mini-toolbar">&nbsp;&nbsp;��֯��������</div>
            <div class="ztree-in">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
        <div class="ztree-right" style="margin-left: 350px;" >
            <div class="mini-toolbar">&nbsp;&nbsp;��֯������Ϣ</div>
            <form class="layui-form layui-row layui-col-space10" action="" style="width:50%;margin-top: 10px">

                <input type="hidden" class="layui-input "   id="id">
                <input type="hidden"  class="layui-input "   id="parentId">
                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label"><span style="color: red">*</span>�ϼ�����</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input layui-disabled" disabled maxlength="16" id="parentName">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label"><span style="color: red">*</span>����</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input "  lay-verify="required"  value="${dic.code}"
                               maxlength="16" name="code" id="code">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label"><span style="color: red">*</span>����</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input "  lay-verify="required"  value="${dic.name}"
                               maxlength="16" name="name" id="name">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block"  id="type">
                        <select id="typeSelect"  lay-filter="kind"  lay-verify="required" >
                            <option value="0" >�ֲ�</option>
                            <option value="1" >����</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label"><span style="color: red">*</span>����</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input "  lay-verify="number" maxlength="16"  id="sort">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input layui-disabled" disabled lay-verify="number" maxlength="16"  id="levelCount">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label"><span style="color: red">*</span>״̬</label>
                    <div class="layui-input-block" id="status">
                        <select id="statusSelect" lay-filter="kind"  lay-verify="required" >
                            <option value="1" >����</option>
                            <option value="0" >����</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label">��ע</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input "    value="${dic.memo}" maxlength="16"  id="memo">
                    </div>
                </div>
                <div class="layui-form-item" style="text-align: center;">
                    <a class="layui-btn" lay-filter="addNews" id="addNews" style="display: none" lay-submit >ȷ��</a>
                    <a class="layui-btn" type="reset" style="display: none" id="reset"  >ȡ��</a>
                </div>
            </form>

        </div>
    </div>

</div>
</body>

<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="/js/system/dept/manageDepart.js"></script>


</html>