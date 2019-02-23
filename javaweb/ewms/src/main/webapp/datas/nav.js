var navs = [
    {
        "title": "个人工作台",
        "icon": "fa-user-circle",
        "spread": true,
        "children": [{
            "title": "我的待办",
            "icon": "fa-pencil-square",
            "href": "system/activitiListener/findProcessing.htm"
        },
            {
                "title": "我的已办",
                "icon": "fa-share-square",
                "href": "system/activitiListener/findProcessed.htm"
            }]
    }, {
        "title": "入库管理",
        "icon": "fa-truck",
        "spread": true,
        "children": [{
            "title": "新增物资接收单",
            "icon": "fa-file-text-o",
            "href": "sheet/wzjs/order.htm?menuCode=wzjs_sheet"
            },
            {
                "title": "物资接收单管理",
                "icon": "fa-tasks",
                "href": "sheet/wzjs/manageOrder.htm"
            },
            {
                "title": "新增物资入库单",
                "icon": "fa-file-archive-o",
                "href": "sheet/rk/wzrk.htm?menuCode=receipt_details"
            },
            {
                "title": "物资入库单管理",
                "icon": "fa-file-powerpoint-o",
                "href": "sheet/rk/manageWzrk.htm"
            }]
    }, {
        "title": "库存管理",
        "icon": "fa-hand-lizard-o",
        "spread": true,
        "children": [{
            "title": "新增物资申领单",
            "icon": "fa-file-text",
            "href": "/sheet/apply/apply.htm?menuCode=XZLLD"
        }, {
            "title": "物资申领单管理",
            "icon": "fa-braille",
            "href": "/sheet/apply/manageApply.htm?menuCode=LLDGL"
        },{
            "title": "新增物资出库单",
            "icon": "fa-vcard",
            "href": "/sheet/ck/sheetCK.htm?menuCode=sheet_CK"
        }, {
            "title": "物资出库单管理",
            "icon": "fa-vcard-o",
            "href": "/sheet/ck/manageSheetCK.htm"
        },{
            "title": "新增物资退库单",
            "icon": "fa-minus",
            "href": "/sheet/tk/add?menuCode=sheet_tk_add"
        }, {
            "title": "物资退库单管理",
            "icon": "fa-minus-circle",
            "href": "/sheet/tk"
        }, {
            "title": "新增物资移库移位",
            "icon": "fa-truck",
            "href": "/sheet/ykyw/add?menuCode=sheet_YW"
        },{
            "title": "物资移库移位管理",
            "icon": "fa-edit",
            "href": "/sheet/ykyw"
        },{
            "title": "新增物资退货单",
            "icon": "fa-credit-card",
            "href": "/sheet/th/add?menuCode=sheet_th_add"
        }, {
            "title": "物资退货单管理",
            "icon": "fa-credit-card-alt",
            "href": "/sheet/th"
        },{
            "title": "新增物资调拨单",
            "icon": "fa-cart-plus",
            "href": "/sheet/db/add?menuCode=wzdbsq"
        }, {
            "title": "物资调拨单管理",
            "icon": "fa-cart-arrow-down",
            "href": "/sheet/db"
        }]
    }, {
        "title": "寄存管理",
        "icon": "fa-file-powerpoint-o",
        "spread": true,
        "children": [
            {
                "title": "新增寄存物资入库单",
                "icon": "fa-file-archive-o",
                "href": "sheet/jcwzrk/jcwzrk.htm?menuCode=deIntoadd"
            },
            {
                "title": "寄存物资入库单管理",
                "icon": "fa-file-powerpoint-o",
                "href": "sheet/jcwzrk/manageJcWzrk.htm?menuCode=deIntomanage"
            },
            {
                "title": "新增寄存物资出库单",
                "icon": "fa-vcard",
                "href": "sheet/jcwzck/jcwzck.htm?menuCode=deOutoadd"
            },
            {
                "title": "寄存物资出库单管理",
                "icon": "fa-vcard-o",
                "href": "sheet/jcwzck/manageJcWzck.htm?menuCode=deOutomanage"
            },
            {
                "title": "寄存材料库存查询",
                "icon": "fa-search",
                "href": "sheet/query/queryJCCX.htm"
            }]
    }, {
        "title": "盘点管理",
        "icon": "fa-address-book-o",
        "spread": true,
        "children": [{
            "title": "新增物资盘点单",
            "icon": "fa-navicon",
            "href": "sheet/pd/add.htm?menuCode=KCPDZD"
            },
            {
                "title": "物资盘点单管理",
                "icon": "fa-newspaper-o",
                "href": "sheet/pd/manageKcpd.htm"
            }]
    }, {
        "title": "杂入杂出",
        "icon": "fa-address-book-o",
        "spread": true,
        "children": [{
            "title": "新增物资杂入单",
            "icon": "fa-newspaper-o",
            "href": "/sheet/zr/add?menuCode=WZZR"
        }, {
            "title": "物资杂入单管理",
            "icon": "fa-navicon",
            "href": "/sheet/zr/manageWzzr.htm"
        }, {
            "title": "新增物资杂出单",
            "icon": "fa-navicon",
            "href": "/sheet/zc/add?menuCode=WZZC"
        },{
            "title": "物资杂出单管理",
            "icon": "fa-newspaper-o",
            "href": "/sheet/zc"
        }]
    }, {
        "title": "系统告警",
        "icon": "fa-bell",
        "spread": true,
        "children": [{
            "title": "保质期告警",
            "icon": "fa-bell-o",
            "href": "/system/bzqgj/showBZQGJ.htm"
        }, {
            "title": "上下限告警",
            "icon": "fa-sort-amount-desc",
            "href": "/system/sxxgj/showSXXGJ.htm"
        }]
    }, {
        "title": "查询统计",
        "icon": "fa-line-chart",
        "spread": true,
        "children": [{
            "title": "物资库存查询",
            "icon": "fa-search",
            "href": "/sheet/query/queryInventory.htm"
        }, {
            "title": "物资入库查询",
            "icon": "fa-shopping-cart",
            "href": "/sheet/query/queryWZSheetRKDetail.htm"
        }, {
            "title": "物资出库查询",
            "icon": "fa-search-minus",
            "href": "/sheet/query/queryCKCX.htm"
        }, {
            "title": "采购计划查询",
            "icon": "fa-sign-out",
            "href": "/sheet/query/queryCGJH.htm"
        }, {
            "title": "采购订单查询",
            "icon": "fa-hourglass-1",
            "href": "/sheet/query/queryCGDD.htm"
        }, {
            "title": "库存收发存报表",
            "icon": "fa-shopping-bag",
            "href": "/sheet/query/queryKCSZ.htm"
        }, {
            "title": "计划库存管理报表",
            "icon": "fa-shopping-bag",
            "href": "/sheet/query/queryPlanDetail.htm"
        }]
    }, {
        "title": "产品库管理",
        "icon": "fa-cubes",
        "spread": true,
        "children": [{
            "title": "产品入库",
            "icon": "fa-cube",
            "href": "/mes/manageMesRk.htm"
        }, {
            "title": "产品出库",
            "icon": "fa-cube",
            "href": "/mes/manageMesCk.htm"
        }]
    }, {
        "title": "系统管理",
        "icon": "fa-gear",
        "spread": true,
        "children": [{
            "title": "菜单管理",
            "icon": "fa-sliders",
            "href": "system/menu/manageMenu.htm"
        }, {
            "title": "部门管理",
            "icon": "fa-users",
            "href": "system/dept/manageDepart.htm"
        }, {
            "title": "人员管理",
            "icon": "fa-user",
            "href": "system/user/manageUser.htm"
        }, {
            "title": "角色管理",
            "icon": "fa-user-times",
            "href": "system/role/manageRole.htm"
        }, {
            "title": "物料分类管理",
            "icon": "fa-pencil-square-o",
            "href": "system/spare"
        }, {
            "title": "物料管理",
            "icon": "fa-puzzle-piece",
            "href": "system/material"
        }, {
            "title": "任务管理",
            "icon": "fa-tasks",
            "href": "system/task"
        }, {
            "title": "库房库区管理",
            "icon": "fa-fort-awesome",
            "href": "system/ware"
        }, {
            "title": "货位标签打印",
            "icon": "fa-print",
            "href": "system/ware/printLocation"
        }, {
            "title": "数据字典",
            "icon": "fa-legal",
            "href": "system/dic"
        }, {
            "title": "单据管理",
            "icon": "fa-file",
            "href": "formTemplate/formTemplateManage/formTemplate.htm"
        }, {
            "title": "流程管理",
            "icon": "fa-arrows-v",
            "href": "system/activitiManage/modelList.htm"
        }, {
            "title": "流程监控",
            "icon": "fa-warning",
            "href": "system/activitiListener/processList.htm"
        }, {
            "title": "供应商管理",
            "icon": "fa-university",
            "href": "system/provider/manageProvider.htm"
        }, {
            "title": "使用单位管理",
            "icon": "fa-th-large",
            "href": "system/useDep/manageUseDep.htm"
        }, {
            "title": "申请单位管理",
            "icon": "fa-th-large",
            "href": "system/applyDep/manageApplyDep.htm"
        }, {
            "title": "接口日志",
            "icon": "fa-calendar",
            "href": "system/taskLog/manageTaskLog.htm"
        }, {
            "title": "系统日志",
            "icon": "fa-calendar",
            "href": "system/log/manageLog.htm"
        }
        ]
    }];