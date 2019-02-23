layui.use(['laydate', 'form', 'table', 'layer', 'element'], function () {

    var layer = parent.layer === undefined ? layui.layer : top.layer;
    var form = layui.form;
    var table = layui.table;
    var $ = layui.$;
    var element = layui.element;
    var ran = Math.random();
    var vipTable = layui.vip_table;

    //��ȡ������Ϣ�б�
    $("#materialNameBtn").on("click", function () {
        var url = "/system/material/listMaterial";
        layui.layer.open({
            title: "������Ϣ"
            , type: 2
            , fixed: false
            , area: ['1000px', '420px']
            , content: url
        });
        return false;
    });
    //�ⷿ
    var before = '';
    form.on('select(store)', function (store) {
        var current = store.value;
        if (current != before) {
            before = current;
            $("#locationName").val('');
            $("#locationId").val('');
            $("#locationCode").val('');
        }
    });
    //���ݿⷿ��Ϣ���ؿ�λ
    $("#storeLocation").on("click", function (e) {
        if (before == '') {
            layer.alert("����ѡ��ⷿ");
        } else {
            var url = "../../system/ware/location?parentId=" + before;
            layui.layer.open({
                title: "��λ"
                , type: 2
                , fixed: false
                , area: ['90%', '90%']
                , content: url
            });
        }
        return false;
    });

    // ������ϸ
    $("#add").on("click", function (e) {
        if (Is_DetailValid()) {
            if ($("#sheetDetailId").val() == ""){
                saveSheetDetails();
            }else {
                updateSheetDetails();
            }
        }

        return false;
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
        var count=$("#detailCount").val()
        if (count == null || count.trim() == '' || isNaN(count.trim())) {
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
        var price=$("#noTaxPrice").val()
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
        if ($("#storeId").val() == "") {
            layer.alert("��ѡ��ⷿ��", "����");
            return;
        } else {
            is_ok = true;
        }
        if ($("#locationId").val() == "") {
            layer.alert("��ѡ���λ��", "����");
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
        var userZtId = parent.layui.$("#userZtId").val();
        var details = [];
        var obj;
        var obj = {
            sheetId: sheetId,
            materialId: $("#materialId").val(),
            tagCode:$("#materialCode").val(),
            materialCode: $("#materialCode").val(),
            materialName: $("#materialName").val(),
            materialSpecification: $("#materialSpecification").val(),
            materialModel: $("#materialModel").val(),
            detailCount: $("#detailCount").val(),
            detailUnitName: $("#detailUnitName").val(),
            noTaxPrice: $("#noTaxPrice").val(),
            noTaxSum: $("#noTaxSum").val(),
            storeId: $("#storeId").val(),
            storeLocationId: $("#locationId").val(),
            storeLocationName:$("#locationName").val(),
            storeLocationCode:$("#locationCode").val(),
            description: $("#description").val(),
            materialBrand:$("#materialBrand").val(),
            ztId: userZtId
        };
        details.push(obj);
        $.ajax({
            type: "POST",
            url: "../detail/addwzjcrkdDetails",
            dataType: "json",
            data: {details: JSON.stringify(details)},
            success: function (ret) {
                if (ret.status == 1) {
                    layer.msg('��ӳɹ�', function () {
                        parent.layui.$("#reloadStatus").val(1);
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else {
                    layer.alert("����ʧ�ܣ�ʧ��ԭ��" + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };
    //�༭���浥�ݷ���
    var updateSheetDetails = function () {
        var loading = layer.msg("������", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });
        var sheetId = parent.layui.$("#id").val();
        var userZtId = parent.layui.$("#userZtId").val();
        var details = [];
        var obj;
        var obj = {
            id:$("#sheetDetailId").val(),
            sheetId: sheetId,
            materialId: $("#materialId").val(),
            tagCode:$("#materialCode").val(),
            materialCode: $("#materialCode").val(),
            materialName: $("#materialName").val(),
            materialSpecification: $("#materialSpecification").val(),
            materialModel: $("#materialModel").val(),
            detailCount: $("#detailCount").val(),
            detailUnitName: $("#detailUnitName").val(),
            noTaxPrice: $("#noTaxPrice").val(),
            noTaxSum: $("#noTaxSum").val(),
            storeId: $("#storeId").val(),
            storeLocationId: $("#locationId").val(),
            storeLocationName:$("#locationName").val(),
            storeLocationCode:$("#locationCode").val(),
            description: $("#description").val(),
            materialBrand:$("#materialBrand").val(),
            ztId: userZtId
        };
        details.push(obj);
        $.ajax({
            type: "POST",
            url: "../jcwzrk/editDetail",
            dataType: "json",
            data: {details: JSON.stringify(details)},
            success: function (ret) {
                if (ret.status == 1) {
                    layer.msg('��ӳɹ�', function () {
                        parent.layui.$("#reloadStatus").val(1);
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else {
                    layer.alert("����ʧ�ܣ�ʧ��ԭ��" + ret.message);
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
    document.getElementById("materialSpecification").value = data.specifications;//���
    document.getElementById("materialModel").value = data.models;//�ͺ�
    document.getElementById("detailUnitName").value = data.unit;
    document.getElementById("description").value = data.description;
    document.getElementById("materialBrand").value = data.brand;//Ʒ��
}
function sumAmount(){
    var detailcount =  $("#detailCount").val();
    var notaxprice = $("#noTaxPrice").val();
    var mn=0;
    if (detailcount!=""&&notaxprice!=""){
        mn = parseFloat(detailcount)*parseFloat(notaxprice);
    }
    $("#noTaxSum").val(mn.toFixed(2));
}