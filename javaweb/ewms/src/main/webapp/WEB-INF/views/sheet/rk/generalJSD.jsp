<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>


    <title>接收单列表</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>

</head>
<body>
<div class="admin-main" style="margin-left:20px">
    <blockquote class="layui-elem-quote">
        <form class="layui-form" action="">

            接收单号:

            <input name="code" autocomplete="on" placeholder="接收单号" class="mini-textbox" type="text">

            订单编号:

            <input name="orderNum" autocomplete="on" placeholder="订单编号" class="mini-textbox" type="text">


            <button class="layui-btn" lay-submit lay-filter="formSubmit">查询</button>
            <a href="javascript:void(0);" class="layui-btn add" id="add">确认</a>


        </form>

    </blockquote>

    <div>

        <table id="jsdList" lay-filter="jsdList"></table>
    </div>
</div>
</body>


<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="../../js/sheet/rk/generalJSD.js"></script>

</html>