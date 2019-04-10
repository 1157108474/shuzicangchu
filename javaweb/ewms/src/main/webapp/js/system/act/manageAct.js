layui.use(['laydate', 'form', 'layer', 'table', 'laytpl','element'], function () {
    var laydate = layui.laydate;
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        element = layui.element;

    //数据列表
    var tableIns = table.render({
        elem: '#manageModel',
        url: 'model.json',
        cellMinWidth: 95,
        page: false,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 30,
        id: "manageModelTable",
        method: 'post',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {title: '序号', templet: '#indexTpl', align: "center", width: 80},
            {field: 'name', title: '流程名称', align: "center", width: 250},
            {field: 'version', title: '流程版本', align: 'center', width: 250},
            {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                width: 300,
                templet: '<div>{{ layui.laytpl.toDateString(d.createTime) }}</div>'
            }
        ]],
    });
    //绑定日期控件
    laydate.render({
        elem: '#startTime'
        , theme: 'molv'
    });
    laydate.render({
        elem: '#endTime'
        , theme: 'molv'
    });
    //搜索
   /* $('#btnSearch').on('click', function () {
        table.reload('manageModel', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                roleName: $(".roleName").val(),  //角色名称
                startTime: $(".startTime").val(),  //开始时间
                endTime: $(".endTime").val()  //结束时间
            }
        });
    });*/

    //部署
    $("#deploy").on("click", function () {
        var checkStatus = table.checkStatus('manageModelTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            layer.confirm('确定部署当前的流程？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("modelDeploy.htm" ,{'id':data[0].id,'depId':data[0].id}, function (data) {
                    table.reload('manageModelTable', {
                        where: {
                            id: $("#act_dicid").val()
                        }
                    });
                    layer.msg(data);
                    layer.close(index);
                })
            })
           // layer.close(index);
        } else {
            layer.msg('请选择一条数据。', {
                anim: 6
            });
        }

    });
    //编辑
    $("#edit").on("click", function () {
        var checkStatus = table.checkStatus('manageModelTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            parent.tab.tabAdd({
                href:"/manageModeler.html?modelId="+data[0].id,
                title: "编辑"+ $('#act_dicName').val()+"流程",
                id:data[0].id
            });
           // window.open("/manageModeler.html?modelId="+data[0].id);
        } else {
            layer.msg('请选择一条数据。', {
                anim: 6
            });
        }
    });
    //新增
    $("#add").on("click", function () {
        var actId = $("#act_dicid").val();
        if (actId != "") {
            $.post("addModelisTrue.json",{'id':actId},
                function(data) {

                    if(data==true){
                        console.log(data);
                        parent.tab.tabAdd({
                            href:"/model/create.htm?id="+actId,
                            title: "新增"+$(".roleName").val()+"流程",
                            id:data.id
                        });
                        /*window.open("/model/create.htm?id="+actId);*/
                    }else{
                        layer.msg('该业务流程已存在工作流！');
                    }
                },"JSON");
        } else {
            layer.msg('请选择具体业务流程。', {
                anim: 6
            });
        }
        return false;
    });
    $("#current").on("click", function () {
        table.reload('manageModelTable', {
            /*page: {
                curr: 1 //重新从第 1 页开始
            },*/
            where: {
                id: $("#act_dicid").val()
            }
        });
    });

    window.clickDic = function(){
        //可以被外部引用
        table.reload('manageModelTable', {
            /*page: {
                curr: 1 //重新从第 1 页开始
            },*/
            where: {
                id: $("#act_dicid").val()
            }
        });
    }

    //时间戳的处理
    layui.laytpl.toDateString = function(d, format){
        var date = new Date(d || new Date())
            ,ymd = [
            this.digit(date.getFullYear(), 4)
            ,this.digit(date.getMonth() + 1)
            ,this.digit(date.getDate())
        ]
            ,hms = [
            this.digit(date.getHours())
            ,this.digit(date.getMinutes())
            ,this.digit(date.getSeconds())
        ];

        format = format || 'yyyy-MM-dd HH:mm:ss';

        return format.replace(/yyyy/g, ymd[0])
            .replace(/MM/g, ymd[1])
            .replace(/dd/g, ymd[2])
            .replace(/HH/g, hms[0])
            .replace(/mm/g, hms[1])
            .replace(/ss/g, hms[2]);
    };

//数字前置补零
    layui.laytpl.digit = function(num, length, end){
        var str = '';
        num = String(num);
        length = length || 2;
        for(var i = num.length; i < length; i++){
            str += '0';
        }
        return num < Math.pow(10, length) ? str + (num|0) : num;
    };
});








