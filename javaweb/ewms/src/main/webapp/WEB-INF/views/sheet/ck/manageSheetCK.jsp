<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增物资申领单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>

</head>
<body>
<div id="formbody" class="formbody">

    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">查询条件</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table>
                        <tr>
                            <td style="width: 300px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库存组织：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" id="extendString2" value="${sheet.extendString2}"
                                               class="layui-input layui-disabled" disabled>
                                        <input type="hidden" id="ztId" value="${sheet.ztId}">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">制单人：</label>
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
                                    <label class="layui-form-label">单据编号：</label>
                                    <div class="layui-input-block">
                                        <input id="code" name="code" type="text" class="layui-input">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">出库类型：</label>
                                    <div class="layui-input-block" id="typeId">
                                        <select>
                                            <option value="0">请选择...</option>
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
                                    <label class="layui-form-label">制单日期:</label>
                                    <div class="layui-input-block">
                                        <div style="float: left;width: 85px!important;">
                                            <input type="text" class="layui-input beginDate" id="beginDate"
                                                   placeholder="yyyy-MM-dd">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">至</div>
                                        <div style="float: left;width: 85px!important;">
                                            <input type="text" class="layui-input endDate" id="endDate"
                                                   placeholder="yyyy-MM-dd">
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据状态：</label>
                                    <div class="layui-input-block" id="status">
                                        <select>
                                            <option value="0">请选择...</option>
                                            <c:forEach items="${receiptTypes}" var="receiptType" varStatus="r">
                                                <option value="${receiptType.id}">${receiptType.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">是否更新成本：</label>
                                    <div class="layui-input-block" id="extendint3">
                                        <select >
                                            <option value="">请选择</option>
                                            <option value="0">未更新</option>
                                            <option value="1">更新成功</option>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td style="width: 210px">
                                <a style="margin-left: 30px" href="javascript:void(0);"
                                   class="layui-btn layui-btn-warm bill-td-button" lay-submit
                                   lay-filter="formSubmit">查询</a>
                                <button class="layui-btn bill-td-button" type="reset">重置</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <div class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">数据列表</h2>
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
    {{# if( (d.extendint3 == null||d.extendint3 == 0) && d.statusName == '已完成') { }}
    <a lay-event="renewalCost" class="layui-btn layui-btn-xs">更新成本</a>
    {{# } }}
    {{# if( d.statusName == '已完成' || d.statusName == '审核中' ) { }}
    <a lay-event="show" class="layui-btn layui-btn-xs">查看</a>
    {{# } }}
    {{# if( d.statusName == '制单中' ) { }}
    <a lay-event="edit" class="layui-btn layui-btn-xs">编辑</a>
    <a lay-event="delete" class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
    {{# } }}

</script>
</html>

