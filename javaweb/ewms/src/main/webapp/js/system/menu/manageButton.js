layui.use(['form', 'layer', 'table', 'laytpl', 'element'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
    //�����б�
    var tableIns = table.render({
        elem: '#manageButton'
        ,url: '/system/button/listButton.json'
        ,cellMinWidth: 80
        ,height: "full-60"
        ,method: 'post'
        ,page: true   //������ҳ
        ,limit:30   //Ĭ��ʮ��������һҳ
        ,limits:[15,30,45]  //���ݷ�ҳ��
        ,id: "manageButtonTable"
        ,where:{menuCode:$("#menuCode").val()}//��ʼ����
        ,cols: [
            [{type: "checkbox",fixed: "left",width: 50}
            ,{field: 'code', title: '��ť����',align: "center",width:120}
            , {field: 'name', title: '��ť����',align: "center",width:120}
            , {field: 'url', title: '��ťURL',align: "center",width:180}
            , {field: 'identifying',title: 'Ȩ�ޱ�ʶ',align: "center"}
            , {field: 'sort',title: '����',align: "center",width:100}
            , {field: 'status',title: '״̬',align: "center",width:100,templet: "#showStatus"}
            ]
        ]
    });
    //���
    function addNews(edit) {
        var tit = '������ť';
        var menuCode = $("#menuCode").val();
        var url = '/system/button/addButton.htm?menuCode=' +menuCode ;
        if (edit) {
            tit = '�༭��ť';
			url = '/system/button/editButton.htm?code=' + edit.code+'&menuCode=' +menuCode;
        }
        layui.layer.open({
            type: 2,
            title: tit,
            area: ['500px', '320px'],
            fixed: false,
            content: url
        });
    }
    //���
    $("#add").click(function () {
        addNews();
    });
    //�༭
    $("#edit").click(function () {
        var checkStatus = table.checkStatus('manageButtonTable'),
            data = checkStatus.data;
        if (data.length == 1) {
            addNews(data[0]);
        } else {
            layer.msg('��ѡ��һ�����ݡ�', {
                offset: 't',
                anim: 6
            });
        }
    });

    //����ɾ��
    $("#delAll").on("click", function(e) {
        var checkStatus = table.checkStatus('manageButtonTable'),
            data = checkStatus.data;
        var codes = [];
        if(data.length > 0) {
            for(var i in data) {
                codes.push(data[i].code);
            }
            layer.confirm('ȷ��ɾ��ѡ�еİ�ť��', {
                icon: 3,
                title: '��ʾ��Ϣ'
            }, function(index) {
                $.ajax({
                    url: "/system/button/delButton.json?codes=" + codes ,
                    type: 'post',
                    contentType: 'application/x-www-form-urlencoded; charset=GBK',
                    success: function(data) {
                        top.layer.msg(data.message, {
                            time: 500
                        });
                        if ('1' == data.status) {
                            location.reload();
                        }
                    }
                });
            })
        } else {
            layer.msg('��ѡ��һ�����ݡ�', {
                offset: 't',
                anim: 6
            });
        }
    });
});