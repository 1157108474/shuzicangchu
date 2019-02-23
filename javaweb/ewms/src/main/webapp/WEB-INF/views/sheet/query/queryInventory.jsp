<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>���ʿ���ѯ</title>
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
                    <table>
                        <tr>
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
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���ϱ��룺</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="materialcode" id="materialcode">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">����������</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="description" id="description">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���Ϸ��ࣺ</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="flname" id="flname">
                                    </div>
                                </div>
                            </td>
                            <%--<td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��Ӧ�̣�</label>
                                    <div class="layui-input-block" style="position:relative;width: 300px !important;">
                                        <input type="text" class="layui-input" placeholder="" maxlength="16"
                                               id="providerName">
                                        <button class="layui-btn bill-button" id="providerBtn">...</button>
                                        <input id="providerId" name="providerIdStr" type="hidden" class="mini-input"/>
                                    </div>
                                </div>
                            </td>--%>
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
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��棺</label>
                                    <div class="layui-input-block">
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input" name="startarea" id="startarea">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">��</div>
                                        <div style="float: left;width: 80px">
                                            <input type="text" class="layui-input" name="endarea" id="endarea">
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƿ���ۣ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select name="ownertype" id="ownertype">
                                            <option></option>
                                            <option value="609">��</option>
                                            <option value="610">��</option>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item" >
                                    <button class="layui-btn layui-btn-warm bill-td-button" lay-submit
                                          style="margin-left: 20px" lay-filter="formSubmit">��ѯ
                                    </button>
                                    <button type="reset" id="reset" class="layui-btn bill-td-button"
                                            style="margin-left: 5px !important;">����
                                    </button>
                                    <a id="export" class="layui-btn bill-td-button"
                                       style="margin-left: 5px !important;">����</a>
                                    <button type="reset" id="print" class="layui-btn bill-td-button"
                                            style="margin-left: 5px !important;">��ǩ��ӡ
                                    </button>
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
            <h2 class="layui-colla-title">�����б�</h2>
            <div class="layui-colla-content layui-show">
                <table id="querygrid" lay-filter="querygrid"></table>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="bar">
    <a lay-event="show" class="layui-btn layui-btn-xs" onclick="showSheet('{{d.id}}','{{d.storelocationcode}}')">�鿴</a>
</script>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/query/queryInventory.js"></script>
</html>

