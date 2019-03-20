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
    var planDetailGr = table.render({
        elem: '#planDetailGrid'
        , url: '/sheet/apply/listPlanDetail.json'
        , cellMinWidth: 80
        , height: "full-150"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , where: {
            ztId: $("#ztId").val()
            , useDepId: $("#useDepId").val()
        }
        , id: "planDetailGridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'applyCount', title: '��������', align: "center", width: 120, edit: 'text',event: 'count'}
                , {field: 'address', title: '��;', align: "center", width: 120, edit: 'text'}
                , {field: 'planCount', title: '�ƻ�����', align: "center", width: 80}
                , {field: 'haveslCount', title: '����������', align: "center", width: 100}
                , {field: 'storeCount', title: '�������', align: "center", width: 80}
                , {field: 'storeuseCount', title: '����������', align: "center", width: 120}
                , {field: 'unIt', title: '��λ', align: "center", width: 60}
                , {field: 'planCode', title: '�ƻ����', align: "center", width: 150}
                , {field: 'userDepName', title: 'ʹ�õ�λ', align: "center", width: 220}
                , {
                field: 'planDate', title: '�ƻ�����', align: "center", width: 100, templet: function (d) {
                    return vipTable.dateformat(d.planDate);
                }
            }
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 150}
                , {field: 'materialDes', title: '��������', align: "center", width: 320}
            ]
        ]
        , done: function (res, curr, count) {
            layer.tips('�������������������;��', '.laytable-cell-1-applyCount', {
                time: 2000
            });
        }
    });
    //��ѯ�ƻ���ϸ
    form.on("submit(formSubmit)", function (data) {
        table.reload('planDetailGridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                planCode: $("#planCode").val()
                , materialCode: $("#materialCode").val()
                , materialDes: $("#materialDes").val()
            }
        });
        return false;
    });

    // ������Ԫ��༭
    table.on('edit(planDetailGrid)', function (obj) {
        var data = obj.data; //�����е������������
        var field = obj.field; //��ǰ�༭���ֶ���
        var value = obj.value; //�õ��޸ĺ��ֵ

        if(check.isDot(value)){
            if(!check.isAllowDecimal(data.unIt)){
            	if("��"!=data.unIt && "��"!=data.unIt){
            		layer.alert(data.unIt+"��λ��������С��");
                    data[field] = 0;
                    return;
            	}
                
            }
        }
    });

    //�ƻ���ϸ����
    $("#detail_add").on("click", function (e) {
        var flag = 1;
        var applyCount, address;
        var details = [];
        var rows = table.checkStatus('planDetailGridTable').data;
        parent.layui.$("#reloadDetailsgrid").val(1);
        if (rows.length > 0) {
            for (var i = 0; i < rows.length; i++) {
                applyCount = rows[i].applyCount;//��ȡ��������
                address = rows[i].address;//��ȡ��ַ
                if (null == address || address == "") {
                    layer.msg("����д��;");
                    return;
                }
                if (isNaN(applyCount) || applyCount == '0' || applyCount <= 0) {
                    layer.msg("��������������Ч����,����д��д����������");
                    return;
                } else if (null == applyCount || applyCount == "") {
                    layer.msg("��������Ϊ��,����д����������");
                    return;
                } else if (applyCount > rows[i].planCount) {
                    layer.msg("�����������ܴ��ڼƻ�����,��������д����������");
                    return;
                } else if (applyCount > rows[i].storeCount) {
                    layer.msg("�����������ܴ��ڿ������,��������д����������");
                    return;
                } else if ((parseInt(applyCount) + parseInt(rows[i].haveslCount)) > rows[i].planCount) {
                    layer.msg("�������������������������ڼƻ���������������д����������");
                    return;
                } else if (applyCount > rows[i].storeuseCount) {
                    layer.msg("�����������ܴ��ڿ���������,��������д����������");
                    return;
                }

                var obj = {
                    sheetId: $("#sheetId").val(),
                    snCode: rows[i].planCode,//�ƻ����
                    sheetDetailId: rows[i].id,
                    materialCode: rows[i].materialCode,
                    description: rows[i].materialDes,
                    detailUnitName: rows[i].unIt,
                    extendDate1: rows[i].planDate,
                    detailCount: applyCount,//��������
                    extendString2: address,//��;
                    ztId: $("#ztId").val()
                };
                details.push(obj);
            }
            $.ajax({
                type: "POST",
                url: "/sheet/apply/saveDetail.json",
                dataType: "json",
                data: {details: JSON.stringify(details)},
                success: function (ret) {
                    top.layer.msg(ret.message, {
                        time: 500
                    });
                    if ('1' == ret.status) {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    } else {
                        layer.alert("����ʧ�ܣ�ʧ��ԭ��" + ret.message);
                    }
                }
            });
        } else {
            layer.msg("��ѡ��һ����ϸ");
        }

    })
});

