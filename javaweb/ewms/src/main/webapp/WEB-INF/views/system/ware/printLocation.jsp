<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>货位标签打印</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>

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
    <div style="margin:5px 10px;height:100%;">

        <div>
            <blockquote class="layui-elem-quote" style="margin-left: 10px;margin-top: 5px;height: 24px">
                <div style="float: left">
                    <a href="javascript:void(0)" class="layui-btn" id="print">打印</a>
                </div>
                <div style="float: right">
                    库存组织:
                    <input id="name" class="mini-textbox"/>
                    &nbsp;&nbsp;
                    库位:
                    <input id="begin" class="mini-textbox"/>
                    至
                    <input id="end" class="mini-textbox"/>
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
<script type="text/javascript" src="../../js/system/ware/printLocation.js"></script>
<script type="text/javascript" src="../../js/ewms.js"></script>
</html>