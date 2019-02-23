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
               style="height: 30px;line-height: 30px;margin-bottom: 1px">打印</a>&nbsp;
        </div>
        <input id="id" value="${sheet.id}" type="hidden">
        <div>
            <div><span style="display:block;text-align:center">中国中煤能源股份有限公司采购中心</span></div>
            <div><span style="display:block;text-align:center">${titleDesc}</span></div>
            <div style="display:block;">
                <table width="100%">
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                库存组织： ${sheet.extendstring2}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                供货单位： ${sheet.extendstring1}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                仓库：${houseCode}
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                订单编号：${sheet.ordernum}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                ${sheet.createDateStr}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                No：${sheet.code}
                            </div>
                        </td>
                    </tr>
                </table>
            </div>


            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1" style="text-align: center">
                    <thead>
                    <tr>
                        <th>货位</th>
                        <th>资产编号</th>
                        <th>物料编码</th>
                        <th>物料描述</th>
                        <th>单位</th>
                        <th>数量</th>
                        <th>不含税单价</th>
                        <th>不含税金额</th>
                        <th>税率</th>
                        <th>含税总金额</th>
                        <th>价格合计（元）</th>

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
                        <td>合计</td>
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
                        <th>合计</th>
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
                            <div class="layui-form-item">物质站长：</div>
                        </td>
                        <td>
                            <div class="layui-form-item">保管员:${sheet.personname}
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
