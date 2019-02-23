<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�����ϸ</title>
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
                            <label class="layui-form-label"><font color="red">* </font>�������ƣ�</label>
                            <div class="layui-input-block">
                                <input type="hidden" id="sheetDetailId">
                                <input type="text" class="layui-input input" id="materialName" readonly="readonly">
                                <button class="layui-btn bill-button" id="materialNameBtn">...</button>
                                <input class="layui-input input" name="creator" id="materialId" type="hidden">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>���ϱ��룺</label>
                            <div class="layui-input-block">
                                <input type="text" id="materialCode" name="materialCode" readonly="readonly"
                                       class="layui-input input">
                                <input type="hidden" id="model" name="materialmodel">
                                <input type="hidden" id="sparescateId" name="categoryid" value="${providerId}">
                                <input type="hidden" id="brand" name="materialbrand">
                                <input type="hidden" id="specifications" name="materialspecification">
                                <input type="hidden" id="providerId" name="providerdepid">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>��λ��</label>
                            <div class="layui-input-block">
                                <input type="text" id="detailUnitName" name="detailUnitName" readonly="readonly"
                                       class="layui-input input">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>������</label>
                            <div class="layui-input-block">
                                <input type="text" id="detailCount" name="detailCount" class="layui-input input"
                                       onchange="sumAmount(this.value)">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>˰�ʣ�</label>
                            <div class="layui-input-block">
                                <input type="text" id="taxRate" name="taxRate" class="layui-input input"
                                       onchange="rateAmount(this.value)">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>����˰���ۣ�</label>
                            <div class="layui-input-block">
                                <input type="text" id="noTaxPrice" name="noTaxPrice" class="layui-input input"
                                       onchange="sumAmount(this.value)">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>����˰��</label>
                            <div class="layui-input-block">
                                <input type="text" id="noTaxSum" name="noTaxSum" readonly="readonly"
                                       class="layui-input input">
                            </div>
                        </div>
                    </td>
                    <td colspan="3">
                        <div class="layui-form-item ">
                            <label class="layui-form-label">����������</label>
                            <div class="layui-input-block">
                                <input type="text" id="description" name="description" readonly="readonly"
                                       class="layui-input">
                            </div>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>�Ƿ��豸��</label>
                            <div class="layui-input-block">
                                <select id="isequipment" name="isequipment" lay-filter="isopen" onchange="isSn()">
                                    <option value="">��ѡ��...</option>
                                    <option value="1">��</option>
                                    <option value="0">��</option>
                                </select>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label">�������кţ�</label>
                            <div class="layui-input-block">
                                <select id="enablesn" name="enablesn" disabled>
                                    <option value="">��ѡ��...</option>
                                    <option value="1">��</option>
                                    <option value="0">��</option>
                                </select>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>�ⷿ��</label>
                            <div class="layui-input-block input">
                                <select lay-verify="required" id="storeId" name="extendstring1">
                                    <option value="">��ѡ��</option>
                                    <c:forEach items="${stores}" var="s">
                                        <option value="${s.code}">${s.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>

                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>�ƻ����ţ�</label>
                            <div class="layui-input-block">
                                <select id="plandepartid" name="plandepartid">
                                    <option value="">��ѡ��...</option>
                                    <c:forEach items="${departList}" var="depart" varStatus="d">
                                        <option value="${depart.id}">${depart.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </td>

                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>�������ޣ�</label>
                            <div class="layui-input-block">
                                <input type="text" id="expirationtime" name="expirationtime" class="layui-input input">
                            </div>
                        </div>
                    </td>

                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label"><font color="red">* </font>�������ڣ�</label>
                            <div class="layui-input-block">
                                <input type="text" id="extenddate2" name="extenddate2" class="layui-input input">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>

                </tr>
                <tr>
                    <td align="center" colspan="3" style="padding-top: 30px">
                        <button class="layui-btn bill-td-button" id="add">����</button>
                        <button type="reset" id="reset" class="layui-btn bill-td-button">ȡ��</button>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</div>

</body>
<%--<script src="http://code.jquery.com/jquery-latest.js"></script>--%>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/zr/addDetails.js"></script>
</html>

