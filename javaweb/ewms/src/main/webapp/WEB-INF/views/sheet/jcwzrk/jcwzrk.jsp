<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增寄存物资入库单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <style type="text/css">
        .form-label {
            width: 220px !important;
        }

        .form-div-input {
            margin-left: 180px !important;
        }
    </style>
</head>
<body>
<div id="formbody" class="formbody">

    <div id="km_toolbar" class="mini-toolbar" style="height:20px;">
        <div style="float:left">
            <div style="float:left"></div>
            <div style="float:left;margin-left:15px;">
                <button id="saveSheet" class="btnfbs" style="display:none"> 保存</button>
                <button id="submit" class="btnfbs"> 提交</button>
                <button id="review" class="btnfbs" style="display:none"> 审核</button>
                <button id="reject" class="btnfbs" style="display:none"> 驳回</button>
            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                单据编号：<label id="sheetcode">${sheet.code}</label>
                制单部门：<label id="dep">${sheet.depName}</label>
                制单人：<label id="creator">${sheet.personName}</label>
                制单时间：<label id="createdate">${sheet.createDateStr}</label>
            </span>
            <input type="hidden" id="id" name="id" value="${sheet.id}">
            <input type="hidden" id="menuCode" name="id" value="${menuCode}">
            <input type="hidden" id="taskId" name="id" value="${taskId}">
            <input type="hidden" id="reloadStatus">
        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">寄存物资入库单</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table id="jcwzrkTable">
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><font color="red">* </font>寄存单位:</label>
                                    <div class="layui-input-block" style="position:relative;">

                                        <input type="text" class="layui-input" id="ownerdepName" value="${sheet.ownerDepName}">
                                        <button class="layui-btn bill-button" id="ownerdepBtn">...</button>
                                        <input class="layui-input" name="creator" id="ownerdepId" type="hidden" value="${sheet.ownerDep}">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备注：</label>
                                    <div class="layui-input-block">
                                        <input id="memo" name="memo" value="${sheet.memo}" class="layui-input"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <div class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">明细列表</h2>
            <div class="layui-colla-content layui-show">
                <div class="layui-tab">
                    <ul class="layui-tab-title">
                        <li class="layui-this">明细列表</li>
                        <li>操作履历</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 389px!important; ">
                        <!--明细列表-->
                        <div class="layui-tab-item layui-show">
                            <div class="mini-toolbar" style="height:20px;margin-left: -5px;">
                                <div style="float:left">
                                    <button class="btnfbs" style="width: 100px!important;" id="addDetails">
                                        新增明细
                                    </button>
                                    <button class="btnfbs" style="width: 80px!important;" id="deleteDetails"> 删除明细
                                    </button>
                                </div>
                            </div>
                            <div style="margin-left: -5px;">
                                <table id="detailsgrid" lay-filter="detailsgrid"></table>
                            </div>
                        </div>
                        <!--操作履历-->
                        <div class="layui-tab-item">
                            <div class="mini-toolbar" style="height:20px;margin-left: -5px;">
                            </div>
                            <div style="margin-left: -5px;">
                                <table id="activitiHis" lay-filter="activitiHis"></table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <script type="text/html" id="bar">
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
            <c:if test="${curbtn.code=='editDetails'}">
                <a lay-event="edit" class="layui-btn layui-btn-xs">编辑</a>
            </c:if>
        </c:forEach>
    </script>
</div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/jcwzrk/jcwzrk.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>
<script type="text/javascript" >
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
        </c:forEach>
    });
</script>
</html>

