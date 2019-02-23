<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>打印接收单</title>
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
            <div><span style="display:block;text-align:center">材料类物资到货接收单</span></div>
            <div style="display:block;">
                <table width="100%">
                    <tr>
                        <td>
                            <div class="layui-form-item">
                                所属单位： ${sheet.extendString2}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                ${sheet.createDateStr}
                            </div>
                        </td>
                        <td>
                            <div class="layui-form-item">
                                到货验收单号：${sheet.code}
                            </div>
                        </td>
                    </tr>
                </table>
            </div>


            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1">
                    <thead>
                    <tr>
                        <th>物资编码</th>
                        <th>物料说明</th>
                        <th>计量单位</th>
                        <th>合同数量</th>
                        <th>实际到货数量</th>
                        <th>生产厂或品牌</th>
                        <th>生产日期</th>
                        <th>出厂编号</th>
                        <th>外在质量及数量验收记录</th>

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
                        <th>供货单位</th>
                        <th>${sheet.extendString1}</th>
                        <th>采购方式</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>存放地点</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>图像及随机资料情况</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>

                    </tr>
                    </thead>
                </table>
            </div>
            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1">
                    <thead>
                    <tr>
                        <th>合同编号</th>
                        <th>${sheet.contractNum}</th>
                        <th>到货日期</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        <th>验收日期</th>
                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div style="margin-left: -10px;">
                <table width="100%" class="need-border" border="1">
                    <thead>
                    <tr>
                        <th>验收意见</th>
                        <th>公司主管部门</th>
                        <th>矿分管职能部门或使用部门</th>
                        <th>监理或质检</th>
                        <th>供应站</th>
                        <th>供应商</th>
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
                            <div class="layui-form-item">库管员：${sheet.personName}</div>
                        </td>
                        <td>
                            <div class="layui-form-item">采购员:${userName}
                            </div>
                        </td>
                    </tr>
                   <%-- <tr>
                        <td>
                            <div class="layui-form-item">计划部门：</div>
                        </td>
                        <td>
                            <div class="layui-form-item">计划审核部门：
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
