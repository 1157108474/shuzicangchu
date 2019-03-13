<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title id="title">新增物资接收单</title>
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
                <button id="saveSheet" class="btnfbs" style=""> 保存</button>
                <button id="submit" class="btnfbs"> 提交</button>
                <button id="review" class="btnfbs"> 审核</button>
                <button id="reject" class="btnfbs" style="display:none"> 驳回</button>
                <span >
                    打印类型： <select id="printType" lay-filter="printType" name="printType">
                          <option value=""></option>
                              <c:forEach items="${printTypes}" var="printType" varStatus="t">
                                  <option value="${printType.key}">${printType.value}</option>
                              </c:forEach>
                     </select>
                </span>
                <button id="print" class="btnfbs" style="display: inline"> 打印</button>
            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                    <c:choose>
                        <%--判断是否是查看或编辑状态,如果是,遍历单据信息--%>
                        <c:when test="${not empty sheetValue}">
                            单据编号：<label id="sheetcode">${sheetValue.code}</label>
                            制单部门：<label id="dep">${sheetValue.depname}</label>
                            制单人：<label id="creator">${sheetValue.personname}</label>
                            <input type="hidden" id="userId" value="${sheetValue.creator}"/>
                            制单时间：<label id="createdate">${sheetValue.createDateStr}</label>
                        </c:when>
                        <c:otherwise>
                            <%--如果当前为新增状态,默认加载当前登录人信息--%>
                            单据编号：<label id="sheetcode"></label>
                            制单部门：<label id="dep">${departName}</label>
                            制单人：<label id="creator">${userName}</label>
                            制单时间：<label id="createdate">${createDate}</label>
                        </c:otherwise>
                    </c:choose>

            </span>

        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">物资接收单</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table id="orderTable">
                        <tr>
                            <td style="width: 360px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>采购订单：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="hidden" id="reloadStatus">
                                        <input type="hidden" id="sheetStatus" value="${sheetValue.status}">
                                        <input type="hidden" id="taskId" name="taskId" value="${taskId}">
                                        <input type="hidden" id="activityName" name="activityName" value="${activityName}">
                                        <input type="hidden" id="shenpi" name="shenpi" value="${shenpi}">
                                        <input type="hidden" id="menuCode" name="menuCode" value="${menuCode}">
                                        <input type="text" class="layui-input" id="ordernum" name="orderNum"
                                               value="${sheetValue.ordernum}" disabled>
                                        <input type="hidden" class="layui-input" id="orderId" name="extendInt1"
                                               value="${sheetValue.extendint1}">
                                        <input type="hidden" id="id" name="id" value="${sheetValue.id}">
                                        <input type="hidden" id="extendString10" name="extendString10" value="${sheetValue.extendString10}">
                                        <c:if test="${empty sheetValue.id}">
                                            <button class="layui-btn bill-button" id="order"
                                                    style="margin-left: 68px !important;">...</button>
                                        </c:if>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">发放号：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled
                                               id="issuecode" name="extendString5" value="${sheetValue.extendstring5}">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">订单类型：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled
                                               id="ordertype" name="extendString3" value="${sheetValue.extendstring3}">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                            <div class="layui-form-item">
                            	 <label class="layui-form-label">物资类型：</label>
                            	 <div class="layui-input-block">
                            	 <span id="sp">
				                     <select  id="extendInt6" lay-filter="extendInt6" name="extendInt6">
				                          <option value=""></option>
				                              <c:forEach items="${printTypes}" var="printType" varStatus="t">
				                                  <option value="${printType.key}">${printType.value}</option>
				                              </c:forEach>
				                     </select>
				                     </span>
				                     <span id="sp1">
				                     <input type="hidden"  class="layui-input" id="dytype" 
                                               value="${sheetValue.extendint6}">
				                     </span>
				                      <span id="sp2">
				                      <input type="text"  class="layui-input" id="dytypeName" 
                                               value="" disabled>
				                      </span>
				                      
				                     </div>
				             </div>
				             </td>
                        </tr>
                        <tr>
                            <td style="width: 360px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">业务实体：</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled
                                               id="extendString4" name="extendString4" value="内蒙古中煤远兴能源化工有限公司"
                                               value="${sheetValue.extendstring4}">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">供应商：</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="providerdepname" name="extendString1"
                                               value="${sheetValue.extendstring1}"
                                               class="layui-input layui-disabled" disabled/>
                                        <input type="hidden" id="providerdepid" name="providerDepId"
                                               value="${sheetValue.providerdepid}" class="layui-input layui-disabled"
                                               disabled/>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库存组织：</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="stockorgname" name="extendString2"
                                               value="${sheetValue.extendstring2}"
                                               class="layui-input layui-disabled" disabled/>
                                        <input type="hidden" id="stockorgid" name="ztId"
                                               value="${sheetValue.ztid}" class="layui-input layui-disabled" disabled/>
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
                        <li>单据附件</li>
                        <li>操作履历</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 389px!important; ">
                        <!--明细列表-->
                        <div class="layui-tab-item layui-show">
                            <div class="mini-toolbar" style="height:20px;margin-left: -5px;">
                                <div style="float:left">
                                    <button class="btnfbs" style="width: 100px!important;" id="addDetails"> 新增明细
                                    </button>
                                    <button class="btnfbs" style="width: 80px!important;" id="deleteDetails"> 删除明细
                                    </button>
                                    <button id="labelPrint" class="btnfbs"
                                            style="display: inline;width:80px!important;"> 标签打印
                                    </button>
                                    
                                    <%--<button class="btnfbs" id="addPlanOther"
                                            style="width:80px!important;">填写计划
                                    </button>--%>
                                </div>
                            </div>
                            <div style="margin-left: -5px;">
                                <table id="detailsgrid" lay-filter="detailsgrid"></table>
                            </div>
                        </div>
                        <!--单据附件-->
                        <div class="layui-tab-item">
                            <div class="mini-toolbar" style="height:20px;margin-left: -5px;">
                                <div style="float:left">
                                    <button class="btnfbs" style="width: 100px!important;" id="importfiles"> 新增附件
                                    </button>
                                </div>
                            </div>
                            <div style="margin-left: -5px;">
                                <table id="filegrid" lay-filter="filegrid"></table>
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
        <a lay-event="showLog" style="color:#1E9FFF">查看</a>
    </script>

    <script type="text/html" id="filebar">
        <a href="{{ d.filePath }}" download="{{ d.fileName }}" target="_blank" style="color:#1E9FFF">下载</a>
        {{# if($("#sheetStatus").val() == 39 ) { }}
        <a href="javascript:void(0)" onclick="deleteFile( '{{d.id}}','{{ d.filePath }}' )" style="color:#1E9FFF">删除</a>
        {{# } }}
    </script>

</div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/order/order.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>
<script type="text/javascript">
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
        </c:forEach>
    });
</script>
</html>

