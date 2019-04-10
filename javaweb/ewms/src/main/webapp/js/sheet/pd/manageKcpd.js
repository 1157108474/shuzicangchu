layui.config({
    base: '/js/static/' // 模块目录
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;

    //明细列表
    var detailsgrid = table.render({
        elem: '#pdsheetgrid'
        , url: 'manageKcpd.json'
        , cellMinWidth: 80
        , height: "full-140"
        , method: 'POST'
        , page: true   //开启分页
        , limit: 30
        , limits: [10, 20 ,30]  //数据分页条
        ,id: 'pdsheetgridTable'
        , cols: [
            [{type: "checkbox", fixed: "left", width: 30}
                ,{title: '序号',toolbar: '#indexTpl',fixed: "left",align: "center",width: 50}
                ,{ title: '操作',width:130, fixed: "left", toolbar: '#barDemo'}
                , {field: 'code', title: '单据编号',fixed: "left", align: "center", width: 150}
                , {field: 'name', title: '盘点名称', align: "center", width: 120}
                , {field: 'houseName', title: '库存组织', align: "center", width: 150}
                , {field: 'statusName', title: '单据状态', align: "center", width: 100}
                , {field: 'personName', title: '制单人', align: "center",width: 120}
                , {field: 'createDate', title: '制单日期', align: "center",width: 250,
                templet: function (d) {
                    return datetimeformat(d.createDate)
                }
            }

            ]
        ]
    });
    //绑定日期控件
    laydate.render({
        elem: '#beginDate'
    });
    //绑定日期控件
    laydate.render({
        elem: '#endDate'
    });
    var reload =function(){
        table.reload('pdsheetgridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where:{
                extendInt1:$("#extendInt1").val(),
                workManId:$("#workManId").val(),
                code:$("#code").val(),
                beginDate:$("#beginDate").val(),
                endDate:$("#endDate").val()
            }
        })
    };
    form.on('submit(search)', function () {
        reload();
        return false;
    });
    //进入制单人页面
    $("#openWorkMan").on("click", function (e) {
        var url = "/sheet/apply/publicDepartUser.htm";
        vipTable.openPage("人员信息", url, "85%","90%");
    });
    $("#reset").on("click", function () {
        $("#workManId").val("");
        $("#workManName").val("");
        $("#code").val("");
        $("#extendInt1").val("");
        $("#beginDate").val("");
        $("#endDate").val("");
    });
    $("#export").on("click", function () {
        var extendInt1=$("#extendInt1").val();
        var workManId=$("#workManId").val();
        var code=$("#code").val();
        var beginDate=$("#beginDate").val();
        var endDate=$("#endDate").val();
        /*var datas =  "temCode="+temCode+"&temName="+temName+"&startTime="+startTime+"&endTime="+endTime;
        window.location = "expActivitiListen.json?"+datas;*/
        var pram ='?extendInt1='+extendInt1+'&workManId='+workManId+'&code='+code+'&beginDate='+beginDate+'&endDate='+endDate;
        location.href = '/sheet/pd/expManageKcpd.json'+pram;
        /*$.post("expManageKcpd.json",{'extendInt1':extendInt1,"workManId":workManId,"code":code,"beginDate":beginDate,"endDate":endDate},
            function(data) {
                if(data){
                    layer.msg('导出文件D:/流程监控.xls成功！');
                }
            },"JSON");*/
    });
    //监听工具条
    table.on('tool(pdsheetgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: data.url+"?oper=edit", //地址
                title: "编辑库存盘点单"+data.code
            });
        }else if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: data.url+"?oper=show", //地址
                title:"查看库存盘点单"+data.code
            });
        }else if(obj.event === 'delete'){
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    type: "delete",
                    url: "/sheet/KCPD-"+data.id,
                    dataType: "json",
                    success: function (ret) {
                        if (ret.status == '1') {
                            layer.alert("删除成功");
                            reload();
                            //$("#submitBtn").click();
                        } else {
                            layer.alert('删除失败：' + ret.message);
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("删除请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                    }
                });
            })
        }
    });
});
//获取人员信息页面回传参数
function obtainUser(data,type) {
    document.getElementById("workManName").value = data.name;
    document.getElementById("workManId").value = data.id;
}

