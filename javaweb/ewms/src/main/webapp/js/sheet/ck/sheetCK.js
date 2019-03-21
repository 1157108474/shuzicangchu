layui.config({
    base: '/js/static/' // ģ��Ŀ¼
}).use(['laydate', 'form', 'table', 'layer', 'element', 'vip_table'], function () {

    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var vipTable = layui.vip_table;
    var $ = layui.$;
    var element = layui.element;
    var ran = Math.random();
    //��ϸ�б�
    var detailsgr = table.render({
        elem: '#detailsgrid'
        , url: '/sheet/ck/listSheetCK.json'
        , cellMinWidth: 80
        , height: "full-70"
        , method: 'post'
        , where: {sheetId: $("#id").val()}
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailsgridTable"
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 120}
                , {field: 'detailUnitName', title: '������λ', align: "center", width: 180}
                , {field: 'detailCount', title: '��������', align: "center", width: 100}
                , {field: 'houseCode', title: '�ⷿ����', align: "center", width: 120}
                , {field: 'houseName', title: '�ⷿ', align: "center", width: 150}
                , {field: 'extendstring1', title: '�ƻ����', align: "center", width: 100}
                , {field: 'noTaxPriceDouble', title: '����˰����', align: "center", width: 100}
                , {field: 'noTaxSumDouble', title: '����˰���', align: "center", width: 100}
                , {field: 'description', title: '��������', align: "center", width: 320}
            ]
        ]
    });

    function ckReload() {
        table.reload('detailsgridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val()
            }
        })
    }

    window.reloadHis = function () {
        //���Ա��ⲿ����
        table.reload('activitiHisTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                taskId: $("#taskId").val()
            }
        });
    }
    $("#print").on("click", function (e) {
    	var taskId = $("#taskId").val();
        vipTable.openPage("��ӡ���ʳ��ⵥ", "/system/print/sheet/WZCK-" + $("#id").val() + "?taskId=" + taskId  , '95%', '95%');
    });
    // ����select�¼�
    form.on('select(ckType)', function (data) {
        var selectValue = data.value;
        var selectName = data.elem[data.elem.selectedIndex].text;
        if (selectName == 'ֱ�ӳ���') {
            showZjChange();
            hideSlChange();
            hideDbChange();
        } else if (selectName == '�������') {
            showSlChange();
            hideDbChange();
            hideZjChange();
        } else if (selectName == '��������') {
            showDbChange();
            hideZjChange();
            hideSlChange();
        } else {
            hideZjChange();
            hideSlChange();
            hideDbChange();
        }
        form.render('select'); //������Ⱦselect���������Ҫ
    });
    if (null != $("#id").val() && '' != $("#id").val()) {
        hideTypeChange();
        form.render();
    }

    //�������� �����������
    function hideTypeChange() {
        //��������
        $("#type").addClass("layui-disabled");
        $("#type").attr("disabled", true);
    }

    //��������-�������� �����������
    function hideAllChange() {
        //��������
        $("#dbCode").addClass("layui-disabled");
        $("#openDb").addClass("layui-disabled");
        $("#dbCode").attr("disabled", true);
        $("#openDb").attr("disabled", true);
        //���뵥��
        $("#applyCode").addClass(" layui-disabled");
        $("#openApply").addClass(" layui-disabled");
        $("#applyCode").attr("disabled", true);
        $("#openApply").attr("disabled", true);
        //ʹ�õ�λ
        $("#useDepName").addClass(" layui-disabled");
        $("#openUseDep").addClass(" layui-disabled");
        $("#useDepName").attr("disabled", true);
        $("#openUseDep").attr("disabled", true);
        // //�ϼ���������
        // $("#officesId").addClass(" layui-disabled");
        // $("#departOfficename").addClass(" layui-disabled");
        // $("#openOffice").addClass(" layui-disabled");
        // $("#officesId").attr("disabled", true);
        // $("#departOfficename").attr("disabled", true);
        // $("#openOffice").attr("disabled", true);
        //���뵥λ
        $("#applyDep").addClass(" layui-disabled");
        $("#applyDep").attr("disabled", true);
        //�ʽ���Դ
        $("#fundSourcesChange").addClass(" layui-disabled");
        $("#fundSourcesChange").attr("disabled", true);
        //��;
        $("#extendstring1").addClass(" layui-disabled");
        $("#extendstring1").attr("disabled", true);
        //��ע
        $("#memo").addClass(" layui-disabled");
        $("#memo").attr("disabled", true);
    }

    //��������-ֱ�ӳ��� ȥ����������
    function showZjChange() {
        //ʹ�õ�λ
        $("#useDepName").removeClass(" layui-disabled");
        $("#openUseDep").removeClass(" layui-disabled");
        $("#useDepName").attr("disabled", false);
        $("#openUseDep").attr("disabled", false);
        $("#useDepName").val('');
        $("#openUseDep").val('');
        // //�ϼ���������
        // $("#officesId").removeClass(" layui-disabled");
        // $("#departOfficename").removeClass(" layui-disabled");
        // $("#openOffice").removeClass(" layui-disabled");
        // $("#officesId").attr("disabled", false);
        // $("#departOfficename").attr("disabled", false);
        // $("#openOffice").attr("disabled", false);
        // $("#officesId").val('');
        // $("#departOfficename").val('');
        // $("#departOfficename").html('');
        // $("#openOffice").val('');
        //���뵥λ
        $("#applyDep").removeClass(" layui-disabled");
        $("#applyDep").attr("disabled", false);
        $("#applyDep").val('');
        //�ʽ���Դ
        $("#fundSourcesChange").removeClass(" layui-disabled");
        $("#fundSourcesChange").attr("disabled", false);
        $("#fundSourcesChange").val('');
        //��;
        $("#extendstring1").removeClass(" layui-disabled");
        $("#extendstring1").attr("disabled", false);
        $("#extendstring1").val('');
        //��ע
        $("#memo").removeClass(" layui-disabled");
        $("#memo").attr("disabled", false);
        $("#memo").val('');
    }

    //��������-ֱ�ӳ��� �����������
    function hideZjChange() {
        //ʹ�õ�λ
        $("#useDepName").addClass(" layui-disabled");
        $("#openUseDep").addClass(" layui-disabled");
        $("#useDepName").attr("disabled", true);
        $("#openUseDep").attr("disabled", true);
        $("#useDepName").val('');
        $("#openUseDep").val('');
        // //�ϼ���������
        // $("#officesId").addClass(" layui-disabled");
        // $("#departOfficename").addClass(" layui-disabled");
        // $("#openOffice").addClass(" layui-disabled");
        // $("#officesId").attr("disabled", true);
        // $("#departOfficename").attr("disabled", true);
        // $("#openOffice").attr("disabled", true);
        // $("#officesId").val('');
        // $("#departOfficename").val('');
        // $("#departOfficename").html('');
        // $("#openOffice").val('');
        //���뵥λ
        $("#applyDep").addClass(" layui-disabled");
        $("#applyDep").attr("disabled", true);
        $("#applyDep").val('');
        //�ʽ���Դ
        $("#fundSourcesChange").addClass(" layui-disabled");
        $("#fundSourcesChange").attr("disabled", true);
        $("#fundSourcesChange").val('');
        //��;
        $("#extendstring1").addClass(" layui-disabled");
        $("#extendstring1").attr("disabled", true);
        $("#extendstring1").val('');
        //��ע
        $("#memo").addClass(" layui-disabled");
        $("#memo").attr("disabled", true);
        $("#memo").val('');
    }

    //��������-������� ȥ����������
    function showSlChange() {
        //���뵥��
        $("#applyCode").removeClass(" layui-disabled");
        $("#openApply").removeClass(" layui-disabled");
        $("#applyCode").attr("disabled", false);
        $("#openApply").attr("disabled", false);
    }

    //��������-������� �����������
    function hideSlChange() {
        //���뵥��
        $("#applyCode").addClass(" layui-disabled");
        $("#openApply").addClass(" layui-disabled");
        $("#applyCode").attr("disabled", true);
        $("#openApply").attr("disabled", true);
        $("#applyCode").val('');
        $("#openApply").val('');
    }

    //��������-�������� ȥ����������
    function showDbChange() {
        //��������
        $("#dbCode").removeClass(" layui-disabled");
        $("#openDb").removeClass(" layui-disabled");
        $("#dbCode").attr("disabled", false);
        $("#openDb").attr("disabled", false);
    }

    //��������-�������� �����������
    function hideDbChange() {
        //��������
        $("#dbCode").addClass("layui-disabled");
        $("#openDb").addClass("layui-disabled");
        $("#dbCode").attr("disabled", true);
        $("#openDb").attr("disabled", true);
        $("#dbCode").val('');
        $("#openDb").val('');
    }

    //�����ϼ���������ҳ��
    $("#openOffice").on("click", function (e) {
        var url = "/system/useDep/generalUseDepDepart.htm?ztId=" + $("#ztId").val();
        vipTable.openPage("����", url, "75%", "85%");
    });
    //�������쵥��ҳ��
    $("#openApply").on("click", function (e) {
        if ($("#ckType select").val() == '449') {
            var url = "/sheet/apply/openApply.htm?ztId=" + $("#ztId").val();
            vipTable.openPage("���쵥��", url, "1080px", "520px");
        } else {
            return false;
        }
    });
    //�����������ҳ��
    $("#openDb").on("click", function (e) {
        if ($("#ckType select").val() == '772') {
            var url = "/sheet/db/dbd?type=ck&ztId=" + $("#ztId").val();
            vipTable.openPage("��������", url, "480px", "320px");
        } else {
            return false;
        }
    });
    //����ʹ�õ�λҳ��
    $("#openUseDep").on("click", function (e) {
        if ($("#ckType select").val() == '448') {
            var url = "/system/useDep/generalUseDep.htm?ztId=" + $("#ztId").val();
            vipTable.openPage("ʹ�õ�λ", url, "500px", "80%");
        } else {
            return false;
        }

    });
    //���������ϸҳ��
    $("#addDetails").on("click", function (e) {
        var cktype = $("#type option:checked").text();
        var id = $("#id").val();
        if (null == id || id == "") {
            saveSheet(cktype, true);
        } else {
            updateSheet(cktype, true, id);
        }
    });
    //���水ť
    $("#saveSheet").on("click", function (e) {
        var cktype = $("#type option:checked").text();
        var id = $("#id").val();
        if (null == id || id == "") {
            saveSheet(cktype, false);
        } else {
            updateSheet(cktype, false, id);
        }
    });

    //���桢�޸ĵ��ݷ���
    function saveSheet(cktype, bool) {
        if (checkSheet()) {
            var ztId = $("#ztId").val();
            var json = {
                "type": "CKD"//��������
                , "typeid": $("#ckType select").val() //��������
                , "menuCode": $("#menuCode").val()
                , "ztid": ztId
                , "extendint1": $("#applyId").val()//���쵥��id
                , "useddepartid": $("#usedDepartId").val()//ʹ�õ�λId
                , "extendstring2": $("#extendString2").val()//�����֯
                , "extendint2": $("#extendInt2").val() //��������
                // , "officesId": $("#officesId").val() //�ϼ���������
                , "applydepartid": $("#applyDepId select").val() //���뵥λ
                , "fundssource": $("#fundSources select").val() //�ʽ���Դ
                , "extendstring1": $("#extendstring1").val() //��;
                , "memo": $("#memo").val() //��ע
            };
            $.ajax({
                type: "POST",
                url: "/sheet/ck/saveSheetCK.json",
                dataType: "json",
                data: json,
                success: function (ret) {
                    if (ret.status == 1) {
                        top.layer.msg(ret.message, {time: 500});
                        console.log(ret);
                        //������ID��Code��ֵ��ҳ����
                        $("#id").val(ret.data.id);
                        $("#code").html(ret.data.code);
                        $("#taskId").val(ret.data.taskId);
                        $("#reloadDetailsgrid").val(0);
                        reloadHis();
                        if (bool) {
                            open(cktype);
                        }
                        hideTypeChange();
                        hideAllChange();
                        form.render();
                    } else {
                        layer.alert("����ʧ�ܣ�ʧ��ԭ��" + ret.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            })
        }
    }

    function updateSheet(cktype, bool, id) {
        //TODO δ���뵥��Ч������
        if (checkSheet()) {
            var ztId = $("#ztId").val();
            var json = {
                "id": id
                , "type": "CKD"//��������
                , "typeid": $("#ckType select").val() //��������
                , "menuCode": $("#menuCode").val()
                , "ztid": ztId
                , "extendint1": $("#applyId").val()//���쵥��id
                , "useddepartid": $("#usedDepartId").val()//ʹ�õ�λId
                , "extendstring2": $("#extendString2").val()//�����֯
                , "extendint2": $("#extendInt2").val() //��������
                // , "officesId": $("#officesId").val() //�ϼ���������
                , "applydepartid": $("#applyDepId select").val() //���뵥λ
                , "fundssource": $("#fundSources select").val() //�ʽ���Դ
                , "extendstring1": $("#extendstring1").val() //��;
                , "memo": $("#memo").val() //��ע
            };

            $.ajax({
                type: "put",
                url: "/sheet/ck/CKD/" + id,
                dataType: "json",
                data: json,
                success: function (ret) {
                    if (ret.status == 1) {
                        top.layer.msg("�����޸ĳɹ���", {time: 500});
                        $("#reloadDetailsgrid").val(0);
                        reloadHis();
                        if (bool) {
                            open(cktype);
                        }
                        hideTypeChange();
                        hideAllChange();
                        form.render();
                    } else {
                        layer.alert("�����޸�ʧ�ܣ�ʧ��ԭ��" + ret.message);
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            })
        }
    }

    function open(cktype) {
        var ztId = $("#ztId").val();
        if (cktype == 'ֱ�ӳ���') {//ֱ�ӳ���
            layer.open({
                title: "ֱ�ӳ���"
                , type: 2
                , fixed: false
                , area: ["90%", "90%"]
                , content: "/sheet/ck/openZJCKDetailWindow.htm?ztId=" + ztId
                , end: function () {
                    if ($("#reloadDetailsgrid").val() == 1) {
                        ckReload();//ˢ���б�
                    }
                }
            })
            //vipTable.openPage("ֱ�ӳ���", "/sheet/ck/openZJCKDetailWindow.htm?ztId=" + ztId, "90%", "80%");
        } else if (cktype == '�������') {//�������
            layer.open({
                title: "�������"
                , type: 2
                , fixed: false
                , area: ["90%", "90%"]
                , content: "/sheet/ck/openSLCKDetailWindow.htm?ztId=" + ztId + "&code=" + $("#applyCode").val()
                , end: function () {
                    if ($("#reloadDetailsgrid").val() == 1) {
                        ckReload();//ˢ���б�
                    }
                }
            })
            //vipTable.openPage("�������", "/sheet/ck/openSLCKDetailWindow.htm?ztId=" + ztId, "90%", "80%");
        } else if (cktype == '��������') {//��������
            layer.open({
                title: "��������"
                , type: 2
                , fixed: false
                , area: ["90%", "90%"]
                , content: "/sheet/ck/openDBCKDetailWindow.htm?ztId=" + ztId + "&code=" + $("#dbCode").val()
                , end: function () {
                    if ($("#reloadDetailsgrid").val() == 1) {
                        ckReload();//ˢ���б�
                    }
                }
            })
            //vipTable.openPage("��������", "/sheet/ck/openDBCKDetailWindow.htm?ztId=" + ztId, "90%", "80%");
        }
    }

    function checkSheet() {
        var ret = true;
        var cktype = $("#type option:checked").text();
        if (cktype == 'ֱ�ӳ���') {//ֱ�ӳ���
            if (null == $("#usedDepartId").val() || $("#usedDepartId").val() == '') {
                top.layer.msg("ʹ�õ�λ����Ϊ��", {time: 500});
                return false;
            }
            // if(null ==$("#officesId").val() || $("#officesId").val()=='' ){
            //     top.layer.msg("�ϼ��������Ų���Ϊ��", {time: 500});
            //     return false;
            // }
            if (null == $("#applyDepId select").val() || $("#applyDepId select").val() == '') {
                top.layer.msg("��ѡ�����뵥λ", {time: 500});
                return false;
            }
            if (null == $("#fundSources select").val() || $("#fundSources select").val() == '' ) {
                top.layer.msg("��ѡ���ʽ���Դ", {time: 500});
                return false;
            }
        } else if (cktype == '�������') {//�������
            if (null == $("#applyId").val() || $("#applyId").val() == '') {
                top.layer.msg("���쵥�Ų���Ϊ��", {time: 500});
                return false;
            }
        } else if (cktype == '��������') {//��������
            if (null == $("#extendInt2").val() || $("#extendInt2").val() == '') {
                top.layer.msg("�������Ų���Ϊ��", {time: 500});
                return false;
            }
        } else {
            top.layer.msg("��ѡ���������", {time: 500});
            return false;
        }
        return ret;
    }

    //ɾ����ϸ
    $("#deleteDetails").click(function () {
        var checkStatus = table.checkStatus("detailsgridTable");
        var data = checkStatus.data;
        var ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm("ȷ��ɾ��ѡ�е���ϸ��", {icon: 3, title: '��ʾ��Ϣ'}, function (index) {
                $.post("/sheet/ck/delSheetDetails.json?ids=" + ids, function (data) {
                    layer.msg(data.message);
                    layer.close(index);
                    ckReload();//ˢ���б�
                })
            })
        } else {
            layer.msg('��ѡ��Ҫɾ�������ݡ�', {offset: 't', anim: 6});
        }
    });
    //��
    $("#split").click(function () {
        var checkStatus = table.checkStatus("detailsgridTable");
        var data = checkStatus.data;
        if (data.length > 0) {
            var json = {
                "type": "CKD"//��������
                , "typeid": $("#ckType select").val() //��������
                , "menuCode": $("#menuCode").val()
                , "ztid": $("#ztId").val()
                , "extendint1": $("#applyId").val()//���쵥��id
                , "useddepartid": $("#usedDepartId").val()//ʹ�õ�λId
                , "extendstring2": $("#extendString2").val()//�����֯
                , "extendint2": $("#extendInt2").val() //��������
                // , "officesId": $("#officesId").val() //�ϼ���������
                , "applydepartid": $("#applyDepId select").val() //���뵥λ
                , "fundssource": $("#fundSources select").val() //�ʽ���Դ
                , "extendstring1": $("#extendstring1").val() //��;
                , "memo": $("#memo").val() //��ע
            };
            var sheetId = $("#id").val();
            $.ajax({
                type: "POST",
                url: "/sheet/ck/splitSheet.json?sheetId=" + sheetId,
                dataType: "json",
                data: {details: JSON.stringify(json)},
                success: function (ret) {
                    if (ret.status == 1) {
                        top.layer.msg("�𵥳ɹ���", {time: 500});
                        ckReload();//ˢ���б�
                    } else {
                        top.layer.msg("��ʧ�ܣ�", {time: 500});
                    }
                },
                error: function (XMLHttpRequest) {
                    layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                }
            })
        } else {
            layer.msg('���������ϸ', {offset: 't', anim: 6});
        }
    });

});

//�ϼ��������ŷ���ֵ
function UseDepDepartGeneral(data) {
    document.getElementById("departOfficename").value = data[0].name;
    document.getElementById("officesId").value = data[0].id;
}

//��ȡ���쵥����ֵ
function getApply(data) {
    document.getElementById("applyCode").value = data.code;
    document.getElementById("applyId").value = data.id;
    document.getElementById("useDepName").value = data.useddepartname;
    document.getElementById("usedDepartId").value = data.usedDepartId;
    // document.getElementById("departOfficename").value = data.officeName;
    // document.getElementById("officesId").value = data.officesId;
    document.getElementById("applyDep").value = data.applyDepartId;
    document.getElementById("fundSourcesChange").value = data.fundsSource;
    layui.form.render('select'); //������Ⱦselect���������Ҫ
}

//��ȡʹ�õ�λ����ֵ
function UseDepGeneral(data) {
    document.getElementById("useDepName").value = data[0].name;
    document.getElementById("usedDepartId").value = data[0].id;
}

//��ȡ����������ֵ
function DBDGeneral(data) {
    console.log(data);
    document.getElementById("dbCode").value = data.code;
    document.getElementById("extendInt2").value = data.id;
}