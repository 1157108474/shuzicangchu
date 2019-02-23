layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var uid = $("#usedepId").val();
    var ztId = $("#ztId").val();
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //数据列表
    table.render({
        elem: '#providerList'  //绑定table id
        , url: 'listUseDepDepartGeneral.json?ztId=' + ztId  //数据请求路径
        , cellMinWidth: 80
        , method: 'post'
        , cols: [[
            {checkbox: true, fixed: 'left'}
            , {field: 'code', align: 'center', title: '编码', fixed: 'left'}
            , {field: 'name', align: 'center', title: '使用单位名称', fixed: 'left'}
            , {
                field: 'organizationType', align: 'center', title: '组织类型', width: 70, templet: function (d) {
                    if (d.organizationType == 1) {
                        return "区队"
                    } else {
                        return "科室"
                    }
                }
            }
            ]]
        , page: true   //开启分页
        , limit: 10   //默认十条数据一页
        , limits: [10, 20, 30, 50]  //数据分页条
        , id: 'providerListReload'
    });
    var reload = function (data) {

        table.reload('providerListReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: JSON.parse(JSON.stringify(data.field))
        })
    };

    //添加
    $("#add").on("click", function (e) {
        var checkStatus = table.checkStatus('providerListReload');
        if (checkStatus.data.length != 1) {
            layer.msg("请选择一条记录进行添加", {
                offset: 't',
                anim: 6
            })
        } else {
            var data = checkStatus.data;
            parent.UseDepDepartGeneral(data);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
});
