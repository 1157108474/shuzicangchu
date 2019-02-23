layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer;
    var setting = {
        view: {
            showIcon:false,
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        edit: {
            enable: true,
            //editNameSelectAll: true,
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
            beforeEditName: beforeEditName,
//            onRemove: onRemove,
//            onRename: onRename,
//            beforeAsync: zTreeBeforeAsync,      // 异步加载事件之前得到相应信息
//            onAsyncSuccess: zTreeOnAsyncSuccess, //异步加载成功的fun
            onAsyncError: zTreeOnAsyncError    //加载错误的fun

        }
        ,async: {
            enable: true,
            url:"spare/spares",
            autoParam:["id","level"],
//            otherParam:{"ran":ran++},
            dataType: "json",//默认text
            type:"get",//默认post
            dataFilter: filter  //异步返回后经过Filter
        }
    };
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
//        for (var i=0, l=childNodes.length; i<l; i++) {
//            childNodes[i].name = childNodes[i].name.replace('','');
//        }

        return childNodes;
    }
    function zTreeOnAsyncError(event, treeId, treeNode){
        layer.alert("加载失败,请稍候重试!");
    }

//    function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
//        alert("异步加载成功!");
//    }

    function onClick(event, treeId, treeNode, clickFlag) {
        $("input").attr("disabled",true);

        $("select").attr("disabled",true);
        // showLog("[ "+getTime()+" onClick ]&nbsp;&nbsp;clickFlag = " + clickFlag + " (" + (clickFlag===1 ? "普通选中": (clickFlag===0 ? "<b>取消选中</b>" : "<b>追加选中</b>")) + ")");
        if(treeNode.id==0) {
            setFormNull();
            $("#name").val('物料分类');

            $("#parentName").val('');
            $("#parentCode").val('');
        }else{
            setForm(treeNode.id,treeNode.level);
            $("#edit").hide();
        }
    }
//    var log, className = "dark";
//    function beforeDrag(treeId, treeNodes) {
//        return false;
//    }
    function beforeEditName(treeId, treeNode) {

//            className = (className === "dark" ? "":"dark");
//            showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
//            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//            zTree.selectNode(treeNode);
//            setTimeout(function() {
//                if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
//                    setTimeout(function() {
//                        zTree.editName(treeNode);
//                    }, 0);
        $("input").attr("disabled",false);
        $("#parentName").attr("disabled",true);
        $("#parentCode").attr("disabled",true);
        $("select").attr("disabled",false);
        setForm(treeNode.id,treeNode.level);
        $("#edit").show();
//                }
//            }, 0);
        return false;
    }
    function beforeRemove(treeId, treeNode) {
//        className = (className === "dark" ? "":"dark");
//        showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
//        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//        zTree.selectNode(treeNode);
        if(confirm("确认删除  -- " + treeNode.name + " 吗？")){
            $.ajax({
                type: "DELETE",
                url: "spare/"+ treeNode.id,
                dataType:"json",
                //data: {"id":id},
                success: function (ret) {
                    if (ret.status == 0) {
                        layer.alert('删除失败：' + ret.message);
                    }else{
                        layer.alert('删除成功');
                        //删除选中的子节点
//                        if(treeNode.isParent) {
//                            ztree.removeChildNodes(treeNode);
//                        }
                        ztree.removeNode(treeNode);
                        return true;
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            });
        }

        return  false;
    }
//    function onRemove(e, treeId, treeNode) {
//        showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
//    }
//    function beforeRename(treeId, treeNode, newName, isCancel) {
//        className = (className === "dark" ? "":"dark");
//        showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
//        if (newName.length == 0) {
//            setTimeout(function() {
//                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//                zTree.cancelEditName();
//                alert("节点名称不能为空.");
//            }, 0);
//            return false;
//        }
//        setForm(treeNode.id);
//        $("#edit").show();
//        return true;
//    }
//    function onRename(e, treeId, treeNode, isCancel) {
//        showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
//    }
    function showRemoveBtn(treeId, treeNode) {
        return treeNode.id != 0;
    }
    function showRenameBtn(treeId, treeNode) {
//        return !treeNode.isLastNode;
        return treeNode.id != 0 ;
    }
//    function showLog(str) {
//        if (!log) log = $("#log");
//        log.append("<li class='"+className+"'>"+str+"</li>");
//        if(log.children("li").length > 8) {
//            log.get(0).removeChild(log.children("li")[0]);
//        }
//    }
//    function getTime() {
//        var now= new Date(),
//            h=now.getHours(),
//            m=now.getMinutes(),
//            s=now.getSeconds(),
//            ms=now.getMilliseconds();
//        return (h+":"+m+":"+s+ " " +ms);
//    }


    function addHoverDom(treeId, treeNode) {
        if(treeNode.level!=3) {
            $("input").attr("disabled",false);
            $("#parentName").attr("disabled",true);
            $("#parentCode").attr("disabled",true);
            $("select").attr("disabled",false);
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='add node' onfocus='this.blur();' ></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_"+treeNode.tId);
            if (btn) btn.bind("click", function(){
//            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//            zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                setFormNull();
                $("#parentId").val(treeNode.id);
                $("#parentName").val(treeNode.name);
                $("#parentCode").val(treeNode.code);
                $("#edit").show();

                return false;
            });
        }
    }

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
    }
//    function selectAll() {
//        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//        zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
//    }



    var ran= Math.random();
    function setForm(id,level) {
        $.ajax({
            type: "GET",
            url: "spare/"+id +"-"+level,
            dataType:"json",
            data: {"ran":ran++},
            success: function (ret) {
                if (ret.status == 0) {
                    layer.alert('获取信息失败：' + ret.message);
                }else{
                    var data = ret.data;

                    $("#id").val(data.id);

                    if(level>1) {
                        $("#parentId").val(data.parentId);
                        $("#parentName").val(data.parentName);
                        $("#parentCode").val(data.parentCode);
                    }else{
                        $("#parentId").val(0);
                        $("#parentName").val("物料分类");
                        $("#parentCode").val('');
                    }
                    $("#name").val(data.name);
                    $("#code").val(data.code);
                  /*  if(data.status == 0){
                        $("#status").html('<option value="1" >启用</option><option value="0" selected>禁用</option>');
                    }else{
                        $("#status").html('<option value="1" selected>启用</option><option value="0" >禁用</option>');

                    }*/

                    $("#status").val(data.status);
                    $("#sort").val(data.sort);
                    $("#memo").val(data.memo);
                    form.render();
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });

    }
    function setFormNull(){
        $("#id").val('');
        $("#name").val('');
        $("#code").val('');
        $("#status").html('<option value="" ></option><option value="1" >启用</option><option value="0" >禁用</option>');
        $("#sort").val('');
        $("#memo").val('');
        form.render();
    }
    var ztree;

    $(document).ready(function(){
//        $.ajax({
//            type: "GET",
//            url: "spare/spares",
//            dataType:"json",
//            data: {ran:ran++,id:0},
//            success: function (ret) {
//
//                if (ret == null) {
//                    layer.alert('获取物料分类信息失败' );
//                } else {

        ztree = $.fn.zTree.init($("#treeDemo"), setting);
        //ret.data);
//                }
//            },
//            error: function (XMLHttpRequest) {
//                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
//            }
//        });


    });
    $ = layui.jquery;
    form.verify({
        sort: function(value){
            var reg =/^[0-9]*$/;
            if (value != '' && isNaN(value) && !reg.test(value)) {
                return '排序必须是正整数';
            }
        },
        codeverify: [/^[a-zA-Z0-9]{1,64}$/, '编码只能是1到64位英文字符或数字']
    });
    form.on("submit(editspare)", function (spare) {
        var type;
        $("#edit").hide();

        if($("#id").val() =='' ){
            type = "POST";
        }else{
            type="PUT";
        }

        $.ajax({
            type: type,
            url: "spare",
            dataType:"json",
            data: JSON.parse(JSON.stringify(spare.field)),
            success: function (ret) {
                if (ret.status == '1') {
                    layer.msg('提交成功', function () {
                        $("#edit").hide();
                    });
                    if(type == "POST"){
                        ztree.addNodes(ztree.getNodeByParam("id", ret.data.parentId, null), {id:ret.data.id, pId:ret.data.parentId, name:ret.data.name,code:ret.data.code});
                        //location.reload();
                    }else{
                        var node =  ztree.getNodeByParam("id", ret.data.id, null)
                        node.name = ret.data.name;
                        ztree.updateNode(node);

                    }
                } else {
                    layer.alert('提交失败：' + ret.message);
                    $("#edit").show();
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                $("#edit").show();
            }
        });
        return false;
    });




});




