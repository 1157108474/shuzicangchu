layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var ordernum = $("#ordernum").val();

    //��ϸ�б�
    var detailGr = table.render({
        elem: '#detailGrid'
        , url: 'listDetails.json?ordernum=' + ordernum
        , cellMinWidth: 80
        , height: "full-80"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailGridTable"
        , cols: [
            [
                {type: "checkbox", fixed: "left", width: 50}
                , {field: 'jscount', title: '��������', align: "center", width: 110, edit: 'text', event: 'countCheck'}
                , {field: 'baseunitcount', title: '�ɹ�����', align: "center", width: 115}
                , {field: 'isCount', title: '�ɽ�������', align: "center", width: 110}
                , {field: 'jstype', title: '�Ƿ����', align: "center",width:110}
                , {
                field: 'isEquipment',
                title: '�Ƿ��豸',
                align: "center",
                width: 100,
                templet: '#orEquipment',
                unresize: true
            }, {
                field: 'enableSn',
                title: '�������к�',
                align: "center",
                width: 120,
                templet: '#orEnableSn',
                unresize: true
            }
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 130}
                , {field: 'description', title: '��������', align: "center", width: 120}
                , {field: 'detailunit', title: '��λ', align: "center", width: 120}
                , {field: 'providerdepname', title: '��Ӧ��', align: "center", width: 220}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('����˴�¼�����������', '.laytable-cell-1-jscount ', {
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
        if (value > data.isCount) {
            layer.alert("�ɽ���������:" + data.isCount + ",�����������ܴ��ڿɽ�������,��������д��������!");
            return false;
        }
        if (value > data.baseunitcount) {
            layer.alert("�����������ܴ��ڲɹ�����,��������д��������!");
            return false;
        }
        if (!/^[0-9]+.?[0-9]*$/.test(value) || value == "0") {
            layer.alert("��������������Ч����,��������д��������!");
            return false;
        }
        if (value == "" || value == null) {
            layer.alert("��������Ϊ��,����д��������!");
            return false;
        }
        if(check.isDot(value)){
            if(!check.isAllowDecimal(data.detailunit)){
                layer.alert(data.detailunit+"��λ��������С��");
                data[field] = 0;
                return;
            }
        }
    });
    var flag = true;
    // �����ϸ
    $("#add").on("click", function (e) {
//    	alert(flag);
    	if(flag){
    		flag = false;
    		parent.layui.$("#reloadStatus").val(1);
            var checkStatus = table.checkStatus('detailGridTable');
            var length = checkStatus.data.length;
            if (length < 1) {
                //���Ϸ�
                layer.msg('������ѡ��һ����¼���');
                flag = true;
            } else {
                var data = checkStatus.data;
                var details = [];
                for (var i = 0; i < length; i++) {

                    //�ύǰ��֤
                    if (data[i].jscount > data[i].isCount) {
                        layer.alert("�����������ܴ��ڿɽ�������,��������д��������!");
                        flag = true;
                        return false;
                    }
                    if (isNaN(data[i].jscount || data[i].jscount == '0')) {
                        layer.alert("��������������Ч����,��������д��������!");
                        flag = true;
                        return false;
                    }
                    if (data[i].jscount == "" || data[i].jscount == null) {
                        layer.alert("��������Ϊ��,����д��������!");
                        flag = true;
                        return false;
                    }
                    if (!/^[0-9]+.?[0-9]*$/.test(data[i].jscount) || data[i].jscount == "0") {
                        layer.alert("��������������Ч����,��������д��������!");
                        flag = true;
                        return false;
                    }

                    var obj = {
                        sheetId: parent.layui.$("#id").val(),
                        sheetDetailId: data[i].id,
                        categoryId: data[i].sparescateId,
                        materialId: data[i].materialId,
                        materialCode: data[i].materialCode,
                        materialName: data[i].materialName,
                        materialBrand: data[i].materialBrand,
                        materialModel: data[i].materialModel,
                        materialSpecification: data[i].materialSpecification,
                        description: data[i].description,
                        detailUnitName: data[i].baseunit,
                        noTaxPrice: data[i].baseunitprice,
                        noTaxSum: data[i].baseunitprice.mul(data[i].jscount),
                        taxPrice: data[i].baseunitprice.mul(1 + data[i].taxRate),
                        taxSum: data[i].baseunitprice.mul(1 + data[i].taxRate).mul(data[i].jscount),
                        taxRate: data[i].taxRate,
                        detailCount: data[i].jscount,
                        isEquipment: $("#isEquipment" + data[i].id).val(),
                        enableSn: $("#enableSn" + data[i].id).val(),
                        ztId: data[i].stockorgid,
                        ownerType: data[i].consignment,
                        providerDepId: data[i].providerdepid,
                        extendString10:data[i].erprownum,
                        extendString1: data[i].extendstring1
                    };
                    details.push(obj);
                }


                $.ajax({
                    type: "POST",
                    url: "../detail/addWZJSDetails",
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
    	}else{
//            flag = true;
            return false;
    	}
        

    });

    form.verify({
            sort: function (value, item) {
                if (value != '' && !/^[0-9]+.?[0-9]*$/.test(value)) {
                    return '����д������';
                }
            }
        }
    );

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
//�˷������������õ���ȷ�ĳ˷����
//˵����javascript�ĳ˷������������������������˵�ʱ���Ƚ����ԡ�����������ؽ�Ϊ��ȷ�ĳ˷������
//���ã�accMul(arg1,arg2)
//����ֵ��arg1����arg2�ľ�ȷ���
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length
    } catch (e) {
    }
    try {
        m += s2.split(".")[1].length
    } catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}

Number.prototype.mul = function (arg) {
    return accMul(arg, this);
};


