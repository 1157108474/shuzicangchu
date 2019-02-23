layui.config({
    base: '/js/static/' // 模块目录
}).use(['form', 'table', 'layer', 'vip_table', 'upload'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;
    var upload = layui.upload;
    var form = layui.form;
    //明细列表
    var detailsgrid = table.render({
        elem: '#detailsgrid'
        , url: 'wzpdDetail.json'
        ,where:{sheetId:$("#id").val()}
        , cellMinWidth: 80
        , height: "full-200"
        , method: 'POST'
        , page: true   //开启分页
        , limit: 10   //默认十五条数据一页
        , limits: [10, 20 ,30]  //数据分页条
        ,id: 'detailsgridReload'
        , cols: [
            [{type: "checkbox", fixed: "left", width: 30}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 120}
                , {field: 'description', title: '物料描述', align: "center", width: 150}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 80}
                /*, {field: 'storeLocationName', title: '库房', align: "center", width: 70}*/
                , {field: 'storeLocationName', title: '库位', align: "center", width: 80}
                , {field: 'sysCount', title: '库存数量', align: "center",width: 100}
                , {field: 'detailCount', title: '盘点数量', align: "center",width: 100}
                , {field: 'stockDate', title: '盘点日期', align: "center", width: 100,
                    templet: function (d) {
                        return dateformat(d.stockDate)
                    }
                }
                , {field: 'extendString1', title: '盘点人', align: "center", width: 80}
                , {field: 'stockStatus', title: '盘点状态', align: "center", width: 100,
                    templet: function (d) {
                        if(d.stockStatus==1){return "已盘";}
                        if(d.stockStatus==0){return "未盘";}
                    }
                }
                , {field: 'stockResult', title: '盘点结果', align: "center", width: 100,
                    templet: function (d) {
                        if(d.stockResult==1){return "盘盈";}
                        if(d.stockResult==-1){return "盘亏";}
                        if(d.stockResult==0){return "正常";}
                    }
                }
                , {field: 'memo', title: '备注', align: "center" , width: 100}
                , { title: '操作', align: "center", width: 100, toolbar: '#barDemo2'}
               /* , { title: '添加盘盈数据', align: "center", width: 100, toolbar: '#barDemo3'}*/
            ]
        ]
    });

    var reload =function(){
        table.reload('detailsgridReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where:{
                sheetId:$("#id").val()
            }

        })
    };
    //保存
    $("#saveSheet").on("click", function (e) {
        var result;
        var id = $("#id").val();

        if( id==""){
            saveSheet(1);
        }else{
            updateSheet(id);
        }

    });

    $("#exprotPdResult").on("click", function (e) {
        var data = table.cache.detailsgridReload;
        if(data.length<=0){
            layer.alert('没有盘点明细可以导出');
        }else{
            location.href = '/sheet/pd/expKcpdResult.json?id='+$("#id").val();
            /*$.post("expKcpdResult.json",{'id':$("#id").val()},
                function(data) {
                    if(data){
                        layer.msg('导出文件D:/盘点结果.xls成功！');
                    }
                },"JSON");*/
        }
    });
    //文件上传
    upload.render({
        elem: '#importPdResult'
        , url: '/sheet/pd/importPdResult.json'
        , exts: 'xls|xlsx' //Excel
        , done: function (res) {
            layer.msg(res.msg);
            reload();
        }
    });

    //添加明细
    $("#addDetails").on("click", function (e) {
        if($("#id").val()==""){
            //open();
            saveSheet(2)
            //layer.alert("请先保存单据");
        }else {
            open();
        }
    });
    $("#deleteDetails").on("click", function () {
        var checkStatus = table.checkStatus('detailsgridReload');
        var data = checkStatus.data;
        if (data.length < 1  ){
            //正上方
            layer.alert('请至少选择一条记录');
        }else{
            var ids = [];
            for (var i = 0; i < data.length; i++) {
                ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的明细？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("deletePDDetail.json",{'ids':JSON.stringify(ids)},
                    function(data) {
                        if(data.status == '1'){
                            reload();
                            layer.alert('明细删除成功');
                        }else{
                            layer.alert('明细删除失败');
                        }
                    },"JSON");
            })
        }
    });

    var open= function(){   //新增明细页面
        var url = "";
        if ($("#extendInt1").val().indexOf('寄售库') != "-1") {
            url = "getjspdStock.htm?extendInt1="+$("#extendInt1").val()+"&&extendInt2="+$("#extendInt2").val();
        }else{
            url = "getxhpdStock.htm?extendInt1="+$("#extendInt1").val()+"&&extendInt2="+$("#extendInt2").val();
        }
        /*if(extendInt2 == 250){//循环盘点
            url = "getxhpdStock.htm";
        }else{//期末盘点
            url = "getjspdStock.htm";
        }*/
        layer.open({
            title: "添加明细"
            , type: 2
            , fixed: false
            , area: ["90%", "90%"]
            , content: url
            , end: function () {
                if($("#reloadDetailsgrid").val()==1) {
                    reload();
                }
            }
        })
    }

    var saveSheet =function(isOpen){
        var extendInt1 = ($("#extendInt1").val()).split("-")[0];
        var extendString1 = $("#extendString1").val();
        var extendInt2 = $("#extendInt2").val();
        var memo = $("#memo").val();
        if(extendInt2==0){
            layer.alert('请选择盘点类型');
            return;
        }else if(extendInt1 == 0){
            layer.alert('请选择库房');
            return;
        }else if(extendString1 == ""){
            layer.alert('请填写盘点名称');
            return;
        }else{
            var loading = layer.msg("保存中", {
                icon: 16,
                shade: 0.2,
                time: false,
                offset: 't'
            });
            $.ajax({
                type: "post",
                url: "/sheet/KCPD",
                dataType: "json",
                data: {"menuCode":$("#menuCode").val(),"extendInt1":extendInt1,"extendString1":extendString1,"extendInt2":extendInt2,"memo":memo},
                success: function (ret) {
                    layer.close(loading);
                    if (ret.status == '1') {
                        $("#id").val(ret.data.id);
                        $("#code").html(ret.data.code);
                        $("#taskId").val(ret.data.taskId);
                        //layer.alert("保存单据成功");
                        reloadHis();
                        if(isOpen == 1){
                            layer.alert('保存单据成功');
                        }else{
                            open();
                        }
                        hide();
                    } else {
                        layer.alert('保存单据失败：' + ret.message);
                        return false;
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("保存单据请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                    return false;
                }
            });
        }

    };
    function hide() {
        //库房
        $("#extendInt1").addClass("layui-disabled");
        $("#extendInt1").attr("disabled", true);
        //盘点名称
        $("#extendString1").addClass("layui-disabled");
        $("#extendString1").attr("disabled", true);
        //库房
        $("#extendInt2").addClass("layui-disabled");
        $("#extendInt2").attr("disabled", true);
        //备注
        $("#memo").addClass("layui-disabled");
        $("#memo").attr("disabled", true);
        form.render('select'); //重新渲染select，这个很重要
    }
    var updateSheet =function(id){
        var extendInt1 = ($("#extendInt1").val()).split("-")[0];
        var extendString1 = $("#extendString1").val();
        var extendInt2 = $("#extendInt2").val();
        var memo = $("#memo").val();
        if(extendInt2==0){
            layer.alert('请选择盘点类型');
            return;
        }else if(extendInt1 == 0){
            layer.alert('请选择库房');
            return;
        }else if(extendString1 == ""){
            layer.alert('请填写盘点名称');
            return;
        }else{
            var loading = layer.msg("保存中", {
                icon: 16
                , shade: 0.2,
                time: false,
                offset: 't'
            });
            $.ajax({
                type: "put",
                url: "KCPDSheetEdit.json",
                dataType: "json",
                data: {"menuCode":$("#menuCode").val(),"id":id,"extendInt1":extendInt1,"extendString1":extendString1,"extendInt2":extendInt2,"memo":memo},
                success: function (ret) {
                    layer.close(loading);
                    if (ret.status == '1') {
                       /* $("#id").val(ret.data.id);
                        $("#code").html(ret.data.code);
                        $("#taskId").val(ret.data.taskId);*/
                        layer.alert('修改成功')
                    } else {
                        layer.alert('保存单据失败：' + ret.message);
                        return false;
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("保存单据请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            });
        }
    };
    form.on('select(extendInt1)', function(data){
        if(data.value == 0){
            $("#extendString1").val("");
        }else{
            $("#extendString1").val(data.value.split("-")[1]+"盘点");
        }

    });
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            layer.open({
                title: "修改明细"
                , type: 2
                , fixed: false
                , area: ["30%", "50%"]
                , content: "editDetailCount.htm?id="+data.id+"&&sysCount="+data.sysCount
                , end: function () {
                        reload();
                }
            })
        }
        if (obj.event === 'addPY') {
            layer.open({
                title: "添加盘盈数据"
                , type: 2
                , fixed: false
                , area: ["70%", "80%"]
                , content: "addPY.htm?id="+data.id
                , end: function () {
                    reload();
                }
            })
        }
    })
});

