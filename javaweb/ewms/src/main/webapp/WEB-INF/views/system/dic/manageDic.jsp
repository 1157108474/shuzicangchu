<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>数据字典</title>
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
        <a href="javascript:void(0)" class="layui-btn" id="add">新增</a>
        <a href="javascript:void(0)" class="layui-btn" id="edit">修改</a>
        <a href="javascript:void(0)" class="layui-btn" id="delete">删除</a>
        <a href="javascript:void(0)" class="layui-btn" id="current">刷新</a>
    </blockquote>
    --%>
    <div  style="margin-left:10px;height:100%;"  >
        <div class="ztree-out" style="width:220px;" >
            <div class="mini-toolbar">&nbsp;&nbsp;数据字典管理</div>
            <div class="ztree-in">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
        <div class="ztree-right" style="margin-left: 225px;">
            <div class="mini-toolbar">&nbsp;&nbsp;数据字典明细</div>
            <form class="layui-form layui-row layui-col-space10" action="" style="width:450px">

                <input type="hidden" class="layui-input " value="${dic.id}" name="idStr" id="id">
                <input type="hidden"  class="layui-input " value="${dic.parent.id}" name="parent.idStr" id="parentId">

                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label">上级名称<span style="color: red">*</span></label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input " disabled   value="${dic.parent.name}"
                               maxlength="16" id="parentName">
                    </div>
                </div>

                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label">上级编码</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input " disabled  value="${dic.parent.code}"
                               maxlength="16" id="parentCode">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label">名称<span style="color: red">*</span></label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input "  lay-verify="required"  value="${dic.name}"
                               maxlength="16" name="name" id="name">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label">编码<span style="color: red">*</span></label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input " lay-verify="codeverify" value="${dic.code}"
                               maxlength="16" name="code" id="code">
                    </div>
                </div>

                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label">状态<span style="color: red">*</span></label>
                    <div class="layui-input-block">

                        <select  lay-filter="kind"  lay-verify="required" value="${dic.status}" name="statusStr" id="status">
                            <option value=""></option>
                            <option value="1" >启用</option>
                            <option value="0" >禁用</option>

                        </select>
                    </div>
                </div>

                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label">排序</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input "  lay-verify="sort"  value="${dic.sort}"
                               maxlength="16" name="sortStr" id="sort">
                    </div>
                </div>

                <div class="layui-form-item" style="margin-top: 5px;width: 90%;">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input " value="${dic.memo}"
                               maxlength="16" name="memo" id="memo">
                    </div>
                </div>

                <div class="layui-form-item" style="text-align: center;">
                    <a class="layui-btn" lay-filter="editDic" style="display: none" lay-submit id="edit"><i class="layui-icon">&#xe609;</i>提交保存 </a>
                </div>
            </form>

        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="../../js/system/dic/manageDic.js"></script>

<script>

    var zNodes = [
    {id: '0',pid:'', name: '数据字典分类', open: true}
    <c:forEach items="${tree}" var="father" varStatus="f">
    , {id: '${father.id}', pId: '0', name: '${father.name}',code:'${father.code}',level:1}
    <c:forEach items="${father.children}" var="son" varStatus="s">
    , {id: '${son.id}', pId: '${son.parent.id}', name: '${son.name}',code:'${son.code}',level:2}
    <%--<c:forEach items="${son.children}" var="grandson" varStatus="g">--%>
    <%--, {id:${grandson.id}, pId: '${grandson.parent.id}', name: '${grandson.name}',code:'${grandson.code}',level:3}--%>
    <%--</c:forEach>--%>
    </c:forEach>
    </c:forEach>
    ];






</script>
</html>