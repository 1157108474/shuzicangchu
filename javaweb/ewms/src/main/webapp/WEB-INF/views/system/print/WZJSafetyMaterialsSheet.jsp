<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>��ӡ��ȫ����</title>
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
                <td colspan="11">���ɹ���úԶ����Դ�������޹�˾����ȫ���ʣ�����������յ�</td>
            </tr>
            <tr class="center_tr">
                <td colspan="11">����ʱ��: ${sheet.createDateStr}</td>
            </tr>
            <tr>
                <td colspan="3">��Ӧ�̵�λ��${sheet.extendString1}</td>
                <td colspan="4">������ţ�${sheet.orderNum}</td>
                <td colspan="3">���յ��ţ�${sheet.code}</td>
            </tr>
            <tr class="my_tr center_tr">
                <td rowspan="2">���</td>
                <td rowspan="2">��������</td>
                <td colspan="4">����������ͺ����ͬԼ��һ��������</td>
                <td colspan="2">����Ҫ�����������</td>
                <td colspan="3">�����������</td>
            </tr>
            <tr class="my_tr center_tr">
                <td>����ͺ�</td>
                <td>��λ</td>
                <td>����</td>
                <td>����Աǩ��ȷ��</td>
                <td>���ս���</td>
                <td>������ǩ��ȷ��</td>
                <td>����/��������</td>
                <td>��֤һ��</td>
                <td>�ɹ�Աǩ��ȷ��</td>
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
                    <td>${detail.outpi }</td>
                    <td>${detail.szyb }</td>
                    <td>${zuihou }</td>
                </tr>
            </c:forEach>
            <!-- <tr class="my_tr">
                <td colspan="3">��ȫ�������ܲ��ţ�</td>
                <td colspan="4">��������</td>
                <td colspan="4">ǩ�֣�</td>
            </tr> -->
            <tr class="my_tr">
                <td colspan="11">ע��һʽ��������һ��Ϊ�������ˣ���ɫ�����ڶ���Ϊ������⣨��ɫ����������Ϊ���ܣ���ɫ����</td>
            </tr>
        </table>
        <iframe id="printf" src="" width="0" height="0" frameborder="0"></iframe>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/print/JSPrint.js"></script>

</html>
