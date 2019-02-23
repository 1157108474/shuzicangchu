<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>��ӡ���յ�</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>

<div class="admin-main" style="commargin-left: 10px;margin-left: 10px">
    <input id="num" type="hidden">
    <div style="margin: 10px">
        <div class="noPrint" style="float:right;">
            <a href="javascript:;" class="layui-btn  layui-btn-small" id="print"
               style="height: 30px;line-height: 30px;margin-bottom: 1px">��ӡ</a>&nbsp;
        </div>
        <input id="id" value="${sheet.id}" type="hidden">
        <div>
            <div><span style="display:block;text-align:center">�й���ú��Դ�ɷ����޹�˾�ɹ�����</span></div>
            <div><span style="display:block;text-align:center">���������ʵ������յ�</span></div>
            <div style="display:block;">
                <table width="100%">
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                ������λ�� ${sheet.extendString2}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                ${sheet.createDateStr}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                �������յ��ţ�${sheet.code}
                            </div>
                        </td>
                    </tr>
                </table>
            </div>


            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1">
                    <thead>
                    <tr>
                        <th>���ʱ���</th>
                        <th>����˵��</th>
                        <th>������λ</th>
                        <th>��ͬ����</th>
                        <th>ʵ�ʵ�������</th>
                        <th>��������Ʒ��</th>
                        <th>��������</th>
                        <th>�������</th>
                        <th>�����������������ռ�¼</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${details}" var="detail">
                    <tr>
                        <td>${detail.materialCode}</td>
                        <td>${detail.description}</td>
                        <td>${detail.detailUnitName}</td>
                        <td></td>
                        <td>${detail.detailCount}</td>
                        <td>${detail.materialBrand}</td>
                        <td></td>
                        <td>${detail.snCode}</td>
                        <td></td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <br>
            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1" style="text-align: center">
                    <thead>
                    <tr>
                        <th>������λ</th>
                        <th>${sheet.extendString1}</th>
                        <th>�ɹ���ʽ</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>��ŵص�</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>ͼ������������</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>

                    </tr>
                    </thead>
                </table>
            </div>
            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1">
                    <thead>
                    <tr>
                        <th>��ͬ���</th>
                        <th>${sheet.contractNum}</th>
                        <th>��������</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>��������</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1">
                    <thead>
                    <tr>
                        <th>�������</th>
                        <th>��˾���ܲ���</th>
                        <th>��ֹ�ְ�ܲ��Ż�ʹ�ò���</th>
                        <th>������ʼ�</th>
                        <th>��Ӧվ</th>
                        <th>��Ӧ��</th>
                    </tr>
                    </thead>
                    <tbody>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    </tbody>
                </table>
            </div>
            <div style="display:block;">
                <table width="100%">
                    <tr>
                        <td>
                            <div class="layui-form-item">���Ա��${sheet.personName}</div>
                        </td>
                        <td>
                            <div class="layui-form-item">�ɹ�Ա:${userName}
                            </div>
                        </td>
                    </tr>
                   <%-- <tr>
                        <td>
                            <div class="layui-form-item">�ƻ����ţ�</div>
                        </td>
                        <td>
                            <div class="layui-form-item">�ƻ���˲��ţ�
                            </div>
                        </td>
                    </tr>--%>
                </table>
            </div>

        </div>
        <iframe id="printf" src="" width="0" height="0" frameborder="0"></iframe>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/print/JSPrint.js"></script>

</html>
