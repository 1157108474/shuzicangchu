<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>��Ʒ������ѯ</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>
<div id="formbody" class="formbody">

    <div id="llSheetHead" class="layui-collapse">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">��ѯ����</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�ӿ���ţ�</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" name="subinvCode">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��λ���ƣ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="locatorName">
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">��λID��</label>
                                    <div class="layui-input-block">
                                        <input type="text" class="layui-input" name="locatorId">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�ʲ���ţ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="assetNumber" >
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���ϱ��룺</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" name="itemNo" >
                                    </div>
                                </div>
                            </td>
                            <td>
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit
                                        lay-filter="formSubmit"
                                        style="margin-left: 36px !important;">��ѯ
                                </button>
                                <button type="reset" class="layui-btn bill-td-button"
                                        style="margin-left: 5px !important;">����
                                </button>
                            </td>


                        </tr>

                    </table>
                </form>
            </div>
        </div>
    </div>
    <div class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">�����б�</h2>
            <div class="layui-colla-content layui-show">
                <table id="querygrid" lay-filter="querygrid"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/mes/queryMesCk.js"></script>
</html>

