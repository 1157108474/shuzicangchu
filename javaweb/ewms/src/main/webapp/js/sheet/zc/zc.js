layui.config({
    base: '/js/static/' // 模块目录
}).use(['form', 'table', 'layer', 'vip_table'], function () {
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    var vipTable = layui.vip_table;
    var ran =Math.random();
    var form = layui.form;
  //  document.getElementById('date').innerHTML= new Date().toLocaleString( );
    //明细列表
    var detailsgrid = table.render({
        elem: '#detailsgrid'
        , url: 'details'
        , cellMinWidth: 80
        , height: "full-240"
        , method: 'POST'
        , page: true   //开启分页
        , limit: 20   //默认十五条数据一页
        , limits: [10, 20 ,30]  //数据分页条
        ,id: 'detailsgridReload'
        ,where:{ran:ran++,sheetId:$("#id").val()}

        , cols: [
            [     {type: "checkbox", fixed: "left", width: 30}
                , {title: '操作', align: "center",fixed:'left', toolbar: '#bar', width: 100}
                , {field: 'materialName', title: '物料名称', align: "center", width: 130}
                , {field: 'materialCode', title: '物料编码', align: "center", width: 130}
                , {field: 'description', title: '物料描述', align: "center", width: 420}
                , {field: 'detailUnitName', title: '单位', align: "center", width: 60}
                , {field: 'houseName', title: '库房', align: "center", width: 110}
                , {field: 'storeLocationName', title: '库位', align: "center", width: 120}
                , {field: 'detailCount', title: '数量', align: "center",width: 80}
                , {field: 'noTaxPriceDouble', title: '不含税单价', align: "center", width: 100}
                , {field: 'noTaxSumDouble', title: '不含税金额', align: "center", width: 100}

            ]
        ]

    });

    var reload =function(){
        table.reload('detailsgridReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where:{
                ran:ran++,
                sheetId: $("#id").val()
            }

        })
    };


//保存按钮
    $("#saveSheet").on("click", function (e) {
        var result;
        var id = $("#id").val();

        if( id==""){
            result = saveSheet(false);
        }else{
            result = updateSheet(id);
        }
        if(result){
            layer.alert("保存成功");
        }
    });

    //新增明细页面
    var open = function () {
        $("#reloadDetailsgrid").val(0);
        layer.open({
            title: "添加明细"
            , type: 2
            , fixed: false
            , area: ["90%", "90%"]
            , content: "addDetails.htm"
            , end: function () {
                if ($("#reloadDetailsgrid").val() == 1) {
                    reload();
                }
            }
        });
    };



    var saveSheet =function(openFlag){
        if($("#ownerdepName").val()==""){
            layer.alert("请先选择库存组织");
            return;
        }

        $.ajax({
            type: "post",
            url: "/sheet/WZZC",
            dataType: "json",
            data: {"menuCode":$("#menuCode").val(),"extendString2":$("#ownerdepName").val(),"ztId":$("#ownerdepId").val(),"extendString1":$("#providerName").val(),"providerDepId":$("#providerId").val(),"extendString4":$("#extendString4").val(),"memo":$("#memo").val()},
            success: function (ret) {

                if (ret.status == '1') {
                    $("#id").val(ret.data.id);
                    $("#code").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    $("#rkBtn").attr("disabled",true);
                    reloadHis();
                    if(openFlag){
                        open();
                    }

                } else {
                    layer.alert('保存单据失败：' + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("保存单据请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };

    var updateSheet =function(id){
        $.ajax({
            type: "put",
            url: "/sheet/WZZC/"+id,
            dataType: "json",
            data: {"memo":$("#memo").val()},
            success: function (ret) {
                if (ret.status == '1') {
                    layer.alert('保存单据成功' );
                    return true;
                } else {
                    layer.alert('保存单据失败：' + ret.message);
                    return false;
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("保存单据请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };

    //添加明细按钮
    $("#addDetails").on("click", function (e) {
        if($("#id").val()==""){
            saveSheet(true);
        } else {
            open();
        }
    });
    //删除明细
    $("#deleteDetails").on("click", function (e) {
        var checkStatus = table.checkStatus('detailsgridReload');
        var data = checkStatus.data;
        var ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的明细？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/sheet/detail/delSheetDetails.json?ids=" + ids, function (data) {
                    reload();
                    layer.msg(data.message);
                    layer.close(index);
                });
            })
        } else {
            layer.msg('请选择要删除的记录', {offset: 't', anim: 6});
        }
    });

    //监听工具条
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        subreload(data.materialCode,data.storeId);

        layer.open({
            type: 1,
            title: "明细详情",
            area: ['600px', '400px'],
            content: $("#showSub")
        });
    });

    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            layer.open({
                title: "修改明细"
                , type: 2
                , fixed: false
                , area: ["90%", "90%"]
                , content: "editDetail.htm?id="+data.id
                , end: function () {
                    reload();
                }
            })
        }
    })
    $("#doprint").on("click", function (e) {
        var printType = $("#printType").val();
        if (printType == '') {
           layer.alert("请选择打印类型");
       }else{

            vipTable.openPage("打印退货单", "/system/print/sheet/WZTH-" + $("#id").val() + "?printType=" + printType, '780px', '400px');

       }
    });

    //获取寄存单位列表
    $("#ownerdepBtn").on("click", function () {
        var url = "/system/user/publicDepart.htm";
        layui.layer.open({
            title: "组织机构"
            , type: 2
            , fixed: false
            , area: ['50%', '85%']
            , content: url
        });
        return false;
    });

    //获取供应商列表
    $("#providerBtn").on("click", function () {
        layui.layer.open({
            type: 2,
            title: '供应商',
            fixed: false,
            area: ['38%', '75%'],
            content: "../../system/provider/generalProvider.htm",
            // scrollbar:false
        });
        return false;
    });
});
function setRkInfo(data) {
    $("#rkCode").val(data.code);
    $("#extendString2").val(data.extendString2);//库存组织
    $("#ztId").val(data.ztId);//库存组织
    $("#extendString1").val(data.extendString1);//库存组织

}
//获取部门页面回传参数
function obtainPart(id, name, ztId, code) {
    document.getElementById("ownerdepName").value = name;
    document.getElementById("ownerdepId").value = id;
}