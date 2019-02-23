<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�����Ĵ�������ⵥ</title>
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
                                    <label class="layui-form-label">���ݱ��:</label>
                                    <div class="layui-input-block">
                                        <input id="code" name="code" type="text" class="layui-input">
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">����״̬��</label>
                                    <div class="layui-input-block">
                                        <select  name="status" id="status">
                                            <option value="">��ѡ��...</option>
                                            <c:forEach items="${orderStatus}" var="d" >
                                                <option value="${d.id}">${d.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƶ��ˣ�</label>
                                    <div class="layui-input-block" style="position:relative;">
                                        <input type="text" class="layui-input" id="creatorName" >
                                        <button class="layui-btn bill-button" id="creatorBtn">...</button>
                                        <input class="layui-input" name="creator" id="creator" type="hidden">
                                    </div>
                                </div>
                            </td>
                        </tr>


                        <tr>
                            <td >
                                <div class="layui-form-item">
                                    <label class="layui-form-label">�Ƶ����ڣ�</label>
                                    <div class="layui-input-block form-label">
                                        <div style="float: left;width: 80px">
                                            <input type="text"  class="layui-input startTime" id="begin" name="begin" placeholder="yyyy-MM-dd">
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;">��</div>
                                        <div style="float: left;width: 80px">
                                            <input type="text"  class="layui-input endTime" id="end" name="end" placeholder="yyyy-MM-dd">
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <button style="margin-left: 30px" class="layui-btn layui-btn-warm bill-td-button"
                                        lay-submit="" lay-filter="search" id="submitBtn">��ѯ
                                </button>
                                <button class="layui-btn bill-td-button " type="reset" id="reset">����</button>
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
                <table id="jcwzrkgrid" lay-filter="jcwzrkgrid"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="/js/sheet/jcwzrk/manageJcWzrk.js"></script>


<script type="text/html" id="barDemo" >

    {{# if( d.statusName == '�Ƶ���' ) { }}
    <a  lay-event="edit" class="layui-btn layui-btn-xs">�༭</a>
    <a  lay-event="delete" class="layui-btn layui-btn-danger layui-btn-xs">ɾ��</a>
    {{# }else{ }}

    <a lay-event="show" class="layui-btn layui-btn-xs">�鿴</a>
    {{#} }}

</script>
</html>

