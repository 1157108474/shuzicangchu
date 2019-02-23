layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var laydate = layui.laydate;
    var vipTable = layui.vip_table;

    //�����б�
    var ordergr = table.render({
        elem: '#jcwzrkgrid'
        , url: 'listManageJcwzrk.json'
        , cellMinWidth: 80
        , height: "full-110"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 20   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "ordergridTable"
        , cols: [
            [{type: 'numbers', title: '���',fixed: "left", width: 50}
                , {title: '����', fixed: "left", toolbar: '#barDemo', width: 130}
                , {field: 'code', title: '���ݱ��',fixed: "left", align: "center", width: 150}
                , {field: 'ownerDepName', title: '�Ĵ浥λ', align: "center"}
                , {field: 'statusName', title: '����״̬', align: "center", width: 150}
                , {
                field: 'createDate', title: '�Ƶ�����', align: "center", width: 150,
                templet: function (d) {
                    return datetimeformat(d.createDate)
                }
            }
                , {field: 'personName', title: '�Ƶ���', align: "center", width: 100}
                , {field: 'depName', title: '�Ƶ�����', align: "center", width: 150}
                , {field: 'memo', title: '��ע', align: "center", width: 100}
            ]
        ]
    });

    var reload = function (data) {

        table.reload('ordergridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: JSON.parse(JSON.stringify(data.field))
        });
    };

    form.on('submit(search)', function (data) {
        reload(data);
        return false;
    });

    laydate.render({
        elem: '#begin'
        , type: 'date'
    });
    laydate.render({
        elem: '#end'
        , type: 'date'
    });
    $("#reset").on("click", function () {
        $("#code").val('');
        $("#status").val('');
        $("#creator").val('');
        $("#creatorName").val('');
        $("#begin").val('');
        $("#end").val('');
    });
    //ѡ���Ƶ���
    $("#creatorBtn").on("click", function () {
        var type="zdr";
        var url = "/system/user/publicDepartUser.htm?type="+type;
        layui.layer.open({
            title: "��Ա��Ϣ"
            , type: 2
            , fixed: false
            , area: ['85%', '90%']
            , content: url
        });
        return false;
    });
    //����������
    table.on('tool(jcwzrkgrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            parent.tab.tabAdd({
                href: data.url+"?oper=edit", //��ַ
                title: "�༭�Ĵ�������ⵥ"+data.code
            });
        }else if(obj.event === 'show'){
            parent.tab.tabAdd({
                href: data.url+"?oper=show", //��ַ
                title:"�鿴�Ĵ�������ⵥ"+data.code
            });
        }else if(obj.event === 'delete'){
            layer.confirm('ȷ��ɾ��ѡ�еļ�¼��', {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.ajax({
                    type: "delete",
                    url: "/sheet/wzjcrkd-"+data.id,
                    dataType: "json",
                    success: function (ret) {
                        if (ret.status == '1') {
                            layer.alert("ɾ���ɹ�");
                            $("#submitBtn").click();
                        } else {
                            layer.alert('ɾ��ʧ�ܣ�' + ret.message);
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("ɾ���������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                    }
                });

            })
        }
    });

});
//��ȡ������Ա��Ϣҳ��ش�����
function obtainUser(data,type) {
    if (type=="zdr"){
        document.getElementById("creatorName").value = data.name;
        document.getElementById("creator").value = data.id;
    }
}




