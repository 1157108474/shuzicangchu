layui.use(['laydate', 'form', 'table', 'layer','element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var id = $("#useId").val();
    var ztId = $("#ztId").val();

    //���ر��
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
            {field:'code',  fixed: 'left', align:'center',title:'�������',width:'45%'},
            {field:'name',fixed: 'left', align:'center',title:'��������',width:'45%'}
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
            {field:'code',  fixed: 'left', align:'center',title:'��ѡ��ķ������',width:'45%'},
            {field:'name',fixed: 'left', align:'center',title:'��ѡ��ķ�������',width:'45%'}
        ]]
    });

    //�����б�����
    $.ajax({
        type:"GET",
        url:"officesScopeList.json?id="+id,
        dataType:"json",
        success:function(ret){
            Reset("table1",ret.scopeList);
            Reset("table2",ret.officesList);
        }
    });



    //����ѡ��ı���
    //checkMoves:�ƶ����ѡ������;dataMoves:�ƶ������������;dataReceives:���ձ����������
    // moveTableId:�ƶ����ݱ��Id��receiveTableId:�������ݱ��Id
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

    //�����
    $("#insert").on("click",function(e){
        var checkMoves = table.checkStatus('table1').data;
        var dataMoves = table.cache.table1;
        var dataReceives = table.cache.table2;
        var moveTableId ='table1';
        var receiveTableId ='table2';
        if(checkMoves == ""){
            layer.msg("��ѡ������һ������")
        }else{
            changeLine(checkMoves,dataMoves,dataReceives,moveTableId,receiveTableId);
        }
    });

    //�Ƴ���
    $("#delete").on("click",function(e){
        var checkMoves = table.checkStatus('table2').data;
        var dataMoves = table.cache.table2;
        var dataReceives = table.cache.table1;
        var moveTableId ='table2';
        var receiveTableId ='table1';
        if(checkMoves == ""){
            layer.msg("��ѡ������һ������")
        }else{
            changeLine(checkMoves,dataMoves,dataReceives,moveTableId,receiveTableId);
        }
    });

    //ƴװids
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
        var loading = layer.msg("�ύ��",{
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
                layer.msg("�ύ�ɹ�",function(){
                    location.href = "showMaterielRange.htm?id="+id;
                });
            } else {
                layer.alert("����ʧ��");
            }
        });

    });


});
