    var layout = [
    	{name: '菜单名称',field: 'name',treeNodes: true,align: "center"}
        ,{name: '菜单编码',field: 'code',align: "center",style: 'width: 80px'}
        , {name: '类型',field: 'type',style: 'width: 80px',align: "center",render: function(row) {
    		if(row.type == 1) {
    			return '目录';
    		} else if(row.type == 2) {
    			return '页面';
    		} else {
    			return '';
    		}
    	}}
    	, {name: '图标',field: 'icon',style: 'width: 80px',align: "center",render: function(row) {
            var icon = (null==row.icon?'':row.icon);
			return '<i class="fa ' + icon + '" aria-hidden="true"></i>';
    	}}
    	, {name: '菜单URL',field: 'url',style: 'width: 200px',align: "center",render: function(row) {
            if(row.type == 1) {
                return '#';
            } else if(row.type == 2 && null != row.url) {
                return row.url;
            } else {
                return '';
            }
        }}
    	, {name: '权限标识',field: 'authIdentity',style: 'width: 300px',align: "center",render: function(row) {
            if(null ==row.authIdentity) {
                return '';
            } else{
                return row.authIdentity;
            }
        }}
        , {name: '排序',field: 'sort',align: "center",style: 'width: 60px'}
        , {name: '状态',field: 'status',style: 'width: 60px',align: "center",render: function(row) {
    		if(row.status == 1) {
    			return '启用';
    		} else if(row.status == 0) {
    			return '禁用';
    		} else {
    			return '';
    		}
    	}}
    	,{name: '按钮模式',field: 'buttonModeStr',style: 'width: 100px',align: "center"}
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
                        elem: '#demo', //传入元素选择器
                        spreadable: false, //设置是否全展开，默认不展开
                        checkbox: true,
                        nodes: ret.data,
                        data:{ran:ran++},
                        layout: layout
                    });
                    form.render();
                }
            });
        });

    	//添加
    	function addNews(edit) {
    		var tit = '添加菜单',
    			url = '/system/menu/addMenu.htm';
    		if(edit) {
    			tit = '修改菜单';
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

    	//添加
    	$("#add").on('click', function() {
    		addNews();
    	});
    	//编辑
    	$("#edit").on('click', function() {
    		var arr = layui.getSelected(tree1);
    		if(arr.length == 1) {
    			addNews(arr[0]);
    		} else {
    			layer.msg('请选择一条数据。', {
    				offset: 't',
    				anim: 6
    			});
    		}
    	});
		//打开功能按钮列表
    	$('#manageButton').on('click', function() {
    		var arr = layui.getSelected(tree1);
    		if(arr.length != 1) {
    			layer.msg('请选择一条数据。', {
    				offset: 't',
    				anim: 6
    			});
    		} else {
    			var data = arr[0];
    			if(data.type == '1') {
    				layer.msg('目录不能分配权限。', {
    					offset: 't',
    					anim: 6
    				});
    			} else {
    				layer.open({
    					type: 2,
    					title: '功能管理',
    					area: ['90%', '90%'],
    					fixed: false,
    					content: '/system/menu/manageButton.htm?code=' + data.code
    				});
    			}
    		}
    	});

    	//批量删除
    	$("#delAll").on("click", function(e) {
    		var arr = layui.getSelected(tree1);
    		var codes = [];
    		if(arr.length > 0) {
    			for(var i in arr) {
                    codes.push(arr[i].id);
    			}
    			layer.confirm('确定删除选中的菜单？', {
    				icon: 3,
    				title: '提示信息'
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
    			layer.msg('请选择一条数据。', {
    				offset: 't',
    				anim: 6
    			});
    		}
    	});
    });