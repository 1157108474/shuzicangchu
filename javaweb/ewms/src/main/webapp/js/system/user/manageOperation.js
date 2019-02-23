layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = parent.layer === undefined ? layui.layer : top.layer;
    var form = layui.form;
    var table = layui.table;
    var element = layui.element;
    var setting = {
        view: {
            showIcon: false,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    //��ȡ��ʼ��֯����
    $.ajax({
        type: "post",
        url: "/system/user/publicDepart.json",
        dataType: "json",
        success: function (ret) {
            var treeObj = $.fn.zTree.init($("#treeDemo"), setting, ret);
            //չ��tree
            treeObj.expandAll(true);
        },
        error: function (XMLHttpRequest) {
            layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
        }
    });

    //���¼��ر��
    function Reset(tab, data) {
        table.reload('' + tab + '', {
            data: data
        });
    }

    //����ѡ��ı���
    //checkMoves:�ƶ����ѡ������;dataMoves:�ƶ������������;dataReceives:���ձ����������
    // moveTableId:�ƶ����ݱ��Id��receiveTableId:�������ݱ��Id
    function changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId) {
        var receives = [];
        var moves = [];
        for (var i in dataReceives) {
            receives.push(dataReceives[i]);
        }
        for (var i in checkMoves) {
            receives.push(checkMoves[i]);
        }
        for (var i in dataMoves) {
            var bool = true;
            for (var j in checkMoves) {
                if (checkMoves[j].id == dataMoves[i].id) {
                    var bool = false;
                }
            }
            if (bool) {
                moves.push(dataMoves[i]);
            }
        }
        Reset(receiveTableId, receives);
        Reset(moveTableId, moves);
    }

    //�����б�
    var tableDept = table.render({
        elem: '#manageDept'
        , height: 230 //����Ӧ�߶�
        , method: 'post'
        , page: false   //������ҳ
        , limit: 100   //Ĭ������һҳ
        , limits: [100]  //���ݷ�ҳ��
        , cellMinWidth: 95
        , id: 'manageDeptTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'name', align: 'center', width: 220, title: '��������'}
            , {field: 'code', align: 'center', title: '���ű���'}
        ]]
    });
    //��֯����
    $.ajax({
        type: "GET",
        url: "/system/user/listDeparts.json?id=" + $("#userId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageDeptTable', ret);
        }
    });

    //��֯���������
    $("#addDept").click(function () {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getSelectedNodes();
        if (nodes.length == 1) {
            var node = nodes[0];
            var data1 = [];
            var dataDept = table.cache.manageDeptTable;
            var bool = true;
            for (var i = 0; i < dataDept.length; i++) {
                var dept = dataDept[i];
                data1.push({
                    id: dept.id,
                    name: dept.name,
                    code: dept.code
                });
                if (dept.id == node.id) {
                    bool = false;
                }
            }
            if (bool) {
                data1.push({
                    id: node.id,
                    name: node.name,
                    code: node.code
                });
            }
            Reset('manageDeptTable', data1);
        } else {
            layer.msg("��ѡ��һ�����ڵ�");
        }
    });
    //��֯�����Ƴ���
    $("#removeDept").click(function () {
        var dataDept = table.cache.manageDeptTable;
        var depts = table.checkStatus('manageDeptTable').data;
        for (var i in depts) {
            for (var k in dataDept) {
                if (dataDept[k].id == depts[i].id) {
                    dataDept.splice(k, 1);
                    break;
                }
            }
        }
        //���¼��ر��
        Reset('manageDeptTable', dataDept);
    });

    //�ⷿ����
    //�ⷿ����δ�����б�
    var tableWareHouse = table.render({
        elem: '#manageWareHouse'
        , height: 230 //����Ӧ�߶�
        , method: 'post'
        , cellMinWidth: 95
        , page: false   //������ҳ
        , limit: 100   //Ĭ������һҳ
        , limits: [100]  //���ݷ�ҳ��
        , id: 'manageWareHouseTable'
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '�������'}
            , {field: 'name', align: 'center', width: 190, title: '��������'}
        ]]
    });
    //��ȡ�ⷿ����δ�����б�
    $.ajax({
        type: "GET",
        url: "/system/user/listWareHouses.json?id=" + $("#userId").val() + "&ztId=" + $("#userZtId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageWareHouseTable', ret);
        }
    });
    //�ⷿ�����ѷ����б�
    var tableNewWareHouse = table.render({
        elem: '#manageNewWareHouse'
        , height: 230 //����Ӧ�߶�
        , method: 'post'
        , cellMinWidth: 95
        , page: false   //������ҳ
        , limit: 100   //Ĭ������һҳ
        , limits: [100]  //���ݷ�ҳ��
        , id: 'manageNewWareHouseTable'
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '�������'}
            , {field: 'name', align: 'center', width: 190, title: '��������'}
        ]]
    });
    //��ȡ�ⷿ�����ѷ����б�
    $.ajax({
        type: "GET",
        url: "/system/user/listNewWareHouses.json?id=" + $("#userId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageNewWareHouseTable', ret);

        }
    });

    //�ⷿ���������
    $("#addWareHouse").click(function () {
        var checkMoves = table.checkStatus('manageWareHouseTable').data;
        var dataMoves = table.cache.manageWareHouseTable;
        var dataReceives = table.cache.manageNewWareHouseTable;
        var moveTableId = 'manageWareHouseTable';
        var receiveTableId = 'manageNewWareHouseTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });
    //�ⷿ����ɾ����
    $("#removeWareHouse").click(function () {
        var checkMoves = table.checkStatus('manageNewWareHouseTable').data;
        var dataMoves = table.cache.manageNewWareHouseTable;
        var dataReceives = table.cache.manageWareHouseTable;
        var moveTableId = 'manageNewWareHouseTable';
        var receiveTableId = 'manageWareHouseTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });

    //���Ϸ�Χ
    //���Ϸ�Χδ�����б�
    var tableSparepart = table.render({
        elem: '#manageSparepart'
        , height: 230 //����Ӧ�߶�
        , method: 'post'
        , data: []
        , page: false   //������ҳ
        , limit: 100   //Ĭ��15������һҳ
        , cellMinWidth: 95
        , id: 'manageSparepartTable'
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '�������'}
            , {field: 'name', align: 'center', width: 180, title: '��������'}
        ]]
    });
    //��ȡ���Ϸ�Χδ�����б�
    $.ajax({
        type: "GET",
        url: "/system/user/listSpareparts.json?id=" + $("#userId").val() + "&ztId=" + $("#userZtId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageSparepartTable', ret);
        }
    });
    //���Ϸ�Χ�ѷ����б�
    var tableNewSparepart = table.render({
        elem: '#manageNewSparepart'
        , height: 230 //����Ӧ�߶�
        , method: 'post'
        , page: false   //������ҳ
        , limit: 100   //Ĭ������һҳ
        , limits: [100]  //���ݷ�ҳ��
        , cellMinWidth: 95
        , id: 'manageNewSparepartTable'
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '�������'}
            , {field: 'name', align: 'center', width: 180, title: '��������'}
        ]]
    });
    //��ȡ���Ϸ�Χ�ѷ����б�
    $.ajax({
        type: "GET",
        url: "/system/user/listNewSpareparts.json?id=" + $("#userId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageNewSparepartTable', ret);
        }
    });

    //���Ϸ�Χ�����
    $("#addSparepart").click(function () {
        var checkMoves = table.checkStatus('manageSparepartTable').data;
        var dataMoves = table.cache.manageSparepartTable;
        var dataReceives = table.cache.manageNewSparepartTable;
        var moveTableId = 'manageSparepartTable';
        var receiveTableId = 'manageNewSparepartTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });
    //���Ϸ�Χɾ����
    $("#removeSparepart").click(function () {
        var checkMoves = table.checkStatus('manageNewSparepartTable').data;
        var dataMoves = table.cache.manageNewSparepartTable;
        var dataReceives = table.cache.manageSparepartTable;
        var moveTableId = 'manageNewSparepartTable';
        var receiveTableId = 'manageSparepartTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });


    //��Ͻ����
    //��Ͻ����δ�����б�
    var tableUseDep = table.render({
        elem: '#manageUseDep'
        , height: 230 //����Ӧ�߶�
        , method: 'post'
        , cellMinWidth: 95
        , page: false   //������ҳ
        , limit: 100   //Ĭ������һҳ
        , limits: [100]  //���ݷ�ҳ��
        , id: 'manageUseDepTable'
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '�������'}
            , {field: 'name', align: 'center', width: 180, title: '��������'}
        ]]
    });
    //��ȡ��Ͻ����δ�����б�
    $.ajax({
        type: "post",
        url: "/system/user/listUseDeps.json?id=" + $("#userId").val() + "&ztId=" + $("#userZtId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageUseDepTable', ret);
        }
    });
    //��Ͻ�����ѷ����б�
    var tableNewUseDep = table.render({
        elem: '#manageNewUseDep'
        , height: 230 //����Ӧ�߶�
        , method: 'post'
        , cellMinWidth: 95
        , id: 'manageNewUseDepTable'
        , page: false   //������ҳ
        , limit: 100   //Ĭ������һҳ
        , limits: [100]  //���ݷ�ҳ��
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '�������'}
            , {field: 'name', align: 'center', width: 180, title: '��������'}
        ]]
    });
    //��ȡ��Ͻ�����ѷ����б�
    $.ajax({
        type: "GET",
        url: "/system/user/listNewUseDeps.json?id=" + $("#userId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageNewUseDepTable', ret);
        }
    });
    //��Ͻ���������
    $("#addUseDep").click(function () {
        var checkMoves = table.checkStatus('manageUseDepTable').data;
        var dataMoves = table.cache.manageUseDepTable;
        var dataReceives = table.cache.manageNewUseDepTable;
        var moveTableId = 'manageUseDepTable';
        var receiveTableId = 'manageNewUseDepTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });
    //��Ͻ����ɾ����
    $("#removeUseDep").click(function () {
        var checkMoves = table.checkStatus('manageNewUseDepTable').data;
        var dataMoves = table.cache.manageNewUseDepTable;
        var dataReceives = table.cache.manageUseDepTable;
        var moveTableId = 'manageNewUseDepTable';
        var receiveTableId = 'manageUseDepTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });

    //ƴװids
    function assemblingIds(datas) {
        var ids = [];
        for (var i in datas) {
            ids.push(datas[i].id);
        }
        return ids;
    }

    //����
    form.on("submit(addNews)", function (data) {
        var depts = assemblingIds(table.cache.manageDeptTable);
        var spareparts = assemblingIds(table.cache.manageNewSparepartTable);
        var wareHouses = assemblingIds(table.cache.manageNewWareHouseTable);
        var useDeps = assemblingIds(table.cache.manageNewUseDepTable);
        var json = {
            id: $("#userId").val()  //Id
            , ztId: $("#userZtId").val()  //ZTID
            , guId: $("#userGuId").val() //GUID
        };

        var str = "?depts=" + depts + "&spareparts=" + spareparts + "&wareHouses=" + wareHouses + "&useDeps=" + useDeps;
        var url = "/system/user/saveUserScopes.json" + str;
        $.post(url, json, function (data) {
            top.layer.msg(data.message, {
                time: 500
            });
            if ('1' == data.status) {
                parent.location.reload();
            }
        });
    });

});