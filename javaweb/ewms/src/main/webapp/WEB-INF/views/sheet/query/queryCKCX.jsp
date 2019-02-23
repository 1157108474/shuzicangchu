<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>���ʳ����ѯ</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <style type="text/css">
        .form-label {
            width: 180px !important;
        }

        .form-div-input {
            margin-left: 180px !important;
        }
    </style>
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
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���ݱ�ţ�</label>
                                    <div class="layui-input-block form-label" style="position:relative;">
                                        <input  type="text" class="layui-input" name="code" id="code">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���ţ�</label>
                                    <div class="layui-input-block form-label" style="position:relative;">
                                        <select name="ztid" id="ztid">
                                            <option value=""></option>
                                            <c:forEach items="${departList}" var="depart" varStatus="d">
                                                <option value="${depart.id}">${depart.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���쵥λ��</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="hidden" class="layui-input" name="usedDepartId"  id="usedDepartId" >
                                        <button class="layui-btn bill-button" id="openUseDep">...</button>
                                        <input type="text" class="layui-input" name="useDepName"  id="useDepName">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�ⷿ��</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select name="storeid" id="storeid">
                                            <option value=""></option>
                                            <c:forEach items="${wareHouseList}" var="wareHouse" varStatus="w">
                                                <option value="${wareHouse.key}">${wareHouse.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�������ƣ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  type="text" class="layui-input" name="materialname" id="materialname">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���ϱ��룺</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  type="text" class="layui-input" name="materialcode" id="materialcode">
                                    </div>
                                </div>
                            </td>

                            <td >
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit lay-filter="formSubmit"
                                 style="margin-left: 36px !important;">��ѯ</button>
                                <button type="reset" id="reset" class="layui-btn bill-td-button" style="margin-left: 5px !important;">����</button>
                                <a id="export" class="layui-btn bill-td-button" style="margin-left: 5px !important;">����</a>
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
                <table id="querygrid" lay-filter="querygrid"></table>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="bar">
    <a lay-event="show" class="layui-btn layui-btn-xs">�鿴</a>
</script>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/query/queryCKCX.js"></script>
</html>

