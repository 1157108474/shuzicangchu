layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var ran = Math.random();
    var vipTable = layui.vip_table;

    //��ϸ�б�
    var detailGr = table.render({
        elem: '#detailGrid'
        , url: 'listDetails.json'
        , cellMinWidth: 80
        , height: "full-80"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 30   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailGridTable"
        , cols: [
            [
                {type: "checkbox", fixed: "left", width: 50}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'materialName', title: '��������', align: "center", width: 120}
                , {field: 'materialSpecification', title: '���Ϲ��', align: "center", width: 120}
                , {field: 'materialModel', title: '�����ͺ�', align: "center", width: 120}
                , {field: 'unitName', title: '��λ', align: "center", width: 60}
                , {field: 'ownerdepName', title: '�Ĵ沿��', align: "center", width: 120}
                , {field: 'storeCount', title: '�������', align: "center", width: 80}
                , {field: 'unseCount', title: '�ɳ�������', align: "center", width: 100}
                , {field: 'detailCount', title: '��������', align: "center", width: 80, edit: 'text', event: 'countCheck'}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('����˴�¼�����������', '.laytable-cell-1-detailCount', {
                time: 2000
            });
        }
    });

    var reload = function (data) {
        table.reload('detailGridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: JSON.parse(JSON.stringify(data.field))
        });
    };

    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });

    // ������Ԫ��༭
    table.on('edit(detailGrid)', function (obj) {
        var data = obj.data; //�����е������������
        var field = obj.field; //��ǰ�༭���ֶ���
        var value = obj.value; //�õ��޸ĺ��ֵ
        if (value > data.unseCount) {
            data.jscount = 0;
            layer.alert("�ɳ���������:" + data.unseCount + ",�����������ܴ��ڿɳ�������,��������д��������!");
            return false;
        }

        if (!/^[0-9]+.?[0-9]*$/.test(value) || value == "0") {
            layer.alert("��������������Ч����,��������д��������!");
            data[field] = 0;
            return false;
        }
        if (value == "" || value == null) {
            layer.alert("��������Ϊ��,����д��������!");
            data[field] = 0;
            return false;
        }
    });

    // �����ϸ
    $("#add").on("click", function (e) {
        parent.layui.$("#reloadStatus").val(1);
        var checkStatus = table.checkStatus('detailGridTable');
        var length = checkStatus.data.length;
        if (length < 1) {
            //���Ϸ�
            layer.msg('������ѡ��һ����¼���');
        } else {
            var data = checkStatus.data;
            var details = [];
            for (var i = 0; i < length; i++) {
                //�ύǰ��֤
                if (data[i].detailCount > data[i].unseCount) {
                    layer.alert("�����������ܴ��ڿɳ�������,��������д��������!");
                    return;
                }
                if (isNaN(data[i].detailCount || data[i].detailCount == '0')) {
                    layer.alert("��������������Ч����,��������д��������!");
                    return;
                }
                if (data[i].detailCount == "" || data[i].detailCount == null) {
                    layer.alert("��������Ϊ��,����д��������!");
                    return;
                }

                var obj = {
                    sheetId: parent.layui.$("#id").val(),
                    sheetDetailId: data[i].id,
                    materialId: data[i].materialId,
                    materialCode: data[i].materialCode,
                    materialName: data[i].materialName,
                    materialModel: data[i].materialModel,
                    materialSpecification: data[i].materialSpecification,
                    detailUnitName: data[i].unitName,
                    /*ownerdep: data[i].ownerdep,
                    storeCount: data[i].storeCount,
                    unseCount: data[i].unseCount,*/
                    detailCount: data[i].detailCount
                };
                details.push(obj);
            }

            $.ajax({
                type: "POST",
                url: "../detail/addwzjcckdDetails",
                dataType: "json",
                data: {details: JSON.stringify(details)},
                success: function (ret) {
                    if (ret.status == '1') {
                        layer.msg('��ӳɹ�', function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.alert('���ʧ�ܣ�' + ret.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            });
        }
        return false;

    });


    //�豸checkbox�����仯ʱ������/�������к�checkbox
    form.on('checkbox(singleCheck)', function (obj) {
        // ��ȡ��ǰcheckbox�Ƿ�Ϊѡ��״̬
        var isEq = obj.elem.checked;
        // ��ȡ��ǰname
        var currentId = obj.elem.name;
        // ��ȡ��ǰID
        var checkId = obj.elem.id;
        if (isEq == true) {
            // isEq = true(ѡ��״̬)  �������к�
            $("#" + checkId).attr("value", 1);
            $("#enableSn" + currentId).removeAttr("disabled");
            form.render();
        } else {
            // isEq = false(δѡ��״̬) �������к�  ����ȡ����ѡ��
            $("#" + checkId).attr("value", 0);
            $("#enableSn" + currentId).attr("disabled", !isEq);
            $("#enableSn" + currentId).removeAttr("checked");
            form.render();
        }

    });

    //�������к� ����¼�
    form.on('checkbox(singCheck)', function (obj) {
        // ��ȡ��ǰcheckbox�Ƿ�Ϊѡ��״̬
        var isSn = obj.elem.checked;
        // ��ȡ��ǰID
        var checkId = obj.elem.id;
        if (isSn == true) {
            $("#" + checkId).attr("value", 1);
            form.render();
        } else {
            $("#" + checkId).attr("value", 0);
            form.render();
        }
    })


});


