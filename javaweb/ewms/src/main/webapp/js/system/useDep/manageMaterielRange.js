layui.use(['laydate', 'form', 'table', 'layer','element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var id = $("#useId").val();
    var ztId = $("#ztId").val();

    //重载表格
    function Reset(tab,data) {
        table.reload(''+tab+'', {
            data:data
        });
    }

    var table1 = table.render({
        elem:"#table1",
        height:"full-55",
        method:"post",
        id:"table1",
        page:true,
        limit:60,
        cols :[[
            {type:'checkbox', fixed: 'left',field:'id', align:'center',width:'5%'},
            {field:'code',  fixed: 'left', align:'center',title:'分类编码',width:'45%'},
            {field:'name',fixed: 'left', align:'center',title:'分类名称',width:'45%'}
        ]]
    });

    var table2 = table.render({
        elem:"#table2",
        height:"full-55",
        method:"post",
        id:"table2",
        page:true,
        limit:60,
        cols :[[
            {type:'checkbox', fixed: 'left',field:'id', align:'center',width:'5%'},
            {field:'code',  fixed: 'left', align:'center',title:'已选择的分类编码',width:'45%'},
            {field:'name',fixed: 'left', align:'center',title:'已选择的分类名称',width:'45%'}
        ]]
    });

    //加载列表数据
    $.ajax({
        type:"GET",
        url:"officesScopeList.json?id="+id,
        dataType:"json",
        success:function(ret){
            Reset("table1",ret.scopeList);
            Reset("table2",ret.officesList);
        }
    });



    //左右选择改变行
    //checkMoves:移动表格选中数据;dataMoves:移动表格所有数据;dataReceives:接收表格所有数据
    // moveTableId:移动数据表格Id；receiveTableId:接收数据表格Id
    function changeLine(checkMoves,dataMoves,dataReceives,moveTableId,receiveTableId) {
        var receives=[];
        var moves=[];
        for(var i in dataReceives){
            receives.push(dataReceives[i]);
        }
        for(var i in checkMoves){
            receives.push(checkMoves[i]);
        }
        for(var i in dataMoves){
            var bool=true;
            for(var j in checkMoves){
                if(checkMoves[j].id==dataMoves[i].id){
                    var bool=false;
                }
            }
            if(bool){
                moves.push(dataMoves[i]);
            }
        }
        Reset(receiveTableId,receives);
        Reset(moveTableId,moves);
    }

    //添加行
    $("#insert").on("click",function(e){
        var checkMoves = table.checkStatus('table1').data;
        var dataMoves = table.cache.table1;
        var dataReceives = table.cache.table2;
        var moveTableId ='table1';
        var receiveTableId ='table2';
        if(checkMoves == ""){
            layer.msg("请选择至少一条数据")
        }else{
            changeLine(checkMoves,dataMoves,dataReceives,moveTableId,receiveTableId);
        }
    });

    //移除行
    $("#delete").on("click",function(e){
        var checkMoves = table.checkStatus('table2').data;
        var dataMoves = table.cache.table2;
        var dataReceives = table.cache.table1;
        var moveTableId ='table2';
        var receiveTableId ='table1';
        if(checkMoves == ""){
            layer.msg("请选择至少一条数据")
        }else{
            changeLine(checkMoves,dataMoves,dataReceives,moveTableId,receiveTableId);
        }
    });

    //拼装ids
    function assemblingIds(datas) {
        var ids = [];
        for (var i in datas){
            ids.push(datas[i].id);
        }
        return ids;
    }

    // commit
    $("#addNews").on("click",function(e){

        var ids = assemblingIds(table.cache.table2);

        // alert - Loading
        var loading = layer.msg("提交中",{
            icon: 16
            ,shade: 0.2,
            time: false,
            offset: 't'
        });

        // url
        var url = "saveMaterielRange.json?ids="+ids+"&id="+id+"&ztId="+ztId;

        // post
        $.post(url,function (data) {
            if (data.status == 1) {
                layer.close(loading);
                layer.msg("提交成功",function(){
                    location.href = "showMaterielRange.htm?id="+id;
                });
            } else {
                layer.alert("保存失败");
            }
        });

    });


});
