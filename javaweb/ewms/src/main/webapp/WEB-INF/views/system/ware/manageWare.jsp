<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>库房库区管理</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="../../plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">

    <style type="text/css">
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
</head>
<body>

<div class="admin-main">
    <div style="margin-left:5px;height:100%;">

        <div class="ztree-out" style="width:180px;">
            <div class="mini-toolbar">&nbsp;&nbsp;库房库区管理</div>
            <div class="ztree-in">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
        <div class="ztree-right" style="margin-left: 185px;">
            <blockquote class="layui-elem-quote" style="margin-left: 10px;height: 48px">
                <div style="float: left">
                    <a href="javascript:void(0)" class="layui-btn" id="add">新增</a>
                    <a href="javascript:void(0)" class="layui-btn" id="edit">修改</a>
                    <a href="javascript:void(0)" class="layui-btn" id="delete">删除</a>
                </div>
                <br>
                <div style="float: right">
                    <input id="id" class="mini-textbox" type="hidden"/>
                    <input id="level" class="mini-textbox" type="hidden"/>
                    库房编码:
                    <input id="code" class="mini-textbox" disabled/>
                    库房名称:
                    <input id="name" class="mini-textbox" disabled/>
                    编码：
                    <input id="codeSearch" class="mini-textbox"/>

                    <a href="javascript:void(0)" class="layui-btn" id="search">查找</a>
                </div>
            </blockquote>

            <div style="margin-left: 10px;">
                <table id="wareList" lay-filter="wareList"></table>
            </div>

        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="../../plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/ware/manageWare.js"></script>
<script type="text/javascript" src="../../js/ewms.js"></script>
</html>