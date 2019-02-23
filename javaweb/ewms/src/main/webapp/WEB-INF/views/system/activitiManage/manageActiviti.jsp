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
    <title>流程管理</title>
    <link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="<%=path %>/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">

    <style type="text/css">
        .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
    </style>
</head>
<body>

<div class="admin-main">

    <div  style="margin:10px 10px;height:100%;"  >
        <%--<div  style="float:left;border:1px solid #617775;width:20%;height:100%;margin-top: 10px"  >
            <ul id="treeDemo" class="ztree"></ul>
        </div>--%>
            <div class="ztree-out" style="width:180px;height:90%;"  >
                <div class="mini-toolbar">&nbsp;&nbsp;业务流程</div>
                <div class="ztree-in">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        <input  value="" id="act_dicid" type="hidden"><input  value="" id="act_dicName" type="hidden">
        <div  class="ztree-right" style="margin-left: 185px;height:100%;">
            <blockquote class="layui-elem-quote" style="margin-top: 10px;">

                <a href="javascript:void(0)" class="layui-btn" id="add">新增</a>
                <a href="javascript:void(0)" class="layui-btn" id="deploy">部署</a>
                <a href="javascript:void(0)" class="layui-btn" id="edit">修改</a>
                <%--<a href="javascript:void(0)" class="layui-btn" id="delete">删除</a>--%>
                <a href="javascript:void(0)" class="layui-btn" id="current">刷新</a>
            </blockquote>
            <table  id="manageModel" lay-filter="manageModel" style="margin-top: -5px;"></table>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="<%=path %>/js/system/act/manageAct.js"></script>
<script>
    var setting = {
        view: {
            showIcon:false,
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        edit: {
            enable: false,
            showRemoveBtn: showRemoveBtn,
            showRenameBtn: showRenameBtn
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick,
            beforeRemove: beforeRemove,
            beforeEditName: beforeEditName
        }
    };
    var zNodes = [
    {id: '0',pid:'', name: '业务流程', open: true}
    <c:forEach items="${tree}" var="father" varStatus="f">
    , {id: '${father.id}', pId: '0', name: '${father.name}',code:'${father.code}',level:1}
    </c:forEach>
    ];

    function onClick(event, treeId, treeNode, clickFlag) {
        if(treeNode.id==0) {
            setFormNull();
            $("#name").val('业务流程');
            $("#parentName").val('');
            $("#parentCode").val('');
        }else{
            $("#act_dicid").val(treeNode.id);
            $("#act_dicName").val(treeNode.name);
            clickDic();
        }
    }
    function beforeEditName(treeId, treeNode) {
    }
    function beforeRemove(treeId, treeNode) {
    }
    function showRemoveBtn(treeId, treeNode) {
    }
    function showRenameBtn(treeId, treeNode) {
    }
    function addHoverDom(treeId, treeNode) {
    };
    function removeHoverDom(treeId, treeNode) {
    };
    function setForm(id) {
    }
    function setFormNull(){
        $("#id").val('');
        $("#name").val('');
        $("#code").val('');
        $("#status").val('');
        $("#sort").val('');
        $("#memo").val('');
    }

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });

</script>
</html>