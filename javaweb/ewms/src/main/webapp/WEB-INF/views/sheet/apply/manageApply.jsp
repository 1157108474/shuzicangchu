<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�������쵥����</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>

</head>
<body>
<div id="formbody" class="formbody">

    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">��ѯ����</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table   >
                        <tr>
                            <td style="width: 300px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�����֯��</label>
                                    <div class="layui-input-block " id="ztId"  >
                                        <select >
                                            <option value="">��ѡ��...</option>
                                            <c:forEach items="${departs}" var="depart" varStatus="d">
                                                <option value="${depart.id}">${depart.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƶ��ˣ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input id="workManId"  class="layui-input layui-hide">
                                        <input id="workManName"  type="text" class="layui-input">
                                        <a href="javascript:void(0);" class="layui-btn bill-button " id="openWorkMan">...</a>
                                    </div>
                                </div>
                            </td>
                            <td  style="width: 300px;">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���ݱ�ţ�</label>
                                    <div class="layui-input-block">
                                        <input id="code" name="code" type="text" class="layui-input">
                                    </div>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td  style="width: 300px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�������ڣ�</label>
                                    <div class="layui-input-block">
                                        <div style="float: left;width: 85px!important;">
                                            <input type="text"  class="layui-input beginDate" id="beginDate" placeholder="yyyy-MM-dd">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">��</div>
                                        <div style="float: left;width: 85px!important;">
                                            <input type="text"  class="layui-input endDate" id="endDate" placeholder="yyyy-MM-dd">
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <%--<label class="layui-form-label">ʹ�õ�λ��</label>
                                    <div class="layui-input-block">
                                        <input type="hidden" name="usedDepartId" id="usedDepartId" class="layui-input layui-disabled" disabled value="${sheet.usedDepartId}">
                                        <input type="text" name="useUnitName" id="useUnitName" class="layui-input layui-disabled" disabled value="${sheet.useUnitName}">
                                        <a href="javascript:void(0);" class="layui-btn bill-button layui-disabled"
                                           disabled>...</a>
                                    </div>--%>
                                    <label class="layui-form-label">ʹ�õ�λ��</label>
                                    <div class="layui-input-block">
                                        <select id="usedDepartId">
                                            <option value="">��ѡ��...</option>
                                            <c:forEach items="${useDeps}" var="useDep" varStatus="a">
                                                <option value="${useDep.id}">${useDep.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <button style="margin-left: 30px"  class="layui-btn layui-btn-warm bill-td-button" lay-submit lay-filter="formSubmit">��ѯ</button>
                            </td>


                        </tr>
                        <tr>
                            <td style="width: 300px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">����״̬��</label>
                                    <div class="layui-input-block" id="status">
                                        <select>
                                            <option value="0">��ѡ��...</option>
                                            <c:forEach items="${dictionaries}" var="dictionarie" varStatus="d">
                                                <option value="${dictionarie.id}">${dictionarie.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                                <td class="bill-td">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">���뵥λ��</label>
                                        <div class="layui-input-block">
                                            <select id="applyDepartId">
                                                <option value="">��ѡ��...</option>
                                                <c:forEach items="${applyDeps}" var="applyDep" varStatus="a">
                                                    <option value="${applyDep.id}">${applyDep.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                <%--<div class="layui-form-item">
                                    <label class="layui-form-label">�ϼ��������ţ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="hidden" value="${sheet.officesId}" class="layui-input" id="officesId">
                                        <input type="text" value="${sheet.officeName}"  class="layui-input" id="officesName">
                                        <a href="javascript:void(0);" class="layui-btn bill-button"
                                           id="openOffice">...</a>
                                    </div>
                                </div>--%>
                            </td>
                            <td style="width: 300px;">
                                <button style="margin-left: 30px"    class="layui-btn bill-td-button" type="reset">����</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <div class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">�����б�</h2>
            <div class="layui-colla-content layui-show">
                <table id="llsheetgrid" lay-filter="llsheetgrid"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/apply/manageApply.js"></script>
<script type="text/html" id="bar">
    {{# if( d.statusName == '�����' || d.statusName == '�����' ) { }}

    <a lay-event="show"  class="layui-btn layui-btn-xs" >�鿴</a>
    {{# } }}

    {{# if( d.statusName == '�Ƶ���' ) { }}
    <a  lay-event="edit"  class="layui-btn layui-btn-xs" >�༭</a>
    <a  lay-event="delete"  class="layui-btn layui-btn-danger layui-btn-xs" >ɾ��</a>

    {{# } }}

</script>
</html>

