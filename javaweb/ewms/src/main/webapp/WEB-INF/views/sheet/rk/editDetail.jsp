<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�༭��ϸ</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>
<div class="admin-main">
    <div class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">�����ϸ</h2>
            <div class="mini-toolbar" style="height:20px;">
                <div style="float:left">
                    <div style="float:left"></div>
                    <div style="float:left;margin-left:20px;">
                        <button class="btnfbs" style="display:inline !important;" id="addSon">���</button>
                    </div>
                </div>
            </div>
            <div class="layui-colla-content layui-show">
                <div>
                    <div style="display: none">
                        <input type="hidden" id="rid" value="${storeValue.id}">
                        <input type="hidden" id="ztId" value="${storeValue.ztId}">
                        <input type="hidden" id="newlocationName">
                        <input type="hidden" id="newlocationCode">
                        <input type="hidden" id="newlocationId">
                        <input type="hidden" id="storeId" value="${storeValue.storeID}">
                        <input type="hidden" id="extendInt1" value="${extendInt1}">
                    </div>
                    <table id="detailList" class="layui-table" lay-filter="detailList" lay-data="{id:'detailList'}">
                        <thead>
                        <tr>
                            <th lay-data="{type:'checkbox', fixed: 'left',field:'id', align:'center'}"></th>
                            <th lay-data="{field:'extendString1',fixed: true, width:80,  align:'center'}">�ⷿ</th>
                            <th lay-data="{field:'fpkw', fixed: true, align:'center',width:120,event:'location'}">��λ
                            </th>
                            <th lay-data="{field:'fpsl', fixed: true, align:'center',width:120,edit:'text'}">��������</th>
                            <th lay-data="{field:'xlh', fixed: true, align:'center',width:120,edit:'text'}">���к�</th>
                            <th lay-data="{field:'kfpsl', width:100,  align:'center'}">�ɷ�������</th>
                            <th lay-data="{field:'materialCode', align:'center',width:120}">���ϱ���</th>
                            <th lay-data="{field:'detailUnitName', width:50, align:'center',width:70}">��λ</th>
                            <th lay-data="{field:'description', align:'center',width:150}">����˵��</th>
                            <th lay-data="{field:'detailCount',width:80,align:'center',width:90}">�������</th>
                            <th lay-data="{field:'jsType', width:80,align:'center',width:95}">��������</th>
                            <th lay-data="{field:'isEquipment', width:80,  align:'center',width:95}">�Ƿ��豸</th>
                            <th lay-data="{field:'enableSn', width:100, align:'center'}">�������к�</th>
                            <th lay-data="{field:'yskw', width:120,  align:'center'}">ԭʼ��λ</th>
                        </tr>

                        </thead>
                        <tbody>
                        <tr>
                            <td>${storeValue.id}</td>
                            <td>${storeValue.warehouseName}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>${storeValue.kfpsl}</td>
                            <td>${storeValue.materialCode}</td>
                            <td>${storeValue.detailUnitName}</td>
                            <td>${storeValue.description}</td>
                            <td>${storeValue.detailCount}</td>
                            <td>${storeValue.jsType}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${storeValue.isEquipment == '1'}">
                                        ��
                                    </c:when>
                                    <c:otherwise>
                                        ��
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${storeValue.enableSn == '1'}">
                                        ����
                                    </c:when>
                                    <c:otherwise>
                                        ������
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${storeValue.yskw}</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <br>
    <%--��λ��Ϣ--%>
    <%--<div class="layui-collapse" lay-filter="test" lay-data="id:test">--%>
    <%--<div class="layui-colla-item">--%>
    <%--<h2 class="layui-colla-title">��λ��Ϣ</h2>--%>
    <%--<div class="layui-colla-content layui-show">--%>
    <%--<div class="mini-toolbar" style="height:20px;">--%>
    <%--<div style="float:left">--%>
    <%--<div style="float:left"></div>--%>
    <%--<div style="float:left;margin-left:20px;">--%>
    <%--<button class="btnfbs" style="display:inline !important;">���</button>--%>

    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<table id="storeDetail" lay-filter="storeDetail">--%>
    <%--</table>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--�������ϸ--%>
    <br>
    <div class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">�������ϸ</h2>
            <div class="layui-colla-content layui-show">
                <div>
                    <table id="subDetail" lay-filter="subDetail">
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>

<script type="text/html" id="subBar">
    <a lay-event="delete" style="color:#1E9FFF">ɾ��</a>

</script>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="../../js/sheet/rk/editDetail.js"></script>

</html>