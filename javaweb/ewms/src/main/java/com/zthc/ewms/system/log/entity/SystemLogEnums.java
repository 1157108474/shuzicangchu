package com.zthc.ewms.system.log.entity;

public class SystemLogEnums {

    /**
     * ----日志对象---
     **/
    public enum LogObject {
        MATERIAL_RECEIVING(100, "物资接收", "WZJS"), MATERIAL_STORAGE(200, "物资入库", "RK"), MATERIAL_JCSTORAGE(210, "寄存物资入库", "wzjcrkd"), APPLY_USE(300, "领用申请", "WZLLD")
        , MATERIAL_OUTPOURING(400, "物资出库", "CKD"),MATERIAL_JCOUTPOURING(410, "寄存物资出库", "wzjcckd"),
        MOVE_HOUSE(500, "移库移位", "YKYW"), MATERIAL_RETREATING(600, "物资退库", "WZTK"), MATERIAL_RETURN(700, "物资退货", "WZTH")
        , MATERIAL_ALLOCATION(800, "物资调拨", "WZDBD"),
        CHECK_LIST(900, "盘点单", "KCPD"), INVENTORY_STATEMENT(1000, "库存收支报表", ""), ORGANIZATION(1100, "组织机构", ""), PERSON_MANAGEMENT(1200, "人员管理", ""),
        MENU_MANAGEMENT(1300, "菜单管理", ""), ROLE_MANAGEMENT(1400, "角色管理", ""), MATERIAL_CLASS(1500, "物料分类", ""), MATERIA_MANAGEMENT(1600, "物料管理", ""),
        STOREHOUSE_MANAGEMENT(1700, "库房库区管理", ""), LABEL_PRINT(1800, "货位标签打印", ""), PROVIDER_MANAGEMENT(1900, "供应商管理", ""), USEDEP_MANAGEMENT(2000, "使用单位管理", ""),
        APPLYDEP_MANAGEMENT(2100, "申请单位管理", ""), DOCUMENT_MANAGEMENT(2200, "单据管理", ""), PROCESS_MANAGEMENT(2300, "流程管理", ""), TASK_MANAGEMENT(2400, "任务管理", ""),
        DATA_DICTIONARY(2500, "数据字典", ""), SYSTEM_MANAGEMENT(2600, "仓库系统", ""), ACTIVITI_OPERATION(2700, "仓库系统", ""),
        WZZC(2800, "物资杂出", "WZZC"), WZZR(2900, "物资杂入", "ZR");

        private int code;
        private String msg;
        private String type;

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        LogObject(int code, String msg, String type) {
            this.code = code;
            this.msg = msg;
            this.type = type;
        }

        public static LogObject getByType(String type) {
            for (LogObject log : values()) {
                if (log.getType().equalsIgnoreCase(type)) {
                    return log;
                }
            }
            return null;
        }
    }

    /**
     * ----操作类型---
     **/
    public enum LogAction {
        ADD(100, "新增"), EDIT(200, "编辑"), DELETE(300, "删除"), SHOW(400, "查看"), IMPORT(500, "导入"), EXPORT(600, "导出"),
        MAINTAIN(700, "维护"), MAKING(800, "制单"), UNAPPROVED(900, "未审批"), APPROVAL_FINSH(1000, "审批完成"),APPROVAL_NEXTPART(1001, "提交审批"),
        ANOTHERPIE(1002, "另派"),COUNT(1100, "统计"),
        ALLOT_ROLE(1200, "分配角色"), ALLOT_PERMISSION(1300, "分配权限"), PUSH_ERP(1400, "推送ERP"), PULL_ERP(1500, "接收ERP"),
        USER_LOGIN(1600, "用户登录"), ALLOT_USER(1700, "分配用户"), REMOVE_USER(1800, "移除用户"),
        ADD_DETAIL(210, "添加明细"), DELETE_DETAIL(220, "删除明细"), UPLOAD_FILE(1900, "上传附件"), ADD_SONDETAIL(230, "分配明细");

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        LogAction(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
