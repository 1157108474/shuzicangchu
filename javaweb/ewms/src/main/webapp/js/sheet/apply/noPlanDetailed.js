layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;
    //��ϸ�б�
    var noPlanDetailGr = table.render({
        elem: '#noPlanDetailGrid'
        , url: '/sheet/apply/listNoPlanDetail.json'
        , cellMinWidth: 80
        , height: "full-150"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , where: {
            ztId: $("#ztId").val(),
            officesId : $("#officeId").val()
        }
        , id: "noPlanDetailGridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'applyCount', title: '��������', align: "center", width: 100, edit: 'text',event: 'count'}
                , {field: 'address', title: 'ʹ�õ�ַ', align: "center", width: 120, edit: 'text'}
                , {field: 'storeCount', title: '�������', align: "center", width: 100}
                , {field: 'storeuseCount', title: '����������', align: "center", width: 120}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 80}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 180}
                , {field: 'descripTion', title: '��������', align: "center", width: 320}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('�����������������ʹ�õ�ַ��', '.laytable-cell-1-applyCount', {
                time: 2000
            });
        }
    });
    //��ѯ�ƻ���ϸ
    form.on("submit(formSubmit)", function (data) {
        table.reload('noPlanDetailGridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                materialCode: $("#materialCode").val()
                , descripTion: $("#descripTion").val()
            }
        });
        return false;
    });
    // ������Ԫ��༭
    table.on('edit(noPlanDetailGrid)', function (obj) {
        var data = obj.data; //�����е������������
        var field = obj.field; //��ǰ�༭���ֶ���
        var value = obj.value; //�õ��޸ĺ��ֵ

        if(check.isDot(value)){
            if(!check.isAllowDecimal(data.detailUnitName)){
                layer.alert(data.detailUnitName+"��λ��������С��");
                data[field] = 0;
                return;
            }
        }
    });

    //�޼ƻ���ϸ����
    $("#detail_add").on("click", function (e) {
        var applyCount, address;
        var details = [];
        var rows = table.checkStatus('noPlanDetailGridTable').data;
        parent.layui.$("#reloadDetailsgrid").val(1);
        if (rows.length > 0) {

            for (var i = 0; i < rows.length; i++) {
                applyCount = rows[i].applyCount;//��ȡ��������
                address = rows[i].address;//��ȡ��ַ
                if (null == address || address == "") {
                    layer.msg("����дʹ�õ�ַ");
                    return;
                }
                if (null == applyCount || applyCount == "") {
                    layer.msg("��������Ϊ��,����д����������");
                    return;
                } else if (isNaN(applyCount) || applyCount == '0' || applyCount <= 0) {
                    layer.msg("��������������Ч����,����д��д����������");
                    return;
                } else if (applyCount > rows[i].storeCount) {
                    layer.msg("�����������ܴ��ڿ������,��������д����������");
                    return;
                } else if (applyCount > rows[i].storeuseCount) {
                    layer.msg("�����������ܴ��ڿ���������,��������д����������");
                    return;
                }
                var obj = {
                    sheetId: $("#sheetId").val(),
                    materialCode: rows[i].materialCode,
                    materialId: rows[i].materiaId,
                    description: rows[i].descripTion,
                    detailUnitName: rows[i].detailUnitName,
                    //detailunit: rows[i].DETAILUNIT,
                    detailCount: applyCount,//��������
                    extendString2: address,//ʹ�õ�ַ
                    ztId: $("#ztId").val()
                };
                details.push(obj);
            }
            console.log(details);
            $.ajax({
                type: "POST",
                url: "/sheet/apply/saveDetail.json",
                dataType: "json",
                data: {details: JSON.stringify(details)},
                success: function (result) {
                    top.layer.msg(result.message, {time: 500});
                    if ('1' == result.status) {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }
                }
            });
        } else {
            layer.msg("��ѡ��һ����ϸ");
        }

    })


});

