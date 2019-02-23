layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var form = layui.form;
    var table = layui.table;
    var $ = layui.jquery;
    var laydate = layui.laydate;

    //��ȡ������Ϣ�б�
    $("#materialNameBtn").on("click", function () {
        var url = "/system/material/listMaterial";
        layui.layer.open({
            title: "������Ϣ"
            , type: 2
            , fixed: false
            , area: ['1000px', '360px']
            , content: url
        });
        return false;
    });


    // ������ϸ
    $("#add").on("click", function (e) {
        if (Is_DetailValid()) {
            if ($("#sheetDetailId").val() == "") {
                saveSheetDetails();
            } else {
                updateSheetDetails();
            }
        }

        return false;
    });

    // ����select�¼�
    form.on('select(isopen)', function (data) {
        var selectValue = data.value;
        if (selectValue == 1) {
            $("#enablesn").removeAttr("disabled");
            form.render();
        } else {
            $("#enablesn").attr("disabled", true);
            form.render();
        }
    });

    laydate.render({
        elem: '#expirationtime'
        , type: 'datetime'
    });

    laydate.render({
        elem: '#extenddate2'
        , type: 'datetime'
    });

    //��֤��ϸ������
    function Is_DetailValid() {
        var is_ok = false;
        if ($("#materialName").val() == "") {
            layer.alert("�������Ʋ���Ϊ��,��ѡ�����ϣ�", "����");
            return;
        } else {
            is_ok = true;
        }
        if ($("#materialCode").val() == "") {
            layer.alert("���ϱ��벻��Ϊ�գ�", "����");
            return;
        } else {
            is_ok = true;
        }
        var count = $("#detailCount").val();
        if (count == null || count.trim() == '' ) {
            layer.alert("��������Ϊ��,��������д������");
            return;
        } else {
            if (count.trim() == 0) {
                layer.alert("��������Ϊ0��");
                return;
            } else {
                is_ok = true;
            }
        }
        if ($("#detailUnitName").val() == "") {
            layer.alert("��ѡ��λ��", "����");
            return;
        } else {
            is_ok = true;
        }
        var taxRate = $("#taxRate").val();
        if (taxRate == null || taxRate.trim() == '' || isNaN(taxRate)) {
            layer.alert("��������Ϊ��,��������д������");
            return;
        } else {
            if (!(/0\.[0-9]+/).test(taxRate)) {
                parent.layer.alert("˰�ʲ��ô���1С��0����������λС����");
                return;
            } else {
                is_ok = true;
            }
        }
        var price = $("#noTaxPrice").val();
        if (price == null || price.trim() == '' || isNaN(price.trim())) {
            layer.alert("����˰���۲���Ϊ��,��������д����˰���ۣ�");
            return;
        } else {
            if (price.trim() == 0) {
                layer.alert("����˰���۲���Ϊ0��");
                return;
            } else {
                is_ok = true;
            }
        }
        if ($("#noTaxSum").val() == "") {
            layer.alert("����˰�ܽ���ȷ��", "����");
            return;
        } else {
            is_ok = true;
        }
        var isEquipment = $("#isequipment").val();
        if (isEquipment == null || isEquipment.trim() == '') {
            layer.alert("��ѡ���Ƿ��豸��");
            return;
        } else {
            is_ok = true;
        }
        var enablesn = $("#enablesn").val();
        /*if (enablesn == null || enablesn.trim() == '') {
            layer.alert("��ѡ���Ƿ��������кţ�");
            return;
        }*/
        if ($("#storeId").val() == "") {
            layer.alert("��ѡ��ⷿ��", "����");
            return;
        } else {
            is_ok = true;
        }

        var expirationtime = $("#expirationtime").val();
        if (expirationtime == null || expirationtime.trim() == '') {
            layer.alert("��ѡ�������ޣ�");
            return;
        } else {
            is_ok = true;
        }
        var extenddate2 = $("#extenddate2").val();
        if (extenddate2 == null || extenddate2.trim() == '') {
            layer.alert("��ѡ������ʱ�䣡");
            return;
        } else {
            is_ok = true;
        }
        var extendint7 = $("#plandepartid").val();
        if (extendint7 == null || extendint7.trim() == '') {
            layer.alert("��ѡ��ƻ����ţ�");
            return;
        } else {
            is_ok = true;
        }
        return is_ok;
    };
    //���浥�ݷ���
    var saveSheetDetails = function () {
        var loading = layer.msg("������", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });
        var sheetId = parent.layui.$("#id").val();
        var details = [];
        var noTaxPrice = parseFloat($("#noTaxPrice").val());
        var taxRate = parseFloat($("#taxRate").val());
        var count = parseFloat($("#detailCount").val());
        var ztId = parent.layui.$("#userZtid").val();
        var obj;
        obj = {
            sheetid: sheetId,
            materialid: $("#materialId").val(),
            categoryid: $("#sparescateId").val(),
            materialcode: $("#materialCode").val(),
            materialname: $("#materialName").val(),
            materialbrand: $("#brand").val(),
            materialmodel: $("#model").val(),
            materialspecification: $("#specifications").val(),
            detailcount: $("#detailCount").val(),
            detailunitname: $("#detailUnitName").val(),
            notaxprice: $("#noTaxPrice").val(),
            notaxsum: $("#noTaxSum").val(),
            taxprice: 0,
            taxRate: $("#taxRate").val(),
            taxsum: noTaxPrice.mul(1 + taxRate).mul(count),
            ztid: ztId,
            ownertype: 610,
            description: $("#description").val(),
            isequipment: $("#isequipment").val(),
            enablesn: $("#enablesn").val(),
            extendstring1: $("#storeId").val(),
            extendfloat1: noTaxPrice.mul(1 + taxRate),
            providerdepid: $("#providerId").val(),
            plandepartid: $("#plandepartid").val(),
            expirationtime: $("#expirationtime").val(),
            extenddate2: $("#extenddate2").val()
        };
        details.push(obj);
        console.log(details);
        $.ajax({
            type: "POST",
            url: "../rkDetail/addZRDetails",
            dataType: "json",
            data: {details: JSON.stringify(details)},
            success: function (ret) {
                if (ret.status == '1') {
                    layer.msg('��ӳɹ�', function () {
                        parent.layui.$("#reloadStatus").val(1);
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
    };

    //ȡ����ť
    $("#reset").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

});

//��ȡ����ҳ��ش�����
function setMaterialInfo(data) {
    document.getElementById("materialId").value = data.id;
    document.getElementById("materialName").value = data.name;
    document.getElementById("materialCode").value = data.code;
    document.getElementById("detailUnitName").value = data.unit;
    document.getElementById("description").value = data.description;
    document.getElementById("sparescateId").value = data.sparescateId;
    document.getElementById("brand").value = data.brand;
    document.getElementById("specifications").value = data.specifications;
    /*document.getElementById("providerId").value = data.providerId;*/
}

function sumAmount(value) {
    if (!(/^(?:[1-9]+\d*|0)(\.\d+)?$/).test(value)) {
        parent.layer.alert("ֻ��������������С����");
    }
    var detailcount = $("#detailCount").val();
    var notaxprice = $("#noTaxPrice").val();
    var mn = 0;
    if (detailcount != "" && notaxprice != "") {
        mn = parseFloat(detailcount) * parseFloat(notaxprice);
    }
    $("#noTaxSum").val(mn.toFixed(2));
}

function rateAmount(value) {
    if (!(/0\.[0-9]+/).test(value)) {
        parent.layer.alert("˰�ʲ��ô���1С��0����������λС����");
    }
}

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