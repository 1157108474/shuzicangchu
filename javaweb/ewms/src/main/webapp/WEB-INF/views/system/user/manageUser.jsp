<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zthc.ewms.system.user.entity.guard.UserEnums" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>人员管理</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <style type="text/css">
        .form-div{
            width: 100%;
            margin-bottom: -3px!important;
        }
        .form-label{
            width: 150px!important;
        }
        .form-div-input{
            margin-left: 150px!important;
        }
    </style>
</head>
<body>

<div class="admin-main">
    <blockquote class="layui-elem-quote" style="margin-left: 5px!important;margin-top: 5px;height: 25px!important;">

        <div style="float: left">
            <a href="javascript:void(0);" class="layui-btn add" id="add">新增</a>
            <a href="javascript:void(0);" class="layui-btn edit" id="edit">编辑</a>
            <a href="javascript:void(0);" class="layui-btn rePWD" id="rePWD">重置密码</a>
            <a href="javascript:void(0);" class="layui-btn layui-btn-danger " id="delAll">删除</a>
        </div>
        <div style="float: right">
            姓名:
            <input id="userName" autocomplete="on"  class="mini-textbox"
                   style="width:150px;display:inline; " type="text">
            机构名称:
            <input id="parentName" autocomplete="on"  class="mini-textbox"
                   style="width:150px;display:inline; " type="text">
            科室/区队:
            <input id="offName" autocomplete="on"  class="mini-textbox"
                   style="width:150px;display:inline; " type="text">

            <button class="layui-btn" lay-submit lay-filter="formSubmit">立即查询</button>
        </div>
    </blockquote>
    <div style="margin-left: 5px;width:60%;float: left">
        <table id="manageUser" lay-filter="manageUser"></table>
    </div>

    <div class="layui-tab" style="margin: 1px;width:36%;float: left">
        <ul class="layui-tab-title">
            <li class="layui-this">基本信息</li>
            <li>其他信息</li>
            <li>人员角色</li>
        </ul>
        <div class="layui-tab-content" style="height: 389px!important; ">
            <!--新增、编辑人员-->
            <div class="layui-tab-item layui-show">
                <form class="layui-form layui-form-pane" id="userForm" action="">

                    <input type="hidden" class="layui-input " id="id">
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label"><span style="color: red">*</span>员工编码</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input " lay-verify="required" maxlength="16" id="code">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label"><span style="color: red">*</span>姓名</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input " lay-verify="required" maxlength="16" id="name">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">名称拼音</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input "  id="spell">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">性别</label>
                        <div class="layui-input-block" id="sex">
                            <select id="sexSelect" lay-filter="kind" >
                                <option value="0">男</option>
                                <option value="1">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">邮箱</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input " maxlength="16" id="email">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label"><span style="color: red">*</span>部门</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input " id="departName">
                            <input type="hidden" name = "departId" id="departId">
                        </div>

                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label"><span style="color: red">*</span>科室区队</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input " id="officesName">
                            <input type="hidden" name = "officesId" id="officesId">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">联系方式</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input "  maxlength="16" id="phone">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">QQ</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input "  maxlength="16" id="qq">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label"><span style="color: red">*</span>排序</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input "  lay-verify="number" maxlength="16"  id="sort">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">状态</label>
                        <div class="layui-input-block" id="status">
                            <select id="statusSelect" lay-filter="kind" >
                                <option value="1">在用</option>
                                <option value="0">禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">单点登录</label>
                        <div class="layui-input-block" id="isSingleLogin">
                            <select id="isSingleLoginSelect" lay-filter="kind">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input "  maxlength="16" id="memo">
                        </div>
                    </div>

                    <div class="layui-form-item" style="text-align: center;margin-top: 5px;">
                        <a class="layui-btn" lay-filter="addNews" id="addNews" style="display: none" lay-submit>确定</a>
                        <a class="layui-btn" type="reset" style="display: none" id="reset">取消</a>

                    </div>
                </form>
            </div>
            <!--其他信息-->
            <div class="layui-tab-item">
                <form class="layui-form layui-form-pane " action="">
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">是否在线</label>
                        <div class="layui-input-block form-div-input"  id="isOnline">
                            <select id="isOnlineSelect" lay-filter="kind">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>

                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">最后登录时间</label>
                        <div class="layui-input-block form-div-input">
                            <input type="text" class="layui-input " disabled id="loginTime">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">最后登录IP地址</label>
                        <div class="layui-input-block form-div-input">
                            <input type="text" class="layui-input " disabled id="loginIp">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">最后登录城市</label>
                        <div class="layui-input-block  form-div-input">
                            <input type="text" class="layui-input " disabled id="loginCity">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">最后修改密码时间</label>
                        <div class="layui-input-block  form-div-input">
                            <input type="text" class="layui-input " disabled id="lastChangePassWord">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">是否审核</label>
                        <div class="layui-input-block form-div-input"  id="isAudit">
                            <select id="isAuditSelect" lay-filter="kind">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                    <label class="layui-form-label form-label">审核人</label>
                    <div class="layui-input-block  form-div-input">
                        <input type="text" class="layui-input " disabled id="auditby">
                    </div>
                </div>
                    <div class="layui-form-item form-div" >
                    <label class="layui-form-label form-label">审核时间</label>
                    <div class="layui-input-block  form-div-input">
                        <input type="text" class="layui-input " disabled id="auditTime">
                    </div>
                </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">创建人</label>
                        <div class="layui-input-block  form-div-input">
                            <input type="text" class="layui-input " disabled id="creator">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">创建时间</label>
                        <div class="layui-input-block  form-div-input">
                            <input type="text" class="layui-input " disabled id="createDate" >
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">编辑人</label>
                        <div class="layui-input-block form-div-input">
                            <input type="text" class="layui-input " disabled id="updater">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">编辑时间</label>
                        <div class="layui-input-block form-div-input">
                            <input type="text" class="layui-input " disabled id="updateDate">
                        </div>
                    </div>
                </form>
            </div>
            <!--人员角色-->
            <div class="layui-tab-item role"  >
                <a href="javascript:void(0);" class="layui-btn addUserRole" id="addUserRole">保存用户角色</a>
                <input type="hidden" class="layui-input " id="roleCodes">
                <input type="hidden" class="layui-input " id="userId">
                <table id="manageRole" lay-filter="manageRole" ></table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/dateUtil.js"></script>
<script type="text/javascript" src="/js/system/user/manageUser.js"></script>

<!--状态-->
<script type="text/html" id="showStatus">
    {{#  if(d.status == '<%=UserEnums.StatusEnum.ENABLE.getStatus()%>'){ }}
    <span class="layui-blue"><%=UserEnums.StatusEnum.ENABLE.getMeaning()%></span>
    {{#  } else if(d.status == '<%=UserEnums.StatusEnum.DISABLE.getStatus()%>'){ }}
    <span class="layui-red"><%=UserEnums.StatusEnum.DISABLE.getMeaning()%></span>
    {{#  } else { }}
    <span class="layui-blue"></span>
    {{#  }}}
</script>
<script type="text/html" id="barOption">
    <div class="layui-btn-group">
        <a href="javascript:void(0);" class="layui-btn edit" style="line-height: inherit!important;"
           lay-event="operation">操作</a>
    </div>
</script>
</html>