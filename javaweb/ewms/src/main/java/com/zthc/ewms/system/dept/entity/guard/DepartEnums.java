package com.zthc.ewms.system.dept.entity.guard;

public class DepartEnums {

    /**
     * 类型
     */
    public enum TypeEnum {
        BRANCH(0, "分部"), DEPART(1, "部门");
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
     * 类型
     */
    public enum StatusEnum {
        ENABLE(1, "启用"), DISABLE(0, "禁用");
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
