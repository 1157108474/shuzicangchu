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
    <title>流程名称</title>
    <link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="<%=path %>/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">

    <style type="text/css">
        .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
    </style>
</head>
<body>

<div class="admin-main">

    <div  style="margin:10px 10px;height:100%;"  >
        <div  style="margin-left:15%;border:1px solid #0085cf;width:70%;position:absolute; height:80%; overflow-y:auto"  >
            <ul id="treeDemo" class="ztree"></ul>
        </div>
        <input  value="" id="tem_dicid" type="hidden">
        <div  style="margin-left:32%;bottom: 10px!important;position:absolute;">
            <a class="layui-btn"  id="addNews" >确定</a>
            <a class="layui-btn" type="reset"  id="reset"  >取消</a>

        </div>
    </div>

</div>
</body>
<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>


<script>
    var setting = {
        view: {
            showIcon:false,
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        edit: {
            enable: true,
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
        {id: '0',pid:'', name: '流程名称', open: true}
        <c:forEach items="${requestScope.actDics}" var="actDics">
        , {id: '${actDics.id}', pId: '0', name: '${actDics.name}',level:1}
        </c:forEach>
    ];

    function onClick(event, treeId, treeNode, clickFlag) {

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
    }
    function removeHoverDom(treeId, treeNode) {
    }

    $("#addNews").click(function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getSelectedNodes();
        if (nodes.length == 1) {
            var data =nodes[0];
            if(data.name == "流程名称"){
                alert("不能选择父节点");
                return;
            }
            window.parent.obtainPart(data.id,data.name);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        } else {
            alert("请选择一个节点");
        }
    });

    $("#reset").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

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