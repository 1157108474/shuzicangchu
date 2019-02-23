    var layout = [
    	{name: '�˵�����',field: 'name',treeNodes: true,align: "center"}
        ,{name: '�˵�����',field: 'code',align: "center",style: 'width: 80px'}
        , {name: '����',field: 'type',style: 'width: 80px',align: "center",render: function(row) {
    		if(row.type == 1) {
    			return 'Ŀ¼';
    		} else if(row.type == 2) {
    			return 'ҳ��';
    		} else {
    			return '';
    		}
    	}}
    	, {name: 'ͼ��',field: 'icon',style: 'width: 80px',align: "center",render: function(row) {
            var icon = (null==row.icon?'':row.icon);
			return '<i class="fa ' + icon + '" aria-hidden="true"></i>';
    	}}
    	, {name: '�˵�URL',field: 'url',style: 'width: 200px',align: "center",render: function(row) {
            if(row.type == 1) {
                return '#';
            } else if(row.type == 2 && null != row.url) {
                return row.url;
            } else {
                return '';
            }
        }}
    	, {name: 'Ȩ�ޱ�ʶ',field: 'authIdentity',style: 'width: 300px',align: "center",render: function(row) {
            if(null ==row.authIdentity) {
                return '';
            } else{
                return row.authIdentity;
            }
        }}
        , {name: '����',field: 'sort',align: "center",style: 'width: 60px'}
        , {name: '״̬',field: 'status',style: 'width: 60px',align: "center",render: function(row) {
    		if(row.status == 1) {
    			return '����';
    		} else if(row.status == 0) {
    			return '����';
    		} else {
    			return '';
    		}
    	}}
    	,{name: '��ťģʽ',field: 'buttonModeStr',style: 'width: 100px',align: "center"}
    ];

    layui.use(['form', 'tree', 'layer', 'table', 'laytpl'], function() {
    	var layer = layui.layer,
    		form = layui.form,
    		$ = layui.jquery,
    		laytpl = layui.laytpl;
    	var tree1;
        var ran = Math.random();
        $(function () {
            $.ajax({
                url:'/system/menu/listMenu.json',
                method:"post",
                data:{ran:ran++},
                success:function (ret) {
                    tree1 = layui.treeGird({
                        elem: '#demo', //����Ԫ��ѡ����
                        spreadable: false, //�����Ƿ�ȫչ����Ĭ�ϲ�չ��
                        checkbox: true,
                        nodes: ret.data,
                        data:{ran:ran++},
                        layout: layout
                    });
                    form.render();
                }
            });
        });

    	//���
    	function addNews(edit) {
    		var tit = '��Ӳ˵�',
    			url = '/system/menu/addMenu.htm';
    		if(edit) {
    			tit = '�޸Ĳ˵�';
    			url = '/system/menu/editMenu.htm?code=' + edit.code;
    		}
    		layui.layer.open({
    			type: 2,
    			title: tit,
    			area: ['674px', '340px'],
    			fixed: false,
    			content: url
    		});
    	}

    	//���
    	$("#add").on('click', function() {
    		addNews();
    	});
    	//�༭
    	$("#edit").on('click', function() {
    		var arr = layui.getSelected(tree1);
    		if(arr.length == 1) {
    			addNews(arr[0]);
    		} else {
    			layer.msg('��ѡ��һ�����ݡ�', {
    				offset: 't',
    				anim: 6
    			});
    		}
    	});
		//�򿪹��ܰ�ť�б�
    	$('#manageButton').on('click', function() {
    		var arr = layui.getSelected(tree1);
    		if(arr.length != 1) {
    			layer.msg('��ѡ��һ�����ݡ�', {
    				offset: 't',
    				anim: 6
    			});
    		} else {
    			var data = arr[0];
    			if(data.type == '1') {
    				layer.msg('Ŀ¼���ܷ���Ȩ�ޡ�', {
    					offset: 't',
    					anim: 6
    				});
    			} else {
    				layer.open({
    					type: 2,
    					title: '���ܹ���',
    					area: ['90%', '90%'],
    					fixed: false,
    					content: '/system/menu/manageButton.htm?code=' + data.code
    				});
    			}
    		}
    	});

    	//����ɾ��
    	$("#delAll").on("click", function(e) {
    		var arr = layui.getSelected(tree1);
    		var codes = [];
    		if(arr.length > 0) {
    			for(var i in arr) {
                    codes.push(arr[i].id);
    			}
    			layer.confirm('ȷ��ɾ��ѡ�еĲ˵���', {
    				icon: 3,
    				title: '��ʾ��Ϣ'
    			}, function(index) {
					$.ajax({
    					url: "/system/menu/delMenus.json?codes=" + codes ,
    					type: 'post',
    					contentType: 'application/x-www-form-urlencoded; charset=GBK',
    					success: function(data) {
                            top.layer.msg(data.message, {
                                time: 500
                            });
                            if ('1' == data.status) {
                                location.reload();
                            }
    					}
    				});
    			})
    		} else {
    			layer.msg('��ѡ��һ�����ݡ�', {
    				offset: 't',
    				anim: 6
    			});
    		}
    	});
    });