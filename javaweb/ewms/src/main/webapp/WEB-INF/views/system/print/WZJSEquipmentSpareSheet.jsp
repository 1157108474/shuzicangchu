<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>打印设备、备品件及材料类</title>
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
                <td colspan="10">内蒙古中煤远兴能源化工有限公司（设备、备品件及材料类）物资入库验收单</td>
            </tr>
            <tr class="center_tr">
                <td colspan="10">验收时间: ${sheet.createDateStr}</td>
            </tr>
            <tr>
                <td colspan="2">供应商单位：${sheet.extendString1}</td>
                <td colspan="4">订单编号：${sheet.orderNum}</td>
                <td colspan="4">接收单号：${sheet.code}</td>
            </tr>
            <tr class="my_tr center_tr">
                <td rowspan="2">序号</td>
                <td rowspan="2">物资名称</td>
                <td colspan="4">数量及规格型号与合同约定一致性验收</td>
                <td colspan="2">技术要求符合性验收</td>
                <td colspan="2">随机资料验收</td>
            </tr>
            <tr class="my_tr center_tr">
                <td>规格型号</td>
                <td>单位</td>
                <td>数量</td>
                <td>保管员签字确认</td>
                <td>验收结论</td>
                <td>验收人签字确认</td>
                <td>验收结论</td>
                <td>采购员签字确认</td>
            </tr>
            <c:forEach items="${details}" var="detail" varStatus="d">
                <tr class="my_tr center_tr">
                    <td>${d.index+1}</td>
                    <td>${detail.materialName}</td>
                    <td>${detail.description}</td>
                    <td>${detail.detailUnitName}</td>
                    <td>${detail.detailCount}</td>
                    <td>${fqr }</td>
                    <td>${detail.jielun1 }</td>
                    <td>${shenpi1 }</td>
                    <td>${detail.jielun2 }</td>
                    <td>${zuihou }</td>
                </tr>
            </c:forEach>
            <tr class="my_tr">
                <td colspan="3">设备主管部门：${activityName}</td>
                <td colspan="4">审核意见：${comment2 }</td>
                <td colspan="3">签字：${shenpi2 }</td>
            </tr>
            <tr class="my_tr">
                <td colspan="10">注：一式三联，第一联为财务入账（白色），第二联为物资入库（粉色），第三联为保管（黄色）。</td>
            </tr>
        </table>
        <iframe id="printf" src="" width="0" height="0" frameborder="0"></iframe>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/print/JSPrint.js"></script>

</html>
