<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <style type="text/css">
        .form-label {
            width: 150px !important;
        }

        .form-div-input {
            margin-left: 150px !important;
        }
    </style>
</head>
<body class="childrenBody" style="width: 90%;height: 90%;margin-left: 10%">
<form class="layui-form layui-form-pane" action="">

    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">质保期限</label>
        <div class="layui-input-block form-div-input">
            <input type="text" id="expirationTime" name="expirationTime" class="layui-input input"
                   value="${sheet.expirationTime}">
            <input type="hidden" id="id" name="id" class="layui-input input" value="${sheet.id}">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">生产日期</label>
        <div class="layui-input-block form-div-input" style="position:relative;">
            <input type="text" id="extendDate2" name="extendDate21" class="layui-input input"
                   value="${sheet.extendDate2}">
            </a>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;width: 90%;">
        <label class="layui-form-label form-label">计划部门</label>
        <div class="layui-input-block  form-div-input">
            <c:choose>
                <c:when test="${empty sheet.planDepartId}">
                    <select id="planDepartId" name="planDepartId">
                        <option value="">请选择...</option>
                        <c:forEach items="${departList}" var="depart" varStatus="d">
                            <option value="${depart.id}">${depart.name}</option>
                        </c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                    <select id="planDepartId" name="planDepartId">
                        <c:forEach items="${departList}" var="depart" varStatus="d">
                            <c:choose>
                                <c:when test="${sheet.planDepartId == depart.id}">
                                    <option value="${depart.id}" selected>${depart.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${sheet.planDepartId}">${sheet.planDepName}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="layui-form-item" style="text-align: center;padding-top: 10px">
        <button class="layui-btn" lay-filter="commitForm" lay-submit><i class="layui-icon">&#xe609;</i>保存</button>
    </div>
</form>


</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/sheet/order/addPlan.js"></script>


</html>