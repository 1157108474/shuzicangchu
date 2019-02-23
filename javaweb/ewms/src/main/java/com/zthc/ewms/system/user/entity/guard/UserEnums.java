package com.zthc.ewms.system.user.entity.guard;

public class UserEnums {

    /**
     * ״̬
     */
    public enum StatusEnum {
        ENABLE(1, "����"), DISABLE(0, "����");
        private int status;
        private String meaning;

        StatusEnum(int status, String meaning) {
            this.status = status;
            this.meaning = meaning;
        }

        public int getStatus() {
            return status;
        }

        public String getMeaning() {
            return meaning;
        }
    }

    /**
     * �Ա�
     */
    public enum SexEnum {
        BOY(0, "��"), GIRL(1, "Ů");
        private int sex;
        private String meaning;

        SexEnum(int sex, String meaning) {
            this.sex = sex;
            this.meaning = meaning;
        }

        public int getSex() {
            return sex;
        }

        public String getMeaning() {
            return meaning;
        }
    }

    //����
    public enum TypeEnum {
        TRANSFER(0, "����Ǩ��"), EWMS(1, "ϵͳ����"), ERP(2, "ERPͬ��");
        private int type;
        private String meaning;

        TypeEnum(int type, String meaning) {
            this.type = type;
            this.meaning = meaning;
        }

        public int getType() {
            return type;
        }

        public String getMeaning() {
            return meaning;
        }

    }

    //��Ա��Χ����
    public enum ScopeTypeEnum {
        ORGANIZATION(1, "������֯"), WAREHOUSE(2, "�ⷿ����"), SPAREPARTSCATE(3, "���Ϸ�Χ"), USEDEP(4, "��Ͻ����");
        private int type;
        private String meaning;

        ScopeTypeEnum(int type, String meaning) {
            this.type = type;
            this.meaning = meaning;
        }

        public int getType() {
            return type;
        }

        public String getMeaning() {
            return meaning;
        }

    }
}
