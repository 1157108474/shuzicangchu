
layui.use(['laydate', 'form', 'layer', 'table'], function () {

    var form = layui.form,
        $ = layui.jquery,
        table = layui.table;

    //数据列表
    var tableIns = table.render({
        elem: '#manageFromTem',
        url: 'formTemplateList.json',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 20,
        id: "manageFromTemTable",
        method: 'post',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {title: '序号', templet: '#indexTpl', align: "center", width: 60},
            {field: 'formTemCard', title: '单据编码', align: 'center', width: 180},
            {field: 'formTemName', title: '单据名称', align: 'center', width: 220},
            {field: 'formTemPre', title: '单据前缀', align: 'center', width: 180},
            {
                field: 'formTemSta', title: '状态', align: 'center', width: 100, templet: function (d) {
                if (d.formTemSta == 1) {
                    return "禁用"
                } else {
                    return "启用"
                }
            }},
            {field: 'formTemCom', title: '说明', align: 'center', width: 120}
        ]],
        page: true
    });


    //添加
    function addNews(tem_dic) {
        var tit = '添加单据',
            url = 'addFormTemplate.htm?tem_dic='+tem_dic;
        layui.layer.open({
            type: 2,
            title: tit,
            area: ['550px', '400px'],
            fixed: false,
            content: url
        });
    }
    //添加
    $("#add").on("click", function () {
        var tem_dic = $("#tem_dicid").val();
        if(tem_dic==null||tem_dic==undefined||tem_dic==""){
            layer.msg('请先选择具体的单据。', {
                anim: 6
            });
        }else{
            addNews(tem_dic);
        }
    });

    //编辑
    $("#edit").on("click", function () {
        var checkStatus = table.checkStatus('manageFromTemTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            tit = '修改单据';
            url = 'editFormTemplate.htm?id='+data[0].id;
            layui.layer.open({
                type: 2,
                title: tit,
                area: ['550px', '400px'],
                fixed: false,
                content: url
            });
        } else {
            layer.msg('请选择一条数据。', {
                anim: 6
            });
        }
    });
    //查看
    $("#showRole").on("click", function (e) {
        var checkStatus = table.checkStatus('manageFromTemTable');
        data = checkStatus.data;
        if (data.length == 1) {
            layer.open({
                type: 2,
                title: '查看信息',
                area: ['450px', '450px'],
                fixed: false,
                content: '/system/role/showRole.htm?r.id=' + data[0].id
            });
        } else {
            layer.msg('请选择一条数据。', {
                offset: 't',
                anim: 6
            });
        }
    });
    window.clickDic = function(){
        //可以被外部引用
        table.reload('manageFromTemTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                id: $("#tem_dicid").val()
            }
        });
    }
    //批量删除
    $("#delete").click(function () {
        var checkStatus = table.checkStatus('manageFromTemTable');
        var  data = checkStatus.data;
        var ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的单据？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("delFromTem.json?ids=" + ids , function (data) {
                    tableIns.reload();
                    layer.msg(data);
                    layer.close(index);
                })
            })
        } else {
            layer.msg('请选择一条数据。', {
                offset: 't',
                anim: 6
            });
        }
    });
});
