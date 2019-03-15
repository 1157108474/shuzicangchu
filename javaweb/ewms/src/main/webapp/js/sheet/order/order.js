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
        , url: 'listJSDDetails.json'
        , cellMinWidth: 80
        , height: "full-160"
        ,done: function(value, date, endDate){
            $('#detailsgrid').change();  // һ��Ҫ������䣡������Ȼû�лص�������
            var shenpi = $("#shenpi").val();
            var tables = $("#detailsgrid").next().find(".layui-table-box"); 
            if(shenpi != 'shenpi'){
                tables.find("[data-field='jielun1']").css("display","none");
                tables.find("[data-field='jielun2']").css("display","none");
                tables.find("[data-field='yanshou2']").css("display","none");
                tables.find("[data-field='inpre']").css("display","none");
	           	 tables.find("[data-field='hanliang']").css("display","none");
	           	 tables.find("[data-field='yanshou1']").css("display","none");
	           	 tables.find("[data-field='ysyq']").css("display","none");
	           	 tables.find("[data-field='outpi']").css("display","none");
	           	 tables.find("[data-field='szyb']").css("display","none");
	           	 tables.find("[data-field='outpre']").css("display","none");
//                tables.find("[data-field='jielun3']").css("display","none");
            }
            var dytype = $("#dytype").val();
            if(dytype==='1'){
            	 tables.find("[data-field='inpre']").css("display","none");
            	 tables.find("[data-field='hanliang']").css("display","none");
            	 tables.find("[data-field='inpre']").css("display","none");
            	 tables.find("[data-field='ysyq']").css("display","none");
            	 tables.find("[data-field='outpi']").css("display","none");
            	 tables.find("[data-field='szyb']").css("display","none");
            	 tables.find("[data-field='outpre']").css("display","none");
    		}else if(dytype==='2'){
    			 tables.find("[data-field='szyb']").css("display","none");
    			 tables.find("[data-field='jielun2']").css("display","none");
    		}else if(dytype==='3'){
    			 tables.find("[data-field='inpre']").css("display","none");
           	     tables.find("[data-field='ysyq']").css("display","none");
           	     tables.find("[data-field='outpre']").css("display","none");
    		}else{
    			tables.find("[data-field='inpre']").css("display","none");
    			tables.find("[data-field='ysyq']").css("display","none");
           	 	tables.find("[data-field='outpi']").css("display","none");
           	 	tables.find("[data-field='szyb']").css("display","none");
           	 	tables.find("[data-field='outpre']").css("display","none");
    		}
            
        }
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , id: "detailsgridTable"
        , where: {ran: ran++, sheetId: $("#id").val()}
        , cols: [
            [{type: "checkbox", fixed: "left", width: 50}
                , {title: '�鿴��ʷ��¼', align: "center", fixed: "left", toolbar: '#bar', width: 148}
                /*, {
                field: 'planDepartName', title: '�ƻ�����', align: "center", width: 140, templet: function (d) {
                    if (d.planDepartId == 0 || d.planDepartId == null) {
                        return "δ��д";
                    }
                }
            }*/
//                , {field: 'id', title: 'id', align: "center", width: 140}
                , {field: 'materialCode', title: '���ϱ���', align: "center", width: 140}
                , {field: 'description', title: '��������', align: "center", width: 140}
                , {field: 'detailUnitName', title: '��λ', align: "center", width: 140}
                , {field: 'detailCount', title: '��������', align: "center", width: 120}
                , {field: 'thCount', title: '�˻�����', align: "center", width: 120}
                , {field: 'ownerName', title: '�Ƿ����', align: "center", width: 120}
                , {
                field: 'isEquipment', title: '�Ƿ��豸', align: "center", width: 120, templet: function (d) {
                    if (d.isEquipment == 1) {
                        return "��";
                    } else {
                        return "��";
                    }
                }
            }
                , {
                field: 'enableSn', title: '�Ƿ��������к�', align: "center", width: 145, templet: function (d) {
                    if (d.enableSn == 1) {
                        return "����";
                    } else {
                        return "������";
                    }
                }
            }
                , {field: 'noTaxPriceDuble', title: '����', align: "center", width: 120}
                , {field: 'hanliang', title: '��Ҫָ��/����', align: "center", width: 130, edit: 'text', event: 'countCheck'}
                , {field: 'inpre', title: '����ԭ���ϼ��鱨��', align: "center", width: 150, edit: 'text', event: 'countCheck'}
                , {field: 'jielun1', title: '���ս���', align: "center", width: 150, edit: 'text', event: 'countCheck'}
//                , {field: 'yanshou1', title: '������', align: "center", width: 120, edit: 'text', event: 'countCheck'}
                , {field: 'ysyq', title: 'һ��һǩ', align: "center", width: 120, edit: 'text', event: 'countCheck'}
                , {field: 'outpi', title: '����/��������', align: "center", width: 130, edit: 'text', event: 'countCheck'}
                , {field: 'szyb', title: '��֤һ��', align: "center", width: 160, edit: 'text', event: 'countCheck'}
                , {field: 'outpre', title: '������鱨��/�ϸ�֤', align: "center", width: 160, edit: 'text', event: 'countCheck'}
                , {field: 'jielun2', title: '���ս���', align: "center", width: 150, edit: 'text', event: 'countCheck'}
//                , {field: 'yanshou2', title: '�ɹ�Ա', align: "center", width: 120, edit: 'text', event: 'countCheck'}
//                , {field: 'jielun3', title: '�������ս���', align: "center", width: 110, edit: 'text', event: 'countCheck'}

            ]
        ]
    });

    // ������Ԫ��༭
    table.on('edit(detailsgrid)', function (obj) {
    	
        var data = obj.data; //�����е������������
        var field = obj.field; //��ǰ�༭���ֶ���
//        if($("#activityName").val()==='һ��'){
//        	
//        }
        
//        var value = obj.value; //�õ��޸ĺ��ֵ
//        if (value > data.isCount) {
//            layer.alert("�ɽ���������:" + data.isCount + ",�����������ܴ��ڿɽ�������,��������д��������!");
//            return false;
//        }
//        if (value > data.baseunitcount) {
//            layer.alert("�����������ܴ��ڲɹ�����,��������д��������!");
//            return false;
//        }
//        if (!/^[0-9]+.?[0-9]*$/.test(value) || value == "0") {
//            layer.alert("��������������Ч����,��������д��������!");
//            return false;
//        }
//        if (value == "" || value == null) {
//            layer.alert("��������Ϊ��,����д��������!");
//            return false;
//        }
//        if(check.isDot(value)){
//            if(!check.isAllowDecimal(data.detailunit)){
//                layer.alert(data.detailunit+"��λ��������С��");
//                data[field] = 0;
//                return;
//            }
//        }
    });
    
    var reload = function () {
        table.reload('detailsgridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val()
            }

        })
    };


    //���ݸ����б�
    var filegr = table.render({
        elem: "#filegrid"
        , url: 'listFile.json'
        , cellMinWidth: 80
        , height: "full-160"
        , method: 'post'
        , page: true   //������ҳ
        , limit: 10   //Ĭ��ʮ��������һҳ
        , limits: [10, 20, 30]  //���ݷ�ҳ��
        , where: {ran: ran++, sheetId: $("#id").val()}
        , id: "filegridTable"
        , cols: [
            [
                {title: '����', align: "center", toolbar: '#filebar', width: 100}
                , {field: 'fileName', title: '�ļ���', align: "center"}
                , {field: 'fileExt', title: '�ļ���׺', align: "center"}
                , {field: 'creatorName', title: '������', align: "center"}
                , {
                field: 'createDate', title: '����ʱ��', align: 'center', templet: function (d) {
                    return datetimeformat(d.createDate);
                }
            }
            ]]
    });

    var reloadFile = function () {
        table.reload('filegridTable', {
            page: {
                curr: 1 //���´ӵ� 1 ҳ��ʼ
            },
            where: {
                ran: ran++,
                sheetId: $("#id").val()
            }

        })
    };


    //������������ҳ��
    $("#importfiles").on("click", function (e) {
        $("#reloadStatus").val(0);
        var check = $("#id").val();
        if (check == "") {
            layer.alert("���ȱ��浥�ݣ�");
        } else {

            layer.open({
                title: "�ϴ�����"
                , type: 2
                , fixed: false
                , area: ["40%", "80%"]
                , content: "addFile.htm?sheetId=" + check
                , end: function () {
                    if ($("#reloadStatus").val() == 1) {
                        reloadFile();
                    }
                }
            });
        }
    });

    //����д�ƻ�ҳ��
    $("#addPlanOther").on("click", function (e) {
        var checkStatus = table.checkStatus('detailsgridTable');
        var datas = checkStatus.data;
        var length = datas.length;
        if (length != 1) {
            layer.alert("��ѡ��һ����ϸ");
        } else {
            vipTable.openPage("��д�ƻ�", "addPlanOther.htm?sheetId=" + datas[0].id, "40%", "90%");
        }
    });

    //�鿴��ʷ��¼
    table.on('tool(detailsgrid)', function (obj) {
        var data = obj.data;
        if (obj.event == 'showLog') {
            vipTable.openPage("�˻�/������ʷ��¼", "orderHistory.htm?detailsGUID=" + data.guid, "60%", "70%");
        }
    });


    $("#order").on("click", function (e) {
        vipTable.openPage("�ɹ������б�", "/sheet/wzjs/generalOrder.htm", '90%', '90%');
        return false;

    });

    //���水ť
    $("#saveSheet").on("click", function (e) {
        var result;
        //�ж��Ƿ�ѡ�вɹ�����,���ûѡ��,�����ڱ���
        var check = $("#orderId").val();
        //�ж��Ƿ���ڵ��ݱ��,���û��,ִ�б���,�����,ִ���޸�
        var check2 = $("#id").val();
        if (check == "") {
            layer.alert("������д������ţ�");
        } else {
            //�������ID������,add
            if (check2 == "") {
                result = saveSheet();
            } else {
                result = updateSheet();
            }

        }
    });

    //���浥�ݷ���
    var saveSheet = function () {

        var loading = layer.msg("������", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });
        //��ȡ����table��������Ϣ,ƴװJSON
        var orderTable = document.getElementById("orderTable");
        var orderTableValue = orderTable.getElementsByTagName("input");
        var extendInt6 = $("#extendInt6").val();
        var extendInt6Name = 'extendInt6';
        var param1 = "{";
        param1 += "\"" + extendInt6Name + "\":\"" + extendInt6 + "\",";
        for (var i = 0; i < orderTableValue.length; i++) {
        		param1 += "\"" + orderTableValue[i].name + "\":\"" + orderTableValue[i].value + "\",";
            
        }
        
        param1 = param1.substring(0, param1.length - 1);
        param1 += "}";
        var orderJson = JSON.parse(JSON.stringify(param1));
        $.ajax({
            type: "POST",
            url: "/sheet/WZJS",
            dataType: "json",
            data: JSON.parse(orderJson),
            success: function (ret) {
                if (ret.status == 1) {
                    //������ID��Code��ֵ��ҳ����
                    $("#id").val(ret.data.id);
                    $("#sheetcode").html(ret.data.code);
                    $("#taskId").val(ret.data.taskId);
                    reloadHis();
                    $("#sheetStatus").val(39);
                    layer.close(loading);
                    layer.msg("���浥�ݳɹ���");
                } else {
                    layer.alert("����ʧ�ܣ�ʧ��ԭ��" + ret.message);
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };

    //������ϸҳ��
    $("#addDetails").on("click", function (e) {
        var check = $("#orderId").val();
        var sheetId = $("#id").val();
        var extendInt6 = $("#extendInt6").val();
        if (check == "") {
            layer.alert("������д������ţ�");
        }else if(extendInt6 == ""){
        	layer.alert("��ѡ���������ͣ�");
        }else {
            if (sheetId == "") {
                // ���жϵ�ǰ�����Ƿ��ѱ���,���δ����,��ִ�б���
                saveSheet();
                open();
            } else {
                // ����ϸҳ��
                open();
            }
        }
    });

    // ɾ����ϸ��ť
    $("#deleteDetails").on("click", function (e) {
        var checkStatus = table.checkStatus('detailsgridTable');
        var datas = checkStatus.data;
        var arr = [];
        if (datas == "") {
            layer.msg("��ѡ��һ����¼����ɾ��", {
                offset: 't',
                anim: 6
            });
        } else {
            layer.confirm('��ȷ��Ҫɾ����', {
                icon: 3, title: '��ʾ��Ϣ', offset: '35%',
                btn: ['ȷ��', 'ȡ��'] //��ť
            }, function () {
                for (var i = 0; i < datas.length; i++) {
                    arr.push(datas[i].id);
                }
                var url = "deleteSheetDetails.json?ids=" + arr;
                $.post(url, function (data) {
                    if (data.status == 1) {
                        layer.msg('ɾ���ɹ�', {
                            time: 2000,
                            end: function () {
                                reload();
                            }
                        });
                    } else {
                        layer.alert(data.message, {
                            offset: '35%'
                        });
                    }
                })
            }, function () {
                layer.closeAll();
            });
        }
    });


    //������ϸҳ��
    var open = function () {
        var num = $("#ordernum").val();
        layer.open({
            title: "�����ϸ"
            , type: 2
            , fixed: false
            , area: ["90%", "80%"]
            , content: "addDetails.htm?orderNum=" + num
            , end: function () {
                if ($("#reloadStatus").val() == 1) {
                    updateSheet();
                    reload();
                }
            }
        });
    };

    //�޸ĵ��ݷ���
    var updateSheet = function () {

        var loading = layer.msg("������", {
            icon: 16
            , shade: 0.2,
            time: false,
            offset: 't'
        });

        //��ȡ����table��������Ϣ,ƴװJSON
        var orderTable = document.getElementById("orderTable");
        var orderTableValue = orderTable.getElementsByTagName("input");
        var param1 = "{";
        for (var i = 0; i < orderTableValue.length; i++) {
            param1 += "\"" + orderTableValue[i].name + "\":\"" + orderTableValue[i].value + "\",";
        }
        param1 = param1.substring(0, param1.length - 1);
        param1 += "}";
        var orderJson = JSON.parse(JSON.stringify(param1));

        $.ajax({
            type: "POST",
            url: "/sheet/wzjs/editSheet.json",
            dataType: "json",
            data: JSON.parse(orderJson),
            success: function (ret) {
                if (ret.status == 1) {
                    layer.close(loading);
                    return true;
                } else {
                    layer.alert("����ʧ�ܣ�ʧ��ԭ��" + ret.message);
                    return false;
                }
            },
            error: function (XMLHttpRequest) {
                layer.alert("�������" + XMLHttpRequest.status + XMLHttpRequest.statusText);
            }
        });
    };
    //��ӡ
    $("#print").on("click", function (e) {
    	var taskId = $("#taskId").val();
    	var printType = $("#dytype").val();
//        var printType = $("#printType").val();
        if ($("#id").val() == "" || $("#id").val() == "0") {
            layer.alert("���ȱ��浥��");
        } 
//        else if (printType == '') {
//            layer.alert("��ѡ���ӡ����");
//        } 
        else {
            var url = "/system/print/sheet/WZJS-" + $("#id").val() + "?ordernum=" + $("#ordernum").val() + "&printType=" + printType + "&taskId=" + taskId;
            vipTable.openPage("��ӡ���յ�", url, '800px', '500px');
        }
    });
    $("#labelPrint").on("click", function (e) {
        var checkStatus = table.checkStatus('detailsgridTable');
        var datas = checkStatus.data;
        var length = datas.length;
        if (datas == "") {
            layer.alert("������ѡ��һ����ϸ");
        } else {
            layer.open({
                type: 2,
                title: '��ӡ',
                fixed: false,
                area: ['60%', '60%'],
                content: "/system/print/sheet/BQDY-" + $("#id").val() + "?num=" + length,
                success: function (layero, index) {
                    var body = layer.getChildFrame('body', index);
                    body.find('input').val(length);
                }
            });
        };
    });

});

function orderGeneral(data) {
    document.getElementById("orderId").value = data[0].id;
    document.getElementById("ordernum").value = data[0].ordernum;
    document.getElementById("ordertype").value = data[0].ordertype;
    document.getElementById("issuecode").value = data[0].issuecode;
    document.getElementById("providerdepid").value = data[0].providerdepid;
    document.getElementById("providerdepname").value = data[0].providerdepname;
    document.getElementById("stockorgname").value = data[0].stockorgname;
    document.getElementById("stockorgid").value = data[0].stockorgid;
    document.getElementById("extendString10").value = data[0].erpRowNum;
}
function deleteFile(id, path) {
    parent.layer.confirm('��ȷ��Ҫɾ����', {
        icon: 3, title: '��ʾ��Ϣ', offset: '35%',
        btn: ['ȷ��', 'ȡ��'] //��ť
    }, function () {
        var url = "/sheet/wzjs/deleteFile.json?id=" + id + "&filePath=" + path;
        parent.layui.$.post(url, function (data) {
            if (data.status == 1) {
                parent.layer.msg('ɾ���ɹ�', {
                    time: 2000,
                    end: function () {
                        layui.table.reload('filegridTable');
                    }
                });

            } else {
                parent.layer.alert("ɾ��ʧ��:" + data.message, {
                    offset: '35%'
                });
            }
        })
    })
}
$(function(){
	if($("#dytype").val()!=''&&$("#dytype").val()!=null){
		$("#sp").hide();
		$("#sp2").show();
//		document.getElementById("extendInt6").style.display="none";
//		document.getElementById("dytypeName").style.display="block";
		if($("#dytype").val()==='1'){
			$("#dytypeName").val("�豸����Ʒ����������");
		}else if($("#dytype").val()==='2'){
			$("#dytypeName").val("ԭ������");
		}else if($("#dytype").val()==='3'){
			$("#dytypeName").val("��ȫ����");
		}else{
			$("#dytypeName").val("��������");
		}
	}else{
		$("#sp").show();
		$("#sp2").hide();
//		$("#extendInt6").show();
//		$("#dytypeName").hide();
//		document.getElementById("dytypeName").style.display="block";
//		document.getElementById("extendInt6").style.display="none";
	}
})
