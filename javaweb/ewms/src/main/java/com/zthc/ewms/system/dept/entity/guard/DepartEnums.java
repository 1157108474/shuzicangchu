package com.zthc.ewms.system.dept.entity.guard;

public class DepartEnums {

    /**
     * ����
     */
    public enum TypeEnum {
        BRANCH(0, "�ֲ�"), DEPART(1, "����");
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
    /**
     * ����
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

}
