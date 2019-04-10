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
                <button id="saveSheet" class="btnfbs" style="display:none"> 保存</button>
                <button id="submit" class="btnfbs" style="display:none"> 提交</button>
                <button id="review" class="btnfbs" style="display:none"> 审核</button>
                <button id="reject" class="btnfbs"  style="display:none"> 驳回</button>
                <c:if test="${sheet.status==41}" >
                    <button id="print" class="btnfbs"  style="display: block"> 打印</button>
                </c:if>
            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                单据编号：<label id="code">${sheet.code}</label>
                制单部门：<label>${sheet.ztidName}</label>
                制单人：    <label>${sheet.personName}</label>
                制单时间：<label >${sheet.createDateStr}</label>
            </span>
            <input id="id" value="${sheet.id}"  type="hidden"/>
            <input id="menuCode" value="${menuCode}"  type="hidden"/>
            <input class="layui-input " value="${taskId}" name="taskId" id="taskId"  type="hidden"/>
            <input id="reloadDetailsgrid" value="" type="hidden"/>
            <input id="commitProcedures" value="SHEETYW_COMMIT" type="hidden"/>
            <!--  <input id="userId" value="${userId}"  type="hidden"/>-->
            <input id="ztId" value="${ztId}"  type="hidden"/>

        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">物资退库单</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table width="100%" >
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>退库类型：</label>
                                    <div class="layui-input-block">
                                        <!--
                                          <c:if test="${sheet.id!= null }">
                                            <select  disabled="true"  value="sheet.typeId">
                                                <option value=""></option>
                                                <c:forEach items="${tkTypes}" var="tkType" varStatus="t">
                                                    <option value="${tkType.id}">${tkType.name}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${sheet.id == null && fg ==1 }">  <!--TODO库管员只能够进行直接退库、普通用户只能够进行申请退库--
                                        <input type="text"  class="layui-input layui-disabled" value="直接退库"   disabled>
                                        </c:if>
                                        <c:if test="${sheet.id == null && fg !=1 }">  <!--库管员只能够进行直接退库、普通用户只能够进行申请退库--
                                            <input type="text"  class="layui-input layui-disabled" value="申请退库"   disabled>
                                        </c:if>
                                        -->
                                        <input type="text"  class="layui-input layui-disabled" value="直接退库"   disabled>

                                    </div>
                                </div>

                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>出库单号：</label>

                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text"  class="layui-input" id="ckCode"  value="${sheet.ckCode}"  disabled>

                                        <button class="layui-btn bill-button" id="ckBtn"
                                                <c:if test="${sheet.id!=null}">disabled</c:if>>...
                                        </button>
                                        <input class="layui-input" name="extendInt1" value="${sheet.extendInt1}" id="extendInt1" type="hidden">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">使用单位：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled"  value="${sheet.usedDepName}" id="usedDepartName" disabled>
                                        <input type="hidden" value="${sheet.usedDepartId}" id="usedDepartId" disabled>
                                        <!-- <button class="layui-btn bill-button layui-disabled" disabled>...
                                        </button>-->
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">科室：</label>
                                    <div class="layui-input-block">
                                        <input id="officesName" value="${sheet.departOfficeName}"
                                               class="layui-input layui-disabled" disabled/>
                                        <input type="hidden" value="${sheet.officesId}" id="officesId" disabled>

                                    </div>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">资金来源：</label>
                                    <div class="layui-input-block">

                                        <select  id="fundsSource" name="fundsSource" extfield="NAME" valuefield="ID" value="${sheet.fundsSource}" disabled>
                                            <option value="">请选择...</option>
                                            <c:forEach items="${fundsSources}" var="fundsSource" varStatus="f">
                                                <option value="${fundsSource.id}" <c:if test="${sheet.fundsSource == fundsSource.id }"> selected </c:if>>${fundsSource.name}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>
                            </td>
                            <td colspan="2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">用途：</label>
                                    <div class="layui-input-block">

                                        <input id="extendString1" value="${sheet.extendString1}"
                                               class="layui-input " />
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">备注：</label>
                                    <div class="layui-input-block">
                                        <input id="memo" value="${sheet.memo}"
                                               class="layui-input "/>
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
                    <div class="layui-tab-content" >
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
<script type="text/javascript" src="/js/sheet/tk/sheetTK.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>

<script type="text/javascript" >
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
        </c:forEach>
    });
</script>
</html>

