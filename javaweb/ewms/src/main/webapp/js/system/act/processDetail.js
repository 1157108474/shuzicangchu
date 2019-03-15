layui.use(['laydate', 'form', 'layer', 'table', 'laytpl','element'], function () {
    var laydate = layui.laydate;
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        $ = layui.jquery,
        element = layui.element;
    //�����б�
   /* var tableIns = table.render({
        elem: '#activitiProcessing',
        url: '',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 20,
        id: "activitiProcessingTable",
        method: 'post',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {title: '���',templet: '#indexTpl',align: "center",width: 50},
            {field: 'id', title: '����ID', align: "center",minWidth:120},
            {field: 'activityName', title: '��������', align: "center",minWidth:120},
            {field: 'assignee', title: '������', align: "center",minWidth:120},
            {field: 'startTime', title: '��ʼʱ��', align: "center",minWidth:120},
            {field: 'endTime', title: '����ʱ��', align: "center",minWidth:120}
        ]],
    });*/
    var tableHis = table.render({
        elem: '#activitiHis',
        url: '/system/activitiListener/historyActInstanceList.json',
        where:{taskId: $("#taskId").val()},
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30, 40],
        limit: 20,
        id: "activitiHisTable",
        method: 'post',
        cols: [[
            // {title: '���',templet: '#indexTpl',align: "center",width: 50},
            {field: 'activityName', title: '��������', align: "center",minWidth:120},
            {field: 'assignee', title: '������', align: "center",minWidth:100},
            {field: 'endTime', title: '����ʱ��', align: "center",minWidth:120,templet:'<div>{{ layui.laytpl.toDateString(d.endTime) }}</div>'},
            {field: '', title: '�������', align: "center",minWidth:100,templet:function(d){
                if(d.endTime != null){
                    return "�Ѵ���"
                }else{
                    return "δ����"
                }
            }},
            {field: 'comment', title: '�������', align: "center",minWidth:100}
            ]],
        page: true
    });
    window.reloadHis = function(){

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
    $("#submit").on("click", function () {//�ύ
        if($("#taskId").val() != ''){
            $.post("/system/activitiListener/isSheetTrue.json", {'taskId':  $("#taskId").val()},
                function(data) {
                    if(data.code){
                        nextPart();
                    }else{
                        layer.alert(data.message);
                    }
                },"JSON");
        }else{
            layer.msg('���ȱ��浥�ݡ�', {
                anim: 6
            });
        }
    });
    $("#review").on("click", function () {//���
//    	 var checkStatus = table.checkStatus('detailsgridTable');
    	var detailsgridTable =  layui.table.cache.detailsgridTable1;
//         debugger;
//    	alert(typeof(detailsgridTable));
         if('undefined' != typeof(detailsgridTable)){
        	 var details = [];
         	for (var i = 0; i < detailsgridTable.length; i++) {
         		var obj = {
         				id:detailsgridTable[i].id,
         			sheetId:detailsgridTable[i].sheetId,
                 	hanliang: detailsgridTable[i].hanliang,
                 	inpre: detailsgridTable[i].inpre,
                 	jielun1: detailsgridTable[i].jielun1,
//                 	yanshou1: detailsgridTable[i].yanshou1,
                 	ysyq: detailsgridTable[i].ysyq,
                 	outpi: detailsgridTable[i].outpi,
                 	szyb: detailsgridTable[i].szyb,
                 	outpre: detailsgridTable[i].outpre,
                 	jielun2: detailsgridTable[i].jielun2,
//                 	yanshou2: detailsgridTable[i].yanshou2,
         		}
         		 details.push(obj);
         	}
         	$.ajax({
                 type: "POST",
                 url: "../detail/updateWZJSDetails",
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
    	
        nextPart();
        //���ж��Ƿ��Ѿ���˹����Ƿ��������󻷽�
        /* $.post("/system/activitiListener/isTaskTrue.json",{'taskId':taskId},
           function(data) {
               if(data){//�ǣ���ʾ���ֱ�Ӱ�ᣩ
                    layer.alert("�û���Ϊ������񻷽ڣ���ֱ�Ӱ�ᡣ");
               }else{//������󻷽�
                    nextPart();
               }
           },"JSON");*/
    });
    /*$("#complete").on("click", function () {//���
       //���һ�����ڣ�ֱ���������
        $.post("/system/activitiListener/completeTask.htm",{'taskId':taskId},
              function(data){
                 alert("��󻷽�");
                  //���������ҳ�洦��
               },"JSON");
    });*/
   /* $("#reject").on("click", function (e) {//���ص��ϻ���
        $.post("/system/activitiButton/taskRollBack.htm",{'taskId':taskId},
            function(data) {
                //����������ҳ�洦��
            },"JSON");
    });*/
     $("#reject").on("click", function () {//���ص���ѡ����
         layer.open({
             type: 2,
             title: '���ػ���ѡ��',
             fixed: false,
             area: ['50%', '40%'],
             content: "system/activitiButton/historyActInstanceList.htm?taskId="+$("#taskId").val()+"&&comment="+$("#comment").val(),
             end: function () {
                 $.post("/system/activitiListener/isProcessComplete.json",{'taskId':$("#taskId").val()},
                     function(data) {
                         if(data){
                             var url = location.href;
                             var parm = parseInt(Math.random() * 10);
                             if (url.lastIndexOf('?') > -1) {
                                 url = url +"&&time="+ parm;
                             } else {
                                 url = url + "?" + parm;
                             }
                             window.location.href = url;
                             //location.reload();
                         }
                     },"JSON");
             }
         });
     });
    /*$("#saveSheet").on("click", function () {//����
        $.post("/system/processManage/startProIns.htm",{'menuCode':$("#menuCode").val()},
            function(data) {
                $("#taskId").val(data);
                alert("����ɹ�");
                //����������ҳ�洦��
            },"JSON");

    });*/
    function nextPart(){
        layer.open({
            type: 2,
            title: '������Աѡ��',
            fixed: false,
            area: ['60%', '90%'],
            content: "system/activitiListener/findNextPart.htm?taskId="+$("#taskId").val()+"&&comment="+$("#comment").val()+"&&isAdmin=true",
            end: function () {
                $.post("../../system/activitiListener/isProcessComplete.json",{'taskId':$("#taskId").val()},
                    function(data) {
                       if(data){
                           var url = location.href;
                           var parm = parseInt(Math.random() * 10);
                           if (url.lastIndexOf('?') > -1) {
                               url = url +"&&time="+ parm;
                           } else {
                               url = url + "?" + parm;
                           }
                            window.location.href = url;
                          // location.reload();
                       }
                    },"JSON");
               /*
                parent.tab.deleteTab($("#taskId").val());
                parent.tab.tabAdd({
                    href:"system/activitiButton/getOnePro.htm?taskId="+$("#taskId").val(), //��ַ
                    title: "�������"
                });*/
            }

        });
    }
    layui.laytpl.toDateString = function(d, format){
        var date = new Date(d || new Date())
            ,ymd = [
            this.digit(date.getFullYear(), 4)
            ,this.digit(date.getMonth() + 1)
            ,this.digit(date.getDate())
        ]
            ,hms = [
            this.digit(date.getHours())
            ,this.digit(date.getMinutes())
            ,this.digit(date.getSeconds())
        ];

        format = format || 'yyyy-MM-dd HH:mm:ss';

        return format.replace(/yyyy/g, ymd[0])
            .replace(/MM/g, ymd[1])
            .replace(/dd/g, ymd[2])
            .replace(/HH/g, hms[0])
            .replace(/mm/g, hms[1])
            .replace(/ss/g, hms[2]);
    };

//����ǰ�ò���
    layui.laytpl.digit = function(num, length, end){
        var str = '';
        num = String(num);
        length = length || 2;
        for(var i = num.length; i < length; i++){
            str += '0';
        }
        return num < Math.pow(10, length) ? str + (num|0) : num;
    };
});







