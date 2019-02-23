<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�����ӳ���</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>

</head>
<body>
<div id="formbody" class="formbody">

    <div id="km_toolbar" class="mini-toolbar" style="height:20px;">
        <div style="float:left">
            <div style="float:left;margin-left:15px;">
                <button id="saveSheet" class="btnfbs"  > ����</button>
                <button id="submit" class="btnfbs" > �ύ</button>
                <button id="review" class="btnfbs"> ���</button>
                <button id="reject" class="btnfbs"  > ����</button>
                <%--<button id="doprint" class="btnfbs" style="display: inline"> ��ӡ</button>--%>
                <!-- <c:if test="${sheet.status==41}" >
                <span >
                    ��ӡ���ͣ� <select id="printType" lay-filter="printType" name="printType">
                          <option value=""></option>
                              <c:forEach items="${printTypes}" var="printType" varStatus="t">
                                  <option value="${printType.id}">${printType.reportName}</option>
                              </c:forEach>
                     </select>
                      <button id="doprint" class="btnfbs" style="display: inline"> ��ӡ</button>
                </span>
                 </c:if>-->

            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                ���ݱ�ţ�<label id="code">${sheet.code}</label>
                �Ƶ����ţ�<label>${sheet.depName}</label>
                �Ƶ��ˣ�<label>${sheet.personName}</label>
                �Ƶ�ʱ�䣺<label >${sheet.createDateStr}</label>
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
            <h2 class="layui-colla-title">�ӳ���</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table width="100%">
                        <tr>

                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>�����֯��</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" id="ownerdepName" value="${sheet.extendString2}">
                                        <button class="layui-btn bill-button" id="ownerdepBtn">...</button>
                                        <input class="layui-input" id="ownerdepId" type="hidden" value="${sheet.ztId}">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��Ӧ�̣�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" id="providerName" value="${sheet.extendString1}">
                                        <button class="layui-btn bill-button" id="providerBtn">...</button>
                                        <input id="providerId" name="providerIdStr" type="hidden" class="layui-input" value="${sheet.providerDepId}"/>

                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">ҵ��ʵ�壺</label>
                                    <div class="layui-input-block">
                                        <input  name="extendString4" id="extendString4"  class="layui-input" value="���ɹ���úԶ����Դ�������޹�˾" disabled/>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��ע��</label>
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
            <h2 class="layui-colla-title">��ϸ�б�</h2>
            <div class="layui-colla-content layui-show">
                <div class="layui-tab">
                    <ul class="layui-tab-title">
                        <li class="layui-this">��ϸ�б�</li>
                        <li>��������</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 289px!important; ">
                        <!--��ϸ�б�-->
                        <div class="layui-tab-item layui-show">
                            <div class="mini-toolbar" style="height:20px;margin-left: -5px;">
                                <div style="float:left">
                                    <button class="btnfbs" style="width: 80px!important;" id="addDetails"> ������ϸ </button>
                                    <button class="btnfbs" style="width: 80px!important;" id="deleteDetails"> ɾ����ϸ </button>
                                </div>
                            </div>
                            <div style="margin-left: -5px;">
                                <table id="detailsgrid" lay-filter="detailsgrid"></table>
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
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
            <c:if test="${curbtn.code=='editDetails'}">
                <a lay-event="edit" class="layui-btn layui-btn-xs">�༭</a>
            </c:if>
        </c:forEach>
    </script>
</div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/zc/zc.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>
<script type="text/javascript" >
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
        </c:forEach>
    });
</script>
</html>

