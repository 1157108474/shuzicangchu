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

    <div id="llSheetHead" class="layui-collapse">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">��ѯ����</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                <input type="hidden" id="taskId" name="taskId" value="${taskId}">
                    <table>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�ɹ�������</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <%--<input type="text" class="layui-input" name="ordernum" id="orderNum" disabled>
                                        <button class="layui-btn bill-button" id="order"
                                                style="margin-left: 28px !important;">...
                                        </button>--%>
                                        <input type="text" class="layui-input" name="ordernum" id="orderNum">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�����֯��</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select id="ztid" name="ztid">
                                            <option value="">��ѡ��...</option>
                                            <c:forEach items="${departList}" var="depart" varStatus="d">
                                                <option value="${depart.id}">${depart.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƶ��ˣ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="creatorName" disabled>
                                        <input type="text" class="layui-hide" name="creator" id="creator">
                                        <button class="layui-btn bill-button" id="creatorBtn"
                                                style="margin-left: 28px !important;">...
                                        </button>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���ݱ�ţ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" name="code">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��������:</label>
                                    <div class="layui-input-block ">
                                        <div class="layui-col-xs5" >
                                            <input type="text" class="layui-input" id="startTime" name="begin">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">��</div>
                                        <div class="layui-col-xs5">
                                            <input type="text" class="layui-input" id="endTime" name="end"
                                            >
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">����״̬��</label>
                                    <div class="layui-input-block">
                                        <select name="status" id="status">
                                            <option value="">��ѡ��...</option>
                                            <c:forEach items="${orderStatus}" var="d">
                                                <option value="${d.id}">${d.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��Ӧ�̣�</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="providerName" class="layui-input" disabled>
                                        <input type="text" id="providerId" name="providerdepid" class="layui-hide">
                                        <button class="layui-btn bill-button" id="provider"
                                                style="margin-left: 28px !important;">...
                                        </button>
                                    </div>
                                </div>
                            </td>

                            <td style="width: 100px;">
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit
                                        lay-filter="formSubmit"
                                        style="margin-left: 36px !important;">��ѯ
                                </button>
                                <button type="reset" class="layui-btn bill-td-button"
                                        style="margin-left: 5px !important;">����
                                </button>
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
                <table id="ordergrid" lay-filter="ordergrid"></table>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="bar">
    {{# if( d.statusname == '�Ƶ���' && ${userId} == d.creator ) { }}
    <a lay-event="edit" class="layui-btn  layui-btn-xs">�༭</a>
    <a lay-event="delete" class="layui-btn layui-btn-danger layui-btn-xs">ɾ��</a>
    {{# }else{ }}

    <a lay-event="show" class="layui-btn  layui-btn-xs">�鿴</a>
    {{#} }}

</script>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/order/manageOrder.js"></script>
</html>

