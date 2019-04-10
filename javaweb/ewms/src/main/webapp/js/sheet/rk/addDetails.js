layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var ran = Math.random();

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
        , where: {ran: ran++, jsCode: $("#num").val()}
        , id: "detailGridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'rkCount', title: '�������', align: "center", width: 100, edit: 'text', event: 'countCheck'}
                , {field: 'wareHouseName', title: '�ⷿ', align: "center", width: 100, edit: 'text', event: 'wareHouse',templet: function (d) {
                    if(d.jsType=='����'){
                        return '���ۿ�';
                    }else {
                        return '';
                    }
            }}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 156}
                , {field: 'description', title: '��������', align: "center", width: 170}
                , {field: 'jsCount', title: '��������', align: "center", width: 120}
                , {
                field: 'kyCount', title: '���������', align: "center", width: 120, templet: function (d) {
                    return d.jsCount - d.isCount;
                }
            }
                , {field: 'jsType', title: '�Ƿ����', align: "center", width: 100}
                , {
                field: 'isEquipment',
                title: '�Ƿ��豸',
                align: "center",
                width: 100,
                unresize: true,
                templet: function (d) {
                    if (d.isEquipment == '1') {
                        return "��";
                    } else {
                        return "��";
                    }
                }
            }
                , {
                field: 'enableSn', title: '�������к�', align: "center", width: 120, unresize: true, templet: function (d) {
                    if (d.enableSn == '1') {
                        return "����";
                    } else {
                        return "������";
                    }
                }
            }
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 120}
                , {field: 'planDepName', title: '�ƻ�����', align: "center", width: 170}
                , {field: 'expirationTime', title: '�ʱ�����', align: "center", width: 170}
                , {field: 'extendDate2', title: '��������', align: "center", width: 170}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('����˴�¼�����������', '.laytable-cell-1-rkCount ', {
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

    //����������
    table.on('tool(detailGrid)', function (obj) {
        var data = obj.data;
        if (obj.event === 'wareHouse') {
            layer.open({
                title: "�ⷿ"
                , type: 2
                , fixed: false
                , area: ['580px', '400px']
                , content: "/system/ware/locationWare"
                , end: function () {
                    obj.update({
                        wareHouseName: $("#newlocationName").val(),
                        wareHouseCode: $("#newlocationCode").val()
                    });
                }
            })
        }

    });
    form.on("submit(formSubmit)", function (data) {
        reload(data);
        return false;
    });


    // ������Ԫ��༭
    table.on('edit(detailGrid)', function (obj) {
        var data = obj.data; //�����е������������
        var field = obj.field; //��ǰ�༭���ֶ���
        var value = obj.value; //�õ��޸ĺ��ֵ

        var kCount = data.jsCount - data.isCount;

        if (value > kCount) {
            data.jscount = 0;
            layer.alert("�����������:" + kCount + ",����������ܴ��ڿ��������,��������д�������!");
            return;
        }
        if (value > data.jsCount) {
            layer.alert("����������ܴ��ڽ�������,��������д���������");
            data[field] = 0;
            return;
        }
        if (!/^[0-9]+.?[0-9]*$/.test(value) || value == "0") {
            layer.alert("�������������Ч����,��������д�������!");
            data[field] = 0;
            return;
        }
        if (value == "" || value == null) {
            layer.alert("�������Ϊ��,����д�������!");
            data[field] = 0;
            return;
        }
        if(check.isDot(value)){
            if(!check.isAllowDecimal(data.detailUnitName)){
            	if("��"!=data.detailUnitName && "��"!=data.detailUnitName && "����"!=data.detailUnitName && "��"!=data.detailUnitName){
            		layer.alert(data.detailUnitName+"��λ��������С��");
                    data[field] = 0;
                    return;
            	}
            }
        }
    });

    var bool = true;
    // �����ϸ
    $("#add").on("click", function (e) {
        if (bool) {
            bool = false;
            parent.layui.$("#reloadStatus").val(1);
            var checkStatus = table.checkStatus('detailGridTable');
            var length = checkStatus.data.length;
            if (length < 1) {
                //���Ϸ�
                layer.msg('������ѡ��һ����¼���');
                bool = true;
            } else {
                var data = checkStatus.data;
                var details = [];
                var kfcode;
                for (var i = 0; i < length; i++) {
                    console.log(data[i]);
                    if (data[i].rkCount > data[i].kyCount) {
                        data[i].rkCount = 0;
                        layer.alert("�����������:" + data[i].kyCount + ",����������ܴ��ڿ��������,��������д�������!");
                        bool = true;
                        return false;
                    }
                    if (data[i].rkCount > data[i].jsCount) {
                        layer.alert("����������ܴ��ڽ�������,��������д���������");
                        data[i].rkCount = 0;
                        bool = true;
                        return false;
                    }
                    if (!/^[0-9]+.?[0-9]*$/.test(data[i].rkCount) || data[i].rkCount == "0") {
                        layer.alert("�������������Ч����,��������д�������!");
                        data[i].rkCount = 0;
                        bool = true;
                        return false;
                    }
                    if(data[i].jsType !='����'){
                        if (data[i].rkCount == "" || data[i].rkCount == null) {
                            layer.alert("�������Ϊ��,����д�������!");
                            data[i].rkCount = 0;
                            bool = true;
                            return false;
                        }
                        if (data[i].wareHouseCode == "" || data[i].wareHouseCode == null) {
                            layer.alert("��ѡ��ⷿ��");
                            bool = true;
                            return false;
                        }
                    }

                    if (data[i].isEquipment == "1") {
                        if (!(/^(\+|-)?\d+$/.test(data[i].rkCount))) {
                            layer.alert("�豸���������������������,��������д��ȷ��������");
                            data[i].rkCount = 0;
                            bool = true;
                            return false;
                        }
                    }

                    /*if (data[i].jsTypeCode == "IsJS") {
                        kfcode = "MDXNY02";
                    } else if (data[i].jsTypeCode == "NoJs") {
                        if (data[i].isEquipment == 1) {
                            kfcode = "MDXNY01";
                        } else {
                            kfcode = "MD159AK";
                        }
                    } else if (data[i].jsTypeCode == "ZSP") {
                        kfcode = data[i].extendString1;
                    }*/
                    var obj = {
                        sheetid: parent.layui.$("#id").val(),
                        sheetdetailid: data[i].id,
                        categoryid: data[i].categoryId,
                        materialid: data[i].materialId,
                        materialcode: data[i].materialCode,
                        materialname: data[i].materialName,
                        materialbrand: data[i].materialBrand,
                        materialmodel: data[i].materialModel,
                        materialspecification: data[i].materialSpecification,
                        detailunitname: data[i].detailUnitName,
                        notaxprice: data[i].notaxPrice,
                        notaxsum: data[i].notaxPrice.mul(data[i].rkCount),
                        taxprice: data[i].taxprice,
                        taxsum: data[i].notaxPrice.mul(1 + data[i].taxRate).mul(data[i].rkCount),
                        taxRate: data[i].taxRate,
                        description: data[i].description,
                        detailcount: data[i].rkCount,
                        isequipment: data[i].isEquipment,
                        enablesn: data[i].enableSn,
                        ztid: data[i].ztId,
                        ownertype: data[i].ownerType,
                        extendstring1: data[i].wareHouseCode,
                        extendint1: data[i].sheetDetailId,
                        extendfloat1: data[i].notaxPrice.mul(1 + data[i].taxRate),
                        providerdepid: data[i].providerDepId,
                        plandepartid: data[i].planDepartId,          //�ƻ�����
                        extenddate2: data[i].extendDate2,              //����ʱ��
                        extendstring10: data[i].erpRowNum,           //erp�к�
                        expirationtime: data[i].expirationTime      //�ʱ�ʱ��
                    };
                    details.push(obj);
                }
                $.ajax({
                    type: "POST",
                    url: "../rkDetail/addRKDetails",
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
                            bool = true;
                        }
                    },
                    error: function (XMLHttpRequest) {
                        layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                        bool = true;
                    }
                });
            }
            return false;
        } else {
            layer.alert("���ύ�������ظ��ύ��");
            bool = true;
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


