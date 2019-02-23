<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增物资出库单</title>
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
                <button id="split" class="btnfbs" >拆单</button>
                <button id="review" class="btnfbs"> 审核</button>
                <button id="reject" class="btnfbs" > 驳回</button>
                <c:if test="${sheet.status==41&&sheet.extendint3 == 1}" >
                    <button id="print" class="btnfbs"  style="display: block"> 打印</button>
                </c:if>
            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                单据编号：<label id="code">${sheet.code}</label>
                制单部门：<label>${sheet.orgName}</label>
                制单人：<label>${sheet.personName}</label>
                制单时间：<label >${sheet.createDateStr}</label>
            </span>

            <input id="menuCode" value="${menuCode}"  type="hidden"/>
            <input id="taskId" name="taskId" value="${taskId}"   type="hidden"/>
            <input id="id" name="id" value="${id}"   type="hidden"/>
            <input type="hidden" class="layui-input " id="reloadDetailsgrid" />
        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">物资出库单</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " id="form_content" action="">
                    <table>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>出库类型：</label>
                                    <div class="layui-input-block" id="ckType">
                                        <select lay-filter="ckType" id ="type">
                                            <option name="ckType" value="">请选择...</option>
                                            <c:forEach items="${ckTypes}" var="cktype" varStatus="d">
                                                <option value="${cktype.id}" <c:if test="${sheet.typeName==cktype.name}"> selected </c:if>  >${cktype.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">申请单号：</label>
                                    <div class="layui-input-block">
                                        <input type="hidden" class="layui-input layui-disabled" disabled name="applyId" id="applyId"  value="${sheet.extendint1}" >
                                        <input type="text" class="layui-input layui-disabled" disabled name="applyCode" value="${sheet.slCode}"
                                               id="applyCode">
                                        <a href="javascript:void(0);" class="layui-btn bill-button layui-disabled" disabled id="openApply">
                                            ...
                                        </a>
                                    </div>
                                </div>
                            </td>

                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库存组织：</label>
                                    <div class="layui-input-block">
                                        <input id="extendString2" name="extendString2" value="${sheet.extendString2}" class="layui-input layui-disabled" disabled/>
                                        <input id="ztId" name="ztId"  value="${sheet.ztId}" class="layui-input layui-disabled" disabled style="display:none;"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">调拨单号：</label>
                                    <div class="layui-input-block">
                                        <input type="hidden" class="layui-input layui-disabled" disabled
                                               value="${sheet.extendint2}" id="extendInt2" >
                                        <input type="text" class="layui-input layui-disabled" disabled id="dbCode" value="${sheet.dbCode}">
                                        <a href="javascript:void(0);" class="layui-btn bill-button layui-disabled " disabled id="openDb">...
                                        </a>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">使用单位：</label>
                                    <div class="layui-input-block">
                                        <input type="hidden" class="layui-input layui-disabled" name="usedDepartId" disabled id="usedDepartId" value="${sheet.useddepartid}">
                                        <input type="text" class="layui-input layui-disabled" name="useDepName" disabled id="useDepName" value="${sheet.useDepName}">
                                        <a href="javascript:void(0);" class="layui-btn bill-button layui-disabled " disabled id="openUseDep">
                                            ...
                                        </a>
                                    </div>
                                </div>
                            </td>
                            <%--<td class="bill-td">--%>
                                <%--<div class="layui-form-item">--%>
                                    <%--<label class="layui-form-label">上级审批部门：</label>--%>
                                    <%--<div class="layui-input-block">--%>
                                        <%--<input type="hidden" class="layui-input layui-disabled" disabled--%>
                                               <%--value="${sheet.officesId}" id="officesId">--%>
                                        <%--<input type="text" class="layui-input layui-disabled" disabled--%>
                                               <%--value="${sheet.departOfficename}" id="departOfficename">--%>
                                        <%--<a href="javascript:void(0);" class="layui-btn bill-button layui-disabled" disabled id="openOffice">--%>
                                            <%--...--%>
                                        <%--</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</td>--%>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">申请单位：</label>
                                    <div class="layui-input-block" id="applyDepId">
                                        <select class=" layui-disabled" disabled id="applyDep" lay-search>
                                            <option value="">请选择...</option>
                                            <c:forEach items="${applyDeps}" var="applyDep" varStatus="a">
                                                <option value="${applyDep.id}" <c:if test="${sheet.applydepartid==applyDep.id}"> selected </c:if> >${applyDep.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">资金来源：</label>
                                    <div class="layui-input-block " id="fundSources">
                                        <select class=" layui-disabled" disabled id="fundSourcesChange" lay-search>
                                            <option value="">请选择...</option>
                                            <c:forEach items="${fundSources}" var="fundSource" varStatus="f">
                                                <option value="${fundSource.id}"  <c:if test="${sheet.fundsSource==fundSource.id}"> selected </c:if> >${fundSource.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">用&nbsp;&nbsp;&nbsp;&nbsp;途：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled id="extendstring1" value="${sheet.extendString1}" >
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled id="memo" value="${sheet.memo}">
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
                                    <button class="btnfbs" style="width: 80px!important;" id="addDetails">
                                        添加明细
                                    </button>
                                    <button class="btnfbs" style="width: 80px!important;" id="deleteDetails">
                                        删除明细
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
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/ck/sheetCK.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>

<script type="text/javascript" >
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
        </c:forEach>
    });
</script>
</html>

