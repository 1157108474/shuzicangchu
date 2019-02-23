<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>添加明细</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <style type="text/css">
        .input {
            width: 160px !important;
        }
        .wahaha {
            padding: 50px 100px !important;
        }
    </style>
</head>
<body>
<div id="formbody" class="formbody">
    <div class="layui-colla-content layui-show wahaha">
        <form class="layui-form " action="">
            <table id="jcwzrkDetailsTable">
                <tr>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>物料名称：</label>
                            <div class="layui-input-block">
                                <input type="hidden" id="sheetDetailId" value="${sheetDetail.id}">
                                <input type="text" class="layui-input input" value="${sheetDetail.materialName}" id="materialName" readonly="readonly">
                                <button class="layui-btn bill-button" id="materialNameBtn">...</button>
                                <input class="layui-input input" name="creator" id="materialId" value="${sheetDetail.materialId}" type="hidden">
                                <input class="layui-input input" name="creator" id="materialBrand" value="${sheetDetail.materialBrand}" type="hidden">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>物料编码：</label>
                            <div class="layui-input-block">
                                <input type="text" id="materialCode" value="${sheetDetail.materialCode}" name="materialCode" readonly="readonly" class="layui-input input">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label">物料规格：</label>
                            <div class="layui-input-block">
                                <input type="text" id="materialSpecification" value="${sheetDetail.materialSpecification}" name="materialSpecification" readonly="readonly" class="layui-input input">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>

                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label">物料型号：</label>
                            <div class="layui-input-block">
                                <input type="text" id="materialModel" value="${sheetDetail.materialModel}" name="materialModel" readonly="readonly" class="layui-input input">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>单位：</label>
                            <div class="layui-input-block">
                                <input type="text" id="detailUnitName" value="${sheetDetail.detailUnitName}" name="detailUnitName" readonly="readonly" class="layui-input input" >
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>数量：</label>
                            <div class="layui-input-block">
                                <input type="text" id="detailCount" value="${sheetDetail.detailCount}" name="detailCount" class="layui-input input" onchange="sumAmount()">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>库房：</label>
                            <div class="layui-input-block input">
                                <select lay-verify="required" id="storeId" lay-filter="store" name="storeId"  value="${sheetDetail.storeId}" >
                                    <option value=""></option>
                                    <c:forEach items="${stores}" var="s">
                                        <c:if test="${s.id==sheetDetail.storeId}">
                                            <option value="${s.id}" selected="selected">${s.name}</option>
                                        </c:if>
                                        <c:if test="${s.id!=sheetDetail.storeId}">
                                            <option value="${s.id}">${s.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>不含税单价：</label>
                            <div class="layui-input-block">
                                <input type="text" id="noTaxPrice" name="noTaxPrice" value="${sheetDetail.noTaxPrice}" class="layui-input input" onchange="sumAmount()">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>不含税金额：</label>
                            <div class="layui-input-block">
                                <input type="text" id="noTaxSum" name="noTaxSum" value="${sheetDetail.noTaxSum}" readonly="readonly" class="layui-input input">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>

                </tr>
                <tr>

                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>库位：</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input input" id="locationName" value="${sheetDetail.storeLocationName}">
                                <button class="layui-btn bill-button" id="storeLocation">...</button>
                                <input class="layui-input input" id="locationId" name="storeLocationId"  value="${sheetDetail.storeLocationId}" type="hidden">
                                <input class="layui-input input" id="locationCode"  type="hidden" value="${sheetDetail.storeLocationCode}">
                            </div>
                        </div>
                    </td>
                    <td colspan="3">
                        <div class="layui-form-item ">
                            <label class="layui-form-label">物料说明：</label>
                            <div class="layui-input-block">
                                <input type="text" id="description" value="${sheetDetail.description}" name="description" readonly="readonly" class="layui-input">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td></td><td></td><td></td>
                </tr>
                <tr>
                    <td align="center" colspan="3" style="padding-top: 30px">
                        <button class="layui-btn bill-td-button" id="add">保存</button>
                        <button type="reset" id="reset" class="layui-btn bill-td-button">取消</button>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</div>

</body>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/jcwzrk/addDetails.js"></script>
</html>

