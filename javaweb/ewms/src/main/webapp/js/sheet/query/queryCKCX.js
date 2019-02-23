layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //数据列表
    table.render({
        elem: '#querygrid'
        , url: 'listQueryCKCX.json'
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //开启分页
        , limit: 20   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '序号',fixed: "left", width: 50}
                , {title: '查看', align: "center",fixed: "left", toolbar: '#bar', width: 80}
                , {field: 'code', title: '单据编号',fixed: "left", align: "center", width: 140}
                , {field: 'materialcode', title: '物料编码', align: "center", width: 120}
                , {field: 'materialname', title: '物料名称', align: "center", width: 100}
                , {field: 'materialspecification', title: '物料规格', align: "center", width: 100}
                , {field: 'materialmodel', title: '物料型号', align: "center", width: 140}
                , {field: 'usdedepname', title: '申领单位', align: "center", width: 200}
                , {field: 'detailcount', title: '数量', align: "center", width: 100}
                , {field: 'kindName', title: '类型', align: "center", width: 100}
                , {field: 'description', title: '物料说明', align: "center", width: 200}
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                ztid: $("#ztid").val(),
                code: $("#code").val(),
                storeid: $("#storeid").val(),
                useddepartid:$("#usedDepartId").val(),
                materialname:$("#materialname").val(),
                materialcode:$("#materialcode").val()
            }
        });
    };
    //监听工具条
    table.on('tool(querygrid)', function (obj) {
        var data = obj.data;
        if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: "/sheet/ck/"+data.sheetid+"?oper=show", //地址
                title: "查看物资出库明细"
            });
        }
    });

    $("#export").click(function () {
        window.location.href="/sheet/query/exportCKExcel.htm?code="+$("#code").val()+"&storeid="+$("#storeid").val()+
            "&materialcode="+$("#materialcode").val()+"&materialname="+$("#materialname").val()
    });

    //组织部门弹窗
   /* $("#departBtn").on("click", function () {
        layui.layer.open({
            type: 2,
            title: '组织机构',
            fixed: false,
            area: ['38%', '75%'],
            content: "../../system/user/publicDepart.htm",
            // scrollbar:false
        });
        return false;
    });*/

    //进入使用单位页面
    $("#openUseDep").on("click", function () {
        if($("#ztid").val()==""){
            alert("请先选择部门!");
            return false;
        }
        layui.layer.open({
            type: 2,
            title: '申领单位',
            fixed: false,
            area: ['500px', '80%'],
            content: "../../system/useDep/generalUseDep.htm?ztId=" + $("#ztid").val(),
            // scrollbar:false
        });
        return false;
    });

    $("#reset").click(function () {
        $("#usedDepartId").val("");
    });
});
//打开页面
function openPage(tit, url, width, height) {
    layui.layer.open({
        title: tit
        , type: 2
        , fixed: false
        , area: [width, height]
        , content: url
    })
}
function obtainPart(id, name, ztId, code) {
    document.getElementById("departName").value = name;
    document.getElementById("departId").value = id;
}

//获取使用单位返回值
function UseDepGeneral(data) {
    document.getElementById("useDepName").value = data[0].name;
    document.getElementById("usedDepartId").value = data[0].id;
}