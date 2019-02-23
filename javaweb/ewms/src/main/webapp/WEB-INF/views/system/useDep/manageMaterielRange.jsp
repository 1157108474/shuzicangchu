<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%
	List<String> buttonNames  = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>


	<title>物料范围管理</title>
	<link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>

</head>
<body>
<div style="width:99% ">
    <input type="hidden" id="useId"  value="${useId}">
    <input type="hidden" id="ztId"  value="${ztId}">
    <div style="float:left;width:48%;margin-left: 1%">
        <table id="table1"></table>
    </div>
    <div style="float:left;width:3%;text-align:center">
        <br>
        <button class="layui-btn" id="insert" style="margin-left:4px"> > </button>
        <br>
        <br>
        <button class="layui-btn" style="margin-left:1px" id="delete"> < </button>
    </div>
    <div style="float:right;width:48%">
        <table id="table2"></table>
    </div>
    <hr>
    <div style="float: right">
        <button class="layui-btn layui-btn-sm" id="addNews">提交</button>
    </div>
</div>
</body>


<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="../../js/system/useDep/manageMaterielRange.js"></script>

</html>