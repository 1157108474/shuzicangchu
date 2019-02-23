<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zthc.ewms.system.user.entity.guard.UserEnums" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>��Ա����</title>
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
            <a href="javascript:void(0);" class="layui-btn add" id="add">����</a>
            <a href="javascript:void(0);" class="layui-btn edit" id="edit">�༭</a>
            <a href="javascript:void(0);" class="layui-btn rePWD" id="rePWD">��������</a>
            <a href="javascript:void(0);" class="layui-btn layui-btn-danger " id="delAll">ɾ��</a>
        </div>
        <div style="float: right">
            ����:
            <input id="userName" autocomplete="on"  class="mini-textbox"
                   style="width:150px;display:inline; " type="text">
            ��������:
            <input id="parentName" autocomplete="on"  class="mini-textbox"
                   style="width:150px;display:inline; " type="text">
            ����/����:
            <input id="offName" autocomplete="on"  class="mini-textbox"
                   style="width:150px;display:inline; " type="text">

            <button class="layui-btn" lay-submit lay-filter="formSubmit">������ѯ</button>
        </div>
    </blockquote>
    <div style="margin-left: 5px;width:60%;float: left">
        <table id="manageUser" lay-filter="manageUser"></table>
    </div>

    <div class="layui-tab" style="margin: 1px;width:36%;float: left">
        <ul class="layui-tab-title">
            <li class="layui-this">������Ϣ</li>
            <li>������Ϣ</li>
            <li>��Ա��ɫ</li>
        </ul>
        <div class="layui-tab-content" style="height: 389px!important; ">
            <!--�������༭��Ա-->
            <div class="layui-tab-item layui-show">
                <form class="layui-form layui-form-pane" id="userForm" action="">

                    <input type="hidden" class="layui-input " id="id">
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label"><span style="color: red">*</span>Ա������</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input " lay-verify="required" maxlength="16" id="code">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label"><span style="color: red">*</span>����</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input " lay-verify="required" maxlength="16" id="name">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">����ƴ��</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input "  id="spell">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">�Ա�</label>
                        <div class="layui-input-block" id="sex">
                            <select id="sexSelect" lay-filter="kind" >
                                <option value="0">��</option>
                                <option value="1">Ů</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">����</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input " maxlength="16" id="email">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label"><span style="color: red">*</span>����</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input " id="departName">
                            <input type="hidden" name = "departId" id="departId">
                        </div>

                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label"><span style="color: red">*</span>��������</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input " id="officesName">
                            <input type="hidden" name = "officesId" id="officesId">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">��ϵ��ʽ</label>
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
                        <label class="layui-form-label"><span style="color: red">*</span>����</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input "  lay-verify="number" maxlength="16"  id="sort">
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">״̬</label>
                        <div class="layui-input-block" id="status">
                            <select id="statusSelect" lay-filter="kind" >
                                <option value="1">����</option>
                                <option value="0">����</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">�����¼</label>
                        <div class="layui-input-block" id="isSingleLogin">
                            <select id="isSingleLoginSelect" lay-filter="kind">
                                <option value="1">��</option>
                                <option value="0">��</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item form-div">
                        <label class="layui-form-label">��ע</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input "  maxlength="16" id="memo">
                        </div>
                    </div>

                    <div class="layui-form-item" style="text-align: center;margin-top: 5px;">
                        <a class="layui-btn" lay-filter="addNews" id="addNews" style="display: none" lay-submit>ȷ��</a>
                        <a class="layui-btn" type="reset" style="display: none" id="reset">ȡ��</a>

                    </div>
                </form>
            </div>
            <!--������Ϣ-->
            <div class="layui-tab-item">
                <form class="layui-form layui-form-pane " action="">
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">�Ƿ�����</label>
                        <div class="layui-input-block form-div-input"  id="isOnline">
                            <select id="isOnlineSelect" lay-filter="kind">
                                <option value="0">��</option>
                                <option value="1">��</option>
                            </select>
                        </div>

                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">����¼ʱ��</label>
                        <div class="layui-input-block form-div-input">
                            <input type="text" class="layui-input " disabled id="loginTime">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">����¼IP��ַ</label>
                        <div class="layui-input-block form-div-input">
                            <input type="text" class="layui-input " disabled id="loginIp">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">����¼����</label>
                        <div class="layui-input-block  form-div-input">
                            <input type="text" class="layui-input " disabled id="loginCity">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">����޸�����ʱ��</label>
                        <div class="layui-input-block  form-div-input">
                            <input type="text" class="layui-input " disabled id="lastChangePassWord">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">�Ƿ����</label>
                        <div class="layui-input-block form-div-input"  id="isAudit">
                            <select id="isAuditSelect" lay-filter="kind">
                                <option value="0">��</option>
                                <option value="1">��</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                    <label class="layui-form-label form-label">�����</label>
                    <div class="layui-input-block  form-div-input">
                        <input type="text" class="layui-input " disabled id="auditby">
                    </div>
                </div>
                    <div class="layui-form-item form-div" >
                    <label class="layui-form-label form-label">���ʱ��</label>
                    <div class="layui-input-block  form-div-input">
                        <input type="text" class="layui-input " disabled id="auditTime">
                    </div>
                </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">������</label>
                        <div class="layui-input-block  form-div-input">
                            <input type="text" class="layui-input " disabled id="creator">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">����ʱ��</label>
                        <div class="layui-input-block  form-div-input">
                            <input type="text" class="layui-input " disabled id="createDate" >
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">�༭��</label>
                        <div class="layui-input-block form-div-input">
                            <input type="text" class="layui-input " disabled id="updater">
                        </div>
                    </div>
                    <div class="layui-form-item form-div" >
                        <label class="layui-form-label form-label">�༭ʱ��</label>
                        <div class="layui-input-block form-div-input">
                            <input type="text" class="layui-input " disabled id="updateDate">
                        </div>
                    </div>
                </form>
            </div>
            <!--��Ա��ɫ-->
            <div class="layui-tab-item role"  >
                <a href="javascript:void(0);" class="layui-btn addUserRole" id="addUserRole">�����û���ɫ</a>
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

<!--״̬-->
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
           lay-event="operation">����</a>
    </div>
</script>
</html>