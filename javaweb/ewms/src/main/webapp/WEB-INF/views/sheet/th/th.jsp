<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增退货单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>

</head>
<body>
<div id="formbody" class="formbody">

    <div id="km_toolbar" class="mini-toolbar" style="height:20px;">
        <div style="float:left">
            <div style="float:left;margin-left:15px;">
                <button id="saveSheet" class="btnfbs"  > 保存</button>
                <button id="submit" class="btnfbs" > 提交</button>
                <button id="review" class="btnfbs"> 审核</button>
                <button id="reject" class="btnfbs"  > 驳回</button>
                 <c:if test="${sheet.status==41}" >
                <span >
                    <%--打印类型： <select id="printType" lay-filter="printType" name="printType">
                          <option value=""></option>
                              <c:forEach items="${printTypes}" var="printType" varStatus="t">
                                  <option value="${printType.key}">${printType.value}</option>
                              </c:forEach>
                     </select>--%>
                      <button id="doprint" class="btnfbs" style="display: inline"> 打印</button>
                </span>
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
            <input id="id" value="${sheet.id}"  type="hidden"/>
            <input id="menuCode" value="${menuCode}"  type="hidden"/>
            <input class="layui-input " value="${taskId}" name="taskId" id="taskId"  type="hidden"/>
            <input id="reloadDetailsgrid" value="" type="hidden"/>
            <input id="userId" value="${userId}"  type="hidden"/>
        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">退货单</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table width="100%">
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>入库单号：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text"  class="layui-input" id="rkCode" value="${sheet.extendString5}" name="extendString5" disabled>

                                        <button class="layui-btn bill-button" id="rkBtn"
                                                <c:if test="${sheet.id!=null}">disabled</c:if>>...
                                        </button>
                                        <input class="layui-input" name="extendInt1" value="${sheet.extendInt1}" id="extendInt1" type="hidden">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">采购订单编号：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  name="orderNum" value="${sheet.orderNum}" class="layui-input" disabled id="orderNum"/>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">发放号：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  name="extendString6" value="${sheet.extendString6}"  class="layui-input" disabled id="extendString6"/>
                                    </div>
                                </div>
                            </td>


                        </tr>
                        <tr>

                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库存组织：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  name="extendString2"  value="${sheet.extendString2}" class="layui-input" disabled id="extendString2"/>
                                        <input  name="ztId" value="${sheet.ztId}" type="hidden" disabled id="ztId"/>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">供应商：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  name="extendString1" value="${sheet.extendString1}" class="layui-input" disabled id="extendString1"/>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">业务实体：</label>
                                    <div class="layui-input-block">
                                        <input  name="extendString4"  class="layui-input" value="内蒙古中煤远兴能源化工有限公司" disabled/>
                                    </div>
                                </div>
                            </td>


                        </tr>
                        <t>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">订单类型：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  name="extendString3" value="${sheet.extendString3}" class="layui-input" disabled id="extendString3"/>
                                    </div>
                                </div>
                            </td>
                            <td></td><td></td>
                        </t>

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
<div style="display: none" id="showSub">
    <div style="margin-left: -10px;" >
        <table id="subdetailsgrid" lay-filter="subdetailsgrid"></table>
    </div>
</div>
<div style="display: none" id="showhis">
    <div style="margin-left: -10px;" >
        <table id="hisdetailsgrid" lay-filter="hisdetailsgrid"></table>
    </div>
</div>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/th/th.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>

<script type="text/javascript" >
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
        </c:forEach>
    });
</script>
<script type="text/html" id="barDemo" >
    <a lay-event="show" style="color:#1E9FFF">查看</a>
</script>
<script type="text/html" id="subbarDemo" >
    <a lay-event="showHistory" class="layui-btn  layui-btn-xs">查看</a>
</script>
</html>

