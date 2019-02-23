<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>物资盘点单管理</title>
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
                    <table   >
                        <tr>
                            <td style="width: 300px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">库存组织：</label>
                                    <div class="layui-input-block " id="ztId"  >
                                        <select id="extendInt1">
                                            <option value="">请选择...</option>
                                            <c:forEach items="${wareHouses}" var="wh" >
                                                <option value="${wh.id}">${wh.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">制单人：</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input id="workManId" type="hidden" class="layui-input">
                                        <input id="workManName"  type="text" class="layui-input">
                                        <a href="javascript:void(0);" class="layui-btn bill-button " id="openWorkMan">...</a>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据编号：</label>
                                    <div class="layui-input-block">
                                        <input id="code" name="code" type="text" class="layui-input">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td  style="width: 300px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">制单日期:</label>
                                    <div class="layui-input-block">
                                        <div style="float: left;width: 85px">
                                            <input type="text"  class="layui-input beginDate" id="beginDate" placeholder="yyyy-MM-dd">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">至</div>
                                        <div style="float: left;width: 85px">
                                            <input type="text"  class="layui-input endDate" id="endDate" placeholder="yyyy-MM-dd">
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td  style="width: 300px">
                                <div class="layui-form-item"  style="margin-left: 40px">
                                    <button class="layui-btn layui-btn-warm bill-td-button" lay-filter="search" lay-submit>查询</button>
                                    <a href="javascript:void(0);" class="layui-btn bill-td-button " lay-filter="export" id="export">导出</a>
                                    <button class="layui-btn bill-td-button " type="reset" id="reset">重置</button>
                                </div>
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
                <table id="pdsheetgrid" lay-filter="pdsheetgrid"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript" src="/js/dateUtil.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/pd/manageKcpd.js"></script>
<script type="text/html" id="barDemo" >

    {{# if( d.statusName == '制单中' ) { }}
    <a  class="layui-btn  layui-btn-xs" lay-event="edit" >编辑</a>
    <a  class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete" >删除</a>
    <%--<a  lay-event="edit" style="color:#1E9FFF">编辑</a>--%>
    <%--<a  lay-event="delete" style="color:#1E9FFF">删除</a>--%>
    {{# }else{ }}
    <a lay-event="show" class="layui-btn layui-btn-xs">查看</a>
    <%--<a lay-event="show" style="color:#1E9FFF">查看</a>--%>
    {{#} }}

</script>
</html>

