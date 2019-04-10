layui.use(['laydate', 'form', 'layer', 'table', 'laytpl','element'], function () {
    var laydate = layui.laydate;
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        element = layui.element;

    //�����б�
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
            {title: '���', templet: '#indexTpl', align: "center", width: 80},
            {field: 'name', title: '��������', align: "center", width: 250},
            {field: 'version', title: '���̰汾', align: 'center', width: 250},
            {
                field: 'createTime',
                title: '����ʱ��',
                align: 'center',
                width: 300,
                templet: '<div>{{ layui.laytpl.toDateString(d.createTime) }}</div>'
            }
        ]],
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
   /* $('#btnSearch').on('click', function () {
        table.reload('manageModel', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                roleName: $(".roleName").val(),  //��ɫ����
                startTime: $(".startTime").val(),  //��ʼʱ��
                endTime: $(".endTime").val()  //����ʱ��
            }
        });
    });*/

    //����
    $("#deploy").on("click", function () {
        var checkStatus = table.checkStatus('manageModelTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            layer.confirm('ȷ������ǰ�����̣�', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
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
            layer.msg('��ѡ��һ�����ݡ�', {
                anim: 6
            });
        }

    });
    //�༭
    $("#edit").on("click", function () {
        var checkStatus = table.checkStatus('manageModelTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            parent.tab.tabAdd({
                href:"/manageModeler.html?modelId="+data[0].id,
                title: "�༭"+ $('#act_dicName').val()+"����",
                id:data[0].id
            });
           // window.open("/manageModeler.html?modelId="+data[0].id);
        } else {
            layer.msg('��ѡ��һ�����ݡ�', {
                anim: 6
            });
        }
    });
    //����
    $("#add").on("click", function () {
        var actId = $("#act_dicid").val();
        if (actId != "") {
            $.post("addModelisTrue.json",{'id':actId},
                function(data) {

                    if(data==true){
                        console.log(data);
                        parent.tab.tabAdd({
                            href:"/model/create.htm?id="+actId,
                            title: "����"+$(".roleName").val()+"����",
                            id:data.id
                        });
                        /*window.open("/model/create.htm?id="+actId);*/
                    }else{
                        layer.msg('��ҵ�������Ѵ��ڹ�������');
                    }
                },"JSON");
        } else {
            layer.msg('��ѡ�����ҵ�����̡�', {
                anim: 6
            });
        }
        return false;
    });
    $("#current").on("click", function () {
        table.reload('manageModelTable', {
            /*page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },*/
            where: {
                id: $("#act_dicid").val()
            }
        });
    });

    window.clickDic = function(){
        //���Ա��ⲿ����
        table.reload('manageModelTable', {
            /*page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },*/
            where: {
                id: $("#act_dicid").val()
            }
        });
    }

    //ʱ����Ĵ���
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

//����ǰ�ò���
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








