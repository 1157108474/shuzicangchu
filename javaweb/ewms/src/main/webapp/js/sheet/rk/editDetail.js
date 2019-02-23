layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var ran = Math.random();

    var subDetail = table.render({
        elem: '#subDetail'
        , url: 'getSonDetail.json'
        , method: 'post'
        , id: "subDetailReload"
        , where: {ran: ran++, detailId: $("#rid").val()}
        , cols: [[{type: 'numbers', title: '���', width: 50}
            , {field: 'storeLocationName', title: '��λ', align: "center"}
            , {field: 'subDetailCount', title: '��������', align: "center"}
            , {field: 'snCode', title: '���к�', align: "center"}
            , {title: '����', align: "center", toolbar: '#subBar'}
        ]
        ]
        , done: function (res, curr, count) {
            layer.tips('�����ε����λ / �������� / ���к� ������Ϣ��', '.laytable-cell-1-fpkw', {
                time: 2000
            });
            /*layer.alert('�����ε����λ / �������� / ���к� ������Ϣ��');*/
        }
    });

    var subReload = function () {
        table.reload('subDetailReload', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ran: ran++,
                sheetId: $("#rId").val()
            }

        })
    };


    //����������
    table.on('tool(detailList)', function (obj) {

        var data = obj.data;
        var storeID = $("#storeId").val();
        if (obj.event === 'location') {
            if (storeID == "" || storeID == 0 || storeID == undefined) {
                layer.alert("�ⷿ��Ϣ����ȷ���޷����䣡");
                return false;
            } else {
                layer.open({
                    title: "��λ"
                    , type: 2
                    , fixed: false
                    , area: ['580px', '400px']
                    , content: "../../system/ware/location?pre=new&ztId=" + $("#ztId").val() + "&parentId=" + storeID
                    , end: function () {
                        obj.update({
                            fpkw: $("#newlocationName").val()
                        });
                    }
                })
            }
        }
    });

    //����������
    table.on('tool(subDetail)', function (obj) {

        var data = obj.data;
        if (obj.event === 'delete') {
            layer.confirm('��ȷ��Ҫɾ����', {
                icon: 3, title: '��ʾ��Ϣ', offset: '35%',
                btn: ['ȷ��', 'ȡ��'] //��ť
            }, function () {
                var url = "../rkDetail/deleteSonDetail.json?id=" + data.id;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('ɾ���ɹ�', {
                            time: 2000,
                            end: function () {

                                location.reload(true);
                            }
                        });

                    } else {
                        layer.alert(data.message, {
                            offset: '35%'
                        });
                    }
                })
            })
        }
    });
    var bool = true;
    //���
    $("#addSon").on("click", function (e) {
        if (bool) {
            bool = false;
            var checkStatus = table.checkStatus('detailList');
            var data = checkStatus.data;
            var length = data.length;
            if (length != 1) {
                layer.msg("��ѡ�����ݽ������");
                bool = true;
            } else {
                var details = [];
                //У��
                for (var i = 0; i < length; i++) {
                    if (data[i].kfpsl == 0) {
                        layer.alert("û�����Ͽ��Է��䣡");
                        bool = true;
                        return false;
                    }
                    if (data[i].fpkw == "" || data[i].fpkw == "0") {
                        layer.alert("��ѡ���λ��");
                        bool = true;
                        return false;
                    }
                    if (isNaN(data[i].fpsl) || data[i].fpsl == "0") {
                        layer.alert("����������Ч���֣���������д������");
                        bool = true;
                        return false;
                    }
                    if (data[i].fpsl == "") {
                        layer.alert("����Ϊ�գ�����д������");
                        bool = true;
                        return false;
                    }
                    // if (parseFloat(data[i].fpsl) > parseFloat(data[i].kfpsl) || data[i].kfpsl - data[i].fpsl < 0) {
                    if (parseFloat(data[i].fpsl) > parseFloat(data[i].kfpsl)) {
                        layer.alert("�����������ܳ����ɷ�����������������д��");
                        bool = true;
                        return false;
                    }
                    if (!/^[0-9]+.?[0-9]*$/.test(data[i].fpsl) || data[i].fpsl < 0) {
                        layer.alert("������������Ϊ������");
                        bool = true;
                        return false;
                    }
                    if (data[i].isEquipment == "��") {
                        if (!(/^(\+|-)?\d+$/.test(data[i].fpsl))) {
                            layer.alert("�豸�ķ�����������������������������д��ȷ��������")
                            bool = true;
                            return false;
                        }
                    }

                    // ��ȡ��λ��Ϣ
                    var lationCode = $("#newlocationCode").val();
                    var lationName = $("#newlocationName").val();

                    var obj = {
                        detailId: $("#rid").val(),
                        subDetailCount: data[i].fpsl,
                        subStock: 0,
                        unitName: data[i].detailUnitName,
                        storeID: $("#storeId").val(),
                        storeLocationId: $("#newlocationId").val(),
                        storeLocationName: lationName,
                        storeLocationCode: lationCode,
                        extendInt1: $("#extendInt1").val(),
                        snCode: data[i].xlh + ""
                    };
                    details.push(obj);
                }
                $.ajax({
                    type: "POST",
                    url: "../rkDetail/addSonDetail.json",
                    dataType: "json",
                    data: {details: JSON.stringify(details)},
                    success: function (ret) {
                        if (ret.status == '1') {
                            layer.msg('��ӳɹ�', function () {
                                location.reload(true);
                            });
                        } else {
                            layer.alert('���ʧ�ܣ�' + ret.message);
                            bool = true;
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                        bool = true;
                    }
                });
            }
        } else {
            layer.alert("���ύ�������ظ��ύ��");
            bool = true;
        }
    });
});
