<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>物料范围管理</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
    <link href="/css/lanrenzhijia.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<input type="hidden" class="layui-input " id="userId" value="${user.id}">
<input type="hidden" class="layui-input " id="userZtId" value="${user.ztId}">
<input type="hidden" class="layui-input " id="userGuId" value="${user.guId}">

<div class="wizardwrapper">
    <div class="1">
        <ul class="mainNav fiveStep ">
            <li class="current"><a title=""><span>组织机构</span></a></li>
            <li><a title=""><span>库房库区</span></a></li>
            <li><a title=""><span>物料范围</span></a></li>
            <li><a title=""><span>下辖科室</span></a></li>
            <li class="mainNavNoBg"><a title=""></a></li>
        </ul>
        <div class="wizardcontent">
            <table>
                <tr>
                    <td style="width: 320px">
                        <div style="border: 1px solid #0085cf;margin-left: 5%">
                            <div class="mini-toolbar">&nbsp;&nbsp;组织机构管理</div>
                            <div class="ztree-in" style="height: 210px;">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>
                    </td>
                    <td style="width:30px;">
                        <div style="margin-left: 20%">
                            <input type="button" value=">" id="addDept"/><br/><br/>
                            <input type="button" value="<" id="removeDept"/>
                        </div>
                    </td>
                    <td>
                        <table id="manageDept" lay-filter="manageDept"></table>
                    </td>
                </tr>
            </table>
        </div>
        <div style="height: 35px;bottom: 0px">
            <hr>
            <div style="clear:both;float: right;margin-right: 10px">
                <button class="layui-btn layui-btn-sm" disabled="disabled">上一步</button>
                <button class="layui-btn layui-btn-sm" onclick="loadnext(1,2);">下一步</button>
                <button class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit>完成</button>
            </div>
        </div>
    </div>
    <div class="wizardpanel 2">
        <ul class="mainNav fiveStep">
            <li class="lastDone"><a title=""><span>组织机构</span></a></li>
            <li class="current"><a title=""><span>库房库区</span></a></li>
            <li><a title=""><span>物料范围</span></a></li>
            <li><a title=""><span>下辖科室</span></a></li>
            <li class="mainNavNoBg"><a title=""></a></li>
        </ul>
        <div class="wizardcontent">
            <table>
                <tr>
                    <td style="width: 330px">
                        <table id="manageWareHouse" lay-filter="manageWareHouse"></table>
                    </td>
                    <td style="width:40px;">
                        <div style="margin-left: 10px">
                            <input type="button" value=">" id="addWareHouse"/><br/><br/>
                            <input type="button" value="<" id="removeWareHouse"/>
                        </div>
                    </td>
                    <td style="width: 330px">
                        <table id="manageNewWareHouse" lay-filter="manageNewWareHouse"></table>
                    </td>
                </tr>
            </table>
        </div>
        <div style="height: 35px;bottom: 0px">
            <hr>
            <div style="clear:both;float: right;margin-right: 10px">
                <button class="layui-btn layui-btn-sm" onclick="loadnext(2,1);">上一步</button>
                <button class="layui-btn layui-btn-sm" onclick="loadnext(2,3);">下一步</button>
                <button class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit>完成</button>
            </div>
        </div>
    </div>
    <div class="wizardpanel 3">
        <ul class="mainNav fiveStep">
            <li class="done"><a title=""><span>组织机构</span></a></li>
            <li class="lastDone"><a title=""><span>库房库区</span></a></li>
            <li class="current"><a title=""><span>物料范围</span></a></li>
            <li><a title=""><span>下辖科室</span></a></li>
            <li class="mainNavNoBg"><a title=""></a></li>
        </ul>
        <div class="wizardcontent">
            <table>
                <tr>
                    <td style="width: 320px">
                        <table id="manageSparepart" lay-filter="manageSparepart"></table>
                    </td>
                    <td style="width:30px;">
                        <div style="margin-left: 20%">
                            <input type="button" value=">" id="addSparepart"/><br/><br/>
                            <input type="button" value="<" id="removeSparepart"/>
                        </div>
                    </td>
                    <td>
                        <table id="manageNewSparepart" lay-filter="manageNewSparepart"></table>
                    </td>
                </tr>
            </table>
        </div>
        <div style="height: 35px;bottom: 0px">
            <hr>
            <div style="clear:both;float: right;margin-right: 10px">
                <button class="layui-btn layui-btn-sm" onclick="loadnext(3,2);">上一步</button>
                <button class="layui-btn layui-btn-sm" onclick="loadnext(3,4);">下一步</button>
                <button class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit>完成</button>
            </div>
        </div>

    </div>
    <div class="wizardpanel 4">
        <ul class="mainNav fiveStep">
            <li class="done"><a title=""><span>组织机构</span></a></li>
            <li class="done"><a title=""><span>库房库区</span></a></li>
            <li class="lastDone"><a title=""><span>物料范围</span></a></li>
            <li class="current"><a title=""><span>下辖科室</span></a></li>
            <li class="mainNavNoBg"><a title=""></a></li>
        </ul>
        <div class="wizardcontent">
            <table>
                <tr>
                    <td style="width: 320px">
                        <table id="manageUseDep" lay-filter="manageUseDep"></table>
                    </td>
                    <td style="width:40px;">
                        <div style="margin-left: 10px"><input type="button" value=">" id="addUseDep"/><br/><br/>
                            <input type="button" value="<" id="removeUseDep"/>
                        </div>
                    </td>
                    <td>
                        <table id="manageNewUseDep" lay-filter="manageNewUseDep"></table>
                    </td>
                </tr>
            </table>
        </div>
        <div style="height: 35px;bottom: 0px">
            <hr>
            <div style="clear:both;float: right;margin-right: 10px">
                <button class="layui-btn layui-btn-sm" onclick="loadnext(4,3);">上一步</button>
                <button class="layui-btn layui-btn-sm" disabled="disabled">下一步</button>
                <button class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit>完成</button>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/dateUtil.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="/js/jquery-latest.pack.js"></script>
<script type="text/javascript" src="/js/system/user/manageOperation.js"></script>

<script type="text/javascript">
    function loadnext(divout, divin) {
        $("." + divout).hide();
        $("." + divin).fadeIn("fast");
        if (divin == 2) {
            $('#addWareHouse').trigger("click");
        } else if (divin == 3) {
            $('#addSparepart').trigger("click");
        } else if (divin == 4) {
            $('#addUseDep').trigger("click");
        }
    }
</script>
</html>