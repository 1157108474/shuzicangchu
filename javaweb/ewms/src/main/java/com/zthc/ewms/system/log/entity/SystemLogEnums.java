package com.zthc.ewms.system.log.entity;

public class SystemLogEnums {

    /**
     * ----��־����---
     **/
    public enum LogObject {
        MATERIAL_RECEIVING(100, "���ʽ���", "WZJS"), MATERIAL_STORAGE(200, "�������", "RK"), MATERIAL_JCSTORAGE(210, "�Ĵ��������", "wzjcrkd"), APPLY_USE(300, "��������", "WZLLD")
        , MATERIAL_OUTPOURING(400, "���ʳ���", "CKD"),MATERIAL_JCOUTPOURING(410, "�Ĵ����ʳ���", "wzjcckd"),
        MOVE_HOUSE(500, "�ƿ���λ", "YKYW"), MATERIAL_RETREATING(600, "�����˿�", "WZTK"), MATERIAL_RETURN(700, "�����˻�", "WZTH")
        , MATERIAL_ALLOCATION(800, "���ʵ���", "WZDBD"),
        CHECK_LIST(900, "�̵㵥", "KCPD"), INVENTORY_STATEMENT(1000, "�����֧����", ""), ORGANIZATION(1100, "��֯����", ""), PERSON_MANAGEMENT(1200, "��Ա����", ""),
        MENU_MANAGEMENT(1300, "�˵�����", ""), ROLE_MANAGEMENT(1400, "��ɫ����", ""), MATERIAL_CLASS(1500, "���Ϸ���", ""), MATERIA_MANAGEMENT(1600, "���Ϲ���", ""),
        STOREHOUSE_MANAGEMENT(1700, "�ⷿ��������", ""), LABEL_PRINT(1800, "��λ��ǩ��ӡ", ""), PROVIDER_MANAGEMENT(1900, "��Ӧ�̹���", ""), USEDEP_MANAGEMENT(2000, "ʹ�õ�λ����", ""),
        APPLYDEP_MANAGEMENT(2100, "���뵥λ����", ""), DOCUMENT_MANAGEMENT(2200, "���ݹ���", ""), PROCESS_MANAGEMENT(2300, "���̹���", ""), TASK_MANAGEMENT(2400, "�������", ""),
        DATA_DICTIONARY(2500, "�����ֵ�", ""), SYSTEM_MANAGEMENT(2600, "�ֿ�ϵͳ", ""), ACTIVITI_OPERATION(2700, "�ֿ�ϵͳ", ""),
        WZZC(2800, "�����ӳ�", "WZZC"), WZZR(2900, "��������", "ZR");

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
     * ----��������---
     **/
    public enum LogAction {
        ADD(100, "����"), EDIT(200, "�༭"), DELETE(300, "ɾ��"), SHOW(400, "�鿴"), IMPORT(500, "����"), EXPORT(600, "����"),
        MAINTAIN(700, "ά��"), MAKING(800, "�Ƶ�"), UNAPPROVED(900, "δ����"), APPROVAL_FINSH(1000, "�������"),APPROVAL_NEXTPART(1001, "�ύ����"),
        ANOTHERPIE(1002, "����"),COUNT(1100, "ͳ��"),
        ALLOT_ROLE(1200, "�����ɫ"), ALLOT_PERMISSION(1300, "����Ȩ��"), PUSH_ERP(1400, "����ERP"), PULL_ERP(1500, "����ERP"),
        USER_LOGIN(1600, "�û���¼"), ALLOT_USER(1700, "�����û�"), REMOVE_USER(1800, "�Ƴ��û�"),
        ADD_DETAIL(210, "�����ϸ"), DELETE_DETAIL(220, "ɾ����ϸ"), UPLOAD_FILE(1900, "�ϴ�����"), ADD_SONDETAIL(230, "������ϸ");

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
