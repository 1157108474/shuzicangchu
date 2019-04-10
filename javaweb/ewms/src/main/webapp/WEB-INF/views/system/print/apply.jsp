<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>打印申领明细</title>
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
                <td colspan="15">内蒙古中煤远兴能源化工有限公司</td>
            </tr>
            <tr class="center_tr">
                <td colspan="15">申领明细表</td>
            </tr>
            <tr class="right_tr">
                <td colspan="15">时间：</td>
            </tr>
            <tr class="my_tr center_tr">
                <td >序号</td>
                <td >申领单编号</td>
                <td >出库科目</td>
                <td >中心/部门</td>
                <td >物资编码</td>
                <td >物资名称</td>
                <td >规格型号</td>
                <td >材质</td>
                <td >单位</td>
                <td >申领数量</td>
                <td >实发数量</td>
                <td >用途</td>
                <td >经办人</td>
                <td >审核人</td>
                <td >备注</td>
            </tr>
            
            <c:forEach items="${details}" var="detail" varStatus="d">
                <tr class="my_tr center_tr">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </c:forEach>
            <tr class="my_tr center_tr">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <!-- <tr class="my_tr">
                <td colspan="3">设备主管部门：</td>
                <td colspan="4">审核意见：</td>
                <td colspan="3">签字：</td>
            </tr> -->
            <!-- <tr class="my_tr">
                <td colspan="10">注：一式三联，第一联为财务入账（白色），第二联为物资入库（粉色），第三联为保管（黄色）。</td>
            </tr> -->
        </table>
        <iframe id="printf" src="" width="0" height="0" frameborder="0"></iframe>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/print/JSPrint.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
    /* var detailCountStr = document.getElementsByClassName("detailCount");
    var countStr = document.getElementById("count");
    var count = 0;
    $.each(detailCountStr, function (i, nav) {
        count += parseFloat(nav.innerHTML);
    });
    countStr.innerHTML = count; */
</script>
</html>
