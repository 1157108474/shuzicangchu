<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>打印物资入库单</title>
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
                <td colspan="11">内蒙古中煤远兴能源化工有限公司</td>
            </tr>
            <tr class="center_tr">
                <td colspan="11">物 资 入 库 单</td>
            </tr>
            <tr class="center_tr">
                <td colspan="11">${sheet.createDateStr}</td>
            </tr>
            <tr>
                <td colspan="4">使用单位：${sheet.extendstring2}</td>
                <td colspan="7">供货单位：${sheet.extendstring1}</td>
            </tr>
            <tr>
                <td colspan="4">采购订单号：${sheet.ordernum}</td>
                <td colspan="7">接收单号：${sheet.receivenum}</td>
            </tr>
            <tr class="my_tr center_tr">
                <td>序号</td>
                <td>物资编码</td>
                <td>物资名称</td>
                <td>规格型号</td>
                <td>仓库</td>
                <td>单位</td>
                <td>数量</td>
                <td>单价</td>
                <td>金额</td>
                <td>含税金额</td>
                <td>备注</td>
            </tr>
            <c:forEach items="${details}" var="detail" varStatus="d">
                <tr class="my_tr center_tr">
                    <td>${d.index+1}</td>
                    <td>${detail.materialCode}</td>
                    <td>${detail.materialName}</td>
                    <td>${detail.description}</td>
                    <td>${detail.houseName}</td>
                    <td>${detail.detailUnitName}</td>
                    <td>${detail.detailCount}</td>
                    <td>${detail.notaxPriceDouble}</td>
                    <td class="taxSum">${detail.notaxSumDouble}</td>
                    <td class="notaxSum">${detail.taxSumDouble}</td>
                    <td></td>
                </tr>
            </c:forEach>
            <tr class="my_tr center_tr">
                <td colspan="2">合计</td>
                <td colspan="6"></td>
                <td id="taxSumCount"></td>
                <td id="notaxSumCount"></td>
                <td></td>
            </tr>
            <tr class="my_tr">
                <td colspan="13">部门负责人：${depName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;采购主管：${cgzgName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;仓储主管：${cczgName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;采购员：${cgyName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入库经办人：${fqrName}</td>
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
    var taxSumStr = document.getElementsByClassName("taxSum");
    var notaxSumStr = document.getElementsByClassName("notaxSum");
    var taxSumCountId = document.getElementById("taxSumCount");
    var notaxSumCountId = document.getElementById("notaxSumCount");
    var taxSumCount = 0;
    $.each(taxSumStr, function (i, nav) {
        taxSumCount += parseFloat(nav.innerHTML);
    });
    taxSumCountId.innerHTML = taxSumCount;
    var notaxSumCoun = 0;
    $.each(notaxSumStr, function (i, nav) {
        notaxSumCoun += parseFloat(nav.innerHTML);
    });
    notaxSumCountId.innerHTML = notaxSumCoun;
</script>
</html>
