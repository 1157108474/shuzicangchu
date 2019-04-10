layui.use(['laydate', 'form', 'layer', 'table', 'laytpl', 'element'], function () {
    var laydate = layui.laydate;
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        element = layui.element;

    //�����б�
    var tableIns = table.render({
        elem: '#activitiListen',
        url: 'findProcessingByperson.json?isAdmin=admin',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 30,
        id: "activitiListenTable",
        method: 'post',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {
                field: 'code', title: '���ݱ��', align: "center", event: "getId", templet: function (d) {
                return "<a class='layui-table-link' href='javascript:;'>" + d.code + "</a>";
            }
            },
            /*
                        {field: 'code', title: '���ݱ��', align: "center",minWidth:120,event:"getId"},
            */
            {field: 'sheetName', title: '��������', align: "center", minWidth: 120},
            {field: 'submitMan', title: '������', align: "center", minWidth: 120},
            {field: 'name', title: '��˻���', align: "center", minWidth: 120},
            {field: 'assignee', title: '������', align: "center", minWidth: 120},
            {field: 'createTime', title: '�ύʱ��', align: "center", minWidth: 120},
            {field: 'status', title: '����״̬', align: "center", minWidth: 120},
            /*{field: 'id', title: '����id', align: "center",minWidth:120},*/
        ]],
        page: true
    });
//�����ڿؼ�
    laydate.render({
        elem: '#startTime'
        , theme: 'molv'
    });
    laydate.render({
        elem: '#endTime'
        , theme: 'molv'
    });
    //����
    form.on("submit(formSubmit)", function (data) {
        table.reload('activitiListenTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                temCode: $("#temCode").val(),
                temName: $("#temName").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val(),
                status: $("#status select").val()//����״̬

            }
        });
        return false;
    });
    //����
    $('#reset').on('click', function () {
        $("#temCode").val("");
        $(".temName").val("");
        $(".startTime").val("");
        $(".endTime").val("");
    });

    //���
    $("#audit").on("click", function () {
        var checkStatus = table.checkStatus('activitiListenTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            /*$.post("/system/activitiListener/isTaskTrue.json",{'taskId':data[0].id},
                function(obj) {
                    if(obj){//�ǣ���ʾ���ֱ�Ӱ�ᣩ
                        layer.alert("�û���Ϊ������񻷽ڣ���ֱ�Ӱ�ᡣ");
                    }else{//������󻷽�*/
            thisPart(data[0].id);
            /*}
        },"JSON");*/
        } else {
            layer.msg('��ѡ��һ�����ݡ�', {
                anim: 6
            });
        }
    });
    //����
    $("#another").on("click", function () {
        var checkStatus = table.checkStatus('activitiListenTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            layer.open({
                type: 2,
                title: '������Աѡ��',
                fixed: false,
                area: ['60%', '80%'],
                content: "system/activitiListener/thisPartAssent.htm?taskId=" + data[0].id,
                end: function () {
                    tableIns.reload();
                }
            });
        } else {
            layer.msg('��ѡ��һ�����ݡ�', {
                anim: 6
            });
        }
    });
    //����
    $("#export").on("click", function () {
        alert("=====");
        var temCode = $("#temCode").val();
        var temName = $("#temName").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        /*var datas =  "temCode="+temCode+"&temName="+temName+"&startTime="+startTime+"&endTime="+endTime;
        window.location = "expActivitiListen.json?"+datas;*/
        $.post("expActivitiListen.json", {
                'temCode': temCode,
                "temName": temName,
                "startTime": startTime,
                "endTime": endTime
            },
            function (data) {
                if (data) {
                    layer.msg('�����ļ�D:/���̼��.xls�ɹ���');
                }
            }, "JSON");
    });

    function thisPart(taskId) {
        layer.open({
            type: 2,
            title: '������Աѡ��',
            fixed: false,
            area: ['60%', '90%'],
            content: "system/activitiListener/findNextPart.htm?taskId=" + taskId + "&&comment=����Ա���&&isAdmin=true",
            end: function () {
                tableIns.reload();
            }
        });
    }

    layui.table.on('tool(activitiListen)', function (obj) {
        // �����
        var data = obj.data;
        $.post("/system/activitiButton/isCompletedTask.htm", {'taskId': data.id},
            function (obj) {
                if (obj) {//����ɵ�����
                    parent.tab.tabAdd({
                        href: "/system/activitiButton/getOnePro.htm?taskId=" + data.id, //��ַ
                        title: "����ҳ��" + data.id,
                        id: data.id
                    });
                } else {
                    parent.tab.tabAdd({
                        href: data.url + "?taskId=" + data.id, //��ַ
                        title: "����ҳ��" + data.id,
                        id: data.id
                    });
                }
            }, "JSON");
    });
});








