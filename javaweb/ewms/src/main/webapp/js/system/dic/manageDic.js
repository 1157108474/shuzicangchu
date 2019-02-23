layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer;
    form.verify({
        sort: function(value){
            var reg =/^[0-9]*$/;
            if (value != '' && isNaN(value) && !reg.test(value)) {
                return '���������������';
            }
        },
        codeverify: [/^[a-zA-Z0-9|_|-]{3,64}$/, '����ֻ����3��64λӢ���ַ������֡�-��_']

    });
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
            beforeEditName: beforeEditName
//            onRemove: onRemove,
//            onRename: onRename

        }
    };




    function onClick(event, treeId, treeNode, clickFlag) {
        $("input").attr("disabled",true);
        $("select").attr("disabled",true);
        // showLog("[ "+getTime()+" onClick ]&nbsp;&nbsp;clickFlag = " + clickFlag + " (" + (clickFlag===1 ? "��ͨѡ��": (clickFlag===0 ? "<b>ȡ��ѡ��</b>" : "<b>׷��ѡ��</b>")) + ")");
        if(treeNode.id==0) {
            setFormNull();
            $("#name").val('�����ֵ����');
            $("#parentName").val('');
            $("#parentCode").val('');
        }else{
            setForm(treeNode.id);
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
//                if (confirm("����ڵ� -- " + treeNode.name + " �ı༭״̬��")) {
//                    setTimeout(function() {
//                        zTree.editName(treeNode);
//                    }, 0);
        $("input").attr("disabled",false);
        $("#parentCode").attr("disabled",true);
        $("#parentName").attr("disabled",true);
        $("select").attr("disabled",false);
        setForm(treeNode.id);
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
        if(confirm("ȷ��ɾ��  -- " + treeNode.name + " ��")){
            $.ajax({
                type: "DELETE",
                url: "dic/"+ treeNode.id,
                dataType:"json",
                //data: {"id":id},
                success: function (ret) {
                    if (ret.status == 0) {
                        layer.alert('ɾ��ʧ�ܣ�' + ret.message);
                    }else{
                        layer.alert('ɾ���ɹ�');
                        //ɾ��ѡ�е��ӽڵ�
//                        if(treeNode.isParent) {
//                            ztree.removeChildNodes(treeNode);
//                        }
                        ztree.removeNode(treeNode);
                        setFormNull();
                        $("#parentId").val('');
                        $("#parentName").val('');
                        $("#parentCode").val('');
                        return true;
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
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
//               layer.alert("�ڵ����Ʋ���Ϊ��.");
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
        if(treeNode.level!=2) {
            $("input").attr("disabled",false);
            $("#parentCode").attr("disabled",true);
            $("#parentName").attr("disabled",true);
            $("select").attr("disabled",false)
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
    };

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
    };
//    function selectAll() {
//        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//        zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
//    }

    var ran= Math.random();
    function setForm(id) {
        $.ajax({
            type: "GET",
            url: "dic/"+id,
            dataType:"json",
            data: {"ran":ran++},
            success: function (ret) {
                if (ret.status == 0) {
                    layer.alert('��ȡ��Ϣʧ�ܣ�' + ret.message);
                }else{
                    var data = ret.data;
                    $("#id").val(data.id);
                    $("#parentId").val(data.parent.id);
                    $("#parentName").val(data.parent.name);
                    $("#parentCode").val(data.parent.code);
                    $("#name").val(data.name);
                    $("#code").val(data.code);
                    // if(data.status == 0){
                    //     $("#status").html('<option value="1" >����</option><option value="0" selected>����</option>');
                    // }else{
                    //     $("#status").html('<option value="1" selected>����</option><option value="0" >����</option>');
                    //
                    // }
                    $("#status").val(data.status);
                    $("#sort").val(data.sort);
                    $("#memo").val(data.memo);
                    form.render();
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });

    }
    function setFormNull(){
        $("#id").val('');
        $("#name").val('');
        $("#code").val('');
       // $("#status").html('<option value="" ></option><option value="1" >����</option><option value="0" >����</option>');
        $("#status").val('');
        $("#sort").val('');
        $("#memo").val('');
        form.render();
    }
    var ztree;
    $(document).ready(function(){
        ztree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        //   $("#selectAll").bind("click", selectAll);
    });

        $ = layui.jquery;
    form.on("submit(editDic)", function (dic) {
        var type;
        $("#edit").hide();

        if($("#id").val() =='' ){
            type = "POST";
        }else{
            type="PUT";
        }

        $.ajax({
            type: type,
            url: "dic",
            dataType:"json",
            data: JSON.parse(JSON.stringify(dic.field)),
            success: function (ret) {
                if (ret.status == '1') {
                    layer.msg('�ύ�ɹ�', function () {
                        $("#edit").hide();
                    });

                    if(type == "POST"){

                        ztree.addNodes(ztree.getNodeByParam("id", ret.data.parent.id, null), {id:ret.data.id, pId:ret.data.parent.id, name:ret.data.name,code:ret.data.code});
                        //location.reload();
                    }else{
                        var node =  ztree.getNodeByParam("id", ret.data.id, null)
                        node.name = ret.data.name;
                        ztree.updateNode(node);

                    }
                } else {
                    layer.alert('�ύʧ�ܣ�' + ret.message);
                    $("#edit").show();
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                $("#edit").show();
            }
        });
        return false;
    });




});





