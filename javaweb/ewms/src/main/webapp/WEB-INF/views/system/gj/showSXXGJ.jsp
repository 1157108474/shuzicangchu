<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�����޸澯</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <style type="text/css">
        .form-label {
            width: 180px !important;
        }

        .form-div-input {
            margin-left: 180px !important;
        }
    </style>
</head>
<body>
<div id="formbody" class="formbody">

    <div id="llSheetHead" class="layui-collapse">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">��ѯ����</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table   >
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">���ϱ��룺</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  type="text" class="layui-input" name="code" id="code">
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">����������</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  type="text" class="layui-input" name="description" id="description" >
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">����ͺţ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input  type="text" class="layui-input" name="models" id="models" >
                                    </div>
                                </div>
                            </td>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�澯���ͣ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <select name="gjtype" id="gjtype">
                                            <option></option>
                                            <option value="0">������޸澯</option>
                                            <option value="1">������޸澯</option>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td >
                                <button class="layui-btn layui-btn-warm bill-td-button" lay-submit lay-filter="formSubmit"
                                    style="margin-left: 36px !important;">��ѯ</button>
                                <button type="reset" class="layui-btn bill-td-button" style="margin-left: 5px !important;">����</button>
                                <a id="export" class="layui-btn bill-td-button" style="margin-left: 5px !important;">����</a>
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
<script type="text/html" id="bar">
    <a lay-event="show" class="layui-btn layui-btn-xs" onclick="showSheet('{{d.id}}')">�鿴</a>
</script>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/js/system/gj/showSXXGJ.js"></script>
</html>

