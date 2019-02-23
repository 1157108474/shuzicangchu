<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�����������쵥</title>
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
                    <table>
                        <tr>
                            <td style="width: 300px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�����֯��</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" id="extendString2" value="${sheet.extendString2}"
                                               class="layui-input layui-disabled" disabled>
                                        <input type="hidden" id="ztId" value="${sheet.ztId}">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƶ��ˣ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input layui-disabled"
                                               value="${sheet.personName}" disabled>
                                        <input type="hidden" class="layui-input layui-disabled" id="creator"
                                               value="${sheet.creator}" disabled>
                                        <button class="layui-btn bill-button layui-disabled" disabled>...</button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���ݱ�ţ�</label>
                                    <div class="layui-input-block">
                                        <input id="code" name="code" type="text" class="layui-input">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�������ͣ�</label>
                                    <div class="layui-input-block" id="typeId">
                                        <select>
                                            <option value="0">��ѡ��...</option>
                                            <c:forEach items="${cktypes}" var="cktype" varStatus="d">
                                                <option value="${cktype.id}">${cktype.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 300px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƶ�����:</label>
                                    <div class="layui-input-block">
                                        <div style="float: left;width: 85px!important;">
                                            <input type="text" class="layui-input beginDate" id="beginDate"
                                                   placeholder="yyyy-MM-dd">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">��</div>
                                        <div style="float: left;width: 85px!important;">
                                            <input type="text" class="layui-input endDate" id="endDate"
                                                   placeholder="yyyy-MM-dd">
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">����״̬��</label>
                                    <div class="layui-input-block" id="status">
                                        <select>
                                            <option value="0">��ѡ��...</option>
                                            <c:forEach items="${receiptTypes}" var="receiptType" varStatus="r">
                                                <option value="${receiptType.id}">${receiptType.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƿ���³ɱ���</label>
                                    <div class="layui-input-block" id="extendint3">
                                        <select >
                                            <option value="">��ѡ��</option>
                                            <option value="0">δ����</option>
                                            <option value="1">���³ɹ�</option>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td style="width: 210px">
                                <a style="margin-left: 30px" href="javascript:void(0);"
                                   class="layui-btn layui-btn-warm bill-td-button" lay-submit
                                   lay-filter="formSubmit">��ѯ</a>
                                <button class="layui-btn bill-td-button" type="reset">����</button>
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
<script type="text/javascript" src="/js/sheet/ck/manageSheetCK.js"></script>
<script type="text/html" id="bar">
    {{# if( (d.extendint3 == null||d.extendint3 == 0) && d.statusName == '�����') { }}
    <a lay-event="renewalCost" class="layui-btn layui-btn-xs">���³ɱ�</a>
    {{# } }}
    {{# if( d.statusName == '�����' || d.statusName == '�����' ) { }}
    <a lay-event="show" class="layui-btn layui-btn-xs">�鿴</a>
    {{# } }}
    {{# if( d.statusName == '�Ƶ���' ) { }}
    <a lay-event="edit" class="layui-btn layui-btn-xs">�༭</a>
    <a lay-event="delete" class="layui-btn layui-btn-danger layui-btn-xs">ɾ��</a>
    {{# } }}

</script>
</html>

