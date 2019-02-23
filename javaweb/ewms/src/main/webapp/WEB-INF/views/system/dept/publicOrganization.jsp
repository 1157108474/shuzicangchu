<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>库存组织列表</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="//plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">

    <style type="text/css">
        .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
    </style>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline" style="margin-left:30px;width: 100px" id="select">
                    <select  lay-filter="kind"  lay-verify="required" >
                        <option value="name" >部门名称</option>
                        <option value="code" >部门编号</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="content" placeholder="请输入搜索的内容" />
                </div>
                <a class="layui-btn btnSearch" id="btnSearch">搜索</a>
            </div>
        </form>
    </blockquote>
    <div  style="margin-left:10%;border:1px solid #617775;width:80%;position:absolute; height:70%; overflow-y:auto"  >
        <ul id="treeDemo" class="ztree"></ul>
    </div>

    <div  style="margin-left:40%;bottom: 10px!important;position:absolute;">
        <a class="layui-btn"  id="addNews" >确定</a>
        <a class="layui-btn" type="reset"  id="reset"  >取消</a>

    </div>
</div>
</body>

<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="/js/system/dept/publicOrganization.js"></script>


</html>