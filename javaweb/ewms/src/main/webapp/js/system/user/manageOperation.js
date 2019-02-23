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
    //获取初始组织机构
    $.ajax({
        type: "post",
        url: "/system/user/publicDepart.json",
        dataType: "json",
        success: function (ret) {
            var treeObj = $.fn.zTree.init($("#treeDemo"), setting, ret);
            //展开tree
            treeObj.expandAll(true);
        },
        error: function (XMLHttpRequest) {
            layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
        }
    });

    //重新加载表格
    function Reset(tab, data) {
        table.reload('' + tab + '', {
            data: data
        });
    }

    //左右选择改变行
    //checkMoves:移动表格选中数据;dataMoves:移动表格所有数据;dataReceives:接收表格所有数据
    // moveTableId:移动数据表格Id；receiveTableId:接收数据表格Id
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

    //部门列表
    var tableDept = table.render({
        elem: '#manageDept'
        , height: 230 //自适应高度
        , method: 'post'
        , page: false   //开启分页
        , limit: 100   //默认数据一页
        , limits: [100]  //数据分页条
        , cellMinWidth: 95
        , id: 'manageDeptTable'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'name', align: 'center', width: 220, title: '部门名称'}
            , {field: 'code', align: 'center', title: '部门编码'}
        ]]
    });
    //组织机构
    $.ajax({
        type: "GET",
        url: "/system/user/listDeparts.json?id=" + $("#userId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageDeptTable', ret);
        }
    });

    //组织机构添加行
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
            layer.msg("请选择一个父节点");
        }
    });
    //组织机构移除行
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
        //重新加载表格
        Reset('manageDeptTable', dataDept);
    });

    //库房库区
    //库房库区未分配列表
    var tableWareHouse = table.render({
        elem: '#manageWareHouse'
        , height: 230 //自适应高度
        , method: 'post'
        , cellMinWidth: 95
        , page: false   //开启分页
        , limit: 100   //默认数据一页
        , limits: [100]  //数据分页条
        , id: 'manageWareHouseTable'
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '分类编码'}
            , {field: 'name', align: 'center', width: 190, title: '分类名称'}
        ]]
    });
    //获取库房库区未分配列表
    $.ajax({
        type: "GET",
        url: "/system/user/listWareHouses.json?id=" + $("#userId").val() + "&ztId=" + $("#userZtId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageWareHouseTable', ret);
        }
    });
    //库房库区已分配列表
    var tableNewWareHouse = table.render({
        elem: '#manageNewWareHouse'
        , height: 230 //自适应高度
        , method: 'post'
        , cellMinWidth: 95
        , page: false   //开启分页
        , limit: 100   //默认数据一页
        , limits: [100]  //数据分页条
        , id: 'manageNewWareHouseTable'
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '分类编码'}
            , {field: 'name', align: 'center', width: 190, title: '分类名称'}
        ]]
    });
    //获取库房库区已分配列表
    $.ajax({
        type: "GET",
        url: "/system/user/listNewWareHouses.json?id=" + $("#userId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageNewWareHouseTable', ret);

        }
    });

    //库房库区添加行
    $("#addWareHouse").click(function () {
        var checkMoves = table.checkStatus('manageWareHouseTable').data;
        var dataMoves = table.cache.manageWareHouseTable;
        var dataReceives = table.cache.manageNewWareHouseTable;
        var moveTableId = 'manageWareHouseTable';
        var receiveTableId = 'manageNewWareHouseTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });
    //库房库区删除行
    $("#removeWareHouse").click(function () {
        var checkMoves = table.checkStatus('manageNewWareHouseTable').data;
        var dataMoves = table.cache.manageNewWareHouseTable;
        var dataReceives = table.cache.manageWareHouseTable;
        var moveTableId = 'manageNewWareHouseTable';
        var receiveTableId = 'manageWareHouseTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });

    //物料范围
    //物料范围未分配列表
    var tableSparepart = table.render({
        elem: '#manageSparepart'
        , height: 230 //自适应高度
        , method: 'post'
        , data: []
        , page: false   //开启分页
        , limit: 100   //默认15条数据一页
        , cellMinWidth: 95
        , id: 'manageSparepartTable'
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '分类编码'}
            , {field: 'name', align: 'center', width: 180, title: '分类名称'}
        ]]
    });
    //获取物料范围未分配列表
    $.ajax({
        type: "GET",
        url: "/system/user/listSpareparts.json?id=" + $("#userId").val() + "&ztId=" + $("#userZtId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageSparepartTable', ret);
        }
    });
    //物料范围已分配列表
    var tableNewSparepart = table.render({
        elem: '#manageNewSparepart'
        , height: 230 //自适应高度
        , method: 'post'
        , page: false   //开启分页
        , limit: 100   //默认数据一页
        , limits: [100]  //数据分页条
        , cellMinWidth: 95
        , id: 'manageNewSparepartTable'
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '分类编码'}
            , {field: 'name', align: 'center', width: 180, title: '分类名称'}
        ]]
    });
    //获取物料范围已分配列表
    $.ajax({
        type: "GET",
        url: "/system/user/listNewSpareparts.json?id=" + $("#userId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageNewSparepartTable', ret);
        }
    });

    //物料范围添加行
    $("#addSparepart").click(function () {
        var checkMoves = table.checkStatus('manageSparepartTable').data;
        var dataMoves = table.cache.manageSparepartTable;
        var dataReceives = table.cache.manageNewSparepartTable;
        var moveTableId = 'manageSparepartTable';
        var receiveTableId = 'manageNewSparepartTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });
    //物料范围删除行
    $("#removeSparepart").click(function () {
        var checkMoves = table.checkStatus('manageNewSparepartTable').data;
        var dataMoves = table.cache.manageNewSparepartTable;
        var dataReceives = table.cache.manageSparepartTable;
        var moveTableId = 'manageNewSparepartTable';
        var receiveTableId = 'manageSparepartTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });


    //下辖科室
    //下辖科室未分配列表
    var tableUseDep = table.render({
        elem: '#manageUseDep'
        , height: 230 //自适应高度
        , method: 'post'
        , cellMinWidth: 95
        , page: false   //开启分页
        , limit: 100   //默认数据一页
        , limits: [100]  //数据分页条
        , id: 'manageUseDepTable'
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '分类编码'}
            , {field: 'name', align: 'center', width: 180, title: '分类名称'}
        ]]
    });
    //获取下辖科室未分配列表
    $.ajax({
        type: "post",
        url: "/system/user/listUseDeps.json?id=" + $("#userId").val() + "&ztId=" + $("#userZtId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageUseDepTable', ret);
        }
    });
    //下辖科室已分配列表
    var tableNewUseDep = table.render({
        elem: '#manageNewUseDep'
        , height: 230 //自适应高度
        , method: 'post'
        , cellMinWidth: 95
        , id: 'manageNewUseDepTable'
        , page: false   //开启分页
        , limit: 100   //默认数据一页
        , limits: [100]  //数据分页条
        , cols: [[
            {checkbox: true, fixed: 'left', width: 40}
            , {field: 'code', align: 'center', width: 80, title: '分类编码'}
            , {field: 'name', align: 'center', width: 180, title: '分类名称'}
        ]]
    });
    //获取下辖科室已分配列表
    $.ajax({
        type: "GET",
        url: "/system/user/listNewUseDeps.json?id=" + $("#userId").val(),
        dataType: "json",
        success: function (ret) {
            Reset('manageNewUseDepTable', ret);
        }
    });
    //下辖科室添加行
    $("#addUseDep").click(function () {
        var checkMoves = table.checkStatus('manageUseDepTable').data;
        var dataMoves = table.cache.manageUseDepTable;
        var dataReceives = table.cache.manageNewUseDepTable;
        var moveTableId = 'manageUseDepTable';
        var receiveTableId = 'manageNewUseDepTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });
    //下辖科室删除行
    $("#removeUseDep").click(function () {
        var checkMoves = table.checkStatus('manageNewUseDepTable').data;
        var dataMoves = table.cache.manageNewUseDepTable;
        var dataReceives = table.cache.manageUseDepTable;
        var moveTableId = 'manageNewUseDepTable';
        var receiveTableId = 'manageUseDepTable';
        changeLine(checkMoves, dataMoves, dataReceives, moveTableId, receiveTableId);
    });

    //拼装ids
    function assemblingIds(datas) {
        var ids = [];
        for (var i in datas) {
            ids.push(datas[i].id);
        }
        return ids;
    }

    //保存
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