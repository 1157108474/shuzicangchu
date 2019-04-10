<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�������ʳ��ⵥ</title>
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
                <button id="submit" class="btnfbs" style="display:none"> �ύ</button>
                <button id="review" class="btnfbs" style="display:none"> ���</button>
                <button id="reject" class="btnfbs"  style="display:none"> ����</button>
                <c:if test="${sheet.status==41}" >
                    <button id="print" class="btnfbs"  style="display: block"> ��ӡ</button>
                </c:if>
            </div>
        </div>
        <div style="float:right">
            <span>|</span>
            <span style="padding-left:10px;">
                ���ݱ�ţ�<label id="code">${sheet.code}</label>
                �Ƶ����ţ�<label>${sheet.ztidName}</label>
                �Ƶ��ˣ�    <label>${sheet.personName}</label>
                �Ƶ�ʱ�䣺<label >${sheet.createDateStr}</label>
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
            <h2 class="layui-colla-title">�����˿ⵥ</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table width="100%" >
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>�˿����ͣ�</label>
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
                                        <c:if test="${sheet.id == null && fg ==1 }">  <!--TODO���Աֻ�ܹ�����ֱ���˿⡢��ͨ�û�ֻ�ܹ����������˿�--
                                        <input type="text"  class="layui-input layui-disabled" value="ֱ���˿�"   disabled>
                                        </c:if>
                                        <c:if test="${sheet.id == null && fg !=1 }">  <!--���Աֻ�ܹ�����ֱ���˿⡢��ͨ�û�ֻ�ܹ����������˿�--
                                            <input type="text"  class="layui-input layui-disabled" value="�����˿�"   disabled>
                                        </c:if>
                                        -->
                                        <input type="text"  class="layui-input layui-disabled" value="ֱ���˿�"   disabled>

                                    </div>
                                </div>

                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><span style="color: red">*</span>���ⵥ�ţ�</label>

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
                                    <label class="layui-form-label">ʹ�õ�λ��</label>
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
                                    <label class="layui-form-label">���ң�</label>
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
                                    <label class="layui-form-label">�ʽ���Դ��</label>
                                    <div class="layui-input-block">

                                        <select  id="fundsSource" name="fundsSource" extfield="NAME" valuefield="ID" value="${sheet.fundsSource}" disabled>
                                            <option value="">��ѡ��...</option>
                                            <c:forEach items="${fundsSources}" var="fundsSource" varStatus="f">
                                                <option value="${fundsSource.id}" <c:if test="${sheet.fundsSource == fundsSource.id }"> selected </c:if>>${fundsSource.name}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>
                            </td>
                            <td colspan="2">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��;��</label>
                                    <div class="layui-input-block">

                                        <input id="extendString1" value="${sheet.extendString1}"
                                               class="layui-input " />
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��ע��</label>
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
            <h2 class="layui-colla-title">��ϸ�б�</h2>
            <div class="layui-colla-content layui-show">
                <div class="layui-tab">
                    <ul class="layui-tab-title">
                        <li class="layui-this">��ϸ�б�</li>
                        <li>��������</li>
                    </ul>
                    <div class="layui-tab-content" >
                        <!--��ϸ�б�-->
                        <div class="layui-tab-item layui-show">
                            <div class="mini-toolbar" style="height:20px;margin-left: -5px;">
                                <div style="float:left">
                                    <button class="btnfbs" style="width: 80px!important;" id="addDetails">
                                        �����ϸ
                                    </button>
                                    <button class="btnfbs" style="width: 80px!important;" id="deleteDetails">
                                        ɾ����ϸ
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

