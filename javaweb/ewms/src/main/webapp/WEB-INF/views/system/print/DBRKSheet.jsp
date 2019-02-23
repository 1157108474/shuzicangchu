<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>打印退货单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>

<div class="admin-main" style="commargin-left: 10px;">
    <input id="num" type="hidden">
    <div style="margin: 10px">
        <div class="noPrint" style="float:right;">
            <a href="javascript:;" class="layui-btn  layui-btn-small" id="print"
               style="height: 30px;line-height: 30px;margin-bottom: 1px">打印</a>&nbsp;
        </div>
        <input id="id" value="${sheet.id}" type="hidden">
        <div>
            <div><span style="display:block;text-align:center">中国中煤能源股份有限公司采购中心</span></div>
            <div><span style="display:block;text-align:center">物资供应中心物资调拨入库单</span></div>
            <div style="display:block;">
                <table width="100%">
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                库存组织： ${sheet.drOrg}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                供货单位： ${sheet.dcOrg}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                仓库：${sheet.houseCode}
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                No：${sheet.rkCode}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                ${sheet.createDateStr}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                调拨单号：${sheet.dbCode}
                            </div>
                        </td>
                    </tr>
                </table>
            </div>


            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1">
                    <thead>
                    <tr>
                        <th>货位</th>
                        <th>物料编码</th>
                        <th>名称</th>
                        <th>规格</th>
                        <th>单位</th>
                        <th>输入数量</th>
                        <th>单价（元）</th>
                        <th>金额（元）</th>
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
                        <th>合计</th>
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
                            <div class="layui-form-item">物资供应站站长：</div>
                        </td>
                        <td>
                            <div class="layui-form-item">保管员:${sheet.rkPerson}
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
