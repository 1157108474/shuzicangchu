<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>审批页面${taskId}</title>
    <style type="text/css">
        .mini-tab-text {
            display: inline-block;
            vertical-align: middle;
            line-height: 16px;
           /* padding: 1px;*/
           /* padding-left: 2px;*/
           /* padding-right: 2px;*/
        }
        .mini-tabs-space {
            border-color: #8CB2E2;
        }
        .mini-tabs-space {
            width: 3px;
            border-bottom: solid 1px #999999;
        }
        .mini-tab {
            color: #333;
        }
        .mini-tab-active {
            border-bottom: solid 1px white;
            background: white;
            cursor: default;
            border-bottom: solid 0px #999999;
        }
        .mini-tab-noactive{
            background: #EBEBEE url(/images/tab/tab.png) repeat-x 0 0;
            border-bottom: solid 1px #999999;
        }
        .mini-tab {
           /* background: #EBEBEE url(/images/tab/tab.png) repeat-x 0 0;*/
            color: #201F35;
            border-top: 1px solid #999999;
            border-left: 1px solid #999999;
            border-right: 1px solid #999999;
            color: black;
            font: 9pt Tahoma;
            padding: 3px 10px 3px 10px;
            text-align: center;
            cursor: pointer;
            white-space: nowrap;
        }
        .mini-tab-dis{
            display: none;
        }
        .mini-tab{
            display: inline;
        }
    </style>
    <link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
</head>

<body>
<input id="taskId" value="${taskId}" type="hidden">
<div class="admin-main">
    <blockquote class="layui-elem-quote" style="margin-left: 10px;margin-top: 5px;margin-right: 10px;">
        <c:forEach items="${premissionButton}" var="pre">
            <a href="javascript:void(0);" class="layui-btn" id="${pre.code}" >${pre.name}</a>
        </c:forEach>
    </blockquote>
    <div style="margin:50px 10px;height:100%;">
        <div style="width: 40%">
            <label class="layui-form-label">批注：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input "  lay-verify="required" value=""
                       maxlength="16" name="comment" id="comment">
            </div>
        </div>
        <div>
            <table>
                <tbody>
                    <tr class="tabs">
                        <td class="mini-tabs-space mini-tabs-firstSpace"><div style="width: 3px"></div></td>
                        <td title="" id="mini-141" index="0" class="mini-tab mini-tab-active"><span class="mini-tab-text">明细列表</span></td>
                        <td class="mini-tabs-space2"style="border-bottom: 2px solid #8CB2E2"><div style="width: 3px"></div></td>
                        <td title="" id="mini-142" index="1" class="mini-tab mini-tab-noactive"><span class="mini-tab-text">操作履历</span></td>
                        <td class="mini-tabs-space mini-tabs-lastSpace" style="width: 100%;"><div></div></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div>
            <div style="float:left;width:100%;height:100%;border-top: none" class="mini-tab-show">
                <table  id="activitiProcessing" lay-filter="activitiProcessing"></table>
            </div>
            <div class="mini-tab-dis"><%-- style="display: none"--%>
                <table  id="activitiHis" lay-filter="activitiHis"></table>
                <%--<image src='<%=path %>/system/activitiListener/getHistoryImage.htm?taskId=${taskId}'></image>--%>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/js/system/act/processDetail.js"></script>
<script type="text/javascript">

    $("#mini-141").click(function(){
        document.getElementById("mini-141").className = "mini-tab mini-tab-active";
        document.getElementById("mini-142").className = "mini-tab mini-tab-noactive";
        $(".mini-tab-show").show();
        $(".mini-tab-dis").hide();
    })
    $("#mini-142").click(function(){
        document.getElementById("mini-141").className = "mini-tab mini-tab-noactive";
        document.getElementById("mini-142").className = "mini-tab mini-tab-active";
        $(".mini-tab-dis").show();
        $(".mini-tab-show").hide();
    })
</script>
</html>