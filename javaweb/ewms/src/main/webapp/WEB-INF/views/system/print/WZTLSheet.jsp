<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>打印物资退料单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <style type="text/css">
        .center_tr td {
            text-align: center;
        }
        .my_tr td {
            border: solid #000 1px;
        }
    </style>
</head>
<body>
<div class="admin-main" style="margin-left: 10px;margin-right: 10px;margin-top: 20px">
    <input id="num" type="hidden">
    <div class="noPrint">
        <a href="javascript:;" class="layui-btn  layui-btn-small" id="print"
           style="height: 30px;line-height: 30px;float:right; margin-right: 2px; margin-top: 2px">打印</a>
    </div>
    <div>
        <input id="id" value="${sheet.id}" type="hidden">
        <table width="100%">
            <tr class="center_tr">
                <td colspan="3">内蒙古中煤远兴能源化工有限公司</td>
            </tr>
            <tr class="center_tr">
                <td colspan="3">退 料 单</td>
            </tr>
            <tr class="my_tr">
                <td>退料单位：</td>
                <td>接收库房：</td>
                <td>日期：${sheet.createDateStr}</td>
            </tr>
            <tr class="my_tr">
                <td>单据编号：</td>
                <td>领用部门：</td>
                <td></td>
            </tr>
        </table>
        <br>
        <table width="100%">
            <tr class="my_tr center_tr">
                <td>物资编码</td>
                <td>物资名称</td>
                <td>规格型号</td>
                <td>单位</td>
                <td>请退数量</td>
                <td>实退数量</td>
                <td>单价</td>
                <td>金额</td>
                <td>货位</td>
                <td>备注</td>
            </tr>
            <c:forEach items="${details}" var="detail" varStatus="d">
                <tr class="my_tr ">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td class="detailCount"></td>
                    <td></td>
                    <td></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <table width="100%">
            <tr class="my_tr ">
                <td>&nbsp;&nbsp;退料人：${sheet.createDateStr}</td>
            </tr>
        </table>
        <iframe id="printf" src="" width="0" height="0" frameborder="0"></iframe>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/print/JSPrint.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
    var detailCountStr = document.getElementsByClassName("detailCount");
    var countStr = document.getElementById("count");
    var count = 0;
    $.each(detailCountStr, function (i, nav) {
        count += parseFloat(nav.innerHTML);
    });
    countStr.innerHTML = count;
</script>
</html>
