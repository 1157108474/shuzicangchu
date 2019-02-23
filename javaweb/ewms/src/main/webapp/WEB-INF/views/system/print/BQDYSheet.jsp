<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>标签打印</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>

</head>
<body>

<div class="admin-main" style="margin-left: 10px;width:80%;">
    <input id="num" type="hidden" value="${num}">
    <div>
        <div class="noPrint">
            <a href="javascript:;" class="layui-btn  layui-btn-small" id="print"
               style="height: 30px;line-height: 30px;margin-bottom: 1px">打印</a>&nbsp;<span id="page"></span>
        </div>
        <div>
            <table class="layui-table" border="1" cellspacing="0" style="width:350px;height:150px;">
                <tr style="height:50px;">
                    <td style="width:50px;">物料描述</td>
                    <td colspan="2"><span id="description"></span></td>
                </tr>
                <tr>
                    <td style="width:50px;">物料编码</td>
                    <td><span id="materialcode"></span></td>
                    <td rowspan="2" style="width: 100px;height:100px"><img></img></td>
                </tr>
                <tr>
                    <td style="width:50px;">单位</td>
                    <td><span id="detailunitname"></span></td>
                </tr>
            </table>
        </div>
        <iframe id="printf" src="" width="0" height="0" frameborder="0"></iframe>
    </div>
</div>
</body>

<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/print/BQDYPrint.js"></script>

</html>