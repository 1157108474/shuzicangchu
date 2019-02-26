<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�����˻�������</title>
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
                    <table width="100%">
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�����֯:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select id="ztId" lay-filter="ztId" name="ztId">
                                            <option value=""></option>
                                            <c:forEach items="${departList}" var="depart" varStatus="d">
                                                <option value="${depart.id}">${depart.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item bill-search">
                                    <label class="layui-form-label">�Ƶ���:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" id="creatorName">
                                        <button class="layui-btn bill-button" id="creatorBtn">...</button>
                                        <input class="layui-hide" name="creator" id="creatorId">

                                    </div>

                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item bill-search">
                                    <label class="layui-form-label">���ݱ�ţ�</label>
                                    <div class="layui-input-block">
                                        <input name="code" type="text" class="layui-input">
                                    </div>
                                </div>
                            </td>
                            <td></td>


                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƶ�����:</label>
                                    <div class="layui-input-block">

                                        <input name="begin" id="begin" type="text" class="layui-input"
                                               placeholder="yyyy-MM-dd" style="width:46%;display:inline">
                                        ��
                                        <input name="end" id="end" type="text" class="layui-input"
                                               placeholder="yyyy-MM-dd" style="width:46%;display:inline">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">����״̬:</label>
                                    <div class="layui-input-block">
                                        <select id="status" lay-filter="callSystem" name="status">
                                            <option value=""></option>
                                            <c:forEach items="${statusList}" var="status" varStatus="d">
                                                <option value="${status.id}">${status.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�ɹ�������ţ�</label>
                                    <div class="layui-input-block">
                                        <input id="orderNum" name="usedDepartID" type="text" class="layui-input">

                                    </div>
                                </div>
                            </td>
                            <td style="width: 30px;">

                                <button style="margin-left: 5px" class="layui-btn layui-btn-warm" lay-submit=""
                                        lay-filter="search" id="submitBtn">��ѯ
                                </button>

                            </td>

                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��Ӧ��:</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" id="providerName">
                                        <button class="layui-btn bill-button" id="providerBtn">...</button>
                                        <input class="layui-hide" name="extendString1" id="providerId">

                                    </div>
                                </div>

                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��ⵥ�ţ�</label>
                                    <div class="layui-input-block">
                                        <input name="extendString5" type="text" class="layui-input">

                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">EPR���յ��ţ�</label>
                                    <div class="layui-input-block">
                                        <input name="extendString7" type="text" class="layui-input">

                                    </div>
                                </div>
                            </td>

                            <td style="width: 30px;">
                                <button style="margin-left: 5px" class="layui-btn" type="reset">����</button>
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
                <table id="sheetgrid" lay-filter="sheetgrid"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/th/manageTh.js"></script>
<script type="text/html" id="barDemo">

    {{# if( d.statusName == '�Ƶ���'  && ${userId} == d.creator   ) { }}
    <a lay-event="edit" class="layui-btn  layui-btn-xs">�༭</a>
    <a lay-event="delete" class="layui-btn layui-btn-danger layui-btn-xs">ɾ��</a>
    {{# }else{ }}

    <a lay-event="show" class="layui-btn  layui-btn-xs" ali>�鿴</a>
    {{#} }}

</script>
</html>
