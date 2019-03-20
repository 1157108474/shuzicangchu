<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>��ӡ������ⵥ</title>
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
           style="height: 30px;line-height: 30px;float:right; margin-right: 2px; margin-top: 2px">��ӡ</a>
    </div>
    <div>
        <input id="id" value="${sheet.id}" type="hidden">
        <table width="100%">
            <tr class="center_tr">
                <td colspan="11">���ɹ���úԶ����Դ�������޹�˾</td>
            </tr>
            <tr class="center_tr">
                <td colspan="11">�� �� �� �� ��</td>
            </tr>
            <tr class="center_tr">
                <td colspan="11">${sheet.createDateStr}</td>
            </tr>
            <tr>
                <td colspan="4">ʹ�õ�λ��${sheet.extendstring2}</td>
                <td colspan="7">������λ��${sheet.extendstring1}</td>
            </tr>
            <tr>
                <td colspan="4">�ɹ������ţ�${sheet.ordernum}</td>
                <td colspan="7">���յ��ţ�${sheet.receivenum}</td>
            </tr>
            <tr class="my_tr center_tr">
                <td>���</td>
                <td>���ʱ���</td>
                <td>��������</td>
                <td>����ͺ�</td>
                <td>�ֿ�</td>
                <td>��λ</td>
                <td>����</td>
                <td>����</td>
                <td>���</td>
                <td>��˰���</td>
                <td>��ע</td>
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
                <td colspan="2">�ϼ�</td>
                <td colspan="6"></td>
                <td id="taxSumCount"></td>
                <td id="notaxSumCount"></td>
                <td></td>
            </tr>
            <tr class="my_tr">
                <td colspan="13">���Ÿ����ˣ�${depName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ɹ����ܣ�${cgzgName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ִ����ܣ�${cczgName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ɹ�Ա��${cgyName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��⾭���ˣ�${fqrName}</td>
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
