layui.use(['laydate', 'form', 'layer', 'table', 'laytpl','element'], function () {
    var laydate = layui.laydate;
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        $ = layui.jquery,
        element = layui.element;
    //数据列表
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
            {title: '序号',templet: '#indexTpl',align: "center",width: 50},
            {field: 'id', title: '任务ID', align: "center",minWidth:120},
            {field: 'activityName', title: '任务名称', align: "center",minWidth:120},
            {field: 'assignee', title: '办理人', align: "center",minWidth:120},
            {field: 'startTime', title: '开始时间', align: "center",minWidth:120},
            {field: 'endTime', title: '结束时间', align: "center",minWidth:120}
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
        limit: 30,
        id: "activitiHisTable",
        method: 'post',
        cols: [[
            // {title: '序号',templet: '#indexTpl',align: "center",width: 50},
            {field: 'activityName', title: '环节名称', align: "center",minWidth:120},
            {field: 'assignee', title: '办理人', align: "center",minWidth:100},
            {field: 'endTime', title: '办理时间', align: "center",minWidth:120,templet:'<div>{{ layui.laytpl.toDateString(d.endTime) }}</div>'},
            {field: '', title: '操作结果', align: "center",minWidth:100,templet:function(d){
                if(d.endTime != null){
                    return "已处理"
                }else{
                    return "未处理"
                }
            }},
            {field: 'comment', title: '操作意见', align: "center",minWidth:100}
            ]],
        page: true
    });
    window.reloadHis = function(){

        //可以被外部引用
        table.reload('activitiHisTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                taskId: $("#taskId").val()
            }
        });
    }
    $("#submit").on("click", function () {//提交
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
            layer.msg('请先保存单据。', {
                anim: 6
            });
        }
    });
    $("#review").on("click", function () {//审核
    	
    	var detailsgridTable =  layui.table.cache.detailsgridTable;
//         debugger;
//    	alert(typeof(detailsgridTable));
         if('undefined' != typeof(detailsgridTable)){
        	 var flag = "";
        	 var dytype = $("#dytype").val();
        	 var activityName = $("#activityName").val();
//        	 alert(activityName);
//           alert("打印类型----"+dytype);
           if(activityName != null&&activityName != ''){
           if(dytype==='1'){
           	
           		if(activityName==='仪电中心'||activityName==='生产运营指挥中心'||activityName==='热动中心'||activityName==='甲醇制造中心'||activityName==='综合办公室'||activityName==='机械动力部'||activityName==='技术质量部'||activityName==='蒙大能源环保'||activityName==='安全环保部'||activityName==='公共工程'||activityName==='供销中心'||activityName==='财务部'||activityName==='计划发展部'||activityName==='党群工作部'){
           			flag = 11;
           		}else if(activityName==='采购员'){
           			flag = 12;
           		}
           	
       	}else if(dytype==='2'){
 	      		if(activityName==='仪电中心'||activityName==='生产运营指挥中心'||activityName==='热动中心'||activityName==='甲醇制造中心'||activityName==='综合办公室'||activityName==='机械动力部'||activityName==='技术质量部'||activityName==='蒙大能源环保'||activityName==='安全环保部'||activityName==='公共工程'||activityName==='供销中心'||activityName==='财务部'||activityName==='计划发展部'||activityName==='党群工作部'){
 	      		    flag = 21;
 	      		}else if(activityName==='采购员'){
 	      			flag = 22;
 	      		}
       	}else if(dytype==='3'){
//       		
 		  		if(activityName==='仪电中心'||activityName==='生产运营指挥中心'||activityName==='热动中心'||activityName==='甲醇制造中心'||activityName==='综合办公室'||activityName==='机械动力部'||activityName==='技术质量部'||activityName==='蒙大能源环保'||activityName==='安全环保部'||activityName==='公共工程'||activityName==='供销中心'||activityName==='财务部'||activityName==='计划发展部'||activityName==='党群工作部'){
 		  			flag = 31;
 		  		}else if(activityName==='采购员'){
 		  			flag = 32;
 		  		}
       	}else{
       		if(activityName==='仪电中心'||activityName==='生产运营指挥中心'||activityName==='热动中心'||activityName==='甲醇制造中心'||activityName==='综合办公室'||activityName==='机械动力部'||activityName==='技术质量部'||activityName==='蒙大能源环保'||activityName==='安全环保部'||activityName==='公共工程'||activityName==='供销中心'||activityName==='财务部'||activityName==='计划发展部'||activityName==='党群工作部'){
       				flag = 41;
       		}else if(activityName==='采购员'){
       			flag = 42;
       		}
       	}
           
     }
        	 
        	 
        	 var details = [];
         	for (var i = 0; i < detailsgridTable.length; i++) {
         		if(flag === 11){
         			if(detailsgridTable[i].jielun1 == null || detailsgridTable[i].jielun1 == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的验收结论');
         				return;
         			}
         		}else if(flag === 12){
         			if(detailsgridTable[i].jielun2 == null || detailsgridTable[i].jielun2 == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的验收结论');
         				return;
         			}
         		}else if(flag === 21){
         			if(detailsgridTable[i].hanliang == null || detailsgridTable[i].hanliang == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的主要指标/含量');
         				return;
         			}
         			if(detailsgridTable[i].inpre == null || detailsgridTable[i].inpre == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的进场原材料检验报告');
         				return;
         			}
         			if(detailsgridTable[i].jielun1 == null || detailsgridTable[i].jielun1 == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的验收结论');
         				return;
         			}
         		}else if(flag === 22){
         			if(detailsgridTable[i].ysyq == null || detailsgridTable[i].ysyq == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的一书一签！');
         				return;
         			}
         			if(detailsgridTable[i].outpi == null || detailsgridTable[i].outpi == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的出场/生产批号！');
         				return;
         			}
         			if(detailsgridTable[i].outpre == null || detailsgridTable[i].outpre == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的出场检查报告/合格证！');
         				return;
         			}
         		}else if(flag === 31){
         			if(detailsgridTable[i].jielun1 == null || detailsgridTable[i].jielun1 == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的验收结论！');
         				return;
         			}
         		}else if(flag === 32){
         			if(detailsgridTable[i].outpi == null || detailsgridTable[i].outpi == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的出场/生产批号！');
         				return;
         			}
         			if(detailsgridTable[i].szyb == null || detailsgridTable[i].szyb == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的三证一标！');
         				return;
         			}
         		}else if(flag === 41){
         			if(detailsgridTable[i].jielun1 == null || detailsgridTable[i].jielun1 == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的验收结论');
         				return;
         			}
         		}else if(flag === 42){
         			if(detailsgridTable[i].jielun2 == null || detailsgridTable[i].jielun2 == ''){
         				layer.alert('请填写物料编码为'+detailsgridTable[i].materialCode+'的验收结论');
         				return;
         			}
         		}
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
                         layer.msg('添加成功', function () {
                             var index = parent.layer.getFrameIndex(window.name);
                             parent.layer.close(index);
                         });
                     } else {
                         layer.alert('添加失败：' + ret.message);
                     }
                 },
                 error: function (XMLHttpRequest) {
                     layer.alert("请求出错：" + XMLHttpRequest.status + XMLHttpRequest.statusText);
                 }
             });
         }
    	
        nextPart();
        //先判断是否已经审核过、是否是最后最后环节
        /* $.post("/system/activitiListener/isTaskTrue.json",{'taskId':taskId},
           function(data) {
               if(data){//是（提示点击直接办结）
                    layer.alert("该环节为最后任务环节，请直接办结。");
               }else{//不是最后环节
                    nextPart();
               }
           },"JSON");*/
    });
    /*$("#complete").on("click", function () {//办结
       //最后一个环节，直接完成任务
        $.post("/system/activitiListener/completeTask.htm",{'taskId':taskId},
              function(data){
                 alert("最后环节");
                  //完成任务后的页面处理
               },"JSON");
    });*/
   /* $("#reject").on("click", function (e) {//驳回到上环节
        $.post("/system/activitiButton/taskRollBack.htm",{'taskId':taskId},
            function(data) {
                //驳回任务后的页面处理
            },"JSON");
    });*/
     $("#reject").on("click", function () {//驳回到所选环节
         layer.open({
             type: 2,
             title: '驳回环节选择',
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
    /*$("#saveSheet").on("click", function () {//保存
        $.post("/system/processManage/startProIns.htm",{'menuCode':$("#menuCode").val()},
            function(data) {
                $("#taskId").val(data);
                alert("保存成功");
                //驳回任务后的页面处理
            },"JSON");

    });*/
    function nextPart(){
        layer.open({
            type: 2,
            title: '环节人员选择',
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
                    href:"system/activitiButton/getOnePro.htm?taskId="+$("#taskId").val(), //地址
                    title: "任务完成"
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

//数字前置补零
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







