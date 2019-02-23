<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增物资接收单</title>
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
                <button id="submit" class="btnfbs"> 提交</button>
                <button id="review" class="btnfbs"> 审核</button>
                <button id="reject" class="btnfbs" style="display:none"> 驳回</button>
                <%--<button id="doprint" class="btnfbs" style="display: inline"> 打印</button>--%>
            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                <c:choose>
                    <%--判断是否是查看或编辑状态,如果是,遍历单据信息--%>
                    <c:when test="${empty sheet}">
                        单据编号：<label id="sheetcode"></label>
                        制单部门：<label id="dep">${departName}</label>
                        制单人：<label id="creator">${userName}</label>
                        <input type="hidden" id="userZtid" value="${ztId}">
                        <input type="hidden" id="loginUserId" value="${loginUserId}"/>
                        制单时间：<label id="createdate">${createDate}</label>
                    </c:when>
                    <c:otherwise>
                        单据编号：<label id="sheetcode">${sheet.code}</label>
                        制单部门：<label id="dep">${sheet.depname}</label>
                        制单人：<label id="creator">${sheet.personname}</label>
                        <input type="hidden" id="userZtid" value="${sheet.ztid}"/>
                        <input type="hidden" id="loginUserId" value="${loginUserId}"/>
                        <input type="hidden" id="taskUserId" value="${taskUserId}"/>
                        制单时间：<label id="createdate">${sheet.createDateStr}</label>
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">物资入库单</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table id="rkTable">
                        <input type="hidden" name="typeId" value="910">
                        <input type="hidden" id="id" name="id" value="${sheet.id}">
                        <input type="hidden" id="taskId" name="taskId" value="${taskId}">
                        <input type="hidden" id="reloadStatus">
                        <input type="hidden" id="sheetStatus" value="${sheet.status}">
                        <input type="hidden" id="menuCode" name="menuCode" value="${menuCode}">

                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">业务实体：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled
                                               id="extendString4" name="extendString4" value="内蒙古中煤远兴能源化工有限公司"
                                               style="width:163px;!important;">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">供应商：</label>
                                    <div class="layui-input-block">
                                        <c:choose>
                                            <c:when test="${not empty sheet}">
                                                <input type="text" name="extendString1" id="providerName"
                                                       value="${sheet.extendstring1}"
                                                       class="layui-input layui-disabled" disabled/>
                                                <input type="hidden" name="providerDepId" id="providerId"
                                                       class="layui-input layui-disabled"
                                                       value="${sheet.providerdepid}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="text" name="extendString1" id="providerName"
                                                       class="layui-input" disabled/>
                                                <button class="layui-btn bill-button" id="provider"
                                                        style="margin-left: 28px !important;">...
                                                </button>
                                                <input type="hidden" name="providerDepId" id="providerId"
                                                       class="layui-input layui-disabled"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </td>
                            <td style="width:320px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><font color="red">* </font>库存组织：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="ownerdepName" name="extendString2"
                                               value="${sheet.extendstring2}" disabled>
                                        <button class="layui-btn bill-button" id="ownerdepBtn">...</button>
                                        <input class="layui-input" id="ownerdepId" name="ztId" type="hidden"
                                               value="${sheet.ztid}">
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
                                    <a href="javascript:void(0);" class="btnfbs" style="width: 80px!important;display:block" id="importKCResult" > 导入库存量 </a>
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
        {{# if($("#sheetStatus").val() == 39 && (d.creator == $("#loginUserId").val() || d.creator == $("#taskUserId").val())){ }}
        <a lay-event="edit" style="color:#1E9FFF">编辑</a>
        {{# } }}
    </script>

</div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/zr/zr.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>
<script type="text/javascript">
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
        </c:forEach>
    });
</script>
</html>

