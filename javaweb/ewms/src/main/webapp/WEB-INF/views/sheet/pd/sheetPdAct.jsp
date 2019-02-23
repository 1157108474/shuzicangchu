<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增物资盘点单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>

</head>
<body>
<div id="formbody" class="formbody">

    <div id="km_toolbar" class="mini-toolbar" style="height:20px;">
        <div style="float:left">
            <div style="float:left;margin-left:15px;">
                <button id="saveSheet" class="btnfbs"  style="display:none"> 保存</button>
                <button id="submit" class="btnfbs" style="display:none"> 提交</button>
                <button id="review" class="btnfbs" style="display:none"> 审核</button>
                <button id="reject" class="btnfbs"  style="display:none"> 驳回</button>
                <button id="complete" class="btnfbs" style="display:none"> 办结</button>
                <button id="print" class="btnfbs" style="display:none"> 打印</button>
            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                单据编号：<label id="code">${sheet.code}</label>
                制单部门：<label>${sheet.depName}</label>
                制单人：<label>${sheet.personName}</label>
                制单时间：<label id="date">${sheet.createDate}</label>
            </span>
            <input id="id" value="${sheet.id}"  type="hidden"/>
            <input id="menuCode" value="${menuCode}"  type="hidden"/>
            <input  class="layui-input " value="${taskId}" name="taskId" id="taskId" type="hidden"/>
            <input id="reloadDetailsgrid" value=""  type="hidden"/>
        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">库存盘点单</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库房：</label>
                                    <div class="layui-input-block">
                                        <select name="extendInt1" id="extendInt1"  lay-filter="extendInt1" disabled="disabled">
                                            <option value="0">请选择...</option>
                                            <c:forEach items="${requestScope.wareHouses}" var="wh">
                                                <c:if test="${wh.id==sheet.extendInt1}">
                                                    <option value="${wh.id}-${wh.name}" selected="selected">${wh.name}</option>
                                                </c:if>
                                                <c:if test="${wh.id!=sheet.extendInt1}">
                                                    <option value="${wh.id}-${wh.name}">${wh.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">盘点名称：<span style="color: red">*</span></label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input"
                                               id="extendString1" name="extendString1" value="${sheet.extendString1}" disabled="disabled">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">盘点类型：<span style="color: red">*</span></label>
                                    <div class="layui-input-block">
                                        <select id="extendInt2" name="extendInt2" disabled="disabled">
                                            <option value="0">请选择...</option>
                                            <c:forEach items="${requestScope.pdTypes}" var="pdtype">
                                                <c:if test="${pdtype.id==sheet.extendInt2}">
                                                    <option value="${pdtype.id}" selected="selected">${pdtype.name}</option>
                                                </c:if>
                                                <c:if test="${pdtype.id!=sheet.extendInt2}">
                                                    <option value="${pdtype.id}">${pdtype.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备注：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input"
                                               id="memo" name="memo" disabled="disabled" value="${sheet.memo}">
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
                                    <button class="btnfbs" style="width: 80px!important;display:none;" id="addDetails" > 添加明细 </button>
                                    <button class="btnfbs" style="width: 80px!important;display:none;" id="deleteDetails" > 删除明细 </button>
                                    <a href="javascript:void(0);" class="btnfbs" style="width: 80px!important;display:none;" id="exprotPdResult" > 导出盘点明细 </a>
                                    <a href="javascript:void(0);" class="btnfbs" style="width: 80px!important;display:none;" id="importPdResult" > 导入盘点结果 </a>
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
                                <table  id="activitiHis" lay-filter="activitiHis"></table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/dateUtil.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/pd/pd.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>
<script type="text/javascript" >
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
            <c:if test="${curbtn.code =='saveSheet'}">
                $("#extendInt1").attr("disabled", false);
                $("#extendString1").attr("disabled", false);
                $("#extendInt2").attr("disabled", false);
                $("#memo").attr("disabled", false);
            </c:if>
        </c:forEach>
    });
</script>
<script type="text/html" id="barDemo2" >
    {{# if( d.stockStatus == 0 ) { }}
    <a  lay-event="edit" style="color:#1E9FFF">编辑</a>
    {{# }else{ }}
    {{#} }}
</script>
<script type="text/html" id="barDemo3" >
    {{# if( d.stockResult == 1 ) { }}
    <a  lay-event="addPY" style="color:#1E9FFF">添加盘盈数据</a>
    {{# }else{ }}
    {{#} }}
</script>
</html>

