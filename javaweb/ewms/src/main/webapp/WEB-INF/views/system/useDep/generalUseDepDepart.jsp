<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>ʹ�õ�λ�����б�</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
</head>
<body>
<div class="admin-main" style="margin-left:20px">

    <blockquote class="layui-elem-quote">
        <form class="layui-form" action="">

            ʹ�õ�λ����:
            <input name="name" autocomplete="on" placeholder="ʹ�õ�λ����" class="mini-textbox" type="text">
            <input id="ztId" type="hidden" value="${ztId}">
            <button class="layui-btn" lay-submit lay-filter="formSubmit" style="">��ѯ</button>
            <a href="javascript:void(0);" class="layui-btn add" id="add">ȷ��</a>
        </form>
    </blockquote>
    <div>
        <table id="providerList" lay-filter="providerList"></table>
    </div>
</div>
</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="../../js/system/useDep/generalUseDepDepart.js"></script>
</html>