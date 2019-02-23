<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>${titleDesc}</title>
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
            <div><span style="display:block;text-align:center">${titleDesc}</span></div>
            <div style="display:block;">
                <table width="100%">
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                �����֯�� ${sheet.extendstring2}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                ������λ�� ${sheet.extendstring1}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                �ֿ⣺${houseCode}
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                ������ţ�${sheet.ordernum}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                ${sheet.createDateStr}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                No��${sheet.code}
                            </div>
                        </td>
                    </tr>
                </table>
            </div>


            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1" style="text-align: center">
                    <thead>
                    <tr>
                        <th>��λ</th>
                        <th>�ʲ����</th>
                        <th>���ϱ���</th>
                        <th>��������</th>
                        <th>��λ</th>
                        <th>����</th>
                        <th>����˰����</th>
                        <th>����˰���</th>
                        <th>˰��</th>
                        <th>��˰�ܽ��</th>
                        <th>�۸�ϼƣ�Ԫ��</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${details}" var="detail">
                        <tr>
                            <td>${detail.storeLocationName }</td>
                            <td></td>
                            <td>${detail.materialCode}</td>
                            <td>${detail.description}</td>
                            <td>${detail.detailUnitName}</td>
                            <td>${detail.detailCount}</td>
                            <td>${detail.notaxPrice}</td>
                            <td>${detail.notaxSum}</td>
                            <td>${detail.taxRate}</td>
                            <td>${detail.taxSum}</td>
                            <td>${detail.extendFloat1}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>�ϼ�</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>${sum1}</td>
                        <td></td>
                        <td>${sum2}</td>
                        <td>${sum3}</td>
                    </tr>


                    </tbody>
                </table>
            </div>
            <%--<div style="margin-left: -10px;">
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
                        <th>${sum1}</th>
                        <th>${sum2}</th>
                        <th>${sum3}</th>
                    </tr>
                    </thead>
                </table>
            </div>--%>
            <div style="display:block;">
                <table width="100%">
                    <tr>
                        <td>
                            <div class="layui-form-item">����վ����</div>
                        </td>
                        <td>
                            <div class="layui-form-item">����Ա:${sheet.personname}
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
<script type="text/javascript" src="/js/system/print/RKPrint.js"></script>

</html>
