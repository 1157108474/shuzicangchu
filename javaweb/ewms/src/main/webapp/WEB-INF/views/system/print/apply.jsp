<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>��ӡ������ϸ</title>
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
                <td colspan="15">���ɹ���úԶ����Դ�������޹�˾</td>
            </tr>
            <tr class="center_tr">
                <td colspan="15">������ϸ��</td>
            </tr>
            <tr class="right_tr">
                <td colspan="15">ʱ�䣺</td>
            </tr>
            <tr class="my_tr center_tr">
                <td >���</td>
                <td >���쵥���</td>
                <td >�����Ŀ</td>
                <td >����/����</td>
                <td >���ʱ���</td>
                <td >��������</td>
                <td >����ͺ�</td>
                <td >����</td>
                <td >��λ</td>
                <td >��������</td>
                <td >ʵ������</td>
                <td >��;</td>
                <td >������</td>
                <td >�����</td>
                <td >��ע</td>
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
                <td colspan="3">�豸���ܲ��ţ�</td>
                <td colspan="4">��������</td>
                <td colspan="3">ǩ�֣�</td>
            </tr> -->
            <!-- <tr class="my_tr">
                <td colspan="10">ע��һʽ��������һ��Ϊ�������ˣ���ɫ�����ڶ���Ϊ������⣨��ɫ����������Ϊ���ܣ���ɫ����</td>
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
