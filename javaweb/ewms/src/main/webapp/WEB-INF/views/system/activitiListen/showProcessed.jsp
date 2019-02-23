<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>我的已办</title>
    <link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
</head>
<body>
<div id="formbody" class="formbody">

    <div id="llSheetHead" class="layui-collapse">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">查询条件</h2>
            <div class="layui-colla-content layui-show">
                <form class="layui-form " action="">
                    <table>
                        <tr>

                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据编号:</label>
                                    <div class="layui-input-block">
                                        <input id="temCode" name="infTaskdetailn" autocomplete="on" type="text" style="width: 300px"
                                               class="layui-input">
                                    </div>
                                </div>
                            </td>

                            <td class="bill-td">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">单据标题：</label>
                                    <div class="layui-input-block">
                                        <input id="temName" name="infTaskdetailn" autocomplete="on" type="text" style="width: 300px"
                                               class="layui-input">
                                    </div>
                                </div>
                            </td>

                        </tr>

                        <tr>
                            <td>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">提交时间:</label>
                                    <div class="layui-input-block form-label">
                                        <div style="float: left;">
                                            <input type="text" class="layui-input startTime" id="startTime" style="width: 140px" placeholder="开始日期"
                                                   >
                                        </div>
                                        <div style="width: 20px;float: left;text-align: center;height: 27px;line-height: 27px;vertical-align:middle;">至</div>
                                        <div style="float: left;">
                                            <input type="text" class="layui-input endTime" id="endTime" style="width: 140px"  placeholder="结束日期"
                                                   >
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="bill-td">
                                <button style="margin-left: 70px" class="layui-btn layui-btn-warm bill-td-button"
                                        lay-submit lay-filter="formSubmit">查询
                                </button>
                                <button class="layui-btn bill-td-button" type="reset" id="reset">重置</button>
                            </td>

                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <div class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">数据列表</h2>
            <div class="layui-colla-content layui-show">
                <table id="activitiProcessed" lay-filter="activitiProcessed"></table>
            </div>
        </div>
    </div>
</div>


</body>
<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/js/system/act/activitiProcessed.js"></script>

</html>