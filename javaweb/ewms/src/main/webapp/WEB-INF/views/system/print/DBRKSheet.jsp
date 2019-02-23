<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>��ӡ�˻���</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>

<div class="admin-main" style="commargin-left: 10px;">
    <input id="num" type="hidden">
    <div style="margin: 10px">
        <div class="noPrint" style="float:right;">
            <a href="javascript:;" class="layui-btn  layui-btn-small" id="print"
               style="height: 30px;line-height: 30px;margin-bottom: 1px">��ӡ</a>&nbsp;
        </div>
        <input id="id" value="${sheet.id}" type="hidden">
        <div>
            <div><span style="display:block;text-align:center">�й���ú��Դ�ɷ����޹�˾�ɹ�����</span></div>
            <div><span style="display:block;text-align:center">���ʹ�Ӧ�������ʵ�����ⵥ</span></div>
            <div style="display:block;">
                <table width="100%">
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                �����֯�� ${sheet.drOrg}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                ������λ�� ${sheet.dcOrg}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                �ֿ⣺${sheet.houseCode}
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                No��${sheet.rkCode}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                ${sheet.createDateStr}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                �������ţ�${sheet.dbCode}
                            </div>
                        </td>
                    </tr>
                </table>
            </div>


            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1">
                    <thead>
                    <tr>
                        <th>��λ</th>
                        <th>���ϱ���</th>
                        <th>����</th>
                        <th>���</th>
                        <th>��λ</th>
                        <th>��������</th>
                        <th>���ۣ�Ԫ��</th>
                        <th>��Ԫ��</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${details}" var="detail">
                    <tr>
                        <td>${detail.storeLocationCode }</td>
                        <td>${detail.materialCode}</td>
                        <td>${detail.description}</td>
                        <td>${detail.materialBrand}</td>
                        <td>${detail.detailUnitName}</td>
                        <td>${detail.subDetailCount}</td>
                        <td>${detail.notaxPrice}</td>
                        <td>${detail.notaxSum}</td>
                    </tr>
                    </tbody>
                </table>
                </c:forEach>
            </div>
            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1">
                    <thead>
                    <tr>
                        <th>�ϼ�</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>${sum1}</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div style="display:block;">
                <table width="100%">
                    <tr>
                        <td>
                            <div class="layui-form-item">���ʹ�Ӧվվ����</div>
                        </td>
                        <td>
                            <div class="layui-form-item">����Ա:${sheet.rkPerson}
                            </div>
                        </td>

                    </tr>
                </table>
            </div>

        </div>
        <iframe id="printf" src="" width="0" height="0" frameborder="0"></iframe>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/print/DBRKPrint.js"></script>

</html>
