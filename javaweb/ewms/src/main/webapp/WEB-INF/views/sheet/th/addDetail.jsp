<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>添加明细</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>
<div id="formbody" class="formbody">
    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <!--
            <h2 class="layui-colla-title">查询条件</h2>-->

            <div class="layui-colla-content layui-show">


                    <form class="layui-form " action="">
                        <table width="100%">
                            <tr>
                                <td>
                                    <div class="layui-form-item bill-search">
                                        <label class="layui-form-label">物料编码：</label>
                                        <div class="layui-input-block">
                                            <input type="text" class="layui-input" name="materialCode">
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="layui-form-item bill-search">
                                        <label class="layui-form-label">物料描述：</label>
                                        <div class="layui-input-block">
                                            <input type="text" class="layui-input" name="description">
                                        </div>
                                    </div>
                                </td>

                                <td class="bill-td" width="20%">
                                   <button class="layui-btn layui-btn-warm bill-td-button" lay-filter="search" lay-submit>查询
                                    </button>
                                    <button class="layui-btn bill-td-button " lay-filter="reset" lay-submit>重置</button>

                                </td>
                            </tr>
                        </table>
                    </form>

                <div>
                    <table id="detailGrid" lay-filter="detailGrid"></table>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-collapse" lay-filter="test" id="stockDetail" style="display: none">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">库存信息</h2>
            <div class="layui-colla-content layui-show">
                <div>
                    <button class="layui-btn bill-td-button" id="add">添加</button>
                </div>
                <div>
                    <table id="detailstock" lay-filter="detailRk" style="display:none"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/th/addDetail.js"></script>
</html>

