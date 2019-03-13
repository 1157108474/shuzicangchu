<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title id="title">�������ʽ��յ�</title>
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
                <button id="saveSheet" class="btnfbs" style=""> ����</button>
                <button id="submit" class="btnfbs"> �ύ</button>
                <button id="review" class="btnfbs"> ���</button>
                <button id="reject" class="btnfbs" style="display:none"> ����</button>
                <span >
                    ��ӡ���ͣ� <select id="printType" lay-filter="printType" name="printType">
                          <option value=""></option>
                              <c:forEach items="${printTypes}" var="printType" varStatus="t">
                                  <option value="${printType.key}">${printType.value}</option>
                              </c:forEach>
                     </select>
                </span>
                <button id="print" class="btnfbs" style="display: inline"> ��ӡ</button>
            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                    <c:choose>
                        <%--�ж��Ƿ��ǲ鿴��༭״̬,�����,����������Ϣ--%>
                        <c:when test="${not empty sheetValue}">
                            ���ݱ�ţ�<label id="sheetcode">${sheetValue.code}</label>
                            �Ƶ����ţ�<label id="dep">${sheetValue.depname}</label>
                            �Ƶ��ˣ�<label id="creator">${sheetValue.personname}</label>
                            <input type="hidden" id="userId" value="${sheetValue.creator}"/>
                            �Ƶ�ʱ�䣺<label id="createdate">${sheetValue.createDateStr}</label>
                        </c:when>
                        <c:otherwise>
                            <%--�����ǰΪ����״̬,Ĭ�ϼ��ص�ǰ��¼����Ϣ--%>
                            ���ݱ�ţ�<label id="sheetcode"></label>
                            �Ƶ����ţ�<label id="dep">${departName}</label>
                            �Ƶ��ˣ�<label id="creator">${userName}</label>
                            �Ƶ�ʱ�䣺<label id="createdate">${createDate}</label>
                        </c:otherwise>
                    </c:choose>

            </span>

        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">���ʽ��յ�</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table id="orderTable">
                        <tr>
                            <td style="width: 360px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>�ɹ�������</label>
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
                                    <label class="layui-form-label">���źţ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled
                                               id="issuecode" name="extendString5" value="${sheetValue.extendstring5}">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�������ͣ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled
                                               id="ordertype" name="extendString3" value="${sheetValue.extendstring3}">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                            <div class="layui-form-item">
                            	 <label class="layui-form-label">�������ͣ�</label>
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
                                    <label class="layui-form-label">ҵ��ʵ�壺</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled
                                               id="extendString4" name="extendString4" value="���ɹ���úԶ����Դ�������޹�˾"
                                               value="${sheetValue.extendstring4}">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��Ӧ�̣�</label>
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
                                    <label class="layui-form-label">�����֯��</label>
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
            <h2 class="layui-colla-title">��ϸ�б�</h2>
            <div class="layui-colla-content layui-show">
                <div class="layui-tab">
                    <ul class="layui-tab-title">
                        <li class="layui-this">��ϸ�б�</li>
                        <li>���ݸ���</li>
                        <li>��������</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 389px!important; ">
                        <!--��ϸ�б�-->
                        <div class="layui-tab-item layui-show">
                            <div class="mini-toolbar" style="height:20px;margin-left: -5px;">
                                <div style="float:left">
                                    <button class="btnfbs" style="width: 100px!important;" id="addDetails"> ������ϸ
                                    </button>
                                    <button class="btnfbs" style="width: 80px!important;" id="deleteDetails"> ɾ����ϸ
                                    </button>
                                    <button id="labelPrint" class="btnfbs"
                                            style="display: inline;width:80px!important;"> ��ǩ��ӡ
                                    </button>
                                    
                                    <%--<button class="btnfbs" id="addPlanOther"
                                            style="width:80px!important;">��д�ƻ�
                                    </button>--%>
                                </div>
                            </div>
                            <div style="margin-left: -5px;">
                                <table id="detailsgrid" lay-filter="detailsgrid"></table>
                            </div>
                        </div>
                        <!--���ݸ���-->
                        <div class="layui-tab-item">
                            <div class="mini-toolbar" style="height:20px;margin-left: -5px;">
                                <div style="float:left">
                                    <button class="btnfbs" style="width: 100px!important;" id="importfiles"> ��������
                                    </button>
                                </div>
                            </div>
                            <div style="margin-left: -5px;">
                                <table id="filegrid" lay-filter="filegrid"></table>
                            </div>
                        </div>
                        <!--��������-->
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
        <a lay-event="showLog" style="color:#1E9FFF">�鿴</a>
    </script>

    <script type="text/html" id="filebar">
        <a href="{{ d.filePath }}" download="{{ d.fileName }}" target="_blank" style="color:#1E9FFF">����</a>
        {{# if($("#sheetStatus").val() == 39 ) { }}
        <a href="javascript:void(0)" onclick="deleteFile( '{{d.id}}','{{ d.filePath }}' )" style="color:#1E9FFF">ɾ��</a>
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

