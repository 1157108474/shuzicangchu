<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>打印物资退货单</title>
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
                <td colspan="2">物 资 退 货 单</td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;&nbsp;供货单位：${sheet.extendString1}</td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;采购订单：${sheet.orderNum}</td>
                <td>&nbsp;&nbsp;接收编号：${sheet.jsCode}</td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;&nbsp;币种：CNY</td>
            </tr>
        </table>
        <br>
        <table width="100%">
            <tr class="my_tr center_tr">
                <td>订单行号</td>
                <td>物资编码</td>
                <td>物资名称</td>
                <td>退货日期</td>
                <td>退回自</td>
                <td>规格</td>
                <td>单位</td>
                <td>仓库</td>
                <td>货位</td>
                <td>退货数量</td>
                <td>退货单价</td>
                <td>退货金额</td>
            </tr>
            <c:forEach items="${details}" var="detail">
                <tr class="my_tr center_tr">
                    <td>${detail.erpRownum}</td>
                    <td>${detail.materialCode}</td>
                    <td>${detail.materialName}</td>
                    <td>${detail.createDate}</td>
                    <td>退回到供应商</td>
                    <td>${detail.specifications}</td>
                    <td>${detail.detailUnitName}</td>
                    <td>${detail.houseName}</td>
                    <td>${detail.storeLocationName}</td>
                    <td>${detail.detailCount}</td>
                    <td>${detail.noTaxPriceDouble}</td>
                    <td class="detailCount">${detail.noTaxSumDouble}</td>
                </tr>
            </c:forEach>
            <tr class="my_tr ">
                <td colspan="9">&nbsp;&nbsp;总计：</td>
                <td></td>
                <td></td>
                <td id="count"></td>
            </tr>
            <tr class="my_tr ">
                <td colspan="12">&nbsp;&nbsp;备注：</td>
            </tr>
        </table>
        <br>
        <table width="100%">
            <tr>
                <td>&nbsp;&nbsp;打印日期：${sheet.createDateStr}</td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;退货人：${sheet.personName}</td>
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
