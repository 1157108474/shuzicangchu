<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>��ӡԭ������</title>
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
                <td colspan="14">���ɹ���úԶ����Դ�������޹�˾��ԭ�����ϣ�����������յ�</td>
            </tr>
            <tr class="center_tr">
                <td colspan="14">����ʱ��: ${sheet.createDateStr}</td>
            </tr>
            <tr>
                <td colspan="4">��Ӧ�̵�λ��${sheet.extendString1}</td>
                <td colspan="6">������ţ�${sheet.orderNum}</td>
                <td colspan="3">���յ��ţ�${sheet.code}</td>
            </tr>
            <tr class="my_tr center_tr">
                <td rowspan="2">���</td>
                <td rowspan="2">��������</td>
                <td colspan="4">����������ͺ����ͬԼ��һ��������</td>
                <td colspan="4">����Ҫ�����������</td>
                <td colspan="4">�����������</td>
            </tr>
            <tr class="my_tr center_tr">
                <td>����ͺ�</td>
                <td>��λ</td>
                <td>����</td>
                <td>����Աǩ��ȷ��</td>
                <td>��Ҫָ��/����</td>
                <td>����ԭ���ϼ��鱨��</td>
                <td>���ս���</td>
                <td>������ǩ��ȷ��</td>
                <td>һ��һǩ</td>
                <td>����/��������</td>
                <td>�������鱨��/�ϸ�֤</td>
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
                    <td>${detail.hanliang}</td>
                    <td>${detail.inpre}</td>
                    <td>${detail.jielun1}</td>
                    <td>${shenpi1}</td>
                    <td>${detail.ysyq}</td>
                    <td>${detail.outpi}</td>
                    <td>${detail.outpre}</td>
                    <td>${zuihou}</td>
                </tr>
            </c:forEach>
            <tr class="my_tr">
                <td colspan="2">��ע��</td>
                <td colspan="12"></td>
            </tr>
            <tr class="my_tr">
                <td colspan="14">ע��һʽ��������һ��Ϊ�������ˣ���ɫ�����ڶ���Ϊ������⣨��ɫ����������Ϊ���ܣ���ɫ����</td>
            </tr>
        </table>
        <input id="inp" type="hidden" value="${shenpi1 }">
        <iframe id="printf" src="" width="0" height="0" frameborder="0"></iframe>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/print/JSPrint.js"></script>
<script type="text/javascript">
	$(function(){
		if($("#inp").val()==null){
			
		}
	})
</script>
</html>
