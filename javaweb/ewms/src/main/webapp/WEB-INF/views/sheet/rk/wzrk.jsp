<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>����������ⵥ</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>

</head>
<body>
<div id="formbody" class="formbody">

    <div id="km_toolbar" class="mini-toolbar" style="height:20px;">
        <div style="float:left">
            <div style="float:left"></div>
            <div style="float:left;margin-left:15px;">
                <button id="saveSheet" class="btnfbs" style="display:none"> ����</button>
                <button id="submit" class="btnfbs"> �ύ</button>
                <button id="review" class="btnfbs"> ���</button>
                <button id="reject" class="btnfbs" style="display:none"> ����</button>
                <button id="print" class="btnfbs" style="display: inline"> ��ӡ</button>
                <%--<span id="print" class="btnfbs" style="width: 100px !important;">--%>
                <%--��ӡ���ͣ� <select id="printType" lay-filter="printType" name="printType">--%>
                <%--<option value=""></option>--%>
                <%--<c:forEach items="${printTypes}" var="printType" varStatus="t">--%>
                <%--<option value="${printType.id}">${printType.reportName}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>

                <%----%>
                <%--</span>--%>
            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                <c:choose>
                    <%--�ж��Ƿ��ǲ鿴��༭״̬,�����,����������Ϣ--%>
                    <c:when test="${empty sheet}">
                        ���ݱ�ţ�<label id="sheetcode"></label>
                        �Ƶ����ţ�<label id="dep">${departName}</label>
                        �Ƶ��ˣ�<label id="creator">${userName}</label>
                        <input type="hidden" id="userZtid" value="${ztId}">
                        <input type="hidden" id="loginUserId" value="${loginUserId}"/>
                        �Ƶ�ʱ�䣺<label id="createdate">${createDate}</label>
                    </c:when>
                    <c:otherwise>
                        ���ݱ�ţ�<label id="sheetcode">${sheet.code}</label>
                        �Ƶ����ţ�<label id="dep">${sheet.depname}</label>
                        �Ƶ��ˣ�<label id="creator">${sheet.personname}</label>
                        <input type="hidden" id="userId" value="${sheet.ztid}"/>
                        <input type="hidden" id="loginUserId" value="${loginUserId}"/>
                        <input type="hidden" id="taskUserId" value="${taskUserId}"/>
                        �Ƶ�ʱ�䣺<label id="createdate">${sheet.createDateStr}</label>
                    </c:otherwise>
                </c:choose>
            </span>
        </div>
    </div>
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">������ⵥ</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table id="rkTable">
                        <tr>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>������ͣ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <c:choose>
                                        <c:when test="${empty sheet}">
                                            <select id="check" lay-filter="isopen" name="typeId">
                                            <option value="">��ѡ��...</option>
                                            <c:forEach items="${rkType}" var="r">
                                                <option value="${r.id}">${r.name}</option>
                                            </c:forEach>
                                        </select>
                                        </c:when>
                                        <c:otherwise>
                                        <select id="check" lay-filter="isopen" name="typeId" disabled>
                                            <option value="${sheet.typeid}" selected>${sheet.typename}</option>
                                            </c:otherwise>
                                            </c:choose>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���յ��ţ�</label>
                                    <div class="layui-input-block">
                                        <input type="hidden" id="id" name="id" value="${sheet.id}">
                                        <input type="hidden" id="taskId" name="taskId" value="${taskId}">
                                        <input type="hidden" id="reloadStatus">
                                        <input type="hidden" id="sheetStatus" value="${sheet.status}">
                                        <input type="hidden" id="menuCode" name="menuCode" value="${menuCode}">
                                        <input type="text" class="layui-input layui-disabled" disabled
                                               id="receiveNum" name="receiveNum" value="${sheet.receivenum}">
                                        <input type="hidden" id="extendInt1" name="extendInt1"
                                               value="${sheet.extendint1}">
                                    </div>
                                </div>
                            </td>
                            <td style="width:300px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�ɹ�������ţ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled form-label" disabled
                                               id="orderNum" name="orderNum" value="${sheet.ordernum}">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�������ţ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="dbName" class="layui-input layui-disabled form-label"
                                               style="width:163px;!important;" value="${sheet.dbnum}" disabled>
                                        <input type="hidden" name="extendInt2" id="extendInt2"
                                               value="${sheet.extendint2}">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�������ͣ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="extendString3" name="extendString3"
                                               value="${sheet.extendstring3}"
                                               class="layui-input layui-disabled form-label" disabled/>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">ҵ��ʵ�壺</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled form-label" disabled
                                               id="extendString4" name="extendString4" value="���ɹ���úԶ����Դ�������޹�˾"
                                               >
                                    </div>
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�����֯��</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled
                                               id="extendString2" name="extendString2"
                                               value="${sheet.extendstring2}">
                                        <input type="hidden" id="ztId" name="ztId" value="${sheet.ztid}">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��Ӧ�̣�</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="extendString1" id="extendString1"
                                               value="${sheet.extendstring1}"
                                               class="layui-input layui-disabled" disabled/>
                                        <input type="hidden" name="providerDepId" id="providerDepId"
                                               class="layui-input layui-disabled" value="${sheet.providerdepid}"
                                        />
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���źţ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input layui-disabled" disabled
                                               id="extendString5" name="extendString5"
                                               value="${sheet.extendstring5}">
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
                        <li>��������</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 389px!important; ">
                        <!--��ϸ�б�-->
                        <div class="layui-tab-item layui-show">
                            <div class="mini-toolbar" style="height:20px;margin-left: -5px;">
                                <div style="float:left">
                                    <button class="btnfbs" style="width: 100px!important;" id="addDetails">
                                        ������ϸ
                                    </button>
                                    <button class="btnfbs" style="width: 80px!important;" id="deleteDetails"> ɾ����ϸ
                                    </button>
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
        {{# if($("#sheetStatus").val() == 39 && (d.creator == $("#loginUserId").val() || d.creator == $("#taskUserId").val())){ }}
        <a lay-event="edit" style="color:#1E9FFF">�༭</a>
        {{# } }}
    </script>

</div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/rk/wzrk.js"></script>
<script type="text/javascript" src="/js/system/act/processDetail.js"></script>
<script type="text/javascript">
    $(function () {
        <c:forEach items="${buttons}" var="curbtn" varStatus="b">
        $("#${curbtn.code}").show();
        </c:forEach>
    });
</script>
</html>

