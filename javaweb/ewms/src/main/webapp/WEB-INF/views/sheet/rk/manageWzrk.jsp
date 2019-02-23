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
                    <table   >
                        <tr>
                            <td >
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
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƶ��ˣ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" id="creatorName" class="layui-input" disabled>
                                        <input type="text" id="creator" name="creator" class="layui-hide">
                                        <button class="layui-btn bill-button" id="creatorBtn">...</button>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�ɹ�������ţ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="ordernum" name="ordernum">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���ݱ��:</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="code" name="code">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">����״̬��</label>
                                    <div class="layui-input-block">
                                        <select  name="status" id="status">
                                            <option value="">��ѡ��...</option>
                                            <c:forEach items="${orderStatus}" var="d" >
                                                <option value="${d.id}">${d.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">ERP���յ��ţ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" id="extendstring6" name="extendstring6">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">������ͣ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select id="check" lay-filter="isopen" name="typeid">
                                            <option value="">��ѡ��...</option>
                                            <c:forEach items="${rkType}" var="r">
                                                <option value="${r.id}">${r.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���յ��ţ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" id="receivenum" name="receivenum">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƶ����ڣ�</label>
                                    <div class="layui-input-block">
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input startTime" id="startTime" name="begin"
                                            >
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">��</div>
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input endTime" id="endTime" name="end"
                                            >
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit lay-filter="formSubmit"
                                        >��ѯ</button>
                                <button type="reset" class="layui-btn bill-td-button" >����</button>
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
                <table id="rkgrid" lay-filter="rkgrid"></table>
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
<script type="text/javascript" src="/js/sheet/rk/manageWzrk.js"></script>
</html>

