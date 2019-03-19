<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增物资申领单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>

</head>
<body>
<div id="formbody" class="formbody">

    <div id="km_toolbar" class="mini-toolbar" style="height:20px;">
        <div style="float:left">
            <div style="float:left"></div>
            <div style="float:left;margin-left:15px;">
                <button id="saveSheet" class="btnfbs"> 保存</button>
                <button id="submit" class="btnfbs"> 提交</button>
                <button id="review" class="btnfbs"> 审核</button>
                <button id="reject" class="btnfbs"> 驳回</button>
                <c:if test="${sheet.status==41}" >
                    <button id="print" class="btnfbs"  style="display: block"> 打印</button>
                </c:if>
            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                单据编号：<label id="code">${sheet.code}</label>
                制单部门：<label>${sheet.depName}</label>
                制单人：<label>${sheet.personName}</label>
                制单时间：<label >${sheet.createDateStr}</label>
            </span>
            <input id="menuCode" value="${menuCode}"  type="hidden"/>
            <input id="depid" name="depid" value=""  type="hidden"/>
            <input id="userid" name="userid" value=""  type="hidden"/>
            <input id="taskId" name="taskId" value="${taskId}"   type="hidden"/>
            <input type="hidden" class="layui-input " id="reloadDetailsgrid" />
            <input id="id" name="id" value="${sheet.id}"  type="hidden"/>
        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">物资领料单</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " id="bill" action="">
                    <table width="100%">
                        <tr>
                            <%--<td class="bill-td">--%>
                                <%--<div class="layui-form-item">--%>
                                    <%--<label class="layui-form-label"><span style="color: red">*</span>上级审批部门：</label>--%>
                                    <%--<div class="layui-input-block" style="position:relative;">--%>
                                        <%--<input type="hidden" name="officesId" value="${sheet.officesId}" class="layui-input" id="officesId">--%>
                                        <%--<input type="text" name="officesName" value="${sheet.officeName}"  class="layui-input" id="officesName">--%>
                                        <%--<a href="javascript:void(0);" class="layui-btn bill-button"--%>
                                           <%--id="openOffice">...</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</td>--%>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>申请单位：</label>
                                    <div class="layui-input-block" id="applyDepartId">
                                        <select name="applyDepartId" textfield="NAME" valuefield="ID" lay-search>
                                            <option value="">请选择...</option>
                                            <c:forEach items="${applyDeps}" var="applyDep" varStatus="a">
                                                <option value="${applyDep.id}" <c:if test="${sheet.applyDepartId==applyDep.id}"> selected </c:if> >${applyDep.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">中心/部门：</label>
                                    <div class="layui-input-block">
                                        <input type="hidden" name="usedDepartId" id="usedDepartId" class="layui-input layui-disabled" disabled value="${sheet.usedDepartId}">
                                        <input type="text" name="useUnitName" id="useUnitName"
                                            class="layui-input layui-disabled" disabled value="${sheet.useUnitName}">
                                        <a href="javascript:void(0);" class="layui-btn bill-button layui-disabled"
                                           disabled>...</a>
                                    </div>
                                </div>
                            </td>
                            <td style="width: 360px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库存组织：</label>
                                    <div class="layui-input-block">
                                        <input id="extendString2" name="extendString2" class="layui-input "
                                               value="${sheet.extendString2}   "/>
                                        <input id="ztId" name="ztId" value="${sheet.ztId}" class="layui-input layui-disabled" disabled
                                               style="display:none;"/>
                                        <a href="javascript:void(0);" class="layui-btn bill-button "  id="openZt">
                                            ...
                                        </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>资金来源：</label>
                                    <div class="layui-input-block" id="fundsSource">
                                        <select name="fundsSource" extfield="NAME" valuefield="ID">
                                            <option value="">${sheet.fundsSource}请选择...</option>
                                            <c:forEach items="${fundsSources}" var="fundsSource" varStatus="f">
                                                <option value="${fundsSource.id}" <c:if test="${sheet.fundsSource == fundsSource.id }"> selected </c:if>>${fundsSource.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">用途：</label>
                                    <div class="layui-input-block">
                                        <input id="extendString1" name="extendString1" value="${sheet.extendString1}" class="layui-input"/>
                                    </div>
                                </div>
                            </td>
                            <td style="width: 360px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备注：</label>
                                    <div class="layui-input-block">
                                        <input id="memo" name="memo" value="${sheet.memo}" class="layui-input"/>
                                    </div>
                                </div>
                            </td>
                            <td></td>
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
                                    <button class="btnfbs"  style="width: 100px!important;" id="addPlanDetail">
                                        新增计划明细
                                    </button>
                                    <button class="btnfbs" style="width: 100px!important;" id="addNoPlanDetail">
                                        新增无计划明细
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

</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/apply/apply.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>

<script type="text/html" id="bar">
    <c:forEach items="${buttons}" var="curbtn" varStatus="b">

        <c:if test="${curbtn.code=='editDetails'}">
            <a  class="layui-btn layui-btn-xs"  lay-event="editDetails" id="editDetails">编辑</a>
        </c:if>
    </c:forEach>
</script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" >
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
        </c:forEach>
    });
</script>
</html>

