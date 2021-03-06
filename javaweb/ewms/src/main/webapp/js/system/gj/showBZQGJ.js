layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });
    //数据列表
    table.render({
        elem: '#querygrid'
        , url: 'BZQGJ.json'
        , cellMinWidth: 80
        , height: "full-125"
        , method: 'post'
        , page: true   //开启分页
        , limit: 30   //默认十五条数据一页
        , limits: [10, 20, 30]  //数据分页条
        , id: "querygridTable"
        , cols: [
            [{type: 'numbers', title: '序号',fixed: "left", width: 50}
                , {title: '查看', align: "center",fixed: "left", toolbar: '#bar', width: 50}
                , {field: 'materialcode', title: '物料编码', align: "center", width: 120}
                , {field: 'materialname', title: '物料名称', align: "center", width: 130}
                , {field: 'description', title: '物料描述', align: "center", width: 240}
                , {field: 'materialmodel', title: '物料型号', align: "center", width: 140}
                , {field: 'storecount', title: '库存数量', align: "center", width: 100}
                , {field: 'createdate', title: '入库时间', align: "center", width: 100}
                , {field: 'expirationtime', title: '保质期时间', align: "center", width: 100}
                , {field: 'syday', title: '剩余天数', align: "center", width: 100}
            ]
        ]
    });
    var reload = function (data) {

        table.reload('querygridTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                materialcode:$("#materialcode").val(),
                description: $("#description").val(),
                syday: $("#syday").val(),
                maturity: $("#maturity").val(),
            }
        });
    };
    //返回年-月-日 时：分：秒型的日期
    function datetimeformat(longdate) {
        if (null == longdate || '' == longdate) {
            return '';
        } else {
            var date = new Date(longdate);
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var seconds = date.getSeconds();
            return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + " " + (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (seconds < 10 ? "0" + seconds : seconds);
        }
    }
    laydate.render({
        elem: '#startTime'
        , type: 'datetime'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'datetime'
    });

    $("#export").click(function () {
        window.location.href="/system/bzqgj/exportBzqGjExcel.htm?materialcode"+$("#materialcode").val()+
            "&description="+$("#description").val()+"&syday="+ $("#syday").val()+ "&maturity="+$("#maturity").val()
    });
});
function showSheet(id) {
    parent.layer.open({
        type: 2,
        title: "明细信息",
        area: ['674px', '340px'],
        resize: true,
        fixed: false, //不固定
        maxmin: true,
        content: "/system/bzqgj/bzqGjDetails.htm?id="+id
    });
};
