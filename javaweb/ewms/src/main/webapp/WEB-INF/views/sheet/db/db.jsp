<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增调拨单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>

</head>
<body>
<div id="formbody" class="formbody">

    <div id="km_toolbar" class="mini-toolbar" style="height:20px;">
        <div style="float:left">
            <div style="float:left"></div>
            <div style="float:left;margin-left:15px;">
                <button id="saveSheet" class="btnfbs"  > 保存</button>
                <button id="submit" class="btnfbs" > 提交</button>
                <button id="review" class="btnfbs"> 审核</button>
                <button id="reject" class="btnfbs"  > 驳回</button>
                <button id="print" class="btnfbs" > 打印</button>
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
            <input class="layui-input " value="${taskId}" name="taskId" id="taskId"  type="hidden"/>
            <input id="reloadDetailsgrid" value="" type="hidden"/>
            <input id="userZtId" value="${userZtId}"  type="hidden"/>

        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">调拨单</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table width="100%">
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label" width="95px" ><a style="color:red;">*</a>调出库存组织:</label>
                                    <div class="layui-input-block" style="position:relative;">

                                        <select id="cOrg" lay-filter="cOrg"  name="extendInt1" <c:if test="${sheet.id!=null}"> disabled="true"  value="${sheet.extendInt1}"</c:if>>
                                            <option value=""></option>
                                            <c:forEach items="${orgs}" var="org" varStatus="d">
                                                <option value="${org.id}" <c:if test="${org.id== sheet.extendInt1}">selected</c:if> >${org.name}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>
                            </td>
                            <td width="33%">
                                <div class="layui-form-item">
                                    <label class="layui-form-label" width="95px" ><a style="color:red;">*</a>调入库存组织:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select id="rOrg" lay-filter="rOrg"  name="extendInt2" <c:if test="${sheet.id!=null}">disabled</c:if>>
                                            <option value=""></option>
                                            <c:forEach items="${orgs}" var="org" varStatus="d">
                                            <option value="${org.id}" <c:if test="${org.id== sheet.extendInt2}">selected</c:if>  >${org.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">调拨原因:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  name="memo" id="memo" value="${sheet.memo}" class="layui-input" />
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
                                    <button class="btnfbs" style="width: 80px!important;" id="addDetails"> 添加明细 </button>
                                    <button class="btnfbs" style="width: 80px!important;" id="deleteDetails"> 删除明细 </button>
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
<script type="text/javascript" src="/js/sheet/db/db.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>
<script type="text/javascript" >
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
        </c:forEach>
    });
</script>
</html>

