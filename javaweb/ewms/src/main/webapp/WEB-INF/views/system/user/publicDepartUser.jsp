<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.zthc.ewms.system.user.entity.guard.UserEnums" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>组织部门用户</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
    <style type="text/css">
        .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
    </style>
</head>
<body>
<div class="layui-form-item" style="display:none;">
    <label class="layui-form-label">roleCode</label>
    <div class="layui-input-block">
        <input type="hidden" class="layui-input " id="roleCode" value="${role.roleCode}">
    </div>
</div>
<div class="admin-main">
    <input type="hidden" class="layui-input " id="type" value="${type}">

    <div  style="margin:10px 10px;height:100%;"  >
        <div  style="float:left;border:1px solid #617775;width:320px;position:absolute; height:308px; overflow-y:auto"  >
            <ul id="treeDemo" class="ztree"></ul>
        </div>
        <div style="margin-top:10px;margin-left: 330px;border:1px solid #617775; height:95%;">
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline" style="width: 100px" id="select">
                            <select  lay-filter="kind"  lay-verify="required" >
                                <option value="name" >人员姓名</option>
                                <option value="code" >员工编号</option>
                                <option value="parentName" >所属部门名称</option>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input content" id="content" placeholder="请输入搜索的内容" />
                        </div>
                        <div class="layui-input-inline">
                            <a class="layui-btn btnSearch" id="btnSearch">搜索</a>
                            <a class="layui-btn confirm" id="addNews">确定</a>
                        </div>

                    </div>
                </form>
            </blockquote>
            <table id="manageUser" lay-filter="manageUser"></table>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="/plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/user/publicDepartUser.js"></script>

<!--用户状态-->
<script type="text/html" id="showStatus">
    {{#  if(d.status == '<%=UserEnums.StatusEnum.ENABLE.getStatus()%>'){ }}
    <span class="layui-blue"><%=UserEnums.StatusEnum.ENABLE.getMeaning()%></span>
    {{#  } else if(d.status == '<%=UserEnums.StatusEnum.DISABLE.getStatus()%>'){ }}
    <span class="layui-red"><%=UserEnums.StatusEnum.DISABLE.getMeaning()%></span>
    {{#  } else { }}
    <span class="layui-blue"></span>
    {{#  }}}
</script>
</html>